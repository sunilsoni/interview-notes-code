package com.interview.notes.code.year.y2026.feb.common.test1;

import java.util.Arrays;

public class MaxProductSubarray {

    // Main logic method using Java 21 syntax
    public static int maxProduct(int[] nums) {
        if (nums.length == 0) return 0; // Handle empty array edge case

        // var: Java 10+ feature for local variable type inference (cleaner code)
        var maxSoFar = nums[0]; // Tracks the highest product ending at current position
        var minSoFar = nums[0]; // Tracks the lowest product (needed for negative number flips)
        var result = maxSoFar;  // Stores the absolute maximum product found globally

        // Loop starting from 2nd element using standard for-loop for O(N) efficiency
        for (int i = 1; i < nums.length; i++) {
            var curr = nums[i]; // Store current number to avoid repeated array access

            // If current number is negative, swap max and min
            // Because: big positive * negative = big negative (new min)
            // And: big negative * negative = big positive (new max)
            if (curr < 0) {
                var temp = maxSoFar; // Temporary storage for swap
                maxSoFar = minSoFar; // Swap max with min
                minSoFar = temp;     // Complete the swap
            }

            // Update maxSoFar: either start new subarray at curr, or extend previous
            maxSoFar = Math.max(curr, maxSoFar * curr);

            // Update minSoFar: either start new subarray at curr, or extend previous
            minSoFar = Math.min(curr, minSoFar * curr);

            // Update global result if local max is higher
            result = Math.max(result, maxSoFar);
        }

        return result; // Return final maximum product
    }

    // Simple custom testing method (No JUnit)
    public static void runTest(String testName, int[] input, int expected) {
        long startTime = System.nanoTime(); // Start timer for performance check

        var actual = maxProduct(input); // Execute logic

        long endTime = System.nanoTime(); // End timer

        // Check if actual matches expected
        var status = (actual == expected) ? "PASS" : "FAIL";

        // Print result in a clean format
        System.out.printf("[%s] Case: %-15s | Exp: %d | Act: %d | Time: %d ns%n",
                status, testName, expected, actual, (endTime - startTime));
    }

    public static void main(String[] args) {
        System.out.println("Running Tests with Java 21...");

        // --- Standard Cases ---
        runTest("Standard 1", new int[]{2, 3, -2, 4}, 6);       // [2,3] -> 6
        runTest("Standard 2", new int[]{-2, 0, -1}, 0);         // 0 is max

        // --- Edge Cases ---
        runTest("Single Negative", new int[]{-2}, -2);          // Single element
        runTest("Double Negative", new int[]{-2, -3}, 6);       // Neg * Neg = Pos
        runTest("Zero Sandwich", new int[]{-2, 0, -3}, 0);      // Zero breaks chain
        runTest("Mixed Sandwich", new int[]{2, -5, -2, -4, 3}, 24); // [-5, -2, -4, 3] -> wrong logic, check math: 2*-5=-10, -10*-2=20, 20*-4=-80. Actually [-2, -4, 3] = 24

        // --- Large Data Input ---
        // Generating 1,000,000 elements. Pattern: 1, 1, 1... 
        // We use all 1s so result is 1, but it tests O(N) speed without overflow issues.
        int largeSize = 1_000_000; // Define large size
        int[] largeData = new int[largeSize]; // Allocate array
        Arrays.fill(largeData, 1); // Fill with 1s

        // Add a "2" in the middle so the answer should be 2
        largeData[500000] = 2;

        runTest("Large Data (1M)", largeData, 2); // Should pass quickly
    }
}