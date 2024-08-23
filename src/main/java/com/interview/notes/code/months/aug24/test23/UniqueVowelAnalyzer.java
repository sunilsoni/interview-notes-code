package com.interview.notes.code.months.aug24.test23;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UniqueVowelAnalyzer {
    private static final String VOWELS = "aeiou";

    public static List<WordVowelCount> analyzeUniqueVowels(String paragraph) {
        // Split the paragraph into words, ignoring punctuation and case
        String[] words = paragraph.toLowerCase()
                .replaceAll("[^a-z\\s]", "")
                .trim()
                .split("\\s+");

        // Use a Map to store unique words and their vowel counts
        Map<String, Integer> uniqueWordCounts = new HashMap<>();

        for (String word : words) {
            int vowelCount = countVowelsInWord(word);
            // If the word is already in the map, keep the higher vowel count
            uniqueWordCounts.merge(word, vowelCount, Math::max);
        }

        // Convert the map to a list of WordVowelCount objects
        return uniqueWordCounts.entrySet().stream()
                .map(entry -> new WordVowelCount(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    private static int countVowelsInWord(String word) {
        return (int) word.chars()
                .filter(ch -> VOWELS.indexOf(ch) != -1)
                .count();
    }

    public static void main(String[] args) {
        String input = "The world is changing rapidly. World leaders must adapt to new challenges. The future of our world depends on our actions today.";
        System.out.println("Input: " + input);
        System.out.println("Output: " + analyzeUniqueVowels(input));

        // Additional test cases
        String input2 = "Hello, World! HELLO world. hello, WORLD?";
        System.out.println("\nInput: " + input2);
        System.out.println("Output: " + analyzeUniqueVowels(input2));

        String input3 = "  Punctuation,  should  be  ignored!  ";
        System.out.println("\nInput: " + input3);
        System.out.println("Output: " + analyzeUniqueVowels(input3));

        String input4 = "";
        System.out.println("\nInput: " + input4);
        System.out.println("Output: " + analyzeUniqueVowels(input4));
    }

    static class WordVowelCount {
        String word;
        int vowelCount;

        WordVowelCount(String word, int vowelCount) {
            this.word = word;
            this.vowelCount = vowelCount;
        }

        @Override
        public String toString() {
            return "{\"" + word + "\": " + vowelCount + "}";
        }
    }
}
