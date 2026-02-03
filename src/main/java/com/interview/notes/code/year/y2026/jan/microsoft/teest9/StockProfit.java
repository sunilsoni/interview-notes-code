package com.interview.notes.code.year.y2026.jan.microsoft.teest9;

import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StockProfit {

    /**
     * Calculates the maximum profit with transaction fees.
     * Logic: Dynamic Programming tracking 'cash' and 'hold' states.
     */
    public static int maxProfit(int[] prices, int fee) {
        // If prices array is null or too small to make a transaction, return 0 profit immediately.
        if (prices == null || prices.length <= 1) return 0;

        // 'cash' represents max profit if we end the day NOT holding a stock.
        // Initialized to 0 because we start with 0 profit and 0 stocks.
        var cash = 0;

        // 'hold' represents max profit if we end the day HOLDING a stock.
        // Initialized to -prices[0] because to hold on day 0, we must buy it (subtract cost).
        var hold = -prices[0];

        // Iterate through prices starting from day 1 (since day 0 is initialized above).
        for (var i = 1; i < prices.length; i++) {
            // Update 'cash': Max of keeping previous cash OR selling current stock (hold + price - fee).
            // We pay the fee at the moment of selling.
            cash = Math.max(cash, hold + prices[i] - fee);

            // Update 'hold': Max of keeping previous stock OR buying new stock (cash - price).
            // Note: We use the *current* 'cash' value (which technically represents yesterday's cash state 
            // before the sell above due to order of operations, or effectively the same day if we didn't sell). 
            // Actually, in this specific DP problem, using the 'cash' value from the same iteration works 
            // because you can't buy and sell on the exact same step to gain profit unless price > price + fee (impossible).
            // Standard approach: hold = Math.max(hold, cash - prices[i]);
            hold = Math.max(hold, cash - prices[i]);
        }
        
        // The result is 'cash' because we want to end up with no stock to maximize realized profit.
        return cash;
    }

    // --- Testing Harness (Java 21 + Streams) ---

    public static void main(String[] args) {
        System.out.println("Running Tests...");

        // Define test cases using a simple Record (Java 14+) for data holding
        record TestCase(int[] prices, int fee, int expected, String desc) {}

        // Create a stream of test cases
        Stream.of(
            new TestCase(new int[]{1, 3, 2, 8, 4, 9}, 2, 8, "Example Case from Screenshot"),
            new TestCase(new int[]{1, 3, 7, 5, 10, 3}, 3, 6, "General Case"),
            new TestCase(new int[]{1, 1, 1, 1}, 0, 0, "Flat Prices"),
            new TestCase(new int[]{9, 8, 7, 1}, 2, 0, "Descending Prices (Loss avoidance)"),
            new TestCase(generateLargeData(1_000_000), 2, -1, "Large Data (1M items)") // -1 indicates we calculate dynamically
        ).forEach(test -> {
            // Start timer for performance check
            var start = System.currentTimeMillis();
            
            // Execute logic
            var actual = maxProfit(test.prices, test.fee);
            
            // Calculate duration
            var time = System.currentTimeMillis() - start;

            // Determine Pass/Fail. For large data, we just ensure it runs without crashing/timeout.
            var passed = test.expected == -1 || (actual == test.expected);
            var status = passed ? "PASS" : "FAIL";

            // Print concise report
            System.out.printf("[%s] %-30s | Fee: %d | Exp: %-4s | Act: %-4d | Time: %dms%n", 
                status, test.desc, test.fee, (test.expected == -1 ? "N/A" : test.expected), actual, time);
        });
    }

    // Helper to generate large dataset using Streams
    private static int[] generateLargeData(int size) {
        // Generates 'size' random integers between 1 and 100
        return IntStream.generate(() -> new Random().nextInt(100) + 1)
                        .limit(size)
                        .toArray();
    }
}