package com.interview.notes.code.year.y2025.september.common.test;

public class PalindromeSubstring {
    public static boolean isPalindrome(String str) {
        int left = 0;
        int right = str.length() - 1;

        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void findAllPalindromeSubstrings(String str) {
        for (int i = 0; i < str.length(); i++) {
            for (int j = i + 1; j <= str.length(); j++) {
                String substring = str.substring(i, j);
                if (isPalindrome(substring)) {
                    System.out.println(substring);
                }
            }
        }
    }

    public static void main(String[] args) {
        String str = "ababa";
        findAllPalindromeSubstrings(str);
    }
}
