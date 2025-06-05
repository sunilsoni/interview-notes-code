package com.interview.notes.code.year.y2025.may.apple.test3;

import java.util.stream.IntStream;   // For Java 8 IntStream.rangeClosed(...) in main
import java.util.Arrays;             // (Optional) For Arrays.toString(...) if needed for debugging

public class BestTimeMultipleTransactions {

    /**
     * Computes maximum profit with as many transactions as you like,
     * subject to “sell before next buy.” In effect, sum up all positive
     * differences between consecutive days.
     *
     * @param prices array of stock prices by day
     * @return maximum total profit (>= 0)
     */
    public static int maxProfitMultipleTx(int[] prices) {
        // 1) Handle null or empty array → no trades possible → profit = 0
        if (prices == null || prices.length < 2) {
            // If length < 2, even a single transaction (buy + sell) is impossible.
            return 0;
        }

        // 2) Initialize an accumulator for total profit.
        int totalProfit = 0; 
        // We will add (prices[i+1] − prices[i]) whenever that difference is positive.

        // 3) Loop through indices i = 0 to (n − 2). We'll compare prices[i] with prices[i+1].
        for (int i = 0; i < prices.length - 1; i++) {
            int todayPrice = prices[i];        // Price on day i
            int nextDayPrice = prices[i + 1];  // Price on day i+1

            // 3.1) Compute the difference if we “bought at day i” and “sold at day i+1”
            int diff = nextDayPrice - todayPrice;

            // 3.2) If this difference is positive, we seize that profit
            if (diff > 0) {
                totalProfit += diff;
                // This is equivalent to: buy on day i, sell on day i+1
                // Then, on the next iteration, it's as if we've “sold” and are free to “buy again.”
            }
            // If diff <= 0, we skip (no transaction on days i→i+1).
        }

        // 4) Return the sum of all positive “uphill” differences
        return totalProfit;
    }

    /**
     * Main method: runs multiple test cases (including a large-data test)
     * and prints PASS/FAIL. This matches the user’s request for a simple
     * main-method-based test harness, no JUnit.
     */
    public static void main(String[] args) {
        // ---------- SMALL TEST CASES ----------

        // 1) We’ll store each test as { description, pricesArray, expectedProfit }
        Object[][] testCases = new Object[][] {
            {
                "Example from prompt: [7,1,5,3,6,4]",
                new int[] {7, 1, 5, 3, 6, 4},
                7
            },
            {
                "Strictly decreasing: [7, 6, 4, 3, 1]",
                new int[] {7, 6, 4, 3, 1},
                0
            },
            {
                "Strictly increasing: [1, 2, 3, 4, 5]",
                new int[] {1, 2, 3, 4, 5},
                4  // profit = (2−1)+(3−2)+(4−3)+(5−4) = 4
            },
            {
                "All equal: [5, 5, 5, 5]",
                new int[] {5, 5, 5, 5},
                0
            },
            {
                "Single element: [10]",
                new int[] {10},
                0
            },
            {
                "Empty array",
                new int[] {}, 
                0
            },
            {
                "Mixed ups and downs: [3, 2, 6, 5, 0, 3]",
                new int[] {3, 2, 6, 5, 0, 3},
                // Explanation:
                //   - Buy 2→sell 6: gain = 6−2 = 4
                //   - Then next segment: prices drop to 5 then 0, so skip
                //   - Finally, buy 0→sell 3: gain = 3−0 = 3
                //   Total = 4 + 3 = 7
                7
            }
        };

        // 2) Loop through small test cases and check PASS/FAIL
        for (int i = 0; i < testCases.length; i++) {
            String description = (String) testCases[i][0];   // e.g. "Example from prompt: [7,1,5,3,6,4]"
            int[] prices       = (int[]) testCases[i][1];    // the array itself
            int expected       = (int) testCases[i][2];      // the expected integer profit

            // 2.1) Call our method
            int actual = maxProfitMultipleTx(prices);

            // 2.2) Compare and print PASS/FAIL
            if (actual == expected) {
                System.out.println(
                    "Test " + (i + 1) + " (" + description + "): PASS"
                );
            } else {
                System.out.println(
                    "Test " + (i + 1) + " (" + description + "): FAIL - Expected "
                    + expected + ", Got " + actual
                );
            }
        }

        // ---------- LARGE-DATA TEST ----------

        System.out.println();  // blank line for readability
        System.out.println("Running large-data test (n = 1,000,000)...");

        // 3) Generate a large array of size 1,000,000.
        //    For simplicity, we’ll create a strictly alternating pattern:
        //    e.g. [1, 2, 1, 2, 1, 2, ...] for 1_000_000 elements.
        //    In that pattern, every pair (1,2) yields profit = 1, and (2,1) yields 0.
        //    So if n=1,000,000, half of the pairs are (1,2), half (2,1), etc.
        //
        //    Alternatively, we could do a strictly increasing [1..1_000_000], 
        //    but that yields profit 999_999. Both are valid. We choose a repeating
        //    pattern to demonstrate “many small transactions.”
        //
        //    Let's do: prices[i] = (i % 2 == 0) ? 1 : 2, for i=0..999,999.
        //
        //    Expected profit = number_of_pairs_where_next>current = 500,000.
        //
        //    Use IntStream to build this array:
        int n = 1_000_000;
        int[] largePrices = IntStream.range(0, n)
                                     // For each index i, map to 1 or 2
                                     .map(i -> (i % 2 == 0) ? 1 : 2)
                                     .toArray();

        // 4) Because pattern is [1,2,1,2,1,2,…], each (1→2) gives +1 profit.
        //    There are exactly 500,000 such upward transitions in a length‐1,000,000 array.
        int expectedLargeProfit = 500_000;

        // 5) Time the computation
        long startTime = System.currentTimeMillis();
        int actualLargeProfit = maxProfitMultipleTx(largePrices);
        long endTime   = System.currentTimeMillis();

        // 6) Print result + elapsed time
        if (actualLargeProfit == expectedLargeProfit) {
            System.out.println(
                "Large-data test: PASS (profit = " + actualLargeProfit + ")"
            );
        } else {
            System.out.println(
                "Large-data test: FAIL - Expected " + expectedLargeProfit
                + ", Got " + actualLargeProfit
            );
        }
        System.out.println(
            "Time taken for large data: " + (endTime - startTime) + " ms"
        );
    }
}