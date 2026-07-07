package com.interview.notes.code.year.y2026.june.Amazon.test1;

import java.util.Arrays;                 // Used to create and fill large test arrays
import java.util.stream.IntStream;        // Used for Java 8 Stream API logic

public class StockMaxProfit {            // Main class for the stock profit problem

    static int maxProfit(int[] prices) {  // Method returns maximum profit from given prices

        if (prices == null || prices.length < 2) return 0; // No profit if data is missing or only one price exists

        return IntStream.range(1, prices.length)            // Start from day 1 because we compare with previous day
                .map(i -> Math.max(0, prices[i] - prices[i - 1])) // Take profit only if today's price is higher than yesterday
                .sum();                                     // Add all positive profits to get final max profit
    }

    static void test(String name, int[] prices, int expected) { // Simple test method, no JUnit needed

        int actual = maxProfit(prices);                         // Call our method and store actual result

        String result = actual == expected ? "PASS" : "FAIL";   // Compare actual and expected output

        System.out.println(result + " | " + name                 // Print PASS or FAIL with test name
                + " | Expected: " + expected                     // Print expected value
                + " | Actual: " + actual);                       // Print actual value
    }

    public static void main(String[] args) {                     // Program starts from main method

        test("Given example", new int[]{100, 180, 260, 310, 40, 535, 695}, 865); // Buy day 0 sell day 3, buy day 4 sell day 6

        test("Only increasing", new int[]{1, 2, 3, 4, 5}, 4);     // Buy at 1 and sell at 5, total profit 4

        test("Only decreasing", new int[]{5, 4, 3, 2, 1}, 0);     // No profit possible because price keeps falling

        test("Same prices", new int[]{7, 7, 7, 7}, 0);            // No profit because price never increases

        test("Single day", new int[]{10}, 0);                    // Cannot sell because only one day exists

        test("Empty array", new int[]{}, 0);                     // No prices means no profit

        test("Null array", null, 0);                             // Null input should safely return 0

        test("Mixed small case", new int[]{3, 2, 6, 5, 0, 3}, 7); // Profit: 2->6 = 4 and 0->3 = 3, total 7

        int[] large = new int[1_000_000];                        // Create large input to test performance

        Arrays.setAll(large, i -> i);                            // Fill array like 0,1,2,3... so profit is easy to verify

        test("Large increasing data", large, 999_999);           // Expected profit is last value minus first value
    }
}