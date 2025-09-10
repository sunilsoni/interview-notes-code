package com.interview.notes.code.year.y2025.september.glider.test1;

import java.util.*;
import java.util.stream.Collectors;

/*
🔎 Problem Restatement in My Own Words
	•	You have a list of orders.
Each order contains:
	•	account_number
	•	fund_symbol
	•	fund_family
	•	quantity
	•	side (BUY or SELL)
	•	You need to match BUY and SELL orders to create exchange orders.
Match criteria:
	1.	Same account_number
	2.	Same fund_family
	•	Each matched exchange order should contain:
	•	account_number
	•	fund_family
	•	buy_fund_symbol
	•	sell_fund_symbol
	•	quantity (min of buy and sell)
	•	After matching, you must also return:
	•	Remaining BUY orders (not fully matched)
	•	Remaining SELL orders (not fully matched)
 */

/**
 * This program matches BUY and SELL fund orders for the same account_number and fund_family.
 * It produces:
 * 1. Matched Exchange Orders
 * 2. Remaining Buy Orders
 * 3. Remaining Sell Orders
 */
public class FundExchangeMatcher {

    /**
     * Main matching logic:
     * - Groups orders by (account_number + fund_family).
     * - Matches BUY and SELL orders to maximize matched quantity.
     * - Allows partial matches.
     */
    public static Map<String, List<?>> matchOrders(List<Order> orders) {
        List<Exchange> matchedExchanges = new ArrayList<>();
        List<Order> remainingBuys = new ArrayList<>();
        List<Order> remainingSells = new ArrayList<>();

        // Group orders by account_number + fund_family
        Map<String, List<Order>> groupedOrders = orders.stream()
                .collect(Collectors.groupingBy(o -> o.accountNumber + "|" + o.fundFamily));

        // Process each group independently
        for (Map.Entry<String, List<Order>> entry : groupedOrders.entrySet()) {
            List<Order> group = entry.getValue();

            // Separate BUY and SELL orders
            Queue<Order> buys = new LinkedList<>(group.stream()
                    .filter(o -> o.side.equals("BUY"))
                    .collect(Collectors.toList()));
            Queue<Order> sells = new LinkedList<>(group.stream()
                    .filter(o -> o.side.equals("SELL"))
                    .collect(Collectors.toList()));

            // Try to match buys and sells
            while (!buys.isEmpty() && !sells.isEmpty()) {
                Order buy = buys.peek();
                Order sell = sells.peek();

                double matchedQty = Math.min(buy.quantity, sell.quantity);

                // Create an exchange record
                matchedExchanges.add(new Exchange(
                        buy.accountNumber,
                        buy.fundFamily,
                        buy.fundSymbol,
                        sell.fundSymbol,
                        matchedQty
                ));

                // Update quantities after matching
                buy.quantity -= matchedQty;
                sell.quantity -= matchedQty;

                // Remove orders that are fully matched
                if (buy.quantity == 0) buys.poll();
                if (sell.quantity == 0) sells.poll();
            }

            // Collect remaining unmatched orders
            remainingBuys.addAll(buys);
            remainingSells.addAll(sells);
        }

        // Return results as a map
        Map<String, List<?>> result = new HashMap<>();
        result.put("matched", matchedExchanges);
        result.put("remainingBuys", remainingBuys);
        result.put("remainingSells", remainingSells);

        return result;
    }

    // Test method with PASS/FAIL validation
    public static void main(String[] args) {
        // Sample Input Orders
        List<Order> orders = Arrays.asList(
                new Order("12345", "ABC", "Fidelity", 100.00, "BUY"),
                new Order("67890", "XYZ", "Vanguard", 50.00, "BUY"),
                new Order("12345", "DEF", "Fidelity", 75.00, "BUY"),
                new Order("12345", "GHI", "Fidelity", 80.00, "SELL"),
                new Order("67890", "UVW", "Vanguard", 50.00, "SELL"),
                new Order("12345", "JKL", "Fidelity", 25.00, "SELL")
        );

        // Run Matching
        Map<String, List<?>> result = matchOrders(orders);

        // Print Results
        System.out.println("Matched Exchange Orders:");
        result.get("matched").forEach(System.out::println);

        System.out.println("\nRemaining Buy Orders:");
        result.get("remainingBuys").forEach(System.out::println);

        System.out.println("\nRemaining Sell Orders:");
        result.get("remainingSells").forEach(System.out::println);

        // Simple PASS/FAIL test validation
        boolean testPassed = result.get("matched").size() == 3 &&
                result.get("remainingBuys").size() == 2 &&
                result.get("remainingSells").isEmpty();

        System.out.println("\nTEST RESULT: " + (testPassed ? "PASS ✅" : "FAIL ❌"));

        // Large Data Test for Performance
        List<Order> largeOrders = new ArrayList<>();
        for (int i = 0; i < 500000; i++) {
            largeOrders.add(new Order("A1", "F" + i, "Fidelity", 100.0, "BUY"));
            largeOrders.add(new Order("A1", "S" + i, "Fidelity", 100.0, "SELL"));
        }
        long start = System.currentTimeMillis();
        matchOrders(largeOrders);
        long end = System.currentTimeMillis();
        System.out.println("Large Data Test Completed in: " + (end - start) + " ms");
    }

    // Order class to represent each buy/sell order
    static class Order {
        String accountNumber;
        String fundSymbol;
        String fundFamily;
        double quantity;
        String side; // BUY or SELL

        Order(String accountNumber, String fundSymbol, String fundFamily, double quantity, String side) {
            this.accountNumber = accountNumber;
            this.fundSymbol = fundSymbol;
            this.fundFamily = fundFamily;
            this.quantity = quantity;
            this.side = side;
        }

        @Override
        public String toString() {
            return "{account_number=" + accountNumber +
                    ", fund_symbol=" + fundSymbol +
                    ", fund_family=" + fundFamily +
                    ", quantity=" + quantity +
                    ", side=" + side + "}";
        }
    }

    // Exchange class to represent a matched exchange order
    static class Exchange {
        String accountNumber;
        String fundFamily;
        String buyFundSymbol;
        String sellFundSymbol;
        double quantity;

        Exchange(String accountNumber, String fundFamily, String buyFundSymbol, String sellFundSymbol, double quantity) {
            this.accountNumber = accountNumber;
            this.fundFamily = fundFamily;
            this.buyFundSymbol = buyFundSymbol;
            this.sellFundSymbol = sellFundSymbol;
            this.quantity = quantity;
        }

        @Override
        public String toString() {
            return "{account_number=" + accountNumber +
                    ", fund_family=" + fundFamily +
                    ", buy_fund_symbol=" + buyFundSymbol +
                    ", sell_fund_symbol=" + sellFundSymbol +
                    ", quantity=" + quantity + "}";
        }
    }
}