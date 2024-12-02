package com.interview.notes.code.year.y2023.Sep23.amazon;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class SortStringByFrequency2 {

    public static String sortStringByFrequency(String s) {
        // Create a map to store the frequency of each character.
        Map<Character, Integer> charFreq = new HashMap<>();
        for (char c : s.toCharArray()) {
            charFreq.put(c, charFreq.getOrDefault(c, 0) + 1);
        }

        // Create a priority queue to store the characters in decreasing order of frequency.
        PriorityQueue<Character> pq = new PriorityQueue<>((c1, c2) -> charFreq.get(c2) - charFreq.get(c1));
        pq.addAll(charFreq.keySet());

        // Build the sorted string.
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            char c = pq.poll();
            for (int i = 0; i < charFreq.get(c); i++) {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "flee";
        String sortedString = sortStringByFrequency(s);
        System.out.println(sortedString);
    }
}
