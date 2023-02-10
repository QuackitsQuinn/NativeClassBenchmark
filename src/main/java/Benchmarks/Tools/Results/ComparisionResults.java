package Benchmarks.Tools.Results;

import Benchmarks.Tools.Base;

public class ComparisionResults {
    private final TestResults nativeTest,javaTest;
    private final Base faster;
    private final long difference;

    /**
     * Results of a native/java comparison test
     * @param nativeTest the native based test
     * @param javaTest the java based test
     */
    public ComparisionResults(TestResults nativeTest,TestResults javaTest) {
        this.nativeTest = nativeTest;
        this.javaTest = javaTest;
        difference = Math.abs(this.nativeTest.getElapsedTime()-this.javaTest.getElapsedTime());
        if (this.nativeTest.getElapsedTime() < this.javaTest.getElapsedTime()) {
            faster = Base.NATIVE;
        } else {
            faster = Base.JAVA;
        }
        }

    /**
     * Get the results of the native test
     * @return the results of the native test
     */
    public TestResults getNativeTest() {
        return nativeTest;
    }

    /**
     * Get the results of the java test
     * @return the results of the java test
     */
    public TestResults getJavaTest() {
        return javaTest;
    }

    /**
     * Get the base that was faster
     * @return the base that was faster
     */
    public Base getFaster() {
        return faster;
    }

    /**
     * Get the difference in time between the two tests. Always positive.
     * @return the difference in time between the two tests
     */
    public long getDifference() {
        return difference;
    }
}

