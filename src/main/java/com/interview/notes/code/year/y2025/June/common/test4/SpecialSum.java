package com.interview.notes.code.year.y2025.June.common.test4;

import java.util.*;
import java.util.stream.*;

public class SpecialSum {

    // Function to find the max sum with no two adjacent
    public static int maxSum(List<Integer> ar) {
        int n = ar.size();
        // Handle base cases
        if (n == 0) return 0;
        if (n == 1) return ar.get(0);

        // dp[i] = max sum from 0..i with no two selected adjacent
        int[] dp = new int[n];
        dp[0] = ar.get(0);
        dp[1] = Math.max(ar.get(0), ar.get(1));

        for (int i = 2; i < n; i++) {
            // Option 1: skip current, take dp[i-1]
            // Option 2: take current, add to dp[i-2]
            dp[i] = Math.max(dp[i-1], ar.get(i) + dp[i-2]);
        }
        return dp[n-1];
    }

    // Helper to parse input line (e.g., "5 5 10 1 100" to List<Integer>)
    private static List<Integer> parseLine(String line) {
        return Arrays.stream(line.trim().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    // Test framework using plain main method
    public static void main(String[] args) {
        // Each test: input, expected
        List<TestCase> tests = Arrays.asList(
            new TestCase("5 5 10 1 100", 115),
            new TestCase("1 2 2 1", 3),
            new TestCase("4 3 6 11 8", 18),
            new TestCase("10", 10), // Single element
            new TestCase("1 2", 2), // Two elements
            new TestCase("2 7 9 3 1", 12), // 2+9+1
            // Large data: alternating 500/1, should pick all 500s
            new TestCase(IntStream.range(0, 100).mapToObj(i -> i % 2 == 0 ? 500 : 1)
                    .map(String::valueOf).collect(Collectors.joining(" ")),
                    25000),
            // All ones
            new TestCase(IntStream.range(0, 100).mapToObj(i -> "1").collect(Collectors.joining(" ")),
                    50)
        );

        // Run all tests
        int pass = 0;
        for (int i = 0; i < tests.size(); i++) {
            TestCase t = tests.get(i);
            int result = maxSum(parseLine(t.input));
            boolean ok = result == t.expected;
            System.out.printf("Test #%d: %s | Expected: %d | Got: %d | %s\n",
                    i+1, t.input.length() < 40 ? t.input : "[LARGE]", t.expected, result, ok ? "PASS" : "FAIL");
            if (ok) pass++;
        }
        System.out.printf("Passed %d/%d tests\n", pass, tests.size());
    }

    // Simple test case structure
    static class TestCase {
        String input;
        int expected;
        TestCase(String input, int expected) {
            this.input = input;
            this.expected = expected;
        }
    }
}
