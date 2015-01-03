package java8ws.benchmark;

import com.carrotsearch.junitbenchmarks.BenchmarkOptions;
import com.carrotsearch.junitbenchmarks.BenchmarkRule;
import java8ws.Workshop;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

@BenchmarkOptions(benchmarkRounds = 10, warmupRounds = 5)
public class Bench06ParallelStream {

    @Rule
    public TestRule benchmarkRun = new BenchmarkRule();

    @Test
    public void benchExercise06a_sequential() {
        Workshop.exercise06a_sequential();
    }

    @Test
    public void benchExercise06b_parallel() {
        Workshop.exercise06b_parallel();
    }
}
