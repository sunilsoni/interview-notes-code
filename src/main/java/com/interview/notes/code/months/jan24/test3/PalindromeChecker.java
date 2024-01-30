package com.interview.notes.code.months.jan24.test3;

public class PalindromeChecker {

    public static boolean isPalindrome(String str) {
        // Remove spaces and convert to lowercase
        str = str.replaceAll("\\s+", "").toLowerCase();

        // Initialize pointers at the beginning and end
        int left = 0;
        int right = str.length() - 1;

        // Compare characters while moving pointers
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false; // Not a palindrome
            }
            left++;
            right--;
        }

        return true; // Palindrome
    }

    public static void main(String[] args) {
        String input = "A man a plan a canal Panama"; // Example input
        boolean result = isPalindrome(input);
        System.out.println("Is the string a palindrome? " + result);
    }
}
