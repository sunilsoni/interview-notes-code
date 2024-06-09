package com.interview.notes.code.months.june24.test2;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutRepeatingCharacters {

    public static int lengthOfLongestSubstring(String s) {
        // HashMap to store the last positions of each character
        Map<Character, Integer> charMap = new HashMap<>();
        int maxLength = 0;
        int start = 0;

        // Loop through the string
        for (int end = 0; end < s.length(); end++) {
            char currentChar = s.charAt(end);

            // If the character is already in the map and its index is greater than or equal to start index
            if (charMap.containsKey(currentChar) && charMap.get(currentChar) >= start) {
                // Move the start index to the right of the last occurrence of the current character
                start = charMap.get(currentChar) + 1;
            }

            // Update the last seen index of the current character
            charMap.put(currentChar, end);

            // Calculate the length of the current substring and update maxLength if necessary
            maxLength = Math.max(maxLength, end - start + 1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        // Example input
        String input = "abcabccccc";
        // Find and print the length of the longest substring without repeating characters
        System.out.println("Length of the longest substring without repeating characters: " + lengthOfLongestSubstring(input));
    }
}
