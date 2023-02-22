package Benchmarks.Tools.Results;

import Benchmarks.Tools.Base;
import Benchmarks.Tools.Utils;

public class ComparisonResults {
    private final BenchmarkResults nativeBenchmark, javaBenchmark;
    private final Base faster;
    private final long difference;
    private final int runTimes;
    private final String name;

    /**
     * Results of a native/java comparison benchmark
     * @param nativeBenchmark the native based benchmark
     * @param javaBenchmark the java based benchmark
     * @param runTimes the number of times the benchmark was run
     */
    public ComparisonResults(BenchmarkResults nativeBenchmark, BenchmarkResults javaBenchmark, String name, int runTimes){
        this.nativeBenchmark = nativeBenchmark;
        this.javaBenchmark = javaBenchmark;
        difference = Math.abs(this.nativeBenchmark.getElapsedTime()-this.javaBenchmark.getElapsedTime());
        if (this.nativeBenchmark.getElapsedTime() < this.javaBenchmark.getElapsedTime()) {
            faster = Base.NATIVE;
        } else {
            faster = Base.JAVA;
        }
        this.runTimes = runTimes;
        this.name = name;
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
    public void print() {
        String faster = getFaster().toString();
        System.out.println("┌┤" + name);
        System.out.println("│┌┤Java Results");
        System.out.println("│├┤ Total Time: " + Utils.formatTime(javaBenchmark.getElapsedTime()));
        System.out.println("│└┤ Average Time Per Run: " + Utils.formatTime(javaBenchmark.getAvgPerRun()));
        System.out.println("│");
        System.out.println("│┌┤Native Results");
        System.out.println("│├┤ Total Time: " + Utils.formatTime(nativeBenchmark.getElapsedTime()));
        System.out.println("│└┤ Average Time Per Run: " + Utils.formatTime(nativeBenchmark.getAvgPerRun()));
        System.out.println("├┤" + faster + " was faster by " + Utils.formatTime(getDifference()));
        System.out.println("└┤ Runs: " + runTimes);
    }
}

