package com.interview.notes.code.months.oct24.test20;

import com.interview.notes.code.months.oct24.test23.Solution;

import java.util.*;

public class TikTokStringChallengeDP {

    /**
     * Returns the minimum number of transformation steps required to ensure every character
     * in the caption has at least one identical adjacent character.
     *
     * @param caption The input string consisting of lowercase English letters.
     * @return The minimum number of transformation steps required.
     */
    public static int getMinTransformations(String caption) {
        if (caption == null || caption.length() < 2) {
            // Edge case: If the string is null or has less than 2 characters,
            // it's impossible to satisfy the condition. However, per constraints, n >=2.
            return 0;
        }

        char[] chars = caption.toCharArray();
        int n = chars.length;
        // Initialize DP array with a size of n+1 to handle indexing up to i+1
        long[] dp = new long[n + 1];
        Arrays.fill(dp, Long.MAX_VALUE / 2); // Prevent overflow

        dp[0] = 0;
        // Base case: Pair first two characters
        dp[1] = Math.abs(chars[0] - chars[1]);

        for (int i = 1; i < n; i++) {
            // Option 1: Pair character i with i-1
            if (i >= 1) {
                long costPairWithPrev = dp[i - 1] + Math.abs(chars[i] - chars[i - 1]);
                if (costPairWithPrev < dp[i]) {
                    dp[i] = costPairWithPrev;
                }
            }

            // Option 2: Pair character i with i+1
            if (i + 1 < n) {
                long costPairWithNext = dp[i - 1] + Math.abs(chars[i] - chars[i + 1]);
                if (costPairWithNext < dp[i + 1]) {
                    dp[i + 1] = costPairWithNext;
                }
            }
        }

        return (int) dp[n - 1];
    }

    /**
     * Runs a series of test cases to verify the correctness of the getMinTransformations method.
     */
    public static void main(String[] args) {
        // Define test cases: each test case is a pair of input and expected output
        List<TestCase> testCases = new ArrayList<>();

        // Sample Test Case 0
        testCases.add(new TestCase("abab", 2));

        // Sample Test Case 1
        testCases.add(new TestCase("abcdef", 3));

        // Additional Test Cases

        // Test Case 2: All characters already satisfy the condition
        testCases.add(new TestCase("aabbcc", 0));

        // Test Case 3: All characters are unique, need to transform
        testCases.add(new TestCase("abcdefg", 3));

        // Test Case 4: Single transformation needed in the middle
        testCases.add(new TestCase("aaca", 2));

        // Test Case 5: Large input: alternating 'a' and 'b', length 100,000
        StringBuilder largeInputBuilder = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            largeInputBuilder.append(i % 2 == 0 ? 'a' : 'b');
        }
        testCases.add(new TestCase(largeInputBuilder.toString(), 0));

        // Test Case 6: Edge Case: all characters are 'a'
        testCases.add(new TestCase("aaaaa", 0));

        // Test Case 7: Edge Case: all characters are 'z'
        testCases.add(new TestCase("zzzzzz", 0));

        // Test Case 8: Edge Case: alternating 'a' and 'z', needing maximum transformations
        testCases.add(new TestCase("azazazazaz", 125));

        // Test Case 9: Another failing test case
        testCases.add(new TestCase("abcdefg", 3));

        // Run all test cases
        int passed = 0;
        for (int i = 0; i < testCases.size(); i++) {
            TestCase tc = testCases.get(i);
            int result = Solution.getMinTransformations(tc.input);
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
