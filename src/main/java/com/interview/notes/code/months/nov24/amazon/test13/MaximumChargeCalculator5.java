package com.interview.notes.code.months.nov24.amazon.test13;

import java.util.*;

public class MaximumChargeCalculator5 {

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

        int n = charge.size();
        long max_initial = Long.MIN_VALUE;

        // Find the maximum individual charge.
        for (int c : charge) {
            max_initial = Math.max(max_initial, (long) c);
        }

        // Find the maximum sum of any two non-adjacent charges.
        long max_pair_sum = Long.MIN_VALUE;
        long max_so_far = charge.get(0);

        for (int i = 1; i < n; i++) {
            if (i >= 2) {
                max_pair_sum = Math.max(max_pair_sum, (long) charge.get(i) + max_so_far);
            }
            // Update max_so_far with the maximum element up to i-1.
            max_so_far = Math.max(max_so_far, (long) charge.get(i - 1));
        }

        // The final maximum charge is the maximum between the single maximum and the pair sum.
        // If no valid pair sum exists (i.e., n < 2), return the maximum initial charge.
        if (max_pair_sum == Long.MIN_VALUE) {
            return max_initial;
        } else {
            return Math.max(max_initial, max_pair_sum);
        }
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
            9 // Expected Output: 9
        ));

        // Example 2
        testCases.add(new TestCase(
            Arrays.asList(-1, 3, 2),
            3 // Expected Output: 3
        ));

        // Example 3
        testCases.add(new TestCase(
            Arrays.asList(-3, 1, 4, -1, 5, -9),
            9 // Expected Output: 9
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
            9 // Expected Output: 9 (3 +5 =8, but better to take maximum element5 or 4 +5=9)
        ));

        // Large Input Test Case
        List<Integer> largeInput = new ArrayList<>();
        for (int i = 0; i < 200000; i++) {
            largeInput.add(1); // All ones, maximum sum of non-adjacent pairs is2
        }
        testCases.add(new TestCase(
            largeInput,
            2 // Expected Output: 2
        ));

        // Edge Case: Two Elements
        testCases.add(new TestCase(
            Arrays.asList(5, 7),
            7 // Expected Output:7
        ));

        // Edge Case: Two Elements with Negative and Positive
        testCases.add(new TestCase(
            Arrays.asList(-5, 10),
            10 // Expected Output:10
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
