package com.interview.notes.code.year.y2024.nov24.amazon.test27;

import java.util.*;
import java.util.stream.Collectors;

public class AnagramFinder {

    /**
     * Identifies all anagrams in the provided list of words.
     *
     * @param words List of words to be analyzed.
     * @return List of words that have at least one anagram in the input list.
     */
    public static List<String> findAnagrams(List<String> words) {
        if (words == null || words.isEmpty()) {
            return Collections.emptyList();
        }

        // Map to group words by their sorted character sequences
        Map<String, List<String>> groupedWords = new HashMap<>();

        for (String word : words) {
            // Normalize the word: convert to lowercase
            String normalizedWord = word.toLowerCase();

            // Convert the word to a character array, sort it, and convert back to a string
            char[] charArray = normalizedWord.toCharArray();
            Arrays.sort(charArray);
            String sortedWord = new String(charArray);

            // Group words by the sorted character sequence
            groupedWords.computeIfAbsent(sortedWord, k -> new ArrayList<>()).add(word);
        }

        // Collect all words that have at least one anagram (group size >=2)
        List<String> anagrams = groupedWords.values().stream()
                .filter(group -> group.size() >= 2)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        return anagrams;
    }

    /**
     * Main method for testing the findAnagrams function.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Define test cases
        List<TestCase> testCases = Arrays.asList(
                new TestCase(
                        Arrays.asList("arts", "book", "ring", "rats", "star", "kobo"),
                        Arrays.asList("arts", "book", "rats", "star", "kobo"),
                        "Basic Test Case"
                ),
                new TestCase(
                        Collections.emptyList(),
                        Collections.emptyList(),
                        "Empty List Test Case"
                ),
                new TestCase(
                        Arrays.asList("listen", "silent", "enlist", "google", "gooegl"),
                        Arrays.asList("listen", "silent", "enlist", "google", "gooegl"),
                        "Multiple Anagrams Test Case"
                ),
                new TestCase(
                        Arrays.asList("abc", "def", "ghi"),
                        Collections.emptyList(),
                        "No Anagrams Test Case"
                ),
                new TestCase(
                        Arrays.asList("a"),
                        Collections.emptyList(),
                        "Single Word Test Case"
                ),
                new TestCase(
                        Arrays.asList("Dormitory", "Dirty room", "Astronomer", "Moon starer"),
                        Arrays.asList("Dormitory", "Dirty room", "Astronomer", "Moon starer"),
                        "Phrase Anagrams Test Case"
                ),
                new TestCase(
                        generateLargeTestCase(100000),
                        generateLargeExpectedOutput(100000),
                        "Large Data Test Case"
                )
        );

        // Execute test cases
        for (TestCase testCase : testCases) {
            try {
                List<String> result = findAnagrams(testCase.input);
                if (result.containsAll(testCase.expected) && testCase.expected.containsAll(result)) {
                    System.out.println("[PASS] " + testCase.description);
                } else {
                    System.out.println("[FAIL] " + testCase.description);
                    System.out.println("Expected: " + testCase.expected);
                    System.out.println("Got     : " + result);
                }
            } catch (Exception e) {
                System.out.println("[ERROR] " + testCase.description + " - " + e.getMessage());
            }
        }
    }

    /**
     * Generates a large test case with the specified number of words.
     * Half of the words are duplicates to form anagrams.
     *
     * @param size Number of words to generate.
     * @return List of words for the large test case.
     */
    private static List<String> generateLargeTestCase(int size) {
        List<String> largeTestCase = new ArrayList<>();
        for (int i = 0; i < size / 2; i++) {
            String word = "word" + i;
            largeTestCase.add(word);
            // Add an anagram by reversing the word
            largeTestCase.add(new StringBuilder(word).reverse().toString());
        }
        return largeTestCase;
    }

    /**
     * Generates the expected output for the large test case.
     *
     * @param size Number of words to generate.
     * @return List of expected anagrams.
     */
    private static List<String> generateLargeExpectedOutput(int size) {
        List<String> expected = new ArrayList<>();
        for (int i = 0; i < size / 2; i++) {
            String word = "word" + i;
            expected.add(word);
            expected.add(new StringBuilder(word).reverse().toString());
        }
        return expected;
    }

    /**
     * Inner class to represent a test case.
     */
    static class TestCase {
        List<String> input;
        List<String> expected;
        String description;

        TestCase(List<String> input, List<String> expected, String description) {
            this.input = input;
            this.expected = expected;
            this.description = description;
        }
    }
}
