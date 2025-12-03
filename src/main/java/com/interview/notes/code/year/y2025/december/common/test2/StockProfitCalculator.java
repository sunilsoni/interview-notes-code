package com.interview.notes.code.year.y2025.december.common.test2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class StockProfitCalculator {
    
    /**
     * Calculates maximum profit from stock trading
     * Strategy: Capture every upward price movement (greedy approach)
     * 
     * @param prices - list of daily stock prices
     * @return maximum possible profit
     */
    public static int calculateMaxProfit(List<Integer> prices) {
        
        // Edge case: if list is null or has less than 2 elements, no trade possible
        // We need at least 2 days to make a buy and sell transaction
        if (prices == null || prices.size() < 2) {
            return 0; // Return zero profit since no valid trade can happen
        }
        
        // Use Java 8 Stream to calculate profit in one pass
        // We look at each pair of consecutive days and sum positive differences
        int maxProfit = IntStream.range(1, prices.size()) // Create stream from index 1 to end
                .map(i -> prices.get(i) - prices.get(i - 1)) // Calculate price difference with previous day
                .filter(diff -> diff > 0) // Only keep positive differences (profitable moves)
                .sum(); // Add up all the profits
        
        // Return the total accumulated profit from all upward movements
        return maxProfit;
    }
    
    /**
     * Alternative implementation with explicit loop for clarity
     * This shows the logic more clearly step by step
     */
    public static int calculateMaxProfitSimple(List<Integer> prices) {
        
        // Handle edge case where trading is not possible
        if (prices == null || prices.size() < 2) {
            return 0; // No profit possible with less than 2 days
        }
        
        // Variable to accumulate total profit across all transactions
        int totalProfit = 0;
        
        // Loop through each day starting from day 2 (index 1)
        for (int day = 1; day < prices.size(); day++) {
            
            // Get today's price from the list
            int todayPrice = prices.get(day);
            
            // Get yesterday's price for comparison
            int yesterdayPrice = prices.get(day - 1);
            
            // Calculate the difference between today and yesterday
            int priceDifference = todayPrice - yesterdayPrice;
            
            // If price went up, we could have bought yesterday and sold today
            // So we add this profit to our total
            if (priceDifference > 0) {
                totalProfit = totalProfit + priceDifference; // Accumulate profit
            }
            // If price went down or stayed same, we do nothing (skip that day)
        }
        
        // Return the maximum profit we calculated
        return totalProfit;
    }
    
    /**
     * Test helper method to check if result matches expected value
     * Prints PASS or FAIL with details
     */
    public static void runTest(String testName, List<Integer> input, int expected) {
        
        // Calculate actual result using our function
        int actual = calculateMaxProfit(input);
        
        // Compare actual with expected to determine pass/fail
        boolean passed = (actual == expected);
        
        // Build result message with test details
        String status = passed ? "PASS" : "FAIL";
        
        // Print the test result with all relevant information
        System.out.println("Test: " + testName);
        System.out.println("  Input: " + input);
        System.out.println("  Expected: " + expected + ", Actual: " + actual);
        System.out.println("  Result: " + status);
        System.out.println(); // Empty line for readability
    }
    
    /**
     * Main method to run all test cases
     * Tests include normal cases, edge cases, and large data
     */
    public static void main(String[] args) {
        
        System.out.println("=== Stock Profit Calculator Tests ===\n");
        
        // Test 1: Given example from the problem
        // Buy at 1, sell at 7 (profit 6), buy at 2, sell at 10 (profit 8), buy at 2, sell at 5 (profit 3)
        // Or equivalently: (3-1) + (7-3) + (5-2) + (7-5) + (10-7) + (5-2) = 2+4+3+2+3+3 = 17
        List<Integer> test1 = Arrays.asList(1, 3, 7, 4, 2, 5, 7, 10, 2, 5);
        runTest("Given Example", test1, 17);
        
        // Test 2: Continuously increasing prices
        // Best strategy: buy on day 1, sell on last day
        // Profit: 5-1=4 OR (2-1)+(3-2)+(4-3)+(5-4) = 1+1+1+1 = 4
        List<Integer> test2 = Arrays.asList(1, 2, 3, 4, 5);
        runTest("Increasing Prices", test2, 4);
        
        // Test 3: Continuously decreasing prices
        // No profitable trade possible, should return 0
        List<Integer> test3 = Arrays.asList(5, 4, 3, 2, 1);
        runTest("Decreasing Prices", test3, 0);
        
        // Test 4: All same prices
        // No price change means no profit opportunity
        List<Integer> test4 = Arrays.asList(5, 5, 5, 5, 5);
        runTest("Same Prices", test4, 0);
        
        // Test 5: Single element - cannot trade
        // Need at least 2 days to buy and sell
        List<Integer> test5 = List.of(10);
        runTest("Single Element", test5, 0);
        
        // Test 6: Empty list - no data means no trades
        List<Integer> test6 = new ArrayList<>();
        runTest("Empty List", test6, 0);
        
        // Test 7: Null input - should handle gracefully
        runTest("Null Input", null, 0);
        
        // Test 8: Two elements with profit
        // Simple buy low sell high scenario
        List<Integer> test8 = Arrays.asList(1, 10);
        runTest("Two Elements Profit", test8, 9);
        
        // Test 9: Two elements with no profit
        // Price dropped, no profitable trade
        List<Integer> test9 = Arrays.asList(10, 1);
        runTest("Two Elements No Profit", test9, 0);
        
        // Test 10: Alternating pattern
        // Buy at each low (1), sell at each high (5)
        // Profit: (5-1)+(5-1)+(5-1) = 4+4+4 = 12
        List<Integer> test10 = Arrays.asList(1, 5, 1, 5, 1, 5);
        runTest("Alternating Pattern", test10, 12);
        
        // Test 11: Large data test - performance check
        // Create a large list to ensure algorithm handles big inputs
        System.out.println("Test: Large Data (100,000 elements)");
        
        // Generate large dataset with alternating pattern 1,2,1,2...
        List<Integer> largeTest = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            // Alternate between 1 and 2 to create predictable profit
            largeTest.add((i % 2 == 0) ? 1 : 2); // Even index: 1, Odd index: 2
        }
        
        // Time the execution for large data
        long startTime = System.currentTimeMillis(); // Record start time
        int largeResult = calculateMaxProfit(largeTest); // Run calculation
        long endTime = System.currentTimeMillis(); // Record end time
        
        // Expected: 50000 pairs of (2-1) = 50000
        int expectedLarge = 50000;
        boolean largePassed = (largeResult == expectedLarge);
        
        System.out.println("  Size: 100,000 elements");
        System.out.println("  Expected: " + expectedLarge + ", Actual: " + largeResult);
        System.out.println("  Time taken: " + (endTime - startTime) + " ms");
        System.out.println("  Result: " + (largePassed ? "PASS" : "FAIL"));
        System.out.println();
        
        // Test 12: Very large values
        // Test with maximum integer values to check overflow handling
        List<Integer> test12 = Arrays.asList(1000000, 2000000, 1500000, 3000000);
        // Profit: (2000000-1000000) + (3000000-1500000) = 1000000 + 1500000 = 2500000
        runTest("Large Values", test12, 2500000);
        
        // Test 13: Mixed pattern with zeros
        // Include zero prices to test edge values
        List<Integer> test13 = Arrays.asList(0, 5, 0, 10, 0);
        // Profit: (5-0) + (10-0) = 5 + 10 = 15
        runTest("With Zero Values", test13, 15);
        
        System.out.println("=== All Tests Completed ===");
    }
}