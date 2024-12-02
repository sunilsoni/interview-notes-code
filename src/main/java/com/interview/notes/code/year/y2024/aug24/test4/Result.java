package com.interview.notes.code.year.y2024.aug24.test4;

import java.util.*;

public class Result {

    public static int reduceGifts(List<Integer> prices, int k, int threshold) {
        int n = prices.size();
        int removalCount = 0;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        // Process the list in chunks of k
        for (int start = 0; start < n; start += k) {
            int end = Math.min(start + k, n);
            int windowSum = 0;
            maxHeap.clear();

            // Fill the window
            for (int i = start; i < end; i++) {
                windowSum += prices.get(i);
                maxHeap.offer(prices.get(i));
            }

            // Adjust the window if necessary
            while (windowSum > threshold && !maxHeap.isEmpty()) {
                int maxPrice = maxHeap.poll();
                windowSum -= maxPrice;
                removalCount++;
            }
        }

        return removalCount;
    }

    public static void main(String[] args) {
        // Test case 1
        List<Integer> prices1 = Arrays.asList(9, 6, 3, 2, 9, 10, 10, 11);
        int k1 = 4;
        int threshold1 = 1;
        System.out.println(reduceGifts(new ArrayList<>(prices1), k1, threshold1)); // Output: 5

        // Test case 2
        List<Integer> prices2 = Arrays.asList(9, 6, 7, 2, 7, 2);
        int k2 = 2;
        int threshold2 = 13;
        System.out.println(reduceGifts(new ArrayList<>(prices2), k2, threshold2)); // Output: 2

        // Additional test case
        List<Integer> prices3 = Arrays.asList(3, 2, 1, 4, 6, 5);
        int k3 = 3;
        int threshold3 = 14;
        System.out.println(reduceGifts(new ArrayList<>(prices3), k3, threshold3)); // Output: 1
    }
}
