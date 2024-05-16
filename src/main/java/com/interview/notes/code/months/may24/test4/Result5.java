package com.interview.notes.code.months.may24.test4;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Result5 {
    public static long findMinimumPrice(List<Integer> price, int m) {
        int n = price.size();

        // Sort prices in descending order
        Collections.sort(price, Collections.reverseOrder());

        // We will store the new prices after applying coupons optimally
        long[] discountedPrices = new long[n];

        // Initially, let's store original prices
        for (int i = 0; i < n; i++) {
            discountedPrices[i] = price.get(i);
        }

        // Apply coupons starting from the most expensive item
        for (int i = 0; i < n && m > 0; i++) {
            long currentPrice = discountedPrices[i];
            // Determine the best number of coupons to use for this price
            long bestPrice = currentPrice;
            int bestCouponsUsed = 0;
            for (int j = 1; j <= m; j++) {
                long potentialPrice = currentPrice / (1L << j);
                if (potentialPrice < bestPrice) {
                    bestPrice = potentialPrice;
                    bestCouponsUsed = j;
                }
            }
            // Apply the best found coupon usage
            discountedPrices[i] = bestPrice;
            m -= bestCouponsUsed;
        }

        // Calculate the total minimum cost
        long totalMinCost = 0;
        for (long priceWithDiscount : discountedPrices) {
            totalMinCost += priceWithDiscount;
        }

        return totalMinCost;
    }

    public static void main(String[] args) {
        // Example 0
        System.out.println("Example 0:");
        long result0 = findMinimumPrice(Arrays.asList(1, 2, 3), 1);
        System.out.println("Minimum cost: " + result0);  // Expected Output: 4

        // Example 1
        System.out.println("Example 1:");
        long result1 = findMinimumPrice(Arrays.asList(1, 1, 1), 0);
        System.out.println("Minimum cost: " + result1);  // Expected Output: 3

        // Add additional examples if needed
        // Example 2
        System.out.println("Example 2:");
        long result2 = Result4.findMinimumPrice(Arrays.asList(2, 4), 2);
        System.out.println("Minimum cost: " + result2);  // Expected Output: 3 (from the initial problem description)
    }
}
