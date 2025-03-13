package com.interview.notes.code.year.y2025.march.common.test1;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class WordPathFinder {

    // Method to find the shortest path from startWord to endWord
    public static int shortestWordPath(String startWord, String endWord, Set<String> dictionary) {
        if (!dictionary.contains(endWord)) return 0; // If endWord not in dictionary, no path exists

        Queue<String> queue = new LinkedList<>();
        queue.offer(startWord);

        int steps = 1; // Start from step 1 since startWord is counted

        while (!queue.isEmpty()) {
            int size = queue.size(); // Current level size

            for (int i = 0; i < size; i++) {
                String currentWord = queue.poll();

                if (currentWord.equals(endWord)) return steps; // Reached endWord

                // Generate all possible next words
                for (int j = 0; j < currentWord.length(); j++) {
                    char[] chars = currentWord.toCharArray();
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        chars[j] = ch;
                        String nextWord = new String(chars);

                        // Check validity and if it's in the dictionary
                        if (dictionary.contains(nextWord)) {
                            queue.offer(nextWord);
                            dictionary.remove(nextWord); // Mark as visited
                        }
                    }
                }
            }
            steps++; // Increment steps for next level
        }

        return 0; // Path does not exist
    }

    // Main method for testing
    public static void main(String[] args) {

        Set<String> dictionary = Stream.of("hot", "dot", "dog", "lot", "log", "cog")
                .collect(Collectors.toSet());

        // Simple tests
        System.out.println("Test Case 1: " +
                (shortestWordPath("hit", "cog", new HashSet<>(dictionary)) == 5 ? "PASS" : "FAIL"));

        System.out.println("Test Case 2: " +
                (shortestWordPath("hit", "hot", new HashSet<>(dictionary)) == 2 ? "PASS" : "FAIL"));

        // Edge case: end word not in dictionary
        System.out.println("Test Case 3 (End word missing): " +
                (shortestWordPath("hit", "xyz", new HashSet<>(dictionary)) == 0 ? "PASS" : "FAIL"));

        // Large data test
        Set<String> largeDict = IntStream.range(0, 10000)
                .mapToObj(i -> "word" + i)
                .collect(Collectors.toSet());
        largeDict.add("end");

        System.out.println("Large Data Test: " +
                (shortestWordPath("word0", "end", new HashSet<>(largeDict)) == 0 ? "PASS" : "FAIL"));
    }
}
