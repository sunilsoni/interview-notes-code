package com.interview.notes.code.year.y2025.august.common.test1;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FindDuplicates {

    /**
     * Method to find all duplicate numbers in the array.
     *
     * @param nums The input integer array.
     * @return A list of duplicate numbers.
     */
    public static List<Integer> findDuplicates(int[] nums) {
        // Convert the int[] array to a List<Integer> using Arrays.stream and boxed()
        // Why? Because Java Streams operate more naturally on Objects than primitives.
        return Arrays.stream(nums)
                .boxed() // Convert each int to Integer
                // Group numbers by value and count how many times each occurs
                .collect(Collectors.groupingBy(num -> num, Collectors.counting()))
                // Convert the Map into a Stream of entries (number -> count)
                .entrySet().stream()
                // Keep only entries where count > 1 (duplicates)
                .filter(entry -> entry.getValue() > 1)
                // Map from entry to just the number (key)
                .map(Map.Entry::getKey)
                // Collect result into a List<Integer>
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        // Define test cases as arrays and expected results
        int[][] testArrays = {
                {4, 3, 2, 7, 8, 2, 3, 1},   // Expected: [2,3]
                {1, 1, 2},             // Expected: [1]
                {1, 2, 3, 4, 5},         // Expected: []
                {},                  // Expected: []
                {5, 5, 5, 5, 5},         // Expected: [5]
        };

        List<List<Integer>> expectedResults = Arrays.asList(
                Arrays.asList(2, 3),
                Arrays.asList(1),
                Arrays.asList(),
                Arrays.asList(),
                Arrays.asList(5)
        );

        // Large test case: 1 million elements with duplicates at the end
        int[] largeArray = new int[1_000_000];
        for (int i = 0; i < largeArray.length; i++) {
            largeArray[i] = i;
        }
        largeArray[999_998] = 42; // Duplicate value
        largeArray[999_999] = 42; // Duplicate value

        // Run all test cases and check pass/fail
        for (int i = 0; i < testArrays.length; i++) {
            List<Integer> result = findDuplicates(testArrays[i]);
            boolean pass = new HashSet<>(result).equals(new HashSet<>(expectedResults.get(i)));
            System.out.println("Test Case " + (i + 1) + " -> " + (pass ? "PASS" : "FAIL")
                    + " | Output: " + result + " | Expected: " + expectedResults.get(i));
        }

        // Test with large data to check performance
        long startTime = System.currentTimeMillis();
        List<Integer> largeResult = findDuplicates(largeArray);
        long endTime = System.currentTimeMillis();
        System.out.println("\nLarge Data Test -> Found Duplicates: " + largeResult
                + " | Time: " + (endTime - startTime) + " ms");
    }
}