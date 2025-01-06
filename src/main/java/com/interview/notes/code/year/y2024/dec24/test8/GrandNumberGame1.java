package com.interview.notes.code.year.y2024.dec24.test8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GrandNumberGame1 {

    /*
     * Implement method/function with name 'solve' below.
     * The function accepts the following as parameters:
     * 1. arr is of type List<Integer>.
     * return long.
     */
    public static long solve(List<Integer> arr) {
        int N = arr.size() / 2; // Number of operations
        int size = arr.size();

        // Precompute GCD for all possible pairs to optimize recursion
        long[][] gcd = new long[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                gcd[i][j] = computeGCD(arr.get(i), arr.get(j));
                gcd[j][i] = gcd[i][j]; // Symmetric property
            }
        }

        // Initialize memoization array with -1 to indicate uncomputed states
        int memoSize = 1 << size; // 2^size possible states
        long[] memo = new long[memoSize];
        Arrays.fill(memo, -1L);

        // Start recursion with all numbers unused (mask = 0) and highest operation number = N
        return dfs(0, N, N, size, gcd, memo);
    }

    /**
     * Recursive DFS function with memoization.
     *
     * @param mask      Current bitmask representing used integers.
     * @param operation Current operation number to assign.
     * @param N         Total number of operations.
     * @param size      Total number of integers.
     * @param gcd       Precomputed GCDs for all pairs.
     * @param memo      Memoization array to cache results.
     * @return Maximum score achievable from the current state.
     */
    private static long dfs(int mask, int operation, int N, int size, long[][] gcd, long[] memo) {
        if (operation == 0) {
            // All operations have been assigned
            return 0;
        }
        if (memo[mask] != -1) {
            // Return cached result for the current state
            return memo[mask];
        }
        long maxScore = 0;
        // Find the first unused number to pair
        int firstUnused = -1;
        for (int i = 0; i < size; i++) {
            if ((mask & (1 << i)) == 0) {
                firstUnused = i;
                break;
            }
        }
        if (firstUnused == -1) {
            // No unused numbers left (should not occur if operation >0)
            return 0;
        }
        // Iterate through all possible pairings with the first unused number
        for (int j = firstUnused + 1; j < size; j++) {
            if ((mask & (1 << j)) == 0) {
                // Pair firstUnused and j
                int newMask = mask | (1 << firstUnused) | (1 << j);
                long currentScore = gcd[firstUnused][j] * operation; // Score for this pairing
                long totalScore = currentScore + dfs(newMask, operation - 1, N, size, gcd, memo); // Recurse for remaining
                if (totalScore > maxScore) {
                    maxScore = totalScore; // Update maximum score
                }
            }
        }
        memo[mask] = maxScore; // Cache the result for the current state
        return maxScore;
    }

    /**
     * Function to compute the Greatest Common Divisor (GCD) of two numbers.
     *
     * @param a First number.
     * @param b Second number.
     * @return GCD of a and b.
     */
    private static long computeGCD(long a, long b) {
        if (b == 0) {
            return a;
        }
        return computeGCD(b, a % b);
    }

    public static void main(String[] args) {
        // Define test cases
        List<TestCase> testCases = new ArrayList<>();

        // Example Test Case 1
        testCases.add(new TestCase(
                Arrays.asList(3, 4, 9, 5),
                7L
        ));

        // Example Test Case 2
        testCases.add(new TestCase(
                Arrays.asList(1, 2, 3, 4, 5, 6),
                14L
        ));

        // Additional Test Case 3: All GCDs are 1
        // Input: [2, 3, 5, 7]
        // Pairings:
        // (2,3)=1 *2 + (5,7)=1 *1 = 2 +1 =3
        testCases.add(new TestCase(
                Arrays.asList(2, 3, 5, 7),
                3L
        ));

        // Additional Test Case 4: All integers identical
        // Input: [6, 6, 6, 6]
        // Pairings:
        // (6,6)=6 *2 + (6,6)=6 *1 =12 +6=18
        testCases.add(new TestCase(
                Arrays.asList(6, 6, 6, 6),
                18L
        ));

        // Additional Test Case 5: Large input with maximum GCDs
        // All pairings have GCD=1000000000
        // N=10, total score = sum from 1 to 10 multiplied by 1e9 =55 *1e9=55000000000
        List<Integer> largeInputMaxGCD = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            largeInputMaxGCD.add(1000000000);
        }
        testCases.add(new TestCase(
                largeInputMaxGCD,
                55000000000L
        ));

        // Additional Test Case 6: Large input with varying GCDs
        // Input: [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20]
        // Expected Output: 365
        testCases.add(new TestCase(
                Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20),
                365L
        ));

        // Run all test cases
        int passed = 0;
        for (int i = 0; i < testCases.size(); i++) {
            TestCase tc = testCases.get(i);
            // Create a mutable list for processing
            List<Integer> inputList = new ArrayList<>(tc.input);
            long result = solve(inputList);
            if (result == tc.expected) {
                System.out.println("Test Case " + (i + 1) + ": PASS");
                passed++;
            } else {
                System.out.println("Test Case " + (i + 1) + ": FAIL");
                System.out.println("   Input Array: " + tc.input);
                System.out.println("   Expected: " + tc.expected);
                System.out.println("   Got: " + result);
            }
        }

        // Summary
        System.out.println("\nPassed " + passed + " out of " + testCases.size() + " test cases.");
    }

    // Helper class to define test cases
    static class TestCase {
        List<Integer> input;
        long expected;

        TestCase(List<Integer> input, long expected) {
            this.input = input;
            this.expected = expected;
        }
    }
}
