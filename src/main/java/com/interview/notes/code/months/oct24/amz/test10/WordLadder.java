package com.interview.notes.code.months.oct24.amz.test10;

import java.util.*;

public class WordLadder {
    // Function to find the length of the shortest transformation sequence
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // Convert the wordList to a set for O(1) lookup
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) {
            // Early exit if the endWord is not in the dictionary
            return 0;
        }

        // Initialize BFS
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);

        // Visited set to prevent cycles
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        int level = 1; // Start from level 1

        while (!queue.isEmpty()) {
            int levelSize = queue.size(); // Number of elements at the current level

            // Process all nodes at the current level
            for (int i = 0; i < levelSize; i++) {
                String currentWord = queue.poll(); // Get the next word in the queue
                // If we have reached the endWord
                if (currentWord.equals(endWord)) {
                    return level; // Return the current level as the shortest path length
                }

                // Generate all possible transformations
                for (String neighbor : getNeighbors(currentWord, wordSet)) {
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor); // Mark the neighbor as visited
                        queue.offer(neighbor); // Add the neighbor to the queue
                    }
                }
            }

            level++; // Increment level after processing current level
        }

        // If endWord is not reachable
        return 0;
    }

    // Helper function to generate all possible transformations of a word
    private List<String> getNeighbors(String word, Set<String> wordSet) {
        List<String> neighbors = new ArrayList<>();

        char[] chars = word.toCharArray();
        // For each character position in the word
        for (int i = 0; i < chars.length; i++) {
            char originalChar = chars[i]; // Store the original character
            // Try all possible lowercase letters
            for (char c = 'a'; c <= 'z'; c++) {
                if (c == originalChar) continue; // Skip if the character is the same as the original
                chars[i] = c; // Replace the character at position i
                String transformedWord = new String(chars); // Create the transformed word
                if (wordSet.contains(transformedWord)) {
                    neighbors.add(transformedWord); // Add the transformed word if it is in the word set
                }
            }
            chars[i] = originalChar; // Restore the original character
        }

        return neighbors;
    }

    // Test method to check if all test cases pass or fail
    public static void main(String[] args) {
        WordLadder solver = new WordLadder();
        boolean allTestsPass = true;

        // Test Case 1
        String beginWord1 = "hitl";
        String endWord1 = "cog";
        List<String> wordList1 = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog", "hit");
        int expected1 = 5;
        int result1 = solver.ladderLength(beginWord1, endWord1, wordList1);
        if (result1 == expected1) {
            System.out.println("Test Case 1: PASS");
        } else {
            System.out.println("Test Case 1: FAIL");
            allTestsPass = false;
        }

        // Test Case 2 (No possible transformation)
        String beginWord2 = "hit";
        String endWord2 = "cog";
        List<String> wordList2 = Arrays.asList("hot", "dot", "dog", "lot", "log");
        int expected2 = 0;
        int result2 = solver.ladderLength(beginWord2, endWord2, wordList2);
        if (result2 == expected2) {
            System.out.println("Test Case 2: PASS");
        } else {
            System.out.println("Test Case 2: FAIL");
            allTestsPass = false;
        }

        // Test Case 3 (Begin word equals end word)
        String beginWord3 = "hit";
        String endWord3 = "hit";
        List<String> wordList3 = Arrays.asList("hit");
        int expected3 = 1;
        int result3 = solver.ladderLength(beginWord3, endWord3, wordList3);
        if (result3 == expected3) {
            System.out.println("Test Case 3: PASS");
        } else {
            System.out.println("Test Case 3: FAIL");
            allTestsPass = false;
        }

        // Test Case 4 (Large data input)
        String beginWord4 = "starting";
        String endWord4 = "stopping";
        List<String> wordList4 = generateLargeWordList(beginWord4, endWord4);
        int expected4 = 9; // Assuming a known shortest path length
        int result4 = solver.ladderLength(beginWord4, endWord4, wordList4);
        if (result4 == expected4) {
            System.out.println("Test Case 4: PASS");
        } else {
            System.out.println("Test Case 4: FAIL");
            allTestsPass = false;
        }

        if (allTestsPass) {
            System.out.println("All test cases passed.");
        } else {
            System.out.println("Some test cases failed.");
        }
    }

    // Generates a large word list for testing performance with large datasets
    private static List<String> generateLargeWordList(String beginWord, String endWord) {
        Set<String> wordSet = new HashSet<>();
        // Generate words of length equal to beginWord
        int wordLength = beginWord.length();
        Random random = new Random();

        // Generate a large number of random words
        for (int i = 0; i < 100000; i++) {
            StringBuilder sb = new StringBuilder(wordLength);
            for (int j = 0; j < wordLength; j++) {
                char c = (char) ('a' + random.nextInt(26)); // Generate a random lowercase letter
                sb.append(c);
            }
            wordSet.add(sb.toString()); // Add the generated word to the set
        }

        // Ensure beginWord and endWord are in the set
        wordSet.add(beginWord);
        wordSet.add(endWord);

        return new ArrayList<>(wordSet); // Convert the set to a list and return it
    }
}
