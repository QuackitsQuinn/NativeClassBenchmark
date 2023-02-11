package Benchmarks.Tools.Results;

import Benchmarks.Tools.Base;

public class ComparisonResults {
    private final BenchmarkResults nativeBenchmark, javaBenchmark;
    private final Base faster;
    private final long difference;

    /**
     * Results of a native/java comparison benchmark
     * @param nativeBenchmark the native based benchmark
     * @param javaBenchmark the java based benchmark
     */
    public ComparisonResults(BenchmarkResults nativeBenchmark, BenchmarkResults javaBenchmark) {
        this.nativeBenchmark = nativeBenchmark;
        this.javaBenchmark = javaBenchmark;
        difference = Math.abs(this.nativeBenchmark.getElapsedTime()-this.javaBenchmark.getElapsedTime());
        if (this.nativeBenchmark.getElapsedTime() < this.javaBenchmark.getElapsedTime()) {
            faster = Base.NATIVE;
        } else {
            faster = Base.JAVA;
        }
        }

    /**
     * Get the results of the native benchmark
     * @return the results of the native benchmark
     */
    public BenchmarkResults getNativeBenchmark() {
        return nativeBenchmark;
    }

    /**
     * Get the results of the java benchmark
     * @return the results of the java benchmark
     */
    public BenchmarkResults getJavaBenchmark() {
        return javaBenchmark;
    }

    /**
     * Get the base that was faster
     * @return the base that was faster
     */
    public Base getFaster() {
        return faster;
    }

    /**
     * Get the difference in time between the two benchmarks. Always positive.
     * @return the difference in time between the two benchmarks
     */
    public long getDifference() {
        return difference;
    }
}

