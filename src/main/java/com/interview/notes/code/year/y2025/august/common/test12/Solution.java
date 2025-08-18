package com.interview.notes.code.year.y2025.august.common.test12;

import java.util.*;

public class Solution {
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.isEmpty() || t.isEmpty())
            return "";

        // frequency of needed characters
        int[] need = new int[128];
        int required = 0;               // number of distinct chars that must be satisfied
        for (char c : t.toCharArray()) {
            if (need[c] == 0) required++;
            need[c]++;
        }

        // sliding window data
        int[] have = new int[128];
        int formed = 0;                 // how many distinct chars are currently satisfied
        int left = 0;
        int bestLen = Integer.MAX_VALUE;
        int bestStart = 0;

        char[] sArr = s.toCharArray();
        for (int right = 0; right < sArr.length; right++) {
            char c = sArr[right];
            if (need[c] > 0) {
                have[c]++;
                if (have[c] == need[c]) formed++;
            }

            // try to shrink from the left as long as window is valid
            while (formed == required && left <= right) {
                int curLen = right - left + 1;
                if (curLen < bestLen) {
                    bestLen = curLen;
                    bestStart = left;
                }

                char cl = sArr[left];
                if (need[cl] > 0) {
                    if (have[cl] == need[cl]) formed--;
                    have[cl]--;
                }
                left++;
            }
        }

        return (bestLen == Integer.MAX_VALUE) ? "" :
               s.substring(bestStart, bestStart + bestLen);
    }

    // For quick local testing
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.minWindow("ADOBECODEBANC", "ABC")); // BANC
        System.out.println(sol.minWindow("a", "a"));               // a
        System.out.println(sol.minWindow("a", "aa"));              // ""
    }
}
