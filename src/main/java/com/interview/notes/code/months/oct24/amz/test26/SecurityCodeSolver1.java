package com.interview.notes.code.months.oct24.amz.test26;

import java.util.*;

public class SecurityCodeSolver1 {

    /*
     * Complete the 'findSecurityCode' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts the following parameters:
     *  1. STRING code
     *  2. LONG_INTEGER k
     */
    public static String findSecurityCode(String code, long k) {
        char[] chars = code.toCharArray();
        long n = chars.length;
        long zeroCount = 0;

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '0') {
                zeroCount++;
            } else if (chars[i] == '1') {
                if (zeroCount == 0) {
                    continue;
                }
                long moveLeft = Math.min(zeroCount, k);
                int targetPos = i - (int) moveLeft;
                if (moveLeft > 0) {
                    chars[i] = '0';
                    chars[targetPos] = '1';
                    k -= moveLeft;
                    zeroCount -= moveLeft;
                }
                if (k == 0) {
                    break;
                }
            }
        }
        return new String(chars);
    }

    // Test case class to hold input and expected output
    static class TestCase {
        String code;
        long k;
        String expected;

        TestCase(String code, long k, String expected) {
            this.code = code;
            this.k = k;
            this.expected = expected;
        }
    }

    public static void main(String[] args) {
        List<TestCase> testCases = new ArrayList<>();

        // Sample Case 0
        testCases.add(new TestCase("0010", 5, "1000"));

        // Sample Case 1
        testCases.add(new TestCase("111", 2, "111"));

        // Example from Problem Description
        testCases.add(new TestCase("00100101", 4, "10010001"));

        // Edge Case: All '0's
        testCases.add(new TestCase("00000", 10, "00000"));

        // Edge Case: All '1's
        testCases.add(new TestCase("11111", 10, "11111"));

        // Edge Case: Alternating '0' and '1'
        testCases.add(new TestCase("0101010101", 5, "1010101000"));

        // Edge Case: k is 0
        testCases.add(new TestCase("1010", 0, "1010"));

        // Edge Case: k is very large
        testCases.add(new TestCase("1001", 1000000000000L, "1100"));

        // Edge Case: Single character '0'
        testCases.add(new TestCase("0", 1, "0"));

        // Edge Case: Single character '1'
        testCases.add(new TestCase("1", 1, "1"));

        // Large Input Case
        StringBuilder largeCodeBuilder = new StringBuilder();
        for (int i = 0; i < 1000000; i++) {
            largeCodeBuilder.append('0');
        }
        for (int i = 0; i < 1000000; i++) {
            largeCodeBuilder.append('1');
        }
        String largeCode = largeCodeBuilder.toString();
        // Expected: All '1's followed by all '0's, which is already the case
        testCases.add(new TestCase(largeCode, 1000000, largeCode));

        // Another Large Input Case: '1's interleaved with '0's
        StringBuilder largeCodeBuilder2 = new StringBuilder();
        for (int i = 0; i < 500000; i++) {
            largeCodeBuilder2.append("10");
        }
        String largeCode2 = largeCodeBuilder2.toString();
        // After sufficient swaps, all '1's should be at the front
        StringBuilder expectedLargeCode2 = new StringBuilder();
        for (int i = 0; i < 500000; i++) {
            expectedLargeCode2.append('1');
        }
        for (int i = 0; i < 500000; i++) {
            expectedLargeCode2.append('0');
        }
        testCases.add(new TestCase(largeCode2, 1000000000L, expectedLargeCode2.toString()));

        // Run all test cases
        int passed = 0;
        for (int idx = 0; idx < testCases.size(); idx++) {
            TestCase tc = testCases.get(idx);
            String output = findSecurityCode(tc.code, tc.k);
            boolean isPass = output.equals(tc.expected);
            if (isPass) {
                System.out.println("Test case " + (idx + 1) + ": PASS");
                passed++;
            } else {
                System.out.println("Test case " + (idx + 1) + ": FAIL");
                System.out.println("   Input: code = \"" + tc.code.substring(0, Math.min(tc.code.length(), 20)) + (tc.code.length() > 20 ? "..." : "") + "\", k = " + tc.k);
                System.out.println("   Expected: \"" + (tc.expected.length() > 20 ? tc.expected.substring(0, 20) + "..." : tc.expected) + "\"");
                System.out.println("   Got:      \"" + (output.length() > 20 ? output.substring(0, 20) + "..." : output) + "\"");
            }
        }
        System.out.println("\nPassed " + passed + " out of " + testCases.size() + " test cases.");
    }
}
