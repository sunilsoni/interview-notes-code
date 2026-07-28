package com.interview.notes.code.year.y2026.july.common.test10;

import java.util.HashMap; // Imports HashMap to remember previously processed remainders.
import java.util.Map; // Imports the Map interface for storing remainder positions.
import java.util.stream.Stream; // Imports Stream API to process all test cases.

public class FractionToDecimal { // Defines the main class for fraction conversion.

    static String convert(int numerator, int denominator) { // Converts the supplied fraction into a decimal string.

        if (denominator == 0) { // Checks whether division by zero was requested.
            throw new ArithmeticException("Denominator cannot be zero"); // Stops execution because division by zero is invalid.
        } // Ends the denominator validation.

        if (numerator == 0) { // Checks whether the fraction value is zero.
            return "0"; // Returns zero without performing further calculations.
        } // Ends the zero numerator check.

        long n = numerator; // Converts the numerator to long to prevent integer overflow.
        long d = denominator; // Converts the denominator to long to prevent integer overflow.
        var result = new StringBuilder(); // Creates a mutable string for building the answer.

        if ((n < 0) != (d < 0)) { // Checks whether exactly one input value is negative.
            result.append('-'); // Adds a negative sign when the final result is negative.
        } // Ends the sign check.

        n = Math.abs(n); // Makes the numerator positive after converting it to long.
        d = Math.abs(d); // Makes the denominator positive after converting it to long.
        result.append(n / d); // Adds the whole-number part of the fraction.
        long remainder = n % d; // Calculates the remainder needed for decimal division.

        if (remainder == 0) { // Checks whether the fraction divides exactly.
            return result.toString(); // Returns the whole-number or terminating result.
        } // Ends the exact division check.

        result.append('.'); // Adds the decimal point before processing decimal digits.
        Map<Long, Integer> seen = new HashMap<>(); // Stores each remainder and its position in the result.

        while (remainder != 0) { // Continues until division ends or a repeating remainder is found.

            var repeatAt = seen.putIfAbsent(remainder, result.length()); // Saves the remainder position or returns its previous position.

            if (repeatAt != null) { // Checks whether this remainder was processed earlier.
                result.insert(repeatAt.intValue(), '(').append(')'); // Places parentheses around the repeating decimal digits.
                break; // Stops because the repeating section has been found.
            } // Ends the repeating remainder check.

            remainder *= 10; // Moves the remainder to the next decimal place.
            result.append(remainder / d); // Adds the next decimal digit.
            remainder %= d; // Calculates the remainder for the following digit.
        } // Ends the decimal division loop.

        return result.toString(); // Returns the completed decimal representation.
    } // Ends the conversion method.

    static boolean test(TestCase test) { // Runs one normal test case.

        var actual = convert(test.numerator(), test.denominator()); // Calls the conversion method using the test inputs.
        var passed = actual.equals(test.expected()); // Compares the actual result with the expected result.

        System.out.printf( // Prints the complete PASS or FAIL information.
                "%s | %d/%d | Expected: %s | Actual: %s%n", // Defines the output format.
                passed ? "PASS" : "FAIL", // Prints PASS when values match, otherwise FAIL.
                test.numerator(), // Prints the numerator.
                test.denominator(), // Prints the denominator.
                test.expected(), // Prints the expected result.
                actual); // Prints the actual result.

        return passed; // Returns whether this test passed.
    } // Ends the normal test method.

    static boolean testZeroDenominator() { // Tests the denominator-zero edge case.

        try { // Starts a block that expects an exception.
            convert(1, 0); // Calls the method with an invalid denominator.
            System.out.println("FAIL | 1/0 | Expected ArithmeticException"); // Prints failure when no exception occurs.
            return false; // Reports that the test failed.
        } catch (ArithmeticException error) { // Handles the expected division-by-zero exception.
            System.out.println("PASS | 1/0 | ArithmeticException handled"); // Prints that the error was correctly handled.
            return true; // Reports that the test passed.
        } // Ends exception handling.
    } // Ends the denominator-zero test.

    public static void main(String[] args) { // Starts the application and executes all tests.

        var tests = Stream.of( // Creates a stream containing all normal test cases.
                new TestCase(1, 2, "0.5"), // Tests a terminating decimal from the given example.
                new TestCase(1, 3, "0.(3)"), // Tests a repeating decimal from the given example.
                new TestCase(1, 6, "0.1(6)"), // Tests a decimal with non-repeating and repeating parts.
                new TestCase(1, 333, "0.(003)"), // Tests repeating digits containing leading zeros.
                new TestCase(-50, 8, "-6.25"), // Tests a negative terminating decimal.
                new TestCase(7, -12, "-0.58(3)"), // Tests a negative denominator and repeating decimal.
                new TestCase(0, 5, "0"), // Tests a zero numerator.
                new TestCase(2, 1, "2"), // Tests a whole-number result.
                new TestCase(Integer.MIN_VALUE, 1, "-2147483648"), // Tests the smallest integer numerator.
                new TestCase(Integer.MIN_VALUE, -1, "2147483648"), // Tests overflow protection using long.
                new TestCase( // Starts a large terminating decimal test.
                        -1, // Uses a negative numerator.
                        Integer.MIN_VALUE, // Uses the smallest integer denominator.
                        "0.0000000004656612873077392578125"), // Provides the expected large decimal result.
                new TestCase( // Starts a long repeating-decimal test.
                        1, // Uses one as the numerator.
                        97, // Uses a denominator with a long repeating cycle.
                        "0.(010309278350515463917525773195876288659793814432989690721649484536082474226804123711340206185567)")) // Provides its expected result.
                .toList(); // Converts the stream into a reusable list.

        long passed = tests.stream() // Creates a stream for running all normal tests.
                .filter(FractionToDecimal::test) // Executes each test and keeps only passing tests.
                .count(); // Counts the number of passing normal tests.

        boolean zeroPassed = testZeroDenominator(); // Runs the denominator-zero test.
        long totalPassed = passed + (zeroPassed ? 1 : 0); // Calculates the total number of passed tests.
        long total = tests.size() + 1L; // Calculates the total number of executed tests.

        System.out.printf( // Prints the final test result.
                "%nFINAL: %s (%d/%d passed)%n", // Defines the final output format.
                totalPassed == total ? "PASS" : "FAIL", // Prints PASS only when every test passes.
                totalPassed, // Prints the total number of passed tests.
                total); // Prints the total number of tests.
    } // Ends the main method.

    record TestCase(int numerator, int denominator, String expected) { // Stores one test input and its expected result.
    } // Ends the TestCase record.
} // Ends the FractionToDecimal class.