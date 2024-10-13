package com.interview.notes.code.months.oct24.test11;

import java.util.*;

public class MaximizeCPU {

    /*
     * Complete the 'maximizeCPU' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     * 1. INTEGER_ARRAY requirements
     * 2. INTEGER processingCapacity
     */

    public static int maximizeCPU(List<Integer> requirements, int processingCapacity) {
        int n = requirements.size();
        int mid = n / 2;

        // Split the list into two halves
        List<Integer> leftList = requirements.subList(0, mid);
        List<Integer> rightList = requirements.subList(mid, n);

        // Generate all possible sums for each half
        List<Long> leftSums = generateSubsetSums(leftList);
        List<Long> rightSums = generateSubsetSums(rightList);

        // Sort the rightSums for binary search
        Collections.sort(rightSums);

        long maxSum = 0;

        // For each sum in leftSums, find the best match in rightSums
        for (long leftSum : leftSums) {
            if (leftSum > processingCapacity) {
                continue;
            }
            long remaining = processingCapacity - leftSum;
            int idx = upperBound(rightSums, remaining);
            if (idx >= 0) {
                long totalSum = leftSum + rightSums.get(idx);
                if (totalSum <= processingCapacity && totalSum > maxSum) {
                    maxSum = totalSum;
                }
            }
        }

        return (int) maxSum;
    }

    // Generate all possible subset sums for a list
    private static List<Long> generateSubsetSums(List<Integer> nums) {
        List<Long> sums = new ArrayList<>();
        int n = nums.size();
        int total = 1 << n; // 2^n subsets

        for (int i = 0; i < total; i++) {
            long sum = 0;
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    sum += nums.get(j);
                }
            }
            sums.add(sum);
        }
        return sums;
    }

    // Find the index of the largest value less than or equal to target
    private static int upperBound(List<Long> list, long target) {
        int left = 0;
        int right = list.size() - 1;
        int result = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (list.get(mid) <= target) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result;
    }

    // Main method to run test cases
    public static void main(String[] args) {
        runTests();
    }

    // Method to run all test cases
    private static void runTests() {
        // Sample Test Case 0
        List<Integer> requirements0 = Arrays.asList(2, 9, 7);
        int processingCapacity0 = 15;
        int expected0 = 11;
        test(requirements0, processingCapacity0, expected0, 0);

        // Sample Test Case 1
        List<Integer> requirements1 = Arrays.asList(7, 6, 9, 11);
        int processingCapacity1 = 25;
        int expected1 = 24;
        test(requirements1, processingCapacity1, expected1, 1);

        // Additional Test Cases
        List<Integer> requirements2 = Arrays.asList(15, 12, 3, 7, 8);
        int processingCapacity2 = 18;
        int expected2 = 18;
        test(requirements2, processingCapacity2, expected2, 2);

        // Edge Case: Empty requirements
        List<Integer> requirements3 = new ArrayList<>();
        int processingCapacity3 = 10;
        int expected3 = 0;
        test(requirements3, processingCapacity3, expected3, 3);

        // Edge Case: Single element equal to capacity
        List<Integer> requirements4 = Arrays.asList(10);
        int processingCapacity4 = 10;
        int expected4 = 10;
        test(requirements4, processingCapacity4, expected4, 4);

        // Large Data Test Case
        List<Integer> requirements5 = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            requirements5.add(1_000_000_000);
        }
        int processingCapacity5 = 2_000_000_000;
        int expected5 = 2_000_000_000;
        test(requirements5, processingCapacity5, expected5, 5);

        // Test Case: No valid subsets
        List<Integer> requirements6 = Arrays.asList(20, 30, 40);
        int processingCapacity6 = 10;
        int expected6 = 0;
        test(requirements6, processingCapacity6, expected6, 6);
    }

    // Helper method for testing
    private static void test(List<Integer> requirements, int processingCapacity, int expected, int testCaseNumber) {
        int result = maximizeCPU(requirements, processingCapacity);
        if (result == expected) {
            System.out.println("Test Case " + testCaseNumber + ": PASS");
        } else {
            System.out.println("Test Case " + testCaseNumber + ": FAIL");
            System.out.println("Expected: " + expected + ", Got: " + result);
        }
    }
}
