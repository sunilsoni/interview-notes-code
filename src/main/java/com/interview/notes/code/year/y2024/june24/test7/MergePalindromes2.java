package com.interview.notes.code.year.y2024.june24.test7;

import java.util.ArrayList;
import java.util.List;

public class MergePalindromes2 {


    public static String mergePalindromes(String s1, String s2) {
        String longest1 = findLongestPalindrome(s1);
        String longest2 = findLongestPalindrome(s2);

        // Now merge to form the largest possible palindrome alphabetically
        return mergeToFormPalindrome(longest1, longest2);
    }

    private static String findLongestPalindrome(String s) {
        String longest = "";
        // Check all possible substrings
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j <= s.length(); j++) {
                String sub = s.substring(i, j);
                if (isPalindrome(sub) && sub.length() > longest.length()) {
                    longest = sub;
                }
            }
        }
        return longest;
    }

    private static boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;
    }

    private static String mergeToFormPalindrome(String p1, String p2) {
        // Simply concatenate and check for the smallest alphabetical order
        List<String> candidates = new ArrayList<>();
        candidates.add(p1 + p2);
        candidates.add(p2 + p1);

        return candidates.stream().min(String::compareTo).orElse("");
    }

    public static void main(String[] args) {
        System.out.println(mergePalindromes("aab", "cca"));
    }
}
