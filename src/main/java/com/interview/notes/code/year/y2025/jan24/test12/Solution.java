package com.interview.notes.code.year.y2025.jan24.test12;
/*

Decimal Conversion
/* Problem Name is &&& Decimal Conversion &&& PLEASE DO NOT REMOVE THIS LINE. */

/**
 * Instructions to candidate.
 * 1) Run this code in the REPL to observe its behavior. The execution entry point is main().
 * 2) Consider adding some additional tests in doTestsPass().
 * 3) Implement vulgarToDecimal() correctly.
 * 4) If time permits, try to improve your implementation.
 */
public class Solution {
    public static void main(String[] args) {
        doTestsPass();
    }

    /**
     * Implement the method that provided numerator and denominator will return a string
     * representing fraction's decimal form.
     * Some fractions in decimal form have cyclic decimal points.
     * <p>
     * Examples:
     * 1/2 = 0.5
     * 1/3 = 0.(3)
     */
    public static String vulgarToDecimal(long numerator, long denominator) {
        if (denominator == 0) return "NaN"; // avoid division by zero

        StringBuilder result = new StringBuilder();

        // Handle negative numbers.
        if ((numerator < 0) ^ (denominator < 0)) {
            result.append("-");
        }

        // Convert to positive values.
        numerator = Math.abs(numerator);
        denominator = Math.abs(denominator);

        // Append integer part.
        long integerPart = numerator / denominator;
        result.append(integerPart);

        long remainder = numerator % denominator;
        if (remainder == 0) {
            return result.toString();
        }

        result.append(".");

        // Map to store remainder and its index in result.
        java.util.Map<Long, Integer> remainderIndexMap = new java.util.HashMap<>();

        while (remainder != 0) {
            if (remainderIndexMap.containsKey(remainder)) {
                int index = remainderIndexMap.get(remainder);
                result.insert(index, "(");
                result.append(")");
                break;
            }
            remainderIndexMap.put(remainder, result.length());
            remainder *= 10;
            result.append(remainder / denominator);
            remainder %= denominator;
        }
        return result.toString();
    }

    public static boolean doTestsPass() {
        boolean testsPassed = true;

        // Provided test cases
        testsPassed &= vulgarToDecimal(1, 2).equals("0.5");
        testsPassed &= vulgarToDecimal(1, 3).equals("0.(3)");

        // Additional tests
        testsPassed &= vulgarToDecimal(2, 4).equals("0.5");
        testsPassed &= vulgarToDecimal(4, 2).equals("2");
        testsPassed &= vulgarToDecimal(22, 7).equals("3.(142857)");
        testsPassed &= vulgarToDecimal(-50, 8).equals("-6.25");
        testsPassed &= vulgarToDecimal(0, 3).equals("0");
        testsPassed &= vulgarToDecimal(1, 6).equals("0.1(6)");
        testsPassed &= vulgarToDecimal(1, 7).equals("0.(142857)");
        testsPassed &= vulgarToDecimal(334334, 1000000).equals("0.334334");

        // testsPassed &= vulgarToDecimal (334334, 1000000).equals ("0.(334)");
        // Edge case: denominator is zero.
        testsPassed &= vulgarToDecimal(1, 0).equals("NaN");

        // Large input test (simulate recurring length)
        // Use a large numerator and denominator without recurrence complexity
        // This just tests performance on large numbers.
        testsPassed &= vulgarToDecimal(123456789, 1).equals("123456789");

        if (testsPassed) {
            System.out.println("Tests passed");
        } else {
            System.out.println("Tests failed");
        }

        return testsPassed;
    }
}
