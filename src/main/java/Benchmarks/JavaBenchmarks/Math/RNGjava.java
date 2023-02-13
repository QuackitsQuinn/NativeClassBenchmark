package Benchmarks.JavaBenchmarks.Math;

import Benchmarks.Tools.AbstractBenchmark;

import java.util.Random;
import java.util.random.RandomGenerator;

public class RNGjava extends AbstractBenchmark {
    private Random randomGenerator;
    @Override
    protected void setup() {
        randomGenerator = new Random();
    }

    @Override
    protected void execute() {
        int i = randomGenerator.nextInt();
    }
}
