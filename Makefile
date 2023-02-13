## Makefile for compiling and moving the rust benchmarks
## Searches for cargo.toml files in /src/main/rust and compiles them, then moves the binaries to /bin/rust.
SRC_DIR = ./src/main/rust
LIB_DIR = ./bin/libs
PROCESSER = ./src/main/python/compile-benchmarks.py

all:
	@echo $(PROCESSER) $(SRC_DIR) $(LIB_DIR)
	@mkdir -p $(LIB_DIR)
## Sadly, tomllib was introduced in python3.11
	@python3.11 $(PROCESSER) $(SRC_DIR) $(LIB_DIR) 