package com.interview.notes.code.year.y2023.Sep23.amazon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SortStringByFrequency {
    public static String frequencySort(String s) {
        // Step 1: Count the frequency of each character
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        // Step 2: Sort the characters based on frequency
        List<Character> characters = new ArrayList<>(frequencyMap.keySet());
        characters.sort((a, b) -> {
            if (frequencyMap.get(b).equals(frequencyMap.get(a))) {
                return Character.compare(a, b); // If frequencies are equal, sort by character
            }
            return frequencyMap.get(b).compareTo(frequencyMap.get(a));
        });

        // Step 3: Construct the output string
        StringBuilder sb = new StringBuilder();
        for (char c : characters) {
            int count = frequencyMap.get(c);
            for (int i = 0; i < count; i++) {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(frequencySort("flee")); // Output: self
    }
}
