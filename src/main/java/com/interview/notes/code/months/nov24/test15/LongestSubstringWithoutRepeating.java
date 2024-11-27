package com.interview.notes.code.months.nov24.test15;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutRepeating {

    /**
     * Finds the length of the longest substring without repeating characters using a HashMap.
     *
     * @param s The input string.
     * @return The length of the longest substring without repeating characters.
     */
    public static int lengthOfLongestSubstringOptimized(String s) {
        // HashMap to store the latest index of each character
        Map<Character, Integer> charIndexMap = new HashMap<>();
        int maxLength = 0; // To keep track of the maximum length found
        int left = 0; // Left pointer of the sliding window

        // Iterate over the string with the right pointer
        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);

            // If currentChar is in the map and its index is >= left, move the left pointer
            if (charIndexMap.containsKey(currentChar) && charIndexMap.get(currentChar) >= left) {
                left = charIndexMap.get(currentChar) + 1;
            }

            // Update or add the current character's index
            charIndexMap.put(currentChar, right);

            // Update maxLength if needed
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    // Example usage
    public static void main(String[] args) {
        String[] testStrings = {"abcabcbb", "bbbbb", "pwwkew", "", "au"};
        for (String test : testStrings) {
            int result = lengthOfLongestSubstringOptimized(test);
            System.out.println("Input: \"" + test + "\" | Longest Substring Length: " + result);
        }
    }
}
