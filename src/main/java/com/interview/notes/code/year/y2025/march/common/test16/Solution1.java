package com.interview.notes.code.year.y2025.march.common.test16;

import java.util.HashMap;
import java.util.Map;

public class Solution1 {

    /**
     * Converts a fraction (numerator/denominator) into its decimal representation.
     * If the decimal repeats, the repeating part is enclosed in parentheses.
     *
     * @param numerator   the numerator of the fraction
     * @param denominator the denominator of the fraction
     * @return the decimal representation as a String
     */
    public static String vulgarToDecimal(long numerator, long denominator) {
        // Handle division by zero.
        if (denominator == 0) {
            throw new ArithmeticException("Division by zero is undefined.");
        }

        // Create a StringBuilder to build the result.
        StringBuilder result = new StringBuilder();

        // If the numerator is zero, the result is "0".
        if (numerator == 0) {
            return "0";
        }

        // Determine the sign. A fraction is negative if either (but not both) numerator or denominator is negative.
        boolean negative = (numerator < 0) ^ (denominator < 0);
        if (negative) {
            result.append("-");
        }

        // Work with absolute values to simplify further calculations.
        long num = Math.abs(numerator);
        long den = Math.abs(denominator);

        // Append the integer part.
        long integerPart = num / den;
        result.append(integerPart);

        // Calculate the initial remainder.
        long remainder = num % den;
        if (remainder == 0) {
            // If there is no remainder, we have a terminating decimal.
            return result.toString();
        }

        // Append the decimal point.
        result.append(".");

        // A map to store seen remainders and their corresponding positions in the result.
        Map<Long, Integer> remainderPositions = new HashMap<>();

        // Process the fractional part using long division.
        while (remainder != 0) {
            // If the remainder has been seen before, we have a repeating sequence.
            if (remainderPositions.containsKey(remainder)) {
                // Insert an opening parenthesis at the position where this remainder first occurred.
                result.insert(remainderPositions.get(remainder), "(");
                // Append a closing parenthesis to mark the end of the repeating sequence.
                result.append(")");
                break;
            }
            // Record the current position of the remainder.
            remainderPositions.put(remainder, result.length());

            // Multiply the remainder by 10 to get the next digit.
            remainder *= 10;
            long nextDigit = remainder / den;
            result.append(nextDigit);
            // Update remainder.
            remainder %= den;
        }

        return result.toString();
    }

    /**
     * The method doTestsPass() runs several test cases to validate the solution.
     * It prints whether the tests passed or failed.
     * <p>
     * We have improved the tests by including:
     * - Terminating decimals (e.g., 1/2, 4/2).
     * - Repeating decimals (e.g., 1/3, 22/7).
     * - Negative fractions (e.g., -1/2, 1/-2).
     * - Edge cases (zero numerator, large numbers).
     *
     * @return true if all tests pass, false otherwise.
     */
    public static boolean doTestsPass() {
        boolean testsPassed = true;

        // Terminating decimals.
        testsPassed &= vulgarToDecimal(1, 2).equals("0.5");           // 0.5
        testsPassed &= vulgarToDecimal(4, 2).equals("2");               // Integer result
        testsPassed &= vulgarToDecimal(0, 3).equals("0");               // Zero numerator

        // Repeating decimals.
        testsPassed &= vulgarToDecimal(1, 3).equals("0.(3)");           // Single-digit repeat
        testsPassed &= vulgarToDecimal(22, 7).equals("3.(142857)");      // Longer repeat

        // Negative fractions.
        testsPassed &= vulgarToDecimal(-1, 2).equals("-0.5");
        testsPassed &= vulgarToDecimal(1, -2).equals("-0.5");
        testsPassed &= vulgarToDecimal(-50, -8).equals("6.25");          // Two negatives yield a positive

        // Large number test.
        testsPassed &= vulgarToDecimal(123456789, 10000).startsWith("12345."); // Check prefix of result

        // Output test result.
        if (testsPassed) {
            System.out.println("Tests passed");
        } else {
            System.out.println("Tests failed");
        }
        return testsPassed;
    }

    /**
     * Main method that serves as the entry point for execution.
     * It calls doTestsPass() to verify that the solution works for various cases.
     */
    public static void main(String[] args) {
        doTestsPass();
    }
}