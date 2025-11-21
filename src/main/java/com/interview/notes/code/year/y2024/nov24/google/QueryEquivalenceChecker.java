package com.interview.notes.code.year.y2024.nov24.google;

import java.util.*;

public class QueryEquivalenceChecker {

    // Map to store synonym relationships
    private static final Map<String, Set<String>> synonymMap = new HashMap<>();

    public static void main(String[] args) {
        // Test cases
        List<List<String>> synonymPairs = Arrays.asList(
                Arrays.asList("rate", "rated"),
                Arrays.asList("best", "top"),
                Arrays.asList("movies", "films"),
                Arrays.asList("rate", "rates")
        );

        List<List<String>> queries = Arrays.asList(
                Arrays.asList("The best rate movie", "The top rated film"),
                Arrays.asList("The best rate movie", "st rate moe"),
                Arrays.asList("Top movie rated", "Top rated movie")
        );

        // Build synonym map
        buildSynonymMap(synonymPairs);

        // Check equivalence for each query pair
        List<Boolean> results = new ArrayList<>();
        for (List<String> queryPair : queries) {
            results.add(areQueriesEquivalent(queryPair.get(0), queryPair.get(1)));
        }

        // Print results
        System.out.println("Test Results: " + results);

        // Verify against expected output
        List<Boolean> expectedOutput = Arrays.asList(true, false, false);
        System.out.println("All tests passed: " + results.equals(expectedOutput));

        // Additional test for large input
        testLargeInput();
    }

    private static void buildSynonymMap(List<List<String>> synonymPairs) {
        for (List<String> pair : synonymPairs) {
            String word1 = pair.get(0).toLowerCase();
            String word2 = pair.get(1).toLowerCase();

            synonymMap.computeIfAbsent(word1, k -> new HashSet<>()).add(word2);
            synonymMap.computeIfAbsent(word2, k -> new HashSet<>()).add(word1);
        }
    }

    private static boolean areQueriesEquivalent(String query1, String query2) {
        String[] words1 = query1.toLowerCase().split("\\s+");
        String[] words2 = query2.toLowerCase().split("\\s+");

        if (words1.length != words2.length) {
            return false;
        }

        for (int i = 0; i < words1.length; i++) {
            if (!areSynonyms(words1[i], words2[i])) {
                return false;
            }
        }

        return true;
    }

    private static boolean areSynonyms(String word1, String word2) {
        if (word1.equals(word2)) {
            return true;
        }

        Set<String> synonyms = synonymMap.get(word1);
        return synonyms != null && synonyms.contains(word2);
    }

    private static void testLargeInput() {
        // Generate a large number of synonym pairs
        List<List<String>> largeSynonymPairs = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            largeSynonymPairs.add(Arrays.asList("word" + i, "synonym" + i));
        }

        // Build synonym map with large input
        buildSynonymMap(largeSynonymPairs);

        // Test with large queries
        String largeQuery1 = String.join(" ", Collections.nCopies(1000, "word0"));
        String largeQuery2 = String.join(" ", Collections.nCopies(1000, "synonym0"));

        long startTime = System.currentTimeMillis();
        boolean result = areQueriesEquivalent(largeQuery1, largeQuery2);
        long endTime = System.currentTimeMillis();

        System.out.println("Large input test result: " + result);
        System.out.println("Large input test execution time: " + (endTime - startTime) + "ms");
    }
}
