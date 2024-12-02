package com.interview.notes.code.year.y2024.july24.test15;

import java.util.HashSet;
import java.util.Set;

public class LongestUniqueSubstring {
    public static String findLongestUniqueSubstring(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }

        int start = 0;
        int maxLength = 0;
        int maxStart = 0;
        Set<Character> charSet = new HashSet<>();

        for (int end = 0; end < input.length(); end++) {
            char currentChar = input.charAt(end);
            while (charSet.contains(currentChar)) {
                charSet.remove(input.charAt(start));
                start++;
            }
            charSet.add(currentChar);

            if (end - start + 1 > maxLength) {
                maxLength = end - start + 1;
                maxStart = start;
            }
        }

        return input.substring(maxStart, maxStart + maxLength);
    }

    public static void main(String[] args) {
        String testString = "abcabcbb";
        System.out.println("Longest unique substring: " + findLongestUniqueSubstring(testString));
    }
}
