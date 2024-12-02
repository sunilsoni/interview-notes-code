package com.interview.notes.code.year.y2023.july23.test8;

public class PalindromeChecker {

    public static boolean isPalindrome(String input) {
        // Remove non-alphanumeric characters and convert to lowercase
        String cleanedInput = input.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        int start = 0;
        int end = cleanedInput.length() - 1;

        while (start < end) {
            if (cleanedInput.charAt(start) != cleanedInput.charAt(end)) {
                return false; // Characters don't match, not a palindrome
            }
            start++;
            end--;
        }

        return true; // All characters matched, it's a palindrome
    }

    public static void main(String[] args) {
        String input = "A man, a plan, a canal, Panama!";
        boolean isPalindrome = isPalindrome(input);
        System.out.println("Is Palindrome: " + isPalindrome);
    }
}
