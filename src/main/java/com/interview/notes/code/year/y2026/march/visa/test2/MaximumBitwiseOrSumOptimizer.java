package com.interview.notes.code.year.y2026.march.visa.test2;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class MaximumBitwiseOrSumOptimizer {

    public static long getMaxOrSum(List<Integer> arr, int k) {
        int n = arr.size();
        long[] prefix = new long[n + 1];
        long[] suffix = new long[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] | arr.get(i);
        }
        
        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = suffix[i + 1] | arr.get(i);
        }

        long multiplier = 1L << k;
        
        return IntStream.range(0, n)
                .mapToLong(i -> prefix[i] | (arr.get(i) * multiplier) | suffix[i + 1])
                .max()
                .orElse(0L);
    }

    public static void main(String[] args) {
        record TestCase(List<Integer> arr, int k, long expected) {}
        
        var testCases = List.of(
            new TestCase(List.of(10, 1), 1, 21),
            new TestCase(List.of(5, 8), 3, 69),
            new TestCase(List.of(12, 9), 1, 30),
            new TestCase(Collections.nCopies(100000, 1), 11, 2049)
        );

        for (var tc : testCases) {
            long actual = getMaxOrSum(tc.arr, tc.k);
            String status = (actual == tc.expected) ? "PASS" : "FAIL";
            System.out.printf("Test: arr size=%d, k=%d | Expected=%d, Actual=%d -> %s%n", 
                    tc.arr.size(), tc.k, tc.expected, actual, status);
        }
    }
}