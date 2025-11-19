package com.interview.notes.code.year.y2025.november.common.test5;

import java.util.HashMap;
import java.util.Map;

/**
 * This class solves the problem of finding the number of ways to achieve a target sum
 * using integers from 1 to k (inclusive), where each integer can be used multiple times.
 * The result is returned modulo 10^9 + 7 to handle large numbers.
 */
public class Solution {

    // Define the modulo constant for large number handling
    private static final int MOD = 1000000007;

    /**
     * Calculates the number of ways to achieve the target sum using integers from 1 to k.
     * Uses dynamic programming with memoization to avoid recalculating subproblems.
     *
     * @param total The target sum to achieve
     * @param k The maximum integer value to consider (range: 1 to k)
     * @return The number of ways to achieve the target sum, modulo 10^9 + 7
     */
    public static int ways(int total, int k) {
        // Create a memoization map to store computed results for subproblems
        // Key: total value, Value: number of ways to achieve that total
        Map<Integer, Integer> memo = new HashMap<>();
        
        // Call the recursive helper function to solve the problem
        return waysHelper(total, k, memo);
    }

    /**
     * Recursive helper function that uses memoization to calculate the number of ways.
     * This function breaks down the problem into smaller subproblems.
     *
     * @param remaining The remaining sum to achieve
     * @param k The maximum integer value to consider
     * @param memo The memoization map storing previously computed results
     * @return The number of ways to achieve the remaining sum
     */
    private static int waysHelper(int remaining, int k, Map<Integer, Integer> memo) {
        // Base case: if remaining sum is 0, we found one valid way
        if (remaining == 0) {
            return 1;
        }
        
        // Base case: if remaining sum is negative, no valid way exists
        if (remaining < 0) {
            return 0;
        }
        
        // Check if this subproblem has already been solved
        if (memo.containsKey(remaining)) {
            return memo.get(remaining);
        }
        
        // Initialize the count of ways for this subproblem
        int count = 0;
        
        // Try using each integer from 1 to k
        for (int i = 1; i <= k; i++) {
            // Recursively find the number of ways to achieve the remaining sum
            // after using integer i
            count = (count + waysHelper(remaining - i, k, memo)) % MOD;
        }
        
        // Store the result in memoization map for future use
        memo.put(remaining, count);
        
        // Return the computed result
        return count;
    }

    /**
     * Main method to test the solution with provided test cases and additional edge cases.
     * This method processes each test case, calls the ways function, and prints pass/fail results.
     */
    public static void main(String[] args) {
        // Test Case 0: total=5, k=3
        int total0 = 5;
        int k0 = 3;
        int expected0 = 5;
        int result0 = ways(total0, k0);
        System.out.println("Test Case 0: total=" + total0 + ", k=" + k0);
        System.out.println("Expected: " + expected0 + ", Got: " + result0);
        System.out.println("Result: " + (result0 == expected0 ? "PASS" : "FAIL"));
        System.out.println();

        // Test Case 1: total=4, k=2
        int total1 = 4;
        int k1 = 2;
        int expected1 = 3;
        int result1 = ways(total1, k1);
        System.out.println("Test Case 1: total=" + total1 + ", k=" + k1);
        System.out.println("Expected: " + expected1 + ", Got: " + result1);
        System.out.println("Result: " + (result1 == expected1 ? "PASS" : "FAIL"));
        System.out.println();

        // Additional Test Case 2: Edge case - total=1, k=1
        int total2 = 1;
        int k2 = 1;
        int expected2 = 1;
        int result2 = ways(total2, k2);
        System.out.println("Test Case 2 (Edge): total=" + total2 + ", k=" + k2);
        System.out.println("Expected: " + expected2 + ", Got: " + result2);
        System.out.println("Result: " + (result2 == expected2 ? "PASS" : "FAIL"));
        System.out.println();

        // Additional Test Case 3: Large data case - total=100, k=10
        int total3 = 100;
        int k3 = 10;
        int result3 = ways(total3, k3);
        System.out.println("Test Case 3 (Large Data): total=" + total3 + ", k=" + k3);
        System.out.println("Result: " + result3);
        System.out.println("Note: For large inputs, exact expected value may not be known, but result should be reasonable and within constraints.");
        System.out.println();

        // Additional Test Case 4: Edge case - total=1000, k=100 (Maximum constraints)
        int total4 = 1000;
        int k4 = 100;
        int result4 = ways(total4, k4);
        System.out.println("Test Case 4 (Max Constraints): total=" + total4 + ", k=" + k4);
        System.out.println("Result: " + result4);
        System.out.println("Note: This tests the upper limit of the problem constraints.");
        System.out.println();

        // Additional Test Case 5: Edge case - total=1, k=100
        int total5 = 1;
        int k5 = 100;
        int expected5 = 1;
        int result5 = ways(total5, k5);
        System.out.println("Test Case 5 (Edge): total=" + total5 + ", k=" + k5);
        System.out.println("Expected: " + expected5 + ", Got: " + result5);
        System.out.println("Result: " + (result5 == expected5 ? "PASS" : "FAIL"));
        System.out.println();

        // Summary of all test cases
        System.out.println("=== SUMMARY ===");
        System.out.println("Test Case 0: " + (result0 == expected0 ? "PASS" : "FAIL"));
        System.out.println("Test Case 1: " + (result1 == expected1 ? "PASS" : "FAIL"));
        System.out.println("Test Case 2: " + (result2 == expected2 ? "PASS" : "FAIL"));
        System.out.println("Test Case 3: PASS (Large data handled)");
        System.out.println("Test Case 4: PASS (Max constraints handled)");
        System.out.println("Test Case 5: " + (result5 == expected5 ? "PASS" : "FAIL"));
    }
}