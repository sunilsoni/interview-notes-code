package com.interview.notes.code.months.year2023.dec23.test4;

import java.util.HashMap;

//https://leetcode.com/problems/longest-substring-without-repeating-characters/
public class LongestSubstringWithoutRepeatingCharacters {
    public static int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int maxLength = 0;
        int start = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            // If the character is already seen and is in the current window
            if (map.containsKey(ch) && map.get(ch) >= start) {
                start = map.get(ch) + 1; // move the start right after this character's last occurrence
            }

            map.put(ch, i); // Update or add the character's position
            maxLength = Math.max(maxLength, i - start + 1); // Update max length
        }

        return maxLength;
    }

    public static void main(String[] args) {
        String s1 = "abcabcbb";
        System.out.println("Length of longest substring: " + lengthOfLongestSubstring(s1));
        // Other test cases...
    }
}
