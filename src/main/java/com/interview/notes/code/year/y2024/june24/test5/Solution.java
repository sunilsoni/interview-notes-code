package com.interview.notes.code.year.y2024.june24.test5;

public class Solution {

    public static String mergePalindromes(String s1, String s2) {
        String palindrome1 = longestPalindrome(s1);
        String palindrome2 = longestPalindrome(s2);

        return combinePalindromes(palindrome1, palindrome2);
    }

    private static String longestPalindrome(String s) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        StringBuilder half = new StringBuilder();
        char middle = 0;
        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 == 1) {
                middle = (char) (i + 'a');
            }
            for (int j = 0; j < freq[i] / 2; j++) {
                half.append((char) (i + 'a'));
            }
        }

        String halfStr = half.toString();
        StringBuilder palindrome = new StringBuilder(halfStr);
        if (middle != 0) {
            palindrome.append(middle);
        }
        palindrome.append(half.reverse().toString());

        return palindrome.toString();
    }

    private static String combinePalindromes(String p1, String p2) {
        int[] freq = new int[26];
        for (char c : p1.toCharArray()) {
            freq[c - 'a']++;
        }
        for (char c : p2.toCharArray()) {
            freq[c - 'a']++;
        }

        StringBuilder half = new StringBuilder();
        char middle = 0;
        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 == 1) {
                middle = (char) (i + 'a');
            }
            for (int j = 0; j < freq[i] / 2; j++) {
                half.append((char) (i + 'a'));
            }
        }

        String halfStr = half.toString();
        StringBuilder result = new StringBuilder(halfStr);
        if (middle != 0) {
            result.append(middle);
        }
        result.append(half.reverse().toString());

        return result.toString();
    }

    public static void main(String[] args) {
        String s1 = "aab";
        String s2 = "cca";
        System.out.println(mergePalindromes(s1, s2));  // Output: acaca
    }
}
