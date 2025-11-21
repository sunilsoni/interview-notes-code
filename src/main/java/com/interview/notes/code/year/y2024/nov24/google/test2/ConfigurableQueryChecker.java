package com.interview.notes.code.year.y2024.nov24.google.test2;

import java.util.*;

/*
Problem Description
In search,
knowing if two queries have
same meaning is a really useful signal as one can expect the same results/answers for equivalent queries.
Given a list of synonym pairs, write a function to determine if two queries are equivalent. Two queries are equivalent if you can match them (exact match or synonyms), word by word in order.
Example:
Synonyms:
I (rate,
rated), (best, top), (movies, films), (rate, rates)]
Queries:
"The best rate movie" "st rate moe 31 ).
("The best rate movie".
("Top movie rated",
"Top rated movie")]
Expected Output: [True, False, False]
 */
public class ConfigurableQueryChecker {
    private final boolean allowTransitive;
    private final Map<String, Set<String>> wordToSynonyms;
    private final Set<SynonymPair> directSynonyms;

    public ConfigurableQueryChecker(List<List<String>> synonymPairs,
                                    List<Boolean> transitiveFlags) {
        this.directSynonyms = new HashSet<>();
        this.wordToSynonyms = new HashMap<>();
        this.allowTransitive = true; // Global flag for transitive relationships

        // Process each synonym pair with its transitive flag
        for (int i = 0; i < synonymPairs.size(); i++) {
            String word1 = synonymPairs.get(i).get(0).toLowerCase();
            String word2 = synonymPairs.get(i).get(1).toLowerCase();
            boolean isTransitive = transitiveFlags.get(i);

            addSynonymPair(word1, word2, isTransitive);
        }

        // Build transitive relationships if allowed
        if (allowTransitive) {
            buildTransitiveRelations();
        }
    }

    public static void main(String[] args) {
        // Test setup
        List<List<String>> synonymPairs = Arrays.asList(
                Arrays.asList("rate", "rated"),
                Arrays.asList("rated", "rates"),
                Arrays.asList("good", "best"),
                Arrays.asList("best", "top")
        );

        // Specify which relationships are transitive
        List<Boolean> transitiveFlags = Arrays.asList(true, true, false, false);

        ConfigurableQueryChecker checker = new ConfigurableQueryChecker(
                synonymPairs, transitiveFlags);

        // Test cases
        testCase(checker, "rate rates", "rates rate",
                "Transitive relationship test", true);

        testCase(checker, "good top", "top good",
                "Non-transitive relationship test", false);

        testCase(checker, "rate movie", "rated movie",
                "Direct synonym test", true);

        // Additional test cases
        runAdvancedTests(checker);
    }

    private static void testCase(ConfigurableQueryChecker checker,
                                 String query1, String query2,
                                 String description, boolean expected) {
        boolean result = checker.areQueriesEquivalent(query1, query2);
        System.out.println("\nTest: " + description);
        System.out.println("Query 1: \"" + query1 + "\"");
        System.out.println("Query 2: \"" + query2 + "\"");
        System.out.println("Expected: " + expected);
        System.out.println("Result: " + result);
        System.out.println("Status: " + (result == expected ? "PASS" : "FAIL"));
    }

    private static void runAdvancedTests(ConfigurableQueryChecker checker) {
        System.out.println("\n=== Advanced Tests ===");

        // Test transitive chains
        testCase(checker, "rate films", "rates movies",
                "Complex transitive chain", true);

        // Test mixed transitive and non-transitive
        testCase(checker, "good movies rate", "best movies rated",
                "Mixed transitive and non-transitive", false);

        // Test case sensitivity
        testCase(checker, "RATE MOVIE", "rated MOVIE",
                "Case sensitivity test", true);

        // Performance test with large input
        runPerformanceTest(checker);
    }

    private static void runPerformanceTest(ConfigurableQueryChecker checker) {
        StringBuilder query1 = new StringBuilder();
        StringBuilder query2 = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            query1.append("rate ");
            query2.append("rated ");
        }

        long startTime = System.currentTimeMillis();
        boolean result = checker.areQueriesEquivalent(
                query1.toString().trim(),
                query2.toString().trim()
        );
        long endTime = System.currentTimeMillis();

        System.out.println("\nPerformance Test:");
        System.out.println("Processing time: " + (endTime - startTime) + "ms");
        System.out.println("Result: " + result);
    }

    private void addSynonymPair(String word1, String word2, boolean isTransitive) {
        directSynonyms.add(new SynonymPair(word1, word2, isTransitive));

        // Add to word-to-synonyms map
        wordToSynonyms.computeIfAbsent(word1, k -> new HashSet<>()).add(word2);
        wordToSynonyms.computeIfAbsent(word2, k -> new HashSet<>()).add(word1);
    }

    private void buildTransitiveRelations() {
        boolean changed;
        do {
            changed = false;
            Set<SynonymPair> newPairs = new HashSet<>();

            for (SynonymPair pair1 : directSynonyms) {
                if (!pair1.isTransitive) continue;

                for (SynonymPair pair2 : directSynonyms) {
                    if (!pair2.isTransitive) continue;

                    String newWord1 = null, newWord2 = null;

                    // Check for transitive relationships
                    if (pair1.word2.equals(pair2.word1)) {
                        newWord1 = pair1.word1;
                        newWord2 = pair2.word2;
                    } else if (pair1.word2.equals(pair2.word2)) {
                        newWord1 = pair1.word1;
                        newWord2 = pair2.word1;
                    }

                    if (newWord1 != null && !areSynonyms(newWord1, newWord2)) {
                        newPairs.add(new SynonymPair(newWord1, newWord2, true));
                        changed = true;
                    }
                }
            }

            // Add new transitive pairs
            for (SynonymPair pair : newPairs) {
                addSynonymPair(pair.word1, pair.word2, true);
            }
        } while (changed);
    }

    public boolean areSynonyms(String word1, String word2) {
        word1 = word1.toLowerCase();
        word2 = word2.toLowerCase();

        if (word1.equals(word2)) return true;

        Set<String> synonyms = wordToSynonyms.get(word1);
        return synonyms != null && synonyms.contains(word2);
    }

    public boolean areQueriesEquivalent(String query1, String query2) {
        if (query1 == null || query2 == null) {
            return query1 == query2;
        }

        String[] words1 = query1.toLowerCase().trim().split("\\s+");
        String[] words2 = query2.toLowerCase().trim().split("\\s+");

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

    static class SynonymPair {
        String word1, word2;
        boolean isTransitive;

        SynonymPair(String word1, String word2, boolean isTransitive) {
            // Store in lexicographical order
            if (word1.compareTo(word2) <= 0) {
                this.word1 = word1;
                this.word2 = word2;
            } else {
                this.word1 = word2;
                this.word2 = word1;
            }
            this.isTransitive = isTransitive;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof SynonymPair pair)) return false;
            return word1.equals(pair.word1) && word2.equals(pair.word2);
        }

        @Override
        public int hashCode() {
            return Objects.hash(word1, word2);
        }
    }
}