package com.interview.notes.code.year.y2024.sept24.amazon.test2;

import java.util.List;
import java.util.PriorityQueue;

public class ReduceGifts {

    public static int reduceGifts(List<Integer> prices, int k, int threshold) {
        int n = prices.size();
        int[] priceArray = prices.stream().mapToInt(i -> i).toArray();
        boolean[] removed = new boolean[n];
        int removals = 0;

        // Compute the initial sum of the first window
        long windowSum = 0;
        for (int i = 0; i < k; i++) {
            windowSum += priceArray[i];
        }

        // Max-Heap to find the largest gift in the window
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[0] - a[0]);

        // Initialize the heap for the first window
        for (int i = 0; i < k; i++) {
            maxHeap.offer(new int[]{priceArray[i], i});
        }

        int left = 0;
        while (true) {
            // Remove gifts from the window until the sum is <= threshold
            while (windowSum > threshold && !maxHeap.isEmpty()) {
                int[] maxGift = maxHeap.poll();
                if (!removed[maxGift[1]]) {
                    windowSum -= maxGift[0];
                    removed[maxGift[1]] = true;
                    removals++;
                }
            }

            // Slide the window
            if (left + k >= n) {
                break;
            }

            // Remove leftmost element from sum
            if (!removed[left]) {
                windowSum -= priceArray[left];
            }
            left++;

            // Add new element to the window
            int right = left + k - 1;
            if (!removed[right]) {
                windowSum += priceArray[right];
                maxHeap.offer(new int[]{priceArray[right], right});
            }
        }

        return removals;
    }


    public static void main(String[] args) {
        // Test Case 1
        List<Integer> prices1 = List.of(9, 6, 3, 2, 9, 10, 10, 11); // immutable list
        int k1 = 4;
        int threshold1 = 1;
        System.out.println(reduceGifts(prices1, k1, threshold1)); // Output should be 5

        // Test Case 2
        List<Integer> prices2 = List.of(9, 6, 7, 2, 7, 2); // immutable list
        int k2 = 2;
        int threshold2 = 13;
        System.out.println(reduceGifts(prices2, k2, threshold2)); // Output should be 2
    }

    public static void main1(String[] args) {
        // Test Case 1
        List<Integer> prices1 = List.of(9, 6, 3, 2, 9, 10, 10, 11);
        int k1 = 4;
        int threshold1 = 1;
        System.out.println(reduceGifts(prices1, k1, threshold1)); // Output: 5

        // Test Case 2
        List<Integer> prices2 = List.of(9, 6, 7, 2, 7, 2);
        int k2 = 2;
        int threshold2 = 13;
        System.out.println(reduceGifts(prices2, k2, threshold2)); // Output: 2

        // Additional Test Cases
        // Test Case 3
        List<Integer> prices3 = List.of(1, 2, 3, 4, 5);
        int k3 = 3;
        int threshold3 = 6;
        System.out.println(reduceGifts(prices3, k3, threshold3)); // Output: 3

        // Test Case 4
        List<Integer> prices4 = List.of(5, 5, 5, 5, 5);
        int k4 = 2;
        int threshold4 = 8;
        System.out.println(reduceGifts(prices4, k4, threshold4)); // Output: 2

        // Test Case 5
        List<Integer> prices5 = List.of(1, 2, 3, 4, 5);
        int k5 = 6;
        int threshold5 = 10;
        System.out.println(reduceGifts(prices5, k5, threshold5)); // Output: 0 (No removals needed)
    }
}
