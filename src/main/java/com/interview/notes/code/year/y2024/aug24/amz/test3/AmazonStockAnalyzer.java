package com.interview.notes.code.year.y2024.aug24.amz.test3;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class AmazonStockAnalyzer {

    public static void main(String[] args) {
        // Test cases
        System.out.println(getMaxNegativePnL(Arrays.asList(5, 3, 1, 2))); // Expected: 2
        System.out.println(getMaxNegativePnL(Arrays.asList(1, 1, 1, 1, 1))); // Expected: 2
        System.out.println(getMaxNegativePnL(Arrays.asList(5, 2, 3, 5, 2, 3))); // Expected: 3
        System.out.println(getMaxNegativePnL(Arrays.asList(10, 5, 3, 2, 1))); // Expected: 4
        System.out.println(getMaxNegativePnL(Arrays.asList(1, 1, 5, 2, 4, 2, 3, 4, 5, 5))); // Expected: 4

        // Add more test cases here for the large inputs
    }

    public static int getMaxNegativePnL(List<Integer> PnL) {
        int n = PnL.size();
        long cumulativePnL = 0;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        // Calculate the initial cumulative PnL
        for (int i = 0; i < n; i++) {
            cumulativePnL += PnL.get(i);
        }

        // Check the initial validity
        if (cumulativePnL <= 0) {
            return 0; // No valid sequence possible
        }

        // Iterate over the PnL list to maximize negative values
        int maxNegatives = 0;
        cumulativePnL = 0;

        for (int i = 0; i < n; i++) {
            int value = PnL.get(i);
            cumulativePnL += value;

            // If the value is positive and can be negated
            if (value > 0 && cumulativePnL - 2L * value > 0) {
                maxHeap.offer(value);
                cumulativePnL -= 2L * value;
                maxNegatives++;
            }
        }

        return maxNegatives;
    }

    public static int getMaxNegativePnL2(List<Integer> PnL) {
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

    public static int getMaxNegativePnL1(List<Integer> PnL) {
        int n = PnL.size();
        long[] prefixSum = new long[n + 1];
        long totalSum = 0;

        for (int i = 0; i < n; i++) {
            totalSum += PnL.get(i);
            prefixSum[i + 1] = totalSum;
        }

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int maxNegatives = 0;
        long currentSum = 0;

        for (int i = 0; i < n; i++) {
            minHeap.offer(PnL.get(i));
            currentSum += PnL.get(i);

            while (!minHeap.isEmpty() && currentSum - 2L * minHeap.peek() > 0) {
                currentSum -= 2L * minHeap.poll();
                maxNegatives++;
            }

            if (currentSum <= 0) break;
        }

        // Check if we can convert more months at the end
        long remainingSum = totalSum - currentSum;
        while (!minHeap.isEmpty() && remainingSum - 2L * minHeap.peek() > 0) {
            remainingSum -= 2L * minHeap.poll();
            maxNegatives++;
        }

        return maxNegatives;
    }
}
