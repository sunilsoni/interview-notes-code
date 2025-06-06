package com.interview.notes.code.year.y2025.may.common.test13;

import java.util.*;
import java.util.stream.Collectors;

public class FrequentWordsFinder {

    public static List<String> findTopKFrequentWords(String[] words, int k) {
        // Edge case: if input array is empty or k is 0
        if (words == null || words.length == 0 || k <= 0) {
            return new ArrayList<>();
        }

        // Step 1: Count word frequencies using HashMap
        Map<String, Integer> wordFreq = new HashMap<>();
        Arrays.stream(words)
                .forEach(word -> wordFreq.merge(word, 1, Integer::sum));

        // Step 2: Sort words based on frequency and lexicographical order
        return wordFreq.entrySet()
                .stream()
                .sorted((e1, e2) -> {
                    // If frequencies are different, sort by frequency in descending order
                    int freqCompare = e2.getValue().compareTo(e1.getValue());
                    // If frequencies are same, sort alphabetically
                    return freqCompare != 0 ? freqCompare : e1.getKey().compareTo(e2.getKey());
                })
                .limit(k) // Take only k elements
                .map(Map.Entry::getKey) // Extract just the words
                .collect(Collectors.toList());
    }

    // Main method for testing
    public static void main(String[] args) {
        // Test Case 1: Normal case
        String[] test1 = {"java", "kafka", "spring", "java", "aws", "spring", "kafka", "java"};
        testCase(test1, 2, Arrays.asList("java", "kafka"), "Test 1: Normal case");

        // Test Case 2: Empty array
        String[] test2 = {};
        testCase(test2, 2, Arrays.asList(), "Test 2: Empty array");

        // Test Case 3: Single element
        String[] test3 = {"java"};
        testCase(test3, 1, Arrays.asList("java"), "Test 3: Single element");

        // Test Case 4: Large input (simulated)
        String[] test4 = generateLargeInput(10000);
        testCase(test4, 3, null, "Test 4: Large input");

        // Test Case 5: Same frequency words
        String[] test5 = {"a", "b", "a", "b"};
        testCase(test5, 2, Arrays.asList("a", "b"), "Test 5: Same frequency");
    }

    // Helper method to test cases
    private static void testCase(String[] input, int k, List<String> expected, String testName) {
        List<String> result = findTopKFrequentWords(input, k);
        if (expected == null) { // For large input test, just verify no exceptions
            System.out.println(testName + ": PASS (No exceptions)");
            return;
        }
        boolean pass = result.equals(expected);
        System.out.println(testName + ": " + (pass ? "PASS" : "FAIL"));
        if (!pass) {
            System.out.println("Expected: " + expected);
            System.out.println("Got: " + result);
        }
    }

    // Helper method to generate large input
    private static String[] generateLargeInput(int size) {
        String[] words = new String[size];
        Random rand = new Random();
        String[] sampleWords = {"java", "python", "scala", "kotlin", "go"};
        for (int i = 0; i < size; i++) {
            words[i] = sampleWords[rand.nextInt(sampleWords.length)];
        }
        return words;
    }
}
