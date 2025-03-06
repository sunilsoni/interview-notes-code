package com.interview.notes.code.year.y2025.march.common.test6;

public class StockPrediction {
    // Class attributes
    private double gain;
    private double stockPrice;

    // Constructor
    public StockPrediction(double gain, double stockPrice) {
        this.gain = gain;
        this.stockPrice = stockPrice;
    }

    // Main method for testing
    public static void main(String[] args) {
        // Test cases
        testCase(60, 120, 7); // Sample case 0
        testCase(5, 3, 4);    // Sample case 1

        // Additional test cases
        testCase(100, 200, 15);
        testCase(0, 50, 10);
        testCase(-10, 30, 5);

        // Edge cases
        testCase(Integer.MAX_VALUE, 100, 50); // Very large gain
        testCase(50, Integer.MAX_VALUE, 100); // Very large stock price
        testCase(50, 100, Integer.MAX_VALUE); // Very large transaction costs
    }

    // Helper method to run and validate test cases
    private static void testCase(double gain, double stockPrice, int transactionCosts) {
        StockPrediction prediction = new StockPrediction(gain, stockPrice);

        // Calculate using all three methods
        double defaultValue = prediction.expectedValue();
        double intOverloadValue = prediction.expectedValue(transactionCosts);
        double stringOverloadValue = prediction.expectedValue(String.valueOf(transactionCosts));

        // Print results
        System.out.println("Test Case: gain=" + gain + ", stockPrice=" + stockPrice +
                ", transactionCosts=" + transactionCosts);
        System.out.println("Default: " + defaultValue);
        System.out.println("Int Overload: " + intOverloadValue);
        System.out.println("String Overload: " + stringOverloadValue);

        // Validate results
        boolean passed = (defaultValue == stockPrice + gain) &&
                (intOverloadValue == defaultValue - transactionCosts) &&
                (stringOverloadValue == intOverloadValue);

        System.out.println("Test Result: " + (passed ? "PASS" : "FAIL"));
        System.out.println("Expected Output: " + defaultValue + " " +
                intOverloadValue + " " + stringOverloadValue);
        System.out.println();
    }

    // Default implementation of expectedValue
    public double expectedValue() {
        // Calculate expected value as stockPrice + gain
        return stockPrice + gain;
    }

    // First overload: takes integer transactionCosts
    public double expectedValue(int transactionCosts) {
        // Calculate expected value as (stockPrice + gain) - transactionCosts
        return (stockPrice + gain) - transactionCosts;
    }

    // Second overload: takes string transactionCosts
    public double expectedValue(String transactionCosts) {
        // Convert string to integer and then calculate
        try {
            int costs = Integer.parseInt(transactionCosts);
            return (stockPrice + gain) - costs;
        } catch (NumberFormatException e) {
            // Handle invalid string input
            System.out.println("Invalid transaction costs format: " + transactionCosts);
            return expectedValue(); // Return default calculation if conversion fails
        }
    }
}