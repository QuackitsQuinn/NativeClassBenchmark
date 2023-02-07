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
        javaResults = javaBase.runTest(times);
        nativeResults = nativeBase.runTest(times);
        return new ComparisionResults(nativeResults,javaResults);
    }

    public TestResults getJavaResults() {
        return javaResults;
    }

    public TestResults getNativeResults() {
        return nativeResults;
    }
}
