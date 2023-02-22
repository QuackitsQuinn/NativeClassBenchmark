package Benchmarks.JavaBenchmarks.Math;

import Benchmarks.Tools.AbstractBenchmark;

import java.util.Random;

public class jRNG extends AbstractBenchmark {
    private Random randomGenerator;
    @Override
    protected void setup() {;
    }

    @Override
    protected void execute() {
        randomGenerator = new Random(); // lives in here because i dont know how to do it in setup in rust
        int i = randomGenerator.nextInt();
    }
}
