package com.interview.notes.code.year.y2025.august.oracle.test1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LogAggregator {
    // Map to store word frequencies
    private final Map<String, Integer> wordCounts = new HashMap<>();

    // Map to track first appearance order of each word
    private final Map<String, Integer> firstAppearance = new HashMap<>();

    // Counter to track word appearance order
    private int orderCounter = 0;

    // Main method for testing
    public static void main(String[] args) {
        LogAggregator logger = new LogAggregator();

        // Test Case 1: Basic word counting
        System.out.println("Test Case 1: Basic word counting");
        logger.ingest("hello world hello");
        assert logger.count("hello") == 2 : "Failed: hello count should be 2";
        assert logger.count("world") == 1 : "Failed: world count should be 1";
        System.out.println("PASS");

        // Test Case 2: Case insensitivity
        System.out.println("\nTest Case 2: Case insensitivity");
        logger.ingest("Hello WORLD");
        assert logger.count("hello") == 3 : "Failed: hello count should be 3";
        assert logger.count("world") == 2 : "Failed: world count should be 2";
        System.out.println("PASS");

        // Test Case 3: Special characters
        System.out.println("\nTest Case 3: Special characters");
        logger.ingest("hello-world! #hello@world");
        assert logger.count("hello") == 5 : "Failed: hello count should be 5";
        assert logger.count("world") == 4 : "Failed: world count should be 4";
        System.out.println("PASS");

        // Test Case 4: Top K with ties
        System.out.println("\nTest Case 4: Top K with ties");
        LogAggregator tieLogger = new LogAggregator();
        tieLogger.ingest("a b c a b a");
        List<Pair<String, Integer>> topResults = tieLogger.topK(3);
        assert topResults.get(0).getKey().equals("a") : "Failed: First word should be 'a'";
        assert topResults.get(1).getKey().equals("b") : "Failed: Second word should be 'b'";
        assert topResults.get(2).getKey().equals("c") : "Failed: Third word should be 'c'";
        System.out.println("PASS");

        // Test Case 5: Large input
        System.out.println("\nTest Case 5: Large input");
        LogAggregator largeLogger = new LogAggregator();
        StringBuilder largeInput = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            largeInput.append("test" + (i % 100) + " ");
        }
        long startTime = System.currentTimeMillis();
        largeLogger.ingest(largeInput.toString());
        List<Pair<String, Integer>> largeResults = largeLogger.topK(10);
        long endTime = System.currentTimeMillis();
        System.out.println("Large input processed in " + (endTime - startTime) + "ms");
        System.out.println("PASS");

        System.out.println("\nAll tests passed successfully!");
    }

    /**
     * Ingests a log line, processes it and updates word frequencies
     *
     * @param line Input log line to process
     */
    public void ingest(String line) {
        // Convert line to lowercase and split on non-letter characters
        String[] words = line.toLowerCase().split("[^a-z]+");

        for (String word : words) {
            // Skip empty strings that may result from splitting
            if (word.isEmpty()) continue;

            // Update word count
            wordCounts.merge(word, 1, Integer::sum);

            // Record first appearance if not seen before
            firstAppearance.putIfAbsent(word, orderCounter++);
        }
    }

    /**
     * Returns the frequency of a specific word
     *
     * @param word Word to look up
     * @return Frequency count of the word
     */
    public int count(String word) {
        return wordCounts.getOrDefault(word.toLowerCase(), 0);
    }

    /**
     * Returns top K words by frequency with stable tie-breaking
     *
     * @param k Number of top words to return
     * @return List of word-count pairs sorted by frequency and appearance
     */
    public List<Pair<String, Integer>> topK(int k) {
        // Create list of word-count pairs
        List<Pair<String, Integer>> result = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : wordCounts.entrySet()) {
            result.add(new Pair<>(entry.getKey(), entry.getValue()));
        }

        // Sort by count (descending) and first appearance (ascending)
        result.sort((a, b) -> {
            int countCompare = b.getValue().compareTo(a.getValue());
            if (countCompare != 0) return countCompare;
            return firstAppearance.get(a.getKey()).compareTo(firstAppearance.get(b.getKey()));
        });

        // Return top K results
        return result.subList(0, Math.min(k, result.size()));
    }

    // Simple Pair class for word-count pairs
    static class Pair<K, V> {
        private final K key;
        private final V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "(" + key + ", " + value + ")";
        }
    }
}
