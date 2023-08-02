package com.interview.notes.code.july23.test5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagrams2 {

    public static int solution(String[] words) {
        // Create a map to store the anagrams of each word.
        Map<Map<Character, Integer>, List<String>> anagrams = new HashMap<>();

        for (String word : words) {
            Map<Character, Integer> charFrequencyMap = getCharFrequencyMap(word);
            anagrams.computeIfAbsent(charFrequencyMap, k -> new ArrayList<>()).add(word);
        }

        // Count the number of anagram groups.
        int count = 0;
        for (List<String> group : anagrams.values()) {
            if (group.size() > 1) {
                count++;
            }
        }

        return count;
    }

    private static Map<Character, Integer> getCharFrequencyMap(String word) {
        Map<Character, Integer> charFrequencyMap = new HashMap<>();
        for (char c : word.toCharArray()) {
            charFrequencyMap.put(c, charFrequencyMap.getOrDefault(c, 0) + 1);
        }
        return charFrequencyMap;
    }

    public static void main(String[] args) {
        String[] words = new String[]{"tea", "eat", "apple", "ate", "vaja", "cut", "java", "utc"};
        int result = solution(words);
        System.out.println(result);
    }
}
