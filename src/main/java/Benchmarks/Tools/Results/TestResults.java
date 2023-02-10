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

    /**
     * Get the start time of the test
     * @return start time in nanoseconds
     */
    public long getStartTime() {
        return startTime;
    }

    /**
     * Get the end time of the test
     * @return end time in nanoseconds
     */

    public long getEndTime() {
        return endTime;
    }

    /**
     * Get the elapsed time of the test in nanoseconds
     * @return elapsed time in nanoseconds
     */
    public long getElapsedTime() {
        return elapsedTime;
    }

    /**
     * Get the amount of times the test was run
     * @return amount of times the test was run
     */
    public int getRunTimes() {
        return runTimes;
    }

    /**
     * Get the average time per run in nanoseconds
     * @return average time per run in nanoseconds
     */
    public double getAvgPerRun() {
        return avgPerRun;
    }
}
