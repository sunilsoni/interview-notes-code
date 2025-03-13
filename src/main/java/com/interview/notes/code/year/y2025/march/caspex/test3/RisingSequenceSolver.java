package com.interview.notes.code.year.y2025.march.caspex.test3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RisingSequenceSolver {

    /**
     * Solves the rising sequence problem.
     *
     * @param B  The value to add to elements
     * @param ar The input sequence
     * @return The minimum number of times B needs to be added
     */
    public static long solve(int B, List<Integer> ar) {
        // Total count of additions required
        long totalAdditions = 0;

        // Process array from left to right starting from second element
        for (int i = 1; i < ar.size(); i++) {
            // Current and previous elements
            long current = ar.get(i);
            long previous = ar.get(i - 1);

            // If current element is not greater than previous
            if (current <= previous) {
                // Calculate the difference to make current > previous
                long diff = previous - current + 1;

                // Calculate how many times we need to add B
                long additions = (diff + B - 1) / B; // Ceiling division

                // Add B that many times to current element
                current += additions * B;
                ar.set(i, (int) current);

                // Add to our total count
                totalAdditions += additions;
            }
        }

        return totalAdditions;
    }

    public static void main(String[] args) {
        // Test cases
        testSolution();
    }

    /**
     * Tests the solution with the provided examples and additional test cases.
     */
    public static void testSolution() {
        // Example 1
        int B1 = 2;
        List<Integer> ar1 = Arrays.asList(1, 3, 3, 2);
        long expected1 = 3;

        // Example 2
        int B2 = 1;
        List<Integer> ar2 = Arrays.asList(1, 1);
        long expected2 = 1;

        // Additional test case - already rising sequence
        int B3 = 5;
        List<Integer> ar3 = Arrays.asList(1, 2, 3, 4, 5);
        long expected3 = 0;

        // Additional test case - large numbers
        int B4 = 1000;
        List<Integer> ar4 = Arrays.asList(1000, 1, 2000, 3);
        long expected4 = 3;

        // Large sequence test
        int B5 = 1;
        List<Integer> ar5 = new ArrayList<>();
        for (int i = 0; i < 2000; i++) {
            ar5.add(1);
        }
        // In this case, we need to add B (i-1) times to the ith element
        long expected5 = (1999L * 2000) / 2 - 1999; // Sum of 1 to 1999

        // Edge case - minimum length
        int B6 = 10;
        List<Integer> ar6 = Arrays.asList(5, 3);
        long expected6 = 1;

        // Run tests and print results
        runTest("Example 1", B1, ar1, expected1);
        runTest("Example 2", B2, ar2, expected2);
        runTest("Already Rising", B3, ar3, expected3);
        runTest("Large Numbers", B4, ar4, expected4);
        runTest("Large Sequence", B5, ar5, expected5);
        runTest("Edge Case - Min Length", B6, ar6, expected6);
    }

    /**
     * Runs a test case and prints result.
     */
    private static void runTest(String testName, int B, List<Integer> ar, long expected) {
        // Create a copy of the list to avoid modifying the original
        List<Integer> arCopy = new ArrayList<>(ar);

        long start = System.currentTimeMillis();
        long result = solve(B, arCopy);
        long end = System.currentTimeMillis();

        boolean passed = result == expected;

        System.out.println(testName + ": " + (passed ? "PASS" : "FAIL") +
                " (Expected: " + expected + ", Got: " + result +
                ", Time: " + (end - start) + "ms)");

        // For failed cases, print additional info
        if (!passed) {
            System.out.println("  Input array: " + ar);
            System.out.println("  B value: " + B);
            System.out.println("  Resulting array: " + arCopy);
        }
    }
}
