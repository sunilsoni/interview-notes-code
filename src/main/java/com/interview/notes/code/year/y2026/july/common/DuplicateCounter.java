package com.interview.notes.code.year.y2026.july.common;

import java.util.Arrays; // Required for array manipulation and stream creation
import java.util.Map; // Required to use the Map interface for our key-value pairs
import java.util.stream.Collectors; // Required for grouping and collecting stream results

public class DuplicateCounter { // Define the main class that holds our logic

    // Method to calculate frequencies and filter out unique elements
    public static Map<Integer, Long> getCounts(int[] arr) { // Accepts an int array and returns a Map of Number -> Count
        if (arr == null) return Map.of(); // Guard clause: return an empty map immediately if the input is null

        return Arrays.stream(arr) // Convert the primitive int array into an IntStream for processing
                .boxed() // Convert primitive ints to Integer objects so they can be stored in collections
                .collect(Collectors.groupingBy(num -> num, Collectors.counting())) // Group by the number itself and count occurrences
                .entrySet().stream() // Take the resulting map and create a new stream from its key-value entries
                .filter(entry -> entry.getValue() > 1) // Keep only the elements that have a frequency strictly greater than 1
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)); // Repackage the filtered entries back into a new Map
    }

    // Main method to act as our test runner (replacing JUnit)
    public static void main(String[] args) { // Application entry point
        
        // TEST CASE 1: Standard provided input
        int[] standardInput = {2, 3, 2, 3, 2, 4}; // The array provided in the problem statement
        Map<Integer, Long> expected1 = Map.of(2, 3L, 3, 2L); // 2 appears 3 times, 3 appears 2 times
        runTest("Test 1 (Standard)", standardInput, expected1); // Execute the test helper

        // TEST CASE 2: All unique elements (No duplicates should be returned)
        int[] uniqueInput = {1, 2, 3, 4, 5}; // Array where every number appears exactly once
        Map<Integer, Long> expected2 = Map.of(); // Expected output is an empty map
        runTest("Test 2 (All Unique)", uniqueInput, expected2); // Execute the test helper

        // TEST CASE 3: Empty array edge case
        int[] emptyInput = {}; // An array with zero elements
        Map<Integer, Long> expectedEmpty = Map.of(); // Expected output is an empty map
        runTest("Test 3 (Empty Array)", emptyInput, expectedEmpty); // Execute the test helper

        // TEST CASE 4: Large data simulation to ensure no memory/performance timeouts
        int[] largeData = new int[100_000]; // Create an array capable of holding 100,000 integers
        Arrays.fill(largeData, 0, 50_000, 7); // Fill the first 50,000 slots with the number 7
        Arrays.fill(largeData, 50_000, 100_000, 9); // Fill the remaining 50,000 slots with the number 9
        Map<Integer, Long> expectedLarge = Map.of(7, 50_000L, 9, 50_000L); // We expect exactly 50k counts for both 7 and 9
        runTest("Test 4 (Large Data 100k)", largeData, expectedLarge); // Execute the test helper
    }

    // Reusable helper method to evaluate tests and print PASS/FAIL
    private static void runTest(String testName, int[] input, Map<Integer, Long> expected) { // Takes test context to validate
        var actual = getCounts(input); // Call our core method using Java local variable type inference (var)
        boolean isPass = actual.equals(expected); // Compare the map we generated against the map we expect
        System.out.println(testName + " -> " + (isPass ? "PASS" : "FAIL")); // Ternary operator to print PASS if true, FAIL if false
        
        if (!isPass) { // If the test did not pass...
            System.out.println("   Expected: " + expected); // ...print what we wanted for debugging
            System.out.println("   Actual:   " + actual); // ...print what we actually got for debugging
        }
    }
}