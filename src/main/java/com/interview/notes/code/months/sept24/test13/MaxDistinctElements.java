package com.interview.notes.code.months.sept24.test13;

import java.util.*;

public class MaxDistinctElements {
    public static int getMaximumDistinctCount(List<Integer> a, List<Integer> b, int k) {
        // Frequency map for elements in 'a'
        Map<Integer, Integer> freqA = new HashMap<>();
        for (int num : a) {
            freqA.put(num, freqA.getOrDefault(num, 0) + 1);
        }

        // Count duplicates in 'a'
        int duplicatesInA = 0;
        for (int count : freqA.values()) {
            if (count > 1) {
                duplicatesInA += count - 1;
            }
        }

        // Unique elements in 'b' not present in 'a'
        Set<Integer> uniqueInB = new HashSet<>(b);
        uniqueInB.removeAll(freqA.keySet());

        // Number of swaps we can perform
        int swaps = Math.min(k, Math.min(duplicatesInA, uniqueInB.size()));

        // Calculate the maximum distinct elements in 'a' after swaps
        int maxDistinctInA = freqA.size() + swaps;

        // The distinct elements cannot exceed the size of 'a'
        maxDistinctInA = Math.min(maxDistinctInA, a.size());

        return maxDistinctInA;
    }

    public static void main(String[] args) {
        // Test Case 1
        List<Integer> a1 = Arrays.asList(2, 3, 3, 2, 2);
        List<Integer> b1 = Arrays.asList(1, 3, 2, 4, 1);
        int k1 = 2;
        System.out.println(getMaximumDistinctCount(a1, b1, k1)); // Expected output: 4

        // Test Case 2
        List<Integer> a2 = Arrays.asList(1, 1, 4, 5, 5);
        List<Integer> b2 = Arrays.asList(4, 4, 3, 1, 5);
        int k2 = 2;
        System.out.println(getMaximumDistinctCount(a2, b2, k2)); // Expected output: 4

        // Test Case 3
        List<Integer> a3 = Arrays.asList(1, 2, 3);
        List<Integer> b3 = Arrays.asList(4, 5, 6);
        int k3 = 5;
        System.out.println(getMaximumDistinctCount(a3, b3, k3)); // Expected output: 3
    }
}
