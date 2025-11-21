package com.interview.notes.code.year.y2024.dec24.oracle.test1;

import java.util.*;

public class SentenceCounter {
    public static List<Long> countSentences(List<String> wordSet, List<String> sentences) {
        // Create anagram mapping
        Map<String, Set<String>> anagramMap = buildAnagramMap(wordSet);

        List<Long> results = new ArrayList<>();
        for (String sentence : sentences) {
            results.add(calculatePossibleSentences(sentence, anagramMap));
        }
        return results;
    }

    private static Map<String, Set<String>> buildAnagramMap(List<String> wordSet) {
        Map<String, Set<String>> anagramMap = new HashMap<>();

        for (String word : wordSet) {
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String sortedWord = new String(chars);

            anagramMap.computeIfAbsent(sortedWord, k -> new HashSet<>()).add(word);
        }
        return anagramMap;
    }

    private static long calculatePossibleSentences(String sentence, Map<String, Set<String>> anagramMap) {
        String[] words = sentence.split(" ");
        long combinations = 1;

        for (String word : words) {
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String sortedWord = new String(chars);

            Set<String> anagrams = anagramMap.get(sortedWord);
            if (anagrams != null) {
                combinations *= anagrams.size();
            }
        }
        return combinations;
    }

    public static void main(String[] args) {
        // Test cases
        runTestCases();
    }

    private static void runTestCases() {
        // Test Case 1: Sample input
        List<String> wordSet1 = Arrays.asList("the", "bats", "tabs", "in", "cat", "act");
        List<String> sentences1 = Arrays.asList("cat the bats", "in the act", "act tabs in");
        testAndPrint("Test Case 1", wordSet1, sentences1, Arrays.asList(4L, 2L, 4L));

        // Test Case 2: Edge case with single character words
        List<String> wordSet2 = Arrays.asList("a", "b", "ab", "ba");
        List<String> sentences2 = List.of("a b ab");
        testAndPrint("Test Case 2", wordSet2, sentences2, List.of(2L));

        // Test Case 3: Large input simulation
        List<String> wordSet3 = generateLargeWordSet();
        List<String> sentences3 = List.of("stop post tops");
        testAndPrint("Test Case 3 (Large Input)", wordSet3, sentences3, List.of(6L));
    }

    private static List<String> generateLargeWordSet() {
        List<String> largeSet = new ArrayList<>();
        largeSet.addAll(Arrays.asList("stop", "post", "tops", "spot", "pots", "opts"));
        // Add more words as needed for testing
        return largeSet;
    }

    private static void testAndPrint(String testName, List<String> wordSet,
                                     List<String> sentences, List<Long> expected) {
        List<Long> result = countSentences(wordSet, sentences);
        boolean passed = result.equals(expected);

        System.out.println(testName);
        System.out.println("Input WordSet: " + wordSet);
        System.out.println("Input Sentences: " + sentences);
        System.out.println("Expected: " + expected);
        System.out.println("Actual: " + result);
        System.out.println("Status: " + (passed ? "PASS" : "FAIL"));
        System.out.println("-------------------");
    }
}
