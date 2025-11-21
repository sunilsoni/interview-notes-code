package com.interview.notes.code.year.y2024.nov24.amazon.test7;

import java.util.*;

/*
* Given a file of currency conversion rates, write a function that converts one currency to another.
Sample log file entries:
{"from": "USD", "to": "EUR", "rate":1.1}
{"from": "EUR", "to": "GBP"
', "rate":1.2}
Means USD is worth 1.32 GBP
 */
public class CurrencyConverter {
    private final Map<String, Map<String, Double>> conversionRates = new HashMap<>();

    public static void main(String[] args) {
        CurrencyConverter converter = new CurrencyConverter();

        // Test case 1: Direct conversion
        converter.addConversionRate("USD", "EUR", 0.85);
        testConversion(converter, "USD", "EUR", 100, 85, "Test case 1");

        // Test case 2: Indirect conversion
        converter.addConversionRate("EUR", "GBP", 0.9);
        testConversion(converter, "USD", "GBP", 100, 76.5, "Test case 2");

        // Test case 3: Same currency conversion
        testConversion(converter, "USD", "USD", 100, 100, "Test case 3");

        // Test case 4: Non-existent conversion
        try {
            converter.convert("USD", "JPY", 100);
            System.out.println("Test case 4: FAIL (Exception expected)");
        } catch (IllegalArgumentException e) {
            System.out.println("Test case 4: PASS");
        }

        // Test case 5: Large data input
        for (int i = 0; i < 1000; i++) {
            converter.addConversionRate("CUR" + i, "CUR" + (i + 1), 1.1);
        }
        testConversion(converter, "CUR0", "CUR999", 100, 100 * Math.pow(1.1, 999), "Test case 5");

        // Test case 6: Cyclic conversion rates
        converter.addConversionRate("A", "B", 2);
        converter.addConversionRate("B", "C", 3);
        converter.addConversionRate("C", "A", 1.0 / 6);
        testConversion(converter, "A", "A", 100, 100, "Test case 6");
    }

    private static void testConversion(CurrencyConverter converter, String from, String to,
                                       double amount, double expected, String testName) {
        double result = converter.convert(from, to, amount);
        boolean passed = Math.abs(result - expected) < 0.0001;
        System.out.println(testName + ": " + (passed ? "PASS" : "FAIL") +
                " (Expected: " + expected + ", Got: " + result + ")");
    }

    public void addConversionRate(String from, String to, double rate) {
        conversionRates.putIfAbsent(from, new HashMap<>());
        conversionRates.get(from).put(to, rate);

        // Add reverse conversion rate
        conversionRates.putIfAbsent(to, new HashMap<>());
        conversionRates.get(to).put(from, 1 / rate);
    }

    public double convert(String from, String to, double amount) {
        if (from.equals(to)) return amount;

        Queue<String> queue = new LinkedList<>();
        Map<String, Double> rates = new HashMap<>();
        Set<String> visited = new HashSet<>();

        queue.offer(from);
        rates.put(from, 1.0);
        visited.add(from);

        while (!queue.isEmpty()) {
            String currency = queue.poll();
            if (currency.equals(to)) {
                return amount * rates.get(to);
            }

            if (conversionRates.containsKey(currency)) {
                for (Map.Entry<String, Double> entry : conversionRates.get(currency).entrySet()) {
                    String nextCurrency = entry.getKey();
                    if (!visited.contains(nextCurrency)) {
                        queue.offer(nextCurrency);
                        rates.put(nextCurrency, rates.get(currency) * entry.getValue());
                        visited.add(nextCurrency);
                    }
                }
            }
        }

        throw new IllegalArgumentException("No conversion rate found for " + from + " to " + to);
    }
}
