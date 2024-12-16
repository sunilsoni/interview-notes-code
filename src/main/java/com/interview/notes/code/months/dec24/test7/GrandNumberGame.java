package com.interview.notes.code.months.dec24.test7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GrandNumberGame {
    public static int solve(List<Integer> arr) {
        int n = arr.size() / 2;
        boolean[] used = new boolean[arr.size()];
        return findMaxScore(arr, used, 1, n);
    }

    private static int findMaxScore(List<Integer> arr, boolean[] used, int operation, int remainingOps) {
        if (remainingOps == 0) return 0;

        int maxScore = 0;
        int n = arr.size();

        for (int i = 0; i < n; i++) {
            if (used[i]) continue;
            for (int j = i + 1; j < n; j++) {
                if (used[j]) continue;

                used[i] = used[j] = true;
                long currentGcd = gcd(arr.get(i), arr.get(j));
                // Handle potential overflow
                long currentScore = (long) operation * currentGcd;
                if (currentScore > Integer.MAX_VALUE) {
                    currentScore = Integer.MAX_VALUE;
                }
                int nextScore = findMaxScore(arr, used, operation + 1, remainingOps - 1);
                maxScore = Math.max(maxScore, (int) currentScore + nextScore);
                used[i] = used[j] = false;
            }
        }

        return maxScore;
    }

    private static long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static void main(String[] args) {
        List<TestCase> testCases = new ArrayList<>();

        // Test case 1: Example from problem
        testCases.add(new TestCase(
                Arrays.asList(3, 4, 9, 5),
                7,
                "Basic example from problem"
        ));

        // Test case 2: Second example
        testCases.add(new TestCase(
                Arrays.asList(1, 2, 3, 4, 5, 6),
                14,
                "Second example from problem"
        ));

        // Test case 3: Even numbers
        testCases.add(new TestCase(
                Arrays.asList(2, 4, 6, 8),
                6,
                "Even numbers"
        ));

        // Test case 4: Large numbers
        testCases.add(new TestCase(
                Arrays.asList(1000000000, 999999999, 999999998, 999999997),
                1999999998,
                "Large numbers"
        ));

        // Test case 5: Prime numbers
        testCases.add(new TestCase(
                Arrays.asList(2, 3, 5, 7),
                3,
                "Prime numbers"
        ));

        // Additional test case for edge cases
        testCases.add(new TestCase(
                Arrays.asList(1, 1, 1, 1),
                3,
                "All same numbers"
        ));

        // Run tests
        int passedTests = 0;
        for (int i = 0; i < testCases.size(); i++) {
            TestCase tc = testCases.get(i);
            long startTime = System.nanoTime();
            int result = solve(tc.input);
            long endTime = System.nanoTime();
            boolean passed = result == tc.expected;

            System.out.printf("Test Case %d (%s): %s\n",
                    i + 1,
                    tc.description,
                    passed ? "PASS" : "FAIL"
            );

            if (!passed) {
                System.out.println("Input: " + tc.input);
                System.out.println("Expected: " + tc.expected);
                System.out.println("Got: " + result);
            }

            System.out.printf("Execution time: %.3f ms\n",
                    (endTime - startTime) / 1_000_000.0);

            if (passed) passedTests++;
            System.out.println();
        }

        System.out.printf("Total: %d/%d tests passed\n",
                passedTests, testCases.size());
    }

    static class TestCase {
        List<Integer> input;
        int expected;
        String description;

        TestCase(List<Integer> input, int expected, String description) {
            this.input = input;
            this.expected = expected;
            this.description = description;
        }
    }
}
