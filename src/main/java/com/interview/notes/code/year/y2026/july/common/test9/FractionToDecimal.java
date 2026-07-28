package com.interview.notes.code.year.y2026.july.common.test9;

import java.util.HashMap; // Imports HashMap to store remainders and their corresponding string indices to detect cycles.
import java.util.List; // Imports List interface to hold our collection of test cases.

public class FractionToDecimal { // Defines the main class that encapsulates our conversion logic and testing method.

    public static String fractionToDecimal(int numerator, int denominator) { // Method signature accepting integer numerator and denominator, returning the formatted string.
        if (numerator == 0) return "0"; // Early return for zero; if the numerator is 0, the math result is always exactly 0.

        var res = new StringBuilder(); // Uses Java 10+ 'var' for brevity, creating a StringBuilder to efficiently construct our result string.

        if ((numerator < 0) ^ (denominator < 0)) res.append("-"); // Uses logical XOR to check if exactly one number is negative; if so, appends a minus sign.

        long num = Math.abs((long) numerator); // Casts numerator to long and gets absolute value to prevent overflow if the input is Integer.MIN_VALUE.
        long den = Math.abs((long) denominator); // Casts denominator to long and gets absolute value to prevent overflow during division.

        res.append(num / den); // Performs integer division to get the whole number part and appends it to our result string.

        long rem = num % den; // Calculates the initial remainder using the modulo operator to see if a fractional part exists.

        if (rem == 0) return res.toString(); // If the remainder is exactly 0, the division is clean and terminating, so we return the string immediately.

        res.append("."); // Since a remainder exists, we append a decimal point to begin constructing the fractional part.

        var map = new HashMap<Long, Integer>(); // Creates a HashMap to track remainders (Key) and the string index where their cycle would start (Value).

        while (rem != 0) { // Starts a loop that continues processing digits as long as we have a non-zero remainder.
            if (map.containsKey(rem)) { // Checks our map to see if we've processed this exact remainder before, indicating a cyclic repetition.
                res.insert(map.get(rem), "("); // If it's a cycle, we insert an opening parenthesis at the stored index where this remainder sequence began.
                res.append(")"); // We append a closing parenthesis at the current end of the string to close the cycle.
                break; // We break out of the while loop because the cyclic portion is completely identified and formatted.
            } // Closes the cycle-checking if-statement block.

            map.put(rem, res.length()); // If it's a new remainder, we store it in the map alongside the current length of the string builder (its index).

            rem *= 10; // We multiply the remainder by 10 to simulate 'bringing down a zero' in long division to calculate the next digit.

            res.append(rem / den); // We divide the scaled remainder by the denominator to get the next decimal digit and append it.

            rem %= den; // We update the remainder to the result of this new division step to prepare for the next loop iteration.
        } // Closes the long division while loop.

        return res.toString(); // Converts the finalized StringBuilder object into an immutable String and returns it to the caller.
    } // Closes the fractionToDecimal method block.

    public static void main(String[] args) { // Main method serving as the entry point for executing our test cases.
        var tests = List.of( // Uses List.of to create an immutable list populated with various normal, cyclic, and large data test cases.
            new TestCase(1, 2, "0.5"), // Test case directly from the provided image for a simple terminating decimal.
            new TestCase(1, 3, "0.(3)"), // Test case directly from the provided image for a simple repeating decimal cycle.
            new TestCase(20, 4, "5"), // Test case verifying that numbers dividing cleanly return just the whole number.
            new TestCase(2, 3, "0.(6)"), // Test case verifying a different basic single-digit repeating decimal.
            new TestCase(4, 333, "0.(012)"), // Test case verifying a multi-digit repeating cycle.
            new TestCase(1, 7, "0.(142857)"), // Test case verifying a longer 6-digit repeating cycle.
            new TestCase(-50, 8, "-6.25"), // Test case checking negative result handling with a terminating decimal.
            new TestCase(-1, -2, "0.5"), // Test case checking that a negative divided by a negative correctly yields a positive.
            new TestCase(1, -6, "-0.1(6)"), // Test case checking where the repeating cycle begins after a static decimal digit.
            new TestCase(Integer.MIN_VALUE, 1, "-2147483648"), // Edge case verifying large data bounds using the minimum integer value to ensure no overflow.
            new TestCase(1, 17, "0.(0588235294117647)") // Large data case checking a complex fraction with a massive 16-digit cyclic repetition.
        ); // Closes the List initialization block.

        tests.stream().forEach(test -> { // Utilizes Java 8 Stream API to iterate cleanly over each test case in the list.
            var result = fractionToDecimal(test.num(), test.den()); // Invokes our conversion method with the numerator and denominator from the current test record.
            var isPass = result.equals(test.expected()); // Evaluates equality between our computed result and the predefined expected string.
            var status = isPass ? "PASS" : "FAIL"; // Uses a ternary operator to assign the string "PASS" if true, or "FAIL" if false.
            System.out.println(status + " -> Input: " + test.num() + "/" + test.den() + " | Expected: " + test.expected() + " | Got: " + result); // Prints the pass/fail status and relevant details to the console.
        }); // Closes the Stream API forEach lambda and statement execution.
    } // Closes the main method execution block.

    record TestCase(int num, int den, String expected) {} // Uses Java 14+ 'record' to create a minimal, immutable data structure for test inputs and expected outputs.
} // Closes the entire FractionToDecimal class wrapper.