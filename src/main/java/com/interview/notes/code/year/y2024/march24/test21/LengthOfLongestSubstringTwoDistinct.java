package com.interview.notes.code.year.y2024.march24.test21;

import java.util.HashMap;


/**
 * Given a strings, return the length of the longest substring that contains at most two distinct characters.
 * Example 1:
 * Input: s = "eceba"
 * Output: 3
 * Explanation: The substring is "ece" which its length is 3.
 * Example 2:
 * Input: s = "ccaabbb"
 * Output: 5
 * Explanation: The substring is "aabbb" which its length is 5.
 */

public class LengthOfLongestSubstringTwoDistinct {

    public static void main(String[] args) {
        LengthOfLongestSubstringTwoDistinct solution = new LengthOfLongestSubstringTwoDistinct();

        System.out.println(solution.lengthOfLongestSubstringTwoDistinct("eceba")); // Output: 3
        System.out.println(solution.lengthOfLongestSubstringTwoDistinct("ccaabbb")); // Output: 5
    }

    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        // HashMap to keep track of the last occurrence of each character
        HashMap<Character, Integer> map = new HashMap<>();
        int left = 0, maxLength = 0;

        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);
            // Update the last occurrence of the current character
            map.put(currentChar, right);

            // When there are more than 2 distinct characters in the current window
            if (map.size() > 2) {
                // Find the leftmost character in the current window and move left pointer just past it
                int leftMost = s.length();
                for (int index : map.values()) {
                    leftMost = Math.min(leftMost, index);
                }
                map.remove(s.charAt(leftMost)); // Remove the leftmost character from the map
                left = leftMost + 1; // Move the left pointer to maintain at most two distinct characters in the window
            }

            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}
