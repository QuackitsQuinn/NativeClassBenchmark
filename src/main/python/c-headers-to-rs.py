import os

class HtoRs:
    """ Convert C headers to Rust FFI bindings.
    """
    def __init__(self, fname: str, dest: str):
        self.file_name = os.path.abspath(fname)

        self.dest = os.path.abspath(dest)

if __name__ == "__main__":
    HtoRs("./target/headers/Benchmarks_NativeBenchmarks_String_nReplace.h","./target/rust/Benchmarks_NativeBenchmarks_String_nReplace.rs")