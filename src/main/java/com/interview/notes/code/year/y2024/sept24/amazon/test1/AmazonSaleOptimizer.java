package com.interview.notes.code.year.y2024.sept24.amazon.test1;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class AmazonSaleOptimizer {
    public static void main(String[] args) {
        // Test cases
        runTestCase(new int[]{3, 2, 1, 4, 6, 5}, 3, 14, 1);
        runTestCase(new int[]{9, 6, 7, 2, 7, 2}, 2, 13, 2);
        runTestCase(new int[]{9, 6, 3, 2, 9, 10, 10, 11}, 4, 1, 5);
    }

    public static int reduceGifts(int[] prices, int k, int threshold) {
        if (prices.length <= k) {
            return 0;
        }

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        int currentSum = 0;
        int itemsToRemove = 0;

        // Process the first k elements
        for (int i = 0; i < k; i++) {
            maxHeap.offer(prices[i]);
            currentSum += prices[i];
        }

        // Check if we need to remove items from the first k elements
        while (currentSum > threshold && !maxHeap.isEmpty()) {
            currentSum -= maxHeap.poll();
            itemsToRemove++;
        }

        // Process the remaining elements
        for (int i = k; i < prices.length; i++) {
            if (prices[i] < maxHeap.peek()) {
                currentSum = currentSum - maxHeap.poll() + prices[i];
                maxHeap.offer(prices[i]);

                while (currentSum > threshold && !maxHeap.isEmpty()) {
                    currentSum -= maxHeap.poll();
                    itemsToRemove++;
                }
            } else {
                itemsToRemove++;
            }
        }

        return itemsToRemove;
    }

    private static void runTestCase(int[] prices, int k, int threshold, int expectedResult) {
        int result = reduceGifts(prices, k, threshold);
        System.out.println("Test Case: " + Arrays.toString(prices) + ", k=" + k + ", threshold=" + threshold);
        System.out.println("Expected: " + expectedResult + ", Got: " + result);
        System.out.println(result == expectedResult ? "PASS" : "FAIL");
        System.out.println();
    }
}