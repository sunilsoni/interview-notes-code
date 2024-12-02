package com.interview.notes.code.year.y2024.may24.test5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordFrequencyCounter {

    public static void main(String[] args) {
        String fileName = "sample.txt"; // Path to the text file

        try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
            Map<String, Long> wordFrequencyMap = lines
                    .flatMap(line -> Stream.of(line.split("\\s+"))) // Split each line into words
                    .map(String::toLowerCase) // Convert each word to lowercase
                    .collect(Collectors.groupingBy(word -> word, Collectors.counting())); // Count the frequency of each word

            // Print the word frequency map
            wordFrequencyMap.forEach((word, frequency) -> System.out.println(word + ": " + frequency));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
