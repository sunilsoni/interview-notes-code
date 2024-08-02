package com.interview.notes.code.months.aug24.test7;

import java.util.Arrays;
import java.util.List;

class Result {
    public static int reduceGifts(List<Integer> prices, int k, int threshold) {
        int n = prices.size();
        if (n < k) return 0;

        int[] prefixSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + prices.get(i);
        }

        for (int i = 0; i <= n - k; i++) {
            boolean valid = true;
            for (int j = i; j <= n - k; j++) {
                int windowSum = prefixSum[j + k] - prefixSum[j];
                if (windowSum > threshold) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                return i;
            }
        }

        return n;
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(reduceGifts(Arrays.asList(3, 2, 1, 4, 6, 5), 3, 14)); // Expected: 1
        System.out.println(reduceGifts(Arrays.asList(9, 6, 7, 2, 7, 2), 2, 13)); // Expected: 2
        System.out.println(reduceGifts(Arrays.asList(9, 6, 3, 2, 9, 10, 10, 11, 4, 1), 4, 30)); // Expected: 5
    }
}