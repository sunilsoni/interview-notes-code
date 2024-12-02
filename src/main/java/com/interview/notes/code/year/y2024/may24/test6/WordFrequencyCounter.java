package com.interview.notes.code.year.y2024.may24.test6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class WordFrequencyCounter {

    public static void main(String[] args) {
        String fileName = "input.txt"; // Specify the path to the input text file

        try {
            // Read all lines from the file and concatenate them into a single string
            String content = new String(Files.readAllBytes(Paths.get(fileName)));

            // Split the content into words, convert them to lowercase, and collect word frequencies
            Map<String, Long> wordFrequencyMap = Arrays.stream(content.split("\\s+"))
                    .map(String::toLowerCase)
                    .collect(Collectors.groupingBy(word -> word, Collectors.counting()));

            // Print the word frequencies
            wordFrequencyMap.forEach((word, frequency) -> System.out.println(word + ": " + frequency));

        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }
    }
}
