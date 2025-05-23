package com.interview.notes.code.year.y2025.may.common.test8;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class StringFrequency {
    public static void main(String[] args) {
        // Sample array of strings
        String[] words = {"apple", "banana", "apple", "cherry", "date", "banana", "apple"};

        // Method 1: Using Collectors.groupingBy and counting
        Map<String, Long> frequency1 = Arrays.stream(words)
                .collect(Collectors.groupingBy(
                        word -> word,    // Key is the word itself
                        Collectors.counting()  // Value is the count
                ));

        // Print results
        System.out.println("Method 1 Results:");
        frequency1.forEach((word, count) ->
                System.out.println(word + ": " + count));

        // Method 2: Using Collectors.toMap
        Map<String, Integer> frequency2 = Arrays.stream(words)
                .collect(Collectors.toMap(
                        word -> word,    // Key is the word itself
                        word -> 1,       // Initial value for each word
                        Integer::sum     // Sum if there's a duplicate
                ));

        System.out.println("\nMethod 2 Results:");
        frequency2.forEach((word, count) ->
                System.out.println(word + ": " + count));
    }
}
