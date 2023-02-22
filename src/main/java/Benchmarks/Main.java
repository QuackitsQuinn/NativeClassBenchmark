package Benchmarks;

import Benchmarks.JavaBenchmarks.Math.jRNG;
import Benchmarks.NativeBenchmarks.Math.nRNG;
import Benchmarks.Tools.ComparisonBenchmark;
import Benchmarks.Tools.Results.ComparisonResults;

public class Main {
    public static void main(String[] args) {
        ComparisonBenchmark benchmark = new ComparisonBenchmark(new jRNG(),new nRNG(), 1000000,"RNG");
        ComparisonResults results = benchmark.run();
        results.print();
    }
}
