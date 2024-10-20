package com.interview.notes.code.months.oct24.test14;

import java.util.Arrays;
import java.util.TreeSet;

public class HouseSegmentSolution {
    public static int[] solution(int[] queries) {
        TreeSet<Integer> houses = new TreeSet<>();
        int[] result = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            houses.add(queries[i]);
            result[i] = getLongestSegment(houses);
        }

        return result;
    }

    private static int getLongestSegment(TreeSet<Integer> houses) {
        if (houses.isEmpty()) return 0;

        int maxSegment = 1;
        int currentSegment = 1;
        Integer prev = null;

        for (Integer house : houses) {
            if (prev != null) {
                if (house == prev + 1) {
                    currentSegment++;
                } else {
                    maxSegment = Math.max(maxSegment, currentSegment);
                    currentSegment = 1;
                }
            }
            prev = house;
        }

        return Math.max(maxSegment, currentSegment);
    }

    public static void main(String[] args) {
        // Test cases
        runTest(new int[]{2, 1, 3}, new int[]{1, 2, 3});
        runTest(new int[]{1, 3, 0, 4}, new int[]{1, 1, 2, 2});
        runTest(new int[]{5, 3, 1, 7, 9}, new int[]{1, 1, 1, 1, 2});
        runTest(new int[]{10, 8, 6, 4, 2}, new int[]{1, 1, 1, 2, 3});

        // Large input test
        int[] largeInput = new int[100000];
        for (int i = 0; i < 100000; i++) {
            largeInput[i] = i * 2;
        }
        long startTime = System.currentTimeMillis();
        int[] largeResult = solution(largeInput);
        long endTime = System.currentTimeMillis();
        System.out.println("Large input test (100,000 elements) - Execution time: " + (endTime - startTime) + "ms");
        System.out.println("Large input test result (first 5 elements): " + Arrays.toString(Arrays.copyOf(largeResult, 5)));
    }

    private static void runTest(int[] input, int[] expected) {
        System.out.println("Input: " + Arrays.toString(input));
        int[] result = solution(input);
        System.out.println("Output: " + Arrays.toString(result));
        System.out.println("Expected: " + Arrays.toString(expected));
        System.out.println("Test " + (Arrays.equals(result, expected) ? "PASSED" : "FAILED"));
        System.out.println();
    }
}
