package com.interview.notes.code.year.y2025.feb25.common.test4;

public class SimpleCalculator {

    // Calculator operations
    public static double add(double a, double b) {
        return a + b;
    }

    public static double subtract(double a, double b) {
        return a - b;
    }

    public static double multiply(double a, double b) {
        return a * b;
    }

    public static double divide(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Division by zero is not allowed");
        }
        return a / b;
    }

    // Main method with test cases
    public static void main(String[] args) {
        // Test cases
        runTest("Addition Test 1", 5, 3, "add", 8d);
        runTest("Addition Test 2", -5, 3, "add", -2d);
        runTest("Subtraction Test", 10, 4, "subtract", 6d);
        runTest("Multiplication Test", 6, 3, "multiply", 18d);
        runTest("Division Test", 15, 3, "divide", 5d);

        // Edge cases
        runTest("Large Numbers", 1000000, 2000000, "add", 3000000d);
        runTest("Zero Division Test", 5, 0, "divide", null);
        runTest("Decimal Numbers", 3.5, 1.5, "multiply", 5.25);
    }

    // Test runner
    private static void runTest(String testName, double a, double b,
                                String operation, Double expectedResult) {
        try {
            double result;
            switch (operation) {
                case "add":
                    result = add(a, b);
                    break;
                case "subtract":
                    result = subtract(a, b);
                    break;
                case "multiply":
                    result = multiply(a, b);
                    break;
                case "divide":
                    result = divide(a, b);
                    break;
                default:
                    System.out.println(testName + ": FAIL - Invalid operation");
                    return;
            }

            if (expectedResult == null) {
                System.out.println(testName + ": FAIL - Expected exception");
                return;
            }

            boolean passed = Math.abs(result - expectedResult) < 0.0001;
            System.out.println(testName + ": " + (passed ? "PASS" : "FAIL") +
                    " (Got: " + result + ", Expected: " + expectedResult + ")");

        } catch (ArithmeticException e) {
            if (expectedResult == null) {
                System.out.println(testName + ": PASS - Expected exception caught");
            } else {
                System.out.println(testName + ": FAIL - Unexpected exception");
            }
        }
    }
}
