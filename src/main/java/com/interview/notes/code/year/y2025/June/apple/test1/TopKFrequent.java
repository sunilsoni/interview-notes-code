package com.interview.notes.code.year.y2025.June.apple.test1;

import java.util.*;

public class TopKFrequent {
    /**
     * Returns the k most frequent elements in the array.
     */
    public static List<Integer> topKFrequentElements(int[] nums, int k) {
        // 1. Count frequencies
        Map<Integer,Integer> freq = new HashMap<>();
        for (int n : nums) {
            freq.put(n, freq.getOrDefault(n, 0) + 1);
        }

        // 2. Maintain a min-heap of size k, ordered by frequency
        PriorityQueue<Map.Entry<Integer,Integer>> heap =
            new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));

        for (Map.Entry<Integer,Integer> e : freq.entrySet()) {
            heap.offer(e);
            if (heap.size() > k) {
                heap.poll();  // remove the entry with lowest frequency
            }
        }

        // 3. Extract keys from heap into a list (lowest→highest), then reverse
        List<Integer> result = new ArrayList<>();
        while (!heap.isEmpty()) {
            result.add(heap.poll().getKey());
        }
        Collections.reverse(result);
        return result;
    }

    // Simple main to demo
    public static void main(String[] args) {
        int[] a = {1,1,3,4,5,6,6,7,7,7};
        List<Integer> top3 = topKFrequentElements(a, 3);
        System.out.println(top3);  // ➞ [7, 6, 1]
    }
}