import os


class HtoRs:
    class method_sig:
        def __init__(self, name: str, ret_type: str, args: list):
            self.name = name
            self.ret_type = ret_type
            self.args = args

        def __repr__(self):
            return f"method_sig(name={self.name}, ret_type={self.ret_type}, args={self.args})"

    """ Convert C headers to Rust FFI bindings.
    """

    def __init__(self, fname: str, dest: str):
        self.file_name = os.path.abspath(fname)
        self.JNIdeps = ["JNIEnv", "objects::JObject"]  # Raw jni dep paths
        self.sysdeps = []  # jni sys deps
        self.dest = os.path.abspath(dest)
        self.methods = self.extract_methods()
        self.methods = [self.remove_unused(m) for m in self.methods]
        self.methsign = [self.to_method_signature(m) for m in self.methods]
        self.rust_methods = [self.method_sig_to_rust(m) for m in self.methsign]
        print(self.build_file(self.rust_methods))

    def add_dep(self, dep: str):
        """Add a dependency to the JNIdeps list."""
        _ = self.JNIdeps.append(dep) if dep not in self.JNIdeps else None

    def extract_methods(self):
        """Extract methods from header file."""
        methods = []
        lastline = ""
        lastlineismethod = False
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
                    print(lastline)
                    lastlineismethod = False
        return methods

    def remove_newlines(self, string: str):
        return "".join(string.splitlines())

    def remove_unused(self, string: str):
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
        print(f"return type: {return_type}")
        print(f"name: {name}")
        print(f"args: {args}")
        return self.method_sig(name, return_type, args)

    def method_sig_to_rust(self, method_sig: method_sig):
        arg_dict = {}
        argnum = 0
        for arg in method_sig.args:
            arg = arg.lstrip().rstrip()
            if arg.endswith(");"):
                arg = arg[:-2]
            print(f"arg: {arg}")
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
        return f'pub extern "System" fn {method_sig.name}({args}) -> {method_sig.ret_type} {{\n\t\n}}'

    def gen_uses(self):
        """Generate the use statements for the file."""
        return "\n".join([f"use super::{dep};" for dep in self.JNIdeps])

    def build_deps(self):
        """Build the dependencies for the file."""
        sysdepstring = ""
        if len(self.sysdeps) > 0:
            sysdepstring = f",sys::{{{', '.join(self.sysdeps)}}}"

        return f"use jni::{{{', '.join(self.JNIdeps)}{sysdepstring}}};"

    def build_file(self, methods: list):
        file = f"{self.build_deps()}\n\n\n"
        for method in methods:
            file += f"{method} \n\n"
        return file


if __name__ == "__main__":
    HtoRs(
        "./target/headers/Benchmarks_NativeBenchmarks_String_nReplace.h",
        "./target/rust/Benchmarks_NativeBenchmarks_String_nReplace.rs",
    )
