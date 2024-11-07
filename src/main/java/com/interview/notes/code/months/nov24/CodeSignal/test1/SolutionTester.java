package com.interview.notes.code.months.nov24.CodeSignal.test1;

import java.util.Arrays;

public class SolutionTester {

    /**
     * Implements the algorithm as described in the problem statement.
     *
     * @param numbers An array of non-negative integers.
     * @return The sum of values obtained from step 3 of the algorithm.
     */
    public static int solution(int[] numbers) {
        int result = 0;
        int n = numbers.length;

        while (true) {
            // Step 1: Find the index of the leftmost non-zero element
            int i = -1;
            for (int idx = 0; idx < n; idx++) {
                if (numbers[idx] != 0) {
                    i = idx;
                    break;
                }
            }

            // If no non-zero element found, finish the algorithm
            if (i == -1) {
                break;
            }

            int x = numbers[i];

            // Step 2: Attempt to subtract x from each element starting at index i
            int j = i;
            while (j < n) {
                if (numbers[j] < x) {
                    break;
                }
                numbers[j] -= x;
                j++;
            }

            // Step 3: Add x to the final result
            result += x;
            // Step 4: Repeat the process
        }

        return result;
    }

    /**
     * A helper method to run a single test case.
     *
     * @param testCaseNumber The identifier for the test case.
     * @param input          The input array for the solution.
     * @param expected       The expected output.
     */
    private static void runTestCase(int testCaseNumber, int[] input, int expected) {
        // Make a copy of the input to avoid modifying the original test case
        int[] inputCopy = Arrays.copyOf(input, input.length);
        int actual = solution(inputCopy);
        if (actual == expected) {
            System.out.println("Test Case " + testCaseNumber + ": PASS");
        } else {
            System.out.println("Test Case " + testCaseNumber + ": FAIL");
            System.out.println("   Input: " + Arrays.toString(input));
            System.out.println("   Expected: " + expected + ", Actual: " + actual);
        }
    }

    public static void main(String[] args) {
        // Define test cases
        int[][] testInputs = {
                {3, 3, 5, 2, 3},           // Example 1
                {5, 5, 5},                 // Example 2
                {0, 0, 0, 0},              // All zeros
                {1},                       // Single element
                {0, 1, 0, 1, 0, 1},        // Alternating zeros and ones
                {10, 20, 30, 40, 50},      // Increasing sequence
                {1000000, 1000000, 1000000}, // Large numbers
                // Large input case: 100 elements, each 1000000
                createLargeTestCase(100, 1000000)
        };

        int[] expectedOutputs = {
                6,      // Example 1
                5,      // Example 2
                0,      // All zeros
                1,      // Single element
                3,      // Alternating zeros and ones
                100,    // 10 + 20 + 30 + 40 (After first iteration: [0, 10, 20, 30, 40]; next: [0,0,10,20,30]; next: [0,0,0,10,20]; next: [0,0,0,0,10]; total: 10+20+30+40=100
                1000000, // All elements are 1000000, subtract once
                1000000  // Large input case: subtract 1000000 once
        };

        // Run all test cases
        for (int i = 0; i < testInputs.length; i++) {
            runTestCase(i + 1, testInputs[i], expectedOutputs[i]);
        }
    }

    /**
     * Creates a large test case with a specified size and element value.
     *
     * @param size  The number of elements in the array.
     * @param value The value to be repeated in the array.
     * @return An array of the specified size with all elements set to the given value.
     */
    private static int[] createLargeTestCase(int size, int value) {
        int[] largeTestCase = new int[size];
        Arrays.fill(largeTestCase, value);
        return largeTestCase;
    }
}
