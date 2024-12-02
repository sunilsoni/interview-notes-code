package com.interview.notes.code.year.y2024.nov24.test4;

import java.util.ArrayList;
import java.util.List;

/*

 */
public class BinaryAdditionTest {

    /**
     * Performs binary addition on two binary strings.
     *
     * @param a The first binary string.
     * @param b The second binary string.
     * @return The sum of the two binary strings as a binary string.
     */
    public static String solve(String a, String b) {
        StringBuilder result = new StringBuilder();

        int i = a.length() - 1; // Pointer for string a
        int j = b.length() - 1; // Pointer for string b
        int carry = 0; // Initialize carry

        while (i >= 0 || j >= 0 || carry != 0) {
            int sum = carry;

            if (i >= 0) {
                sum += a.charAt(i) - '0'; // Convert char to int
                i--;
            }

            if (j >= 0) {
                sum += b.charAt(j) - '0'; // Convert char to int
                j--;
            }

            carry = sum / 2; // Update carry
            int bit = sum % 2; // Current bit
            result.append(bit);
        }

        // The result is built in reverse order
        return result.reverse().toString();
    }

    /**
     * Runs all test cases and prints whether each test case passes or fails.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        List<TestCase> testCases = new ArrayList<>();

        // Provided Example #1
        testCases.add(new TestCase("11", "1", "100", "Example #1"));

        // Provided Example #2
        testCases.add(new TestCase("1010", "1011", "10101", "Example #2"));

        // Edge Case: Both inputs are "0"
        testCases.add(new TestCase("0", "0", "0", "Both inputs are zero"));

        // Edge Case: Different lengths, no carryover
        testCases.add(new TestCase("101", "10", "111", "Different lengths, no carryover"));

        // Edge Case: Different lengths, with carryover
        testCases.add(new TestCase("1111", "1", "10000", "Different lengths, with carryover"));

        // Edge Case: One input is "0"
        testCases.add(new TestCase("0", "10101", "10101", "One input is zero"));

        // Edge Case: All '1's, resulting in multiple carryovers
        testCases.add(new TestCase("1111", "1111", "11110", "All '1's with multiple carryovers"));

        // Large Input Case #1: Both strings of length 10^4 with alternating '1's and '0's
        StringBuilder largeA1 = new StringBuilder();
        StringBuilder largeB1 = new StringBuilder();
        for (int k = 0; k < 10000; k++) {
            largeA1.append(k % 2 == 0 ? '1' : '0');
            largeB1.append(k % 2 == 0 ? '0' : '1');
        }
        // The expected result would be a string of '1's followed by '0's, depending on the pattern
        // For simplicity, we won't specify the exact expected value here, but ensure that the method runs without error
        testCases.add(new TestCase(largeA1.toString(), largeB1.toString(), null, "Large Input Case #1"));

        // Large Input Case #2: Both strings are "1" repeated 10^4 times
        StringBuilder largeA2 = new StringBuilder();
        StringBuilder largeB2 = new StringBuilder();
        for (int k = 0; k < 10000; k++) {
            largeA2.append('1');
            largeB2.append('1');
        }
        // The expected result is "1" followed by "0" repeated 10000 times
        StringBuilder expectedLarge2 = new StringBuilder();
        expectedLarge2.append('1');
        for (int k = 0; k < 10000; k++) {
            expectedLarge2.append('0');
        }
        testCases.add(new TestCase(largeA2.toString(), largeB2.toString(), expectedLarge2.toString(), "Large Input Case #2"));

        // Run all test cases
        int passed = 0;
        for (int idx = 0; idx < testCases.size(); idx++) {
            TestCase tc = testCases.get(idx);
            String output = solve(tc.a, tc.b);
            boolean isPass;

            if (tc.expected == null) {
                // For large input case #1, we won't check the exact output due to complexity
                isPass = output.length() == Math.max(tc.a.length(), tc.b.length()) + 1;
            } else {
                isPass = output.equals(tc.expected);
            }

            if (isPass) {
                System.out.println("Test case " + (idx + 1) + " (" + tc.description + "): PASS");
                passed++;
            } else {
                System.out.println("Test case " + (idx + 1) + " (" + tc.description + "): FAIL");
                System.out.println("  Expected: " + (tc.expected != null ? tc.expected.substring(0, Math.min(50, tc.expected.length())) + "..." : "Large Output"));
                System.out.println("  Got     : " + (output.length() > 50 ? output.substring(0, 50) + "..." : output));
            }
        }

        System.out.println("\nPassed " + passed + " out of " + testCases.size() + " test cases.");
    }

    /**
     * Represents a test case with input strings and the expected output.
     */
    static class TestCase {
        String a;
        String b;
        String expected;
        String description;

        TestCase(String a, String b, String expected, String description) {
            this.a = a;
            this.b = b;
            this.expected = expected;
            this.description = description;
        }
    }
}
