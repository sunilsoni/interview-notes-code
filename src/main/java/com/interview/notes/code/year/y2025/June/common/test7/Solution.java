package com.interview.notes.code.year.y2025.June.common.test7;

import java.util.HashMap;

public class Solution {
    public static String longestNonRepeatingSubstring(String s) {
        if (s == null || s.length() == 0) return "";

        // Variables to track the current window
        int start = 0;
        int maxLength = 0;
        int maxStart = 0;

        // Use HashMap to store character and its last position
        HashMap<Character, Integer> map = new HashMap<>();

        for (int end = 0; end < s.length(); end++) {
            char currentChar = s.charAt(end);

            // If we have seen this character before and it's in our current window
            if (map.containsKey(currentChar) && map.get(currentChar) >= start) {
                // Move start to position after the last occurrence
                start = map.get(currentChar) + 1;
            }

            // Update the last position of current character
            map.put(currentChar, end);

            // Update maxLength if current window is larger
            if (end - start + 1 > maxLength) {
                maxLength = end - start + 1;
                maxStart = start;
            }
        }

        // Return the longest substring
        return s.substring(maxStart, maxStart + maxLength);
    }

    public static void main(String[] args) {
        String test = "akspa";
        System.out.println("Input: " + test);
        System.out.println("Longest non-repeating substring: " +
                longestNonRepeatingSubstring(test));

        // More test cases
        System.out.println("Input: abcabcbb");
        System.out.println("Longest non-repeating substring: " +
                longestNonRepeatingSubstring("abcabcbb"));
    }
}
