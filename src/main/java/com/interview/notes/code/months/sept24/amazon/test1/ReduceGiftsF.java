package com.interview.notes.code.months.sept24.amazon.test1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReduceGiftsF {

    public static int reduceGifts(List<Integer> prices, int k, int threshold) {
        // Convert the immutable list to a mutable list
        List<Integer> mutablePrices = new ArrayList<>(prices);

        // Sort the mutable list in ascending order
        Collections.sort(mutablePrices);

        // We will remove items from the end of the sorted list (expensive items).
        int removeCount = 0;
        int n = mutablePrices.size();

        // Continue removing items until no k-sized subset exceeds the threshold.
        while (true) {
            boolean valid = true;

            // Check all subarrays of size k
            for (int i = 0; i <= n - k; i++) {
                int sum = 0;
                // Calculate the sum of k consecutive items
                for (int j = 0; j < k; j++) {
                    sum += mutablePrices.get(i + j);
                }

                if (sum > threshold) {
                    valid = false;
                    break;
                }
            }

            // If all subsets are valid, return the number of removed items
            if (valid) {
                return removeCount;
            }

            // If not valid, remove the largest item (since the list is sorted, it's at the end)
            mutablePrices.remove(n - 1);
            removeCount++;
            n--; // Reduce the list size by 1
        }
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
}
