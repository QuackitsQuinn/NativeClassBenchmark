package Benchmarks.NativeBenchmarks.Math;

import Benchmarks.Tools.AbstractBenchmark;
import Benchmarks.Tools.Utils;

public class nRNG extends AbstractBenchmark {
    static {
        System.load(Utils.getLibString("random_benchmark"));
    }
    @Override
    protected native void setup();

    @Override
    protected native void execute();
}

