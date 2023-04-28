package com.interview.notes.code.tricky;

import java.util.*;

public class LetterCandles5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        String s = sc.next();

        // Count the occurrences of each letter
        Map<Character, Integer> counts = new HashMap<>();
        for (char c : s.toCharArray()) {
            counts.put(c, counts.getOrDefault(c, 0) + 1);
        }

        // Calculate the initial cost of the box
        int cost = 0;
        for (int count : counts.values()) {
            cost += count * count;
        }

        // Use a priority queue to keep track of the current cost and the cost after removing one candle
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(cost);
        for (int count : counts.values()) {
            pq.offer(cost - count * count + (count - 1) * (count - 1));
        }

        // Remove candles until we have removed the maximum number allowed or the priority queue is empty
        while (m > 0 && !pq.isEmpty()) {
            cost = pq.poll();
            m--;
            if (m > 0) {
                pq.offer(cost - 1);
            }
        }

        System.out.println(cost);
    }
}
