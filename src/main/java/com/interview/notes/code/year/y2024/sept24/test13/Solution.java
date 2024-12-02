package com.interview.notes.code.year.y2024.sept24.test13;

import java.util.*;

public class Solution {

    public static int getMaximumDistinctCount(List<Integer> a, List<Integer> b, int k) {
        int n = a.size();
        Map<Integer, Integer> freqA = new HashMap<>();
        Set<Integer> uniqueB = new HashSet<>();

        // Count frequencies in A and unique elements in B
        for (int i = 0; i < n; i++) {
            freqA.put(a.get(i), freqA.getOrDefault(a.get(i), 0) + 1);
            uniqueB.add(b.get(i));
        }

        // Count distinct elements in A
        int distinctA = 0;
        List<Integer> duplicatesA = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : freqA.entrySet()) {
            if (entry.getValue() == 1) {
                distinctA++;
            } else {
                duplicatesA.add(entry.getKey());
            }
        }

        // Sort duplicates by frequency (descending)
        duplicatesA.sort((x, y) -> freqA.get(y) - freqA.get(x));

        // Process swaps
        for (int num : uniqueB) {
            if (k == 0) break;
            if (!freqA.containsKey(num)) {
                distinctA++;
                k--;
            }
        }

        // Handle remaining swaps if any
        for (int dup : duplicatesA) {
            if (k == 0) break;
            int freq = freqA.get(dup);
            int swaps = Math.min(freq - 1, k);
            distinctA += swaps;
            k -= swaps;
        }

        return distinctA;
    }

    public static void main(String[] args) {
        // Test case
        List<Integer> a = Arrays.asList(1, 1, 4, 5, 5);
        List<Integer> b = Arrays.asList(4, 4, 3, 1, 5);
        System.out.println(getMaximumDistinctCount(a, b, 2)); // Expected: 4
    }
}