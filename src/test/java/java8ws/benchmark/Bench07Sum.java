package java8ws.benchmark;

import com.carrotsearch.junitbenchmarks.BenchmarkOptions;
import com.carrotsearch.junitbenchmarks.BenchmarkRule;
import java8ws.Workshop;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

@BenchmarkOptions(benchmarkRounds = 10, warmupRounds = 5)
public class Bench07Sum {

    @Rule
    public TestRule benchmarkRun = new BenchmarkRule();

    @Test
    public void benchExercise07a_sequentialLoop() {
        Workshop.exercise07a_sequentialLoop();
    }

    @Test
    public void benchExercise07b_parallelForEachSum() {
        Workshop.exercise07b_parallelForEachSum();
    }

    @Test
    public void benchExercise07c_parallelReduce() {
        Workshop.exercise07c_parallelReduce();
    }

    @Test
    public void benchExercise07d_parallelSum() {
        Workshop.exercise07d_parallelSum();
    }
}
