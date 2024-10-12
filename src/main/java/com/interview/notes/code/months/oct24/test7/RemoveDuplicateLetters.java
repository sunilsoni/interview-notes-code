package com.interview.notes.code.months.oct24.test7;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Stack;

/*
WORKING

Given a string s, remove duplicate letters so that every letter appears once and only once. You must make sure your result is the
smallest in lexicographical order among all possible results. Example 1: Input: 5 = "bcabc" Output: "abc" Note: Lexicographical
order is similar to alphabetical order. For instance, "abc" is smaller than "acb".
 */
public class RemoveDuplicateLetters {

    /**
     * Main method to run test cases.
     */
    public static void main(String[] args) {
        RemoveDuplicateLetters solver = new RemoveDuplicateLetters();
        solver.runTests();
    }

    /**
     * Method to remove duplicate letters and return the lexicographically smallest result.
     *
     * @param s The input string consisting of lowercase English letters.
     * @return The lexicographically smallest string after removing duplicate letters.
     */
    public String removeDuplicateLetters(String s) {
        int[] lastIndex = new int[26]; // Last occurrence index of each character
        boolean[] inResult = new boolean[26]; // Whether a character is in the current result
        Stack<Character> stack = new Stack<>(); // Stack to build the result

        // Compute the last occurrence index for each character
        for (int i = 0; i < s.length(); i++) {
            lastIndex[s.charAt(i) - 'a'] = i;
        }

        // Iterate over the string characters
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // Skip if character is already in result
            if (inResult[c - 'a']) {
                continue;
            }

            // Remove characters that are greater than current character c
            // and can appear later
            while (!stack.isEmpty() && c < stack.peek() && lastIndex[stack.peek() - 'a'] > i) {
                char removed = stack.pop();
                inResult[removed - 'a'] = false;
            }

            // Add current character to stack and mark it as included
            stack.push(c);
            inResult[c - 'a'] = true;
        }

        // Build the result from the stack
        StringBuilder result = new StringBuilder();
        for (char c : stack) {
            result.append(c);
        }

        return result.toString();
    }

    /**
     * Method to run predefined and additional test cases.
     */
    public void runTests() {
        Map<String, String> testCases = new LinkedHashMap<>();
        testCases.put("bcabc", "abc");
        testCases.put("cbacdcbc", "acdb");
        testCases.put("abcd", "abcd");
        testCases.put("ecbacba", "eacb");
        testCases.put("leetcode", "letcod");
        testCases.put("", "");
        testCases.put("aaaaa", "a");
        testCases.put("edebbed", "bed");
        testCases.put("bbcaac", "bac");

        // Add a large test case
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            sb.append('a' + (i % 26));
        }
        String largeTestInput = sb.toString();
        String largeTestOutput = "abcdefghijklmnopqrstuvwxyz";
        testCases.put(largeTestInput, largeTestOutput);

        boolean allPassed = true;
        int testNumber = 1;

        for (Map.Entry<String, String> testCase : testCases.entrySet()) {
            String input = testCase.getKey();
            String expectedOutput = testCase.getValue();
            String actualOutput = removeDuplicateLetters(input);

            if (actualOutput.equals(expectedOutput)) {
                System.out.println("Test case " + testNumber + " PASSED.");
            } else {
                System.out.println("Test case " + testNumber + " FAILED.");
                System.out.println("Input: " + (input.length() > 50 ? input.substring(0, 50) + "..." : input));
                System.out.println("Expected Output: " + expectedOutput);
                System.out.println("Actual Output: " + actualOutput);
                allPassed = false;
            }
            testNumber++;
        }

        if (allPassed) {
            System.out.println("All test cases PASSED.");
        } else {
            System.out.println("Some test cases FAILED.");
        }
    }
}
