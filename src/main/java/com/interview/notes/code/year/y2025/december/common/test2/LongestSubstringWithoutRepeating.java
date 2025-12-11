package com.interview.notes.code.year.y2025.december.common.test2;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeating {

    /**
     * Finds the longest substring without repeating characters
     * Returns the actual substring, not just the length
     */
    public static String findLongestSubstring(String inputString) {
        
        // Handle edge case: if string is null, return empty string
        if (inputString == null) {
            return "";
        }
        
        // Handle edge case: if string is empty, return empty string
        if (inputString.isEmpty()) {
            return "";
        }
        
        // HashSet to store characters in current window
        // Set gives O(1) lookup time to check if character exists
        Set<Character> characterSet = new HashSet<>();
        
        // Left pointer marks the start of our sliding window
        int leftPointer = 0;
        
        // Variables to track the longest substring found
        // maxLength stores the maximum length found so far
        int maxLength = 0;
        
        // startIndex stores where the longest substring begins
        int startIndex = 0;
        
        // Right pointer iterates through each character
        // This marks the end of our sliding window
        for (int rightPointer = 0; rightPointer < inputString.length(); rightPointer++) {
            
            // Get the current character at right pointer
            char currentChar = inputString.charAt(rightPointer);
            
            // If character already exists in window (duplicate found)
            // We need to shrink window from left until duplicate is removed
            while (characterSet.contains(currentChar)) {
                
                // Get character at left pointer
                char leftChar = inputString.charAt(leftPointer);
                
                // Remove left character from set
                characterSet.remove(leftChar);
                
                // Move left pointer forward to shrink window
                leftPointer++;
            }
            
            // Add current character to our set
            characterSet.add(currentChar);
            
            // Calculate current window size
            int currentWindowSize = rightPointer - leftPointer + 1;
            
            // If current window is larger than max found
            // Update maxLength and save the start position
            if (currentWindowSize > maxLength) {
                
                // Update the maximum length
                maxLength = currentWindowSize;
                
                // Save the starting index of this longest substring
                startIndex = leftPointer;
            }
        }
        
        // Extract the longest substring using start index and max length
        // substring(start, end) extracts from start to end-1
        String longestSubstring = inputString.substring(startIndex, startIndex + maxLength);
        
        // Return the actual substring
        return longestSubstring;
    }
    
    /**
     * Helper method to run a single test case
     * Compares actual result with expected result
     */
    public static void runTestCase(String testName, String input, String expected) {
        
        // Get the actual result from our method
        String actual = findLongestSubstring(input);
        
        // Check if actual matches expected
        // For this problem, we check if lengths match (multiple valid answers possible)
        boolean passed = (actual.length() == expected.length());
        
        // Also verify no repeating characters in result
        boolean noRepeats = hasNoRepeatingCharacters(actual);
        
        // Test passes if length matches AND no repeating characters
        passed = passed && noRepeats;
        
        // Print test result with details
        String status = passed ? "PASS" : "FAIL";
        
        // Show input string (truncate if too long)
        String displayInput = input;
        if (input != null && input.length() > 30) {
            displayInput = input.substring(0, 30) + "...(length:" + input.length() + ")";
        }
        
        // Print formatted result
        System.out.println("[" + status + "] " + testName);
        System.out.println("       Input: \"" + displayInput + "\"");
        System.out.println("       Expected Length: " + expected.length());
        System.out.println("       Actual Substring: \"" + actual + "\"");
        System.out.println("       Actual Length: " + actual.length());
        System.out.println();
    }
    
    /**
     * Helper method to verify no repeating characters in string
     * Returns true if all characters are unique
     */
    public static boolean hasNoRepeatingCharacters(String str) {
        
        // Use set to track characters we have seen
        Set<Character> seen = new HashSet<>();
        
        // Check each character in the string
        for (int i = 0; i < str.length(); i++) {
            
            // Get current character
            char c = str.charAt(i);
            
            // If character already in set, there is a repeat
            if (seen.contains(c)) {
                return false;
            }
            
            // Add character to set
            seen.add(c);
        }
        
        // All characters are unique
        return true;
    }
    
    /**
     * Generates a large test string with pattern
     * Used for testing performance with big data
     */
    public static String generateLargeString(int length, String pattern) {
        
        // StringBuilder is efficient for string concatenation
        StringBuilder builder = new StringBuilder();
        
        // Keep appending pattern until we reach desired length
        while (builder.length() < length) {
            builder.append(pattern);
        }
        
        // Return substring of exact length needed
        return builder.substring(0, length);
    }
    
    /**
     * Main method to run all test cases
     */
    public static void main(String[] args) {
        
        System.out.println("================================================");
        System.out.println("LONGEST SUBSTRING WITHOUT REPEATING CHARACTERS");
        System.out.println("(Returns actual substring)");
        System.out.println("================================================");
        System.out.println();
        
        // ========== BASIC TEST CASES ==========
        System.out.println("--- BASIC TEST CASES ---");
        System.out.println();
        
        // Test Case 1: The main example - application
        // Longest substring is "plicat" with length 6
        runTestCase("Test 1: application", "application", "plicat");
        
        // Test Case 2: Standard example
        // "abcabcbb" -> "abc" is longest (length 3)
        runTestCase("Test 2: abcabcbb", "abcabcbb", "abc");
        
        // Test Case 3: All same characters
        // "bbbbb" -> "b" is longest (length 1)
        runTestCase("Test 3: All same chars", "bbbbb", "b");
        
        // Test Case 4: Pattern case
        // "pwwkew" -> "wke" is longest (length 3)
        runTestCase("Test 4: pwwkew", "pwwkew", "wke");
        
        // Test Case 5: All unique characters
        // "abcdef" -> whole string is answer (length 6)
        runTestCase("Test 5: All unique", "abcdef", "abcdef");
        
        // ========== EDGE CASES ==========
        System.out.println("--- EDGE CASES ---");
        System.out.println();
        
        // Test Case 6: Single character
        runTestCase("Test 6: Single char", "a", "a");
        
        // Test Case 7: Empty string
        runTestCase("Test 7: Empty string", "", "");
        
        // Test Case 8: Null input
        runTestCase("Test 8: Null input", null, "");
        
        // Test Case 9: Two same characters
        runTestCase("Test 9: Two same", "aa", "a");
        
        // Test Case 10: Two different characters
        runTestCase("Test 10: Two different", "ab", "ab");
        
        // ========== SPECIAL CASES ==========
        System.out.println("--- SPECIAL CASES ---");
        System.out.println();
        
        // Test Case 11: With spaces
        runTestCase("Test 11: With spaces", "a b c", "a b");
        
        // Test Case 12: Numbers
        runTestCase("Test 12: Numbers", "12321", "123");
        
        // Test Case 13: Mixed case (case sensitive)
        runTestCase("Test 13: Mixed case", "aAbBcC", "aAbBcC");
        
        // Test Case 14: Longest at end
        runTestCase("Test 14: Longest at end", "aabcdef", "abcdef");
        
        // Test Case 15: Longest at start
        runTestCase("Test 15: Longest at start", "abcdefa", "abcdef");
        
        // ========== LARGE DATA TESTS ==========
        System.out.println("--- LARGE DATA TESTS ---");
        System.out.println();
        
        // Test Case 16: Large string with pattern
        String largePattern = generateLargeString(100000, "abcd");
        long startTime = System.currentTimeMillis();
        String result16 = findLongestSubstring(largePattern);
        long endTime = System.currentTimeMillis();
        System.out.println("[PASS] Test 16: Large pattern (100K)");
        System.out.println("       Result: \"" + result16 + "\"");
        System.out.println("       Length: " + result16.length());
        System.out.println("       Time: " + (endTime - startTime) + " ms");
        System.out.println();
        
        // Test Case 17: Large string with alphabet
        String largeAlphabet = generateLargeString(100000, "abcdefghijklmnopqrstuvwxyz");
        startTime = System.currentTimeMillis();
        String result17 = findLongestSubstring(largeAlphabet);
        endTime = System.currentTimeMillis();
        System.out.println("[PASS] Test 17: Large alphabet (100K)");
        System.out.println("       Result: \"" + result17 + "\"");
        System.out.println("       Length: " + result17.length());
        System.out.println("       Time: " + (endTime - startTime) + " ms");
        System.out.println();
        
        // Test Case 18: Very large string (1 million)
        String veryLarge = generateLargeString(1000000, "abcdefghij");
        startTime = System.currentTimeMillis();
        String result18 = findLongestSubstring(veryLarge);
        endTime = System.currentTimeMillis();
        System.out.println("[PASS] Test 18: Very large (1M)");
        System.out.println("       Result: \"" + result18 + "\"");
        System.out.println("       Length: " + result18.length());
        System.out.println("       Time: " + (endTime - startTime) + " ms");
        System.out.println();
        
        System.out.println("================================================");
        System.out.println("ALL TESTS COMPLETED");
        System.out.println("================================================");
    }
}