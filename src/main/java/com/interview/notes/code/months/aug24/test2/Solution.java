package com.interview.notes.code.months.aug24.test2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution {
    public static int reduceGifts(List<Integer> prices, int k, int threshold) {
        int n = prices.size();
        if (n <= k) {
            // Check if the sum of all elements is already below the threshold
            int totalSum = prices.stream().mapToInt(Integer::intValue).sum();
            if (totalSum <= threshold) return 0; // No removal needed

            // If the sum is above the threshold, remove items to minimize the sum
            Collections.sort(prices, Collections.reverseOrder()); // Sort descending to remove the largest first
            int currentSum = totalSum;
            int removedCount = 0;
            for (int price : prices) {
                currentSum -= price;
                removedCount++;
                if (currentSum <= threshold) break;
            }
            return removedCount;
        }

        // Same logic as before for cases where k < n
        int[] freq = new int[n];
        int maxRemovals = 0;

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

        if (maxRemovals == 0) return 0;

        int removalCount = 0;
        for (int i = 0; i < n; i++) {
            if (freq[i] > 0) {
                removalCount++;
            }
        }

        return removalCount;
    }

    public static void main(String[] args) {
        List<Integer> prices6 = Arrays.asList(9, 6, 3, 2, 9, 10, 10, 11);
        int k6 = 4;
        int threshold6 = 1;
        System.out.println(reduceGifts(prices6, k6, threshold6)); // Adjusted approach
    }
}
