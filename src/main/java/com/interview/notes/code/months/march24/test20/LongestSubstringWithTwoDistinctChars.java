package com.interview.notes.code.months.march24.test20;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithTwoDistinctChars {

    public static int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        int maxLength = 0;
        int start = 0; // Start index of the current substring
        Map<Character, Integer> charCountMap = new HashMap<>(); // Stores the count of each character

        for (int end = 0; end < s.length(); end++) {
            char currChar = s.charAt(end);
            charCountMap.put(currChar, charCountMap.getOrDefault(currChar, 0) + 1);

            // Shrink the window if there are more than two distinct characters
            while (charCountMap.size() > 2) {
                char leftChar = s.charAt(start);
                charCountMap.put(leftChar, charCountMap.get(leftChar) - 1);
                if (charCountMap.get(leftChar) == 0) {
                    charCountMap.remove(leftChar);
                }
                start++;
            }

            // Update the maximum length
            maxLength = Math.max(maxLength, end - start + 1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        String example1 = "eceba";
        String example2 = "ccaabbb";

        System.out.println("Example 1: " + lengthOfLongestSubstringTwoDistinct(example1)); // Output: 3
        System.out.println("Example 2: " + lengthOfLongestSubstringTwoDistinct(example2)); // Output: 5
    }
}
