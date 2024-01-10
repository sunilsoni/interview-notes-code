package com.interview.notes.code.months.year2023.nov23.test5;

/**
 * A team of analysts at Amazon needs to analyze the stock prices of Amazon over a period of several months. A group of consecutively chosen months is said to be maximum profitable if the price in its first or last month is the maximum for the group. More formally, a group of consecutive months [l, r] (1 ≤ l < r < n) is said to be maximum profitable if either:
 * <p>
 * stockPrice[l] = max(stockPrice[l], stockPrice[l + 1], ..., stockPrice[r])
 * or, stockPrice[r] = max(stockPrice[l], stockPrice[l + 1], ..., stockPrice[r])
 * Given prices over n consecutive months, find the number of maximum profitable groups which can be formed. Note that the months chosen must be consecutive, i.e., you must choose a subarray of the given array.
 * <p>
 * Example:
 * <p>
 * Consider there are n = 3 months of data, stockPrice = [2, 3, 2]. All possible groups are shown in the leftmost column
 */

/**
 * Complexity Analysis
 * Time Complexity: O(n^3) - The nested loops contribute to a cubic time complexity since for each subarray, we find the maximum element.
 * Space Complexity: O(1) - We only use a fixed number of variables, so the space complexity is constant.
 */
public class StockAnalyzer {
    public static void main(String[] args) {
        int[] stockPrices = {2, 3, 2}; // Example input
        System.out.println("Number of maximum profitable groups: " + countMaxProfitableGroups(stockPrices));
    }

    private static int countMaxProfitableGroups(int[] prices) {
        int n = prices.length;
        int count = 0;

        // Iterate over all possible subarrays
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                // Find the maximum in the current subarray
                int max = findMax(prices, i, j);

                // Check if the subarray is maximum profitable
                if (prices[i] == max || prices[j] == max) {
                    count++;
                }
            }
        }
        return count;
    }

    // Helper method to find the maximum in a subarray
    private static int findMax(int[] prices, int start, int end) {
        int max = prices[start];
        for (int k = start + 1; k <= end; k++) {
            if (prices[k] > max) {
                max = prices[k];
            }
        }
        return max;
    }
}
