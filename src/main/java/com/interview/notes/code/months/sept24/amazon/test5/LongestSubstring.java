package com.interview.notes.code.months.sept24.amazon.test5;

public class LongestSubstring {
    public static void main(String[] args) {
        // Sample test cases
        System.out.println(getLongestSubstring("abcd"));      // Output: 4
        System.out.println(getLongestSubstring("fghbbadcba")); // Output: 5
    }

    /*
     * Function to find the length of the longest valid substring
     */
    public static int getLongestSubstring(String s) {
        // Edge case: if the string length is less than 2, no valid substring exists
        if (s.length() < 2) return 0;

        int maxLength = 0;
        int n = s.length();

        // Sliding window approach
        int start = 0; // Start of the current window

        for (int end = 1; end < n; end++) {
            // Check if the first character in the current window is smaller than the last one
            if (s.charAt(start) < s.charAt(end)) {
                maxLength = Math.max(maxLength, end - start + 1);
            } else {
                // Move the start to the current end if the condition is not satisfied
                start = end;
            }
        }

        return maxLength;
    }
}
