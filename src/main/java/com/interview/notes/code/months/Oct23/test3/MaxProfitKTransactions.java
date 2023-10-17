package com.interview.notes.code.months.Oct23.test3;

public class MaxProfitKTransactions {
    public static void main(String[] args) {
        MaxProfitKTransactions obj = new MaxProfitKTransactions();

        // Test with Example 1
        int k1 = 2;
        int[] prices1 = {2, 4, 1};
        int result1 = obj.maxProfit(k1, prices1);
        System.out.println("Example 1 Output: " + result1);  // Output should be 2

        // Test with Example 2
        int k2 = 2;
        int[] prices2 = {3, 2, 6, 5, 0, 3};
        int result2 = obj.maxProfit(k2, prices2);
        System.out.println("Example 2 Output: " + result2);  // Output should be 7
    }

    public int maxProfit(int k, int[] prices) {
        // Step 1: Handle edge cases
        int n = prices.length;
        if (k >= n / 2) {
            // Make as many transactions as possible
            int maxProfit = 0;
            for (int i = 1; i < n; i++) {
                maxProfit += Math.max(0, prices[i] - prices[i - 1]);
            }
            return maxProfit;  // Time Complexity: O(n), Space Complexity: O(1)
        }

        // Step 2: Initialize DP array
        int[][] dp = new int[k + 1][n];

        // Step 3: Populate DP array
        for (int i = 1; i <= k; i++) {
            int maxDiff = -prices[0];
            for (int j = 1; j < n; j++) {
                // Update dp[i][j] for k transactions up to j-th day
                dp[i][j] = Math.max(dp[i][j - 1], prices[j] + maxDiff);

                // Update maxDiff for future transactions
                maxDiff = Math.max(maxDiff, dp[i - 1][j] - prices[j]);
            }
        }

        // Step 4: Return the answer
        return dp[k][n - 1];  // Time Complexity: O(k * n), Space Complexity: O(k * n)
    }
}
