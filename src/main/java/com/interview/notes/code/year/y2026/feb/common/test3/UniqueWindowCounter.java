package com.interview.notes.code.year.y2026.feb.common.test3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class UniqueWindowCounter {

    // Logic to find unique counts in windows
    public static List<Integer> countDistinct(int[] arr, int k) {
        // Use var for type inference (Java 10+)
        var result = new ArrayList<Integer>(); // List to store the answer for each window

        // HashMap to store Number -> Frequency. Size of map = count of unique items
        var map = new HashMap<Integer, Integer>();

        // Loop through the entire array once (Linear Time O(N))
        for (int i = 0; i < arr.length; i++) {

            // Add current element to map. 'merge' updates count cleanly (Java 8+)
            // If key exists, add 1 to old value. If not, put 1.
            map.merge(arr[i], 1, Integer::sum);

            // Logic to remove the element sliding out of the window
            // We only start removing after the window is fully formed (index >= k)
            if (i >= k) {
                int elementToRemove = arr[i - k]; // Identify the element leaving the window

                // Decrease count. If count becomes 0, remove key entirely.
                // computeIfPresent simplifies the "check and remove" logic
                map.computeIfPresent(elementToRemove, (key, count) -> count > 1 ? count - 1 : null);
            }

            // If we have hit window size 'k' or more, record the unique count
            if (i >= k - 1) {
                result.add(map.size()); // Map size represents unique elements count
            }
        }
        return result; // Return the list of counts
    }

    // --- Simple Test Framework (No JUnit) ---

    public static void main(String[] args) {
        System.out.println("--- Starting Tests ---");

        // Test Case 1: Provided Input
        int[] input1 = {10, 20, 30, 10, 20};
        int k1 = 3;
        List<Integer> expected1 = List.of(3, 3, 3);
        runTest("Test Case 1 (Basic)", input1, k1, expected1);

        // Test Case 2: Duplicates inside window
        int[] input2 = {1, 2, 1, 3, 4, 2, 3};
        int k2 = 4;
        // Window 1 [1,2,1,3] -> Unique: 1,2,3 (Size 3)
        // Window 2 [2,1,3,4] -> Unique: 2,1,3,4 (Size 4)
        // Window 3 [1,3,4,2] -> Unique: 1,3,4,2 (Size 4)
        // Window 4 [3,4,2,3] -> Unique: 3,4,2 (Size 3)
        List<Integer> expected2 = List.of(3, 4, 4, 3);
        runTest("Test Case 2 (Duplicates)", input2, k2, expected2);

        // Test Case 3: All same elements
        int[] input3 = {5, 5, 5, 5};
        int k3 = 3;
        // Window [5,5,5] -> Unique: 5 (Size 1)
        List<Integer> expected3 = List.of(1, 1);
        runTest("Test Case 3 (Identical)", input3, k3, expected3);

        // Test Case 4: Large Data Simulation
        runLargeDataTest();
    }

    // Helper method to run small tests
    public static void runTest(String testName, int[] input, int k, List<Integer> expected) {
        var actual = countDistinct(input, k); // Run logic
        boolean passed = actual.equals(expected); // Compare lists
        System.out.println(testName + ": " + (passed ? "PASS" : "FAIL"));
        if (!passed) System.out.println("   Expected: " + expected + ", Got: " + actual);
    }

    // Helper for Large Data (Performance Check)
    public static void runLargeDataTest() {
        System.out.print("Test Case 4 (Large Data 1M elements)... ");
        int size = 1_000_000;
        int[] largeInput = new int[size];

        // Fill with random data
        Arrays.fill(largeInput, 1); // Worst case for collisions or simple fills

        long start = System.currentTimeMillis(); // Start timer
        var result = countDistinct(largeInput, 1000); // Run with k=1000
        long end = System.currentTimeMillis(); // End timer

        // Validation: Logic should not crash and return correct result size
        boolean passed = result.size() == (size - 1000 + 1);

        System.out.println(passed ? "PASS" : "FAIL");
        System.out.println("   Time taken: " + (end - start) + "ms");
    }
}