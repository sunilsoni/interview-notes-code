package com.interview.notes.code.year.y2025.october.common.test9;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    // Main method to run test cases and demonstrate solution
    public static void main(String[] args) {
        // Test cases to verify different scenarios
        runTestCase("5; 0,1,2,3,2", 2);  // Basic case with duplicate in middle
        runTestCase("3; 0,1,0", 0);      // Duplicate at ends
        runTestCase("4; 1,2,1,3", 1);    // Duplicate spread apart
        runTestCase("10; 0,1,2,3,4,5,6,7,7,8", 7);  // Larger dataset

        // Edge case tests
        runTestCase("2; 0,0", 0);        // Minimum size array
        runTestCase("15; 0,1,2,3,4,5,6,7,8,9,10,11,12,12,13", 12);  // Large array
    }

    // Method to find duplicate number using Stream API
    private static int findDuplicate(int[] numbers) {
        // Use streams to find the first number that appears twice
        return Arrays.stream(numbers)
                // Box to Integer for grouping
                .boxed()
                // Group numbers by their value and count occurrences
                .collect(Collectors.groupingBy(
                        i -> i,
                        Collectors.counting()
                ))
                // Convert to entry set for filtering
                .entrySet()
                .stream()
                // Find entry where count is 2 (duplicate)
                .filter(entry -> entry.getValue() == 2)
                // Get the key (duplicate number)
                .map(Map.Entry::getKey)
                // Get first (and only) result
                .findFirst()
                // Convert to primitive int
                .orElse(-1);
    }

    // Helper method to run and validate test cases
    private static void runTestCase(String input, int expectedOutput) {
        // Parse input string into array
        String[] parts = input.split("; ");
        int[] numbers = Arrays.stream(parts[1].split(","))
                .mapToInt(Integer::parseInt)
                .toArray();

        // Find duplicate using our method
        int result = findDuplicate(numbers);

        // Validate result
        boolean passed = result == expectedOutput;

        // Print test results
        System.out.println("Test Case: " + input);
        System.out.println("Expected: " + expectedOutput);
        System.out.println("Got: " + result);
        System.out.println("Status: " + (passed ? "PASS" : "FAIL"));
        System.out.println("--------------------");
    }
}
