package com.interview.notes.code.year.y2025.july.apple.test4;

import java.util.*;

public class WordLadder {
    // Test cases
    public static void main(String[] args) {
        WordLadder solution = new WordLadder();

        // Test Case 1
        String beginWord1 = "hit";
        String endWord1 = "cog";
        List<String> wordList1 = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        System.out.println("Test Case 1:");
        System.out.println(solution.findLadders(beginWord1, endWord1, wordList1));

        // Test Case 2
        String beginWord2 = "red";
        String endWord2 = "tax";
        List<String> wordList2 = Arrays.asList("ted", "tex", "red", "tax", "tad", "den", "rex", "pee");
        System.out.println("\nTest Case 2:");
        System.out.println(solution.findLadders(beginWord2, endWord2, wordList2));

        // Edge Cases
        // Test Case 3: No transformation possible
        String beginWord3 = "hit";
        String endWord3 = "cog";
        List<String> wordList3 = Arrays.asList("hot", "dot", "dog", "lot", "log");
        System.out.println("\nTest Case 3 (No path):");
        System.out.println(solution.findLadders(beginWord3, endWord3, wordList3));

        // Test Case 4: Single character transformation
        String beginWord4 = "a";
        String endWord4 = "c";
        List<String> wordList4 = Arrays.asList("a", "b", "c");
        System.out.println("\nTest Case 4 (Single char):");
        System.out.println(solution.findLadders(beginWord4, endWord4, wordList4));
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> result = new ArrayList<>();
        Set<String> dictionary = new HashSet<>(wordList);

        // If endWord is not in dictionary, return empty list
        if (!dictionary.contains(endWord)) {
            return result;
        }

        // Keep track of the shortest path to each word
        Map<String, List<List<String>>> paths = new HashMap<>();
        paths.put(beginWord, new ArrayList<>(List.of(Collections.singletonList(beginWord))));

        // Keep track of words at current level
        Set<String> currentLevel = new HashSet<>();
        currentLevel.add(beginWord);

        // BFS
        while (!currentLevel.isEmpty() && !currentLevel.contains(endWord)) {
            Set<String> nextLevel = new HashSet<>();
            // Remove words from dictionary that we've already used
            dictionary.removeAll(currentLevel);

            // Explore neighbors of each word in current level
            for (String word : currentLevel) {
                char[] wordChars = word.toCharArray();

                // Try changing each character
                for (int i = 0; i < wordChars.length; i++) {
                    char original = wordChars[i];

                    // Try each possible character
                    for (char c = 'a'; c <= 'z'; c++) {
                        wordChars[i] = c;
                        String newWord = new String(wordChars);

                        if (dictionary.contains(newWord)) {
                            nextLevel.add(newWord);

                            // Add all possible paths to this new word
                            List<List<String>> newPaths = new ArrayList<>();
                            for (List<String> path : paths.get(word)) {
                                List<String> newPath = new ArrayList<>(path);
                                newPath.add(newWord);
                                newPaths.add(newPath);
                            }

                            // Store paths in the map
                            paths.putIfAbsent(newWord, new ArrayList<>());
                            paths.get(newWord).addAll(newPaths);
                        }
                    }
                    wordChars[i] = original;
                }
            }

            // Move to next level
            currentLevel = nextLevel;
        }

        // Return all paths that reach endWord
        return paths.getOrDefault(endWord, new ArrayList<>());
    }
}
