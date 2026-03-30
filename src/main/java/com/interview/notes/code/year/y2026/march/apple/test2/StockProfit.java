package com.interview.notes.code.year.y2026.march.apple.test2;

public class StockProfit { // Main class declaration

    public static int maxProfit(int[] prices) { // Method takes an array of stock prices and returns max profit
        var minPrice = Integer.MAX_VALUE; // Track lowest price, initialized to max possible so any first price is lower
        var maxProfit = 0; // Track highest profit, starting at 0 since we can choose not to buy at all
        
        for (var price : prices) { // Loop through each price sequentially day by day
            minPrice = Math.min(minPrice, price); // If today's price is cheaper than our recorded minPrice, update it
            maxProfit = Math.max(maxProfit, price - minPrice); // Check if selling today gives better profit; update if yes
        } // Close the loop
        
        return maxProfit; // Return the final maximum profit found
    } // Close the method

    public static void main(String[] args) { // Simple main method for testing without JUnit

        var tests = java.util.List.of( // Create an immutable list of test cases using Java 9+ factory method
            new TestCase(new int[]{7, 1, 5, 3, 6, 4}, 5), // Buy at 1, sell at 6 -> profit 5
            new TestCase(new int[]{7, 6, 4, 3, 1}, 0), // Prices only drop, no profit possible -> 0
            new TestCase(new int[]{1, 2}, 1), // Minimum viable profit case
            new TestCase(new int[]{3, 3, 3}, 0), // Flat prices, no profit possible -> 0
            new TestCase(new int[]{}, 0) // Edge case: empty array -> 0
        ); // Close the list

        var testNum = 1; // Simple counter to label our test cases in console output

        for (var test : tests) { // Loop through our list of defined test cases
            var result = maxProfit(test.prices()); // Execute the method with the current test's array
            var status = (result == test.expected()) ? "PASS" : "FAIL"; // Compare actual result with expected result
            System.out.println("Test " + testNum++ + ": " + status + " (Got: " + result + ")"); // Print out whether it passed or failed
        } // Close the test loop

        // --- LARGE DATA TEST --- // Comment block separating large data section
        var largeSize = 100_000; // Define a massive array size for performance testing
        var largePrices = new int[largeSize]; // Initialize the large array

        for (var i = 0; i < largePrices.length; i++) { // Loop to populate the large array
            largePrices[i] = largeSize - i; // Fill it with decreasing numbers so minPrice keeps updating
        } // Close array population loop

        largePrices[largeSize - 2] = 1; // Inject a tiny buy price right near the end
        largePrices[largeSize - 1] = 100; // Inject a high sell price at the very end

        var start = System.nanoTime(); // Start the timer to check performance
        var largeResult = maxProfit(largePrices); // Execute method against the massive array
        var timeMs = (System.nanoTime() - start) / 1_000_000.0; // Calculate elapsed time in milliseconds

        var largeStatus = (largeResult == 99) ? "PASS" : "FAIL"; // Expected is 100 - 1 = 99
        System.out.println("Large Data Test: " + largeStatus + " (Time: " + timeMs + " ms)"); // Output large test result and execution time
    } // Close main method

    // Java 14+ Record feature to compactly store test case inputs and expected outputs
    record TestCase(int[] prices, int expected) {} // Defines an immutable object with prices array and expected profit
} // Close main class