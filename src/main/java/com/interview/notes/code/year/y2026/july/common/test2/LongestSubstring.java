package com.interview.notes.code.year.y2026.july.common.test2;

import java.util.HashMap;

public class LongestSubstring {
    public static int lengthOfLongestSubstring(String s) {
        // Map tracks each character and the exact index where we last saw it.
        var seen = new HashMap<Character, Integer>();
        // Keeps track of the longest valid window length we have found.
        int maxLen = 0;
        // For-loop expands the window using 'right', while 'left' marks the start boundary.
        for (int right = 0, left = 0; right < s.length(); right++) {
            // Extract the current character at the right edge of our window.
            var ch = s.charAt(right);
            // If we've seen this character before, we have a duplicate in our window.
            if (seen.containsKey(ch)) {
                // Move the left pointer just past the old duplicate so the window is valid again.
                left = Math.max(left, seen.get(ch) + 1);
            }
            // Calculate current window size and update the maximum length if it is larger.
            maxLen = Math.max(maxLen, right - left + 1);
            // Update the map with the most recent index of the current character.
            seen.put(ch, right);
        }
        // Return the highest length recorded.
        return maxLen;
    }
    public static void main(String[] args) {
        int result = lengthOfLongestSubstring("abcabcbb");
        System.out.println(result);
    }
}