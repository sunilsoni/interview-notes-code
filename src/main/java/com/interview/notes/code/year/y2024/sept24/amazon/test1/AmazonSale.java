package com.interview.notes.code.year.y2024.sept24.amazon.test1;

import java.util.Arrays;

public class AmazonSale {

    public static int reduceGifts(int[] prices, int k, int threshold) {
        int n = prices.length;
        if (n < k) return 0; // No need to remove any items if there are less than k items

        Arrays.sort(prices);

        // Calculate the sum of the first k elements
        int currentSum = 0;
        for (int i = 0; i < k; i++) {
            currentSum += prices[i];
        }

        int minRemovals = 0;
        if (currentSum > threshold) {
            // Calculate the number of items to remove
            minRemovals = n - k;
        } else {
            // Use a sliding window to find any other k consecutive items exceeding the threshold
            for (int i = k; i < n; i++) {
                currentSum = currentSum - prices[i - k] + prices[i];
                if (currentSum > threshold) {
                    minRemovals = n - i - 1;
                    break;
                }
            }
        }

        return minRemovals;
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
