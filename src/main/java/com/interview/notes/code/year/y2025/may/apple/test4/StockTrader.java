package com.interview.notes.code.year.y2025.may.apple.test4;

public class StockTrader {

    public static int maxProfitConstrained(int[] prices, int fee, int cooldownDays) {
        if (prices == null || prices.length <= 1) return 0;

        int n = prices.length;

        // dp[i][0]: maximum profit if we are holding stock at day i
        // dp[i][1]: maximum profit if we are not holding stock and can buy at day i
        int[][] dp = new int[n][2];

        // Initialize first day
        dp[0][0] = -prices[0];  // Buy stock
        dp[0][1] = 0;           // Do nothing

        // For each day
        for (int i = 1; i < n; i++) {
            // If holding stock, either:
            // 1. Keep holding from previous day
            // 2. Buy stock after waiting for cooldown period
            dp[i][0] = dp[i - 1][0];  // Continue holding
            if (i > cooldownDays) {
                dp[i][0] = Math.max(dp[i][0], dp[i - cooldownDays - 1][1] - prices[i]);
            } else if (i > 0) {
                dp[i][0] = Math.max(dp[i][0], -prices[i]);
            }

            // If not holding stock, either:
            // 1. Stay without stock from previous day
            // 2. Sell stock from previous day (consider fee)
            dp[i][1] = Math.max(dp[i - 1][1],              // Stay without stock
                    dp[i - 1][0] + prices[i] - fee);  // Sell stock
        }

        return dp[n - 1][1];  // Return maximum profit without holding stock
    }

    public static void main(String[] args) {
        System.out.println("Running tests...\n");

        // Test 1: Only fee, no cooldown
        int[] prices1 = {1, 3, 2, 8, 4, 9};
        int result1 = maxProfitConstrained(prices1, 2, 0);
        System.out.println("Test 1 (Fee=2, No Cooldown): " +
                (result1 == 8 ? "PASS" : "FAIL") +
                " (Expected: 8, Got: " + result1 + ")");

        // Test 2: Only cooldown, no fee
        int[] prices2 = {1, 2, 3, 0, 2};
        int result2 = maxProfitConstrained(prices2, 0, 1);
        System.out.println("Test 2 (No Fee, Cooldown=1): " +
                (result2 == 3 ? "PASS" : "FAIL") +
                " (Expected: 3, Got: " + result2 + ")");

        // Test 3: Both fee and cooldown
        int[] prices3 = {1, 4, 6, 2, 8, 3, 9};
        int result3 = maxProfitConstrained(prices3, 1, 1);
        System.out.println("Test 3 (Fee=1, Cooldown=1): " +
                (result3 == 11 ? "PASS" : "FAIL") +
                " (Expected: 11, Got: " + result3 + ")");

        // Test 4: Edge case - empty array
        int[] prices4 = {};
        int result4 = maxProfitConstrained(prices4, 1, 1);
        System.out.println("Test 4 (Empty Array): " +
                (result4 == 0 ? "PASS" : "FAIL") +
                " (Expected: 0, Got: " + result4 + ")");

        System.out.println("\nPerformance Test:");
        int[] prices5 = new int[10000];
        for (int i = 0; i < prices5.length; i++) {
            prices5[i] = i % 100;
        }

        long startTime = System.currentTimeMillis();
        maxProfitConstrained(prices5, 2, 1);
        long endTime = System.currentTimeMillis();

        System.out.println("Large dataset (10000 elements) processed in: " +
                (endTime - startTime) + "ms");
    }
}
