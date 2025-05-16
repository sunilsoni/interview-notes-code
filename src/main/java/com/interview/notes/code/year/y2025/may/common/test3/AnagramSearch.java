package com.interview.notes.code.year.y2025.may.common.test3;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AnagramSearch {

    // Method to convert word to a Map with character counts (ignores case)
    private static Map<Character, Long> getCharFrequencyMap(String word) {
        return word.toLowerCase().chars()
                .mapToObj(c -> (char) c)                        // Convert int to char
                .collect(Collectors.groupingBy(
                        Function.identity(),                   // Group by character
                        Collectors.counting()));               // Count occurrences
    }

    // Check if two words have the same character frequency map
    private static boolean isAnagram(String word, Map<Character, Long> baseFreqMap) {
        return getCharFrequencyMap(word).equals(baseFreqMap);
    }

    // Returns all matching anagram-like words from the dictionary
    public static List<String> findMatchingWords(List<String> dictionary, String input) {
        Map<Character, Long> inputFreqMap = getCharFrequencyMap(input); // Base map from input word

        // Filter dictionary words that match the frequency map of input word
        return dictionary.stream()
                .filter(word -> isAnagram(word, inputFreqMap))
                .collect(Collectors.toList());
    }

    // Main method to run and validate test cases
    public static void main(String[] args) {
        // Example dictionary
        List<String> dictionary = Arrays.asList(
                "testing", "tes", "test", "estt", "Test", "TTes", "text", "extt", "tex", "ext"
        );

        // Input word
        String inputWord = "Test";

        // Expected output (can be lowercase/uppercase, but must match frequency)
        Set<String> expectedOutput = new HashSet<>(Arrays.asList("test", "estt", "Test", "TTes"));

        // Run the function
        List<String> result = findMatchingWords(dictionary, inputWord);

        // Print results
        System.out.println("Search Word: " + inputWord);
        System.out.println("Matched Words: " + result);
        
        // Validate result
        boolean isPass = result.size() == expectedOutput.size() && expectedOutput.containsAll(result);
        System.out.println("Test Case 1: " + (isPass ? "PASS" : "FAIL"));

        // Additional edge test case: Empty dictionary
        System.out.println("\nEdge Case: Empty dictionary");
        List<String> emptyDict = Collections.emptyList();
        System.out.println("Result: " + findMatchingWords(emptyDict, inputWord));

        // Large data test
        System.out.println("\nLarge Dictionary Test:");
        List<String> largeDict = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            largeDict.add("test");  // add many matches
            largeDict.add("text");  // and some mismatches
            largeDict.add("TesT");
            largeDict.add("estt");
        }
        long startTime = System.currentTimeMillis();
        List<String> largeResult = findMatchingWords(largeDict, "Test");
        long endTime = System.currentTimeMillis();
        System.out.println("Matched: " + largeResult.size());
        System.out.println("Time Taken: " + (endTime - startTime) + " ms");
    }
}