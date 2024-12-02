package com.interview.notes.code.year.y2023.nov23.test3;

import java.util.HashMap;

public class SubstringFinder {

    public static String findLongestSubstringWithoutRepeatingChars(String inputString) {
        if (inputString == null) return ""; // Handle null input

        HashMap<Character, Integer> charIndexMap = new HashMap<>();
        String maxSubstring = "";
        int start = 0; // Starting index of the current substring

        for (int end = 0; end < inputString.length(); end++) {
            char currentChar = inputString.charAt(end);

            // If the character is found in the map and its last position is on or after 'start',
            // update 'start' to the position after that character to avoid repeats.
            if (charIndexMap.containsKey(currentChar)) {
                start = Math.max(start, charIndexMap.get(currentChar) + 1);
            }

            // If the current substring is longer than the previously found longest,
            // update 'maxSubstring' to the current substring.
            if (end - start + 1 > maxSubstring.length()) {
                maxSubstring = inputString.substring(start, end + 1);
            }

            // Update the last seen position of 'currentChar'.
            charIndexMap.put(currentChar, end);
        }

        return maxSubstring;
    }

    public static void main(String[] args) {
        String input = "asdtffgffssd";
        String longestSubstring = findLongestSubstringWithoutRepeatingChars(input);
        System.out.println("The longest substring without repeating characters is: " + longestSubstring);
    }
}
