package com.interview.notes.code.year.y2025.april.common.test2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class RepeatedWords {
    public static void findRepeatedWords(String sentence) {
        // Convert the sentence to lowercase to make the comparison case-insensitive
        sentence = sentence.toLowerCase();

        // Split the sentence into words and remove punctuation
        String[] words = sentence.replaceAll("[^a-zA-Z\\s]", "").split("\\s+");

        // Using Stream to count word frequencies
        Map<String, Long> wordFrequency = Arrays.stream(words)
                .collect(Collectors.groupingBy(
                        word -> word,
                        HashMap::new,
                        Collectors.counting()
                ));

        // Print only the words that appear more than once
        System.out.println("Repeated words and their frequencies:");
        wordFrequency.entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue() + " times"));
    }

    public static void main(String[] args) {
        String sentence = "The quick brown fox jumps over the lazy dog. The fox is quick.";
        findRepeatedWords(sentence);
    }
}
