package com.interview.notes.code.year.y2024.oct24.test4;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Stack;

public class RemoveDuplicateLetters {

    public static void main(String[] args) {
        RemoveDuplicateLetters solver = new RemoveDuplicateLetters();
        solver.runTests();
    }


    /**
     * Removes duplicate letters to produce the smallest lexicographical order string.
     *
     * @param s Input string consisting of lowercase letters.
     * @return Resulting string after removing duplicates.
     */
    public String removeDuplicateLetters(String s) {
        int[] charCount = new int[26]; // Count of each character
        boolean[] inStack = new boolean[26]; // Whether a character is in the stack
        Stack<Character> stack = new Stack<>();

        // Count occurrences of each character
        for (char c : s.toCharArray()) {
            charCount[c - 'a']++;
        }

        // Iterate over the string
        for (char c : s.toCharArray()) {
            int idx = c - 'a';
            charCount[idx]--; // Decrement count as character is processed

            if (inStack[idx]) {
                continue; // Skip if character is already in stack
            }

            // Remove characters that are greater than current and can appear later
            while (!stack.isEmpty() && c < stack.peek() && charCount[stack.peek() - 'a'] > 0) {
                inStack[stack.pop() - 'a'] = false;
            }

            stack.push(c);
            inStack[idx] = true;
        }

        // Build result from stack
        StringBuilder result = new StringBuilder();
        for (char c : stack) {
            result.append(c);
        }

        return result.toString();
    }

    /**
     * Runs all test cases and outputs pass/fail results.
     */
    public void runTests() {
        Map<String, String> testCases = new LinkedHashMap<>();
        testCases.put("bcabc", "abc");
        testCases.put("cbacdcbc", "acdb");
        testCases.put("abcd", "abcd");
        testCases.put("ecbacba", "eacb");
        testCases.put("leetcode", "letcod");
        // Add large test case
        testCases.put(generateLargeTestCase(), "abcdefghijklmnopqrstuvwxyz");

        int passed = 0;
        int testNumber = 1;

        for (Map.Entry<String, String> testCase : testCases.entrySet()) {
            String input = testCase.getKey();
            String expected = testCase.getValue();
            String output = removeDuplicateLetters(input);

            if (output.equals(expected)) {
                System.out.println("Test Case " + testNumber + ": PASS");
                passed++;
            } else {
                System.out.println("Test Case " + testNumber + ": FAIL");
                System.out.println("Input:    " + input);
                System.out.println("Expected: " + expected);
                System.out.println("Got:      " + output);
            }
            testNumber++;
        }

        System.out.println("\nPassed " + passed + " out of " + testCases.size() + " test cases.");
    }

    /**
     * Generates a large test case string containing all lowercase letters in reverse order.
     *
     * @return A large test case string.
     */
    private String generateLargeTestCase() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10000; i++) { // Repeat to make it large
            for (char c = 'z'; c >= 'a'; c--) {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
