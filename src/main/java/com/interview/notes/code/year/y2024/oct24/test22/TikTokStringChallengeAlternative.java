package com.interview.notes.code.year.y2024.oct24.test22;

import java.util.ArrayList;
import java.util.List;

public class TikTokStringChallengeAlternative {

    /**
     * Returns the minimum number of characters that need to be changed to ensure every character
     * in the caption has at least one identical adjacent character.
     *
     * @param caption The input string consisting of lowercase English letters.
     * @return The minimum number of characters that need to be changed.
     */
    public static int getMinCharactersToChange(String caption) {
        if (caption == null || caption.length() < 2) {
            return 0;
        }

        char[] chars = caption.toCharArray();
        int changes = 0;
        int n = chars.length;

        for (int i = 0; i < n; i++) {
            // Check if current character has an identical left or right neighbor
            boolean hasLeft = (i > 0) && (chars[i] == chars[i - 1]);
            boolean hasRight = (i < n - 1) && (chars[i] == chars[i + 1]);

            if (hasLeft || hasRight) {
                continue;
            }

            // Decide which neighbor to match
            if (i < n - 1) {
                // Change current character to match the right neighbor
                chars[i] = chars[i + 1];
            } else {
                // Change current character to match the left neighbor
                chars[i] = chars[i - 1];
            }
            changes++;
        }

        return changes;
    }

    /**
     * Runs a series of test cases to verify the correctness of the getMinCharactersToChange method.
     */
    public static void main(String[] args) {
        // Define test cases: each test case is a pair of input and expected output
        List<TestCase> testCases = new ArrayList<>();

        // Sample Test Case 0
        testCases.add(new TestCase("abab", 2));

        // Sample Test Case 1
        testCases.add(new TestCase("abcdef", 3));

        // Additional Test Cases

        // All characters already satisfy the condition
        testCases.add(new TestCase("aabbcc", 0));

        // All characters are unique, need to transform
        testCases.add(new TestCase("abcdefg", 3));

        // Single transformation needed in the middle
        testCases.add(new TestCase("aaca", 1));

        // Large input: alternating 'a' and 'b', length 100,000
        StringBuilder largeInputBuilder = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            largeInputBuilder.append(i % 2 == 0 ? 'a' : 'b');
        }
        testCases.add(new TestCase(largeInputBuilder.toString(), 50000));

        // Edge Case: all characters are 'a'
        testCases.add(new TestCase("aaaaa", 0));

        // Edge Case: all characters are 'z'
        testCases.add(new TestCase("zzzzzz", 0));

        // Edge Case: alternating 'a' and 'z', needing maximum transformations
        testCases.add(new TestCase("azazazazaz", 5));

        // Run all test cases
        int passed = 0;
        for (int i = 0; i < testCases.size(); i++) {
            TestCase tc = testCases.get(i);
            int result = getMinCharactersToChange(tc.input);
            if (result == tc.expectedOutput) {
                System.out.println("Test case " + (i + 1) + ": PASS");
                passed++;
            } else {
                System.out.println("Test case " + (i + 1) + ": FAIL");
                System.out.println("    Input: \"" + tc.input + "\"");
                System.out.println("    Expected Output: " + tc.expectedOutput);
                System.out.println("    Your Output: " + result);
            }
        }

        System.out.println("\n" + passed + " out of " + testCases.size() + " test cases passed.");
    }

    /**
     * Helper class to represent a test case.
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
