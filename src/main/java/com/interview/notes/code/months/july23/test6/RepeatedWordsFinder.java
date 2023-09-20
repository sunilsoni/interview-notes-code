package com.interview.notes.code.months.july23.test6;

import java.util.HashMap;
import java.util.Map;

public class RepeatedWordsFinder {
    public static void main(String[] args) {
        String text = "MISSISSIPPI";

        Map<String, Integer> wordCountMap = countWords(text);

        // Display repeated words
        System.out.println("Repeated words:");
        for (Map.Entry<String, Integer> entry : wordCountMap.entrySet()) {
            if (entry.getValue() > 1) {
                System.out.println(entry.getKey() + " - Occurrences: " + entry.getValue());
            }
        }
    }

    public static Map<String, Integer> countWords(String text) {
        // Split the text into individual words using whitespace as a delimiter
        String[] words = text.split("\\s+");

        // Create a HashMap to store the count of each word
        Map<String, Integer> wordCountMap = new HashMap<>();

        // Iterate through each word in the text
        for (String word : words) {
            // Convert the word to lowercase to ignore case sensitivity
            word = word.toLowerCase();

            // Update the count of the word in the HashMap
            wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
        }

        return wordCountMap;
    }
}
