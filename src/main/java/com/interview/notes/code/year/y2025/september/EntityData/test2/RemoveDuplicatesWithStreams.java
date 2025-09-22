package com.interview.notes.code.year.y2025.september.EntityData.test2;

import java.util.*;
import java.util.stream.Collectors;

public class RemoveDuplicatesWithStreams {
    public static void main(String[] args) {
        // Define a list of test cases, each with input and expected output for dedupe + descending sort
        List<TestCase> testCases = Arrays.asList(
                new TestCase(
                        "SampleList",
                        Arrays.asList("banana", "apple", "orange", "apple", "banana"),
                        Arrays.asList("orange", "banana", "apple")  // expected descending order
                ),
                new TestCase(
                        "AllDuplicates",
                        Arrays.asList("x", "x", "x"),
                        List.of("x")
                ),
                new TestCase(
                        "NoDuplicates",
                        Arrays.asList("c", "b", "a"),
                        Arrays.asList("c", "b", "a")  // already in descending order
                )
        );

        // Run each test and print PASS or FAIL
        for (TestCase tc : testCases) {
            List<String> result = removeDuplicatesAndSortDesc(tc.input);
            boolean passed = result.equals(tc.expected);
            System.out.println(tc.name + ": " + (passed ? "PASS" : "FAIL"));
        }

        // Large data test to ensure performance
        int size = 100_000;
        List<String> largeInput = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            String value = "val" + i;
            largeInput.add(value);
            largeInput.add(value);
        }
        List<String> largeResult = removeDuplicatesAndSortDesc(largeInput);
        System.out.println("LargeDataTest: " + (largeResult.size() == size ? "PASS" : "FAIL"));
    }

    /**
     * Removes duplicates and sorts in descending order.
     * Uses Stream API: filter nulls, distinct, reverse sort, then collect.
     */
    private static List<String> removeDuplicatesAndSortDesc(List<String> list) {
        return list.stream()
                .filter(Objects::nonNull)             // drop nulls
                .distinct()                           // keep first of each unique element
                .sorted(Comparator.reverseOrder())    // sort in reverse (descending) order
                .collect(Collectors.toList());       // gather back into a List
    }

    /**
     * Container for test data: name, input list, expected output list
     */
    private static class TestCase {
        String name;
        List<String> input;
        List<String> expected;

        TestCase(String name, List<String> input, List<String> expected) {
            this.name = name;
            this.input = input;
            this.expected = expected;
        }
    }
}