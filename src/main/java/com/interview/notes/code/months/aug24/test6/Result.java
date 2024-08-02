package com.interview.notes.code.months.aug24.test6;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

class Result {
    public static int reduceGifts(List<Integer> prices, int k, int threshold) {
        int n = prices.size();
        if (n < k) return 0;

        // Use a PriorityQueue as a max heap
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        maxHeap.addAll(prices);

        int count = 0;
        int[] window = new int[k];

        while (true) {
            // Fill the window with k highest prices
            for (int i = 0; i < k; i++) {
                if (!maxHeap.isEmpty()) {
                    window[i] = maxHeap.poll();
                } else {
                    window[i] = 0;
                }
            }

            int windowSum = Arrays.stream(window).sum();

            if (windowSum <= threshold) {
                break;
            }

            // Remove the highest price
            count++;

            // Add back k-1 elements to the heap
            for (int i = 1; i < k; i++) {
                maxHeap.offer(window[i]);
            }
        }

        return count;
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(reduceGifts(Arrays.asList(3, 2, 1, 4, 6, 5), 3, 14)); // Expected: 1
        System.out.println(reduceGifts(Arrays.asList(9, 6, 7, 2, 7, 2), 2, 13)); // Expected: 2
    }
}