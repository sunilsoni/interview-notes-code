package com.interview.notes.code.months.aug24.amz.test4;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class AmazonStockAnalyzer {

    public static int getMaxNegativePnL(List<Integer> PnL) {
        int n = PnL.size();
        long totalSum = 0;
        long runningSum = 0;
        int maxNegatives = 0;
        PriorityQueue<Long> minHeap = new PriorityQueue<>();

        // Calculate total sum and add all elements to the minHeap
        for (int pnl : PnL) {
            totalSum += pnl;
            minHeap.offer((long) pnl);
        }

        while (!minHeap.isEmpty() && totalSum > 0) {
            long smallest = minHeap.poll();
            if (totalSum - 2 * smallest > 0) {
                totalSum -= 2 * smallest;
                maxNegatives++;
            } else {
                break;
            }
        }

        return maxNegatives;
    }

    public static int getMaxNegativePnL3(List<Integer> PnL) {
        int n = PnL.size();
        long totalSum = 0;
        PriorityQueue<Long> minHeap = new PriorityQueue<>();

        // Calculate total sum and add all elements to the minHeap
        for (int pnl : PnL) {
            totalSum += pnl;
            minHeap.offer((long) pnl);
        }

        int maxNegatives = 0;
        long convertedSum = 0;

        while (!minHeap.isEmpty()) {
            long smallest = minHeap.poll();
            if (convertedSum + smallest < totalSum - convertedSum - smallest) {
                convertedSum += smallest;
                maxNegatives++;
            } else {
                break;
            }
        }

        return maxNegatives;
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(getMaxNegativePnL(Arrays.asList(5, 3, 1, 2))); // Expected: 2
        System.out.println(getMaxNegativePnL(Arrays.asList(1, 1, 1, 1, 1))); // Expected: 2
        System.out.println(getMaxNegativePnL(Arrays.asList(5, 2, 3, 5, 2, 3))); // Expected: 3
        System.out.println(getMaxNegativePnL(Arrays.asList(10, 5, 3, 2, 1))); // Expected: 4
        System.out.println(getMaxNegativePnL(Arrays.asList(1, 1, 5, 2, 4, 2, 3, 4, 5, 5))); // Expected: 4
    }

    public long getMaxNegativePnL(int n, int[] stocks) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

        int cumSum = stocks[0];

        for (int i = 1; i < stocks.length; i++) {
            if (stocks[i] < cumSum) {
                maxHeap.add(stocks[i]);
                cumSum -= stocks[i];
            } else {
                if (!maxHeap.isEmpty() && stocks[i] < maxHeap.peek()) {
                    int num = maxHeap.poll();
                    cumSum += 2 * num;
                    maxHeap.add(stocks[i]);
                    cumSum -= stocks[i];
                } else cumSum += stocks[i];

            }

        }

        return maxHeap.size();

    }
}
