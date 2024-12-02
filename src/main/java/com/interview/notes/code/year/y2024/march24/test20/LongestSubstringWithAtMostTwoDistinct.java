package com.interview.notes.code.year.y2024.march24.test20;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithAtMostTwoDistinct {

    public static int lengthOfLongestSubstringTwoDistinct(String s) {
        int maxLength = 0;
        int left = 0;
        int right = 0;
        // Use a map to store character frequencies in the current window
        Map<Character, Integer> charFreq = new HashMap<>();

        while (right < s.length()) {
            char currentChar = s.charAt(right);

            // Update the character frequency in the map
            charFreq.put(currentChar, charFreq.getOrDefault(currentChar, 0) + 1);

            // If the window contains more than two distinct characters, shrink it from the left side
            while (charFreq.size() > 2) {
                char leftChar = s.charAt(left);
                charFreq.put(leftChar, charFreq.get(leftChar) - 1);
                if (charFreq.get(leftChar) == 0) {
                    charFreq.remove(leftChar);
                }
                left++;
            }

            // Update the maximum length if the current window size is larger
            maxLength = Math.max(maxLength, right - left + 1);

            right++;
        }

        return maxLength;
    }

    public static void main(String[] args) {
        String str1 = "eceba";
        int length1 = lengthOfLongestSubstringTwoDistinct(str1);
        System.out.println("Length of longest substring with at most two distinct characters in \"" + str1 + "\": " + length1); // Output: 3

        String str2 = "ccaabbb";
        int length2 = lengthOfLongestSubstringTwoDistinct(str2);
        System.out.println("Length of longest substring with at most two distinct characters in \"" + str2 + "\": " + length2); // Output: 5
    }
}
