package com.interview.notes.code.year.y2024.nov24.test14;

public class CoinChangeWays {

    // Recursive solution with memoization
    public static long calculateCoinWays(int n) {
        int[] coins = {25, 10, 5, 1}; // Quarter, Dime, Nickel, Penny
        long[][] memo = new long[n + 1][coins.length];
        return countWays(n, coins, 0, memo);
    }

    private static long countWays(int remaining, int[] coins, int coinIndex, long[][] memo) {
        // Base cases
        if (remaining == 0) return 1;
        if (remaining < 0 || coinIndex >= coins.length) return 0;

        // Check memoized result
        if (memo[remaining][coinIndex] != 0) {
            return memo[remaining][coinIndex];
        }

        // Two recursive choices:
        // 1. Include current coin
        // 2. Skip current coin
        long includeCurrentCoin = countWays(remaining - coins[coinIndex], coins, coinIndex, memo);
        long skipCurrentCoin = countWays(remaining, coins, coinIndex + 1, memo);

        // Memoize and return total ways
        memo[remaining][coinIndex] = includeCurrentCoin + skipCurrentCoin;
        return memo[remaining][coinIndex];
    }

    // Performance and stress testing method
    public static void testCoinChangeWays() {
        // Test cases with increasing complexity
        int[] testCases = {
                0,      // Edge case: Zero cents
                1,      // Minimum possible input
                5,      // Nickel
                10,     // Dime
                25,     // Quarter
                100,    // Complex scenario
                500,    // Large input
                1000    // Very large input
        };

        for (int testCase : testCases) {
            long startTime = System.nanoTime();
            long ways = calculateCoinWays(testCase);
            long endTime = System.nanoTime();

            System.out.printf("Test Case %d cents: %d ways (Execution Time: %d ns)%n",
                    testCase, ways, (endTime - startTime));
        }

        // Performance stress test
        long largeInput = 5000;
        long startTime = System.nanoTime();
        long result = calculateCoinWays((int) largeInput);
        long endTime = System.nanoTime();

        System.out.printf("Large Input %d cents: %d ways (Execution Time: %d ns)%n",
                largeInput, result, (endTime - startTime));
    }

    public static void main(String[] args) {
        testCoinChangeWays();
    }
}
