package com.interview.notes.code.year.y2025.april.common.test1;

import java.util.Arrays;

public class SubstringWithoutRepeatingChars {
    public static int findLongestSubstring(String s) {
        if (s == null || s.isEmpty()) return 0;
        
        int[] lastSeen = new int[128]; // Tracks last position of each character
        Arrays.fill(lastSeen, -1);
        
        int maxLength = 0;
        int start = 0;
        
        for (int end = 0; end < s.length(); end++) {
            char currentChar = s.charAt(end);
            
            // If character was seen before after current start
            if (lastSeen[currentChar] >= start) {
                start = lastSeen[currentChar] + 1;
            }
            
            lastSeen[currentChar] = end;
            maxLength = Math.max(maxLength, end - start + 1);
        }
        
        return maxLength;
    }

    public static void main(String[] args) {
        // Test cases
        testCase("abcabcbb", 3);
        testCase("bbbbb", 1);
        testCase("pwwkew", 3);
        testCase("", 0);
        testCase("a", 1);
        testCase("aab", 2);
        testCase("dvdf", 3);
        
        // Large input test
        StringBuilder large = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            large.append((char)('a' + (i % 26)));
        }
        testCase(large.toString(), 26);
    }
    
    private static void testCase(String input, int expected) {
        int result = findLongestSubstring(input);
        boolean passed = result == expected;
        System.out.printf("Input: %s\nExpected: %d, Got: %d, Test: %s\n\n",
            input.length() > 50 ? input.substring(0, 50) + "..." : input,
            expected,
            result,
            passed ? "PASS" : "FAIL");
    }
}
