package com.interview.notes.code.year.y2024.sept24.amazon.test1;

import java.util.Arrays;

public class ReduceGifts1 {

    public static int reduceGifts(int[] prices, int k, int threshold) {
        // Sort the array in descending order to make it easier to remove expensive items.
        Arrays.sort(prices);

        // We will remove items from the end of the sorted list (expensive items).
        int removeCount = 0;
        int n = prices.length;

        // Continue removing items until no k-sized subset exceeds the threshold.
        while (true) {
            boolean valid = true;
            for (int i = 0; i <= n - k; i++) {
                int sum = 0;
                // Calculate the sum of k consecutive items
                for (int j = 0; j < k; j++) {
                    sum += prices[i + j];
                }
                if (sum > threshold) {
                    valid = false;
                    break;
                }
            }
            // If all subsets are valid, return the number of removed items
            if (valid) {
                return removeCount;
            }
            // If not valid, remove the largest item (since the array is sorted, it's at the end)
            removeCount++;
            n--; // Reduce the array size by 1 (removing the last item)
        }
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
