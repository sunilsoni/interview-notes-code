package com.interview.notes.code.months.aug24.amz.test6;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
    public static int getMaxNegativePnL(List<Integer> PnL) {
        int n = PnL.size();
        long totalSum = 0;
        for (int value : PnL) {
            totalSum += value;
        }

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        long currentSum = 0;
        int negativeCount = 0;

        for (int value : PnL) {
            currentSum += value;
            minHeap.offer(value);

            while (!minHeap.isEmpty() && currentSum > totalSum / 2) {
                int smallest = minHeap.poll();
                currentSum -= 2 * smallest;
                negativeCount++;
            }

            if (currentSum <= 0) {
                negativeCount--;
                break;
            }
        }

        return negativeCount;
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(getMaxNegativePnL(Arrays.asList(5, 3, 1, 2))); // Expected: 2
        System.out.println(getMaxNegativePnL(Arrays.asList(1, 1, 1, 1, 1))); // Expected: 2
        System.out.println(getMaxNegativePnL(Arrays.asList(5, 2, 3, 5, 2, 3))); // Expected: 3

        // Additional test cases
        System.out.println(getMaxNegativePnL(Arrays.asList(1, 2, 3, 4, 5))); // Expected: 2
        System.out.println(getMaxNegativePnL(Arrays.asList(10, 1, 1, 1, 1))); // Expected: 4
        System.out.println(getMaxNegativePnL(Arrays.asList(1, 1, 1, 1, 10))); // Expected: 4
    }
}
