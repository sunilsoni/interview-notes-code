package com.interview.notes.code.year.y2025.july.apple.test3;

import java.util.*;

public class WordLadder {
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // Convert wordList to HashSet for O(1) lookup time
        Set<String> wordSet = new HashSet<>(wordList);

        // Early termination: if endWord isn't in dictionary, no transformation possible
        if (!wordSet.contains(endWord)) return 0;

        // Initialize queue for BFS with the starting word
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);

        // Initialize sequence length counter
        int length = 1;

        // BFS loop continues while there are words to process
        while (!queue.isEmpty()) {
            // Get number of words at current level
            // This is crucial for level-by-level processing
            int size = queue.size();

            // Process all words at current level
            for (int i = 0; i < size; i++) {
                // Get current word from queue
                String current = queue.poll();

                // Try changing each character position
                for (int j = 0; j < current.length(); j++) {
                    // Convert to char array for easier character manipulation
                    char[] wordChars = current.toCharArray();

                    // Try all possible character replacements (a to z)
                    for (char c = 'a'; c <= 'z'; c++) {
                        // Replace character at position j with current char c
                        wordChars[j] = c;

                        // Create new word from modified char array
                        String transformed = new String(wordChars);

                        // If we found the end word, return current length + 1
                        // Add 1 because we need one more step to reach endWord
                        if (transformed.equals(endWord)) {
                            return length + 1;
                        }

                        // If transformed word exists in dictionary:
                        // 1. Add it to queue for processing
                        // 2. Remove from set to avoid cycles
                        if (wordSet.contains(transformed)) {
                            queue.offer(transformed);
                            wordSet.remove(transformed);
                        }
                    }
                }
            }
            // Increment length after processing entire level
            length++;
        }

        // If we reach here, no transformation sequence found
        return 0;
    }
}
