package com.interview.notes.code.year.y2025.march.common.test1;

import java.util.*;
import java.util.stream.*;

public class WordLadderSolution {

    // Simulated dictionary for demonstration.
    // In a real-world scenario, this would be replaced with an actual dictionary lookup.
    private static Set<String> dictionary = new HashSet<>(Arrays.asList(
        "hit", "hot", "dot", "dog", "cog", "lot", "log"
    ));

    // Main method to run test cases.
    public static void main(String[] args) {
        // Run the test cases and print PASS/FAIL results.
        testWordLadder();
    }

    /**
     * Finds the shortest transformation sequence (word ladder) from beginWord to endWord.
     * Each step in the sequence changes one letter, and each intermediate word must be valid.
     *
     * @param beginWord The starting word.
     * @param endWord   The target word.
     * @return A list representing the sequence of words from beginWord to endWord, or an empty list if no path exists.
     */
    public static List<String> findWordLadder(String beginWord, String endWord) {
        // If the start and end words are not the same length, return empty as per constraints.
        if (beginWord.length() != endWord.length()) return Collections.emptyList();

        // Queue for BFS where each element is a path (list of words).
        Queue<List<String>> queue = new LinkedList<>();
        // Start by adding the initial word in a list.
        queue.offer(Arrays.asList(beginWord));

        // Set to track visited words to prevent cycles.
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        // Process nodes in the BFS queue.
        while (!queue.isEmpty()) {
            // Remove the first path from the queue.
            List<String> path = queue.poll();
            // Get the last word in the current path.
            String lastWord = path.get(path.size() - 1);

            // If we have reached the target word, return the path.
            if (lastWord.equals(endWord)) {
                return path;
            }

            // Generate all possible one-letter transformations of the last word.
            // Using Java 8 IntStream to iterate over each character index.
            IntStream.range(0, lastWord.length()).forEach(i -> {
                // For each letter from 'a' to 'z'.
                for (char c = 'a'; c <= 'z'; c++) {
                    // Build a new word by replacing the character at position i.
                    StringBuilder sb = new StringBuilder(lastWord);
                    sb.setCharAt(i, c);
                    String newWord = sb.toString();

                    // Check that the new word is different, valid, and not already visited.
                    if (!newWord.equals(lastWord) && isValid(newWord) && !visited.contains(newWord)) {
                        // Mark newWord as visited.
                        visited.add(newWord);
                        // Create a new path by appending newWord to the current path.
                        List<String> newPath = new ArrayList<>(path);
                        newPath.add(newWord);
                        // Add the new path to the queue for further exploration.
                        queue.offer(newPath);
                    }
                }
            });
        }
        // Return an empty list if no transformation sequence exists.
        return Collections.emptyList();
    }

    /**
     * Dummy isValid method.
     * In a real implementation, this would check against an actual dictionary.
     *
     * @param word The word to validate.
     * @return true if the word exists in the dictionary, false otherwise.
     */
    public static boolean isValid(String word) {
        return dictionary.contains(word);
    }

    /**
     * Runs test cases to verify the solution.
     * It prints out whether each test case passes or fails.
     */
    public static void testWordLadder() {
        // Define test cases with start word, end word, and the expected transformation sequence.
        List<TestCase> testCases = Arrays.asList(
            // Test case where a valid transformation exists.
            new TestCase("hit", "cog", Arrays.asList("hit", "hot", "dot", "dog", "cog")),
            // Another valid transformation example.
            new TestCase("hit", "log", Arrays.asList("hit", "hot", "lot", "log")),
            // Edge case: start and end are the same.
            new TestCase("hit", "hit", Arrays.asList("hit")),
            // Fail case: no possible transformation.
            new TestCase("hit", "xyz", Collections.emptyList())
        );

        int testCaseNumber = 1;
        // Evaluate each test case.
        for (TestCase tc : testCases) {
            List<String> result = findWordLadder(tc.beginWord, tc.endWord);
            // Compare the result with the expected output.
            boolean passed = result.equals(tc.expectedPath);
            System.out.println("Test Case " + testCaseNumber + ": " + (passed ? "PASS" : "FAIL"));
            if (!passed) {
                System.out.println("   Expected: " + tc.expectedPath);
                System.out.println("   Got     : " + result);
            }
            testCaseNumber++;
        }
    }

    // Helper class to encapsulate a test case.
    static class TestCase {
        String beginWord;
        String endWord;
        List<String> expectedPath;

        TestCase(String beginWord, String endWord, List<String> expectedPath) {
            this.beginWord = beginWord;
            this.endWord = endWord;
            this.expectedPath = expectedPath;
        }
    }
}