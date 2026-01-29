package com.interview.notes.code.year.y2026.jan.microsoft.test5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DistinctSubarrayOptimizer {

    public static int findMinimumLengthSubarray(List<Integer> arr, int k) {
        if (arr == null || arr.isEmpty() || k < 1) return -1;

        var freqMap = new HashMap<Integer, Integer>();
        int minLength = Integer.MAX_VALUE;
        int left = 0;

        for (int right = 0; right < arr.size(); right++) {
            freqMap.merge(arr.get(right), 1, Integer::sum);

            while (freqMap.size() >= k) {
                minLength = Math.min(minLength, right - left + 1);
                freqMap.compute(arr.get(left), (key, val) -> val == 1 ? null : val - 1);
                left++;
            }
        }

        return minLength == Integer.MAX_VALUE ? -1 : minLength;
    }

    public static void main(String[] args) {
        runTest("Sample Case 0", Arrays.asList(2, 2, 1, 1, 3), 3, 4);
        runTest("Sample Case 1", Arrays.asList(1, 2, 2, 1, 2), 4, -1);
        runTest("Edge Case: k=1", Arrays.asList(1, 2, 3), 1, 1);
        runTest("Edge Case: k > n", Arrays.asList(1, 2), 5, -1);
        runTest("Exact Match", Arrays.asList(1, 2, 3), 3, 3);

        // Large Data Test
        int size = 100_000;
        var largeList = new ArrayList<Integer>(size);
        for (int i = 0; i < size; i++) largeList.add(i % 100);
        // 100 distinct numbers repeating. k=50 should be found quickly (approx 50 length).
        runTest("Large Data Input (100k elements)", largeList, 50, 50);
    }

    private static void runTest(String testName, List<Integer> input, int k, int expected) {
        long startTime = System.nanoTime();
        int result = findMinimumLengthSubarray(input, k);
        long duration = (System.nanoTime() - startTime) / 1_000_000;

        String status = (result == expected) ? "PASS" : "FAIL (Expected " + expected + ", Got " + result + ")";
        System.out.printf("%-35s | Result: %-5d | Time: %3d ms | Status: %s%n", testName, result, duration, status);
    }
}