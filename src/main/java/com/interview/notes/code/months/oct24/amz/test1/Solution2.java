package com.interview.notes.code.months.oct24.amz.test1;

import java.util.ArrayList;
import java.util.List;

public class Solution2 {

    /**
     * Finds the length of the longest substring in the given string where the first character is
     * lexicographically smaller than the last character. If no such substring exists, returns 0.
     *
     * @param s the input string
     * @return the length of the longest valid substring
     */
    public static int getLongestSubstring(String s) {
        int n = s.length();
        int maxLen = 0;

        // Iterate through all possible substrings
        for (int i = 0; i < n - 1; i++) {
            char firstChar = s.charAt(i);
            for (int j = n - 1; j > i; j--) {
                char lastChar = s.charAt(j);
                if (firstChar < lastChar) {
                    int len = j - i + 1;
                    if (len > maxLen) {
                        maxLen = len;
                    }
                    // Since we're moving from the end, no need to check shorter substrings starting at i
                    break;
                }
            }
        }

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
        testCases.add(new TestCase("abcba", 4)); // Corrected Expected Output to 4
        testCases.add(new TestCase("zxy", 2));    // Corrected Expected Output to 2
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
