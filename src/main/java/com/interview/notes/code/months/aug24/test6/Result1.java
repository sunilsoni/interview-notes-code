package com.interview.notes.code.months.aug24.test6;

import java.util.*;

public class Result1 {

    public static int reduceGifts(List<Integer> prices, int k, int threshold) {
        // Sort prices in descending order
        Collections.sort(prices, Collections.reverseOrder());
        
        // Initialize the removal count
        int removalCount = 0;
        
        // Use a max heap to keep track of the k largest sums
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        
        // Iterate through the prices to ensure the sum of any k elements is below the threshold
        while (true) {
            boolean valid = true;
            for (int i = 0; i <= prices.size() - k; i++) {
                int sum = 0;
                for (int j = i; j < i + k; j++) {
                    sum += prices.get(j);
                }
                if (sum > threshold) {
                    prices.remove(i);
                    removalCount++;
                    valid = false;
                    break;
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

        // Edge case test
        List<Integer> prices4 = Arrays.asList(
            265377388, 260560467, 252266962, 242782067, 240026098, 227589934, 225190782, 225117868, 
            222702835, 219142305, 186547219, 171041123, 152119705, 135950882, 116687166, 116254037, 
            105891993, 101812527, 95553209, 82737520, 79506507, 71401319, 54610315, 51225118, 
            39362109, 34969318, 31776522, 31085917, 16647284, 3786628);
        int k4 = 30;
        int threshold4 = 8765;
        System.out.println(reduceGifts(new ArrayList<>(prices4), k4, threshold4)); // Output should be the number of removals needed
    }
}
