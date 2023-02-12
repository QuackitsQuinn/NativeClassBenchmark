import Benchmarks.Tools.BenchmarkRunner;
import Benchmarks.Tools.Results.BenchmarkResults;
import Benchmarks.Tools.Results.ComparisonResults;
import org.junit.jupiter.api.Test;

public class Tests {
    @Test
    public void testPrintBenchmarkResults() {
        BenchmarkResults benchmarkResults1 = new BenchmarkResults(System.nanoTime(), System.nanoTime()+100, 1);
        BenchmarkResults benchmarkResults2 = new BenchmarkResults(System.nanoTime(), System.nanoTime()+150, 1);
        new ComparisonResults(benchmarkResults1, benchmarkResults2, "test", 0).print();
    }
}
