package com.interview.notes.code.months.nov24.amazon.test13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaximumChargeCalculator2 {

    /**
     * Function to compute the maximum charge that can be obtained.
     *
     * @param charge List of integer charges.
     * @return The maximum charge as a long.
     */
    public static long getMaximumCharge(List<Integer> charge) {
        if (charge == null || charge.isEmpty()) {
            // If the list is empty, return 0 as there's no charge.
            return 0;
        }

        long maxCharge = Long.MIN_VALUE;

        // Find the maximum individual charge.
        for (int c : charge) {
            maxCharge = Math.max(maxCharge, (long) c);
        }

        // Find the maximum sum of any two adjacent charges.
        for (int i = 0; i < charge.size() - 1; i++) {
            long sum = (long) charge.get(i) + (long) charge.get(i + 1);
            maxCharge = Math.max(maxCharge, sum);
        }

        return maxCharge;
    }

    /**
     * Main method to test the getMaximumCharge function with various test cases.
     */
    public static void main(String[] args) {
        // Define test cases
        List<TestCase> testCases = new ArrayList<>();

        // Example 1
        testCases.add(new TestCase(
                Arrays.asList(-2, 4, 9, 1, -1),
                13 // Expected Output: 13 (Explanation: 4 + 9 = 13)
        ));

        // Example 2
        testCases.add(new TestCase(
                Arrays.asList(-1, 3, 2),
                5 // Expected Output: 5 (3 + 2 = 5)
        ));

        // Example 3
        testCases.add(new TestCase(
                Arrays.asList(-3, 1, 4, -1, 5, -9),
                9 // Expected Output: 9 (4 + 5 = 9)
        ));

        // Additional Test Cases

        // Single Element
        testCases.add(new TestCase(
                Arrays.asList(10),
                10 // Expected Output: 10
        ));

        // All Negative Elements
        testCases.add(new TestCase(
                Arrays.asList(-5, -3, -10, -1),
                -1 // Expected Output: -1
        ));

        // Mixed Positive and Negative with Larger Sum
        testCases.add(new TestCase(
                Arrays.asList(2, -1, 3, 4, -2, 5),
                7 // Expected Output: 7 (3 + 4 = 7, or 5 + 2 = 7)
        ));

        // Large Input Test Case
        List<Integer> largeInput = new ArrayList<>();
        for (int i = 0; i < 200000; i++) {
            largeInput.add(1); // All ones, maximum sum of adjacent is 2
        }
        testCases.add(new TestCase(
                largeInput,
                2 // Expected Output: 2
        ));

        // Run all test cases
        int passed = 0;
        for (int i = 0; i < testCases.size(); i++) {
            TestCase tc = testCases.get(i);
            long result = getMaximumCharge(tc.charge);
            if (result == tc.expected) {
                System.out.println("Test Case " + (i + 1) + ": PASS");
                passed++;
            } else {
                System.out.println("Test Case " + (i + 1) + ": FAIL (Expected " + tc.expected + ", Got " + result + ")");
            }
        }

        System.out.println(passed + "/" + testCases.size() + " Test Cases Passed.");
    }

    /**
     * Helper class to define a test case.
     */
    static class TestCase {
        List<Integer> charge;
        long expected;

        TestCase(List<Integer> charge, long expected) {
            this.charge = charge;
            this.expected = expected;
        }
    }
}
