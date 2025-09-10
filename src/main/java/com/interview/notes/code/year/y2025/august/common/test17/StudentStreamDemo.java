package com.interview.notes.code.year.y2025.august.common.test17;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Demo class showing Java 8 Stream solution:
 * 1) sort by name (case-insensitive, nulls last)
 * 2) remove age < 10
 * 3) group by age
 * 4) collect to Map<age, List<Student>>
 * Also includes main() tests (no JUnit) with PASS/FAIL output, including large data.
 */
public class StudentStreamDemo {

    /**
     * Core pipeline:
     * - Sort by name (case-insensitive, nulls-last to avoid NPEs and keep nulls at end)
     * - Filter out age < 10
     * - Group by age into a TreeMap so keys (ages) are sorted ascending
     * - Within each age group, insertion order follows the encounter order
     * (because we sorted upstream by name, each group's list ends up name-ordered)
     */
    public static Map<Integer, List<Student>> processStudents(List<Student> students) {
        // Define a null-safe, case-insensitive comparator for names
        Comparator<String> nameOrder = Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER); // keeps nulls last

        // Build Comparator<Student> using the null-safe string comparator
        Comparator<Student> byName = Comparator.comparing(Student::name, nameOrder); // compare by name safely

        // Run the stream pipeline
        return students.stream()                                                               // start stream over the list
                .sorted(byName)                                                                // 1) sort by name (case-insensitive, nulls last)
                .filter(s -> s.age() >= 10)                                                 // 2) remove age < 10
                .collect(Collectors.groupingBy(                                                // 3) group into map
                        Student::age,                                                       // group key: age
                        TreeMap::new,                                                          // use TreeMap so ages (keys) stay sorted
                        Collectors.toList()                                                    // group value: List<Student> preserving encounter order
                ));
    }

    /**
     * Check that every student's age in the map is >= 10.
     * Returns true if the condition holds for all.
     */
    private static boolean allAgesAtLeastTen(Map<Integer, List<Student>> grouped) {
        // stream over entries, ensure keys are >= 10 and all contained students match the key
        return grouped.entrySet().stream()                                      // iterate over age -> list
                .allMatch(e -> e.getKey() >= 10 &&                              // key (age) must be >= 10
                        e.getValue().stream().allMatch(s -> s.age() == e.getKey())); // each student age matches the bucket
    }

    // -----------------------
    // Validation helpers
    // -----------------------

    /**
     * Check that within each age group, students are in name order per our comparator.
     * Since we sorted upstream, the list per group should already respect that.
     */
    private static boolean eachGroupIsSortedByName(Map<Integer, List<Student>> grouped) {
        Comparator<String> nameOrder = Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER); // same comparator as pipeline
        return grouped.values().stream()                    // for each list<student> in the map
                .allMatch(StudentStreamDemo::isSortedByName); // use a helper that checks the list
    }

    /**
     * Utility to check a list is sorted by name per comparator.
     * Returns true if non-decreasing order holds for the entire list.
     */
    private static boolean isSortedByName(List<Student> list) {
        Comparator<String> nameOrder = Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER); // nulls last, case-insensitive
        for (int i = 1; i < list.size(); i++) {                                              // iterate adjacent pairs
            String prev = list.get(i - 1).name();                                        // previous name
            String curr = list.get(i).name();                                            // current name
            if (nameOrder.compare(prev, curr) > 0) {                                        // if out of order, fail
                return false;                                                               // not sorted
            }
        }
        return true;                                                                        // all pairs OK
    }

    /**
     * Ensure the grouped map exactly reflects the filtered set.
     * - No student with age < 10
     * - All students appear in the bucket for their age
     * This check reconstructs the list and cross-compares sizes.
     */
    private static boolean groupedMatchesFilter(List<Student> original, Map<Integer, List<Student>> grouped) {
        // Recreate filtered list as done in pipeline (only the filter step is needed for this check)
        List<Student> filtered = original.stream()
                .filter(s -> s.age() >= 10)
                .collect(Collectors.toList());

        // Flatten grouped map back to a list and compare sizes
        long groupedCount = grouped.values().stream().mapToLong(List::size).sum(); // total students in all buckets
        if (groupedCount != filtered.size()) return false;                         // mismatch indicates a problem

        // Additionally, confirm every filtered student is present in its age bucket
        // (Here we do a simple containment check by identity; for production, consider equals/hashCode.)
        for (Student s : filtered) {
            List<Student> bucket = grouped.get(s.age()); // find the list for the student's age
            if (bucket == null || !bucket.contains(s)) {    // must exist and contain that exact student
                return false;                               // otherwise fail
            }
        }
        return true;                                        // all good
    }

    /**
     * Make a small, hand-crafted dataset that includes:
     * - names with different cases
     * - null name
     * - ages below and above 10
     */
    private static List<Student> sampleStudents() {
        List<Student> list = new ArrayList<>();                          // mutable list for easy appends
        list.add(new Student(101, "alice", 12));                         // valid, lower-case
        list.add(new Student(102, "Bob", 9));                            // age < 10, should be removed
        list.add(new Student(103, "bob", 14));                           // valid, same letters different case
        list.add(new Student(104, null, 16));                            // null name, should sort last
        list.add(new Student(105, "Charlie", 10));                       // boundary: age == 10 is kept
        list.add(new Student(106, "ALAN", 12));                          // valid, different case to test case-insensitive
        list.add(new Student(107, "dave", 8));                           // age < 10, should be removed
        list.add(new Student(108, "Eve", 12));                           // another age 12
        return list;                                                     // return the sample
    }

    // -----------------------
    // Test data generators
    // -----------------------

    /**
     * Generate a large dataset (N students) with:
     * - rollNo: sequential
     * - age: random 5..25
     * - name: random alphabetic string length 5..10
     * This helps performance testing and PASS/FAIL checks at scale.
     */
    private static List<Student> generateLarge(int n) {
        ThreadLocalRandom rnd = ThreadLocalRandom.current();             // fast thread-local RNG
        return IntStream.range(0, n)                                     // 0..n-1
                .mapToObj(i -> new Student(                              // create a new Student for each index
                        i,                                               // rollNo equals index
                        randomName(rnd.nextInt(5, 11)),                  // name: random letters length 5..10
                        rnd.nextInt(5, 26)                               // age: 5..25 (some <10 to exercise filter)
                ))
                .collect(Collectors.toList());                           // collect to list
    }

    /**
     * Helper to build a random lowercase alphabetic name of given length.
     */
    private static String randomName(int len) {
        ThreadLocalRandom rnd = ThreadLocalRandom.current();             // RNG
        StringBuilder sb = new StringBuilder(len);                       // efficient string builder
        for (int i = 0; i < len; i++) {                                  // generate len characters
            char c = (char) ('a' + rnd.nextInt(26));                     // random 'a'..'z'
            sb.append(c);                                                // append to builder
        }
        return sb.toString();                                            // return the final string
    }

    /**
     * Print a single PASS/FAIL line with a descriptive label.
     */
    private static void assertPrint(String label, boolean condition) {
        System.out.println((condition ? "PASS: " : "FAIL: ") + label);   // clear, single-line status
    }

    // -----------------------
    // PASS/FAIL test runner
    // -----------------------

    /**
     * Main method to run all tests (no JUnit): small cases + large data.
     */
    public static void main(String[] args) {
        // ---------- Small, Hand-Crafted Test ----------
        List<Student> sample = sampleStudents();                         // build sample data
        Map<Integer, List<Student>> groupedSample = processStudents(sample); // run the pipeline

        // Validate: all ages >= 10
        assertPrint("Sample: all ages >= 10", allAgesAtLeastTen(groupedSample));

        // Validate: each group's list sorted by name (case-insensitive, nulls last)
        assertPrint("Sample: each group sorted by name", eachGroupIsSortedByName(groupedSample));

        // Validate: grouped content matches filter(s -> age >= 10)
        assertPrint("Sample: grouped matches filter", groupedMatchesFilter(sample, groupedSample));

        // Optional: show the result map briefly (keep output small)
        System.out.println("Sample grouped result: " + groupedSample);

        // ---------- Large Data Test (~100,000) ----------
        int N = 100_000;                                                 // target size as requested
        List<Student> big = generateLarge(N);                             // build large input

        long t0 = System.nanoTime();                                      // start timer
        Map<Integer, List<Student>> groupedBig = processStudents(big);    // run pipeline at scale
        long t1 = System.nanoTime();                                      // end timer

        // Validate on large data
        assertPrint("Large: all ages >= 10", allAgesAtLeastTen(groupedBig));
        assertPrint("Large: each group sorted by name", eachGroupIsSortedByName(groupedBig));
        assertPrint("Large: grouped matches filter", groupedMatchesFilter(big, groupedBig));

        // Print timing so you can gauge performance locally
        double ms = (t1 - t0) / 1_000_000.0;                              // convert ns to ms
        System.out.println("Large data processed in ~" + ms + " ms for " + N + " students.");

        // Small sanity: show number of distinct ages and total kept
        long kept = groupedBig.values().stream().mapToLong(List::size).sum(); // total kept after filter
        System.out.println("Large data summary => ages: " + groupedBig.size() + ", kept: " + kept);
    }

    /**
     * Simple Student POJO for Java 8 (no record).
     *
     * @param rollNo Unique identifier for the student
     * @param name   Student name (can be null; we handle nulls-last in sorting)
     * @param age    Student age
     */
        record Student(int rollNo, String name, int age) {
        // Constructor sets all fields; immutability helps reasoning in streams
        // Assign roll number
        // Assign name
        // Assign age

        // toString for readable console output during debugging and sample verification
            @Override
            public String toString() {
                return "Student{rollNo=" + rollNo + ", name='" + name + "', age=" + age + "}";
            }
        }
}