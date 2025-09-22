package com.interview.notes.code.year.y2025.september.EntityData.test1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class RemoveDuplicatesWithStreams {
    public static void main(String[] args) {
        // Define a list of test cases, each with input and expected output for dedupe + sort
        List<TestCase> testCases = Arrays.asList(
                new TestCase(
                        "SampleList",
                        Arrays.asList("banana", "apple", "orange", "apple", "banana"),
                        Arrays.asList("apple", "banana", "orange")
                ),
                new TestCase(
                        "AllDuplicates",
                        Arrays.asList("x", "x", "x"),
                        List.of("x")
                ),
                new TestCase(
                        "NoDuplicates",
                        Arrays.asList("c", "b", "a"),
                        Arrays.asList("a", "b", "c")
                )
        );

        // Run each test and print PASS or FAIL
        for (TestCase tc : testCases) {
            List<String> result = removeDuplicatesAndSort(tc.input);
            boolean passed = result.equals(tc.expected);
            System.out.println(tc.name + ": " + (passed ? "PASS" : "FAIL"));
        }

        // Large data test
        int size = 100_000;
        List<String> largeInput = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            String value = "val" + i;
            largeInput.add(value);
            largeInput.add(value);
        }
        List<String> largeResult = removeDuplicatesAndSort(largeInput);
        System.out.println("LargeDataTest: " + (largeResult.size() == size ? "PASS" : "FAIL"));
    }

    /**
     * Removes duplicates and sorts in ascending order.
     * Uses Stream API: filter nulls, distinct, sort, then collect.
     */
    private static List<String> removeDuplicatesAndSort(List<String> list) {
        return list.stream()
                .filter(Objects::nonNull)       // drop nulls
                .distinct()                     // keep first of each
                .sorted()                       // sort in natural (ascending) order
                .collect(Collectors.toList()); // gather into List
    }

    // Container for test data: name, input list, expected output
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