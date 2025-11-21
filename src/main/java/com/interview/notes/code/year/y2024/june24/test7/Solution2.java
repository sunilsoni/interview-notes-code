package com.interview.notes.code.year.y2024.june24.test7;

public class Solution2 {

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
                if (middle == 0 || (char) (i + 'a') < middle) {
                    middle = (char) (i + 'a');
                }
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
        palindrome.append(half.reverse());

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
                if (middle == 0 || (char) (i + 'a') < middle) {
                    middle = (char) (i + 'a');
                }
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
        result.append(half.reverse());

        return result.toString();
    }

    public static void main(String[] args) {
        // Example 1
        String s1 = "aab";
        String s2 = "cca";
        System.out.println("Example 1:");
        System.out.println("Input: s1 = " + s1 + ", s2 = " + s2);
        System.out.println("Output: " + mergePalindromes(s1, s2));
        System.out.println();

        // Example 2
        s1 = "aabbc";
        s2 = "ddefefa";
        System.out.println("Example 2:");
        System.out.println("Input: s1 = " + s1 + ", s2 = " + s2);
        System.out.println("Output: " + mergePalindromes(s1, s2));
        System.out.println();

        // Example 3
        s1 = "racecar";
        s2 = "level";
        System.out.println("Example 3:");
        System.out.println("Input: s1 = " + s1 + ", s2 = " + s2);
        System.out.println("Output: " + mergePalindromes(s1, s2));
        System.out.println();
    }
}
