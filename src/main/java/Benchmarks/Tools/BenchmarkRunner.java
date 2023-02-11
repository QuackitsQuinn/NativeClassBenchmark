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
            benchmark.run();
        }
    }
    private void printBenchmarkResults(ComparisonResults benchmark) {
        BenchmarkResults javaResults = benchmark.getJavaBenchmark();
        BenchmarkResults nativeResults = benchmark.getNativeBenchmark();
        System.out.println("┌┤"+javaResults.getName());
        System.out.println("┌┤Java Results");
        System.out.println("├┤ Total Time: " + javaResults.getElapsedTime() + "ns");
        System.out.println("├┤ Average Time Per Run: " + javaResults.getAvgPerRun() + "ns");
        System.out.println("└┤ Runs: " + javaResults.getRunTimes());
        System.out.println();
        System.out.println("┌┤Native Results");
        System.out.println("├┤ Total Time: " + nativeResults.getElapsedTime() + "ns");
        System.out.println("├┤ Average Time Per Run: " + nativeResults.getAvgPerRun() + "ns");
        System.out.println("└┤ Runs: " + nativeResults.getRunTimes());
    }
}
