package com.interview.notes.code.months.nov24.google.test1;

import java.util.*;

public class QueryEquivalenceChecker {
    private List<Set<String>> synonymGroups;

    public QueryEquivalenceChecker(List<List<String>> synonymPairs) {
        buildSynonymGroups(synonymPairs);
    }

    public static void main(String[] args) {
        // Test cases
        List<List<String>> synonymPairs = Arrays.asList(
                Arrays.asList("rate", "rated"),
                Arrays.asList("best", "top"),
                Arrays.asList("movies", "films"),
                Arrays.asList("rate", "rates")
        );

        QueryEquivalenceChecker checker = new QueryEquivalenceChecker(synonymPairs);

        // Test Case 1
        boolean result1 = checker.areQueriesEquivalent(
                "The best rate movie",
                "The top rate movie"
        );
        System.out.println("Test Case 1: " + (result1 ? "PASS" : "FAIL"));

        // Test Case 2
        boolean result2 = checker.areQueriesEquivalent(
                "st rate moe",
                "The best rate movie"
        );
        System.out.println("Test Case 2: " + (!result2 ? "PASS" : "FAIL"));

        // Test Case 3
        boolean result3 = checker.areQueriesEquivalent(
                "Top movie rated",
                "Top rated movie"
        );
        System.out.println("Test Case 3: " + (!result3 ? "PASS" : "FAIL"));

        // Additional test cases for edge scenarios
        boolean result4 = checker.areQueriesEquivalent(
                "",
                ""
        );
        System.out.println("Edge Case 1 (Empty strings): " + (result4 ? "PASS" : "FAIL"));

        // Large data test case
        StringBuilder longQuery1 = new StringBuilder();
        StringBuilder longQuery2 = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            longQuery1.append("best rate movies ");
            longQuery2.append("top rated films ");
        }

        long startTime = System.currentTimeMillis();
        boolean result5 = checker.areQueriesEquivalent(
                longQuery1.toString().trim(),
                longQuery2.toString().trim()
        );
        long endTime = System.currentTimeMillis();

        System.out.println("Large Data Test: " + (result5 ? "PASS" : "FAIL"));
        System.out.println("Processing time: " + (endTime - startTime) + "ms");
    }

    // Builds groups of synonyms using Union-Find approach
    private void buildSynonymGroups(List<List<String>> synonymPairs) {
        Map<String, Set<String>> wordToGroup = new HashMap<>();
        synonymGroups = new ArrayList<>();

        for (List<String> pair : synonymPairs) {
            String word1 = pair.get(0);
            String word2 = pair.get(1);

            Set<String> group1 = wordToGroup.get(word1);
            Set<String> group2 = wordToGroup.get(word2);

            if (group1 == null && group2 == null) {
                Set<String> newGroup = new HashSet<>();
                newGroup.add(word1);
                newGroup.add(word2);
                synonymGroups.add(newGroup);
                wordToGroup.put(word1, newGroup);
                wordToGroup.put(word2, newGroup);
            } else if (group1 == null) {
                group2.add(word1);
                wordToGroup.put(word1, group2);
            } else if (group2 == null) {
                group1.add(word2);
                wordToGroup.put(word2, group1);
            } else if (group1 != group2) {
                group1.addAll(group2);
                for (String word : group2) {
                    wordToGroup.put(word, group1);
                }
                synonymGroups.remove(group2);
            }
        }
    }

    public boolean areQueriesEquivalent(String query1, String query2) {
        if (query1 == null || query2 == null) {
            return query1 == query2;
        }

        String[] words1 = query1.toLowerCase().split("\\s+");
        String[] words2 = query2.toLowerCase().split("\\s+");

        if (words1.length != words2.length) {
            return false;
        }

        for (int i = 0; i < words1.length; i++) {
            if (words1[i].equals(words2[i])) {
                continue;
            }

            boolean foundSynonym = false;
            for (Set<String> group : synonymGroups) {
                if (group.contains(words1[i]) && group.contains(words2[i])) {
                    foundSynonym = true;
                    break;
                }
            }

            if (!foundSynonym) {
                return false;
            }
        }
        return true;
    }
}