package com.interview.notes.code.months.june24.test8;

import java.util.HashMap;

public class LongestSubstring {
    public static String longestSubstringWithoutRepeating(String s) {
        int n = s.length();
        int maxLength = 0;
        int start = 0;
        HashMap<Character, Integer> map = new HashMap<>();

        int i = 0;

        for (int j = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }

            if (j - i + 1 > maxLength) {
                maxLength = j - i + 1;
                start = i;
            }

            map.put(s.charAt(j), j + 1);
        }

        return s.substring(start, start + maxLength);
    }

    public static void main(String[] args) {
        String input = "abcabcbb";
        String result = longestSubstringWithoutRepeating(input);
        System.out.println("Longest substring without repeating characters: " + result);
    }
}
