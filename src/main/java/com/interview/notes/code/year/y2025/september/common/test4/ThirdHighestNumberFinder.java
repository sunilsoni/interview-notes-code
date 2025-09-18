package com.interview.notes.code.year.y2025.september.common.test4;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ThirdHighestNumberFinder {

    public static void main(String[] args) {
        // Prepare test cases: each has an input list and the expected third highest
        List<TestCase> testCases = Arrays.asList(
                new TestCase(
                        Arrays.asList(
                                "  ", "2000 ", "apple", " 12", "  200", " 15",
                                "2 8", "-249", " 25", "98", ".22", "-22",
                                "32", "22", "-29", " 21"
                        ),
                        Optional.of(98)                         // third highest is 98
                ),
                new TestCase(
                        Arrays.asList("3", "1", "2"),
                        Optional.of(1)                          // third highest is 1
                ),
                new TestCase(
                        Arrays.asList("5", "5", "5", "5"),
                        Optional.empty()                        // no third distinct value
                ),
                new TestCase(
                        Arrays.asList("10", "9"),
                        Optional.empty()                        // fewer than 3 numbers
                ),
                new TestCase(
                        generateLargeList(1_000_000),           // large data test
                        Optional.of(999_997)                   // third highest in 1..1_000_000
                )
        );

        // Run each test case and print PASS or FAIL
        for (int i = 0; i < testCases.size(); i++) {
            TestCase tc = testCases.get(i);                         // fetch the test case
            Optional<Integer> actual = findThirdHighest(tc.list);   // compute result
            if (actual.equals(tc.expected)) {
                System.out.println("Test case " + (i + 1) + ": PASS");
            } else {
                System.out.println(
                        "Test case " + (i + 1) + ": FAIL" +
                                " (expected " + tc.expected + " but got " + actual + ")"
                );
            }
        }
    }

    /**
     * Finds the third highest integer in a list of strings.
     *
     * @param list input List of raw strings
     * @return Optional containing the third highest, or empty if not found
     */
    public static Optional<Integer> findThirdHighest(List<String> list) {
        return list.stream()
                .map(String::trim)                          // trim whitespace from each string
                .filter(s -> s.matches("-?\\d+"))           // keep only valid integer strings
                .map(Integer::parseInt)                     // convert strings to int values
                .distinct()                                 // remove duplicate integers
                .sorted(Comparator.reverseOrder())          // sort in descending order
                .skip(2)                                    // skip the top two values
                .findFirst();                               // take the third value if present
    }

    /**
     * Generates a large list of stringified integers from "1" up to size.
     *
     * @param size the maximum integer to include
     * @return List of Strings ["1","2",...,"size"]
     */
    public static List<String> generateLargeList(int size) {
        return IntStream.rangeClosed(1, size)            // stream numbers from 1 to size
                .mapToObj(Integer::toString)     // convert each to its String form
                .collect(Collectors.toList());   // gather into a List<String>
    }

    /**
     * Simple holder for a single test case.
     */
    static class TestCase {
        List<String> list;               // the raw input list
        Optional<Integer> expected;      // the expected third highest

        TestCase(List<String> list, Optional<Integer> expected) {
            this.list = list;            // save input
            this.expected = expected;    // save expected result
        }
    }
}