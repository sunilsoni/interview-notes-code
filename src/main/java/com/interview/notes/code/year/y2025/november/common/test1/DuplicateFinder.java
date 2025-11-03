package com.interview.notes.code.year.y2025.november.common.test1;

import java.util.*;
import java.util.stream.Collectors;

public class DuplicateFinder {

    // Method to find duplicates in a list
    public static List<Integer> findDuplicates(List<Integer> input) {
        // Group elements by value and count occurrences
        return input.stream()
            .collect(Collectors.groupingBy(
                num -> num, // key: the number itself
                Collectors.counting() // value: how many times it appears
            ))
            .entrySet().stream() // convert map to stream of entries
            .filter(entry -> entry.getValue() > 1) // keep only duplicates
            .map(Map.Entry::getKey) // extract the duplicate number
            .collect(Collectors.toList()); // collect into a list
    }

    // Main method to run test cases
    public static void main(String[] args) {
        // Define test cases as Map of input to expected output
        Map<List<Integer>, List<Integer>> testCases = new LinkedHashMap<>();

        // Test Case 1: Basic duplicates
        testCases.put(Arrays.asList(1, 2, 3, 2, 4, 5, 1), Arrays.asList(1, 2));

        // Test Case 2: No duplicates
        testCases.put(Arrays.asList(10, 20, 30), List.of());

        // Test Case 3: All duplicates
        testCases.put(Arrays.asList(5, 5, 5, 5), List.of(5));

        // Test Case 4: Large input
        List<Integer> largeInput = new ArrayList<>();
        List<Integer> expectedLargeOutput = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            largeInput.add(i % 1000); // repeat numbers 0–999
        }
        for (int i = 0; i < 1000; i++) {
            expectedLargeOutput.add(i); // all numbers are duplicated
        }
        testCases.put(largeInput, expectedLargeOutput);

        // Run each test case
        int testCaseNumber = 1;
        for (Map.Entry<List<Integer>, List<Integer>> entry : testCases.entrySet()) {
            List<Integer> input = entry.getKey(); // Input list
            List<Integer> expected = entry.getValue(); // Expected output

            long startTime = System.nanoTime(); // Start timer
            List<Integer> actual = findDuplicates(input); // Run method
            long endTime = System.nanoTime(); // End timer

            // Sort both lists for comparison (order doesn't matter)
            Collections.sort(actual);
            Collections.sort(expected);

            boolean passed = actual.equals(expected); // Compare results

            // Print result
            System.out.println("Test Case " + testCaseNumber + ": " + (passed ? "PASS" : "FAIL"));
            System.out.println("Execution Time: " + (endTime - startTime) / 1_000_000 + " ms");
            testCaseNumber++;
        }
    }
}
