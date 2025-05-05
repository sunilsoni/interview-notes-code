package com.interview.notes.code.year.y2025.april.common.tst6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WordFrequency {
    public static void main(String[] args) throws IOException {
        // Path to your text file
        String filePath = "path/to/your/textfile.txt";

        // Read all lines from the file
        List<String> lines = Files.readAllLines(Paths.get(filePath));

        // Count word frequencies
        Map<String, Long> wordCount = lines.stream()
                .flatMap(line -> Arrays.stream(line.split("\\s+")))
                .map(word -> word.replaceAll("[^a-zA-Z]", "").toLowerCase())
                .filter(word -> !word.isEmpty())
                .collect(Collectors.groupingBy(word -> word, Collectors.counting()));

        // Find the word with the highest frequency
        String mostFrequentWord = wordCount.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("No words found");

        // Output the result
        System.out.println("Most frequent word: " + mostFrequentWord);
    }
}
