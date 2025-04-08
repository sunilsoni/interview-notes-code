package com.interview.notes.code.year.y2025.april.caspex.test1;

import java.math.BigInteger;
import java.util.Arrays;

public class BinaryAdditionSolution {
    /*
     1. Use Java8+/Stream API Version
     2. Problem Analysis:
        - We need to add two binary strings and return the sum as a binary string.
        - Input strings consist only of '0' and '1'.
        - No leading zeros (except the case of a single '0').
     3. Solution Design:
        - Convert the binary strings to BigInteger.
        - Add the BigIntegers.
        - Convert the sum back to a binary string.
     4. Implementation:
        - Implement solve(String a, String b) returning the binary sum.
     5. Testing:
        - Use a simple main method with test cases (no JUnit).
        - Print pass/fail for each test.
        - Include an additional large-data test to ensure correctness and performance.
     6. Code Review:
        - Code is concise, readable, and follows best practices.
        - Avoided any non-Java8 libraries and frameworks.
    */

    // The required method/function
    public static String solve(String a, String b) {
        BigInteger num1 = new BigInteger(a, 2);
        BigInteger num2 = new BigInteger(b, 2);
        BigInteger sum = num1.add(num2);
        return sum.toString(2);
    }

    // Simple main method to test pass/fail
    public static void main(String[] args) {
        // Test cases: {inputA, inputB, expectedOutput}
        String[][] testCases = {
            {"11",   "1",    "100"},   // Example #1
            {"1010", "1011", "10101"}, // Example #2
            {"0",    "0",    "0"},
            {"1",    "0",    "1"},
            {"101",  "101",  "1010"}
        };

        // Check each test case
        Arrays.stream(testCases).forEach(tc -> {
            String actual   = solve(tc[0], tc[1]);
            String expected = tc[2];
            if (actual.equals(expected)) {
                System.out.println("PASS for input (" + tc[0] + ", " + tc[1] + ") => " + actual);
            } else {
                System.out.println("FAIL for input (" + tc[0] + ", " + tc[1] + ")\n"
                        + "  Expected: " + expected + "\n"
                        + "  Got:      " + actual);
            }
        });

        // Large data test: 100 '1's + 100 '1's
        String largeA = new String(new char[100]).replace('\0', '1'); 
        String largeB = new String(new char[100]).replace('\0', '1'); 
        String largeExpected = new BigInteger(largeA, 2).add(new BigInteger(largeB, 2)).toString(2);
        String largeActual   = solve(largeA, largeB);
        if (largeActual.equals(largeExpected)) {
            System.out.println("PASS for large input (100 '1's each) => length: " + largeActual.length());
        } else {
            System.out.println("FAIL for large input (100 '1's each)\n"
                    + "  Expected length: " + largeExpected.length() + "\n"
                    + "  Got length:      " + largeActual.length());
        }
    }
}
