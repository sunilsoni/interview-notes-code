package com.interview.notes.code.year.y2025.June.common.test5;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static String longestNonRepeatingSubstring(String s) {
        if (s == null || s.length() == 0) return "";
        
        Set<Character> set = new HashSet<>();
        int start = 0;
        int maxLength = 0;
        int maxStart = 0;
        
        for (int end = 0; end < s.length(); end++) {
            char currentChar = s.charAt(end);
            
            // While we have a repeating character, remove from start
            while (set.contains(currentChar)) {
                set.remove(s.charAt(start));
                start++;
            }
            
            set.add(currentChar);
            
            // Update maxLength if current window is larger
            if (end - start + 1 > maxLength) {
                maxLength = end - start + 1;
                maxStart = start;
            }
        }
        
        return s.substring(maxStart, maxStart + maxLength);
    }

    public static void main(String[] args) {
        String test = "akspa";
        System.out.println("Input: " + test);
        System.out.println("Longest non-repeating substring: " + 
                          longestNonRepeatingSubstring(test));
    }
}
