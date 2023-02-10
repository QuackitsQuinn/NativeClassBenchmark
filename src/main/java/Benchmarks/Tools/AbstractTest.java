package Benchmarks.Tools;

import Benchmarks.Tools.Results.TestResults;

/**
 * This is the class that needs to be extended to create a test.
 */
// TODO: Refactor uses of Test to Benchmark
public abstract class AbstractTest {
    /**
     * This method is called before the test is run.
     * It is used to set up the test. This method is called once.
     */
    protected abstract void setup();
    /**
     * This method is called every time the test is run.
     * It is used to execute the test. This method is called each time the test is run.
     */
    protected abstract void execute();

    /**
     * This method is used to run the test.
     * @param times The amount of times the test is run.
     * @return The results of the test.
     */
    public TestResults runTest(int times) {
        setup();
        long startTime = System.nanoTime();
        for (int i = 0; i < times; i++) {
            execute();
        }
        return new TestResults(startTime,System.nanoTime(),times);
    }
}
