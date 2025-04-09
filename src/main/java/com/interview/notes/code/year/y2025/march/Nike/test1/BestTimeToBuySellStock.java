package com.interview.notes.code.year.y2025.march.Nike.test1;

import java.util.Random;

public class BestTimeToBuySellStock {

    // Function to calculate maximum profit
    public static int maxProfit(int[] prices) {
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            // If the price increased from the previous day, add the difference to profit.
            if (prices[i] > prices[i - 1]) {
                profit += prices[i] - prices[i - 1];
            }
        }
        return profit;
    }

    // Method to run a test case and print PASS/FAIL
    public static void runTest(String testName, int[] prices, int expectedProfit) {
        int result = maxProfit(prices);
        if (result == expectedProfit) {
            System.out.println(testName + ": PASS");
        } else {
            System.out.println(testName + ": FAIL (Expected " + expectedProfit + " but got " + result + ")");
        }
    }

    public static void main(String[] args) {
        // Provided test case
        runTest("Test Case 1", new int[]{7, 1, 5, 3, 6, 4}, 7);

        // Edge cases:
        runTest("Empty Array", new int[]{}, 0);
        runTest("Single Element", new int[]{5}, 0);
        runTest("Constant Prices", new int[]{3, 3, 3, 3}, 0);
        runTest("Decreasing Prices", new int[]{9, 8, 7, 6}, 0);

        // Additional tests:
        // Test where profit is gained on multiple days
        runTest("Multiple Profitable Transactions", new int[]{1, 2, 3, 4, 5}, 4);

        // Test with random large data input
        int[] largeInput = new int[1000000];
        Random rand = new Random();
        for (int i = 0; i < largeInput.length; i++) {
            // Generating prices between 1 and 1000
            largeInput[i] = 1 + rand.nextInt(1000);
        }
        // We don't know the expected profit here, so just run to test performance.
        long startTime = System.currentTimeMillis();
        int profitLarge = maxProfit(largeInput);
        long endTime = System.currentTimeMillis();
        System.out.println("Large Input Test: Completed in " + (endTime - startTime) + " ms with profit = " + profitLarge);
    }
}
