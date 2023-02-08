package Benchmarks.Tools;

import Benchmarks.Tools.Results.TestResults;

public class TestRunner {
    public TestRunner() {
    }
    public void runTest(CompTest compTest, int times) {
        compTest.run(times);
        TestResults javaResults = compTest.getJavaResults();
        TestResults nativeResults = compTest.getNativeResults();
        System.out.println("Java Results ============================================================");
        System.out.println("Start Time: " + javaResults.getStartTime());
        System.out.println("End Time: " + javaResults.getEndTime());
        System.out.println("Elapsed Time: " + javaResults.getElapsedTime());
        System.out.println("Run Times: " + javaResults.getRunTimes());
        System.out.println("Average Time Per Run: " + javaResults.getAvgPerRun());
        System.out.println("Native Results ============================================================");
        System.out.println("Start Time: " + nativeResults.getStartTime());
        System.out.println("End Time: " + nativeResults.getEndTime());
        System.out.println("Elapsed Time: " + nativeResults.getElapsedTime());
        System.out.println("Run Times: " + nativeResults.getRunTimes());
        System.out.println("Average Time Per Run: " + nativeResults.getAvgPerRun());
    }
}
