package com.interview.notes.code.year.y2025.march.common.test16;


import java.util.HashMap;
import java.util.Map;

/*

**Problem Name: "Decimal Conversion"**
(Please do not remove this line)

### Instructions to Candidate:
1. Run this code in the REPL to observe its behavior. The execution entry point is `main()`.
2. Consider adding some additional tests in `doTestsPass()`.
3. Implement `vulgarToDecimal()` correctly.
4. If time permits, try to improve your implementation.

---

### Given Task:
Implement a method `vulgarToDecimal(long numerator, long denominator)` that takes a fraction as input and returns its decimal representation as a string.

### Additional Details:
- Some fractions in decimal form have cyclic (repeating) decimal points.
- Examples:
  - `1/2 = 0.5`
  - `1/3 = 0.(3)` (repeating 3)

---

 */
public class Solution {

    /**
     * Converts a fraction to its decimal representation, properly handling
     * repeating decimals by enclosing them in parentheses.
     *
     * @param numerator   The top number in the fraction
     * @param denominator The bottom number in the fraction
     * @return String representation of the decimal value
     */
    public static String vulgarToDecimal(long numerator, long denominator) {
        // Handle division by zero
        if (denominator == 0) {
            return "Undefined";
        }

        // Handle zero numerator
        if (numerator == 0) {
            return "0";
        }

        StringBuilder result = new StringBuilder();

        // Handle negative numbers
        boolean isNegative = (numerator < 0 && denominator > 0) || (numerator > 0 && denominator < 0);
        if (isNegative) {
            result.append("-");
        }

        // Work with absolute values
        numerator = Math.abs(numerator);
        denominator = Math.abs(denominator);

        // Add integer part
        long integerPart = numerator / denominator;
        result.append(integerPart);

        // Process fractional part
        long remainder = numerator % denominator;

        if (remainder == 0) {
            return result.toString(); // No fractional part
        }

        result.append(".");

        // Map to track remainders and their positions
        Map<Long, Integer> remainderPositions = new HashMap<>();

        // Start the fractional part calculation
        StringBuilder fractionBuilder = new StringBuilder();
        int position = 0;

        while (remainder != 0 && !remainderPositions.containsKey(remainder)) {
            remainderPositions.put(remainder, position);

            remainder *= 10;
            fractionBuilder.append(remainder / denominator);
            remainder %= denominator;
            position++;
        }

        // If we found a repeating sequence
        if (remainder != 0) {
            int repeatStart = remainderPositions.get(remainder);
            result.append(fractionBuilder, 0, repeatStart);
            result.append("(");
            result.append(fractionBuilder.substring(repeatStart));
            result.append(")");
        } else {
            // No repeating sequence
            result.append(fractionBuilder);
        }

        return result.toString();
    }

    /**
     * Tests the vulgarToDecimal method with various cases.
     * Returns true if all tests pass, otherwise false.
     */
    public static boolean doTestsPass() {
        boolean testsPassed = true;

        // Original test cases
        testsPassed &= vulgarToDecimal(11, 21).equals("0.5(238095)") && check("11/21", "0.5(238095)");
        testsPassed &= vulgarToDecimal(1, 3).equals("0.(3)") && check("1/3", "0.(3)");

        // Additional test cases
        testsPassed &= vulgarToDecimal(1, 2).equals("0.5") && check("1/2", "0.5");
        testsPassed &= vulgarToDecimal(4, 3).equals("1.(3)") && check("4/3", "1.(3)");
        testsPassed &= vulgarToDecimal(0, 5).equals("0") && check("0/5", "0");
        testsPassed &= vulgarToDecimal(-1, 4).equals("-0.25") && check("-1/4", "-0.25");
        testsPassed &= vulgarToDecimal(1, -4).equals("-0.25") && check("1/-4", "-0.25");
        testsPassed &= vulgarToDecimal(22, 7).equals("3.(142857)") && check("22/7", "3.(142857)");
        testsPassed &= vulgarToDecimal(1, 6).equals("0.1(6)") && check("1/6", "0.1(6)");
        testsPassed &= vulgarToDecimal(1, 11).equals("0.(09)") && check("1/11", "0.(09)");
        testsPassed &= vulgarToDecimal(1, 97).equals("0.(010309278350515463917525773195876288659793814432989690721649484536082474226804123711340206185567)") && check("1/97", "0.(0103...)");

        // Edge cases
        testsPassed &= vulgarToDecimal(0, 1).equals("0") && check("0/1", "0");
        testsPassed &= vulgarToDecimal(5, 1).equals("5") && check("5/1", "5");

        if (testsPassed) {
            System.out.println("All tests passed!");
        } else {
            System.out.println("Some tests failed!");
        }
        return testsPassed;
    }

    /**
     * Helper method to print test results
     */
    private static boolean check(String fraction, String expected) {
        String actual = vulgarToDecimal(parseFraction(fraction));
        boolean pass = actual.equals(expected) ||
                (expected.length() > 10 && expected.endsWith("...") &&
                        actual.startsWith(expected.substring(0, expected.length() - 3)));
        System.out.println(fraction + " = " + actual + " (" + (pass ? "PASS" : "FAIL") + ")");
        return pass;
    }

    /**
     * Helper to parse a fraction string like "1/3"
     */
    private static long[] parseFraction(String fraction) {
        String[] parts = fraction.split("/");
        return new long[]{Long.parseLong(parts[0]), Long.parseLong(parts[1])};
    }

    /**
     * Overloaded method to accept fraction as an array
     */
    private static String vulgarToDecimal(long[] fraction) {
        return vulgarToDecimal(fraction[0], fraction[1]);
    }

    public static void main(String[] args) {
        doTestsPass();
    }
}
