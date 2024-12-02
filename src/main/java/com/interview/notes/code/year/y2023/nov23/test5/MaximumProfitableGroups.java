package com.interview.notes.code.year.y2023.nov23.test5;


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
public class MaximumProfitableGroups {
    public static int countMaximumProfitGroups(int[] stockPrice) {
        int n = stockPrice.length;
        int max_profit_groups = 0;

        for (int i = 1; i <= n - 2; i++) {
            int leftMax = getMax(stockPrice, 0, i);
            int rightMax = getMax(stockPrice, i, n - 1);

            if (stockPrice[i] >= leftMax || stockPrice[i] >= rightMax) {
                max_profit_groups++;
            }
        }

        return max_profit_groups;
    }

    public static int getMax(int[] arr, int start, int end) {
        int max = arr[start];
        for (int i = start + 1; i <= end; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] stockPrice = {2, 3, 2};
        int result = countMaximumProfitGroups(stockPrice);
        System.out.println("Number of maximum profitable groups: " + result);
    }
}
