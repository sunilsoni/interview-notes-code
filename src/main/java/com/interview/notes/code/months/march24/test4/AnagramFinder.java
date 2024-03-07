package com.interview.notes.code.months.march24.test4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnagramFinder {

    public static void main(String[] args) {
        String[] words = {"listen", "silent", "hello", "world", "eat", "tea", "tan", "bat"};
        printAnagramGroups(words);
    }

    public static void printAnagramGroups(String[] words) {
        Map<String, List<String>> anagramGroups = new HashMap<>();

        for (String word : words) {
            String charCount = getCharacterCount(word);
            List<String> anagrams = anagramGroups.getOrDefault(charCount, new ArrayList<>());
            anagrams.add(word);
            anagramGroups.put(charCount, anagrams);
        }

        for (List<String> group : anagramGroups.values()) {
            if (group.size() > 1) {
                System.out.println("Anagram Group: " + group);
            }
        }
    }

    private static String getCharacterCount(String word) {
        int[] count = new int[26]; // Assuming only lowercase English alphabets
        for (char c : word.toCharArray()) {
            count[c - 'a']++;
        }
        StringBuilder builder = new StringBuilder();
        for (int num : count) {
            builder.append(num).append(",");
        }
        return builder.toString();
    }
}
