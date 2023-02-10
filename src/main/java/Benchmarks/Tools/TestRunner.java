package Benchmarks.Tools;

import Benchmarks.Tools.Results.TestResults;

public class TestRunner {
    public TestRunner() {
    }
    public void runTest(CompTest compTest, int times) {
        compTest.run(times);
        TestResults javaResults = compTest.getJavaResults();
        TestResults nativeResults = compTest.getNativeResults();
    }
}
