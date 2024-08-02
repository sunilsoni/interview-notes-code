package com.interview.notes.code.months.aug24.test6;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

class Result2 {
    public static int reduceGifts(List<Integer> prices, int k, int threshold) {
        if (prices.size() <= k) {
            return 0;
        }

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        int sum = 0;
        int itemsToRemove = 0;

        // Add first k elements to the heap and calculate initial sum
        for (int i = 0; i < k; i++) {
            maxHeap.offer(prices.get(i));
            sum += prices.get(i);
        }

        // Check if initial sum exceeds threshold
        while (sum > threshold && !maxHeap.isEmpty()) {
            int removed = maxHeap.poll();
            sum -= removed;
            itemsToRemove++;
        }

        // Process remaining elements
        for (int i = k; i < prices.size(); i++) {
            int currentPrice = prices.get(i);
            maxHeap.offer(currentPrice);
            sum += currentPrice;

            // Remove the smallest element from the previous window
            sum -= prices.get(i - k);
            maxHeap.remove(prices.get(i - k));

            while (sum > threshold && !maxHeap.isEmpty()) {
                int removed = maxHeap.poll();
                sum -= removed;
                itemsToRemove++;
            }
        }

        return itemsToRemove;
    }

    public static void main(String[] args) {
        // Test Case 1 (Given example)
        List<Integer> prices1 = Arrays.asList(3, 2, 1, 4, 6, 5);
        int k1 = 3;
        int threshold1 = 14;
        System.out.println("Test Case 1 Result: " + reduceGifts(prices1, k1, threshold1)); // Expected: 1

        // Test Case 2 (Sample Case 0)
        List<Integer> prices2 = Arrays.asList(9, 6, 7, 2, 7, 2);
        int k2 = 2;
        int threshold2 = 13;
        System.out.println("Test Case 2 Result: " + reduceGifts(prices2, k2, threshold2)); // Expected: 2

        // Additional test cases...
    }
}