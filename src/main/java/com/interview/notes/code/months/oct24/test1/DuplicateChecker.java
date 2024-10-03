package com.interview.notes.code.months.oct24.test1;

import java.util.*;
import java.util.stream.Collectors;

public class DuplicateChecker {

    // Method to check for duplicates using Java 8 Streams
    public static boolean hasDuplicates(int[] inputArray) {
        // Convert the array to a stream, collect elements into a Set, and check if the size changes
        return Arrays.stream(inputArray)
                     .boxed() // Convert int primitives to Integer objects
                     .collect(Collectors.toSet()) // Collect into a Set
                     .size() < inputArray.length; // If Set size is smaller, there were duplicates
    }

    // Main method to run the test cases
    public static void main(String[] args) {
        // Test cases
        int[][] testCases = {
            {},                      // Empty array
            {1},                     // Single element
            {1, 2, 3, 4, 5},         // No duplicates
            {1, 2, 3, 4, 5, 1},      // Duplicate 1
            {10, 20, 30, 40, 50},    // No duplicates
            {100, 100, 100, 100},    // All duplicates
            {Integer.MAX_VALUE, Integer.MIN_VALUE, 0, Integer.MAX_VALUE},  // Duplicate Integer.MAX_VALUE
        };

        boolean[] expectedResults = {
            false,  // Empty array
            false,  // Single element
            false,  // No duplicates
            true,   // Duplicate 1
            false,  // No duplicates
            true,   // All duplicates
            true    // Duplicate Integer.MAX_VALUE
        };

        // Run each test case and check if it passes
        for (int i = 0; i < testCases.length; i++) {
            boolean result = hasDuplicates(testCases[i]);
            System.out.println("Test Case " + (i + 1) + ": " + (result == expectedResults[i] ? "PASS" : "FAIL"));
        }
    }
}
