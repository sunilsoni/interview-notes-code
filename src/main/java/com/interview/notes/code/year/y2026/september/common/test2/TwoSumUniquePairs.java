package com.interview.notes.code.year.y2026.september.common.test2;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class TwoSumUniquePairs {

    // --- Brute Force Method: O(N^2) Time ---
    public static Set<Pair> findPairsBruteForce(int[] nums, int target) {
        // Storage for unique pairs
        var result = new HashSet<Pair>();

        // Check if input array cannot form pairs
        if (nums == null || nums.length < 2) {
            // Return empty set
            return result;
        }

        // Outer pointer scans from the start
        for (int i = 0; i < nums.length; i++) {
            // Inner pointer scans elements to the right of outer pointer
            for (int j = i + 1; j < nums.length; j++) {
                // If sum matches target, record the pair
                if (nums[i] + nums[j] == target) {
                    // Add standardized pair to set
                    result.add(new Pair(nums[i], nums[j]));
                }
            }
        }
        // Return accumulated pairs
        return result;
    }

    // --- Optimal Method: O(N) Time using HashSet ---
    public static Set<Pair> findPairsOptimal(int[] nums, int target) {
        // Set to store the final unique pairs
        var result = new HashSet<Pair>();

        // Check if array has less than 2 numbers
        if (nums == null || nums.length < 2) {
            // Return empty set if impossible to form pair
            return result;
        }

        // Set to track numbers seen so far during iteration
        var seen = new HashSet<Integer>();

        // Iterate through each number in the array
        for (int num : nums) {
            // Calculate the exact partner needed to reach target sum
            int complement = target - num;

            // If the required partner was already encountered earlier
            if (seen.contains(complement)) {
                // Add the pair to result set (duplicates filtered automatically)
                result.add(new Pair(num, complement));
            }

            // Record current number as seen for subsequent elements
            seen.add(num);
        }

        // Return all unique matching pairs
        return result;
    }

    // --- Test Verification Helper ---
    private static void verify(String testName, Set<Pair> actual, Set<Pair> expected) {
        // Compare contents of actual set with expected set
        boolean passed = actual.equals(expected);
        // Print test name, status (PASS/FAIL), and contents
        if (passed) {
            System.out.println("[PASS] " + testName);
        } else {
            System.out.println("[FAIL] " + testName + " | Expected: " + expected + ", Got: " + actual);
        }
    }

    // Main execution entry point running all tests
    static void main(String[] args) {
        System.out.println("=== RUNNING FUNCTIONAL TEST CASES ===");

        // Test Case 1: Standard case with target 8
        int[] case1 = {2, 4, 3, 5, 6, -2, 4, 7, 8, 9};
        int target1 = 8;
        // Expected: (2, 6), (3, 5), (4, 4)
        var expected1 = Set.of(new Pair(2, 6), new Pair(3, 5), new Pair(4, 4));
        verify("Case 1 (Standard - BruteForce)", findPairsBruteForce(case1, target1), expected1);
        verify("Case 1 (Standard - Optimal)", findPairsOptimal(case1, target1), expected1);

        // Test Case 2: Array with heavy duplicate values
        int[] case2 = {4, 4, 4, 4, 4};
        int target2 = 8;
        // Expected: Only one unique pair (4, 4)
        var expected2 = Set.of(new Pair(4, 4));
        verify("Case 2 (Duplicates - BruteForce)", findPairsBruteForce(case2, target2), expected2);
        verify("Case 2 (Duplicates - Optimal)", findPairsOptimal(case2, target2), expected2);

        // Test Case 3: Negative numbers and zero
        int[] case3 = {-3, 5, 0, 8, 11, -1, 9};
        int target3 = 8;
        // Expected: (-3, 11), (0, 8), (-1, 9)
        var expected3 = Set.of(new Pair(-3, 11), new Pair(0, 8), new Pair(-1, 9));
        verify("Case 3 (Negatives & Zero - BruteForce)", findPairsBruteForce(case3, target3), expected3);
        verify("Case 3 (Negatives & Zero - Optimal)", findPairsOptimal(case3, target3), expected3);

        // Test Case 4: No matching pairs
        int[] case4 = {1, 2, 3, 9};
        int target4 = 8;
        // Expected: Empty set
        var expected4 = Set.<Pair>of();
        verify("Case 4 (No Matches - BruteForce)", findPairsBruteForce(case4, target4), expected4);
        verify("Case 4 (No Matches - Optimal)", findPairsOptimal(case4, target4), expected4);

        // Test Case 5: Edge cases (single element and empty array)
        int[] case5Empty = {};
        int[] case5Single = {8};
        verify("Case 5 (Empty Array)", findPairsOptimal(case5Empty, 8), Set.of());
        verify("Case 5 (Single Element)", findPairsOptimal(case5Single, 8), Set.of());

        // Test Case 6: Large Data Test (100,000 elements)
        System.out.println("\n=== RUNNING LARGE DATA TEST (100,000 elements) ===");
        int size = 100_000;
        int[] largeArray = new int[size];
        Random random = new Random(42);

        // Populate array with numbers between -10,000 and 10,000
        for (int i = 0; i < size; i++) {
            largeArray[i] = random.nextInt(20_001) - 10_000;
        }
        int largeTarget = 8;

        // Measure execution time of Optimal O(N) solution
        long startOptimal = System.currentTimeMillis();
        Set<Pair> optimalResult = findPairsOptimal(largeArray, largeTarget);
        long endOptimal = System.currentTimeMillis();

        // Print performance result
        System.out.println("[PASS] Large Data Optimal Solution completed successfully.");
        System.out.println("  -> Total Unique Pairs Found: " + optimalResult.size());
        System.out.println("  -> Time taken (Optimal): " + (endOptimal - startOptimal) + " ms");
    }

    // Record represents an immutable pair of integers with built-in equals, hashCode, and toString
    record Pair(int first, int second) {
        // Constructor standardizes order so Pair(3, 5) equals Pair(5, 3)
        Pair(int first, int second) {
            // Put the smaller value first
            this.first = Math.min(first, second);
            // Put the larger value second
            this.second = Math.max(first, second);
        }
    }
}