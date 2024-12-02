package com.interview.notes.code.year.y2024.nov24.test8;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class BracketValidator {

    /**
     * Validates if the bracket sequence is valid
     * Time Complexity: O(n) where n is string length
     * Space Complexity: O(n) for stack storage
     */
    public static boolean isValid(String s) {
        if (s == null || s.isEmpty()) {
            return true;
        }

        Stack<Character> stack = new Stack<>();
        Map<Character, Character> bracketPairs = new HashMap<>();
        bracketPairs.put(')', '(');
        bracketPairs.put('}', '{');
        bracketPairs.put(']', '[');

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }

                char lastOpening = stack.pop();
                if (lastOpening != bracketPairs.get(c)) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    /**
     * Helper method to run test case and print result
     */
    private static void runTest(String testName, String input, boolean expected) {
        boolean result = isValid(input);
        boolean passed = result == expected;

        System.out.println("Test Case: " + testName);
        System.out.println("Input: " + input);
        System.out.println("Expected: " + expected);
        System.out.println("Result: " + result);
        System.out.println("Status: " + (passed ? "PASS" : "FAIL"));
        System.out.println();
    }

    /**
     * Generates a large valid bracket string
     */
    private static String generateLargeBracketString(int size) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append("({[]})");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        // Test Case 1: Basic valid cases
        runTest("Basic Valid Case 1", "[()]", true);
        runTest("Basic Valid Case 2", "()[]{}", true);

        // Test Case 2: Basic invalid cases
        runTest("Basic Invalid Case 1", "(]", false);
        runTest("Basic Invalid Case 2", "([)]", false);

        // Test Case 3: Empty and null cases
        runTest("Empty String", "", true);
        runTest("Null String", null, true);

        // Test Case 4: Complex nested cases
        runTest("Complex Nested Valid", "({[()]()})", true);
        runTest("Complex Nested Invalid", "({[()]()}]", false);

        // Test Case 5: Unmatched brackets
        runTest("Unmatched Opening", "(((", false);
        runTest("Unmatched Closing", ")))", false);

        // Test Case 6: Mixed invalid cases
        runTest("Mixed Invalid", "({)}[", false);

        // Test Case 7: Large valid input
        String largeInput = generateLargeBracketString(10000);
        System.out.println("Large Input Test (20000 brackets)");
        long startTime = System.currentTimeMillis();
        boolean largeResult = isValid(largeInput);
        long endTime = System.currentTimeMillis();
        System.out.println("Result: " + largeResult);
        System.out.println("Processing Time: " + (endTime - startTime) + "ms");
        System.out.println("Status: " + (largeResult ? "PASS" : "FAIL"));
        System.out.println();

        // Test Case 8: Single brackets
        runTest("Single Opening Bracket", "(", false);
        runTest("Single Closing Bracket", ")", false);
    }
}