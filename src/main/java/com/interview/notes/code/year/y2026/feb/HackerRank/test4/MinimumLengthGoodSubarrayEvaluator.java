package com.interview.notes.code.year.y2026.feb.HackerRank.test4;

import java.util.Arrays;
import java.util.List;

public class MinimumLengthGoodSubarrayEvaluator {

    public static int findMinimumLengthSubarray(List<Integer> arr, int k) {
        int[] freq = new int[1000001];
        int distinct = 0;
        int minLen = Integer.MAX_VALUE;
        int left = 0;

        for (int right = 0; right < arr.size(); right++) {
            if (freq[arr.get(right)]++ == 0) {
                distinct++;
            }
            while (distinct >= k) {
                minLen = Math.min(minLen, right - left + 1);
                if (--freq[arr.get(left++)] == 0) {
                    distinct--;
                }
            }
        }

        return minLen == Integer.MAX_VALUE ? -1 : minLen;
    }

    public static void main(String[] args) {
        runTest(Arrays.asList(2, 2, 1, 1, 3), 3, 4);
        runTest(Arrays.asList(3, 2, 3, 3, 1, 3), 3, 4);
        runTest(Arrays.asList(1, 2, 2, 1, 2), 4, -1);

        Integer[] largeData = new Integer[100000];
        Arrays.fill(largeData, 1);
        largeData[99998] = 2;
        largeData[99999] = 3;
        runTest(Arrays.asList(largeData), 3, 3);
    }

    private static void runTest(List<Integer> arr, int k, int expected) {
        if (findMinimumLengthSubarray(arr, k) == expected) {
            System.out.println("PASS");
        } else {
            System.out.println("FAIL");
        }
    }
}