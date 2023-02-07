package Benchmarks.Tools.Results;

/**
 * Small class for random statistical data from benchmarks such as time per command run and elapsed time.
 */
public class TestResults {
    private final long startTime,endTime,elapsedTime;
    private final int runTimes;
    private final double avgPerRun;

    public TestResults(long startTime, long endTime, int runTimes) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.runTimes = runTimes;
        this.elapsedTime = endTime-startTime;
        this.avgPerRun = (double) elapsedTime / (double) runTimes;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public long getElapsedTime() {
        return elapsedTime;
    }

    public int getRunTimes() {
        return runTimes;
    }

    public double getAvgPerRun() {
        return avgPerRun;
    }
}
