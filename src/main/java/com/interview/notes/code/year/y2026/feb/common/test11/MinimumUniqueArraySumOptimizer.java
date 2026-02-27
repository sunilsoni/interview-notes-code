package com.interview.notes.code.year.y2026.feb.common.test11;

import java.util.Arrays;
import java.util.List;

public class MinimumUniqueArraySumOptimizer {

    public static int getMinimumUniqueSum(List<Integer> arr) {
        int[] prev = {0};
        return arr.stream()
            .sorted()
            .mapToInt(x -> prev[0] = Math.max(x, prev[0] + 1))
            .sum();
    }

    public static void main(String[] args) {
        runTestCase(Arrays.asList(1, 2, 2), 6, "Sample Case 0");
        runTestCase(Arrays.asList(1, 2, 3), 6, "Sample Case 1");
        runTestCase(Arrays.asList(2, 2, 4, 5), 14, "Sample Case 2");
        runTestCase(Arrays.asList(3, 2, 1, 2, 7), 17, "Example Case");

        Integer[] largeData = new Integer[2000];
        Arrays.fill(largeData, 3000);
        int expectedLargeSum = 0;
        for (int i = 0; i < 2000; i++) {
            expectedLargeSum += (3000 + i);
        }
        runTestCase(Arrays.asList(largeData), expectedLargeSum, "Large Data Constraint Test");
    }

    private static void runTestCase(List<Integer> arr, int expected, String testName) {
        int result = getMinimumUniqueSum(arr);
        if (result == expected) {
            System.out.println(testName + ": PASS");
        } else {
            System.out.println(testName + ": FAIL - Expected " + expected + " but got " + result);
        }
    }
}