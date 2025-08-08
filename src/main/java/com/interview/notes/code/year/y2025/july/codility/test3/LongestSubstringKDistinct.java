package com.interview.notes.code.year.y2025.july.codility.test3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LongestSubstringKDistinct {

    public static int lengthOfLongestSubstringKDistinct(String s, int k) {
        int left = 0, maxLen = 0;
        Map<Character, Integer> freq = new HashMap<>();
        for (int right = 0; right < s.length(); right++) {
            // add s[right]
            freq.merge(s.charAt(right), 1, Integer::sum);
            // shrink until ≤ k distinct
            while (freq.size() > k) {
                char c = s.charAt(left++);
                freq.compute(c, (ch, cnt) -> (cnt == 1) ? null : cnt - 1);
            }
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }

    public static void main(String[] args) {
        List<Object[]> tests = Arrays.asList(
                new Object[]{"eceba", 2, 3},
                new Object[]{"aa", 1, 2},
                new Object[]{"a", 0, 0},
                new Object[]{"", 5, 0},
                new Object[]{"abc", 2, 2}
        );

        tests.forEach(t -> {
            String s = (String) t[0];
            int k = (int) t[1];
            int exp = (int) t[2];
            int got = lengthOfLongestSubstringKDistinct(s, k);
            System.out.printf(
                    "%s  s=\"%s\", k=%d → %d%n",
                    (got == exp ? "PASS" : "FAIL"), s, k, got
            );
        });
    }
}