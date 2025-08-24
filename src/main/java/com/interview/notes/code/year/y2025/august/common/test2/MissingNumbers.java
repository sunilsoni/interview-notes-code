package com.interview.notes.code.year.y2025.august.common.test2;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MissingNumbers {

    // Main method to find missing numbers using Stream API with List<Integer> input
    public static List<Integer> findMissingNumbers(List<Integer> nums) {
        // Handle null or empty input by returning empty list
        if (nums == null || nums.isEmpty()) {
            return new ArrayList<>();
        }

        // Find maximum number in the list using stream max operation
        int maxNum = nums.stream()
                .mapToInt(Integer::intValue)
                .max()
                .orElse(0);

        // Convert input list to Set for O(1) lookup efficiency
        Set<Integer> numSet = new HashSet<>(nums);

        // Generate stream from 0 to maxNum and filter missing numbers
        return IntStream.rangeClosed(0, maxNum)
                .boxed()
                .filter(num -> !numSet.contains(num))
                .collect(Collectors.toList());
    }

    // Main method for testing the solution
    public static void main(String[] args) {
        // Test Case 1: Basic case with one missing number
        testCase(
                Arrays.asList(0, 2),
                Arrays.asList(1),
                "Test 1: Basic case"
        );

        // Test Case 2: No missing numbers
        testCase(
                Arrays.asList(2, 1, 0),
                Arrays.asList(),
                "Test 2: No missing numbers"
        );

        // Test Case 3: Multiple missing numbers with duplicates
        testCase(
                Arrays.asList(5, 5, 2),
                Arrays.asList(0, 1, 3, 4),
                "Test 3: Multiple missing with duplicates"
        );

        // Test Case 4: Empty list
        testCase(
                new ArrayList<>(),
                Arrays.asList(),
                "Test 4: Empty list"
        );

        // Test Case 5: Large numbers
        testCase(
                Arrays.asList(100, 99, 98),
                IntStream.range(0, 98)
                        .boxed()
                        .collect(Collectors.toList()),
                "Test 5: Large numbers"
        );

        // Test Case 6: Single element
        testCase(
                Arrays.asList(1),
                Arrays.asList(0),
                "Test 6: Single element"
        );

        // Test Case 7: Null input
        testCase(
                null,
                Arrays.asList(),
                "Test 7: Null input"
        );
    }

    // Helper method to run test cases and print results
    private static void testCase(List<Integer> input, List<Integer> expected, String testName) {
        // Get result from findMissingNumbers method
        List<Integer> result = findMissingNumbers(input);

        // Compare result with expected output
        boolean passed = result.equals(expected);

        // Print test results
        System.out.println(testName + ": " + (passed ? "PASSED" : "FAILED"));

        // If test failed, print expected and actual results
        if (!passed) {
            System.out.println("Input: " + input);
            System.out.println("Expected: " + expected);
            System.out.println("Got: " + result);
            System.out.println();
        }
    }
}
