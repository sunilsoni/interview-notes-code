package com.interview.notes.code.months.aug24.test3;

import java.util.*;

public class Solution {
    public static int reduceGifts(List<Integer> prices, int k, int threshold) {
        if (prices == null || prices.isEmpty() || k <= 0 || threshold < 0) {
            return 0; // Handle invalid inputs
        }

        int n = prices.size();
        if (n < k) {
            return 0; // No need to remove any items if there are fewer items than k
        }

        // Sort prices in descending order
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        maxHeap.addAll(prices);

        int sum = 0;
        int itemsToRemove = 0;
        Queue<Integer> window = new LinkedList<>();

        // Calculate sum of k highest prices
        for (int i = 0; i < k; i++) {
            int price = maxHeap.poll();
            sum += price;
            window.offer(price);
        }

        // Remove items until sum is less than or equal to threshold
        while (sum > threshold && !maxHeap.isEmpty()) {
            int removed = window.poll();
            int added = maxHeap.poll();
            sum = sum - removed + added;
            window.offer(added);
            itemsToRemove++;
        }

        // If we've removed all items and sum is still greater than threshold
        while (sum > threshold && !window.isEmpty()) {
            sum -= window.poll();
            itemsToRemove++;
        }

        return itemsToRemove;
    }

    public static void main(String[] args) {
        // Test case 1
        List<Integer> prices1 = Arrays.asList(3, 2, 1, 4, 6, 5);
        int k1 = 3;
        int threshold1 = 14;
        System.out.println(reduceGifts(prices1, k1, threshold1)); // Expected: 1

        // Test case 2
        List<Integer> prices2 = Arrays.asList(9, 6, 7, 2, 7, 2);
        int k2 = 2;
        int threshold2 = 13;
        System.out.println(reduceGifts(prices2, k2, threshold2)); // Expected: 2

        // Test case 3: Large input
        List<Integer> prices3 = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            prices3.add(i + 1);
        }
        int k3 = 1000;
        int threshold3 = 10000;
        System.out.println(reduceGifts(prices3, k3, threshold3)); // Expected: 99501

        // Test case 4: Edge case - all prices are the same
        List<Integer> prices4 = Arrays.asList(5, 5, 5, 5, 5);
        int k4 = 3;
        int threshold4 = 10;
        System.out.println(reduceGifts(prices4, k4, threshold4)); // Expected: 2

        // Test case 5: Edge case - k equals n
        List<Integer> prices5 = Arrays.asList(1, 2, 3, 4, 5);
        int k5 = 5;
        int threshold5 = 10;
        System.out.println(reduceGifts(prices5, k5, threshold5)); // Expected: 2


        // Test case 5: Edge case - k equals n
        List<Integer> prices6 = Arrays.asList(9, 6, 3, 2, 9, 10, 10, 11);
        int k6 = 4;
        int threshold6 = 1;
        System.out.println(reduceGifts(prices6, k6, threshold6)); // Expected: 5
    }
}
