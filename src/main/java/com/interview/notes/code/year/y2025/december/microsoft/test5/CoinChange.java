package com.interview.notes.code.year.y2025.december.microsoft.test5;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class CoinChange {

    // This method calculates the minimum number of coins needed to make the given amount
    public static int minCoins(int[] coins, int amount) {
        // Create a DP array to store minimum coins for each value from 0 to amount
        int[] dp = new int[amount + 1];

        // Fill the array with a large number (we use amount + 1 as "infinity")
        Arrays.fill(dp, amount + 1);

        // Base case: 0 coins needed to make amount 0
        dp[0] = 0;

        // Loop through all amounts from 1 to target amount
        for (int i = 1; i <= amount; i++) {
            // Try every coin to see if it can contribute to current amount i
            for (int coin : coins) {
                if (coin <= i) {
                    // Update dp[i] with the minimum coins needed
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        // If dp[amount] is still "infinity", return -1 (no solution)
        return dp[amount] > amount ? -1 : dp[amount];
    }

    // Main method to test multiple cases and print PASS/FAIL
    public static void main(String[] args) {
        // Define test cases as a list of inputs and expected outputs
        List<TestCase> testCases = Arrays.asList(
                new TestCase(new int[]{1, 5, 10, 25}, 67, 6),     // Example from problem
                new TestCase(new int[]{2}, 3, -1),                // No solution case
                new TestCase(new int[]{1, 2, 5}, 11, 3),          // Standard case
                new TestCase(new int[]{1}, 0, 0),                 // Zero amount
                new TestCase(new int[]{1}, 10000, 10000),         // Large input case
                new TestCase(IntStream.rangeClosed(1, 100).toArray(), 9999, 100) // Stress test
        );

        // Loop through each test case and check result
        for (int i = 0; i < testCases.size(); i++) {
            TestCase tc = testCases.get(i);
            int result = minCoins(tc.coins, tc.amount);
            boolean pass = result == tc.expected;
            System.out.println("Test Case " + (i + 1) + ": " + (pass ? "PASS" : "FAIL"));
        }
    }

    // Helper class to store test case data
    static class TestCase {
        int[] coins;
        int amount;
        int expected;

        TestCase(int[] coins, int amount, int expected) {
            this.coins = coins;
            this.amount = amount;
            this.expected = expected;
        }
    }
}
