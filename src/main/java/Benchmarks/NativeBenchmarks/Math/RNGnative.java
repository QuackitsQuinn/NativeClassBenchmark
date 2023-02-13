package Benchmarks.NativeBenchmarks.Math;

import Benchmarks.Tools.AbstractBenchmark;

public class RNGnative extends AbstractBenchmark {
    static {
        System.loadLibrary("RNGnative");
    }
    @Override
    protected native void setup();

    @Override
    protected native void execute();
}

