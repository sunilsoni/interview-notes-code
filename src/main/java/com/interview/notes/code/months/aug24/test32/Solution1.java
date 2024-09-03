package com.interview.notes.code.months.aug24.test32;

import java.util.*;

public class Solution1 {

    public static List<List<String>> getSearchResults(List<String> words, List<String> queries) {
        // Create a map for quick anagram lookup
        Map<String, List<String>> anagramMap = new HashMap<>();

        // Preprocess words
        for (String word : words) {
            String sortedWord = sortString(word);
            anagramMap.computeIfAbsent(sortedWord, k -> new ArrayList<>()).add(word);
        }

        // Process queries
        List<List<String>> results = new ArrayList<>();
        for (String query : queries) {
            String sortedQuery = sortString(query);
            List<String> anagrams = anagramMap.getOrDefault(sortedQuery, new ArrayList<>());
            Collections.sort(anagrams);  // Sort alphabetically
            results.add(anagrams);
        }

        return results;
    }

    private static String sortString(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    public static void main(String[] args) {
        // Test cases
        List<String> words1 = Arrays.asList("duel", "speed", "dule", "cars");
        List<String> queries1 = Arrays.asList("spede", "deul");
        System.out.println(getSearchResults(words1, queries1));

        List<String> words2 = Arrays.asList("allot", "cat", "peach", "dusty", "act", "cheap");
        List<String> queries2 = Arrays.asList("tac", "study", "peahc");
        System.out.println(getSearchResults(words2, queries2));

        List<String> words3 = Arrays.asList("emits", "items", "baker", "times", "break");
        List<String> queries3 = Arrays.asList("mites", "brake");
        System.out.println(getSearchResults(words3, queries3));
    }
}
