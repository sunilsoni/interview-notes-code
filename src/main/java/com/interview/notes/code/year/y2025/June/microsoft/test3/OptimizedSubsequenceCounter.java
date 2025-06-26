package com.interview.notes.code.year.y2025.June.microsoft.test3;

import java.util.*;

public class OptimizedSubsequenceCounter {
    
    /**
     * Optimized method to count matching subsequences using character position mapping
     * Time Complexity: O(N + sum(len(words)))
     */
    public static int numMatchingSubseq(String s, String[] words) {
        // Create map of character -> list of waiting words at specific positions
        // Each bucket contains pairs of (word, index) waiting for a specific character
        @SuppressWarnings("unchecked")
        ArrayList<Pair>[] waiting = new ArrayList[26];
        for (int i = 0; i < 26; i++) {
            waiting[i] = new ArrayList<>();
        }
        
        // Count of successful matches
        int count = 0;
        
        // Initialize waiting lists - each word starts waiting for its first character
        for (String word : words) {
            if (word.length() > 0) {
                int index = word.charAt(0) - 'a';
                waiting[index].add(new Pair(word, 0));
            } else {
                // Empty string is always a subsequence
                count++;
            }
        }
        
        // Process each character in the main string
        for (char c : s.toCharArray()) {
            int index = c - 'a';
            // Get all words waiting for current character
            ArrayList<Pair> currentWaiting = waiting[index];
            waiting[index] = new ArrayList<>(); // Reset waiting list for this character
            
            // Process all words waiting for current character
            for (Pair pair : currentWaiting) {
                pair.position++; // Move to next character in word
                
                if (pair.position == pair.word.length()) {
                    // Word complete - found a valid subsequence
                    count++;
                } else {
                    // Word not complete - add to waiting list for next character
                    char nextChar = pair.word.charAt(pair.position);
                    waiting[nextChar - 'a'].add(pair);
                }
            }
        }
        
        return count;
    }
    
    // Helper class to store word and current position
    private static class Pair {
        String word;
        int position;
        
        Pair(String word, int position) {
            this.word = word;
            this.position = position;
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Basic test
        runTest("abcde", 
                new String[]{"a", "bb", "ace", "aec"}, 
                2,
                "Basic Test");

        // Test Case 2: Empty words
        runTest("abc", 
                new String[]{""}, 
                1,
                "Empty Word Test");

        // Test Case 3: Repeated characters
        runTest("aaaa", 
                new String[]{"a", "aa", "aaa", "aaaa"}, 
                4,
                "Repeated Characters Test");

        // Test Case 4: Large string test with many words
        StringBuilder largeSb = new StringBuilder();
        String[] largeWords = new String[1000];
        for (int i = 0; i < 5000; i++) {
            largeSb.append((char)('a' + (i % 26)));
        }
        for (int i = 0; i < 1000; i++) {
            largeWords[i] = "abc";
        }
        runTest(largeSb.toString(), 
                largeWords, 
                1000,
                "Large String Test");

        // Test Case 5: Stress test with long words
        String[] longWords = new String[100];
        for (int i = 0; i < 100; i++) {
            longWords[i] = "abcdefghijklmnopqrstuvwxyz";
        }
        runTest("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz", 
                longWords, 
                100,
                "Long Words Test");
    }

    /**
     * Helper method to run tests and print results with execution time
     */
    private static void runTest(String s, String[] words, int expected, String testName) {
        long startTime = System.nanoTime();
        int result = numMatchingSubseq(s, words);
        long endTime = System.nanoTime();
        double duration = (endTime - startTime) / 1_000_000.0; // Convert to milliseconds
        
        System.out.printf("%s: %s (Expected: %d, Got: %d) - Time: %.2f ms%n",
                         testName,
                         result == expected ? "PASS" : "FAIL",
                         expected,
                         result,
                         duration);
    }
}
