package com.interview.notes.code.year.y2024.sept24.test13;

import java.util.*;

public class MaximumDistinctCount {

    public static int getMaximumDistinctCount(List<Integer> a, List<Integer> b, int k) {
        // Frequency map for array a
        Map<Integer, Integer> freqA = new HashMap<>();
        for (int num : a) {
            freqA.put(num, freqA.getOrDefault(num, 0) + 1);
        }

        // Frequency map for array b
        Map<Integer, Integer> freqB = new HashMap<>();
        for (int num : b) {
            freqB.put(num, freqB.getOrDefault(num, 0) + 1);
        }

        // Initial distinct count in a
        int initialDistinctCount = freqA.size();

        // Identify potential beneficial swaps
        List<Integer> candidates = new ArrayList<>();
        for (int num : b) {
            if (!freqA.containsKey(num)) {
                candidates.add(num);
            }
        }

        // Sort candidates by frequency in b (descending)
        candidates.sort((x, y) -> freqB.get(y) - freqB.get(x));

        // Perform swaps
        int swaps = 0;
        for (int num : candidates) {
            if (swaps < k) {
                initialDistinctCount++;
                swaps++;
            } else {
                break;
            }
        }

        return initialDistinctCount;
    }

    public static void main(String[] args) {
        List<Integer> a = Arrays.asList(1, 1, 4, 5, 5);
        List<Integer> b = Arrays.asList(4, 4, 3, 1, 5);
        int k = 2;

        System.out.println(getMaximumDistinctCount(a, b, k)); // Output: 4
    }
}
