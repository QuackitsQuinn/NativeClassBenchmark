package Benchmarks.Tools.Results;

public class ComparisionResults {
    public enum Bases {
        NATIVE,
        JAVA
    }
    private final TestResults nativeTest,javaTest;
    private final Bases faster;
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
            faster = Bases.NATIVE;
        } else {
            faster = Bases.JAVA;
        }
        }

    public TestResults getNativeTest() {
        return nativeTest;
    }

    public TestResults getJavaTest() {
        return javaTest;
    }

    public Bases getFaster() {
        return faster;
    }

    public long getDifference() {
        return difference;
    }
}

