package java8ws;

import static org.junit.Assert.*;

import java8ws.utils.Person;
import java8ws.utils.Person.Gender;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WorkshopTest {

    @Test
    public void testExercise01() {
        Workshop.exercise01();
    }

    @Test
    public void testExercise02() {
        final List<String> result = Workshop.exercise02();
        System.out.println(result);
        assertEquals(Arrays.asList("ABCD", "EFGH", "IJKL"), result);
    }

    @Test
    public void testExercise03a_removeIf() {
        final List<String> result = Workshop.exercise03a_removeIf();
        System.out.println(result);
        assertEquals(Arrays.asList("abcd", "mnopq"), result);
    }

    @Test
    public void testExercise03b_stream() {
        final List<String> result = Workshop.exercise03b_stream();
        System.out.println(result);
        assertEquals(Arrays.asList("abcd", "mnopq"), result);
    }

    @Test
    public void testExercise04() {
        final List<String> result = Workshop.exercise04();
        System.out.println(result);
        assertEquals(Arrays.asList("abcd", "mnopq", "efghijkl"), result);
    }

    @Test
    public void testExercise05() {
        final List<String> result = Workshop.exercise05();
        System.out.println(result);
        assertEquals(100L, (long) result.size());
    }

    @Test
    public void testExercise06a_sequential() {
        final List<String> result = Workshop.exercise06a_sequential();
        System.out.println(result);
        assertEquals(Arrays.asList("ABCD", "EFGHIJKL", "MNOPQ"), result);
    }

    @Test
    public void testExercise06b_parallel() {
        final List<String> result = Workshop.exercise06b_parallel();
        System.out.println(result);
        assertEquals(Arrays.asList("ABCD", "EFGHIJKL", "MNOPQ"), result);
    }

    private static final long SUM_RESULT = 2_000_000_001_000_000_000L;

    @Test
    public void testExercise07a_sequentialLoop() {
        assertEquals(SUM_RESULT, Workshop.exercise07a_sequentialLoop());
    }

    @Test
    public void testExercise07b_parallelForEachSum() {
        assertEquals(SUM_RESULT, Workshop.exercise07b_parallelForEachSum());
    }

    @Test
    public void testExercise07c_parallelReduce() {
        assertEquals(SUM_RESULT, Workshop.exercise07c_parallelReduce());
    }

    @Test
    public void testExercise07d_parallelSum() {
        assertEquals(SUM_RESULT, Workshop.exercise07d_parallelSum());
    }

    @Test
    public void testExercise08() {
        final String result = Workshop.exercise08();
        System.out.println(result);
        assertEquals("Marcus,Hans,Andi", result);
    }

    @Test
    public void testExercise09() {
        final Map<Gender, List<Person>> result = Workshop.exercise09();
        System.out.println(result);
        assertEquals(
                "Lisa,Maria", result.get(Gender.FEMALE).stream().map(Person::getName).collect(Collectors.joining(",")));
        assertEquals(
                "Emil,Marcus,Andi,Hans",
                result.get(Gender.MALE).stream().map(Person::getName).collect(Collectors.joining(",")));
    }

    @Test
    public void testExercise10() {
        assertEquals(53, Workshop.exercise10("Maria").getAge());
        assertEquals("Dummy", Workshop.exercise10("unknown").getName());
    }
}
