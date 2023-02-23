## Makefile for compiling and moving the rust benchmarks
## Searches for cargo.toml files in /src/main/rust and compiles them, then moves the binaries to /bin/lib.
SRC_DIR = ./src/main/rust
LIB_DIR = ./bin/lib
PROCESSER = ./src/main/python/compile-benchmarks.py

all:
	@echo $(PROCESSER) $(SRC_DIR) $(LIB_DIR)
	@mkdir -p $(LIB_DIR)
## tomllib was introduced in python3.11 so using 3.11 is required.
	@python3.11 $(PROCESSER) $(SRC_DIR) $(LIB_DIR)
HEADER_DIR = ./target/headers
RUST_DIR = ./target/rust
CONVERTER = ./src/main/python/header_to_rs.py
## Converts the headers to rust forign function declarations.
create_headers:
	@echo "Cleaning to remove old headers"
	@mvn clean
	@echo "Creating headers"
	@mvn compile
	@python3.11 $(CONVERTER) $(HEADER_DIR) $(RUST_DIR)
