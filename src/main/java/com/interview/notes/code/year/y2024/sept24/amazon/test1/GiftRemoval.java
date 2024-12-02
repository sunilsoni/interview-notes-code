package com.interview.notes.code.year.y2024.sept24.amazon.test1;

import java.util.Arrays;

public class GiftRemoval {
    public static int reduceGifts(int[] prices, int k, int threshold) {
        int n = prices.length;

        // Sort the array in descending order
        Arrays.sort(prices);
        //Collections.reverse(prices);

        // Calculate the maximum allowed sum for k items
        int maxAllowedSum = k * threshold;

        // Iterate through the array and remove items that contribute to exceeding the threshold
        int removedCount = 0;
        int currentSum = 0;
        for (int i = 0; i < n; i++) {
            currentSum += prices[i];
            if (currentSum > maxAllowedSum) {
                removedCount++;
                currentSum -= prices[i];
            }
        }

        // If the remaining array size is still greater than k, remove the excess items
        if (n - removedCount > k) {
            removedCount += n - removedCount - k;
        }

        return removedCount;
    }

    public static void main(String[] args) {
        int[] prices = {3, 2, 1, 4, 6, 5};
        int k = 3;
        int threshold = 14;
        int result = reduceGifts(prices, k, threshold);
        System.out.println("Minimum number of items to remove: " + result);
    }
}