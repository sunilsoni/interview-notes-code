package com.interview.notes.code.year.y2025.June.common.test16;

/**
 * A simple Calculator class that performs basic arithmetic operations
 * Version: Java 8
 * Author: Developer
 * Purpose: Demonstrate basic arithmetic operations with proper testing
 */
public class Calculator {

    // Class level variables to store calculation history
    private double lastResult;

    /**
     * Main method to test calculator functionality
     * Includes various test cases including edge cases
     */
    public static void main(String[] args) {
        // Create calculator instance
        Calculator calc = new Calculator();

        // Test Case 1: Basic addition
        double result1 = calc.add(5, 3);
        System.out.println("Test 1 - Basic Addition: " +
                (result1 == 8 ? "PASS" : "FAIL"));

        // Test Case 2: Adding negative numbers
        double result2 = calc.add(-5, -3);
        System.out.println("Test 2 - Negative Addition: " +
                (result2 == -8 ? "PASS" : "FAIL"));

        // Test Case 3: Basic subtraction
        double result3 = calc.subtract(10, 4);
        System.out.println("Test 3 - Basic Subtraction: " +
                (result3 == 6 ? "PASS" : "FAIL"));

        // Test Case 4: Large numbers (edge case)
        double result4 = calc.add(Double.MAX_VALUE / 2, Double.MAX_VALUE / 2);
        System.out.println("Test 4 - Large Numbers: " +
                (!Double.isInfinite(result4) ? "PASS" : "FAIL"));

        // Test Case 5: Zero handling
        double result5 = calc.subtract(5, 0);
        System.out.println("Test 5 - Zero Handling: " +
                (result5 == 5 ? "PASS" : "FAIL"));
    }

    /**
     * Adds two numbers and stores the result
     *
     * @param a first number
     * @param b second number
     * @return sum of two numbers
     */
    public double add(double a, double b) {
        // Store result for history tracking
        lastResult = a + b;
        return lastResult;
    }

    /**
     * Subtracts second number from first number
     *
     * @param a first number (minuend)
     * @param b second number (subtrahend)
     * @return difference between numbers
     */
    public double subtract(double a, double b) {
        // Store result for history tracking
        lastResult = a - b;
        return lastResult;
    }
}
