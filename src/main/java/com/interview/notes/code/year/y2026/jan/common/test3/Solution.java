package com.interview.notes.code.year.y2026.jan.common.test3;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    // Logic: Iterate array -> Sum into Map -> Find Max in Map
    private static String topStock(String[][] input) {
        // Return empty if input is null/empty to prevent errors
        if (input == null || input.length == 0) return "";

        // Map to hold Ticker (Key) and Total Investment (Value)
        Map<String, Double> map = new HashMap<>();

        // Loop through every order in the input array
        for (String[] order : input) {
            String ticker = order[0]; // Get Ticker name
            // Parse strings to double for calculation
            double price = Double.parseDouble(order[1]);
            double qty = Double.parseDouble(order[2]);

            // Calculate current order value
            double cost = price * qty;

            // Update the map: add to existing value or put new value
            if (map.containsKey(ticker)) {
                map.put(ticker, map.get(ticker) + cost);
            } else {
                map.put(ticker, cost);
            }
        }

        // Variable to track the highest investment found
        String bestStock = "";
        double maxVal = -1.0;

        // Loop through the map to find the winner
        for (String key : map.keySet()) {
            double val = map.get(key);
            // If this stock is higher than current max, update it
            if (val > maxVal) {
                maxVal = val;
                bestStock = key;
            }
        }
        return bestStock;
    }

    // Simple test helper to print PASS/FAIL
    private static void check(String name, String[][] in, String exp) {
        String res = topStock(in); // Run logic
        // Print result: PASS if matches expected, else FAIL
        System.out.println(name + ": " + (res.equals(exp) ? "PASS" : "FAIL"));
    }

    public static void main(String[] args) {
        // Case 1: Provided Example
        // AAPL total: (120.3*3) + (110.8*4) = 804.1 (Winner)
        String[][] t1 = {
                {"AAPL", "120.3", "3"}, {"GOOGL", "190.9", "4"},
                {"AAPL", "110.8", "4"}, {"AMZN", "790.3", "1"}
        };
        check("Test 1 (Base)", t1, "AAPL");

        // Case 2: Large Data (High volume)
        // TSLA: 1000.5 * 50000 = 50,025,000.0 (Winner)
        String[][] t2 = {
                {"TSLA", "1000.50", "50000"},
                {"NVDA", "400.20", "10000"}
        };
        check("Test 2 (Large Data)", t2, "TSLA");

        // Case 3: Single Input
        String[][] t3 = {{"MSFT", "250.0", "10"}};
        check("Test 3 (Single)", t3, "MSFT");
    }
}