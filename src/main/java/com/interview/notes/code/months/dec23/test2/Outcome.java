package com.interview.notes.code.months.dec23.test2;

import java.util.HashMap;
import java.util.PriorityQueue;

public class Outcome {
    public static int solve(int N, int M, String S) {
        HashMap<Character, Integer> charCounts = new HashMap<>();
        for (int i = 0; i < N; i++) {
            char c = S.charAt(i);
            charCounts.put(c, charCounts.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        for (int count : charCounts.values()) {
            maxHeap.add(count);
        }

        while (M > 0 && !maxHeap.isEmpty()) {
            int top = maxHeap.poll();
            if (top > 1) {
                maxHeap.add(top - 1);
            }
            M--;
        }

        int minCost = 0;
        while (!maxHeap.isEmpty()) {
            int count = maxHeap.poll();
            minCost += count * count;
        }

        return minCost;
    }

    // Test the method
    public static void main(String[] args) {
        System.out.println(solve(6, 2, "bacacc")); // Output: 6
        System.out.println(solve(15, 3, "xxxxxxxxxxxxxxx")); // Output: 144
    }
}
