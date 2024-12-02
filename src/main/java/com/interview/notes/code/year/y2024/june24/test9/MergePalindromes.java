package com.interview.notes.code.year.y2024.june24.test9;

public class MergePalindromes {
    // Helper function to create the longest palindrome from a given string
    private static String longestPalindrome(String s) {
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        StringBuilder left = new StringBuilder();
        String middle = "";
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < count[i] / 2; j++) {
                left.append((char) (i + 'a'));
            }
            if (count[i] % 2 == 1 && middle.equals("")) {
                middle = String.valueOf((char) (i + 'a'));
            }
        }

        String reverseLeft = new StringBuilder(left).reverse().toString();
        return left.toString() + middle + reverseLeft;
    }

    public static String mergePalindromes(String s1, String s2) {
        String p1 = longestPalindrome(s1);
        String p2 = longestPalindrome(s2);

        // Count characters in both palindromes
        int[] count = new int[26];
        for (char c : p1.toCharArray()) {
            count[c - 'a']++;
        }
        for (char c : p2.toCharArray()) {
            count[c - 'a']++;
        }

        StringBuilder left = new StringBuilder();
        String middle = "";
        for (int i = 0; i < 26; i++) {
            int pairs = count[i] / 2;
            for (int j = 0; j < pairs; j++) {
                left.append((char) (i + 'a'));
            }
            if (count[i] % 2 == 1 && middle.equals("")) {
                middle = String.valueOf((char) (i + 'a'));
            }
        }

        String reverseLeft = new StringBuilder(left).reverse().toString();
        return left.toString() + middle + reverseLeft;
    }

    public static void main(String[] args) {
        String s1 = "aab";
        String s2 = "cca";
        System.out.println(mergePalindromes(s1, s2));  // Output should be acaca
    }
}
