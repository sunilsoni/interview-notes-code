package com.interview.notes.code.year.y2023.june23.test5;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        String[] arr = {"ab", "ab", "ab", "bc", "bc", "aa", "bd"};
        System.out.println(findKthMostFrequent(arr, 1));  // "ab"
        System.out.println(findKthMostFrequent(arr, 2));  // "bc"
        System.out.println(findKthMostFrequent(arr, 3));  // "aa"
        System.out.println(findKthMostFrequent(arr, 4));  // "bd"
    }

    public static String findKthMostFrequent(String[] arr, int k) {
        Map<String, Integer> map = new HashMap<>();
        for (String str : arr) {
            map.put(str, map.getOrDefault(str, 0) + 1);
        }

        PriorityQueue<String> pq = new PriorityQueue<>(
                (w1, w2) -> map.get(w1).equals(map.get(w2)) ?
                        w2.compareTo(w1) : map.get(w1) - map.get(w2)
        );

        for (String word : map.keySet()) {
            pq.offer(word);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        return pq.peek();
    }
}
