package com.interview.notes.code.year.y2024.nov24.amazon.test7;

import java.util.*;


public class CurrencyConverter2 {
    private Map<String, Map<String, Double>> conversionGraph;

    public CurrencyConverter2() {
        this.conversionGraph = new HashMap<>();
    }

    public static void main(String[] args) {
        CurrencyConverter2 converter = new CurrencyConverter2();

        // Test Case 1: Direct conversion
        System.out.println("Running Test Case 1: Direct conversion");
        converter.addRate("USD", "EUR", 0.85);
        double result1 = converter.convert("USD", "EUR", 100);
        boolean test1Pass = Math.abs(result1 - 85.0) < 0.0001;
        System.out.println("Test Case 1: " + (test1Pass ? "PASS" : "FAIL"));

        // Test Case 2: Indirect conversion
        System.out.println("\nRunning Test Case 2: Indirect conversion");
        converter.addRate("EUR", "GBP", 0.86);
        double result2 = converter.convert("USD", "GBP", 100);
        boolean test2Pass = Math.abs(result2 - 73.1) < 0.0001;
        System.out.println("Test Case 2: " + (test2Pass ? "PASS" : "FAIL"));

        // Test Case 3: Same currency conversion
        System.out.println("\nRunning Test Case 3: Same currency");
        double result3 = converter.convert("USD", "USD", 100);
        boolean test3Pass = Math.abs(result3 - 100.0) < 0.0001;
        System.out.println("Test Case 3: " + (test3Pass ? "PASS" : "FAIL"));

        // Test Case 4: Large data test
        System.out.println("\nRunning Test Case 4: Large data test");
        // Create a chain of 1000 currencies
        for (int i = 0; i < 999; i++) {
            converter.addRate("CUR" + i, "CUR" + (i + 1), 1.1);
        }
        try {
            double result4 = converter.convert("CUR0", "CUR999", 100);
            boolean test4Pass = result4 > 0;
            System.out.println("Test Case 4: " + (test4Pass ? "PASS" : "FAIL"));
        } catch (Exception e) {
            System.out.println("Test Case 4: FAIL - " + e.getMessage());
        }

        // Test Case 5: Invalid currency
        System.out.println("\nRunning Test Case 5: Invalid currency");
        try {
            converter.convert("USD", "XXX", 100);
            System.out.println("Test Case 5: FAIL");
        } catch (IllegalArgumentException e) {
            System.out.println("Test Case 5: PASS");
        }

        // Test Case 6: Complex conversion path
        System.out.println("\nRunning Test Case 6: Complex conversion path");
        converter.addRate("JPY", "USD", 0.0088);
        converter.addRate("GBP", "JPY", 155.0);
        double result6 = converter.convert("EUR", "JPY", 100);
        boolean test6Pass = result6 > 0;
        System.out.println("Test Case 6: " + (test6Pass ? "PASS" : "FAIL"));
    }

    // Add conversion rate to the graph
    public void addRate(String from, String to, double rate) {
        conversionGraph.computeIfAbsent(from, k -> new HashMap<>()).put(to, rate);
        // Add reverse rate
        conversionGraph.computeIfAbsent(to, k -> new HashMap<>()).put(from, 1.0 / rate);
    }

    // Convert currency using BFS to find conversion path
    public double convert(String from, String to, double amount) {
        if (from.equals(to)) return amount;
        if (!conversionGraph.containsKey(from) || !conversionGraph.containsKey(to)) {
            throw new IllegalArgumentException("Currency not found");
        }

        Queue<String> queue = new LinkedList<>();
        Queue<Double> rates = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        Map<String, Double> conversionRates = new HashMap<>();

        queue.offer(from);
        rates.offer(1.0);
        visited.add(from);
        conversionRates.put(from, 1.0);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            double currentRate = rates.poll();

            if (current.equals(to)) {
                return amount * currentRate;
            }

            for (Map.Entry<String, Double> neighbor : conversionGraph.get(current).entrySet()) {
                String nextCurrency = neighbor.getKey();
                if (!visited.contains(nextCurrency)) {
                    visited.add(nextCurrency);
                    double newRate = currentRate * neighbor.getValue();
                    queue.offer(nextCurrency);
                    rates.offer(newRate);
                    conversionRates.put(nextCurrency, newRate);
                }
            }
        }

        throw new IllegalArgumentException("No conversion path found");
    }
}