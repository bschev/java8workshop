package java8ws;

import java8ws.utils.Person;
import java8ws.utils.Person.Gender;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * Java 8 Übungen zu Neuerungen im Collection-Framework, Streams, Lambda-Ausdrücken und Methodenreferenzen.
 *
 * @author bschulzeeverding
 */
public class Workshop {

    private Workshop() {
    }

    /**
     * Gebe alle Werte der Liste auf der Konsole aus.
     * <p/>
     * Tip: Es gibt einige neue Methoden an den Interfaces Collection und Iterable.
     */
    public static void exercise01() {
        final List<String> stingList = Arrays.asList("abcd", "efgh", "ijkl");

        // --------------------------------------------------------------------------------
        // Lambda Lösung mit Parameter-Typ und geschweiften Klammern für den Body

        stingList.forEach(
                (String v) -> {
                    System.out.println(v);
                });

        // --------------------------------------------------------------------------------
        // Lambda Lösung kurz

        stingList.forEach(v -> System.out.println(v));

        // --------------------------------------------------------------------------------
        // Methodenreferenz Lösung

        stingList.forEach(System.out::println);
    }

    /**
     * Ersetze alle Werte mit den jeweiligen Werten in Großbuchstaben.
     * <p/>
     * Tip: Es gibt einige neue Methoden am Interface List.
     *
     * @return das Ergebnis
     */
    public static List<String> exercise02() {
        final List<String> stingList = Arrays.asList("abcd", "efgh", "ijkl");

//      stingList.replaceAll(v -> v.toUpperCase()); // Lambda Lösung
        stingList.replaceAll(String::toUpperCase); // Methodenreferenz Lösung

        return stingList;
    }

    /**
     * Entferne alle Werte der Liste die mehr als 5 Zeichen haben.
     * Nutze dafür {@link java.util.Collection#removeIf(java.util.function.Predicate)}.
     *
     * @return das Ergebnis
     */
    public static List<String> exercise03a_removeIf() {
        final List<String> stingList = Arrays.asList("abcd", "efghijkl", "mnopq");

        // --------------------------------------------------------------------------------
        // Da stringList auf einem Array aufbaut kann die Länge der Liste nicht verändert werden,
        // daher müssen erst alle Elemente in eine neue Liste kopiert werden,
        // bevor dann die Elemente mit zu vielen Zeichen gelöscht werden können.
        //
        // stingList.removeIf(...) -> java.lang.UnsupportedOperationException

        final List<String> stingListCopy = new ArrayList<>(stingList);
        stingListCopy.removeIf(v -> v.length() > 5);

        return stingListCopy;
    }

    /**
     * Entferne alle Werte der Liste die mehr als 5 Zeichen haben. Nutze dafür die Stream API.
     *
     * @return das Ergebnis
     */
    public static List<String> exercise03b_stream() {
        final List<String> stingList = Arrays.asList("abcd", "efghijkl", "mnopq");

        return stingList.stream()                         // Stream Source
                .filter(v -> v.length() <= 5)             // Intermediate Operation
                .collect(Collectors.toList());            // Terminal Operation
    }

    /**
     * Sortiere die Liste nach Länge der Strings. Nutze dafür die Stream API.
     * <p/>
     * Tip: Das Interface Comparator wurde um einige Methoden erweitert über die Comparators erzeugt werden können.
     *
     * @return das Ergebnis
     */
    public static List<String> exercise04() {
        final List<String> stingList = Arrays.asList("abcd", "efghijkl", "mnopq");

        return stingList.stream()                                 // Stream Source
                .sorted(Comparator.comparingInt(String::length))  // Intermediate Operation
                .collect(Collectors.toList());                    // Terminal Operation
    }

    /**
     * Erzeuge eine Liste mit 100 String Werten ("value 1", "value 2", "value 3", ...)
     * <p/>
     * Tip: Das JDK wurde für die Verarbeitung der primitiven Typen int, long und double um spezielle Streams erweitert
     * (IntStream, LongStream, DoubleStream).
     *
     * @return das Ergebnis
     */
    public static List<String> exercise05() {

        return IntStream.rangeClosed(1, 100)                    // Stream Source
                .mapToObj(aIntValue -> "value " + aIntValue)    // Intermediate Operation
                .collect(Collectors.toList());                  // Terminal Operation
    }

    /**
     * Ersetze alle Werte mit den jeweiligen Werten in Großbuchstaben per Stream API.
     * Simuliere zusätzlich eine langlaufende Operation.
     * <p/>
     * Tip: {@link #longRunningTask(long, String)} per peek in die Pipeline einbauen.
     *
     * @return das Ergebnis
     */
    public static List<String> exercise06a_sequential() {
        final List<String> stingList = Arrays.asList("abcd", "efghijkl", "mnopq");

        return stingList.stream()                                // Stream Source
                .peek(v -> longRunningTask(500L, v))             // Intermediate Operation, peek führt eine Aktion für jedes Element im Steam aus.
                .map(String::toUpperCase)                        // Intermediate Operation, map transformiert Elemente mithilfe einer Function<T, R>.
                .collect(Collectors.toList());                   // Terminal Operation, hier werden die Daten aus einem Stream in eine Liste übertragen.
    }

    /**
     * Ersetze alle Werte mit den jeweiligen Werten in Großbuchstaben per Stream API.
     * Simuliere zusätzlich eine langlaufende Operation. Nutze dafür diesmal einen parallelen Stream.
     * Auf Multi-Core Rechnern sollte das zu einer Laufzeitverbesserung führen.
     * <p/>
     * Tip: Vergleiche die Laufzeiten von exercise06a_sequential und exercise06b_parallel -> Bench06ParallelStream
     *
     * @return das Ergebnis
     */
    public static List<String> exercise06b_parallel() {
        final List<String> stingList = Arrays.asList("abcd", "efghijkl", "mnopq");

        return stingList.parallelStream()
                .peek(v -> longRunningTask(500L, v))
                .map(String::toUpperCase)
                .collect(Collectors.toList());
    }

    private static final long SUM_UP_LIMIT = 2_000_000_000L;

    /**
     * Bilde die Summe aller Integer von 1 - SUM_UP_LIMIT (1 + 2 + 3 + ... + SUM_UP_LIMIT).
     * Summiere in einer Schleife (herkömmliche JDK 7 Variante).
     *
     * @return das Ergebnis
     */
    public static long exercise07a_sequentialLoop() {
        long sum = 0L;
        for (int i = 1; i <= SUM_UP_LIMIT; i++) {
            sum += (long) i;
        }

        return sum;
    }

    /**
     * Bilde die Summe aller Integer von 1 - SUM_UP_LIMIT (1 + 2 + 3 + ... + SUM_UP_LIMIT).
     * Diesmal nutze parallele Streams.
     * <p/>
     * Tip: Mit der Terminal Operation forEach kann eine Aktion für jedes Elements aus dem Stream ausgeführt werden.
     *
     * @return das Ergebnis
     */
    public static long exercise07b_parallelForEachSum() {
        // --------------------------------------------------------------------------------
        // Naheliegender Lösungsansatz, die Verwendung einer lokalen Variablen.
        // Dies funktioniert nicht da lokale Variablen aus einer Lambda heraus nicht verändert werden dürfen.
        // Ein Grund dafür ist das bei paralleler Stream Verarbeitung Race Conditions auftreten würden.

        /*
        long sum = 0L;
        LongStream.rangeClosed(1, SUM_UP_LIMIT)
        .forEach(i -> sum += i); // -> error
        */

        // --------------------------------------------------------------------------------
        // Umgehung der Race Conditions unter Verwendung von AtomicLong.
        // Dieser Ansatz funktioniert, wirkt sich aber stark auf die Performance aus!

        final AtomicLong sum = new AtomicLong(0L);
        LongStream.rangeClosed(1, SUM_UP_LIMIT)
                .parallel()
                .forEach(sum::addAndGet); // Methodenreferenz Lösung
//                .forEach(i -> sum.addAndGet(i)); // Lambda Lösung

        return sum.get();
    }

    /**
     * Bilde die Summe aller Integer von 1 - SUM_UP_LIMIT (1 + 2 + 3 + ... + SUM_UP_LIMIT).
     * Nutze parallele Streams.
     * <p/>
     * Tip: Summiere die Zahlen nicht sondern nutze die Terminal Methode reduce um das Ergebnis zu berechnen.
     * <p/>
     * 1+2_3+4_5+6_7+8_9+10_11+12_...
     * _3 + 7___11+15___19 + 23___...
     * ___10  +  26________42     ...
     * ...
     *
     * @return das Ergebnis
     */
    public static long exercise07c_parallelReduce() {

        return LongStream.rangeClosed(1L, SUM_UP_LIMIT)
                .parallel()
                .reduce(0, (s1, s2) -> s1 + s2); // Lambda Lösung
//                .reduce(0, Long::sum); // Methodenreferenz Lösung
    }

    /**
     * Bilde die Summe aller Integer von 1 - SUM_UP_LIMIT (1 + 2 + 3 + ... + SUM_UP_LIMIT).
     * Nutze parallele Streams.
     * <p/>
     * Tip: Nutze {@link LongStream#sum()}, diese ist gleichwertig zu der reduce Lösung.
     * Tip: Vergleiche die Laufzeiten von exercise07a - exercise07c -> Bench07Sum.
     * Ändere SUM_UP_LIMIT zu einem kleineren Wert zb. 500_000_000L und lasse den Benchmark nochmals laufen.
     *
     * @return das Ergebnis
     */
    public static long exercise07d_parallelSum() {

        return LongStream.rangeClosed(1L, SUM_UP_LIMIT)
                .parallel()
                .sum();
    }

    /**
     * Erzeuge eine rückwärts alphabetisch sortiere kommaseparierte Liste mit den Namen aller männlichen Personen
     * die mindestens 18 Jahre alt sind.
     *
     * @return das Ergebnis
     */
    public static String exercise08() {
        final List<Person> persons = Arrays.asList(
                new Person("Hans", 65, Gender.MALE), new Person("Lisa", 23, Gender.FEMALE),
                new Person("Marcus", 18, Gender.MALE), new Person("Maria", 48, Gender.FEMALE),
                new Person("Andi", 32, Gender.MALE), new Person("Emil", 5, Gender.MALE));

        return persons.stream()
                .filter(person -> person.getAge() >= 18)
                .filter(person -> person.getGender() == Gender.MALE)
//                .map(person -> person.getName()) // Lambda Lösung
                .map(Person::getName) // Methodenreferenz Lösung
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.joining(","));
    }

    /**
     * Gruppiere die Personen nach Geschlecht und sortiere sie nach Alter.
     *
     * @return das Ergebnis
     */
    public static Map<Gender, List<Person>> exercise09() {
        final List<Person> persons = Arrays.asList(
                new Person("Hans", 65, Gender.MALE), new Person("Lisa", 23, Gender.FEMALE),
                new Person("Marcus", 18, Gender.MALE), new Person("Maria", 48, Gender.FEMALE),
                new Person("Andi", 32, Gender.MALE), new Person("Emil", 5, Gender.MALE));

        return persons.stream()
                .sorted(Comparator.comparingInt(Person::getAge))
                .collect(Collectors.groupingBy(Person::getGender));
    }

    /**
     * Durchsuche die Personen nach dem als Parameter gegebenen Namen, falls es keine Person mit dem Namen gibt gebe
     * die "dummy" Person zurück, falls es mehrere Personen mit dem selben Namen gibt gebe die ältere Person zurück.
     * <p/>
     * Tip: Nutze die neu eingeführte Klasse java.util.Optional.
     *
     * @param personName der Personen Namen nach dem gesucht wird.
     * @return das Ergebnis
     */
    public static Person exercise10(final String personName) {
        final List<Person> persons = Arrays.asList(
                new Person("Hans", 65, Gender.MALE), new Person("Lisa", 23, Gender.FEMALE),
                new Person("Marcus", 18, Gender.MALE), new Person("Maria", 48, Gender.FEMALE),
                new Person("Maria", 53, Gender.MALE), new Person("Emil", 5, Gender.MALE));

        final Person dummyPerson = new Person("Dummy", 1, Gender.MALE);

        final Optional<Person> anyPerson = persons.stream()
                .filter(p -> p.getName().equals(personName))
                .sorted(Comparator.comparingInt(Person::getAge).reversed())
                .findFirst();

        return anyPerson.orElse(dummyPerson);
    }

    /**
     * Simulates a long running task.
     *
     * @param millis number of milliseconds to sleep
     * @param value  some value
     */
    public static void longRunningTask(final long millis, final String value) {
        try {
            System.out.printf("** Task start: %s **%n", value);
            Thread.sleep(millis);
            System.out.printf("** Task end: %s **%n", value);
        } catch (InterruptedException ignored) {
        }
    }

}
