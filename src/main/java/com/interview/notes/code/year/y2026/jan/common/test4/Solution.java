package com.interview.notes.code.year.y2026.jan.common.test4;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Solution {

    /**
     * Finds the stock with the greatest total investment.
     * Logic: Groups orders by Ticker -> Sums (Price * Qty) -> Returns max key.
     */
    private static String topStock(String[][] input) {
        // Return empty if input is null or empty to prevent errors
        if (input == null || input.length == 0) return "";

        // Start a stream processing pipeline on the input array
        return Arrays.stream(input)
                // Collect data into a Map<String, Double> where Key is Ticker
                .collect(Collectors.groupingBy(
                        order -> order[0], // The Ticker is at index 0 (e.g., "AAPL")
                        // Downstream collector: Sum the calculated investment for this ticker
                        Collectors.summingDouble(order ->
                                // Parse Price (idx 1) and Quantity (idx 2) and multiply them
                                Double.parseDouble(order[1]) * Double.parseDouble(order[2])
                        )
                ))
                .entrySet().stream() // Convert the resulting Map to a Stream of Entries
                .max(Map.Entry.comparingByValue()) // Find the entry with the highest investment value
                .map(Map.Entry::getKey) // Extract just the Ticker string (Key) from that entry
                .orElse(""); // Return empty string if something went wrong (fallback)
    }

    /**
     * Simple test runner to handle PASS/FAIL logic and print results.
     */
    private static void runTest(String testName, String[][] input, String expected) {
        String result = topStock(input); // Execute logic
        boolean passed = result.equals(expected); // Verify result
        // Print formatted output: Test Name | Status | Actual vs Expected
        System.out.println(testName + ": " + (passed ? "PASS" : "FAIL") + " (Got: " + result + ")");
    }

    public static void main(String[] args) {
        // --- Test Case 1: Provided Example ---
        String[][] t1 = {
                {"AAPL", "120.3", "3"},
                {"GOOGL", "190.9", "4"},
                {"AAPL", "110.8", "4"},
                {"AMZN", "790.3", "1"}
        };
        // AAPL total = (120.3*3) + (110.8*4) = 804.1 (Highest)
        runTest("Test Case 1 (Base)", t1, "AAPL");

        // --- Test Case 2: Large Data Simulation ---
        // Simulating high volume and higher prices to test Double capacity
        String[][] t2 = {
                {"TSLA", "1000.50", "50000"}, // 50,025,000.0
                {"NVDA", "400.20", "10000"},  // 4,002,000.0
                {"TSLA", "100.00", "5"}       // Adds negligible amount to TSLA
        };
        runTest("Test Case 2 (Large Input)", t2, "TSLA");

        // --- Test Case 3: Edge Case (Single Element) ---
        String[][] t3 = {{"MSFT", "250.0", "10"}};
        runTest("Test Case 3 (Single Item)", t3, "MSFT");

        // --- Test Case 4: Tie Breaker / Overwrite check ---
        // If prices are identical, the logic returns one of them (stable sort not guaranteed in hashmaps)
        // Here we ensure distinct winners work correctly
        String[][] t4 = {
                {"A", "10.0", "1"}, // 10
                {"B", "10.0", "2"}  // 20
        };
        runTest("Test Case 4 (Simple logic)", t4, "B");
    }
}