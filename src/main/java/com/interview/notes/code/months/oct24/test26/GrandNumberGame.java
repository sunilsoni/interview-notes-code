package com.interview.notes.code.months.oct24.test26;

import java.util.*;

/**
 * Grand Number Game Solver
 * <p>
 * Problem Description:
 * In a game scenario, you are presented with an array containing 2N positive integers.
 * You have N operations to manipulate these numbers. In each operation, you choose
 * any two positive integers from the array. The score for each operation is calculated
 * as the operation number multiplied by the GCD of the two chosen numbers. The total
 * score is the sum of the scores from all operations. The objective is to maximize
 * the total score.
 * <p>
 * Solution Approach:
 * Since N is up to 10, the total number of integers is up to 20. We can model this
 * problem using recursive dynamic programming with memoization. We use a bitmask
 * to represent the set of used numbers. For each recursive call, we consider all
 * possible pairs of unused numbers, compute the score for the current operation,
 * and recurse for the next operation. We memoize the results to avoid redundant
 * computations. This approach efficiently explores all possible pairings to find
 * the maximum total score.
 */
public class GrandNumberGame {

    // Memoization map to store computed results for bitmask states
    private static Map<Integer, Integer> memo;
    private static int N;
    private static int[] numbers;

    /**
     * Solves the Grand Number Game problem.
     *
     * @param arr List of integers representing the array.
     * @return The maximum total score.
     */
    public static int solve(List<Integer> arr) {
        N = arr.size() / 2;
        numbers = new int[2 * N];
        for (int i = 0; i < 2 * N; i++) {
            numbers[i] = arr.get(i);
        }
        memo = new HashMap<>();
        return dfs(0, N);
    }

    /**
     * Recursive function to compute the maximum score using dynamic programming.
     *
     * @param bitmask Bitmask representing the used numbers.
     * @param k       Current operation number.
     * @return The maximum score achievable from the current state.
     */
    private static int dfs(int bitmask, int k) {
        if (bitmask == (1 << (2 * N)) - 1) {
            return 0; // All numbers are used
        }
        if (memo.containsKey(bitmask)) {
            return memo.get(bitmask);
        }
        int maxScore = 0;
        // Find the first unused number
        int first = -1;
        for (int i = 0; i < 2 * N; i++) {
            if ((bitmask & (1 << i)) == 0) {
                first = i;
                break;
            }
        }
        // Try pairing the first unused number with all other unused numbers
        for (int j = first + 1; j < 2 * N; j++) {
            if ((bitmask & (1 << j)) == 0) {
                int newBitmask = bitmask | (1 << first) | (1 << j);
                int gcdValue = gcd(numbers[first], numbers[j]);
                int currentScore = k * gcdValue;
                int totalScore = currentScore + dfs(newBitmask, k - 1);
                maxScore = Math.max(maxScore, totalScore);
            }
        }
        memo.put(bitmask, maxScore);
        return maxScore;
    }

    /**
     * Computes the Greatest Common Divisor (GCD) of two integers using the
     * Euclidean algorithm.
     *
     * @param a First integer.
     * @param b Second integer.
     * @return The GCD of a and b.
     */
    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    /**
     * Main method to test the solve function with various test cases.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        // List of test cases
        List<TestCase> testCases = new ArrayList<>();

        // Test case 1: Example provided in the problem
        testCases.add(new TestCase(2, Arrays.asList(3, 4, 9, 5), 7));

        // Test case 2: Another example provided in the problem
        testCases.add(new TestCase(3, Arrays.asList(1, 2, 3, 4, 5, 6), 14));

        // Test case 3: Edge case with N = 1
        testCases.add(new TestCase(1, Arrays.asList(1, 1), 1));

        // Test case 4: All numbers are the same
        testCases.add(new TestCase(2, Arrays.asList(5, 5, 5, 5), 15));

        // Test case 5: All numbers are co-prime
        testCases.add(new TestCase(2, Arrays.asList(2, 3, 5, 7), 5));

        // Test case 6: Large numbers
        testCases.add(new TestCase(2, Arrays.asList(1000000000, 999999999, 999999998, 999999997), 2999999995L));

        // Test case 7: N = 10 with small numbers
        List<Integer> largeTestCase = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            largeTestCase.add(i);
        }
        testCases.add(new TestCase(10, largeTestCase, 167));

        // Run test cases
        for (int i = 0; i < testCases.size(); i++) {
            TestCase tc = testCases.get(i);
            int result = solve(tc.arr);
            boolean pass = result == tc.expected;
            System.out.println("Test Case " + (i + 1) + ": " + (pass ? "PASS" : "FAIL"));
            if (!pass) {
                System.out.println("Expected: " + tc.expected + ", Got: " + result);
            }
        }
    }

    /**
     * Helper class to store test cases.
     */
    static class TestCase {
        int N;
        List<Integer> arr;
        long expected;

        TestCase(int N, List<Integer> arr, long expected) {
            this.N = N;
            this.arr = arr;
            this.expected = expected;
        }
    }
}
