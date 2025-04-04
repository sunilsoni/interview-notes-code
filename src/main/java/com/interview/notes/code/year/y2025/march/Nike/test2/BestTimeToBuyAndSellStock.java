package com.interview.notes.code.year.y2025.march.Nike.test2;

/*
### **Best Time to Buy and Sell Stock**

**Description:**
You are given an integer array `prices` where `prices[i]` is the price of a given stock on the *i-th* day.
On each day, you may decide to buy and/or sell the stock.
You can only hold **at most one share** of the stock at any time.
However, you can buy it and sell it on the same day.
Find and return the **maximum profit** you can achieve.

**Input:**
- An array of integers `prices` representing the price of the stock on each day:
  `[7, 1, 5, 3, 6, 4]`

**Output:**
- An integer representing the **maximum profit** you can achieve:
  `7`

 */
public class BestTimeToBuyAndSellStock {
    
    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        
        int maxProfit = 0;
        
        for (int i = 1; i < prices.length; i++) {
            // If current price is higher than previous day's price
            if (prices[i] > prices[i - 1]) {
                // Add the profit to our total
                maxProfit += prices[i] - prices[i - 1];
            }
        }
        
        return maxProfit;
    }
    
    public static void main(String[] args) {
        // Test cases
        testCase(new int[]{7, 1, 5, 3, 6, 4}, 7, "Example 1");
        testCase(new int[]{1, 2, 3, 4, 5}, 4, "Continuously increasing prices");
        testCase(new int[]{5, 4, 3, 2, 1}, 0, "Continuously decreasing prices");
        testCase(new int[]{}, 0, "Empty array");
        testCase(new int[]{1}, 0, "Single price");
        testCase(new int[]{1, 1, 1, 1}, 0, "Flat prices");
        
        // Large input test
        int[] largeInput = generateLargeInput(100000);
        long startTime = System.currentTimeMillis();
        int result = maxProfit(largeInput);
        long endTime = System.currentTimeMillis();
        System.out.println("Large input test (100,000 elements) - Result: " + result + 
                           ", Time taken: " + (endTime - startTime) + "ms");
    }
    
    private static void testCase(int[] prices, int expected, String description) {
        int result = maxProfit(prices);
        boolean passed = result == expected;
        
        System.out.println("Test: " + description);
        System.out.println("Input: " + arrayToString(prices));
        System.out.println("Expected: " + expected);
        System.out.println("Result: " + result);
        System.out.println("Status: " + (passed ? "PASS" : "FAIL"));
        System.out.println();
    }
    
    private static String arrayToString(int[] arr) {
        if (arr == null || arr.length == 0) {
            return "[]";
        }
        
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
    
    private static int[] generateLargeInput(int size) {
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            // Generate prices between 1 and 1000
            result[i] = (int)(Math.random() * 1000) + 1;
        }
        return result;
    }
}