package com.interview.notes.code.datastructure.string;

import java.util.HashSet;
import java.util.Set;

public class FindLongestSubstring {
    public static void main(String[] args) {
        String str = "pwwkew";

        System.out.println(getUniqueCharacterSubstringBruteForce(str));

    }

    static String getUniqueCharacterSubstringBruteForce(String input) {
        String output = "";
        for (int start = 0; start < input.length(); start++) {
            Set<Character> visited = new HashSet<>();
            int end = start;
            for (; end < input.length(); end++) {
                char currChar = input.charAt(end);
                if (visited.contains(currChar)) {
                    break;
                } else {
                    visited.add(currChar);
                }
            }
            if (output.length() < end - start + 1) {
                output = input.substring(start, end);
            }
        }
        return output;
    }
}
