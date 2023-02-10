package Benchmarks.Tools;

import Benchmarks.Tools.Results.ComparisionResults;
import Benchmarks.Tools.Results.BenchmarkResults;
/**
 * Class to compare two benchmarks
 */
public class ComparisonBenchmark {
    private final AbstractBenchmark javaBase,nativeBase;
    private BenchmarkResults javaResults,nativeResults;
    public ComparisonBenchmark(AbstractBenchmark java, AbstractBenchmark Native) {
        javaBase = java;
        nativeBase = Native;
    }

    /**
     * Runs the benchmarks in parallel with threading and returns the results
     * @param times the amount of times to run the benchmark
     * @return the results of the Benchmark
     */
    public ComparisionResults run(int times) {

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
        return new ComparisionResults(nativeResults,javaResults);
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
