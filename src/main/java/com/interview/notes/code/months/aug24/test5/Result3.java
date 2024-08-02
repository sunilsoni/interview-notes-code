package com.interview.notes.code.months.aug24.test5;

import java.util.*;

public class Result3 {

    public static int reduceGifts(List<Integer> prices, int k, int threshold) {
        // Sort prices in descending order
        Collections.sort(prices, Collections.reverseOrder());
        
        // Calculate initial sum for the first k elements
        int removalCount = 0;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        while (true) {
            boolean valid = true;
            int currentSum = 0;

            // Calculate the sum for the initial window
            for (int i = 0; i < k; i++) {
                currentSum += prices.get(i);
            }

            // Add initial window elements to the max heap
            for (int i = 0; i < k; i++) {
                maxHeap.add(prices.get(i));
            }

            if (currentSum > threshold) {
                valid = false;
            }

            // Slide the window across the array
            for (int i = k; i < prices.size(); i++) {
                if (!valid) {
                    break;
                }
                currentSum += prices.get(i) - prices.get(i - k);
                maxHeap.add(prices.get(i));
                maxHeap.remove(prices.get(i - k));
                if (currentSum > threshold) {
                    valid = false;
                }
            }

            if (valid) {
                break;
            }

            // Remove the largest element and try again
            prices.remove(maxHeap.poll());
            removalCount++;
            maxHeap.clear();
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
