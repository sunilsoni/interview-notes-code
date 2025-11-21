package com.interview.notes.code.year.y2024.oct24.amazon.test13;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class CurrencyConverter {

    // Adjacency list to represent the currency conversion graph
    private final Map<String, List<ConversionRate>> graph;

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
                new ConversionRate("USD", "EUR", new BigDecimal("1.1")),
                new ConversionRate("EUR", "GBP", new BigDecimal("1.2")),
                new ConversionRate("GBP", "JPY", new BigDecimal("150.0")),
                new ConversionRate("AUD", "USD", new BigDecimal("0.7")),
                new ConversionRate("CAD", "AUD", new BigDecimal("1.05"))
                // Add more rates as needed
        );

        // Adding conversion rates to the converter
        for (ConversionRate rate : conversionRates) {
            converter.addConversion(rate.from, rate.to, rate.rate);
        }

        // Define test cases
        List<TestCase> testCases = new ArrayList<>();

        // Basic tests
        testCases.add(new TestCase("USD", "EUR", new BigDecimal("1.1"), "Direct conversion USD to EUR"));
        testCases.add(new TestCase("EUR", "GBP", new BigDecimal("1.2"), "Direct conversion EUR to GBP"));
        testCases.add(new TestCase("USD", "GBP", new BigDecimal("1.32"), "Indirect conversion USD -> EUR -> GBP"));
        testCases.add(new TestCase("GBP", "USD", BigDecimal.ONE.divide(new BigDecimal("1.32"), MathContext.DECIMAL128), "Reverse indirect conversion GBP -> EUR -> USD"));
        testCases.add(new TestCase("USD", "JPY", new BigDecimal("1.32").multiply(new BigDecimal("150.0")), "Indirect conversion USD -> EUR -> GBP -> JPY"));
        testCases.add(new TestCase("AUD", "GBP", new BigDecimal("0.7").multiply(new BigDecimal("1.1")).multiply(new BigDecimal("1.2")), "Indirect conversion AUD -> USD -> EUR -> GBP"));
        testCases.add(new TestCase("CAD", "JPY", new BigDecimal("1.05").multiply(new BigDecimal("0.7")).multiply(new BigDecimal("1.1")).multiply(new BigDecimal("1.2")).multiply(new BigDecimal("150.0")), "Indirect conversion CAD -> AUD -> USD -> EUR -> GBP -> JPY"));

        // Edge cases
        testCases.add(new TestCase("USD", "USD", BigDecimal.ONE, "Same currency conversion"));
        testCases.add(new TestCase("USD", "INR", null, "Non-existent target currency"));
        testCases.add(new TestCase("INR", "USD", null, "Non-existent source currency"));
        testCases.add(new TestCase("AUD", "INR", null, "No conversion path available"));

        // Performance test with large data
        // Generating a chain of currencies: CUR0 -> CUR1 -> CUR2 -> ... -> CUR9999
        int largeDataSize = 10000;
        for (int i = 0; i < largeDataSize - 1; i++) {
            converter.addConversion("CUR" + i, "CUR" + (i + 1), new BigDecimal("1.01"));
        }
        // Add conversion from CUR9999 to CURFinal with rate 1.05
        converter.addConversion("CUR9999", "CURFinal", new BigDecimal("1.05"));
        // Adding a test case for large data
        // Expected rate: 1.01^(9999) * 1.05
        BigDecimal expectedLargeRate = BigDecimal.ONE;
        for (int i = 0; i < largeDataSize - 1; i++) {
            expectedLargeRate = expectedLargeRate.multiply(new BigDecimal("1.01"), MathContext.DECIMAL128);
        }
        expectedLargeRate = expectedLargeRate.multiply(new BigDecimal("1.05"), MathContext.DECIMAL128);
        testCases.add(new TestCase("CUR0", "CURFinal", expectedLargeRate, "Large data conversion from CUR0 to CURFinal"));

        // Execute test cases
        int passed = 0;
        int failed = 0;
        for (TestCase testCase : testCases) {
            BigDecimal result = converter.convert(testCase.fromCurrency, testCase.toCurrency);
            boolean isPass;
            if (testCase.expectedRate == null) {
                isPass = (result == null);
            } else {
                // Define a tolerance based on the magnitude of the expected rate
                BigDecimal tolerance = testCase.expectedRate.multiply(new BigDecimal("1e-12")); // Adjust as needed
                isPass = (result != null) && (result.subtract(testCase.expectedRate).abs().compareTo(tolerance) < 0);
            }

            if (isPass) {
                passed++;
                System.out.println("[PASS] " + testCase.description);
            } else {
                failed++;
                String expectedStr = (testCase.expectedRate != null) ? testCase.expectedRate.toPlainString() : "null";
                String gotStr = (result != null) ? result.toPlainString() : "null";
                System.out.println("[FAIL] " + testCase.description + " | Expected: " + expectedStr + ", Got: " + gotStr);
            }

            // For the large data test, measure performance
            if (testCase.description.startsWith("Large data")) {
                long startTime = System.nanoTime();
                BigDecimal rate = converter.convert(testCase.fromCurrency, testCase.toCurrency);
                long endTime = System.nanoTime();
                long durationInMillis = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
                System.out.println("    Conversion Rate: " + ((rate != null) ? rate.toPlainString() : "null"));
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
    public void addConversion(String from, String to, BigDecimal rate) {
        // Add forward rate
        ConversionRate forward = new ConversionRate(from, to, rate);
        graph.computeIfAbsent(from, k -> new ArrayList<>()).add(forward);

        // Add reverse rate
        if (rate.compareTo(BigDecimal.ZERO) != 0) { // Avoid division by zero
            BigDecimal reverseRate = BigDecimal.ONE.divide(rate, MathContext.DECIMAL128);
            ConversionRate reverse = new ConversionRate(to, from, reverseRate);
            graph.computeIfAbsent(to, k -> new ArrayList<>()).add(reverse);
        }
    }

    /**
     * Converts an amount from one currency to another.
     *
     * @param fromCurrency The source currency code.
     * @param toCurrency   The target currency code.
     * @return The converted amount as BigDecimal, or null if conversion is not possible.
     */
    public BigDecimal convert(String fromCurrency, String toCurrency) {
        if (!graph.containsKey(fromCurrency) || !graph.containsKey(toCurrency)) {
            return null; // One of the currencies does not exist
        }

        if (fromCurrency.equals(toCurrency)) {
            return BigDecimal.ONE; // Conversion rate is 1 if currencies are the same
        }

        // BFS to find the conversion rate
        Queue<String> queue = new LinkedList<>();
        Queue<BigDecimal> ratesQueue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.add(fromCurrency);
        ratesQueue.add(BigDecimal.ONE);
        visited.add(fromCurrency);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            BigDecimal currentRate = ratesQueue.poll();

            List<ConversionRate> neighbors = graph.getOrDefault(current, new ArrayList<>());
            for (ConversionRate neighbor : neighbors) {
                if (visited.contains(neighbor.to)) {
                    continue;
                }

                BigDecimal accumulatedRate = currentRate.multiply(neighbor.rate, MathContext.DECIMAL128);

                if (neighbor.to.equals(toCurrency)) {
                    return accumulatedRate;
                }

                queue.add(neighbor.to);
                ratesQueue.add(accumulatedRate);
                visited.add(neighbor.to);
            }
        }

        return null; // Conversion path not found
    }

    /**
     * Represents a conversion rate from one currency to another.
     */
    static class ConversionRate {
        String from;
        String to;
        BigDecimal rate;

        public ConversionRate(String from, String to, BigDecimal rate) {
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
        BigDecimal expectedRate;
        String description;

        public TestCase(String from, String to, BigDecimal expected, String desc) {
            this.fromCurrency = from;
            this.toCurrency = to;
            this.expectedRate = expected;
            this.description = desc;
        }
    }
}