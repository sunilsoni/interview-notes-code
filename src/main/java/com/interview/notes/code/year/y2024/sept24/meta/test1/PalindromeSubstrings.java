package com.interview.notes.code.year.y2024.sept24.meta.test1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PalindromeSubstrings {

    public static List<String> findPalindromeSubstrings(String s) {
        Set<String> palindromes = new HashSet<>();

        for (int i = 0; i < s.length(); i++) {
            // Check for odd-length palindromes
            expandAroundCenter(s, i, i, palindromes);

            // Check for even-length palindromes
            expandAroundCenter(s, i, i + 1, palindromes);
        }

        return new ArrayList<>(palindromes);
    }

    private static void expandAroundCenter(String s, int left, int right, Set<String> palindromes) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            palindromes.add(s.substring(left, right + 1));
            left--;
            right++;
        }
    }

    public static void main(String[] args) {
        String input = "aabbaab";
        List<String> result = findPalindromeSubstrings(input);
        System.out.println("Palindromic substrings in \"" + input + "\":");
        for (String palindrome : result) {
            System.out.println(palindrome);
        }
    }
}