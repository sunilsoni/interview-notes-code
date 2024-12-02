package com.interview.notes.code.year.y2024.aug24.test4;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution {
    public static int reduceGifts(List<Integer> prices, int k, int threshold) {
        if (prices == null || prices.isEmpty() || k <= 0 || threshold < 0) {
            return 0; // Handle invalid inputs
        }

        int n = prices.size();
        if (n < k) {
            return 0; // No need to remove any items if there are fewer items than k
        }

        // Sort prices in descending order
        Collections.sort(prices, Collections.reverseOrder());

        int itemsToRemove = 0;
        long sum = 0; // Use long to prevent integer overflow

        for (int i = 0; i < k; i++) {
            sum += prices.get(i);
        }

        while (sum > (long) threshold * k && k > 0) {
            itemsToRemove++;
            if (k == n) {
                sum -= prices.get(k - 1);
                k--;
                n--;
            } else {
                sum = sum - prices.get(k - 1) + (k < n ? prices.get(k) : 0);
                k--;
            }
        }

        // If we've removed all items and still can't satisfy the condition
        if (k == 0 && sum > 0) {
            return prices.size();
        }

        return itemsToRemove;
    }

    public static void main(String[] args) {
        // Test cases
        List<Integer> prices1 = Arrays.asList(3, 2, 1, 4, 6, 5);
        System.out.println(reduceGifts(prices1, 3, 14)); // Expected: 1

        List<Integer> prices2 = Arrays.asList(9, 6, 7, 2, 7, 2);
        System.out.println(reduceGifts(prices2, 2, 13)); // Expected: 2

        List<Integer> prices3 = Arrays.asList(9, 6, 3, 2, 9, 10, 10, 11);
        System.out.println(reduceGifts(prices3, 4, 1)); // Expected: 5

        List<Integer> prices4 = Arrays.asList(5, 5, 5, 5, 5);
        System.out.println(reduceGifts(prices4, 3, 10)); // Expected: 2

        List<Integer> prices5 = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println(reduceGifts(prices5, 5, 10)); // Expected: 2

        List<Integer> prices6 = Arrays.asList(10, 10, 10);
        System.out.println(reduceGifts(prices6, 2, 1)); // Expected: 3
    }
}
