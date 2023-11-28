package com.interview.notes.code.months.nov23.test6;

import java.util.*;

/**
 * EAT ATE TEA CAT RAT
 * Group together the word which are in the same character
 * Grp 1 ( EAT ATE TEA)
 * Grp 2 ( CAT)
 * Grp 3 (RAT )
 */
public class AnagramGrouper {
    public static void main(String[] args) {
        String[] words = {"EAT", "ATE", "TEA", "CAT", "RAT"};
        Map<String, List<String>> groupedAnagrams = groupAnagrams(words);
        printAnagramGroups(groupedAnagrams);
    }

    // Groups words that are anagrams of each other
    private static Map<String, List<String>> groupAnagrams(String[] words) {
        Map<String, List<String>> anagramGroups = new HashMap<>();
        for (String word : words) {
            char[] chars = word.toCharArray();
            Arrays.sort(chars); // Sort the characters of the word
            String sortedWord = new String(chars);

            // Add the original word to the list of its sorted form
            anagramGroups.computeIfAbsent(sortedWord, k -> new ArrayList<>()).add(word);
        }
        return anagramGroups;
    }

    // Prints the groups of anagrams
    private static void printAnagramGroups(Map<String, List<String>> anagramGroups) {

        for (List<String> group : anagramGroups.values()) {
            System.out.println("Group: " + group);
        }
    }
}
