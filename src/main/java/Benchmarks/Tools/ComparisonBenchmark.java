package Benchmarks.Tools;

import Benchmarks.Tools.Results.ComparisonResults;
import Benchmarks.Tools.Results.BenchmarkResults;
/**
 * Class to compare two benchmarks
 */
public class ComparisonBenchmark {
    private final AbstractBenchmark javaBase,nativeBase;
    private BenchmarkResults javaResults,nativeResults;
    private final Integer times;
    private final String name;
    public ComparisonBenchmark(AbstractBenchmark java, AbstractBenchmark Native, Integer times, String name) {
        javaBase = java;
        nativeBase = Native;
        this.times = times;
        this.name = name;
    }

    /**
     * Runs the benchmarks in parallel with threading and returns the results
     * @return the results of the Benchmark
     */
    public ComparisonResults run() {

        Thread javaThread = new Thread(() -> javaResults = javaBase.runBenchmark(times));
        Thread nativeThread = new Thread(() -> nativeResults = nativeBase.runBenchmark(times));
        javaThread.start();
        nativeThread.start();
        try {
            javaThread.join();
            nativeThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new ComparisonResults(nativeResults,javaResults, name, times);
    }
    /**
     * Gets the results of the java benchmark
     * @return the results of the benchmark
     */
    public BenchmarkResults getJavaResults() {
        return javaResults;
    }

    /**
     * Gets the results of the native benchmark
     * @return the results of the benchmark
     */
    public BenchmarkResults getNativeResults() {
        return nativeResults;
    }
}
