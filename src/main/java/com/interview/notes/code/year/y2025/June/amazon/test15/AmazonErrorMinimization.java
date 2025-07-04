package com.interview.notes.code.year.y2025.June.amazon.test15;

import java.util.Arrays;
import java.util.List;

public class AmazonErrorMinimization {
    private static final int MOD = 1_000_000_007;

    public static int getMinErrors(String errorString, int x, int y) {
        int n = errorString.length();
        char[] s = errorString.toCharArray();

        // Check two scenarios: replace all '!' with '0', then with '1', choose min.
        return Math.min(countErrors(s, '0', x, y), countErrors(s, '1', x, y));
    }

    private static int countErrors(char[] s, char replaceChar, int x, int y) {
        long zeroCount = 0, oneCount = 0, errors = 0;

        for (char c : s) {
            char current = (c == '!') ? replaceChar : c;

            if (current == '0') {
                errors = (errors + oneCount * y) % MOD;
                zeroCount++;
            } else {
                errors = (errors + zeroCount * x) % MOD;
                oneCount++;
            }
        }

        return (int) errors;
    }

    // Simple test method without JUnit
    public static void main(String[] args) {
        List<TestCase> testCases = Arrays.asList(
                new TestCase("0!1!1!", 2, 3, 10),
                new TestCase("!!!!!!!", 23, 47, 0),
                new TestCase("010101", 1, 1, 9),
                new TestCase("!0!1!0", 5, 10, 25),
                new TestCase("!", 1000, 1000, 0)
        );

        for (TestCase tc : testCases) {
            int result = getMinErrors(tc.errorString, tc.x, tc.y);
            System.out.printf("Test %s: Expected=%d, Actual=%d [%s]\n",
                    tc.errorString, tc.expected, result,
                    (result == tc.expected ? "PASS" : "FAIL"));
        }

        // Edge large data test
        char[] largeInput = new char[100000];
        Arrays.fill(largeInput, '!');
        int largeTestResult = getMinErrors(new String(largeInput), 100000, 100000);
        System.out.println("Large input test completed. Result = " + largeTestResult);
    }

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
