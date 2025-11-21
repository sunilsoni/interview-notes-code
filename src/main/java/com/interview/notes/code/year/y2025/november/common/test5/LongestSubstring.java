package com.interview.notes.code.year.y2025.november.common.test5;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstring {

    public static int lengthOfLongestSubstring(String s) {
        int left = 0, right = 0, maxLength = 0;
        Set<Character> seen = new HashSet<>();

        while (right < s.length()) {
            char c = s.charAt(right);
            if (!seen.contains(c)) {
                seen.add(c);
                maxLength = Math.max(maxLength, right - left + 1);
                right++;
            } else {
                seen.remove(s.charAt(left));
                left++;
            }
        }
        return maxLength;
    }

    // Quick test
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb")); // Output: 3 ("abc")
        System.out.println(lengthOfLongestSubstring("bbbbb"));    // Output: 1 ("b")
        System.out.println(lengthOfLongestSubstring("pwwkew"));   // Output: 3 ("wke")
    }
}
