package com.interview.notes.code.months.nov24.geico.test1;

import java.util.Arrays;
import java.util.List;

class Solution {
    /**
     * Main method to test the solution with various test cases.
     */
    public static void main(String[] args) {
        Solution sol = new Solution();
        int testCaseNumber = 1;
        boolean allPassed = true;

        // Define test cases
        List<TestCase> testCases = Arrays.asList(
                new TestCase(new int[]{4, 1, 2, 3}, 6),
                new TestCase(new int[]{1, 2, 3, 3, 2, 1, 5}, 7),
                new TestCase(new int[]{1000000000, 1, 2, 2, 1000000000, 1, 1000000000}, 999999998),
                new TestCase(new int[]{1, 2, 3, 4, 5}, 5),
                new TestCase(new int[]{5, 4, 3, 2, 1}, 5),
                new TestCase(new int[]{1}, 1),
                new TestCase(new int[]{1, 1, 1, 1}, 1),
                new TestCase(generateLargeTestCase(200000, 1000000000), 1000000000)
        );

        // Execute test cases
        for (TestCase tc : testCases) {
            long startTime = System.currentTimeMillis();
            int result = sol.solution(tc.input);
            long endTime = System.currentTimeMillis();
            boolean passed = result == tc.expectedOutput;
            allPassed = allPassed && passed;
            System.out.printf("Test Case %d: %s | Expected: %d, Got: %d | Time: %dms%n",
                    testCaseNumber,
                    passed ? "PASS" : "FAIL",
                    tc.expectedOutput,
                    result,
                    (endTime - startTime)
            );
            testCaseNumber++;
        }

        // Summary
        if (allPassed) {
            System.out.println("All test cases PASSED.");
        } else {
            System.out.println("Some test cases FAILED.");
        }
    }

    /**
     * Helper method to generate a large test case with specified size and price.
     *
     * @param size  The size of the test case.
     * @param price The price to fill the test case.
     * @return An array filled with the specified price.
     */
    private static int[] generateLargeTestCase(int size, int price) {
        int[] largeTest = new int[size];
        Arrays.fill(largeTest, price);
        return largeTest;
    }

    /**
     * Calculates the maximum possible income from buying and selling an asset over N days.
     *
     * @param A An array of integers representing the asset prices over N days.
     * @return The maximum income possible modulo 1,000,000,000.
     */
    public int solution(int[] A) {
        int N = A.length;
        if (N == 0) return 0;

        long income = A[0];
        int i = 1;
        while (i < N) {
            // Find the next valley (local minimum)
            while (i < N && A[i] >= A[i - 1]) {
                i++;
            }
            if (i == N) break;
            long buyPrice = A[i];
            i++;

            // Find the next peak (local maximum)
            while (i < N && A[i] >= A[i - 1]) {
                i++;
            }
            long sellPrice = A[i - 1];
            income += (sellPrice - buyPrice);
        }

        // Return the last nine digits of income
        return (int) (income % 1_000_000_000);
    }

    /**
     * Inner class to represent a test case.
     */
    private static class TestCase {
        int[] input;
        int expectedOutput;

        TestCase(int[] input, int expectedOutput) {
            this.input = input;
            this.expectedOutput = expectedOutput;
        }
    }
}
