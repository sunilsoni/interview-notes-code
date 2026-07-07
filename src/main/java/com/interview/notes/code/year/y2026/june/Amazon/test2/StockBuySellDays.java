package com.interview.notes.code.year.y2026.june.Amazon.test2;

import java.util.ArrayList;                 // Needed to store all buy/sell transactions
import java.util.List;                      // Needed to return list of transactions

public class StockBuySellDays {             // Main class

    static List<Trade> getTrades(int[] prices) { // This method returns actual buy and sell days

        List<Trade> trades = new ArrayList<>(); // Store all valid buy/sell transactions here

        if (prices == null || prices.length < 2) return trades; // No trade possible for null, empty, or single day

        for (int price : prices) { // Check each price once
            if (price < 0) throw new IllegalArgumentException("Price cannot be negative"); // Stock price cannot be negative
        }

        int day = 0; // Start checking from day 0

        while (day < prices.length - 1) { // Continue until second last day because we compare with next day

            while (day < prices.length - 1 && prices[day + 1] <= prices[day]) day++; // Skip falling or same prices

            int buyDay = day; // Current low price day becomes buy day

            while (day < prices.length - 1 && prices[day + 1] > prices[day]) day++; // Move while price is increasing

            int sellDay = day; // Highest day before drop becomes sell day

            if (sellDay > buyDay) { // Add trade only if selling day is after buying day

                long profit = (long) prices[sellDay] - prices[buyDay]; // Calculate profit safely using long

                trades.add(new Trade(buyDay, sellDay, prices[buyDay], prices[sellDay], profit)); // Save this transaction
            }
        }

        return trades; // Return all buy/sell transactions
    }

    static long totalProfit(List<Trade> trades) { // Helper method to calculate total profit from trades

        return trades.stream() // Convert trades list into stream
                .mapToLong(Trade::profit) // Take profit from each trade
                .sum(); // Add all profits
    }

    static void test(String name, int[] prices, long expectedProfit, int expectedTrades) { // Simple PASS/FAIL test method

        List<Trade> trades = getTrades(prices); // Get actual buy/sell trades

        long actualProfit = totalProfit(trades); // Calculate total profit

        boolean pass = actualProfit == expectedProfit && trades.size() == expectedTrades; // Check profit and trade count

        System.out.println((pass ? "PASS" : "FAIL") + " | " + name); // Print test result

        System.out.println("Expected Profit: " + expectedProfit + ", Actual Profit: " + actualProfit); // Print profit check

        System.out.println("Trades: " + trades); // Print buy/sell days

        System.out.println(); // Print empty line for readability
    }

    public static void main(String[] args) { // Program starts here

        test("Given example", new int[]{100, 180, 260, 310, 40, 535, 695}, 865, 2); // Main example

        test("Only increasing", new int[]{10, 20, 30, 40}, 30, 1); // Buy day 0, sell day 3

        test("Only decreasing", new int[]{40, 30, 20, 10}, 0, 0); // No trade possible

        test("Same prices", new int[]{10, 10, 10}, 0, 0); // No profit possible

        test("Single day", new int[]{10}, 0, 0); // Cannot buy and sell

        test("Empty array", new int[]{}, 0, 0); // No data

        test("Null array", null, 0, 0); // Null input

        test("Mixed case", new int[]{3, 2, 6, 5, 0, 3}, 7, 2); // Buy 1 sell 2, buy 4 sell 5
    }

    record Trade(int buyDay, int sellDay, int buyPrice, int sellPrice, long profit) {} // Java 21 record to store one transaction
}