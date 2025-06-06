package com.interview.notes.code.year.y2025.may.apple.test1;

import java.util.stream.IntStream;

public class BestTimeToBuySell {

    /**
     * Computes the maximum profit from at most one buy and one sell.
     * If no positive profit is possible, returns 0.
     */
    public static int maxProfit(int[] prices) {
        // Handle null or empty input: no transaction possible
        if (prices == null || prices.length == 0) {
            return 0;                                // Early return when there's no data
        }

        // Initialize minPriceSoFar with a large value (first day’s price will immediately replace it)
        int minPriceSoFar = Integer.MAX_VALUE;       // Keeps track of the lowest price seen up to the current index
        int maxProfitSoFar = 0;                       // Keeps track of the best profit found so far

        // Loop through each day's price
        for (int price : prices) {
            // Update minPriceSoFar if current price is lower
            if (price < minPriceSoFar) {
                minPriceSoFar = price;               // We found a new lower price to potentially buy at
            }

            // Calculate potential profit if we sold today (price - minPriceSoFar)
            int potentialProfit = price - minPriceSoFar; // Difference between today's price and the lowest buy price so far

            // Update maxProfitSoFar if selling today yields more profit than ever before
            if (potentialProfit > maxProfitSoFar) {
                maxProfitSoFar = potentialProfit;     // Record the new maximum profit
            }
        }

        // Return the maximum profit (zero if no positive profit was ever found)
        return maxProfitSoFar;
    }

    /**
     * Main method: runs multiple test cases (including large data) and prints PASS/FAIL.
     */
    public static void main(String[] args) {
        // Define a 2D array of test cases: each entry is {testCaseIndex, expectedProfit}
        // We'll keep prices separately in a parallel array of int[] or generate them on the fly.
        Object[][] testCases = new Object[][]{
                // Format: { "Description", pricesArray, expectedMaxProfit }
                {"Example 1: Increasing then decreasing", new int[]{7, 1, 5, 3, 6, 4}, 5},
                {"Example 2: Strictly decreasing", new int[]{7, 6, 4, 3, 1}, 0},
                {"Edge 1: Single element", new int[]{10}, 0},
                {"Edge 2: Empty array", new int[]{}, 0},
                {"Edge 3: All equal prices", new int[]{5, 5, 5, 5, 5}, 0},
                {"Edge 4: Profit at the end", new int[]{2, 1, 2, 0, 1, 5}, 5},
                {"Edge 5: Profit at the very last day", new int[]{5, 4, 3, 2, 1, 10}, 9}
        };

        // Loop through all the small test cases
        for (int i = 0; i < testCases.length; i++) {
            String description = (String) testCases[i][0];   // Human-readable description
            int[] prices = (int[]) testCases[i][1];    // The prices array
            int expected = (int) testCases[i][2];      // The expected result

            int actual = maxProfit(prices);                  // Compute actual profit
            // Print PASS or FAIL
            if (actual == expected) {
                System.out.println("Test " + (i + 1) + " (" + description + "): PASS");
            } else {
                System.out.println("Test " + (i + 1) + " (" + description + "): FAIL - Expected "
                        + expected + ", Got " + actual);
            }
        }

        // ------------------------------------------------------------
        // Now test with a large data input (size = 1,000,000) using IntStream
        // We'll generate a strictly increasing array [1, 2, 3, ..., 1000000].
        // In that scenario, the best buy is at price=1 on day 1, and best sell is at price=1000000 on day 1,000,000.
        // So expected profit = 1000000 - 1 = 999999.
        // ------------------------------------------------------------
        System.out.println();
        System.out.println("Running large-data test (n = 1,000,000)...");

        // Generate int[] of size 1,000,000 with values [1, 2, 3, ..., 1_000_000]
        int[] largePrices = IntStream.rangeClosed(1, 1_000_000).toArray();  // Java 8 Stream API usage

        // Expected profit for a strictly increasing sequence
        int expectedLargeProfit = 1_000_000 - 1;  // Should be 999,999

        long startTime = System.currentTimeMillis();   // Start timing
        int actualLargeProfit = maxProfit(largePrices);
        long endTime = System.currentTimeMillis();   // End timing

        // Print result and time taken
        if (actualLargeProfit == expectedLargeProfit) {
            System.out.println("Large-data test: PASS (profit = " + actualLargeProfit + ")");
        } else {
            System.out.println("Large-data test: FAIL - Expected " + expectedLargeProfit
                    + ", Got " + actualLargeProfit);
        }
        System.out.println("Time taken for large data: " + (endTime - startTime) + " ms");
    }
}