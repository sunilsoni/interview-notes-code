package com.interview.notes.code.months.dec24.test7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinRemovalsToAvoidTriplets {

    /*
     * Implement method/function with name 'solve' below.
     * The function accepts following as parameters.
     * 1. ar is of type List<Integer>.
     * return int.
     */
    public static int solve(List<Integer> ar) {
        if (ar == null || ar.size() < 3) return 0;

        int removals = 0;
        int i = 0;

        while (i < ar.size() - 2) {
            int first = ar.get(i);
            int second = ar.get(i + 1);
            int third = ar.get(i + 2);

            // Check for strictly increasing
            if (first < second && second < third) {
                removals++;
                // Remove the middle element to disrupt the increasing sequence
                ar.remove(i + 1);
                // After removal, do not increment i to check for overlapping triplets
            }
            // Check for strictly decreasing
            else if (first > second && second > third) {
                removals++;
                // Remove the middle element to disrupt the decreasing sequence
                ar.remove(i + 1);
                // After removal, do not increment i to check for overlapping triplets
            } else {
                i++; // Move to the next triplet
            }
        }

        return removals;
    }

    public static void main(String[] args) {
        // Define test cases
        List<TestCase> testCases = new ArrayList<>();

        // Example Test Case 1
        testCases.add(new TestCase(
                Arrays.asList(1, 2, 4, 1, 2),
                1
        ));

        // Example Test Case 2
        testCases.add(new TestCase(
                Arrays.asList(1, 2, 3, 5),
                2
        ));

        // Additional Test Case 3: No removals needed
        testCases.add(new TestCase(
                Arrays.asList(1, 3, 2, 4, 3),
                0
        ));

        // Additional Test Case 4: All increasing
        testCases.add(new TestCase(
                Arrays.asList(1, 2, 3, 4, 5),
                2
        ));

        // Additional Test Case 5: All decreasing
        testCases.add(new TestCase(
                Arrays.asList(5, 4, 3, 2, 1),
                2
        ));

        // Additional Test Case 6: Alternating pattern
        testCases.add(new TestCase(
                Arrays.asList(1, 3, 2, 4, 3, 5, 4),
                0
        ));

        // Additional Test Case 7: Large input with no removals
        List<Integer> largeInputNoRemovals = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            largeInputNoRemovals.add(i % 2);
        }
        testCases.add(new TestCase(
                largeInputNoRemovals,
                0
        ));

        // Additional Test Case 8: Large input with maximum removals
        List<Integer> largeInputMaxRemovals = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            largeInputMaxRemovals.add(i);
        }
        // For strictly increasing sequence of 10000 elements, removals = 10000 - 2 = 9998
        testCases.add(new TestCase(
                largeInputMaxRemovals,
                9998
        ));

        // Run all test cases
        int passed = 0;
        for (int i = 0; i < testCases.size(); i++) {
            TestCase tc = testCases.get(i);
            // Create a mutable list for processing
            List<Integer> inputList = new ArrayList<>(tc.input);
            int result = solve(inputList);
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
        int expected;

        TestCase(List<Integer> input, int expected) {
            this.input = input;
            this.expected = expected;
        }
    }
}
