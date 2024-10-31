package com.interview.notes.code.months.oct24.amazon.test1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution1 {

    /**
     * Finds the length of the longest substring in the given string where the first character is
     * lexicographically smaller than the last character. If no such substring exists, returns 0.
     *
     * @param s the input string
     * @return the length of the longest valid substring
     */
    public static int getLongestSubstring(String s) {
        int n = s.length();
        int[] earliest = new int[26];
        int[] latest = new int[26];
        Arrays.fill(earliest, -1);
        Arrays.fill(latest, -1);

        // Record earliest and latest occurrences of each character
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            if (earliest[c] == -1) {
                earliest[c] = i;
            }
            latest[c] = i;
        }

        int maxLen = 0;

        // Iterate over all pairs of characters
        for (int c1 = 0; c1 < 26; c1++) {
            if (earliest[c1] == -1) continue;
            for (int c2 = c1 + 1; c2 < 26; c2++) {
                if (latest[c2] == -1) continue;
                int len = latest[c2] - earliest[c1] + 1;
                if (len > maxLen) {
                    maxLen = len;
                }
            }
        }

        // Return the result if valid substring length is greater than 1
        return maxLen >= 2 ? maxLen : 0;
    }

    /**
     * Main method to run test cases.
     */
    public static void main(String[] args) {
        // List of test cases
        List<TestCase> testCases = new ArrayList<>();
        testCases.add(new TestCase("abcd", 4));
        testCases.add(new TestCase("fghbbadcba", 5));
        testCases.add(new TestCase("aaaa", 0));
        testCases.add(new TestCase("abcba", 3));
        testCases.add(new TestCase("zxy", 3));
        testCases.add(new TestCase("edcba", 0));
        testCases.add(new TestCase("a", 0));
        testCases.add(new TestCase("ab", 2));
        testCases.add(new TestCase("ba", 0));
        testCases.add(new TestCase("abcdecba", 7));

        // Run test cases
        int testCaseNumber = 1;
        for (TestCase testCase : testCases) {
            int result = getLongestSubstring(testCase.input);
            if (result == testCase.expectedOutput) {
                System.out.println("Test case " + testCaseNumber + ": PASS");
            } else {
                System.out.println("Test case " + testCaseNumber + ": FAIL");
                System.out.println("Input: " + testCase.input);
                System.out.println("Expected Output: " + testCase.expectedOutput);
                System.out.println("Actual Output: " + result);
            }
            testCaseNumber++;
        }
    }

    /**
     * Helper class to store test cases.
     */
    static class TestCase {
        String input;
        int expectedOutput;

        TestCase(String input, int expectedOutput) {
            this.input = input;
            this.expectedOutput = expectedOutput;
        }
    }
}
