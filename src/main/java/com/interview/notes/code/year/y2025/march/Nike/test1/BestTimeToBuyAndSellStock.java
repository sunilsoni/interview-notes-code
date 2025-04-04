package com.interview.notes.code.year.y2025.march.Nike.test1;

import java.util.Arrays;

public class BestTimeToBuyAndSellStock {

    // This method calculates the maximum profit using the greedy approach
    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0; // No transactions possible
        }

        int totalProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            // If today's price is higher than yesterday's,
            // we "buy" yesterday and "sell" today to capture the profit.
            if (prices[i] > prices[i - 1]) {
                totalProfit += prices[i] - prices[i - 1];
            }
        }
        return totalProfit;
    }

    // Simple main method to test different scenarios
    public static void main(String[] args) {
        // Define test cases
        int[][] testCases = {
            {7, 1, 5, 3, 6, 4},    // Expected 7
            {1, 2, 3, 4, 5},       // Expected 4
            {7, 6, 4, 3, 1},       // Expected 0
            {},                    // Expected 0 (empty array)
            {5},                   // Expected 0 (single element)
            {2, 1, 2, 0, 1}        // Expected 2 (buy at 1, sell at 2, buy at 0, sell at 1)
        };

        int[] expectedResults = {
            7,
            4,
            0,
            0,
            0,
            2
        };

        // Run each test
        for (int i = 0; i < testCases.length; i++) {
            int result = maxProfit(testCases[i]);
            int expected = expectedResults[i];
            boolean pass = (result == expected);

            System.out.println("Test " + (i + 1)
                    + " | Input: " + Arrays.toString(testCases[i])
                    + " | Expected: " + expected
                    + " | Got: " + result
                    + " | " + (pass ? "PASS" : "FAIL"));
        }

        // Example of testing with a large data input (performance test)
        // Just for demonstration; not printing entire array.
        int[] largeData = new int[100000];
        for (int i = 0; i < largeData.length; i++) {
            // A simple pattern: strictly increasing (worst-case scenario for memory, but easy to validate large data)
            largeData[i] = i; 
        }
        int largeResult = maxProfit(largeData);
        System.out.println("Large data test (size=" + largeData.length + "): profit=" + largeResult);
    }
}
