package Benchmarks.Tools;

import Benchmarks.Tools.Results.BenchmarkResults;

/**
 * This is the class that needs to be extended to create a benchmark.
 */
public abstract class AbstractBenchmark {
    private String testName;
    /**
     * This method is called before the benchmark is run.
     * It is used to set up the benchmark. This method is called once.
     */
    protected abstract void setup();
    /**
     * This method is called every time the benchmark is run.
     * It is used to execute the benchmark. This method is called each time the benchmark is run.
     */
    protected abstract void execute();

    /**
     * This method is used to run the benchmark.
     * @param times The amount of times the benchmark is run.
     * @return The results of the benchmark.
     */
    public BenchmarkResults runBenchmark(int times) {
        setup();
        if (testName == null) {
            throw new IllegalStateException("Test name not set");
        }
        long startTime = System.nanoTime();
        for (int i = 0; i < times; i++) {
            execute();
        }
        return new BenchmarkResults(startTime,System.nanoTime(),times);
    }
    /**
     * Set the name of the benchmark. Required for the benchmark to run.
     * @param testName The name of the benchmark.
     */
    protected void setTestName(String testName) {
        this.testName = testName;
    }
    /**
     * Get the name of the benchmark.
     * @return The name of the benchmark.
     */
    public String getTestName() {
        return testName;
    }
}
