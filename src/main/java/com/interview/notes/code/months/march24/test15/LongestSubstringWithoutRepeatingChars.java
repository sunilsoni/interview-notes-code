package com.interview.notes.code.months.march24.test15;

import java.util.HashSet;

/**
 * Given a strings, find the length of the longest substring without repeating characters.
 * Input: s = "abcabcbb"
 * Output: 3
 * assert longestSubstringWithout('abcabcbb') == 3
 * assert longestSubstringWithout('bbbbb') == 1
 * assert longestSubstringWithout('pwwkew') == 3
 */
public class LongestSubstringWithoutRepeatingChars {

    public static int longestSubstringWithout(String s) {
        int n = s.length();
        HashSet<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;

        while (i < n && j < n) {
            // Try to extend the range [i, j]
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            } else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert longestSubstringWithout("abcabcbb") == 3;
        assert longestSubstringWithout("bbbbb") == 1;
        assert longestSubstringWithout("pwwkew") == 3;

        System.out.println(longestSubstringWithout("abcabcbb"));

        System.out.println("All tests passed.");
    }
}
