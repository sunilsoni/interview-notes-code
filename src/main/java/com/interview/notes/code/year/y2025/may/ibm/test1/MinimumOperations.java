package com.interview.notes.code.year.y2025.may.ibm.test1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//WORKING 100%
public class MinimumOperations {

    public static int getMinOperations(String s, int m, int k) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        int operations = 0;

        for (int i = 0; i <= n - m; ) {
            boolean isZeroSegment = true;
            for (int j = 0; j < m; j++) {
                if (chars[i + j] != '0') {
                    isZeroSegment = false;
                    break;
                }
            }
            if (isZeroSegment) {
                int flipStart = Math.min(i + m - 1, n - k);
                for (int j = flipStart; j < flipStart + k; j++) {
                    chars[j] = '1';
                }
                operations++;
                i = flipStart + k;
            } else {
                i++;
            }
        }

        return operations;
    }

    // Main method for testing
    public static void main(String[] args) {

        class TestCase {
            final String s;
            final int m;
            final int k;
            final int expected;

            TestCase(String s, int m, int k, int expected) {
                this.s = s;
                this.m = m;
                this.k = k;
                this.expected = expected;
            }
        }

        List<TestCase> testCases = Arrays.asList(
                new TestCase("000000", 3, 2, 1),
                new TestCase("10101", 1, 1, 2),
                new TestCase("10101", 2, 3, 0),
                new TestCase("00000", 2, 1, 2),
                new TestCase("00000", 2, 3, 1),
                new TestCase("000000", 2, 2, 2),
                new TestCase("000000000110011100", 2, 7, 2)
        );

        // Testing provided test cases
        for (int i = 0; i < testCases.size(); i++) {
            TestCase tc = testCases.get(i);
            int result = getMinOperations(tc.s, tc.m, tc.k);
            System.out.printf("Test Case %d: %s\n", i, (result == tc.expected ? "PASS" : "FAIL") + " (Expected: " + tc.expected + ", Got: " + result + ")");
        }

        // Large Data Test Case
        String largeTest = IntStream.range(0, 200000)
                .mapToObj(i -> "0")
                .collect(Collectors.joining());

        long start = System.currentTimeMillis();
        int largeTestResult = getMinOperations(largeTest, 1000, 500);
        long end = System.currentTimeMillis();
        System.out.printf("Large Test Case: Operations = %d, Time = %d ms\n", largeTestResult, (end - start));
    }
}