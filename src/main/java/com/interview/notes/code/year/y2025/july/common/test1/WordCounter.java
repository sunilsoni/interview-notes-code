package com.interview.notes.code.year.y2025.july.common.test1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordCounter {

    /**
     * Reads a text file and returns a map of each distinct word to its occurrence count.
     *
     * @param filePath the path to the text file
     * @return a Map where keys are words (lowercased) and values are their counts
     * @throws IOException if an I/O error occurs reading from the file
     */
    public static Map<String, Long> countWords(String filePath) throws IOException {
        // Open the file as a stream of lines; ensures file is closed automatically
        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            return lines
                    // Split each line into words by non-word characters (punctuation, spaces, etc.)
                    .flatMap(line -> Arrays.stream(line.split("\\W+")))
                    // Remove any empty strings resulting from consecutive non-word chars
                    .filter(word -> !word.isEmpty())
                    // Normalize to lowercase for case-insensitive counting
                    .map(String::toLowerCase)
                    // Collect into a map: word → count
                    .collect(Collectors.groupingBy(
                            Function.identity(),            // key = the word itself
                            Collectors.counting()           // value = number of occurrences
                    ));
        }
    }

    /**
     * Simple test harness in main(): creates sample files, runs countWords, and reports PASS/FAIL.
     */
    public static void main(String[] args) {
        try {
            // ---------- Test Case 1: small sample ----------
            Path test1 = Files.createTempFile("test1", ".txt");
            Files.write(test1, Arrays.asList(
                    "Hello world",
                    "HELLO, world!",
                    "foo bar foo"
            ));
            Map<String, Long> result1 = countWords(test1.toString());
            Map<String, Long> expected1 = Map.of(
                    "hello", 2L,
                    "world", 2L,
                    "foo", 2L,
                    "bar", 1L
            );
            System.out.println("Test 1: " + (result1.equals(expected1) ? "PASS" : "FAIL"));

            // ---------- Test Case 2: repeated large data ----------
            Path test2 = Files.createTempFile("test2", ".txt");
            // Generate 100_000 lines of "alpha beta" → 200_000 words
            try (Stream<String> gen = Stream.generate(() -> "alpha beta").limit(100_000)) {
                Files.write(test2, gen.collect(Collectors.toList()));
            }
            Map<String, Long> result2 = countWords(test2.toString());
            boolean pass2 = result2.getOrDefault("alpha", 0L) == 100_000L
                    && result2.getOrDefault("beta", 0L) == 100_000L
                    && result2.size() == 2;
            System.out.println("Test 2 (large data): " + (pass2 ? "PASS" : "FAIL"));

        } catch (IOException e) {
            System.err.println("I/O error during tests: " + e.getMessage());
        }
    }
}