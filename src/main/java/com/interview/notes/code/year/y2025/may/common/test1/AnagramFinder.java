package com.interview.notes.code.year.y2025.may.common.test1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AnagramFinder {

    // Main method to demonstrate functionality and run tests
    public static void main(String[] args) {
        // Test cases
        runTests();

        // Example usage with file
        String searchWord = "Test";
        List<String> dictionary = readDictionaryFile("dictionary.txt");
        List<String> matches = findMatches(dictionary, searchWord);

        System.out.println("\nMatches for " + searchWord + ":");
        matches.forEach(System.out::println);
    }

    // Core logic to find matching words
    public static List<String> findMatches(List<String> dictionary, String searchWord) {
        // Convert search word to frequency map for comparison
        Map<Character, Long> searchFreq = getCharacterFrequency(searchWord);

        // Stream through dictionary and filter matching words
        return dictionary.stream()
                .filter(word -> getCharacterFrequency(word).equals(searchFreq))
                .collect(Collectors.toList());
    }

    // Helper method to get character frequency map
    private static Map<Character, Long> getCharacterFrequency(String word) {
        return word.chars()
                .mapToObj(ch -> (char) ch)
                .collect(Collectors.groupingBy(
                        ch -> ch,
                        Collectors.counting()));
    }

    // Read dictionary file
    private static List<String> readDictionaryFile(String filename) {
        try {
            return Files.readAllLines(Paths.get(filename));
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Test method to verify functionality
    private static void runTests() {
        System.out.println("Running tests...");

        // Test case 1: Basic matching
        List<String> testDict = Arrays.asList(
                "testing", "tes", "test", "estt",
                "Test", "TTes", "text", "extt",
                "tex", "ext"
        );

        String searchWord = "Test";
        List<String> results = findMatches(testDict, searchWord);

        // Expected results
        List<String> expected = Arrays.asList("test", "estt", "Test", "TTes");

        // Verify results
        boolean passed = results.size() == expected.size() &&
                results.containsAll(expected);

        System.out.println("Test 1: " + (passed ? "PASS" : "FAIL"));

        // Test case 2: Empty input
        passed = findMatches(new ArrayList<>(), searchWord).isEmpty();
        System.out.println("Test 2 (Empty input): " + (passed ? "PASS" : "FAIL"));

        // Test case 3: Large input performance
        List<String> largeDict = generateLargeInput(100000);
        long startTime = System.currentTimeMillis();
        findMatches(largeDict, searchWord);
        long endTime = System.currentTimeMillis();

        System.out.println("Test 3 (Performance): " +
                ((endTime - startTime) < 1000 ? "PASS" : "FAIL") +
                " (Time: " + (endTime - startTime) + "ms)");
    }

    // Helper method to generate large test data
    private static List<String> generateLargeInput(int size) {
        return Stream.generate(() -> "test")
                .limit(size)
                .collect(Collectors.toList());
    }
}
