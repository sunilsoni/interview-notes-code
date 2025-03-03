package com.interview.notes.code.year.y2025.march.caspex.test1;

import java.util.*;
import java.util.stream.*;

public class GrandNumberGame {

    /**
     * The solve method computes the maximum total score by choosing pairs of numbers
     * and multiplying their GCD by the operation number.
     * 
     * @param arr List of 2N positive integers.
     * @return Maximum total score.
     */
    public static int solve(List<Integer> arr) {
        int n = arr.size();
        // dp[mask] will store maximum score achievable from state 'mask'
        int[] dp = new int[1 << n];
        Arrays.fill(dp, -1);
        return helper(arr, 0, dp);
    }

    /**
     * Recursive helper that uses a bitmask to represent the state of used numbers.
     * 
     * @param arr The input array.
     * @param mask A bitmask where a bit value of 1 indicates the number has been used.
     * @param dp Memoization array.
     * @return Maximum score from the current state.
     */
    private static int helper(List<Integer> arr, int mask, int[] dp) {
        int n = arr.size();
        if (mask == (1 << n) - 1) {  // all numbers have been used
            return 0;
        }
        if (dp[mask] != -1) {
            return dp[mask];
        }
        int count = Integer.bitCount(mask);
        // Determine the current operation number (1-indexed)
        int op = count / 2 + 1;
        int maxScore = 0;
        // Optimization: always pick the first available number (i)
        for (int i = 0; i < n; i++) {
            if ((mask & (1 << i)) == 0) { // if arr[i] is unused
                for (int j = i + 1; j < n; j++) {
                    if ((mask & (1 << j)) == 0) { // if arr[j] is unused
                        int newMask = mask | (1 << i) | (1 << j);
                        int currentScore = op * gcd(arr.get(i), arr.get(j)) + helper(arr, newMask, dp);
                        maxScore = Math.max(maxScore, currentScore);
                    }
                }
                // Break after handling the first unused index to avoid redundant reordering.
                break;
            }
        }
        dp[mask] = maxScore;
        return maxScore;
    }

    /**
     * Euclidean algorithm to compute the greatest common divisor (GCD) of two numbers.
     */
    private static int gcd(int a, int b) {
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

    /**
     * A simple method to run test cases and print whether they pass or fail.
     */
    public static void runTests() {
        // List of test cases: each test case is a pair of input (List<Integer>)
        // and expected output.
        List<TestCase> testCases = new ArrayList<>();

        // Provided test cases
        testCases.add(new TestCase(Arrays.asList(3, 4, 9, 5), 7, "Example #1"));
        testCases.add(new TestCase(Arrays.asList(1, 2, 3, 4, 5, 6), 14, "Example #2"));

        // Edge Test: minimal input (N=1)
        testCases.add(new TestCase(Arrays.asList(2, 3), 1, "Edge Case: [2,3]"));
        testCases.add(new TestCase(Arrays.asList(2, 4), 2, "Edge Case: [2,4]"));

        // Additional Test: all numbers same
        // For [2,2,2,2]: best pairing is (2,2) for op1 and (2,2) for op2.
        // Score: 1*2 + 2*2 = 6.
        testCases.add(new TestCase(Arrays.asList(2, 2, 2, 2), 6, "All Same Numbers"));

        // Large data test: N=10, 20 numbers.
        // We use random positive integers between 1 and 100.
        Random rand = new Random();
        List<Integer> largeInput = IntStream.range(0, 20)
                .mapToObj(i -> rand.nextInt(100) + 1)
                .collect(Collectors.toList());
        // Since the maximum score is not predetermined for random input, we just run the method.
        testCases.add(new TestCase(largeInput, -1, "Large Random Input"));

        // Execute test cases
        for (TestCase tc : testCases) {
            int result = solve(tc.input);
            if (tc.expected == -1) {
                // For random test case, simply output the result.
                System.out.println(tc.description + " - Output: " + result);
            } else {
                if (result == tc.expected) {
                    System.out.println(tc.description + " - PASS");
                } else {
                    System.out.println(tc.description + " - FAIL (Expected: " + tc.expected + ", Got: " + result + ")");
                }
            }
        }
    }

    /**
     * Inner class to store test case information.
     */
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

    /**
     * Main method to run all test cases.
     */
    public static void main(String[] args) {
        runTests();
    }
}