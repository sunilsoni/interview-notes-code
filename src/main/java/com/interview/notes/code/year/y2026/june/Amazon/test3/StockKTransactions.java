package com.interview.notes.code.year.y2026.june.Amazon.test3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StockKTransactions { // Main class

    static Result maxProfitK(int[] prices, int k) { // Method to find max profit with at most k transactions

        List<Trade> empty = new ArrayList<>(); // Empty result for invalid or no-trade cases

        if (prices == null || prices.length < 2 || k <= 0) return new Result(0, empty); // No transaction possible

        for (int price : prices) if (price < 0) throw new IllegalArgumentException("Price cannot be negative"); // Validate prices

        int n = prices.length; // Store number of days

        long[][] dp = new long[k + 1][n]; // dp[t][d] = max profit till day d with at most t transactions

        boolean[][] sold = new boolean[k + 1][n]; // true means we sold stock on this day

        int[][] buyAt = new int[k + 1][n]; // stores buy day when we sell on a day

        for (int t = 1; t <= k; t++) { // Try transaction count from 1 to k

            long best = -prices[0]; // Best value if we buy on day 0

            int bestBuyDay = 0; // Current best buy day

            for (int day = 1; day < n; day++) { // Check each day from day 1

                long skip = dp[t][day - 1]; // Option 1: do nothing today

                long sellToday = prices[day] + best; // Option 2: sell today

                if (sellToday > skip) { // If selling today gives better profit

                    dp[t][day] = sellToday; // Store better profit

                    sold[t][day] = true; // Mark that we sold today

                    buyAt[t][day] = bestBuyDay; // Store matching buy day

                } else { // If doing nothing is better

                    dp[t][day] = skip; // Carry previous best profit
                }

                long newBest = dp[t - 1][day] - prices[day]; // Profit left after buying today

                if (newBest > best) { // If today is better buy point

                    best = newBest; // Update best buy value

                    bestBuyDay = day; // Update best buy day
                }
            }
        }

        List<Trade> trades = new ArrayList<>(); // Store final selected trades

        int t = k; // Start from max allowed transactions

        int day = n - 1; // Start from last day

        while (t > 0 && day > 0) { // Backtrack until no transaction or days left

            if (!sold[t][day]) { // If no sell happened on this day

                day--; // Move to previous day

            } else { // If sell happened on this day

                int buy = buyAt[t][day]; // Get matching buy day

                int sell = day; // Current day is sell day

                long profit = (long) prices[sell] - prices[buy]; // Calculate this trade profit

                trades.add(new Trade(buy, sell, prices[buy], prices[sell], profit)); // Add trade

                day = buy; // Continue checking before buy day

                t--; // One transaction is used
            }
        }

        Collections.reverse(trades); // Reverse because backtracking gives trades from end to start

        return new Result(dp[k][n - 1], trades); // Return total profit and selected trades
    }

    static void test(String name, int[] prices, int k, long expectedProfit) { // Simple PASS/FAIL test

        Result result = maxProfitK(prices, k); // Call method

        boolean pass = result.profit() == expectedProfit; // Compare expected and actual profit

        System.out.println((pass ? "PASS" : "FAIL") + " | " + name); // Print test status

        System.out.println("K = " + k + ", Expected = " + expectedProfit + ", Actual = " + result.profit()); // Print profit

        System.out.println("Trades = " + result.trades()); // Print buy/sell days

        System.out.println(); // Empty line
    }

    public static void main(String[] args) { // Main method

        int[] prices = {100, 180, 260, 310, 40, 535, 695}; // Existing example

        test("Existing example with K = 1", prices, 1, 655); // Best single transaction is 40 to 695

        test("Existing example with K = 2", prices, 2, 865); // Both transactions allowed

        test("Existing example with K = 3", prices, 3, 865); // Extra transaction does not increase profit

        test("Only decreasing", new int[]{5, 4, 3, 2, 1}, 2, 0); // No profit

        test("Only increasing", new int[]{1, 2, 3, 4, 5}, 2, 4); // Buy day 0, sell day 4

        test("Mixed case K = 1", new int[]{3, 2, 6, 5, 0, 3}, 1, 4); // Buy 2, sell 6

        test("Mixed case K = 2", new int[]{3, 2, 6, 5, 0, 3}, 2, 7); // Buy 2 sell 6, buy 0 sell 3
    }

    record Trade(int buyDay, int sellDay, int buyPrice, int sellPrice, long profit) {} // Stores one buy/sell transaction

    record Result(long profit, List<Trade> trades) {} // Stores total profit and all trades
}