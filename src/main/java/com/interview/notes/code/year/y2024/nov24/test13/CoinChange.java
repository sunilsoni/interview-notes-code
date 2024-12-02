package com.interview.notes.code.year.y2024.nov24.test13;

public class CoinChange {
    public static long calculateWays(int amount) {
        if (amount < 0) return 0;

        // coins available: quarter (25), dime (10), nickel (5), penny (1)
        int[] coins = {25, 10, 5, 1};

        // dp array to store number of ways
        long[] dp = new long[amount + 1];
        dp[0] = 1; // base case: one way to make 0 cents

        // For each coin, calculate ways for all amounts from coin value to target
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }

        return dp[amount];
    }

    public static void main(String[] args) {
        // Test cases
        int[] testCases = {4, 10, 25, 100, 200};
        long[] expectedResults = {4, 9, 13, 242, 2728};

        System.out.println("Running test cases...\n");

        // Run each test case
        for (int i = 0; i < testCases.length; i++) {
            int amount = testCases[i];
            long expected = expectedResults[i];
            long result = calculateWays(amount);

            boolean passed = (result == expected);

            System.out.printf("Test Case %d: Amount = %d cents\n", i + 1, amount);
            System.out.printf("Expected: %d, Got: %d\n", expected, result);
            System.out.printf("Status: %s\n\n", passed ? "PASS" : "FAIL");
        }

        // Edge cases
        System.out.println("Testing edge cases...\n");

        // Test negative amount
        testEdgeCase(-1, 0, "Negative amount");

        // Test zero amount
        testEdgeCase(0, 1, "Zero amount");

        // Test large amount
        testEdgeCase(1000, 142511, "Large amount");
    }

    private static void testEdgeCase(int amount, long expected, String description) {
        long result = calculateWays(amount);
        boolean passed = (result == expected);

        System.out.printf("Edge Case: %s (Amount = %d)\n", description, amount);
        System.out.printf("Expected: %d, Got: %d\n", expected, result);
        System.out.printf("Status: %s\n\n", passed ? "PASS" : "FAIL");
    }
}
