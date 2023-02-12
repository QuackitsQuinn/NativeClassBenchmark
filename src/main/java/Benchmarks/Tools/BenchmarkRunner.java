package Benchmarks.Tools;

import java.util.ArrayList;

import Benchmarks.Tools.Results.BenchmarkResults;
import Benchmarks.Tools.Results.ComparisonResults;

public class BenchmarkRunner {
    private ArrayList<ComparisonBenchmark> benchmarks = new ArrayList<>();
    public BenchmarkRunner() {
    }
    public void addBenchmark(ComparisonBenchmark benchmark, int times) {

        benchmarks.add(benchmark);
    }
    public void runBenchmarks() {

        for (ComparisonBenchmark benchmark : benchmarks) {
            benchmark.run().print();
        }
    }
}
