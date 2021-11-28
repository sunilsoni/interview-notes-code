package com.interview.notes.code.LeetCode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ValidAnagram {
    public boolean isAnagramSorting(String s, String t) {
        if ((s == null) || (t == null) || (t.length() != s.length())) {
            return false;
        }
        char[] sArr1 = s.toCharArray();
        char[] sArr2 = t.toCharArray();
        Arrays.sort(sArr1);
        Arrays.sort(sArr2);
        return Arrays.equals(sArr1, sArr2);
    }

    public boolean isAnagramHash(String s, String t) {
        if ((s == null) || (t == null) || (t.length() != s.length())) {
            return false;
        }

        int n = s.length();

        Map<Character, Integer> counts = new HashMap<>();

        for (int i = 0; i < n; ++i) {
            counts.put(s.charAt(i), counts.getOrDefault(s.charAt(i), 0) + 1);
        }

        for (int i = 0; i < n; ++i) {
            counts.put(t.charAt(i), counts.getOrDefault(t.charAt(i), 0) - 1);
            if (counts.getOrDefault(t.charAt(i), -1) < 0) {
                return false;
            }
        }

        return true;
    }

}
