package com.interview.notes.code.year.y2025.June.common.test7;

import java.util.*;

public class LongestSubstringUnique {

    // Method to calculate longest substring without repeating characters
    public static int lengthOfLongestSubstring(String s) {
        // Set to track characters in current window
        Set<Character> window = new HashSet<>();
        int left = 0; // left pointer of sliding window
        int maxLen = 0; // track maximum length found so far

        // Iterate over characters in the string using right pointer
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            // If character is already in window, slide the left pointer to remove duplicates
            while (window.contains(c)) {
                // remove character at left pointer and move it forward
                window.remove(s.charAt(left));
                left++;
            }

            // Add current character to window
            window.add(c);

            // Calculate current window length and update maxLen
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }

    // Main method to test with sample and edge cases
    public static void main(String[] args) {
        Map<String, Integer> testCases = new LinkedHashMap<>();
        testCases.put("abcabcbb", 3);
        testCases.put("bbbbb", 1);
        testCases.put("pwwkew", 3);
        testCases.put("", 0);
        testCases.put("a", 1);
        testCases.put("abcdefg", 7);
        testCases.put("abba", 2);
        testCases.put("dvdf", 3);
        testCases.put("anviaj", 5);
        testCases.put("aab", 2);

        boolean allPassed = true;

        for (Map.Entry<String, Integer> test : testCases.entrySet()) {
            int result = lengthOfLongestSubstring(test.getKey());
            boolean pass = result == test.getValue();
            System.out.println("Input: " + test.getKey() + " | Expected: " + test.getValue() + " | Got: " + result + " | " + (pass ? "PASS" : "FAIL"));
            if (!pass) allPassed = false;
        }

        // Large input test
        String large = String.join("", Collections.nCopies(100000, "a")) + "b"; // 100000 'a's followed by 'b'
        int result = lengthOfLongestSubstring(large);
        System.out.println("Large Input Test | Expected: 2 | Got: " + result + " | " + (result == 2 ? "PASS" : "FAIL"));

        System.out.println("\nOverall Result: " + (allPassed ? "✅ All test cases passed." : "❌ Some test cases failed."));
    }
}