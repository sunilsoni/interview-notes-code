package com.interview.notes.code.year.y2024.feb24.test6;

public class PalindromeCheck {
    // Method to check if a string is a palindrome
    private static boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    // Method to find which character to remove to make the string a palindrome
    public static String makePalindrome(String s) {
        int left = 0, right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                // If removing the left or right character makes it a palindrome
                if (isPalindrome(s, left + 1, right) || isPalindrome(s, left, right - 1)) {
                    return "Remove character at position: " + (isPalindrome(s, left + 1, right) ? left : right);
                } else {
                    return "More than one character needs to be removed.";
                }
            }
            left++;
            right--;
        }
        return "Already a palindrome.";
    }

    public static void main(String[] args) {
        String input = "racebcar";
        System.out.println(makePalindrome(input));
    }
}
