package com.interview.notes.code.months.sept24.amazon.test1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReduceGifts {

    public static int reduceGifts(List<Integer> prices, int k, int threshold) {
        // Convert the immutable list to a mutable list
        List<Integer> mutablePrices = new ArrayList<>(prices);

        // Sort the mutable list in ascending order
        Collections.sort(mutablePrices);

        // Start binary search for minimum removals
        int left = 0, right = mutablePrices.size(), minRemovals = mutablePrices.size();

        while (left <= right) {
            int mid = left + (right - left) / 2;
            // Check if removing 'mid' items can make the sum of k items <= threshold
            if (canSatisfyCondition(mutablePrices, k, threshold, mid)) {
                minRemovals = mid;  // Try for fewer removals
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return minRemovals;
    }

    // Helper function to check if we can satisfy the condition with 'removeCount' removals
    private static boolean canSatisfyCondition(List<Integer> prices, int k, int threshold, int removeCount) {
        int n = prices.size();
        // Sliding window technique to check sums of size k after removing 'removeCount' largest elements
        int windowSum = 0;

        // Sum the first 'k' elements after removing 'removeCount' elements
        for (int i = 0; i < k; i++) {
            windowSum += prices.get(i);
        }

        // If the initial window sum is already less than or equal to the threshold, return true
        if (windowSum <= threshold) {
            return true;
        }

        // Use sliding window to check all other subarrays of size 'k'
        for (int i = k; i < n - removeCount; i++) {
            windowSum += prices.get(i) - prices.get(i - k);
            if (windowSum <= threshold) {
                return true;
            }
        }

        // If no valid subarray is found, return false
        return false;
    }

    public static void main(String[] args) {
        // Test Case 1
        List<Integer> prices1 = List.of(9, 6, 3, 2, 9, 10, 10, 11);
        int k1 = 4;
        int threshold1 = 1;
        System.out.println(reduceGifts(prices1, k1, threshold1)); // Output should be 5

        // Test Case 2
        List<Integer> prices2 = List.of(9, 6, 7, 2, 7, 2);
        int k2 = 2;
        int threshold2 = 13;
        System.out.println(reduceGifts(prices2, k2, threshold2)); // Output should be 2

        // Add more test cases to verify correctness and performance
    }
}
