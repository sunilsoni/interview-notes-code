package com.interview.notes.code.months.march24.test6;

public class PalindromeInterpolation {
    public static String getSmallestPalindrome(String s) {
        char[] chars = s.toCharArray();
        int left = 0;
        int right = s.length() - 1;

        // Replace '?' with the mirrored character or 'a' if both are '?'
        while (left <= right) {
            if (chars[left] == '?' && chars[right] == '?') {
                chars[left] = chars[right] = 'a';
            } else if (chars[left] == '?') {
                chars[left] = chars[right];
            } else if (chars[right] == '?') {
                chars[right] = chars[left];
            } else if (chars[left] != chars[right]) {
                return "-1"; // Cannot form a palindrome
            }
            left++;
            right--;
        }

        // Check if the resulting string is a palindrome
        if (new String(chars).equals(new StringBuilder(new String(chars)).reverse().toString())) {
            return new String(chars);
        } else {
            return "-1";
        }
    }

    public static void main(String[] args) {
        String input = "axxb??";
        System.out.println(getSmallestPalindrome(input));


        String input1 = "a?rt?????";
        System.out.println(getSmallestPalindrome(input1));
    }
}
