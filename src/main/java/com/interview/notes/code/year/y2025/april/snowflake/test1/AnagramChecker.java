package com.interview.notes.code.year.y2025.april.snowflake.test1;

import java.util.Map;
import java.util.stream.Collectors;

public class AnagramChecker {
    
    // Main method containing test cases
    public static void main(String[] args) {
        // Test cases to verify the solution
        testAnagram("listen", "silent", true);    // Basic anagram
        testAnagram("hello", "world", false);     // Non-anagram
        testAnagram("", "", true);                // Empty strings
        testAnagram("a", "a", true);              // Single character
        testAnagram("rat", "tar", true);          // Simple anagram
        testAnagram("hello", "hello", true);      // Same string
        testAnagram("Hello", "hello", false);     // Case sensitive
        testAnagram("abc", "abcd", false);        // Different lengths
        testAnagram("  ", "  ", true);            // Whitespace
        
        // Large input test
        String str1 = "a".repeat(100000);
        String str2 = "a".repeat(100000);
        testAnagram(str1, str2, true);            // Large input test
    }

    // Method to check if two strings are anagrams using Java 8 Stream API
    public static boolean areAnagrams(String str1, String str2) {
        // If strings are null or of different lengths, they can't be anagrams
        if (str1 == null || str2 == null || str1.length() != str2.length()) {
            return false;
        }

        // Convert strings to character frequency maps using streams
        Map<Character, Long> freqMap1 = str1.chars()
                .mapToObj(ch -> (char) ch)
                .collect(Collectors.groupingBy(
                        ch -> ch,
                        Collectors.counting()
                ));

        Map<Character, Long> freqMap2 = str2.chars()
                .mapToObj(ch -> (char) ch)
                .collect(Collectors.groupingBy(
                        ch -> ch,
                        Collectors.counting()
                ));

        // Compare the frequency maps
        return freqMap1.equals(freqMap2);
    }

    // Test helper method to verify results
    private static void testAnagram(String str1, String str2, boolean expectedResult) {
        boolean result = areAnagrams(str1, str2);
        System.out.printf("Test: '%s' and '%s' -> %s (Expected: %s)%n",
                str1, str2, result, expectedResult);
        System.out.println(result == expectedResult ? "PASS" : "FAIL");
    }
}
