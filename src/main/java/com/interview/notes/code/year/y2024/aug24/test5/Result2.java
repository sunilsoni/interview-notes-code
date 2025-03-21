package com.interview.notes.code.year.y2024.aug24.test5;

import java.util.*;

public class Result2 {

    public static int reduceGifts(List<Integer> prices, int k, int threshold) {
        // Sort prices in descending order
        Collections.sort(prices, Collections.reverseOrder());

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        int removalCount = 0;

        while (true) {
            boolean valid = true;
            for (int i = 0; i <= prices.size() - k; i++) {
                int sum = 0;
                for (int j = i; j < i + k; j++) {
                    sum += prices.get(j);
                }
                if (sum > threshold) {
                    for (int j = i; j < i + k; j++) {
                        maxHeap.add(prices.get(j));
                    }
                    int maxElement = maxHeap.poll();
                    prices.remove(Integer.valueOf(maxElement));
                    removalCount++;
                    maxHeap.clear();
                    valid = false;
                    break; // Restart the checking process
                }
            }
            if (valid) break;
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
