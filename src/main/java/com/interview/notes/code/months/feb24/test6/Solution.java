package com.interview.notes.code.months.feb24.test6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    public static List<List<Integer>> textQueries(List<String> sentences, List<String> queries) {
        List<List<Integer>> result = new ArrayList<>();

        // Create a map for each sentence with word occurrence
        List<Map<String, Integer>> sentenceMaps = new ArrayList<>();
        for (String sentence : sentences) {
            Map<String, Integer> wordMap = new HashMap<>();
            for (String word : sentence.split("\\s+")) {
                wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
            }
            sentenceMaps.add(wordMap);
        }

        // Process each query
        for (String query : queries) {
            List<Integer> queryResult = new ArrayList<>();
            String[] queryWords = query.split("\\s+");

            // Check each sentence for the query words
            for (int i = 0; i < sentences.size(); i++) {
                boolean matches = true;
                for (String word : queryWords) {
                    if (!sentenceMaps.get(i).containsKey(word)) {
                        matches = false;
                        break;
                    }
                }
                if (matches) {
                    queryResult.add(i);
                }
            }

            // If no sentence matched, add [-1]
            if (queryResult.isEmpty()) {
                queryResult.add(-1);
            }

            result.add(queryResult);
        }

        return result;
    }

    public static void main(String[] args) {
        // Example usage:
        List<String> sentences = List.of(
                "jim likes mary",
                "kate likes tom",
                "tom does not like jim"
        );
        List<String> queries = List.of(
                "jim tom",
                "likes"
        );

        List<List<Integer>> results = textQueries(sentences, queries);
        for (List<Integer> res : results) {
            System.out.println(res);
        }
    }
}
