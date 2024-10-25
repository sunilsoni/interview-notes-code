package com.interview.notes.code.months.oct24.amz.test12;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * CurrencyConverter is responsible for storing currency conversion rates and
 * providing functionality to convert one currency to another.
 */
public class CurrencyConverter {

    // Adjacency list to represent the currency conversion graph
    private Map<String, List<ConversionRate>> graph;

    /**
     * Initializes the CurrencyConverter with an empty conversion graph.
     */
    public CurrencyConverter() {
        this.graph = new HashMap<>();
    }

    /**
     * Main method to execute test cases and demonstrate the CurrencyConverter functionality.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        CurrencyConverter converter = new CurrencyConverter();

        // Sample conversion rates
        List<ConversionRate> conversionRates = Arrays.asList(
                new ConversionRate("USD", "EUR", 1.1),
                new ConversionRate("EUR", "GBP", 1.2),
                new ConversionRate("GBP", "JPY", 150.0),
                new ConversionRate("AUD", "USD", 0.7),
                new ConversionRate("CAD", "AUD", 1.05)
                // Add more rates as needed
        );

        // Adding conversion rates to the converter
        for (ConversionRate rate : conversionRates) {
            converter.addConversion(rate.from, rate.to, rate.rate);
        }

        // Define test cases
        List<TestCase> testCases = new ArrayList<>();

        // Basic tests
        testCases.add(new TestCase("USD", "EUR", 1.1, "Direct conversion USD to EUR"));
        testCases.add(new TestCase("EUR", "GBP", 1.2, "Direct conversion EUR to GBP"));
        testCases.add(new TestCase("USD", "GBP", 1.32, "Indirect conversion USD -> EUR -> GBP"));
        testCases.add(new TestCase("GBP", "USD", 1.0 / 1.32, "Reverse indirect conversion GBP -> EUR -> USD"));
        testCases.add(new TestCase("USD", "JPY", 1.32 * 150.0, "Indirect conversion USD -> EUR -> GBP -> JPY"));
        testCases.add(new TestCase("AUD", "GBP", 0.7 * 1.1 * 1.2, "Indirect conversion AUD -> USD -> EUR -> GBP"));
        testCases.add(new TestCase("CAD", "JPY", 1.05 * 0.7 * 1.1 * 1.2 * 150.0, "Indirect conversion CAD -> AUD -> USD -> EUR -> GBP -> JPY"));

        // Edge cases
        testCases.add(new TestCase("USD", "USD", 1.0, "Same currency conversion"));
        testCases.add(new TestCase("USD", "INR", -1, "Non-existent target currency"));
        testCases.add(new TestCase("INR", "USD", -1, "Non-existent source currency"));
        testCases.add(new TestCase("AUD", "INR", -1, "No conversion path available"));

        // Performance test with large data
        // Generating a chain of currencies: CUR0 -> CUR1 -> CUR2 -> ... -> CUR9999
        int largeDataSize = 10000;
        for (int i = 0; i < largeDataSize - 1; i++) {
            converter.addConversion("CUR" + i, "CUR" + (i + 1), 1.01);
        }
        // Add conversion from CUR9999 to CURFinal with rate 1.05
        converter.addConversion("CUR9999", "CURFinal", 1.05);
        // Adding a test case for large data
        // Expected rate: 1.01^(9999) * 1.05
        double expectedLargeRate = Math.pow(1.01, largeDataSize - 1) * 1.05;
        testCases.add(new TestCase("CUR0", "CURFinal", expectedLargeRate, "Large data conversion from CUR0 to CURFinal"));

        // Execute test cases
        int passed = 0;
        int failed = 0;
        for (TestCase testCase : testCases) {
            double result = converter.convert(testCase.fromCurrency, testCase.toCurrency);
            boolean isPass;
            if (testCase.expectedRate < 0) {
                isPass = (result == -1);
            } else {
                // Allow a small epsilon for floating-point comparisons
                double epsilon = 1e-6;
                isPass = Math.abs(result - testCase.expectedRate) < epsilon;
            }

            if (isPass) {
                passed++;
                System.out.println("[PASS] " + testCase.description);
            } else {
                failed++;
                System.out.println("[FAIL] " + testCase.description + " | Expected: " + testCase.expectedRate + ", Got: " + result);
            }

            // For the large data test, measure performance
            if (testCase.description.startsWith("Large data")) {
                long startTime = System.nanoTime();
                double rate = converter.convert(testCase.fromCurrency, testCase.toCurrency);
                long endTime = System.nanoTime();
                long durationInMillis = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
                System.out.println("    Conversion Rate: " + rate);
                System.out.println("    Time Taken: " + durationInMillis + " ms");
            }
        }

        // Summary
        System.out.println("\nTest Summary:");
        System.out.println("Total Test Cases: " + testCases.size());
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failed);
    }

    /**
     * Adds a conversion rate to the graph. Also adds the reverse conversion rate.
     *
     * @param from The source currency code.
     * @param to   The target currency code.
     * @param rate The conversion rate from source to target.
     */
    public void addConversion(String from, String to, double rate) {
        // Add forward rate
        ConversionRate forward = new ConversionRate(from, to, rate);
        graph.computeIfAbsent(from, k -> new ArrayList<>()).add(forward);

        // Add reverse rate
        if (rate != 0) { // Avoid division by zero
            ConversionRate reverse = new ConversionRate(to, from, 1.0 / rate);
            graph.computeIfAbsent(to, k -> new ArrayList<>()).add(reverse);
        }
    }

    /**
     * Converts an amount from one currency to another.
     *
     * @param fromCurrency The source currency code.
     * @param toCurrency   The target currency code.
     * @return The converted amount, or -1 if conversion is not possible.
     */
    public double convert(String fromCurrency, String toCurrency) {
        if (!graph.containsKey(fromCurrency) || !graph.containsKey(toCurrency)) {
            return -1; // One of the currencies does not exist
        }

        if (fromCurrency.equals(toCurrency)) {
            return 1.0; // Conversion rate is 1 if currencies are the same
        }

        // BFS to find the conversion rate
        Queue<String> queue = new LinkedList<>();
        Queue<Double> ratesQueue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.add(fromCurrency);
        ratesQueue.add(1.0);
        visited.add(fromCurrency);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            double currentRate = ratesQueue.poll();

            List<ConversionRate> neighbors = graph.getOrDefault(current, new ArrayList<>());
            for (ConversionRate neighbor : neighbors) {
                if (visited.contains(neighbor.to)) {
                    continue;
                }

                double accumulatedRate = currentRate * neighbor.rate;

                if (neighbor.to.equals(toCurrency)) {
                    return accumulatedRate;
                }

                queue.add(neighbor.to);
                ratesQueue.add(accumulatedRate);
                visited.add(neighbor.to);
            }
        }

        return -1; // Conversion path not found
    }

    /**
     * Represents a conversion rate from one currency to another.
     */
    static class ConversionRate {
        String from;
        String to;
        double rate;

        public ConversionRate(String from, String to, double rate) {
            this.from = from;
            this.to = to;
            this.rate = rate;
        }
    }

    /**
     * Represents a test case with input currencies, expected result, and description.
     */
    static class TestCase {
        String fromCurrency;
        String toCurrency;
        double expectedRate;
        String description;

        public TestCase(String from, String to, double expected, String desc) {
            this.fromCurrency = from;
            this.toCurrency = to;
            this.expectedRate = expected;
            this.description = desc;
        }
    }
}