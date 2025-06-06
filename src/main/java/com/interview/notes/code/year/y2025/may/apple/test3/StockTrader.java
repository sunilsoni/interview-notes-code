package com.interview.notes.code.year.y2025.may.apple.test3;

public class StockTrader {

    /**
     * Calculates maximum profit with both transaction fee and cooldown constraints
     *
     * @param prices       Array of daily stock prices
     * @param fee          Transaction fee for each trade (0 if no fee)
     * @param cooldownDays Number of days to wait after selling (0 if no cooldown)
     * @return Maximum profit possible
     */
    public static int maxProfitConstrained(int[] prices, int fee, int cooldownDays) {
        // Handle edge cases
        if (prices == null || prices.length <= 1) return 0;

        // dp array to track states for each day
        // dp[i][0] = max profit when holding stock
        // dp[i][1] = max profit when not holding stock and can buy
        // dp[i][2] = max profit when in cooldown period
        int[][] dp = new int[prices.length][3];

        // Initialize first day
        dp[0][0] = -prices[0];  // Buying stock on day 0
        dp[0][1] = 0;           // Not doing anything on day 0
        dp[0][2] = Integer.MIN_VALUE;  // Cannot be in cooldown on day 0

        // Process each day
        for (int i = 1; i < prices.length; i++) {
            // Hold state: either keep holding or buy new stock
            dp[i][0] = Math.max(dp[i - 1][0],           // Continue holding
                    dp[i - 1][1] - prices[i]); // Buy new stock

            // Free state: either stay free or sell stock (consider fee)
            dp[i][1] = Math.max(dp[i - 1][1],           // Stay free
                    (i > cooldownDays) ?     // Check if cooldown period passed
                            dp[i - 1][2] : Integer.MIN_VALUE); // Come from cooldown

            // Cooldown state: sell stock and enter cooldown
            dp[i][2] = dp[i - 1][0] + prices[i] - fee;  // Sell stock with fee
        }

        // Return maximum of free state and cooldown state
        return Math.max(dp[prices.length - 1][1], dp[prices.length - 1][2]);
    }

    public static void main(String[] args) {
        // Test cases
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

        // Test 5: Large dataset performance test
        System.out.println("\nPerformance Test:");
        int[] prices5 = new int[10000];
        for (int i = 0; i < prices5.length; i++) {
            prices5[i] = i % 100;  // Creates repeating pattern
        }

        long startTime = System.currentTimeMillis();
        maxProfitConstrained(prices5, 2, 1);
        long endTime = System.currentTimeMillis();

        System.out.println("Large dataset (10000 elements) processed in: " +
                (endTime - startTime) + "ms");
    }
}
