package com.interview.notes.code.months.aug24.test1;

import java.util.*;

class Result {
    public static int reduceGifts(List<Integer> prices, int k, int threshold) {
        if (prices.size() <= k) {
            return 0;
        }

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        int itemsToRemove = 0;

        // Add all prices to the max heap
        for (int price : prices) {
            maxHeap.offer(price);
        }

        // Calculate the sum of the k highest prices
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += maxHeap.poll();
        }

        // Remove items until the sum is less than or equal to the threshold
        while (sum > threshold && !maxHeap.isEmpty()) {
            int highestRemaining = maxHeap.poll();
            int lowestInSum = maxHeap.peek() != null ? maxHeap.peek() : 0;
            
            sum = sum - lowestInSum + highestRemaining;
            itemsToRemove++;
        }

        // If we've removed all items and sum is still greater than threshold
        if (sum > threshold) {
            itemsToRemove++;
        }

        return itemsToRemove;
    }
}

public class Main {
    public static void main(String[] args) {
        // Test Case 1
        List<Integer> prices1 = Arrays.asList(3, 2, 1, 4, 6, 5);
        int k1 = 3;
        int threshold1 = 14;
        System.out.println("Test Case 1 Result: " + Result.reduceGifts(prices1, k1, threshold1));
        // Expected Output: 1

        // Test Case 2 (Sample Case 0)
        List<Integer> prices2 = Arrays.asList(9, 6, 7, 2, 7, 2);
        int k2 = 2;
        int threshold2 = 13;
        System.out.println("Test Case 2 Result: " + Result.reduceGifts(prices2, k2, threshold2));
        // Expected Output: 2

        // Test Case 3 (Corner case: n < k)
        List<Integer> prices3 = Arrays.asList(1, 2, 3);
        int k3 = 4;
        int threshold3 = 10;
        System.out.println("Test Case 3 Result: " + Result.reduceGifts(prices3, k3, threshold3));
        // Expected Output: 0

        // Test Case 4 (Large input)
        List<Integer> prices4 = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            prices4.add(i + 1);
        }
        int k4 = 1000;
        int threshold4 = 10000;
        System.out.println("Test Case 4 Result: " + Result.reduceGifts(prices4, k4, threshold4));
        // Expected Output: 99501
    }
}
