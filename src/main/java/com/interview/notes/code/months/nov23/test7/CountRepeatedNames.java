package com.interview.notes.code.months.nov23.test7;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CountRepeatedNames {
    public static void main(String[] args) {
        String s = "India Japan HongKong China USA Japan USA Japan USA";

        // Split the input string into words
        List<String> words = Arrays.asList(s.split(" "));

        // Use Java 8 streams to count occurrences of each word
        Map<String, Long> wordCounts = words.stream()
                .collect(Collectors.groupingBy(
                        word -> word,
                        Collectors.counting()
                ));

        // Print the counts of repeated names
        wordCounts.entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));

        // Print the unique names
        wordCounts.entrySet().stream()
                .filter(entry -> entry.getValue() ==1)
                .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));
    }
}
