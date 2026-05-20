package com.interview.notes.code.year.y2026.may.Coupang.test5;

import java.util.Arrays;
import java.util.HashSet;

public class LongestWordDictionary {

    public static String longestWord(String[] words) {
        // Sort the array alphabetically so lexicographically smaller words appear first
        Arrays.sort(words);
        
        // Create a HashSet to store valid words that can be built incrementally
        var builtWords = new HashSet<String>();
        
        // Add an empty string to act as the base prefix for all single-character words
        builtWords.add("");
        
        // Initialize a variable to keep track of the longest valid word found so far
        var longest = "";
        
        // Loop through each word in the newly sorted array
        for (var word : words) {
            // Check if the current word's prefix (word minus its last char) is in our valid set
            if (builtWords.contains(word.substring(0, word.length() - 1))) {
                // If the prefix exists, this word is also valid, so add it to the set
                builtWords.add(word);
                
                // Check if this newly validated word is strictly longer than our current longest
                if (word.length() > longest.length()) {
                    // Update the longest word to be this current word
                    longest = word;
                }
            }
        }
        
        // Return the final longest word found after evaluating all items
        return longest;
    }

    // --- Simple Main Method for Testing (No JUnit) ---
    public static void main(String[] args) {
        System.out.println("--- Running Tests ---");
        
        // Test Case 1: Provided Example 1
        String[] tc1 = {"w","wo","wor","worl","world"};
        runTest("Test Case 1", tc1, "world");

        // Test Case 2: Provided Example 2
        String[] tc2 = {"a","banana","app","appl","ap","apply","apple"};
        runTest("Test Case 2", tc2, "apple");

        // Test Case 3: No valid combinations
        String[] tc3 = {"banana", "orange", "apple"};
        runTest("Test Case 3", tc3, "");

        // Test Case 4: Tie breaker with identical lengths
        String[] tc4 = {"a", "ab", "abc", "z", "zy", "zyx"};
        runTest("Test Case 4", tc4, "abc");

        // Test Case 5: Large Dataset simulation
        // Generate a large array where "z...z" is technically the longest built word
        String[] largeData = new String[1000];
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 1000; i++) {
            // Fill with some dummy data
            largeData[i] = "dummy" + i;
        }
        // Inject a valid sequence up to length 30 at the end
        for (int i = 0; i < 30; i++) {
            sb.append("z");
            largeData[970 + i] = sb.toString();
        }
        // The expected longest word is a string of 30 'z's
        runTest("Test Case 5 (Large Data)", largeData, sb.toString());
    }

    // Custom test runner method to print PASS/FAIL status cleanly
    private static void runTest(String testName, String[] input, String expected) {
        String result = longestWord(input);
        boolean isPass = result.equals(expected);
        
        if (isPass) {
            System.out.println("✅ PASS: " + testName);
        } else {
            System.out.println("❌ FAIL: " + testName + " | Expected: '" + expected + "' but got: '" + result + "'");
        }
    }
}