package com.interview.notes.code.year.y2025.may.apple.test2;

public class StockTrader {
    // Solution for stock trading with transaction fee
    public static int maxProfitWithFee(int[] prices, int fee) {
        // Edge case check
        if (prices == null || prices.length <= 1) return 0;
        
        // hold represents maximum profit if we're holding stock
        int hold = -prices[0];
        // free represents maximum profit when we don't have any stock
        int free = 0;
        
        // Iterate through prices starting from day 1
        for (int i = 1; i < prices.length; i++) {
            // Previous free state vs selling stock now
            int prevFree = free;
            // Max of: keeping current free state OR selling stock (hold + current price - fee)
            free = Math.max(free, hold + prices[i] - fee);
            // Max of: keeping current hold state OR buying new stock (prevFree - current price)
            hold = Math.max(hold, prevFree - prices[i]);
        }
        
        // Return free state as we want maximum profit without holding stock
        return free;
    }
    
    // Solution for stock trading with cooldown period
    public static int maxProfitWithCooldown(int[] prices) {
        // Edge case check
        if (prices == null || prices.length <= 1) return 0;
        
        // hold: max profit while holding stock
        // sold: max profit after selling stock
        // rest: max profit while in cooldown
        int hold = -prices[0], sold = 0, rest = 0;
        
        for (int i = 1; i < prices.length; i++) {
            int prevHold = hold;
            int prevSold = sold;
            int prevRest = rest;
            
            // Can either keep holding or buy after rest
            hold = Math.max(prevHold, prevRest - prices[i]);
            // Can sell if we were holding
            sold = prevHold + prices[i];
            // Can rest if we were in rest state or just sold
            rest = Math.max(prevRest, prevSold);
        }
        
        // Return maximum of sold or rest state
        return Math.max(sold, rest);
    }
    
    // Test method
    public static void main(String[] args) {
        // Test cases for transaction fee
        testWithFee();
        
        // Test cases for cooldown
        testWithCooldown();
    }
    
    private static void testWithFee() {
        System.out.println("Testing with Transaction Fee:");
        
        // Test case 1: Normal case
        int[] prices1 = {1, 3, 2, 8, 4, 9};
        int fee1 = 2;
        int result1 = maxProfitWithFee(prices1, fee1);
        System.out.println("Test 1: " + (result1 == 8 ? "PASS" : "FAIL") + 
                          " (Expected: 8, Got: " + result1 + ")");
        
        // Test case 2: Edge case - empty array
        int[] prices2 = {};
        int result2 = maxProfitWithFee(prices2, 2);
        System.out.println("Test 2: " + (result2 == 0 ? "PASS" : "FAIL") + 
                          " (Expected: 0, Got: " + result2 + ")");
        
        // Test case 3: Large data
        int[] prices3 = new int[10000];
        for (int i = 0; i < prices3.length; i++) {
            prices3[i] = i % 100; // Creates repeating pattern
        }
        long startTime = System.currentTimeMillis();
        maxProfitWithFee(prices3, 2);
        System.out.println("Test 3 (Large Data): Completed in " + 
                          (System.currentTimeMillis() - startTime) + "ms");
    }
    
    private static void testWithCooldown() {
        System.out.println("\nTesting with Cooldown:");
        
        // Test case 1: Normal case
        int[] prices1 = {1, 2, 3, 0, 2};
        int result1 = maxProfitWithCooldown(prices1);
        System.out.println("Test 1: " + (result1 == 3 ? "PASS" : "FAIL") + 
                          " (Expected: 3, Got: " + result1 + ")");
        
        // Test case 2: Edge case - empty array
        int[] prices2 = {};
        int result2 = maxProfitWithCooldown(prices2);
        System.out.println("Test 2: " + (result2 == 0 ? "PASS" : "FAIL") + 
                          " (Expected: 0, Got: " + result2 + ")");
        
        // Test case 3: Large data
        int[] prices3 = new int[10000];
        for (int i = 0; i < prices3.length; i++) {
            prices3[i] = i % 100;
        }
        long startTime = System.currentTimeMillis();
        maxProfitWithCooldown(prices3);
        System.out.println("Test 3 (Large Data): Completed in " + 
                          (System.currentTimeMillis() - startTime) + "ms");
    }
}
