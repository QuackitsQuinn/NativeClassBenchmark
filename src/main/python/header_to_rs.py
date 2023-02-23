"""Convert C headers to Rust FFI bindings.
"""
import os
import customlogger # pylint: disable=import-error 
# ^ Weirld __init__.py stuff to make imports work
class HtoRs:
    """ Convert C headers to Rust FFI bindings.
    """
    class MethodSig:
        """A method signature."""
        def __init__(self, name: str, ret_type: str, args: list):
            self.name = name
            self.ret_type = ret_type
            self.args = args
        def __repr__(self):
            return f"method_sig(name={self.name}, ret_type={self.ret_type}, args={self.args})"


    def __init__(self, fname: str, dest: str):
        """Initializes and processes the header file."""
        # TODO: Add logging
        self.logger = customlogger.Logger("HeaderToRust", customlogger.Logger.Level.INFO)
        self.file_name = os.path.abspath(fname)
        self.jni_deps = ["JNIEnv", "objects::JObject"]  # Raw jni dep paths
        self.sysdeps = []  # jni sys deps
        self.dest = os.path.abspath(dest)
        self.methods = self.extract_methods()
        if len(self.methods) == 0:
            self.logger.failure(f"Failed to extract methods from {self.file_name}.")
        self.methods = [self.remove_unused(m) for m in self.methods]
        self.methsign = [self.to_method_signature(m) for m in self.methods]
        self.rust_methods = [self.method_sig_to_rust(m) for m in self.methsign]
        self.built = self.build_file(self.rust_methods)
        self.logger.info(f"Built {len(self.rust_methods)} methods.")
        self.write_file()
        self.logger.success(f"Successfully wrote {self.dest}.")

    def add_dep(self, dep: str) -> None:
        """Add a dependency to the JNIdeps list."""
        _ = self.jni_deps.append(dep) if dep not in self.jni_deps else None # i like this for no reason

    def extract_methods(self) -> list:
        """Extract methods from header file.
           Only place where the header file is read.
        """
        methods = []
        lastline = ""
        lastlineismethod = False
        try:
            with open(self.file_name, "r") as f:
                lines = f.readlines()
                for line in lines:
                    if line.startswith("JNIEXPORT"):
                        lastline = line
                        lastlineismethod = True
                    elif lastlineismethod:
                        lastline = self.remove_newlines(lastline).lstrip().rstrip()
                        lastline += line.lstrip().rstrip()
                        lastline = self.remove_newlines(lastline)
                        methods.append(lastline)
                        lastlineismethod = False
        except Exception as e:
            self.logger.fatal(f"Error reading file {self.file_name}: {e}")
        self.logger.info(f"Found {len(methods)} methods.")
        return methods

    def remove_newlines(self, string: str):
        """Remove newlines from a string."""
        return "".join(string.splitlines()) # weird but works

    def remove_unused(self, string: str):
        """Remove unused parts of a method declaration and clean it up."""
        return (
            string.replace("JNIEXPORT", "")
            .replace("JNICALL", "")
            .replace("*", "")
            .lstrip()
            .rstrip()
        )

    def to_method_signature(self, string: str):
        """Convert a method declaration to a method signature."""
        return_type = string.split(" ")[0]
        name = string.split(" ")[2].split("(")[0]
        args = string.split("(")[1].split(",")
        self.logger.debug(f"Found method {name} with args {args}")
        return self.MethodSig(name, return_type, args)

    def method_sig_to_rust(self, method_sig: MethodSig):
        """Convert a method signature to a rust method."""
        arg_dict = {}
        argnum = 0
        for arg in method_sig.args:
            arg = arg.lstrip().rstrip()
            if arg.endswith(");"):
                arg = arg[:-2]
            argnum += 1
            if arg == "JNIEnv":
                arg_dict["_env"] = "JNIEnv"
            elif arg == "jobject":
                arg_dict["_obj"] = "jobject"
            else:
                arg_dict[f"Arg{argnum}"] = arg
                self.sysdeps.append(arg)  # lets hope all other vars are sys deps
        if method_sig.ret_type == "void":
            method_sig.ret_type = "()"
        args = ", ".join([f"{arg}: {arg_dict[arg]}" for arg in arg_dict])
        self.logger.debug(f"Converted {method_sig.name} to rust method with args ({args})")
        return f'#[no_mangle]\npub extern "System" fn {method_sig.name}({args}) -> {method_sig.ret_type} {{\n\t\n}}'

    def build_deps(self) -> str:
        """Build the dependencies for the file."""
        sysdepstring = ""
        if len(self.sysdeps) > 0:
            sysdepstring = f",sys::{{{', '.join(self.sysdeps)}}}"
        self.logger.debug(f"Building deps with {self.jni_deps} and {self.sysdeps}")
        return f"use jni::{{{', '.join(self.jni_deps)}{sysdepstring}}};"

    def build_file(self, methods: list) -> str:
        """Compiles all of the funky information into a rust ffi file."""
        file = f"{self.build_deps()}\n\n\n"
        for method in methods:
            file += f"{method} \n\n"
        return file
    def write_file(self) -> None:
        """Writes the file to the destination."""
        self.logger.info(f"Writing file to {self.dest}")
        if not os.path.exists(os.path.dirname(self.dest)):
            os.makedirs(os.path.dirname(self.dest))
        with open(self.dest, "w") as f:
            f.write(self.built)
class convFolder:
    """Converts all headers in a folder to rust files."""
    def __init__(self, folder: str, dest: str):
        """Initializes and processes the folder."""
        self.logger = customlogger.Logger("All", customlogger.Logger.Level.INFO)
        self.folder = os.path.abspath(folder)
        self.dest = os.path.abspath(dest)
        self.files = self.get_files()
        self.logger.info(f"Found {len(self.files)} files to convert.")
        self.converted = []
        for file in self.files:
            self.logger.info(f"Converting {file}")
            self.converted.append(
                HtoRs(file, file.replace(self.folder, self.dest).replace(".h", ".rs"))
            )
            self.logger.success(f"Successfully converted {file}")
        self.logger.success(f"Successfully converted {len(self.converted)} files.")

    def get_files(self) -> list:
        """Get all files in the folder."""
        files = []
        for root, _, file in os.walk(self.folder):
            for f in file:
                if f.endswith(".h"):
                    files.append(os.path.join(root, f))
        return files

if __name__ == "__main__":
    convFolder("./target/headers", "./target/rust")
