package com.interview.notes.code.year.y2024.nov24.amazon.test16;

import java.util.ArrayList;
import java.util.List;

public class AttractiveCodeGenerator {

    /**
     * Generates the smallest attractive code greater than or equal to old_code.
     *
     * @param old_code The original product code as a string.
     * @param k        The attractiveness parameter.
     * @return The smallest attractive new_code satisfying the conditions.
     */
    public static String findSmallestAppealing(String old_code, int k) {
        int n = old_code.length();
        String prefix = old_code.substring(0, k);
        String candidate = buildCode(prefix, k, n);

        if (candidate.compareTo(old_code) >= 0 && isAttractive(candidate, k)) {
            return candidate;
        } else {
            // Increment the prefix
            String incrementedPrefix = incrementString(prefix);
            // Handle cases where prefix increment leads to length increase
            if (incrementedPrefix.length() > k) {
                // New length after prefix increment
                int newLength = n + 1;
                StringBuilder sb = new StringBuilder();
                sb.append("1");
                for (int i = 1; i < newLength; i++) {
                    sb.append("0");
                }
                return sb.toString();
            }
            String newCandidate = buildCode(incrementedPrefix, k, n);
            // If the new candidate is still not >= old_code, recurse
            if (newCandidate.compareTo(old_code) >= 0 && isAttractive(newCandidate, k)) {
                return newCandidate;
            } else {
                // If not, increment the prefix again
                String furtherIncrementedPrefix = incrementString(incrementedPrefix);
                if (furtherIncrementedPrefix.length() > k) {
                    int newLength = n + 1;
                    StringBuilder sb = new StringBuilder();
                    sb.append("1");
                    for (int i = 1; i < newLength; i++) {
                        sb.append("0");
                    }
                    return sb.toString();
                }
                return buildCode(furtherIncrementedPrefix, k, n);
            }
        }
    }

    /**
     * Builds the attractive code by repeating the prefix.
     *
     * @param prefix The prefix to repeat.
     * @param k      The attractiveness parameter.
     * @param n      The total length of the code.
     * @return The constructed code.
     */
    private static String buildCode(String prefix, int k, int n) {
        StringBuilder sb = new StringBuilder();
        while (sb.length() < n) {
            sb.append(prefix);
        }
        // If sb.length() > n, truncate
        if (sb.length() > n) {
            sb.setLength(n);
        }
        return sb.toString();
    }

    /**
     * Increments a numeric string by 1.
     *
     * @param s The numeric string.
     * @return The incremented string.
     */
    private static String incrementString(String s) {
        StringBuilder sb = new StringBuilder(s);
        int i = sb.length() - 1;
        while (i >= 0 && sb.charAt(i) == '9') {
            sb.setCharAt(i, '0');
            i--;
        }
        if (i >= 0) {
            sb.setCharAt(i, (char) (sb.charAt(i) + 1));
        } else {
            sb.insert(0, '1');
        }
        return sb.toString();
    }

    /**
     * Checks if the code is attractive based on the parameter k.
     *
     * @param code The code to check.
     * @param k    The attractiveness parameter.
     * @return True if the code is attractive, else False.
     */
    private static boolean isAttractive(String code, int k) {
        int n = code.length();
        for (int i = 0; i < n - k; i++) {
            if (code.charAt(i) != code.charAt(i + k)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Main method to run test cases.
     */
    public static void main(String[] args) {
        List<TestCase> testCases = new ArrayList<>();

        // Provided Sample Test Cases
        testCases.add(new TestCase("41242", 4, "41244"));
        testCases.add(new TestCase("353", 2, "353"));
        testCases.add(new TestCase("1234", 2, "1313"));

        // Additional Test Cases
        testCases.add(new TestCase("999", 2, "999"));          // Test Case 5
        testCases.add(new TestCase("123456", 3, "124124"));    // Test Case 6
        testCases.add(new TestCase("100000", 1, "111111"));    // Test Case 7
        testCases.add(new TestCase("1299", 2, "1313"));        // Test Case 8
        testCases.add(new TestCase("1299", 1, "2222"));        // Test Case 9
        testCases.add(new TestCase("1999", 2, "2020"));        // Test Case 10
        testCases.add(new TestCase("2199", 2, "2222"));        // Test Case 11

        // Large Test Case
        StringBuilder largeOldCodeBuilder = new StringBuilder();
        for (int i = 0; i < 200000; i++) {
            largeOldCodeBuilder.append("1");
        }
        String largeOldCode = largeOldCodeBuilder.toString();
        StringBuilder largeNewCodeBuilder = new StringBuilder();
        for (int i = 0; i < 200000; i++) {
            largeNewCodeBuilder.append("1");
        }
        String largeNewCode = largeNewCodeBuilder.toString();
        testCases.add(new TestCase(largeOldCode, 100000, largeNewCode)); // Test Case 12

        // Run Test Cases
        int passed = 0;
        for (int i = 0; i < testCases.size(); i++) {
            TestCase tc = testCases.get(i);
            String result = findSmallestAppealing(tc.old_code, tc.k);
            if (result.equals(tc.expected)) {
                System.out.println("Test Case " + (i + 1) + ": PASS");
                passed++;
            } else {
                System.out.println("Test Case " + (i + 1) + ": FAIL");
                System.out.println("   Input: old_code = " + tc.old_code + ", k = " + tc.k);
                System.out.println("   Expected: " + tc.expected);
                System.out.println("   Got:      " + result);
            }
        }
        System.out.println("\nPassed " + passed + " out of " + testCases.size() + " test cases.");
    }

    /**
     * Helper class to store test cases.
     */
    static class TestCase {
        String old_code;
        int k;
        String expected;

        TestCase(String old_code, int k, String expected) {
            this.old_code = old_code;
            this.k = k;
            this.expected = expected;
        }
    }
}
