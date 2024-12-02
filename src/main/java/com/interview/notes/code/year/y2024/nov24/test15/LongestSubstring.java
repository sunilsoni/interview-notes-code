package com.interview.notes.code.year.y2024.nov24.test15;

import java.util.HashSet;

public class LongestSubstring {
    public static String findLongestSubstring(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }

        HashSet<Character> set = new HashSet<>();
        int maxLength = 0;
        int start = 0;
        int left = 0;
        int right = 0;

        // Sliding window approach
        while (right < str.length()) {
            char currentChar = str.charAt(right);

            // If character is not in set, add it and move right pointer
            if (!set.contains(currentChar)) {
                set.add(currentChar);
                if (right - left + 1 > maxLength) {
                    start = left;
                    maxLength = right - left + 1;
                }
                right++;
            }
            // If character is in set, remove left character and move left pointer
            else {
                set.remove(str.charAt(left));
                left++;
            }
        }

        return str.substring(start, start + maxLength);
    }

    public static void main(String[] args) {
        String[] testCases = {
                "abcabcbb",   // Expected: "abc"
                "bbbbb",      // Expected: "b"
                "pwwkew",     // Expected: "wke"
                "dvdf",       // Expected: "vdf"
                ""           // Expected: ""
        };

        for (String test : testCases) {
            System.out.println("Input: " + test);
            System.out.println("Longest substring: " + findLongestSubstring(test));
            System.out.println();
        }
    }
}
