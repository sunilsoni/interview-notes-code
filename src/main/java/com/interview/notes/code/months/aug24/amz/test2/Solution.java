package com.interview.notes.code.months.aug24.amz.test2;

import java.util.List;

public class Solution {
    public static int getMaxNegativePnL(List<Integer> PnL) {
        int n = PnL.size();
        int[] prefixSum = new int[n];
        prefixSum[0] = PnL.get(0);

        // Compute prefix sums
        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + PnL.get(i);
        }

        int minPrefixSum = prefixSum[0];
        int maxNegatives = 0;

        for (int i = 1; i < n; i++) {
            // Try to convert PnL[i] to negative
            if (prefixSum[i] - 2 * PnL.get(i) > 0 && prefixSum[i] - 2 * PnL.get(i) > minPrefixSum) {
                prefixSum[i] -= 2 * PnL.get(i); // Convert PnL[i] to negative
                maxNegatives++;
            }
            minPrefixSum = Math.min(minPrefixSum, prefixSum[i]);
        }

        return maxNegatives;
    }

    public static void main(String[] args) {
        // Test cases
        List<Integer> PnL1 = List.of(5, 2, 3, 5, 2, 3);
        System.out.println(getMaxNegativePnL(PnL1)); // Expected output: 3

        List<Integer> PnL2 = List.of(1, 1, 1, 1, 1);
        System.out.println(getMaxNegativePnL(PnL2)); // Expected output: 2
    }
}
