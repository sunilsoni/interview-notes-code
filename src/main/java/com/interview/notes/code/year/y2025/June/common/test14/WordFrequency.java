package com.interview.notes.code.year.y2025.June.common.test14;

import java.util.HashMap;
import java.util.Map;

public class WordFrequency {

    public static Map<String, Integer> getWordFrequency(String input) {
        // Handle null or empty input
        if (input == null || input.trim().isEmpty()) {
            return new HashMap<>();
        }

        // Split the string into words
        String[] words = input.toLowerCase().split("\\s+");

        // Create HashMap to store word frequencies
        Map<String, Integer> wordFrequencyMap = new HashMap<>();

        // Count each word
        for (String word : words) {
            wordFrequencyMap.put(word, wordFrequencyMap.getOrDefault(word, 0) + 1);
        }

        return wordFrequencyMap;
    }

    public static void printWordFrequency(String input) {
        System.out.println("\nAnalyzing string: \"" + input + "\"");
        System.out.println("Word Frequencies:");
        System.out.println("----------------");

        Map<String, Integer> frequency = getWordFrequency(input);

        // Print each word and its frequency
        frequency.entrySet().stream()
                .sorted(Map.Entry.comparingByKey()) // Sort alphabetically
                .forEach(entry -> {
                    System.out.printf("%-10s appears %d time(s)%n",
                            "\"" + entry.getKey() + "\"",
                            entry.getValue());
                });
    }

    public static void main(String[] args) {
        // Test cases
        String input1 = "my name is sravani my name is good";
        String input2 = "hello world hello java world";
        String input3 = "the the the quick brown fox";

        // Test with different inputs
        printWordFrequency(input1);
        printWordFrequency(input2);
        printWordFrequency(input3);

        // Test edge cases
        System.out.println("\nTesting edge cases:");
        System.out.println("Empty string: " + getWordFrequency(""));
        System.out.println("Null string: " + getWordFrequency(null));
    }
}
