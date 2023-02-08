package Benchmarks.Tools;

import Benchmarks.Tools.Results.ComparisionResults;
import Benchmarks.Tools.Results.TestResults;

public class CompTest {
    private final AbstractTest javaBase,nativeBase;
    private TestResults javaResults,nativeResults;
    public CompTest(AbstractTest java, AbstractTest Native) {
        javaBase = java;
        nativeBase = Native;
    }
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

    public TestResults getJavaResults() {
        return javaResults;
    }

    public TestResults getNativeResults() {
        return nativeResults;
    }
}
