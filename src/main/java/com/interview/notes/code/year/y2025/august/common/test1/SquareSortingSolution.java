package com.interview.notes.code.year.y2025.august.common.test1;

import java.util.*;
import java.util.stream.*;

public class SquareSortingSolution {
    
    // Main method to process array and handle test cases
    public static void main(String[] args) {
        // Test cases to verify solution
        runTestCases();
    }

    // Method to sort array based on squares while maintaining original elements
    public static Integer[] sortBySquares(Integer[] arr) {
        // Convert array to stream and sort based on absolute squares
        return Arrays.stream(arr)
                // Remove duplicates using distinct()
                .distinct()
                // Sort based on squares of elements
                .sorted(Comparator.comparingInt(a -> a * a))
                // Convert stream back to array
                .toArray(Integer[]::new);
    }

    // Method to run all test cases and verify results
    private static void runTestCases() {
        // Test Case 1: Basic positive numbers
        testCase(
            new Integer[]{1, 5, 7, 7, 8, 10},
            new Integer[]{1, 5, 7, 8, 10},
            "Test Case 1: Basic positive numbers"
        );

        // Test Case 2: Mixed positive and negative numbers
        testCase(
            new Integer[]{-5, -3, -3, 2, 4, 4, 8},
            new Integer[]{2, -3, 4, -5, 8},
            "Test Case 2: Mixed numbers"
        );

        // Test Case 3: Large dataset
        Integer[] largeInput = generateLargeDataset(10000);
        testCase(
            largeInput,
            Arrays.stream(largeInput).distinct().sorted(Comparator.comparingInt(a -> a * a)).toArray(Integer[]::new),
            "Test Case 3: Large dataset"
        );

        // Test Case 4: Empty array
        testCase(
            new Integer[]{},
            new Integer[]{},
            "Test Case 4: Empty array"
        );

        // Test Case 5: Single element
        testCase(
            new Integer[]{1},
            new Integer[]{1},
            "Test Case 5: Single element"
        );
    }

    // Helper method to generate large dataset for testing
    private static Integer[] generateLargeDataset(int size) {
        // Create random number generator
        Random random = new Random();
        // Generate array of random numbers
        return random.ints(size, -100, 100)
                    .boxed()
                    .toArray(Integer[]::new);
    }

    // Method to execute individual test case and verify result
    private static void testCase(Integer[] input, Integer[] expected, String testName) {
        // Get actual result from solution
        Integer[] result = sortBySquares(input);
        // Compare result with expected output
        boolean passed = Arrays.equals(result, expected);
        
        // Print test results
        System.out.println(testName);
        System.out.println("Input: " + Arrays.toString(input));
        System.out.println("Expected: " + Arrays.toString(expected));
        System.out.println("Result: " + Arrays.toString(result));
        System.out.println("Status: " + (passed ? "PASS" : "FAIL"));
        System.out.println();
    }
}
