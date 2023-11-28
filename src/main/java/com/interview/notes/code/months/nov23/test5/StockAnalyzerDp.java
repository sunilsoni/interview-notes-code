package com.interview.notes.code.months.nov23.test5;

/**
 * Complexity Analysis
 * <p>
 * Time Complexity: O(n^2) - The precomputation of maximums takes O(n), and the double loop for counting maximum profitable groups takes O(n^2).
 * Space Complexity: O(n) - Due to the additional arrays used for storing left and right maximums.
 * This dynamic programming approach significantly improves the performance, especially for larger arrays, by avoiding redundant computations.
 */
public class StockAnalyzerDp {
    public static void main(String[] args) {
        int[] stockPrices = {2, 3, 2}; // Example input
        System.out.println("Number of maximum profitable groups: " + countMaxProfitableGroups(stockPrices));
    }

    private static int countMaxProfitableGroups(int[] prices) {
        int n = prices.length;
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];
        int count = 0;

        // Compute leftMax
        leftMax[0] = prices[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], prices[i]);
        }

        // Compute rightMax
        rightMax[n - 1] = prices[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], prices[i]);
        }

        // Count maximum profitable groups
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (prices[i] == rightMax[j] || prices[j] == leftMax[i]) {
                    count++;
                }
            }
        }
        return count;
    }
}
