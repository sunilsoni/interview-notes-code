package com.interview.notes.code.year.y2024.sept24.amazon.test5;

public class Solution {

    public static int getLongestSubstring(String s) {
        int n = s.length();
        int maxLength = 0;

        int start = 0; // Start of the sliding window

        // Loop through the string from left to right
        for (int end = 1; end < n; end++) {
            // If the first character of the window is lexicographically smaller
            // than the current character, it's a valid substring.
            if (s.charAt(start) < s.charAt(end)) {
                // Calculate the current valid substring length
                int length = end - start + 1;
                // Update the max length
                maxLength = Math.max(maxLength, length);
            } else {
                // If it's not valid, move the start pointer to the current position
                start = end;  // Reset the window
            }
        }

        // If no valid substring was found, return 0
        return maxLength > 1 ? maxLength : 0;
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(getLongestSubstring("abcd")); // Output: 4
        System.out.println(getLongestSubstring("fghbbadcba")); // Output: 5
        System.out.println(getLongestSubstring("aabbccdd"));  // Output: 0
        System.out.println(getLongestSubstring("zabcdzy"));  // Output: 5
    }
}
