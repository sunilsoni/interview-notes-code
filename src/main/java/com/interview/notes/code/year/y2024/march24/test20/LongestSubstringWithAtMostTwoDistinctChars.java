package com.interview.notes.code.year.y2024.march24.test20;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithAtMostTwoDistinctChars {
    public static void main(String[] args) {
        LongestSubstringWithAtMostTwoDistinctChars solution = new LongestSubstringWithAtMostTwoDistinctChars();

        // Test cases
        String s1 = "eceba";
        String s2 = "ccaabbb";

        System.out.println("Output for '" + s1 + "': " + solution.lengthOfLongestSubstringTwoDistinct(s1)); // Output: 3
        System.out.println("Output for '" + s2 + "': " + solution.lengthOfLongestSubstringTwoDistinct(s2)); // Output: 5
    }

    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        Map<Character, Integer> charMap = new HashMap<>();
        int maxLength = 0;
        int left = 0;
        int right = 0;

        while (right < s.length()) {
            char c = s.charAt(right);
            charMap.put(c, right);

            if (charMap.size() > 2) {
                int leftmostIndex = s.length();
                for (int index : charMap.values()) {
                    leftmostIndex = Math.min(leftmostIndex, index);
                }
                charMap.remove(s.charAt(leftmostIndex));
                left = leftmostIndex + 1;
            }

            maxLength = Math.max(maxLength, right - left + 1);
            right++;
        }

        return maxLength;
    }
}
