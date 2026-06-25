package com.interview.notes.code.year.y2026.june.common.test6;

import java.util.stream.Collectors; // Required to use Stream Collectors

public class AnagramCheckStreamOnly { // Defines the main class

    public static void main(String[] args) { // Entry point for our manual testing
        // Test Case 1: The standard example provided
        runTest("manoj", "anjom", true); 
        // Test Case 2: Different lengths to trigger fast-fail
        runTest("hello", "world1", false); 
        // Test Case 3: Same length, different characters
        runTest("java", "ruby", false); 
        // Test Case 4: Large data inputs using Java's repeat feature
        runTest("a".repeat(50000) + "b", "b" + "a".repeat(50000), true); 
    } // End of main method

    // Helper method to execute tests and print PASS/FAIL
    static void runTest(String str1, String str2, boolean expected) { // Takes inputs and expected boolean
        // Call our pure stream anagram logic
        var actual = isAnagram(str1, str2); 
        // Determine output text by matching actual with expected
        var status = (actual == expected) ? "PASS" : "FAIL"; 
        // Print the status to the console, truncating long strings for readability
        System.out.println(status + " -> Test with: " + str1.substring(0, Math.min(str1.length(), 5)) + "..."); 
    } // End of test helper

    // Pure Stream API method to check if strings are anagrams
    static boolean isAnagram(String s1, String s2) { // Accepts the two strings
        // Guard clause: immediately return false if null or lengths differ (O(1) time)
        if (s1 == null || s2 == null || s1.length() != s2.length()) return false; 
        
        // s1: Get char stream -> sort -> convert int to String -> join into one String
        var sorted1 = s1.chars().sorted().mapToObj(Character::toString).collect(Collectors.joining()); 
        
        // s2: Get char stream -> sort -> convert int to String -> join into one String
        var sorted2 = s2.chars().sorted().mapToObj(Character::toString).collect(Collectors.joining()); 
        
        // Compare the two purely stream-generated strings
        return sorted1.equals(sorted2); 
    } // End of anagram logic

} // End of class