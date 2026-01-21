package com.interview.notes.code.year.y2025.december.common.test2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StockTradingDays {

    /**
     * Returns list of indices for buy and sell days
     * List alternates: buy, sell, buy, sell...
     * <p>
     * Strategy: Buy at valleys (local mins), Sell at peaks (local maxs)
     *
     * @param prices - list of daily stock prices
     * @return list of day indices in buy-sell alternating order
     */
    public static List<Integer> getBuySellDays(List<Integer> prices) {

        // Create list to store our buy and sell day indices
        List<Integer> tradingDays = new ArrayList<>();

        // Edge case: need at least 2 days to make any trade
        // Cannot buy and sell on same day, need minimum 2 days
        if (prices == null || prices.size() < 2) {
            return tradingDays; // Return empty list, no trades possible
        }

        // Get total number of days for easier reference
        int totalDays = prices.size();

        // Variable to track current position in the price list
        int currentDay = 0;

        // Keep looping until we've checked all days
        // We need at least 2 days remaining to make a trade
        while (currentDay < totalDays - 1) {

            // STEP 1: Find the next valley (local minimum) to BUY
            // Keep moving forward while price is going DOWN or staying same
            // We want to find the bottom before price starts rising
            while (currentDay < totalDays - 1 &&
                    prices.get(currentDay) >= prices.get(currentDay + 1)) {
                // Price is decreasing or flat, keep looking for bottom
                currentDay = currentDay + 1; // Move to next day
            }

            // Check if we've reached the end without finding a buy opportunity
            // If we're at the last day, no point buying (can't sell later)
            if (currentDay == totalDays - 1) {
                break; // Exit the loop, no more trades possible
            }

            // Found a valley! This is our BUY day
            // Price at currentDay is lower than price at currentDay+1
            int buyDay = currentDay;
            tradingDays.add(buyDay); // Record the buy day index

            // STEP 2: Find the next peak (local maximum) to SELL
            // Keep moving forward while price is going UP
            // We want to find the top before price starts falling
            while (currentDay < totalDays - 1 &&
                    prices.get(currentDay) < prices.get(currentDay + 1)) {
                // Price is still increasing, keep holding
                currentDay = currentDay + 1; // Move to next day
            }

            // Found a peak! This is our SELL day
            // Either price is about to drop, or we reached the end
            int sellDay = currentDay;
            tradingDays.add(sellDay); // Record the sell day index

            // Move to next day to look for another buy opportunity
            currentDay = currentDay + 1;
        }

        // Return the complete list of trading days
        return tradingDays;
    }

    /**
     * Helper method to calculate profit from trading days
     * Used to verify our buy-sell days give correct profit
     */
    public static int calculateProfitFromDays(List<Integer> prices, List<Integer> tradingDays) {

        // Variable to accumulate total profit
        int totalProfit = 0;

        // Loop through trading days in pairs (buy, sell)
        // i is buy index, i+1 is sell index
        for (int i = 0; i < tradingDays.size(); i = i + 2) {

            // Get the buy day index from our list
            int buyDayIndex = tradingDays.get(i);

            // Get the sell day index (next element in list)
            int sellDayIndex = tradingDays.get(i + 1);

            // Get actual prices on those days
            int buyPrice = prices.get(buyDayIndex);
            int sellPrice = prices.get(sellDayIndex);

            // Calculate profit for this transaction
            int profit = sellPrice - buyPrice;

            // Add to total
            totalProfit = totalProfit + profit;
        }

        return totalProfit;
    }

    /**
     * Test helper to run a single test case
     */
    public static void runTest(String testName, List<Integer> prices,
                               List<Integer> expectedDays, int expectedProfit) {

        // Get actual result from our function
        List<Integer> actualDays = getBuySellDays(prices);

        // Calculate profit from the trading days we found
        int actualProfit = 0;
        if (actualDays.size() >= 2) {
            actualProfit = calculateProfitFromDays(prices, actualDays);
        }

        // Check if days match expected
        boolean daysMatch = actualDays.equals(expectedDays);

        // Check if profit matches (this is the key validation)
        boolean profitMatch = (actualProfit == expectedProfit);

        // Determine overall pass/fail
        String status = (daysMatch && profitMatch) ? "PASS" : "FAIL";

        // Print results
        System.out.println("Test: " + testName);
        System.out.println("  Input Prices: " + prices);
        System.out.println("  Expected Days: " + expectedDays);
        System.out.println("  Actual Days:   " + actualDays);
        System.out.println("  Expected Profit: " + expectedProfit);
        System.out.println("  Actual Profit:   " + actualProfit);
        System.out.println("  Result: " + status);

        // Print the actual trades for clarity
        if (actualDays.size() >= 2) {
            System.out.print("  Trades: ");
            for (int i = 0; i < actualDays.size(); i = i + 2) {
                int buyIdx = actualDays.get(i);
                int sellIdx = actualDays.get(i + 1);
                System.out.print("Buy@" + buyIdx + "($" + prices.get(buyIdx) +
                        ")->Sell@" + sellIdx + "($" + prices.get(sellIdx) + ") ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Main method to run all tests
     */
    public static void main(String[] args) {

        System.out.println("=== Stock Buy/Sell Days Tests ===\n");

        // Test 1: Given example
        // [1, 3, 7, 4, 2, 5, 7, 10, 2, 5]
        // Buy@0(1), Sell@2(7): profit 6
        // Buy@4(2), Sell@7(10): profit 8  
        // Buy@8(2), Sell@9(5): profit 3
        // Total: 17
        List<Integer> test1 = Arrays.asList(1, 3, 7, 4, 2, 5, 7, 10, 2, 5);
        List<Integer> expected1 = Arrays.asList(0, 2, 4, 7, 8, 9);
        runTest("Given Example", test1, expected1, 17);

        // Test 2: Continuously increasing
        // Just buy at start, sell at end
        List<Integer> test2 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> expected2 = Arrays.asList(0, 4);
        runTest("Increasing Prices", test2, expected2, 4);

        // Test 3: Continuously decreasing
        // No profitable trades, empty list
        List<Integer> test3 = Arrays.asList(5, 4, 3, 2, 1);
        List<Integer> expected3 = new ArrayList<>();
        runTest("Decreasing Prices", test3, expected3, 0);

        // Test 4: All same prices
        // No profit opportunity
        List<Integer> test4 = Arrays.asList(5, 5, 5, 5);
        List<Integer> expected4 = new ArrayList<>();
        runTest("Same Prices", test4, expected4, 0);

        // Test 5: Simple one trade
        List<Integer> test5 = Arrays.asList(2, 8);
        List<Integer> expected5 = Arrays.asList(0, 1);
        runTest("Simple Two Days", test5, expected5, 6);

        // Test 6: No profit possible
        List<Integer> test6 = Arrays.asList(8, 2);
        List<Integer> expected6 = new ArrayList<>();
        runTest("Two Days No Profit", test6, expected6, 0);

        // Test 7: Alternating pattern
        // [1, 5, 1, 5, 1, 5]
        // Buy@0, Sell@1, Buy@2, Sell@3, Buy@4, Sell@5
        List<Integer> test7 = Arrays.asList(1, 5, 1, 5, 1, 5);
        List<Integer> expected7 = Arrays.asList(0, 1, 2, 3, 4, 5);
        runTest("Alternating Pattern", test7, expected7, 12);

        // Test 8: Empty list
        List<Integer> test8 = new ArrayList<>();
        List<Integer> expected8 = new ArrayList<>();
        runTest("Empty List", test8, expected8, 0);

        // Test 9: Single element
        List<Integer> test9 = List.of(5);
        List<Integer> expected9 = new ArrayList<>();
        runTest("Single Element", test9, expected9, 0);

        // Test 10: V-shape pattern
        // [10, 5, 1, 5, 10]
        // Buy at bottom (index 2), sell at end (index 4)
        List<Integer> test10 = Arrays.asList(10, 5, 1, 5, 10);
        List<Integer> expected10 = Arrays.asList(2, 4);
        runTest("V-Shape Pattern", test10, expected10, 9);

        // Test 11: Mountain shape
        // [1, 5, 10, 5, 1]
        // Buy at start, sell at peak
        List<Integer> test11 = Arrays.asList(1, 5, 10, 5, 1);
        List<Integer> expected11 = Arrays.asList(0, 2);
        runTest("Mountain Shape", test11, expected11, 9);

        // Test 12: Large data test
        System.out.println("Test: Large Data (100,000 elements)");
        List<Integer> largeTest = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            largeTest.add((i % 2 == 0) ? 1 : 10); // Alternating 1, 10
        }

        long startTime = System.currentTimeMillis();
        List<Integer> largeResult = getBuySellDays(largeTest);
        long endTime = System.currentTimeMillis();

        int largeProfit = calculateProfitFromDays(largeTest, largeResult);
        int expectedLargeProfit = 50000 * 9; // 50000 trades of profit 9 each

        System.out.println("  Size: 100,000 elements");
        System.out.println("  Number of trades: " + (largeResult.size() / 2));
        System.out.println("  Expected Profit: " + expectedLargeProfit);
        System.out.println("  Actual Profit: " + largeProfit);
        System.out.println("  Time: " + (endTime - startTime) + " ms");
        System.out.println("  Result: " + (largeProfit == expectedLargeProfit ? "PASS" : "FAIL"));
        System.out.println();

        System.out.println("=== All Tests Completed ===");
    }
}