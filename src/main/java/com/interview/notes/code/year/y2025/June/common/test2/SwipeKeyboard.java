package com.interview.notes.code.year.y2025.June.common.test2;

import java.util.*;

public class SwipeKeyboard {
    
    // Method to find all matching words from dictionary that match swipe pattern
    public static List<String> findMatchingWords(String swipePattern, List<String> dictionary) {
        List<String> matches = new ArrayList<>();
        
        // Iterate through each word in dictionary
        dictionary.stream()
                 .filter(word -> isWordMatch(word, swipePattern))
                 .forEach(matches::add);
        
        return matches;
    }
    
    // Helper method to check if word matches swipe pattern
    private static boolean isWordMatch(String word, String swipePattern) {
        int wordIndex = 0;    // Track position in word
        int patternIndex = 0; // Track position in swipe pattern
        
        // Try to find each letter of the word in the swipe pattern
        while (wordIndex < word.length() && patternIndex < swipePattern.length()) {
            if (word.charAt(wordIndex) == swipePattern.charAt(patternIndex)) {
                wordIndex++;   // Move to next letter in word
            }
            patternIndex++;   // Always move forward in pattern
        }
        
        // Word matches if we found all its letters
        return wordIndex == word.length();
    }
    
    // Main method with test cases
    public static void main(String[] args) {
        // Test Case 1: Basic matching
        String swipe1 = "hgeflmlko";
        List<String> dict1 = Arrays.asList("hello", "help", "world");
        System.out.println("Test 1: " + findMatchingWords(swipe1, dict1));
        // Expected: [hello]
        
        // Test Case 2: Multiple matches
        String swipe2 = "abcdefg";
        List<String> dict2 = Arrays.asList("ace", "bad", "ag");
        System.out.println("Test 2: " + findMatchingWords(swipe2, dict2));
        // Expected: [ace, ag]
        
        // Test Case 3: No matches
        String swipe3 = "xyz";
        List<String> dict3 = Arrays.asList("hello", "world");
        System.out.println("Test 3: " + findMatchingWords(swipe3, dict3));
        // Expected: []
        
        // Test Case 4: Large input test
        StringBuilder largeSwiping = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            largeSwiping.append("abcdefghijklmnopqrstuvwxyz");
        }
        List<String> largeDict = Arrays.asList("hello", "world", "test", "zebra");
        System.out.println("Test 4 (Large Input): " + 
            findMatchingWords(largeSwiping.toString(), largeDict).size() + " matches found");
    }
}
