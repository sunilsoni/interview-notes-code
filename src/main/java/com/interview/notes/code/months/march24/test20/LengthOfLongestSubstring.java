package com.interview.notes.code.months.march24.test20;

import java.util.HashMap;

/**
 * Given a strings, find the length of the longest substring without repeating characters.
 * Example 1:
 * Input: s = "abcabebb"
 * Output: 3
 * Example 2:
 * Explanation: The answer is "abc", with the length of 3.
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * Example 3:
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Constraints:
 * 0 <= s. length <= 5 * 104
 * s consists of English letters, digits, symbols and spaces.
 */
public class LengthOfLongestSubstring {

    public static void main(String[] args) {
        LengthOfLongestSubstring solution = new LengthOfLongestSubstring();

        System.out.println(solution.lengthOfLongestSubstring("abcabcbb")); // Output: 3
        System.out.println(solution.lengthOfLongestSubstring("bbbbb"));    // Output: 1
        System.out.println(solution.lengthOfLongestSubstring("pwwkew"));   // Output: 3
        // Example with symbols and spaces
        System.out.println(solution.lengthOfLongestSubstring("a bc@abc#d "));   // Output should reflect longest unique substring length considering all characters
    }

    public int lengthOfLongestSubstring(String s) {
        // Base condition check
        if (s == null || s.length() == 0) {
            return 0;
        }

        // This will store the maximum length of the substring without repeating characters
        int maxLength = 0;
        // This map keeps track of the characters and their last seen positions in the string
        HashMap<Character, Integer> map = new HashMap<>();

        // Initialize left pointer of the window
        int left = 0;

        // Iterate over the string with the right pointer
        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);

            // If the character is already seen and its last seen position is on or after the left pointer
            // Move the left pointer right next to the last seen position of the current character
            if (map.containsKey(currentChar) && map.get(currentChar) >= left) {
                left = map.get(currentChar) + 1;
            }

            // Update the last seen position of the current character
            map.put(currentChar, right);

            // Calculate the maximum length as the maximum of the current max length and the size of the current window
            maxLength = Math.max(maxLength, right - left + 1);
        }

        // Return the maximum length found
        return maxLength;
    }
}
