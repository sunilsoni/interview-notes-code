package com.interview.notes.code.year.y2025.november.linkedin.test2;

import java.util.stream.IntStream;

// Public class that contains both the matching logic and the test main method
public class ParenthesisMatcherDemo {

    // Static method to check if the parentheses in the string are properly matched
    public static boolean matched(String s) {
        // Check if the input string reference is null; if it is null, we cannot process it, so return false
        if (s == null) {
            // Return false for null to signal invalid input rather than “balanced”
            return false;
        }

        // Create an integer variable to track how many '(' are currently open and not yet closed
        int balance = 0;

        // Loop through every character in the string from index 0 to length - 1
        for (int i = 0; i < s.length(); i++) {
            // Read the current character at position i
            char ch = s.charAt(i);

            // If the current character is an opening parenthesis '('
            if (ch == '(') {
                // Increase balance by 1 because we have one more open parenthesis
                balance++;
            }
            // Otherwise, if the current character is a closing parenthesis ')'
            else if (ch == ')') {
                // If balance is zero, it means there is no matching '(' before this ')'
                if (balance == 0) {
                    // Return false immediately because order is invalid (more closes than opens)
                    return false;
                }
                // If balance is positive, we have at least one open '(' to match this ')'
                balance--;
            }
            // If the character is neither '(' nor ')', we do nothing because we must ignore non-brace characters
        }

        // After processing all characters, check if all opened '(' have been closed
        // If balance is zero, every '(' had a matching ')'
        return balance == 0;
    }

    // Main method used as a simple manual test harness instead of JUnit
    public static void main(String[] args) {
        // Define an array of test input strings to verify various scenarios
        String[] inputs = new String[] {
                "()()()()",        // multiple simple matched pairs
                "((45+)*a3)",      // matches with non-parenthesis characters mixed in
                "((((()))",        // extra '(' left open at the end
                ")))(((",          // closing brackets appear before opening ones
                "",                // empty string should be balanced
                "abc123",          // no parentheses at all should be treated as balanced
                "(",               // single opening parenthesis is not balanced
                ")",               // single closing parenthesis is not balanced
                "(a+b)*(c+d)",     // typical math expression with balanced parentheses
                "(a+b)*(c+d",      // missing one closing parenthesis
                null               // explicit null test, expected false
        };

        // Define the expected boolean results for each test case in the same order as the inputs
        Boolean[] expected = new Boolean[] {
                true,   // "()()()()"        → balanced
                true,   // "((45+)*a3)"      → balanced
                false,  // "((((()))"        → unbalanced (two extra '(')
                false,  // ")))((("          → invalid order and unbalanced
                true,   // ""                → considered balanced
                true,   // "abc123"          → no parentheses, so balanced
                false,  // "("               → one '(' with no ')'
                false,  // ")"               → one ')' with no '(' before
                true,   // "(a+b)*(c+d)"     → balanced math expression
                false,  // "(a+b)*(c+d"      → missing one ')'
                false   // null              → we treat null as invalid / unbalanced
        };

        // Print a header to make the console output easier to read
        System.out.println("Running ParenthesisMatcherDemo tests...");

        // Use Java 8 IntStream to iterate over indices and run all test cases
        IntStream.range(0, inputs.length).forEach(index -> {
            // Fetch the input string for this index
            String input = inputs[index];

            // Fetch the expected boolean result for this index
            Boolean exp = expected[index];

            // Call the matched method with the current input to get the actual result
            boolean actual = matched(input);

            // Build a readable label for null versus non-null input for printing
            String displayInput = (input == null) ? "null" : "\"" + input + "\"";

            // Determine whether this specific test passed by comparing expected and actual
            boolean pass = (exp == actual);

            // Print detailed information for this test case, including input, expected, and actual
            System.out.println(
                    "Test " + index +
                            " | Input=" + displayInput +
                            " | Expected=" + exp +
                            " | Actual=" + actual +
                            " | Result=" + (pass ? "PASS" : "FAIL")
            );
        });

        // Now create a large balanced test string to check performance and behavior on big inputs
        int pairs = 1_000_000; // Choose one million pairs "()" which gives two million characters total

        // Use a StringBuilder to efficiently build a large string of parentheses
        StringBuilder largeBalancedBuilder = new StringBuilder(pairs * 2);

        // Use Java 8 IntStream to append "()" pairs pairs times without manual for-loop
        IntStream.range(0, pairs).forEach(i -> largeBalancedBuilder.append("()"));

        // Convert the StringBuilder into a String to use as a test input
        String largeBalanced = largeBalancedBuilder.toString();

        // Call the matched method on the large balanced string to check correctness and performance
        boolean largeBalancedResult = matched(largeBalanced);

        // Print summary result for the large balanced test case
        System.out.println("Large Balanced Test | Length=" + largeBalanced.length()
                + " | Expected=true"
                + " | Actual=" + largeBalancedResult
                + " | Result=" + (largeBalancedResult ? "PASS" : "FAIL"));

        // Create a large unbalanced string by adding one extra '(' at the end of the balanced large string
        String largeUnbalanced = largeBalanced + "(";

        // Call the matched method on the large unbalanced string
        boolean largeUnbalancedResult = matched(largeUnbalanced);

        // Print summary result for the large unbalanced test case
        System.out.println("Large Unbalanced Test | Length=" + largeUnbalanced.length()
                + " | Expected=false"
                + " | Actual=" + largeUnbalancedResult
                + " | Result=" + (!largeUnbalancedResult ? "PASS" : "FAIL"));
    }
}
