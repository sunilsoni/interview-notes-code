package com.interview.notes.code.year.y2025.jan.oracle.test3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MaximizeCPU {

    public static int maximizeCPU(List<Integer> requirements, int processingCapacity) {
        int n = requirements.size();
        int mid = n / 2;

        List<Long> firstHalf = new ArrayList<>();
        List<Long> secondHalf = new ArrayList<>();

        // Generate all possible sums for the first half
        generateAllSums(requirements, 0, mid, firstHalf);

        // Generate all possible sums for the second half
        generateAllSums(requirements, mid, n, secondHalf);

        // Sort the second half sums for binary search
        Collections.sort(secondHalf);

        long maxSum = 0;

        for (long sum1 : firstHalf) {
            if (sum1 > processingCapacity) continue;
            long remaining = processingCapacity - sum1;
            // Binary search to find the largest sum2 <= remaining
            int idx = upperBound(secondHalf, remaining);
            if (idx >= 0) {
                long sum2 = secondHalf.get(idx);
                long total = sum1 + sum2;
                if (total > maxSum) {
                    maxSum = total;
                }
            }
        }

        // Since the problem expects an integer, cast the result
        return (int) maxSum;
    }

    // Helper method to generate all possible subset sums
    private static void generateAllSums(List<Integer> requirements, int start, int end, List<Long> sums) {
        sums.add(0L);
        for (int i = start; i < end; i++) {
            int current = requirements.get(i);
            int size = sums.size();
            for (int j = 0; j < size; j++) {
                long newSum = sums.get(j) + current;
                sums.add(newSum);
            }
        }
    }

    // Helper method to perform upper bound binary search
    private static int upperBound(List<Long> list, long key) {
        int left = 0;
        int right = list.size() - 1;
        int result = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (list.get(mid) <= key) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result;
    }

    // Main method for testing
    public static void main(String[] args) {
        // Define test cases
        List<TestCase> testCases = Arrays.asList(
                new TestCase(
                        Arrays.asList(7, 6, 9, 11),
                        25,
                        24
                ),
                new TestCase(
                        Arrays.asList(2, 9, 7),
                        15,
                        11
                ),
                new TestCase(
                        Arrays.asList(15, 12, 3, 7, 8),
                        18,
                        18
                ),
                new TestCase(
                        Arrays.asList(20, 30, 40),
                        10,
                        0
                ),
                new TestCase(
                        Arrays.asList(1),
                        1,
                        1
                ),
                new TestCase(
                        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                                11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
                                21, 22, 23, 24, 25, 26, 27, 28, 29, 30,
                                31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42),
                        1000000000,
                        903
                )
        );

        // Run test cases
        int passed = 0;
        for (int i = 0; i < testCases.size(); i++) {
            TestCase tc = testCases.get(i);
            int result = maximizeCPU(tc.requirements, tc.processingCapacity);
            if (result == tc.expected) {
                System.out.println("Test Case " + (i + 1) + ": PASS");
                passed++;
            } else {
                System.out.println("Test Case " + (i + 1) + ": FAIL (Expected " + tc.expected + ", Got " + result + ")");
            }
        }
        System.out.println(passed + " out of " + testCases.size() + " test cases passed.");
    }

    // Helper class to define test cases
    static class TestCase {
        List<Integer> requirements;
        int processingCapacity;
        int expected;

        TestCase(List<Integer> requirements, int processingCapacity, int expected) {
            this.requirements = requirements;
            this.processingCapacity = processingCapacity;
            this.expected = expected;
        }
    }
}
