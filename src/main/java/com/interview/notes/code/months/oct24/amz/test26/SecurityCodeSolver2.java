package com.interview.notes.code.months.oct24.amz.test26;

import java.util.*;

public class SecurityCodeSolver2 {

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
        int n = chars.length;
        char[] result = new char[n];
        Arrays.fill(result, '0');

        // Collect all '1's positions
        List<Integer> ones = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (chars[i] == '1') {
                ones.add(i);
            }
        }

        int currentOne = 0; // Pointer to the current '1' in the list
        for (int pos = 0; pos < n && currentOne < ones.size(); pos++) {
            // Iterate through '1's to find the first '1' that can be moved to 'pos'
            while (currentOne < ones.size()) {
                int onePos = ones.get(currentOne);
                if (onePos < pos) {
                    // This '1' is already placed
                    currentOne++;
                    continue;
                }
                long distance = onePos - pos;
                if (distance <= k) {
                    // Move this '1' to 'pos'
                    result[pos] = '1';
                    k -= distance;
                    currentOne++;
                    break;
                } else {
                    // Cannot move any '1' to 'pos'
                    break;
                }
            }
            // If no '1' is moved to 'pos', it remains '0'
        }

        // Place the remaining '1's in their original positions shifted by the swaps done
        for (; currentOne < ones.size(); currentOne++) {
            int originalPos = ones.get(currentOne);
            long newPosLong = originalPos + k;
            int newPos = (int) Math.min(newPosLong, n - 1);
            // Find the next available position from newPos backwards
            while (newPos > 0 && result[newPos] == '1') {
                newPos--;
            }
            if (result[newPos] == '0') {
                result[newPos] = '1';
                k -= (originalPos - newPos);
                if (k < 0) break;
            }
        }

        return new String(result);
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

        // Test Case 6: Adjusted Expected Output
        // Original Expected: "1010101000"
        // Correct Expected: "1101000101"
        testCases.add(new TestCase("0101010101", 5, "1101000101"));

        // Edge Case: k is 0
        testCases.add(new TestCase("1010", 0, "1010"));

        // Edge Case: k is very large
        testCases.add(new TestCase("1001", 1000000000000L, "1100"));

        // Edge Case: Single character '0'
        testCases.add(new TestCase("0", 1, "0"));

        // Edge Case: Single character '1'
        testCases.add(new TestCase("1", 1, "1"));

        // Large Input Case: All '0's followed by all '1's
        StringBuilder largeCodeBuilder = new StringBuilder();
        for (int i = 0; i < 1000000; i++) {
            largeCodeBuilder.append('0');
        }
        for (int i = 0; i < 1000000; i++) {
            largeCodeBuilder.append('1');
        }
        String largeCode = largeCodeBuilder.toString();
        // Expected: Move as many '1's as possible to the front with k=2000000
        // Each '1' can move left by up to 1000000 positions, but k=2000000 allows moving first two '1's completely
        // Expected: "11" followed by 999998 '1's and 1000000 '0's
        StringBuilder expectedLargeCode = new StringBuilder();
        for (int i = 0; i < 2000000; i++) {
            if (i < 2) {
                expectedLargeCode.append('1');
            } else {
                expectedLargeCode.append('0');
            }
        }
        for (int i = 0; i < 999998; i++) {
            expectedLargeCode.append('1');
        }
        testCases.add(new TestCase(largeCode, 2000000L, expectedLargeCode.toString()));

        // Test Case 12: '1's interleaved with '0's
        StringBuilder largeCodeBuilder2 = new StringBuilder();
        for (int i = 0; i < 500000; i++) {
            largeCodeBuilder2.append("10");
        }
        String largeCode2 = largeCodeBuilder2.toString();
        // Expected: Move as many '1's as possible to the front with k=1000000000
        // Each '1' can move left by up to its position index
        // With k=1000000000, all '1's can be moved to the front
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
                System.out.println("   Input: code = \"" + abbreviate(tc.code) + "\", k = " + tc.k);
                System.out.println("   Expected: \"" + abbreviate(tc.expected) + "\"");
                System.out.println("   Got:      \"" + abbreviate(output) + "\"");
            }
        }
        System.out.println("\nPassed " + passed + " out of " + testCases.size() + " test cases.");
    }

    // Helper method to abbreviate long strings in output
    private static String abbreviate(String s) {
        if (s.length() <= 20) {
            return s;
        } else {
            return s.substring(0, 10) + "..." + s.substring(s.length() - 10);
        }
    }
}
