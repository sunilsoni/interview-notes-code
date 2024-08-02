package com.interview.notes.code.months.aug24.test2;

import java.util.*;

public class Solution1 {
    public static int reduceGifts(List<Integer> prices, int k, int threshold) {
        if (prices.size() < k) {
            return 0; // No need to remove any items if there are fewer items than k
        }

        int n = prices.size();
        int[] freq = new int[n]; // Frequency array to count how often each item needs to be removed
        int maxRemovals = 0; // Tracks the maximum times an item is part of an exceeding sum

        // Calculate sums of all k-length subarrays and count frequencies of each item
        for (int i = 0; i <= n - k; i++) {
            int sum = 0;
            for (int j = i; j < i + k; j++) {
                sum += prices.get(j);
            }
            if (sum > threshold) {
                for (int j = i; j < i + k; j++) {
                    freq[j]++;
                    maxRemovals = Math.max(maxRemovals, freq[j]);
                }
            }
        }

        // If no combination exceeds, no removals needed
        if (maxRemovals == 0) {
            return 0;
        }

        // Count the number of items that need to be removed at least once
        int removalCount = 0;
        for (int i = 0; i < n; i++) {
            if (freq[i] > 0) {
                removalCount++;
            }
        }

        return removalCount;
    }

    public static void main(String[] args) {
        List<Integer> prices1 = Arrays.asList(3, 2, 1, 4, 6, 5);
        int k1 = 3;
        int threshold1 = 14;
        System.out.println(reduceGifts(prices1, k1, threshold1)); // Output: 1

        List<Integer> prices2 = Arrays.asList(9, 6, 7, 2, 7, 2);
        int k2 = 2;
        int threshold2 = 13;
        System.out.println(reduceGifts(prices2, k2, threshold2)); // Output: 2


        // Test case 5: Edge case - k equals n
        List<Integer> prices6 = Arrays.asList(9, 6, 3, 2, 9, 10, 10, 11);
        int k6 = 4;
        int threshold6 = 1;
        System.out.println(reduceGifts(prices6, k6, threshold6)); // Expected: 5 Actual is 8
    }
}
