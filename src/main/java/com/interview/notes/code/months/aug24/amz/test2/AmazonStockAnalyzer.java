package com.interview.notes.code.months.aug24.amz.test2;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class AmazonStockAnalyzer {

    public static void main(String[] args) {
        // Test cases
        System.out.println(getMaxNegativePnL(Arrays.asList(5, 3, 1, 2))); // Expected: 2
        System.out.println(getMaxNegativePnL(Arrays.asList(1, 1, 1, 1, 1))); // Expected: 2
        System.out.println(getMaxNegativePnL(Arrays.asList(5, 2, 3, 5, 2, 3))); // Expected: 3
        System.out.println(getMaxNegativePnL(Arrays.asList(10, 5, 3, 2, 1))); // Expected: 4
        System.out.println(getMaxNegativePnL(Arrays.asList(1, 1, 5, 2, 4, 2, 3, 4, 5, 5))); // New test case

        // Add more test cases here...
    }

    public static int getMaxNegativePnL(List<Integer> PnL) {
        int n = PnL.size();
        long totalSum = 0;
        for (int val : PnL) {
            totalSum += val;
        }

        long runningSum = 0;
        int maxNegatives = 0;
        PriorityQueue<Long> minHeap = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            runningSum += PnL.get(i);
            minHeap.offer((long) PnL.get(i));

            while (!minHeap.isEmpty() && runningSum - 2 * minHeap.peek() > 0) {
                runningSum -= 2 * minHeap.poll();
                maxNegatives++;
            }

            if (runningSum <= 0) {
                break;
            }
        }

        // Check if we can convert more months to negative
        while (!minHeap.isEmpty() && totalSum - 2 * minHeap.peek() > 0) {
            totalSum -= 2 * minHeap.poll();
            maxNegatives++;
        }

        return maxNegatives;
    }
}
