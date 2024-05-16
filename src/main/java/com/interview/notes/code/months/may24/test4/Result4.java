package com.interview.notes.code.months.may24.test4;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

//working 9/15
class Result4 {
    public static long findMinimumPrice2(List<Integer> price, int m) {
        int n = price.size();

        // Handle the edge case when there are no items
        if (n == 0) return 0;

        // Using a min-heap to prioritize higher prices for discount application
        PriorityQueue<Long> minHeap = new PriorityQueue<>(Collections.reverseOrder());

        // Initialize the heap with original prices
        for (int i = 0; i < n; i++) {
            minHeap.add((long) price.get(i));
        }

        // Apply m coupons, each time to the current highest price
        for (int i = 0; i < m; i++) {
            if (minHeap.isEmpty()) break; // In case there are more coupons than items
            long highestPrice = minHeap.poll();
            highestPrice = highestPrice / 2; // Apply coupon
            minHeap.add(highestPrice);
        }

        // Sum up all the prices left in the heap
        long totalMinCost = 0;
        while (!minHeap.isEmpty()) {
            totalMinCost += minHeap.poll();
        }

        return totalMinCost;
    }


    public static long findMinimumPrice1(List<Integer> price, int m) {
        int n = price.size();

        // Sort the prices in descending order to apply coupons to the most expensive items first
        Collections.sort(price, Collections.reverseOrder());

        // Total minimal cost after applying all possible discounts
        long totalMinCost = 0;

        // Apply coupons to the most expensive items first
        for (int i = 0; i < n; i++) {
            long currentPrice = price.get(i);
            if (m > 0) {
                // Calculate the number of times we can apply a coupon
                int times = (int) (Math.log(currentPrice) / Math.log(2));
                // Actual coupons to apply is the minimum of remaining coupons or calculated times
                int couponsToApply = Math.min(m, times);
                currentPrice >>= couponsToApply; // Apply the coupons by right-shifting
                m -= couponsToApply; // Decrease the number of available coupons
            }
            totalMinCost += currentPrice; // Add the discounted or original price
        }

        return totalMinCost;
    }

    public static void main(String[] args) {
        // Example 0
        System.out.println("Example 0:");
        long result0 = Result4.findMinimumPrice(Arrays.asList(1, 2, 3), 1);
        System.out.println("Minimum cost: " + result0);  // Expected Output: 4

        // Example 1
        System.out.println("Example 1:");
        long result1 = Result4.findMinimumPrice(Arrays.asList(1, 1, 1), 0);
        System.out.println("Minimum cost: " + result1);  // Expected Output: 3

        // Add additional examples if needed
        // Example 2
        System.out.println("Example 2:");
        long result2 = Result4.findMinimumPrice(Arrays.asList(2, 4), 2);
        System.out.println("Minimum cost: " + result2);  // Expected Output: 3 (from the initial problem description)
    }

    public static long findMinimumPrice(List<Integer> price, int m) {
        int n = price.size();

        // Handle the edge case when there are no items
        if (n == 0) return 0;

        // Priority Queue to prioritize highest prices for coupon application
        PriorityQueue<Long> minHeap = new PriorityQueue<>(Collections.reverseOrder());

        // Initialize the heap with original prices
        for (int i = 0; i < n; i++) {
            if (price.get(i) > 0) {  // Only add non-zero prices
                minHeap.add((long) price.get(i));
            }
        }

        // If no coupons or only zero prices, sum up the original prices directly
        if (m == 0 || minHeap.isEmpty()) {
            long total = 0;
            for (long p : minHeap) {
                total += p;
            }
            return total;
        }

        // Apply m coupons efficiently
        while (m > 0 && !minHeap.isEmpty()) {
            long highestPrice = minHeap.poll();
            int couponsUsed = 0;

            // Calculate maximum number of times we can halve the price
            while (couponsUsed < m && highestPrice > 1) {
                highestPrice /= 2;
                couponsUsed++;
            }

            // Add the reduced price back to the heap
            if (highestPrice > 0) {
                minHeap.add(highestPrice);
            }
            m -= couponsUsed;
        }

        // Sum up all the prices left in the heap
        long totalMinCost = 0;
        while (!minHeap.isEmpty()) {
            totalMinCost += minHeap.poll();
        }

        return totalMinCost;
    }
}
