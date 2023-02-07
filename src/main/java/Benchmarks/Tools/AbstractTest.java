package Benchmarks.Tools;

import Benchmarks.Tools.Results.TestResults;

public abstract class AbstractTest {
    protected abstract void setup();
    protected abstract void execute();
    public TestResults runTest(int times) {
        setup();
        long startTime = System.nanoTime();
        for (int i = 0; i < times; i++) {
            execute();
        }
        return new TestResults(startTime,System.nanoTime(),times);
    }
}
