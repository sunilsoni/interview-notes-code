package com.interview.notes.code.months.dec24.test1;

import java.util.ArrayList;
import java.util.List;

public class ParenthesesBalancer {

    /**
     * Calculates the minimum number of insertions required to balance the parentheses in the string.
     *
     * @param s The input string containing '(' and ')'.
     * @return The minimum number of insertions needed to balance the string.
     */
    public static int getMin(String s) {
        int balance = 0;      // Tracks the balance of parentheses
        int insertions = 0;   // Counts the number of insertions needed

        for (char c : s.toCharArray()) {
            if (c == '(') {
                balance++;
            } else if (c == ')') {
                balance--;
                if (balance < 0) {
                    insertions++; // Insert '(' to balance
                    balance = 0;  // Reset balance after insertion
                }
            }
        }

        // Any remaining '(' need corresponding ')' insertions
        insertions += balance;

        return insertions;
    }

    /**
     * Runs predefined test cases to verify the correctness of the getMin function.
     */
    public static void runTests() {
        class TestCase {
            String input;
            int expected;

            TestCase(String input, int expected) {
                this.input = input;
                this.expected = expected;
            }
        }

        List<TestCase> testCases = new ArrayList<>();

        // Sample Test Cases
        testCases.add(new TestCase("(()))", 2));
        testCases.add(new TestCase("()()", 0));

        // Additional Test Cases
        testCases.add(new TestCase("", 0)); // Empty string
        testCases.add(new TestCase("(((", 3)); // All '('
        testCases.add(new TestCase(")))", 3)); // All ')'
        testCases.add(new TestCase("()()()()()", 0)); // Already balanced
        testCases.add(new TestCase("(()(()))", 0)); // Nested balanced
        testCases.add(new TestCase("())(()", 2)); // Mixed imbalance
        testCases.add(new TestCase(")(", 2)); // Single pair misordered
        testCases.add(new TestCase("(()))(()", 2)); // Multiple imbalances

        // Large Test Case
        StringBuilder largeInputBuilder = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            largeInputBuilder.append('(');
        }
        String largeInput = largeInputBuilder.toString();
        testCases.add(new TestCase(largeInput, 100000));

        int passed = 0;
        for (int i = 0; i < testCases.size(); i++) {
            TestCase tc = testCases.get(i);
            int result = getMin(tc.input);
            if (result == tc.expected) {
                System.out.println("Test Case " + (i + 1) + ": PASS");
                passed++;
            } else {
                System.out.println("Test Case " + (i + 1) + ": FAIL (Expected " + tc.expected + ", Got " + result + ")");
            }
        }

        System.out.println(passed + " out of " + testCases.size() + " test cases passed.");
    }

    public static void main(String[] args) {
        runTests();
    }
}
