package com.interview.notes.code.year.y2025.november.oci.test8;// Importing Arrays utility to quickly build lists of test cases.

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * This class contains logic to check if a string has balanced parentheses
 * and a main method to run multiple test cases (including large inputs).
 */
public class BalancedParenthesesChecker {

    /**
     * Core method to check if a given string has balanced parentheses.
     * This method uses a simple integer counter and a single left-to-right pass.
     *
     * @param input The string we want to check.
     * @return true if parentheses are balanced, false otherwise.
     */
    public static boolean hasBalancedParentheses(String input) {
        // If the input is null, we consider it NOT valid, so we return false early.
        if (input == null) {
            return false;
        }

        // This integer keeps track of how many '(' are currently open and not yet closed.
        int balance = 0;

        // Loop over each character of the input string from left to right.
        for (int i = 0; i < input.length(); i++) {
            // Read the character at the current index i.
            char ch = input.charAt(i);

            // If the character is an opening parenthesis, we increase the balance.
            if (ch == '(') {
                // Increment balance because we have one more '(' without a matching ')'.
                balance++;
            }
            // Else if the character is a closing parenthesis, we need to handle it.
            else if (ch == ')') {
                // If balance is already zero, it means we have a ')' without a previous '('.
                if (balance == 0) {
                    // Return false because we found an invalid closing parenthesis.
                    return false;
                }
                // Otherwise, we decrement balance because one '(' is now matched with this ')'.
                balance--;
            }
            // For any other character (letters, digits, spaces, etc.) we do nothing.
            // We simply ignore those characters in the context of parentheses balancing.
        }

        // After processing all characters, if balance is zero, all '(' are properly closed.
        // If balance is not zero, we still have unmatched '(' so it is not balanced.
        return balance == 0;
    }

    /**
     * Helper method to run a single test case and print if it PASS or FAIL.
     *
     * @param testCase The TestCase object containing input, expected result, and name.
     */
    private static void runSingleTest(TestCase testCase) {
        // Call the core method hasBalancedParentheses with the test input.
        boolean actual = hasBalancedParentheses(testCase.input);

        // Check if the actual output matches the expected output.
        boolean pass = (actual == testCase.expected);

        // Build a string representation of the input, handling null safely.
        String inputDisplay = (testCase.input == null) ? "null" : "\"" + testCase.input + "\"";

        // If the test passed, print PASS with details.
        if (pass) {
            // Printing a clear PASS message with test name, input, expected, and actual values.
            System.out.println("Test: " + testCase.name + " | PASS | input=" + inputDisplay
                    + " | expected=" + testCase.expected + " | actual=" + actual);
        } else {
            // If the test failed, print FAIL with details so we can debug.
            System.out.println("Test: " + testCase.name + " | FAIL | input=" + inputDisplay
                    + " | expected=" + testCase.expected + " | actual=" + actual);
        }
    }

    /**
     * Method that sets up and runs all test cases, including edge cases and large data tests.
     */
    private static void runAllTests() {
        // Create a list of normal and edge-case test scenarios.
        // We use Arrays.asList to build a fixed-size list of TestCase objects.
        List<TestCase> basicTests = Arrays.asList(
                // Empty string: no parentheses, we treat it as balanced.
                new TestCase("", true, "Empty string"),
                // Single pair of parentheses is clearly balanced.
                new TestCase("()", true, "Single pair"),
                // Two separate balanced pairs.
                new TestCase("()()", true, "Two separate pairs"),
                // Nested balanced parentheses.
                new TestCase("(())", true, "Nested balanced"),
                // One extra '(' that is never closed.
                new TestCase("(()", false, "Missing closing"),
                // One extra ')' that closes too much.
                new TestCase("())", false, "Extra closing"),
                // Non-parenthesis characters between a pair should be ignored.
                new TestCase("abc(def)ghi", true, "Letters with balanced"),
                // Unbalanced because there is an extra ')' after balanced part.
                new TestCase("(a(b)c)d)", false, "Letters with extra closing"),
                // More nested balanced parentheses.
                new TestCase("(((())))", true, "Deeply nested balanced"),
                // Closing parenthesis comes before opening, clearly invalid.
                new TestCase(")(", false, "Closing before opening"),
                // No parentheses at all, treated as balanced.
                new TestCase("abc", true, "No parentheses"),
                // Null input is considered invalid (not balanced).
                new TestCase(null, false, "Null input")
        );

        // Use a simple counter to track how many tests pass.
        int[] passCount = {0};
        // Use a simple counter to track total tests.
        int[] totalCount = {0};

        // Use Java 8 Stream API to loop over basicTests list.
        basicTests.stream()
                // For each TestCase in the stream, run the single test.
                .forEach(testCase -> {
                    // Increase total count for each test that we are about to run.
                    totalCount[0]++;
                    // Run this test and print PASS/FAIL.
                    runSingleTest(testCase);
                    // After running, we call our core method again to check pass or fail.
                    boolean actual = hasBalancedParentheses(testCase.input);
                    // If actual result equals expected, then increment the pass counter.
                    if (actual == testCase.expected) {
                        passCount[0]++;
                    }
                });

        // Now we also create and run large data tests to verify performance and correctness.
        runLargeInputTests(passCount, totalCount);

        // Finally, print a summary line showing how many tests passed out of total.
        System.out.println("Summary: " + passCount[0] + " / " + totalCount[0] + " tests passed.");
    }

    /**
     * Method to create and run large input test cases to ensure the algorithm
     * works efficiently on big data and does not fail due to performance issues.
     *
     * @param passCount  array of length 1 storing number of passed tests (so it can be mutated).
     * @param totalCount array of length 1 storing total number of tests (so it can be mutated).
     */
    private static void runLargeInputTests(int[] passCount, int[] totalCount) {
        // Choose a large size for the string to simulate heavy input (e.g., 1 million characters).
        int largeSize = 1_000_000;

        // Build a large balanced string using Java 8 IntStream:
        // pattern: "()()()..." so it will always be balanced.
        String largeBalanced = IntStream.range(0, largeSize)
                // Map each index to "(" if even, ")" if odd.
                .mapToObj(i -> (i % 2 == 0) ? "(" : ")")
                // Join all "(" and ")" strings into one big string.
                .collect(Collectors.joining());

        // Create a large unbalanced string by taking the balanced one and adding an extra "(" at the end.
        String largeUnbalanced = largeBalanced + "(";

        // Define a test case object for the large balanced string.
        TestCase largeBalancedTest = new TestCase(
                largeBalanced,   // The big balanced string.
                true,            // Expected result is true.
                "Large balanced (" + (largeBalanced.length()) + " chars)" // Name includes size.
        );

        // Define a test case object for the large unbalanced string.
        TestCase largeUnbalancedTest = new TestCase(
                largeUnbalanced, // The big unbalanced string.
                false,           // Expected result is false due to extra "(".
                "Large unbalanced (" + (largeUnbalanced.length()) + " chars)" // Name includes size.
        );

        // Run the large balanced test and update counters.
        totalCount[0]++;                           // Increment total test count.
        runSingleTest(largeBalancedTest);         // Execute the test and print PASS/FAIL.
        if (hasBalancedParentheses(largeBalancedTest.input) == largeBalancedTest.expected) {
            // If actual result matches expected, increment passed test count.
            passCount[0]++;
        }

        // Run the large unbalanced test and update counters.
        totalCount[0]++;                           // Increment total test count.
        runSingleTest(largeUnbalancedTest);       // Execute the test and print PASS/FAIL.
        if (hasBalancedParentheses(largeUnbalancedTest.input) == largeUnbalancedTest.expected) {
            // If actual result matches expected, increment passed test count.
            passCount[0]++;
        }
    }

    /**
     * Main method: entry point of the program.
     * It will run all test cases and print PASS/FAIL for each one.
     *
     * @param args Command-line arguments (not used in this program).
     */
    public static void main(String[] args) {
        // Call runAllTests to execute all basic and large input tests.
        runAllTests();
    }

    /**
     * @param input    Field to store the input string for this test case.
     * @param expected Field to store the expected boolean result for this test case.
     * @param name     Field to store a human-readable name/label for this test case.
     */ // Static inner class to represent a single test case with input, expected result, and name.
    private record TestCase(String input, boolean expected, String name) {
        // Constructor to create a TestCase object with given input, expected, and name.
        // Assigning the constructor parameter "input" to the field "input".
        // Assigning the constructor parameter "expected" to the field "expected".
        // Assigning the constructor parameter "name" to the field "name".
    }
}
