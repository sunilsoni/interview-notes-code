package com.interview.notes.code.year.y2024.sept24.test11;

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
        int distinctA = freqA.size();

        // Count elements in A with frequency > 1
        PriorityQueue<Integer> duplicatesA = new PriorityQueue<>((x, y) -> freqA.get(y) - freqA.get(x));
        for (Map.Entry<Integer, Integer> entry : freqA.entrySet()) {
            if (entry.getValue() > 1) {
                duplicatesA.offer(entry.getKey());
            }
        }

        // Process swaps
        for (int num : uniqueB) {
            if (k == 0) break;
            if (!freqA.containsKey(num)) {
                if (!duplicatesA.isEmpty()) {
                    int dup = duplicatesA.poll();
                    freqA.put(dup, freqA.get(dup) - 1);
                    if (freqA.get(dup) == 1) {
                        distinctA++;
                    } else {
                        duplicatesA.offer(dup);
                    }
                }
                distinctA++;
                k--;
            }
        }

        // Handle remaining swaps if any
        while (k > 0 && !duplicatesA.isEmpty()) {
            int dup = duplicatesA.poll();
            int freq = freqA.get(dup);
            int swaps = Math.min(freq - 1, k);
            distinctA += swaps;
            k -= swaps;
        }

        return distinctA;
    }

    public static void main(String[] args) {
        // Test cases
        List<Integer> a1 = Arrays.asList(2, 3, 3, 2, 2);
        List<Integer> b1 = Arrays.asList(1, 3, 2, 4, 1);
        System.out.println(getMaximumDistinctCount(a1, b1, 2)); // Expected: 4

        List<Integer> a2 = Arrays.asList(1, 1, 4, 5, 5);
        List<Integer> b2 = Arrays.asList(4, 4, 3, 1, 5);
        System.out.println(getMaximumDistinctCount(a2, b2, 2)); // Expected: 4

        List<Integer> a3 = Arrays.asList(1, 2, 3);
        List<Integer> b3 = Arrays.asList(4, 5, 6);
        System.out.println(getMaximumDistinctCount(a3, b3, 5)); // Expected: 3
    }
}