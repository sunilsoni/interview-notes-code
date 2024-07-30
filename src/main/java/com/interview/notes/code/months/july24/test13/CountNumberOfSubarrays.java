package com.interview.notes.code.months.july24.test13;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class CountNumberOfSubarrays {
    public static long countNumberOfSubarrays(List<Integer> arr, int k) {
        Map<Long, Long> sumFrequency = new HashMap<>();
        long count = 0;
        long cumulativeSum = 0;

        sumFrequency.put(0L, 1L);

        for (int num : arr) {
            cumulativeSum += num;

            if (sumFrequency.containsKey(cumulativeSum - k)) {
                count += sumFrequency.get(cumulativeSum - k);
            }

            sumFrequency.put(cumulativeSum, sumFrequency.getOrDefault(cumulativeSum, 0L) + 1);
        }

        return count;
    }

    public static void main(String[] args) {
        // Example 1
        List<Integer> arr1 = Arrays.asList(1, 2, 3, 0);
        int k1 = 3;
        long result1 = countNumberOfSubarrays(arr1, k1);
        System.out.println("Example 1:");
        System.out.println("Input: arr = " + arr1 + ", k = " + k1);
        System.out.println("Output: " + result1);
        System.out.println();

        // Example 2
        List<Integer> arr2 = Arrays.asList(1, 1, 1);
        int k2 = 2;
        long result2 = countNumberOfSubarrays(arr2, k2);
        System.out.println("Example 2:");
        System.out.println("Input: arr = " + arr2 + ", k = " + k2);
        System.out.println("Output: " + result2);
    }
}
