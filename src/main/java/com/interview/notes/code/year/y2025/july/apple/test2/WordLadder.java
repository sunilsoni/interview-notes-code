package com.interview.notes.code.year.y2025.july.apple.test2;

import java.util.*;

public class WordLadder {

    // Main method to execute tests
    public static void main(String[] args) {
        test("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"), 5);
        test("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log"), 0);

        // Large test case for performance check
        List<String> largeWordList = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            largeWordList.add("hit" + i);
        }
        largeWordList.add("cog");
        test("hit", "cog", largeWordList, 0);
    }

    // Helper method to perform tests and output pass/fail
    private static void test(String begin, String end, List<String> wordList, int expectedLength) {
        int result = ladderLength(begin, end, wordList);
        if (result == expectedLength) {
            System.out.println("Test PASSED. Expected: " + expectedLength + " Got: " + result);
        } else {
            System.out.println("Test FAILED. Expected: " + expectedLength + " Got: " + result);
        }
    }

    // Main method solving word ladder problem using BFS
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // Set for fast lookup of words
        Set<String> wordSet = new HashSet<>(wordList);

        // If the end word is not in dictionary, no valid transformation
        if (!wordSet.contains(endWord)) return 0;

        // Queue for BFS traversal, starting from beginWord
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);

        // Track the depth (length of sequence)
        int length = 1;

        while (!queue.isEmpty()) {
            int size = queue.size(); // Current level size
            for (int i = 0; i < size; i++) {
                String current = queue.poll(); // Current word being explored

                // Generate all possible one-letter transformations
                for (int j = 0; j < current.length(); j++) {
                    char[] wordChars = current.toCharArray(); // Convert word to character array for modification
                    for (char c = 'a'; c <= 'z'; c++) { // Change each character to 'a' to 'z'
                        wordChars[j] = c;
                        String transformed = new String(wordChars);

                        // Check if transformation is valid
                        if (transformed.equals(endWord)) {
                            return length + 1; // Found valid sequence
                        }

                        // If word is in set, add to queue for next exploration
                        if (wordSet.contains(transformed)) {
                            queue.offer(transformed);
                            wordSet.remove(transformed); // Remove word to prevent revisiting
                        }
                    }
                }
            }
            length++; // Increment sequence length at each level
        }

        // No valid sequence found
        return 0;
    }
}
