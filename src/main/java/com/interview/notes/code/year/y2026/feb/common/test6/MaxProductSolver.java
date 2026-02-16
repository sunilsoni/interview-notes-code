package com.interview.notes.code.year.y2026.feb.common.test6;

import java.util.Arrays;

public class MaxProductSolver {

    /**
     * Finds the maximum product of a contiguous subarray.
     * Uses long to prevent overflow on large data inputs.
     */
    public static long solve(int[] nums) {
        // Edge case: if array is empty, return 0
        if (nums == null || nums.length == 0) return 0;

        // Initialize max and min tracking variables with the first element
        // We use 'double' or 'long' here to handle large product results safely
        long currentMax = nums[0]; // Tracks the highest product ending at current position
        long currentMin = nums[0]; // Tracks the lowest product (negative) ending at current position
        long globalMax = nums[0];  // Tracks the highest product found so far anywhere

        // Loop through the array starting from the second element
        for (int i = 1; i < nums.length; i++) {
            long num = nums[i]; // Get current number as long

            // If current number is negative, swap max and min
            // Because: big positive * negative = big negative (new min)
            // And: big negative * negative = big positive (new max)
            if (num < 0) {
                long temp = currentMax; // Store currentMax temporarily
                currentMax = currentMin; // Swap logic
                currentMin = temp;      // Swap logic
            }

            // Update currentMax: It's either the current number alone (restart) 
            // or the current number * previous max (continue chain)
            currentMax = Math.max(num, currentMax * num);

            // Update currentMin: It's either the current number alone 
            // or current number * previous min
            currentMin = Math.min(num, currentMin * num);

            // Update the global result if we found a new highest
            globalMax = Math.max(globalMax, currentMax);
        }

        return globalMax; // Return the final result
    }

    // --- Simple Test Runner ---

    public static void main(String[] args) {
        System.out.println("--- Starting Tests ---");

        // Test Case 1: The problem example
        // Logic: 3 -> 6 -> 30 -> -30 -> -60 -> 540 (-60 * -9)
        test(new int[]{3, 2, 5, -1, 2, -9}, 540);

        // Test Case 2: Array with zero
        // Logic: Subarrays are [2, 3] = 6, or [4] = 4. 0 resets.
        test(new int[]{2, 3, -2, 4}, 6);

        // Test Case 3: All negatives
        // Logic: -2 * -3 = 6 is the max.
        test(new int[]{-2, 0, -1}, 0);

        // Test Case 4: Edge case single element
        test(new int[]{-5}, -5);

        // Test Case 5: Large Data Input (Values that create large product)
        // 10 * 10 * 10 * 10 * 10 = 100,000
        test(new int[]{10, 10, 10, 10, 10}, 100000);

        // Test Case 6: Alternating signs
        // 2*-5 = -10; -10*-2 = 20; 20*-4 = -80; -80*3 = -240. Max is 20.
        test(new int[]{2, -5, -2, -4, 3}, 20);

        System.out.println("--- All Tests Completed ---");
    }

    /**
     * Helper method to run tests and print PASS/FAIL status.
     */
    private static void test(int[] input, long expected) {
        long result = solve(input); // Execute logic
        String status = (result == expected) ? "PASS" : "FAIL"; // Determine status

        // Print clear summary using Java formatted strings
        System.out.printf("Input: %s | Expected: %d | Actual: %d | Status: %s%n",
                Arrays.toString(input), expected, result, status);
    }
}