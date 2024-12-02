package com.interview.notes.code.year.y2024.aug24.test5;

import java.util.*;

public class Result1 {

    public static int reduceGifts(List<Integer> prices, int k, int threshold) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        int removalCount = 0;
        int windowSum = 0;

        // Initialize the first window
        for (int i = 0; i < k; i++) {
            maxHeap.offer(prices.get(i));
            windowSum += prices.get(i);
        }

        // Process the first window
        while (windowSum > threshold) {
            int maxPrice = maxHeap.poll();
            windowSum -= maxPrice;
            removalCount++;
        }

        // Process the rest of the list
        for (int i = k; i < prices.size(); i++) {
            // Remove the first element of the previous window
            int removedPrice = prices.get(i - k);
            windowSum -= removedPrice;
            maxHeap.remove(removedPrice);

            // Add the new element
            maxHeap.offer(prices.get(i));
            windowSum += prices.get(i);

            // Adjust the window if necessary
            while (windowSum > threshold) {
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
