package com.interview.notes.code.months.march24.test3;

import java.util.*;

public class AnagramFinder {

    public static void main(String[] args) {
        String[] words = {"listen", "silent", "hello", "world", "eat", "tea", "tan", "bat"};
        printAnagramGroups(words);
    }

    public static void printAnagramGroups(String[] words) {
        Map<String, List<String>> anagramGroups = new HashMap<>();

        for (String word : words) {
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String sortedWord = new String(chars);

            List<String> anagrams = anagramGroups.getOrDefault(sortedWord, new ArrayList<>());
            anagrams.add(word);
            anagramGroups.put(sortedWord, anagrams);
        }

        for (List<String> group : anagramGroups.values()) {
            if (group.size() > 1) {
                System.out.println("Anagram Group: " + group);
            }
        }
    }
}
