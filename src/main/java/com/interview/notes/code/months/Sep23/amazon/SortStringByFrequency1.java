package com.interview.notes.code.months.Sep23.amazon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SortStringByFrequency1 {
    public static String frequencySort(String s) {
        // Step 1: Count the frequency of each character
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        // Step 2: Sort the characters based on frequency
        List<Character> characters = new ArrayList<>(frequencyMap.keySet());
        characters.sort((a, b) -> frequencyMap.get(b).compareTo(frequencyMap.get(a)));

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
