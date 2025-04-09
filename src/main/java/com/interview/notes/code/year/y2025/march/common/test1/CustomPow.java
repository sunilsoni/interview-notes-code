package com.interview.notes.code.year.y2025.march.common.test1;

public class CustomPow {

    // Custom power method without using Math.pow
    public static double customPow(double base, double exponent) {
        // If exponent is zero, return 1
        if (exponent == 0) {
            return 1;
        }

        // Check if exponent is an integer (within a tolerance)
        if (exponent == (int) exponent) {
            int exp = (int) exponent;
            // For negative integer exponent, compute positive exponent and then take reciprocal
            if (exp < 0) {
                return 1.0 / customPow(base, -exp);
            }
            // Fast exponentiation for positive integer exponent
            double result = 1;
            while (exp > 0) {
                if ((exp & 1) == 1) {
                    result *= base;
                }
                base *= base;
                exp >>= 1;
            }
            return result;
        } else {
            // For fractional exponent, use exp(log(base) * exponent)
            // This works for positive base only. For negative base and non-integer exponent, result is undefined.
            return Math.exp(exponent * Math.log(base));
        }
    }

    // Main method for testing the customPow method
    public static void main(String[] args) {
        boolean allPassed = true;

        // Test cases
        allPassed &= testCase(2, 3, 8);
        allPassed &= testCase(5, 2, 25);
        allPassed &= testCase(3, 4, 81);
        allPassed &= testCase(10, 3, 1000);
        allPassed &= testCase(7, 0, 1);
        allPassed &= testCase(4, 0.5, 2);
        allPassed &= testCase(-2, 3, -8);
        allPassed &= testCase(2, -2, 0.25);
        allPassed &= testCase(9, 0.5, 3);
        allPassed &= testCase(2.5, 2, 6.25);

        // Additional test cases for edge scenarios
        allPassed &= testCase(0, 5, 0);
        allPassed &= testCase(5, 0, 1);
        // For large exponent
        allPassed &= testCase(1.0001, 10000, Math.exp(10000 * Math.log(1.0001)));

        System.out.println(allPassed ? "All tests PASS" : "Some tests FAIL");
    }

    // Helper method for testing a single case
    private static boolean testCase(double base, double exponent, double expected) {
        double result = customPow(base, exponent);
        // Tolerance for floating point comparison
        double tolerance = 1e-9;
        boolean pass = Math.abs(result - expected) < tolerance;
        System.out.println("customPow(" + base + ", " + exponent + ") = " + result
                + " | Expected: " + expected + " | " + (pass ? "PASS" : "FAIL"));
        return pass;
    }
}
