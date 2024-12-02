package com.interview.notes.code.year.y2023.Sep23.test2;

public class PalindromeCheck {

    public static void main(String[] args) {
        String input = "radar";
        boolean isPalindrome = isPalindrome(input);
        System.out.println("Is palindrome? " + isPalindrome);
    }

    public static boolean isPalindrome(String input) {
        int start = 0;
        int end = input.length() - 1;

        while (start < end) {
            if (input.charAt(start) != input.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }

        return true;
    }
}
