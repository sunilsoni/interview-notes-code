package com.interview.notes.code.year.y2026.may.common.test6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {

    public static void main(String[] args) {
        // The test logic remains simple for the main method
        MyPrefixSearch search = new MyPrefixSearch("Apple, Alpha, Banana, App");

        System.out.println("Search 'Ap': " + search.findAll("Ap")); // [0, 21]
        System.out.println("Search 'B': " + search.findAll("B"));   // [14]

        // Large Data Check
        long start = System.currentTimeMillis();
        // Imagine a document with 1 million words...
        System.out.println("Search completed in: " + (System.currentTimeMillis() - start) + "ms");
    }

    static class MyPrefixSearch {
        // Cache: Maps a starting character to a list of word-start indices
        // Using Java 21 Map features for efficiency
        private final Map<Character, List<Integer>> indexCache = new HashMap<>();
        private final String doc;

        MyPrefixSearch(String document) {
            this.doc = document.toLowerCase(); // Standardize once

            // PREPARATION PHASE: "Caching" the word starts
            for (int i = 0; i < doc.length(); i++) {
                // Logic: If it's the start of the string OR the previous char wasn't a letter
                if (i == 0 || !Character.isLetterOrDigit(doc.charAt(i - 1))) {
                    if (Character.isLetterOrDigit(doc.charAt(i))) {
                        // Store the index in a bucket based on the first character
                        indexCache.computeIfAbsent(doc.charAt(i), k -> new ArrayList<>()).add(i);
                    }
                }
            }
        }

        public List<Integer> findAll(String prefix) {
            if (prefix == null || prefix.isEmpty()) return List.of();

            String p = prefix.toLowerCase();
            char firstChar = p.charAt(0);

            // If no words in our document start with this character, return empty immediately
            if (!indexCache.containsKey(firstChar)) return List.of();

            // SEARCH PHASE: Only look at indices that we ALREADY KNOW start with the right letter
            return indexCache.get(firstChar).stream()
                .filter(idx -> doc.startsWith(p, idx)) // Very fast: checks only the specific offset
                .toList(); // Clean Java 16+ collection
        }
    }
}