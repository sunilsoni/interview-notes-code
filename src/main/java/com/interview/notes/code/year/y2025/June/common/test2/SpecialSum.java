package com.interview.notes.code.year.y2025.June.common.test2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SpecialSum {

    /**
     * Compute maximum subset sum with no two chosen elements adjacent.
     *
     * @param ar List of positive integers (may be empty)
     * @return maximum sum with no adjacent elements chosen
     */
    public static int maxSum(List<Integer> ar) {
        // If list is empty, nothing to choose
        if (ar == null || ar.isEmpty()) {
            return 0;
        }
        // incl: max sum including previous element
        int incl = 0;
        // excl: max sum excluding previous element
        int excl = 0;
        // Iterate through each element
        for (int x : ar) {
            // tempExcl holds previous excl/incl max before update
            int newExcl = Math.max(incl, excl);
            // If we include current x, previous must have been excluded
            int newIncl = excl + x;
            // Update for next iteration
            incl = newIncl;
            excl = newExcl;
        }
        // Final answer is max of including or excluding last element
        return Math.max(incl, excl);
    }

    /**
     * Simple main to run test cases and a large random test for performance.
     * Prints PASS/FAIL for each case.
     */
    public static void main(String[] args) {
        // List of test cases: pair of input array and expected result
        List<TestCase> tests = new ArrayList<>();

        // Provided examples
        tests.add(new TestCase(new int[]{5, 5, 10, 1, 100}, 115));
        tests.add(new TestCase(new int[]{1, 2, 2, 1}, 3));
        tests.add(new TestCase(new int[]{4, 3, 6, 11, 8}, 18));

        // Additional edge cases
        tests.add(new TestCase(new int[]{}, 0)); // empty array
        tests.add(new TestCase(new int[]{7}, 7)); // single element
        tests.add(new TestCase(new int[]{5, 10}, 10)); // two elements: pick max
        tests.add(new TestCase(new int[]{10, 5}, 10));
        tests.add(new TestCase(new int[]{5, 5}, 5)); // equal elements
        tests.add(new TestCase(new int[]{2, 1, 1, 2}, 4)); // pick 2 + 2
        tests.add(new TestCase(new int[]{100, 1, 1, 100}, 200)); // pick both 100s
        tests.add(new TestCase(new int[]{1, 100, 1, 100, 1}, 200)); // pick both 100s

        // Case: all small values
        int nSmall = 100;
        int[] allOnes = new int[nSmall];
        Arrays.fill(allOnes, 1);
        // For all ones of length 100, maximum sum = ceil(100/2) = 50
        tests.add(new TestCase(allOnes, (nSmall + 1) / 2));

        // Run each test case
        System.out.println("Running fixed test cases:");
        for (TestCase tc : tests) {
            // Convert int[] to List<Integer>
            List<Integer> list = new ArrayList<>(tc.input.length);
            for (int v : tc.input) {
                list.add(v);
            }
            // Compute
            int actual = maxSum(list);
            // Check PASS/FAIL
            if (actual == tc.expected) {
                System.out.printf("PASS: input=%s expected=%d actual=%d%n",
                        Arrays.toString(tc.input), tc.expected, actual);
            } else {
                System.out.printf("FAIL: input=%s expected=%d actual=%d%n",
                        Arrays.toString(tc.input), tc.expected, actual);
            }
        }

        // Large random test for performance
        int largeSize = 100_000;
        System.out.println("\nRunning large random test of size " + largeSize);
        // Generate random values 1..500
        Random rand = new Random(12345); // fixed seed for reproducibility
        // Using Java 8 streams to generate an int[] of length largeSize
        int[] largeArray = rand.ints(largeSize, 1, 501).toArray();
        // Convert to List<Integer> (note: this has overhead; for pure performance measurement
        // one could write a version taking int[], but here we measure list-version)
        List<Integer> largeList = new ArrayList<>(largeSize);
        for (int v : largeArray) {
            largeList.add(v);
        }
        long start = System.nanoTime();
        int resultLarge = maxSum(largeList);
        long end = System.nanoTime();
        double elapsedMs = (end - start) / 1_000_000.0;
        System.out.printf("Large test result = %d; time = %.2f ms%n", resultLarge, elapsedMs);
    }

    /**
     * Helper class to hold a test case.
     */
    private static class TestCase {
        int[] input;
        int expected;

        TestCase(int[] input, int expected) {
            this.input = input;
            this.expected = expected;
        }
    }
}
