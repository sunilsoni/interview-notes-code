package com.interview.notes.code.year.y2025.december.common.test4;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LongestSubstringTest {

    // Method to find longest substring length without repeating characters
    public static int longestSubstring(String s) {
        // Map to store last seen index of each character
        Map<Character, Integer> seen = new HashMap<>();
        
        // Variable to track start of current window
        int start = 0;
        
        // Variable to track maximum length found
        int maxLength = 0;
        
        // Loop through each character in the string
        for (int end = 0; end < s.length(); end++) {
            char c = s.charAt(end); // Current character
            
            // If character already seen and inside current window
            if (seen.containsKey(c) && seen.get(c) >= start) {
                // Move start to one position after last seen index
                start = seen.get(c) + 1;
            }
            
            // Update last seen index of current character
            seen.put(c, end);
            
            // Update maxLength with current window size
            maxLength = Math.max(maxLength, end - start + 1);
        }
        
        // Return the maximum length found
        return maxLength;
    }

    // Main method for testing
    public static void main(String[] args) {
        // List of test cases with expected results
        List<String> testInputs = Arrays.asList(
            "",             // Empty string
            "aaaaa",        // All same characters
            "abcabcbb",     // Repeating pattern
            "pwwkew",       // Mixed repeats
            "abcdef",       // All unique
            "abba"          // Palindrome with repeats
        );
        
        List<Integer> expectedOutputs = Arrays.asList(
            0,  // Expected for ""
            1,  // Expected for "aaaaa"
            3,  // Expected for "abcabcbb"
            3,  // Expected for "pwwkew"
            6,  // Expected for "abcdef"
            2   // Expected for "abba"
        );
        
        // Run each test case
        for (int i = 0; i < testInputs.size(); i++) {
            String input = testInputs.get(i);
            int expected = expectedOutputs.get(i);
            int result = longestSubstring(input);
            
            // Print PASS/FAIL for each case
            if (result == expected) {
                System.out.println("Test " + (i+1) + " PASS: Input='" + input + "' Result=" + result);
            } else {
                System.out.println("Test " + (i+1) + " FAIL: Input='" + input + "' Expected=" + expected + " Got=" + result);
            }
        }
        
        // Large data test case using Stream API
        String largeInput = IntStream.range(0, 100000) // Generate 100k numbers
                                     .mapToObj(i -> String.valueOf((char)('a' + (i % 26)))) // Cycle through a-z
                                     .collect(Collectors.joining());
        
        int largeResult = longestSubstring(largeInput);
        System.out.println("Large Input Test Result: " + largeResult);
    }
}
