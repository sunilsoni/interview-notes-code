package com.interview.notes.code.year.y2025.may.apple.test2;

public class StockProfit {
    
    /**
     * Calculates maximum profit from one buy-sell transaction
     * Uses Java 8 Stream API for cleaner implementation
     * Time Complexity: O(n) - single pass through array
     * Space Complexity: O(1) - constant space used
     */
    public static int maxProfit(int[] prices) {
        // Handle edge cases - empty or single element array
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        // Track minimum price seen so far and maximum profit
        int minPrice = prices[0];
        int maxProfit = 0;

        // Iterate through prices starting from second element
        for (int i = 1; i < prices.length; i++) {
            // Update minimum price if current price is lower
            minPrice = Math.min(minPrice, prices[i]);
            
            // Calculate potential profit if we sell at current price
            int currentProfit = prices[i] - minPrice;
            
            // Update maximum profit if current profit is higher
            maxProfit = Math.max(maxProfit, currentProfit);
        }

        return maxProfit;
    }

    /**
     * Main method for testing different scenarios
     * Includes various test cases and edge cases
     */
    public static void main(String[] args) {
        // Test Case 1: Normal case with profit
        testCase(new int[]{7, 1, 5, 3, 6, 4}, 5, "Normal case with profit");

        // Test Case 2: Decreasing prices - no profit
        testCase(new int[]{7, 6, 4, 3, 1}, 0, "Decreasing prices");

        // Test Case 3: Empty array
        testCase(new int[]{}, 0, "Empty array");

        // Test Case 4: Single element
        testCase(new int[]{1}, 0, "Single element");

        // Test Case 5: Two elements with profit
        testCase(new int[]{1, 2}, 1, "Two elements with profit");

        // Test Case 6: Two elements without profit
        testCase(new int[]{2, 1}, 0, "Two elements without profit");

        // Test Case 7: Large data set
        int[] largeArray = generateLargeArray(100000);
        testCase(largeArray, calculateExpectedProfit(largeArray), "Large dataset");
    }

    /**
     * Helper method to run test cases and print results
     */
    private static void testCase(int[] prices, int expectedProfit, String testName) {
        int result = maxProfit(prices);
        System.out.printf("Test: %s - %s (Expected: %d, Got: %d)%n",
                testName,
                result == expectedProfit ? "PASS" : "FAIL",
                expectedProfit,
                result);
    }

    /**
     * Generates large test array for performance testing
     */
    private static int[] generateLargeArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * 1000);
        }
        return array;
    }

    /**
     * Calculates expected profit for verification
     */
    private static int calculateExpectedProfit(int[] prices) {
        if (prices.length <= 1) return 0;
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        
        for (int price : prices) {
            if (price < minPrice) {
                minPrice = price;
            } else {
                maxProfit = Math.max(maxProfit, price - minPrice);
            }
        }
        return maxProfit;
    }
}
