package com.interview.notes.code.months.june24.test1;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        String text = "Java is a programming language Java is also a platform Java is fun";

        // Split the text into words and create a stream
        Map<String, Long> wordFrequency = Arrays.stream(text.split("\\s+"))
                // Normalize the words to lowercase to ensure case-insensitive comparison
                .map(String::toLowerCase)
                // Group the words by their occurrences
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        // Filter the words with more than one occurrence
        Map<String, Long> duplicateWords = wordFrequency.entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        // Print the duplicate words and their frequencies
        duplicateWords.forEach((word, frequency) -> System.out.println(word + ": " + frequency));
    }
}

