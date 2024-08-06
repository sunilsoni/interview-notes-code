package com.interview.notes.code.months.aug24.amz.test1;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class AmazonStockAnalyzer {

    public static void main(String[] args) {
        // Test cases
        System.out.println(getMaxNegativePnL(Arrays.asList(5, 3, 1, 2))); // Expected: 2
        System.out.println(getMaxNegativePnL(Arrays.asList(1, 1, 1, 1, 1))); // Expected: 2
        System.out.println(getMaxNegativePnL(Arrays.asList(5, 2, 3, 5, 2, 3))); // Expected: 3

        // My additional test case
        System.out.println(getMaxNegativePnL(Arrays.asList(10, 5, 3, 2, 1))); // Expected: 4


        // Test cases
        System.out.println(getMaxNegativePnL(Arrays.asList(5, 3, 1, 2))); // Expected: 2
        System.out.println(getMaxNegativePnL(Arrays.asList(1, 1, 1, 1, 1))); // Expected: 2
        System.out.println(getMaxNegativePnL(Arrays.asList(5, 2, 3, 5, 2, 3))); // Expected: 3
        System.out.println(getMaxNegativePnL(Arrays.asList(10, 5, 3, 2, 1))); // Expected: 4
        System.out.println(getMaxNegativePnL(Arrays.asList(1, 1, 5, 2, 4, 2, 3, 4, 5, 5))); // New test case
    }

    public static int getMaxNegativePnL(List<Integer> PnL) {
        int n = PnL.size();
        long runningSum = 0;
        int maxNegatives = 0;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            runningSum += PnL.get(i);
            minHeap.offer(PnL.get(i));

            // Try to convert as many months to losses as possible
            while (!minHeap.isEmpty() && runningSum - 2L * minHeap.peek() > 0) {
                runningSum -= 2L * minHeap.poll();
                maxNegatives++;
            }

            // Break if cumulative sum becomes non-positive
            if (runningSum <= 0) {
                break;
            }
        }

        return maxNegatives;
    }
}
