package com.interview.notes.code.year.y2025.may.common.test9;

import java.util.*;

public class WordBreakSolution {

    public static List<String> wordBreak(String s, List<String> wordDict) {
        // Create a HashSet for O(1) word lookup
        Set<String> dictionary = new HashSet<>(wordDict);

        // Create result object to store all valid sentences
        Result result = new Result();

        // Start recursive process with initial empty StringBuilder
        findSentences(s, 0, dictionary, new StringBuilder(), result);

        return result.sentences;
    }

    // Recursive helper method to find all possible valid sentences
    private static void findSentences(String s, int start, Set<String> dict,
                                      StringBuilder current, Result result) {
        // Base case: if we've processed the entire string
        if (start >= s.length()) {
            // Remove trailing space and add to results
            result.sentences.add(current.toString().trim());
            return;
        }

        // Try all possible words starting from current position
        for (int end = start + 1; end <= s.length(); end++) {
            // Extract potential word
            String word = s.substring(start, end);

            // If word exists in dictionary, process it
            if (dict.contains(word)) {
                // Save current length to backtrack later
                int len = current.length();

                // Add word and space to current sentence
                current.append(word).append(" ");

                // Recurse with remaining string
                findSentences(s, end, dict, current, result);

                // Backtrack by removing the added word and space
                current.setLength(len);
            }
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        // Test Case 1: Trading suspicious message
        String s1 = "stockgodown";
        List<String> dict1 = Arrays.asList("stock", "go", "down", "money");
        testCase("Test 1", s1, dict1, List.of("stock go down"));

        // Test Case 2: Normal message
        String s2 = "pineapplepenapple";
        List<String> dict2 = Arrays.asList("apple", "pen", "applepen", "pine", "pineapple");
        testCase("Test 2", s2, dict2,
                Arrays.asList("pine apple pen apple", "pineapple pen apple", "pine applepen apple"));

        // Test Case 3: Edge case - Empty string
        testCase("Test 3 (Empty)", "", new ArrayList<>(), new ArrayList<>());

        // Test Case 4: Large input test
        StringBuilder largeInput = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            largeInput.append("stock");
        }
        List<String> largeDict = List.of("stock");
        testCase("Test 4 (Large Input)", largeInput.toString(), largeDict, null);
    }

    // Helper method to run and validate test cases
    private static void testCase(String testName, String input, List<String> dict,
                                 List<String> expected) {
        System.out.println("\nRunning " + testName + ":");
        System.out.println("Input: " + input);
        System.out.println("Dictionary: " + dict);

        long startTime = System.currentTimeMillis();
        List<String> result = wordBreak(input, dict);
        long endTime = System.currentTimeMillis();

        System.out.println("Output: " + result);
        System.out.println("Time taken: " + (endTime - startTime) + "ms");

        if (expected != null) {
            boolean passed = new HashSet<>(result).equals(new HashSet<>(expected));
            System.out.println("Test " + (passed ? "PASSED" : "FAILED"));
        }
    }

    // Main class to store the result during recursion
    static class Result {
        List<String> sentences = new ArrayList<>();
    }
}
