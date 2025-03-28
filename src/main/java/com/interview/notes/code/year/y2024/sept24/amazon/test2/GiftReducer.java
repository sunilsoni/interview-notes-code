package com.interview.notes.code.year.y2024.sept24.amazon.test2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GiftReducer {

    public static int reduceGifts(List<Integer> prices, int k, int threshold) {
        // Convert the prices list into a mutable ArrayList
        List<Integer> mutablePrices = new ArrayList<>(prices);

        // Sort prices in descending order
        Collections.sort(mutablePrices, Collections.reverseOrder());

        // If there are less than k items, no need to remove anything
        if (mutablePrices.size() < k) {
            return 0;
        }

        // Initialize count of items to remove
        int itemsToRemove = 0;

        // Check sums of every k items, starting with the largest
        while (true) {
            boolean valid = true;
            for (int i = 0; i <= mutablePrices.size() - k; i++) {
                int currentSum = 0;
                // Calculate the sum of current k items
                for (int j = 0; j < k; j++) {
                    currentSum += mutablePrices.get(i + j);
                }
                // If the sum exceeds the threshold, remove the largest item
                if (currentSum > threshold) {
                    mutablePrices.remove(i);  // Remove the item
                    itemsToRemove++;
                    valid = false;
                    break;  // Restart the checking after removing the item
                }
            }
            // If no more sums exceed the threshold, we are done
            if (valid) {
                break;
            }
        }

        return itemsToRemove;
    }

    public static void main(String[] args) {
        // Example test case 1
        List<Integer> prices1 = Arrays.asList(9, 6, 7, 2, 7, 2);
        int k1 = 2;
        int threshold1 = 13;
        System.out.println(reduceGifts(prices1, k1, threshold1));  // Expected output: 2

        // Example test case 2
        List<Integer> prices2 = Arrays.asList(9, 6, 3, 2, 9, 10, 10, 11);
        int k2 = 4;
        int threshold2 = 1;
        System.out.println(reduceGifts(prices2, k2, threshold2));  // Expected output: 5
    }
}
