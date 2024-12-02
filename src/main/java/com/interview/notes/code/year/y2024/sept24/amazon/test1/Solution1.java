package com.interview.notes.code.year.y2024.sept24.amazon.test1;

import java.util.Arrays;

class Solution1 {

    public static int reduceGifts(int[] prices, int k, int threshold) {
        Arrays.sort(prices);
        int n = prices.length;
        int l = 0, r = n - 1;
        int minToRemove = n;
        while (l < r) {
            int sum = prices[l] + prices[r];
            if (sum <= threshold) {
                l++;
            } else {
                minToRemove = Math.min(minToRemove, n - 1 - r);
                r--;
            }
        }
        return minToRemove;
    }

    public static void main(String[] args) {
        // Test Case 1
        int[] prices1 = {9, 6, 3, 2, 9, 10, 10, 11};
        int k1 = 4;
        int threshold1 = 1;
        System.out.println(reduceGifts(prices1, k1, threshold1)); // Output should be 5

        // Test Case 2
        int[] prices2 = {9, 6, 7, 2, 7, 2};
        int k2 = 2;
        int threshold2 = 13;
        System.out.println(reduceGifts(prices2, k2, threshold2)); // Output should be 2

        // Additional tests as needed...
    }
}