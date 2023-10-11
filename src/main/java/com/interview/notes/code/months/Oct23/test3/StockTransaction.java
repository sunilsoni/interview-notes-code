package com.interview.notes.code.months.Oct23.test3;

public class StockTransaction {

    public static int maxProfit(int k, int[] prices) {
        // Create a 2D array to store the maximum profit at each day
        // for each number of transactions.
        int[][] profits = new int[k + 1][prices.length];

        // Initialize the profits array.
        for (int i = 0; i <= k; i++) {
            for (int j = 0; j < prices.length; j++) {
                profits[i][j] = 0;
            }
        }

        // Iterate over the prices array and calculate the maximum
        // profit at each day for each number of transactions.
        for (int i = 1; i <= k; i++) {
            for (int j = 1; j < prices.length; j++) {
                // The maximum profit at day j for i transactions is
                // the maximum of the following two values:
                // 1. The maximum profit at day j - 1 for i transactions.
                // 2. The current price minus the minimum price from day 0
                // to day j - 1 plus the maximum profit at day j - 1
                // for i - 1 transactions.

                profits[i][j] = Math.max(profits[i][j - 1], prices[j] - getMinPrice(0, j - 1, prices) + profits[i - 1][j - 1]);
            }
        }

        // Return the maximum profit at the last day for k transactions.
        return profits[k][prices.length - 1];
    }

    // Returns the minimum price from day i to day j in the given prices array.
    private static int getMinPrice(int i, int j, int[] prices) {
        int minPrice = prices[i];
        for (int k = i + 1; k <= j; k++) {
            if (prices[k] < minPrice) {
                minPrice = prices[k];
            }
        }
        return minPrice;
    }
}
