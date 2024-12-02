package com.interview.notes.code.year.y2024.may24.test12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WordFrequencyCounter {

    public static void main(String[] args) {
        String filePath = "input.txt"; // Path to your text file

        try {
            // Read all lines from the text file and concatenate them into a single string
            String content = Files.lines(Paths.get(filePath))
                    .collect(Collectors.joining(" "));

            // Split the content into words using whitespace as delimiter
            String[] words = content.split("\\s+");

            // Count the frequency of each word using streams and lambdas
            Map<String, Long> wordFrequency = Arrays.stream(words)
                    .map(String::toLowerCase) // Convert to lowercase for case-insensitivity
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

            // Print the word frequency
            wordFrequency.forEach((word, frequency) -> System.out.println(word + ": " + frequency));
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }
}
