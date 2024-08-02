package com.interview.notes.code.months.aug24.test5;

import java.util.*;

public class Result {

    public static int reduceGifts(List<Integer> prices, int k, int threshold) {
        // Sort prices in descending order
        Collections.sort(prices, Collections.reverseOrder());

        int removalCount = 0;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        // Calculate initial sums for all possible windows
        for (int i = 0; i <= prices.size() - k; i++) {
            int sum = 0;
            for (int j = 0; j < k; j++) {
                sum += prices.get(i + j);
            }
            maxHeap.add(sum);
        }

        while (!maxHeap.isEmpty() && maxHeap.peek() > threshold) {
            int maxSum = maxHeap.poll();
            int maxIndex = prices.indexOf(maxSum / k);
            if (maxIndex >= 0) {
                prices.remove(maxIndex);
                removalCount++;
            }

            maxHeap.clear();
            for (int i = 0; i <= prices.size() - k; i++) {
                int sum = 0;
                for (int j = 0; j < k; j++) {
                    sum += prices.get(i + j);
                }
                maxHeap.add(sum);
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
