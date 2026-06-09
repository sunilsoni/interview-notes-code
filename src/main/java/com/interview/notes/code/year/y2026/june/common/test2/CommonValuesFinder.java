package com.interview.notes.code.year.y2026.june.common.test2;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class CommonValuesFinder {

    public static void main(String[] args) {
        // --- TEST CASE 1: Provided Input ---
        // Create the first immutable list of integers with duplicate values using Java 9+ List.of()
        List<Integer> ints1 = List.of(1, 2, 3, 4, 5, 6, 6, 5, 4, 3, 2, 1);
        
        // Create the second immutable list of integers to compare against
        List<Integer> ints2 = List.of(6, 5, 4, 3, 2, 1);
        
        // Expected output: unique common elements between both lists
        List<Integer> expected1 = List.of(1, 2, 3, 4, 5, 6);
        
        // Execute the function and store the result
        List<Integer> result1 = findCommon(ints1, ints2);
        
        // Validate and print the result using our custom test method
        runTest("Test Case 1 (Standard)", result1, expected1);


        // --- TEST CASE 2: Large Data Input (Performance Check) ---
        // Generate a massive list of 1 million numbers to ensure our code doesn't freeze (O(N) performance)
        List<Integer> largeList1 = IntStream.rangeClosed(1, 1_000_000).boxed().toList();
        
        // Generate a second list of 500,000 numbers
        List<Integer> largeList2 = IntStream.rangeClosed(500_000, 1_500_000).boxed().toList();
        
        // The expected common numbers are exactly the overlap: 500,000 to 1,000,000
        List<Integer> expectedLarge = IntStream.rangeClosed(500_000, 1_000_000).boxed().toList();
        
        // Execute the function on the massive dataset
        List<Integer> resultLarge = findCommon(largeList1, largeList2);
        
        // Validate and print the result for the large dataset
        runTest("Test Case 2 (Large Data)", resultLarge, expectedLarge);
    }

    /**
     * Core logic method to find common elements efficiently.
     */
    public static List<Integer> findCommon(List<Integer> list1, List<Integer> list2) {
        // Convert list2 into a HashSet to allow lightning-fast O(1) lookups, crucial for large data
        Set<Integer> fastLookupSet = new HashSet<>(list2);
        
        // Return the final processed data
        return list1.stream()                   // Open a stream to process elements of list1 one by one sequentially
                    .filter(fastLookupSet::contains) // Keep the element ONLY if it exists in our fastLookupSet
                    .distinct()                 // Strip out any duplicate numbers so we only return unique common values
                    .toList();                  // Collect the remaining elements into a fresh, immutable List (Java 16+ feature)
    }

    /**
     * Simple test runner replacing JUnit.
     */
    public static void runTest(String testName, List<Integer> actual, List<Integer> expected) {
        // Check if both lists have the exact same size and contain identical elements (ignoring order)
        boolean isPass = actual.size() == expected.size() && actual.containsAll(expected);
        
        // Print the test name alongside PASS or FAIL based on the boolean result
        System.out.println(testName + " -> " + (isPass ? "PASS" : "FAIL"));
    }
}