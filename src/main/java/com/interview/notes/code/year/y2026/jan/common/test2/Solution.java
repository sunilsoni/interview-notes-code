package com.interview.notes.code.year.y2026.jan.common.test2;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Solution {

    /**
     * Hybrid Solution: Loop for parsing + Map.merge for aggregation.
     *
     * @param input 2D array {Ticker, Price, Qty}
     * @return Ticker with highest investment
     */
    private static String topStock(String[][] input) {
        // Handle empty/null inputs gracefully
        if (input == null || input.length == 0) return "";

        // 'var' reduces type declaration verbosity (Java 10+)
        var map = new HashMap<String, Double>();

        // Enhanced loop is cleaner than Stream mapping for array indices
        for (var order : input) {
            // map.merge: If key exists, sum values. If not, add key. (Java 8 feature)
            // Logic: Key=Ticker, Value=Price*Qty, MergeFunction=Sum old+new
            map.merge(order[0], Double.parseDouble(order[1]) * Double.parseDouble(order[2]), Double::sum);
        }

        // Collections.max is shorter than stream().max(). Returns the Key (Ticker) of largest Value.
        return Collections.max(map.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    /**
     * Test Runner: Validates Pass/Fail and logic.
     */
    private static void test(String name, String[][] input, String expected) {
        // Execute the function
        String actual = topStock(input);
        // Check if Actual result matches Expected result
        boolean pass = actual.equals(expected);
        // Print status
        System.out.println(name + " -> " + (pass ? "PASS" : "FAIL") + " [Expected: " + expected + ", Got: " + actual + "]");
    }

    public static void main(String[] args) {
        // Test 1: Standard provided case
        // AAPL = 120.3*3 (360.9) + 110.8*4 (443.2) = 804.1 (Winner)
        String[][] t1 = {{"AAPL", "120.3", "3"}, {"GOOGL", "190.9", "4"}, {"AAPL", "110.8", "4"}, {"AMZN", "790.3", "1"}};
        test("Case 1 (Base)", t1, "AAPL");

        // Test 2: Large Data / High Values
        // TSLA = 50,025,000.0 (Winner vs NVDA 4M)
        String[][] t2 = {{"TSLA", "1000.5", "50000"}, {"NVDA", "400.2", "10000"}};
        test("Case 2 (Large)", t2, "TSLA");

        // Test 3: Single Entry Edge Case
        String[][] t3 = {{"MSFT", "300.5", "10"}};
        test("Case 3 (Single)", t3, "MSFT");

        // Test 4: Empty Input Edge Case
        test("Case 4 (Empty)", new String[][]{}, "");
    }
}