package com.interview.notes.code.year.y2024.oct24.amazon.test5;

import java.util.*;

public class Solution {

    /**
     * Function to find the maximum number of negative PnL entries
     * such that the cumulative PnL remains strictly positive.
     *
     * @param PnL List of positive integers representing PnL values.
     * @return The maximum number of PnL entries that can be negative.
     */
    public static int getMaxNegativePnl(List<Integer> PnL) {
        long cumSum = 0;
        int negCount = 0;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for (int pnl : PnL) {
            cumSum += pnl;
            // Attempt to negate the current PnL
            cumSum -= 2L * pnl;
            maxHeap.add(pnl);
            negCount++;

            // Ensure cumulative sum remains positive
            while (cumSum <= 0 && !maxHeap.isEmpty()) {
                int largestNegatedPnl = maxHeap.poll();
                cumSum += 2L * largestNegatedPnl;
                negCount--;
            }
        }

        return negCount;
    }

    public static void main(String[] args) {
        // Directly run test cases without using Scanner
        runTests();
    }

    /**
     * Method to run predefined test cases and check for pass/fail.
     */
    private static void runTests() {
        List<TestCase> testCases = new ArrayList<>();

        // Sample Test Case 0
        testCases.add(new TestCase(Arrays.asList(1, 1, 1, 1, 1), 2));

        // Sample Test Case 1
        testCases.add(new TestCase(Arrays.asList(5, 2, 3, 5, 2, 3), 3));

        // Additional Test Cases
        testCases.add(new TestCase(Arrays.asList(5, 3, 1, 2), 2));
        testCases.add(new TestCase(Arrays.asList(1), 0));
        testCases.add(new TestCase(Arrays.asList(10, 1, 1, 1, 1), 2));
        testCases.add(new TestCase(Arrays.asList(2, 2, 2, 2, 2), 2));
        testCases.add(new TestCase(Arrays.asList(1000000000, 1000000000, 1000000000), 1));

        // Large Input Test Case
        testCases.add(generateLargeTestCase(100000, 1));

        boolean allPassed = true;

        for (int i = 0; i < testCases.size(); i++) {
            TestCase tc = testCases.get(i);
            int output = getMaxNegativePnl(tc.PnL);
            if (output == tc.expected) {
                System.out.println("Test Case " + (i + 1) + ": PASS");
            } else {
                System.out.println("Test Case " + (i + 1) + ": FAIL (Expected " + tc.expected + ", Got " + output + ")");
                allPassed = false;
            }
        }

        if (allPassed) {
            System.out.println("All test cases passed.");
        } else {
            System.out.println("Some test cases failed.");
        }
    }

    /**
     * Helper method to generate a large test case.
     *
     * @param size  The size of the PnL list.
     * @param value The PnL value to use for all entries.
     * @return A TestCase object with the generated PnL list and expected result.
     */
    private static TestCase generateLargeTestCase(int size, int value) {
        List<Integer> pnlList = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            pnlList.add(value);
        }
        // For value = 1, the expected maximum negatives is size / 2
        int expectedNegatives = size / 2;
        return new TestCase(pnlList, expectedNegatives);
    }

    /**
     * Helper class to store test cases.
     */
    private static class TestCase {
        List<Integer> PnL;
        int expected;

        TestCase(List<Integer> PnL, int expected) {
            this.PnL = PnL;
            this.expected = expected;
        }
    }
}
