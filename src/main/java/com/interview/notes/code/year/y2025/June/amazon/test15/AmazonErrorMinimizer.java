package com.interview.notes.code.year.y2025.June.amazon.test15;

import java.util.*;

public class AmazonErrorMinimizer {
    private static final int MOD = 1_000_000_007;

    public static int getMinErrors(String errorString, int x, int y) {
        // Try two options: replace all ! with 0, and all ! with 1
        String all0 = errorString.replace('!', '0');
        String all1 = errorString.replace('!', '1');
        long err0 = calcError(all0, x, y);
        long err1 = calcError(all1, x, y);
        return (int) (Math.min(err0, err1) % MOD);
    }

    // Counts "01" and "10" subsequences in string
    private static long calcError(String s, int x, int y) {
        long count0 = 0, count1 = 0, res01 = 0, res10 = 0;
        for (char c : s.toCharArray()) {
            if (c == '0') {
                count0++;
                res10 += count1;
                if (res10 >= MOD) res10 -= MOD;
            } else if (c == '1') {
                count1++;
                res01 += count0;
                if (res01 >= MOD) res01 -= MOD;
            }
        }
        long total = (res01 % MOD * x % MOD + res10 % MOD * y % MOD) % MOD;
        return total;
    }

    // --- Main method with tests ---
    public static void main(String[] args) {
        List<TestCase> testCases = Arrays.asList(
                new TestCase("0!1!1!", 2, 3, 10),
                new TestCase("!!!!!!!", 23, 47, 0),
                new TestCase("0101", 1, 1, 4),
                new TestCase("0!0!1!0", 5, 7, 5),
                // Large case: all zeros, should be 0
                new TestCase("00000".replace("0", "!"), 10, 100, 0),
                // Large mixed, should pass in reasonable time
                new TestCase("0!1!0!1!0!1!0!1!0!1!", 1, 1, 25)
        );

        int pass = 0;
        for (TestCase tc : testCases) {
            int actual = getMinErrors(tc.errorString, tc.x, tc.y);
            boolean isPass = actual == tc.expected;
            System.out.printf("Input: %-12s | x=%-3d | y=%-3d | Expected: %-5d | Actual: %-5d | %s\n",
                    tc.errorString, tc.x, tc.y, tc.expected, actual, isPass ? "PASS" : "FAIL");
            if (isPass) pass++;
        }
        System.out.println("Total Passed: " + pass + "/" + testCases.size());

        // Performance test: 100,000 '!'
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100_000; i++) sb.append('!');
        long start = System.currentTimeMillis();
        int result = getMinErrors(sb.toString(), 1, 1);
        long end = System.currentTimeMillis();
        System.out.println("Large Input Test: " + (result == 0 ? "PASS" : "FAIL") +
                " (Time: " + (end - start) + " ms)");
    }

    // Test case holder
    static class TestCase {
        String errorString;
        int x, y, expected;
        TestCase(String errorString, int x, int y, int expected) {
            this.errorString = errorString;
            this.x = x;
            this.y = y;
            this.expected = expected;
        }
    }
}
