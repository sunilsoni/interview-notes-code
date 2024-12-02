package com.interview.notes.code.year.y2024.nov24.google.test1;

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
public class NonTransitiveQueryChecker {
    // Store direct synonym pairs only
    private Set<Pair> directSynonyms;

    public NonTransitiveQueryChecker(List<List<String>> synonymPairs) {
        directSynonyms = new HashSet<>();
        for (List<String> pair : synonymPairs) {
            directSynonyms.add(new Pair(pair.get(0).toLowerCase(),
                    pair.get(1).toLowerCase()));
        }
    }

    public static void main(String[] args) {
        // Test cases demonstrating non-transitivity
        List<List<String>> synonymPairs = Arrays.asList(
                Arrays.asList("rate", "rated"),    // A = B
                Arrays.asList("rated", "rates"),   // B = C
                Arrays.asList("good", "best"),     // X = Y
                Arrays.asList("best", "top")       // Y = Z
        );

        NonTransitiveQueryChecker checker = new NonTransitiveQueryChecker(synonymPairs);

        // Test Case 1: Direct synonyms
        testCase(checker, "rate rated", "rated rate",
                "Direct synonyms should be equivalent", true);

        // Test Case 2: Non-transitive relationship
        testCase(checker, "rate rates", "rates rate",
                "Non-direct synonyms should NOT be equivalent", false);

        // Test Case 3: Multiple word comparison
        testCase(checker, "good movies", "best movies",
                "Direct synonyms with multiple words should be equivalent", true);

        // Test Case 4: Transitive relationship (should fail)
        testCase(checker, "good top", "top good",
                "Transitive synonyms should NOT be equivalent", false);

        // Test Case 5: Exact match
        testCase(checker, "the movie", "the movie",
                "Exact matches should be equivalent", true);

        // Test Case 6: Case sensitivity
        testCase(checker, "RATE movie", "rated MOVIE",
                "Case insensitive direct synonyms should be equivalent", true);

        // Large data test
        testLargeData(checker);
    }

    private static void testCase(NonTransitiveQueryChecker checker,
                                 String query1, String query2,
                                 String description, boolean expectedResult) {
        boolean result = checker.areQueriesEquivalent(query1, query2);
        System.out.println("\nTest Case: " + description);
        System.out.println("Query 1: \"" + query1 + "\"");
        System.out.println("Query 2: \"" + query2 + "\"");
        System.out.println("Expected: " + expectedResult);
        System.out.println("Result: " + result);
        System.out.println("Status: " + (result == expectedResult ? "PASS" : "FAIL"));
    }

    private static void testLargeData(NonTransitiveQueryChecker checker) {
        // Create large queries
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

        System.out.println("\nLarge Data Test:");
        System.out.println("Processing time: " + (endTime - startTime) + "ms");
        System.out.println("Result: " + result);
    }

    private boolean areDirectSynonyms(String word1, String word2) {
        return directSynonyms.contains(new Pair(word1, word2));
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
            if (!words1[i].equals(words2[i]) &&
                    !areDirectSynonyms(words1[i], words2[i])) {
                return false;
            }
        }
        return true;
    }

    private static class Pair {
        String word1, word2;

        Pair(String word1, String word2) {
            // Store in lexicographical order for consistent comparison
            if (word1.compareTo(word2) <= 0) {
                this.word1 = word1;
                this.word2 = word2;
            } else {
                this.word1 = word2;
                this.word2 = word1;
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Pair)) return false;
            Pair pair = (Pair) o;
            return word1.equals(pair.word1) && word2.equals(pair.word2);
        }

        @Override
        public int hashCode() {
            return Objects.hash(word1, word2);
        }
    }
}