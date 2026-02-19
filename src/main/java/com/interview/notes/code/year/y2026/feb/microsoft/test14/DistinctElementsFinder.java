package com.interview.notes.code.year.y2026.feb.microsoft.test14;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class DistinctElementsFinder {

    // Method to find distinct elements using Java Stream API
    public static List<Integer> findDistinct(List<Integer> numbers) {
        // Return an empty list immediately if the input list is null to avoid NullPointerException
        if (numbers == null) return Collections.emptyList();

        // Convert the List<Integer> into a Stream to enable functional-style operations
        return numbers.stream()
                // Use the distinct() intermediate operation to filter out duplicate elements
                .distinct()
                // Collect the remaining distinct elements back into an unmodifiable List (Java 16+)
                .toList();
    }

    // Main method to act as our simple testing framework
    public static void main(String[] args) {
        // Test Case 1: Standard list with duplicates
        List<Integer> list1 = List.of(1, 2, 2, 3, 4, 4, 5);
        List<Integer> expected1 = List.of(1, 2, 3, 4, 5);
        // runTest("Test Case 1 (Standard)", list1, expected1);

        // Test Case 2: List with all duplicates
        List<Integer> list2 = List.of(7, 7, 7, 7);
        List<Integer> expected2 = List.of(7);
        //runTest("Test Case 2 (All Duplicates)", list2, expected2);

        // Test Case 3: Empty list
        List<Integer> list3 = List.of();
        List<Integer> expected3 = List.of();
        // runTest("Test Case 3 (Empty List)", list3, expected3);

        // Test Case 4: Null input handling
        // runTest("Test Case 4 (Null Input)", null, List.of());

        // Test Case 5: Large data input (100,000 elements, mostly duplicates)
        // Generate a large list of numbers from 0 to 9 repeated many times
        List<Integer> largeList = IntStream.range(0, 100000).map(i -> i % 10).boxed().toList();
        // The expected result should only be the numbers 0 through 9
        List<Integer> expectedLarge = List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        //  runTest("Test Case 5 (Large Data)", largeList, expectedLarge);
    }

    // Helper method to execute a test, compare results, and print PASS/FAIL
    //private static void runTest(String testName, List<Integer> input, List
}