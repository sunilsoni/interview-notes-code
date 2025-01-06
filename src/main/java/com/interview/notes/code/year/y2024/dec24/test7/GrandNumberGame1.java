package com.interview.notes.code.year.y2024.dec24.test7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GrandNumberGame1 {
    public static int solve(List<Integer> arr) {
        int n = arr.size() / 2;
        boolean[] used = new boolean[arr.size()];
        return findMaxScore(arr, used, 1, n);
    }

    private static int findMaxScore(List<Integer> arr, boolean[] used, int operation, int remainingOps) {
        if (remainingOps == 0) return 0;

        int maxScore = 0;
        int n = arr.size();

        // Try all possible pairs
        for (int i = 0; i < n; i++) {
            if (used[i]) continue;
            for (int j = i + 1; j < n; j++) {
                if (used[j]) continue;

                // Use current pair
                used[i] = used[j] = true;
                int currentScore = operation * gcd(arr.get(i), arr.get(j));
                int nextScore = findMaxScore(arr, used, operation + 1, remainingOps - 1);
                maxScore = Math.max(maxScore, currentScore + nextScore);

                // Backtrack
                used[i] = used[j] = false;
            }
        }

        return maxScore;
    }

    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
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

        // Test case 3: Small numbers
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
