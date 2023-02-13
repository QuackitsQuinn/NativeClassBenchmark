import os
import subprocess
import sys
import tomllib

class CompileBenchmarks:
    def __init__(self, search_path: str, dest_path:str):
        self.os = sys.platform
        self.search_path = os.path.abspath(search_path)
        self.dest_path = os.path.abspath(dest_path)
        print(f"Searching for Cargo.toml files in {search_path}")
        paths = self.search_cargo(search_path)
        print(f"Found {len(paths)} projects.")
        print("Compiling...")
        self.compile_paths(paths)
    def search_cargo(self,search_path: str):
        cargo_paths = []
        cargo_name = "Cargo.toml"
        for root,dirs,files in os.walk(search_path):
            if cargo_name in files:
                abspath = os.path.abspath(root)
                cargo_paths.append(abspath)
        return cargo_paths
    def compile_paths(self,pathlist: list):
        oldcwd = os.getcwd()
        for x in pathlist:
            os.chdir(x)
            cargocommand = "cargo build --release"
            print(f"Building {self.get_project_name(x)}")
            subprocess.call(cargocommand.split(" "))
            self.mv_lib(x)

        os.chdir(oldcwd)

    def get_project_name(self,directory: str):
        try:
            with open(os.path.join(directory,"Cargo.toml")) as cargo:
                cargo = tomllib.loads(cargo.read())
                return cargo['package']['name']
        except Exception as e:
            print(e)

    def get_lib_path(self, rootpath: str):
        dynamiclibname = ""
        projectname = self.get_project_name(rootpath)
        if self.os.startswith("darwin"):
            dynamiclibname = f"lib{projectname}.dylib"
        elif self.os.startswith("linux"):
            dynamiclibname = f"{projectname}.so"
        elif self.os.startswith("win32"):
            dynamiclibname = f"{projectname}.dll"
        return os.path.join(rootpath,f'target/release/{dynamiclibname}')
    def mv_lib(self,path):
        libpath = self.get_lib_path(path)
        fname = libpath.split("/")[-1]
        os.rename(libpath,os.path.join(self.dest_path,fname))

        
if __name__ == "__main__":
    # CompileBenchmarks("./src/main/rust","./bin/libs")
    CompileBenchmarks(sys.argv[1],sys.argv[2])