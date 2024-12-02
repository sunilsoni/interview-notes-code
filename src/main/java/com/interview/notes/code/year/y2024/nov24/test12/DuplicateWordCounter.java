package com.interview.notes.code.year.y2024.nov24.test12;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DuplicateWordCounter {
    public static void main(String[] args) {
        String sentence = "My name is my name Naresh";

        // Split the sentence into words, convert to lowercase, and count duplicates
        Map<String, Long> wordCounts = Arrays.stream(sentence.toLowerCase().split("\\s+"))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        // Filter and print words that appear more than once
        wordCounts.entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .forEach(entry -> System.out.println(entry.getKey() + " - " + entry.getValue()));

        // Count the number of duplicate word types
        long duplicateTypesCount = wordCounts.values().stream()
                .filter(count -> count > 1)
                .count();

        System.out.println("Number of duplicate word types: " + duplicateTypesCount);
    }
}
