package com.interview.notes.code.year.y2024.nov24.amazon.test7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CurrencyConverter1 {

    // Graph representation: currency -> (adjacent currency -> rate)
    private final Map<String, Map<String, Double>> conversionGraph;

    public CurrencyConverter1() {
        this.conversionGraph = new HashMap<>();
    }

    /**
     * Main method for testing the CurrencyConverter.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        CurrencyConverter1 converter = new CurrencyConverter1();
        try {
            // Load conversion rates from a sample file
            converter.loadConversionRates("conversion_rates.log");
        } catch (IOException e) {
            System.out.println("Error loading conversion rates: " + e.getMessage());
            return;
        }

        // Define test cases
        List<TestCase> testCases = new ArrayList<>();
        // Sample test cases
        testCases.add(new TestCase("USD", "EUR", 100, 110.0)); // Direct conversion
        testCases.add(new TestCase("EUR", "GBP", 100, 120.0)); // Direct conversion
        testCases.add(new TestCase("USD", "GBP", 100, 132.0)); // Indirect conversion via EUR
        testCases.add(new TestCase("GBP", "USD", 100, 75.75757575)); // Reverse conversion
        testCases.add(new TestCase("USD", "JPY", 100, -1)); // Non-existent conversion

        // Large data test case
        // Assuming the log file has a large number of currencies and conversion rates
        // For demonstration, we'll simulate it with a loop
        for (int i = 0; i < 1000; i++) {
            String from = "CUR" + i;
            String to = "CUR" + (i + 1);
            double rate = 1.0 + i * 0.01;
            converter.conversionGraph
                    .computeIfAbsent(from, k -> new HashMap<>())
                    .put(to, rate);
            converter.conversionGraph
                    .computeIfAbsent(to, k -> new HashMap<>())
                    .put(from, 1.0 / rate);
        }
        // Adding a test case that utilizes the large data
        testCases.add(new TestCase("CUR0", "CUR1000", 1, 1.0)); // Should be reachable

        // Execute test cases
        for (TestCase testCase : testCases) {
            double result = converter.convert(testCase.fromCurrency, testCase.toCurrency, testCase.amount);
            boolean pass = false;
            if (testCase.expectedResult == -1) {
                pass = result == -1;
            } else {
                pass = Math.abs(result - testCase.expectedResult) < 1e-6;
            }
            System.out.println("Test Case: " + testCase + " => " + (pass ? "PASS" : "FAIL"));
        }
    }

    /**
     * Loads conversion rates from a file.
     *
     * @param filePath Path to the log file containing conversion rates.
     * @throws IOException If an I/O error occurs.
     */
    public void loadConversionRates(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            // Remove any leading/trailing whitespaces
            line = line.trim();
            if (line.isEmpty()) continue;

            // Parse JSON-like entries
            Map<String, String> entry = parseLogEntry(line);
            if (entry.containsKey("from") && entry.containsKey("to") && entry.containsKey("rate")) {
                String from = entry.get("from");
                String to = entry.get("to");
                double rate;
                try {
                    rate = Double.parseDouble(entry.get("rate"));
                } catch (NumberFormatException e) {
                    // Invalid rate format; skip this entry
                    continue;
                }

                // Update graph for direct conversion
                conversionGraph
                        .computeIfAbsent(from, k -> new HashMap<>())
                        .put(to, rate);

                // Optionally, add reverse conversion if needed
                // Assuming reciprocal rate is valid
                conversionGraph
                        .computeIfAbsent(to, k -> new HashMap<>())
                        .put(from, 1.0 / rate);
            }
        }
        reader.close();
    }

    /**
     * Parses a log entry string into a map.
     *
     * @param logEntry The log entry string.
     * @return A map of key-value pairs extracted from the log entry.
     */
    private Map<String, String> parseLogEntry(String logEntry) {
        Map<String, String> map = new HashMap<>();
        logEntry = logEntry.replaceAll("[{}\"]", ""); // Remove braces and quotes
        String[] pairs = logEntry.split(",");
        for (String pair : pairs) {
            String[] keyValue = pair.split(":");
            if (keyValue.length != 2) continue;
            map.put(keyValue[0].trim(), keyValue[1].trim());
        }
        return map;
    }

    /**
     * Converts an amount from one currency to another.
     *
     * @param fromCurrency The source currency code.
     * @param toCurrency   The target currency code.
     * @param amount       The amount to convert.
     * @return The converted amount, or -1 if conversion is not possible.
     */
    public double convert(String fromCurrency, String toCurrency, double amount) {
        if (!conversionGraph.containsKey(fromCurrency) || !conversionGraph.containsKey(toCurrency)) {
            return -1;
        }
        if (fromCurrency.equals(toCurrency)) {
            return amount;
        }

        // BFS to find the conversion rate
        Queue<Pair<String, Double>> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.add(new Pair<>(fromCurrency, 1.0));
        visited.add(fromCurrency);

        while (!queue.isEmpty()) {
            Pair<String, Double> current = queue.poll();
            String currentCurrency = current.getKey();
            double currentRate = current.getValue();

            Map<String, Double> neighbors = conversionGraph.get(currentCurrency);
            if (neighbors == null) continue;

            for (Map.Entry<String, Double> entry : neighbors.entrySet()) {
                String neighbor = entry.getKey();
                double rate = entry.getValue();
                double cumulativeRate = currentRate * rate;

                if (neighbor.equals(toCurrency)) {
                    return amount * cumulativeRate;
                }

                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(new Pair<>(neighbor, cumulativeRate));
                }
            }
        }

        // Conversion path not found
        return -1;
    }

    /**
     * Inner class to represent a test case.
     */
    private static class TestCase {
        String fromCurrency;
        String toCurrency;
        double amount;
        double expectedResult;

        public TestCase(String from, String to, double amt, double expected) {
            this.fromCurrency = from;
            this.toCurrency = to;
            this.amount = amt;
            this.expectedResult = expected;
        }

        @Override
        public String toString() {
            return String.format("Convert %.2f %s to %s (Expected: %.6f)", amount, fromCurrency, toCurrency, expectedResult);
        }
    }

    /**
     * Simple Pair class to hold currency and cumulative rate.
     *
     * @param <K> Type of the first element.
     * @param <V> Type of the second element.
     */
    private class Pair<K, V> {
        private final K key;
        private final V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }
}
