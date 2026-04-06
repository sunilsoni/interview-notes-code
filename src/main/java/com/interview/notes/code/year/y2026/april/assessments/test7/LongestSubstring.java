package com.interview.notes.code.year.y2026.april.assessments.test7;

import java.util.Arrays;

public class LongestSubstring {

    public static int solve(String s) {
        var max = 0;
        var seen = new int[128];
        Arrays.fill(seen, -1);
        var start = 0;

        for (var end = 0; end < s.length(); end++) {
            var ch = s.charAt(end);
            start = Math.max(start, seen[ch] + 1);
            max = Math.max(max, end - start + 1);
            seen[ch] = end;
        }

        return max;
    }

    public static void main(String[] args) {
        var input = "abcabcbb";
        System.out.println("Longest substring length: " + solve(input));
    }
}
