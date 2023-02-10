package Benchmarks.Tools;

import Benchmarks.Tools.Results.ComparisionResults;
import Benchmarks.Tools.Results.TestResults;
/**
 * Class to compare two tests
 */
public class CompTest {
    private final AbstractTest javaBase,nativeBase;
    private TestResults javaResults,nativeResults;
    public CompTest(AbstractTest java, AbstractTest Native) {
        javaBase = java;
        nativeBase = Native;
    }

    /**
     * Runs the tests in parallel with threading and returns the results
     * @param times the amount of times to run the benchmark
     * @return the results of the Benchmark
     */
    public ComparisionResults run(int times) {

        Thread javaThread = new Thread(() -> javaResults = javaBase.runTest(times));
        Thread nativeThread = new Thread(() -> nativeResults = nativeBase.runTest(times));
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
    public TestResults getJavaResults() {
        return javaResults;
    }

    /**
     * Gets the results of the native benchmark
     * @return the results of the benchmark
     */
    public TestResults getNativeResults() {
        return nativeResults;
    }
}
