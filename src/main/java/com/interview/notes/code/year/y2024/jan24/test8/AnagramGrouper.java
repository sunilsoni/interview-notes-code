package com.interview.notes.code.year.y2024.jan24.test8;

import java.util.*;

public class AnagramGrouper {

    // Method to group words by their anagram equivalence
    public static List<List<String>> groupAnagrams(String[] words) {
        if (words == null || words.length == 0) return Collections.emptyList();

        Map<String, List<String>> map = new HashMap<>();
        for (String word : words) {
            char[] characters = word.toCharArray();
            Arrays.sort(characters); // Sort characters of the word
            String sorted = new String(characters);
            map.computeIfAbsent(sorted, k -> new ArrayList<>()).add(word);
        }

        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        String[] words = {"EAT", "ATE", "TEA", "CAT", "RAT"};
        List<List<String>> groupedAnagrams = groupAnagrams(words);

        // Print grouped anagrams
        for (List<String> group : groupedAnagrams) {
            System.out.println(group);
        }
    }
}
