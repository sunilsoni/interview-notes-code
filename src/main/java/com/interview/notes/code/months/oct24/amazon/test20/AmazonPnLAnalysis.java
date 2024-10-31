package com.interview.notes.code.months.oct24.amazon.test20;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class AmazonPnLAnalysis {

    /**
     * Function to find the maximum number of PnL elements that can be made negative
     * while keeping cumulative sums strictly positive.
     *
     * @param PnL array of positive integers representing profit and loss
     * @return maximum number of PnL elements that can be negative
     */
    public static int getMaxNegativePnL(int[] PnL) {
        long cumulativeSum = PnL[0]; // Initialize cumulative sum with the first month's PnL
        int totalNegatives = 0; // Counter for negative PnL elements

        // Min-heap to store negative PnL elements
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int i = 1; i < PnL.length; i++) {
            cumulativeSum += -PnL[i]; // Assume flipping PnL[i] to negative
            minHeap.add(PnL[i]);
            totalNegatives++;

            // If cumulative sum becomes non-positive, undo flips starting with the smallest PnL
            while (cumulativeSum <= 0 && !minHeap.isEmpty()) {
                int smallestPnL = minHeap.poll();
                cumulativeSum += 2L * smallestPnL; // Undo the flip
                totalNegatives--;
            }
        }

        return totalNegatives;
    }

    /**
     * Main method for testing the getMaxNegativePnL function.
     */
    public static void main(String[] args) {
        // List of test cases
        List<TestCase> testCases = new ArrayList<>();

        // Sample Test Case 0
        testCases.add(new TestCase(
                new int[]{1, 1, 1, 1, 1},
                2,
                "Sample Case 0"
        ));

        // Sample Test Case 1
        testCases.add(new TestCase(
                new int[]{5, 2, 3, 5, 2, 3},
                3,
                "Sample Case 1"
        ));

        // Edge Case: All PnLs are the same
        testCases.add(new TestCase(
                new int[]{5, 5, 5, 5, 5},
                2,
                "Edge Case: All PnLs are the same"
        ));

        // Edge Case: Large PnLs
        testCases.add(new TestCase(
                new int[]{1000000000, 1000000000, 1000000000},
                1,
                "Edge Case: Large PnLs"
        ));

        // Large Input Case
        int n = 100000;
        int[] largePnL = new int[n];
        Arrays.fill(largePnL, 1);
        testCases.add(new TestCase(
                largePnL,
                n / 2,
                "Large Input Case"
        ));

        // Run and test each case
        for (TestCase testCase : testCases) {
            int result = getMaxNegativePnL(testCase.PnL);
            if (result == testCase.expectedOutput) {
                System.out.println(testCase.description + ": PASS");
            } else {
                System.out.println(testCase.description + ": FAIL (Expected " + testCase.expectedOutput + ", Got " + result + ")");
            }
        }
    }

    /**
     * Class to represent a test case.
     */
    static class TestCase {
        int[] PnL;
        int expectedOutput;
        String description;

        TestCase(int[] PnL, int expectedOutput, String description) {
            this.PnL = PnL;
            this.expectedOutput = expectedOutput;
            this.description = description;
        }
    }
}
