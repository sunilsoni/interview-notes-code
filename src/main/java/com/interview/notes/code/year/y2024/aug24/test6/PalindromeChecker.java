package com.interview.notes.code.year.y2024.aug24.test6;

public class PalindromeChecker {

    public static boolean isPalindrome(String str) {
        if (str == null) {
            return false;
        }

        // Remove non-alphanumeric characters and convert to lowercase
        String cleanStr = str.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        if (cleanStr.isEmpty()) {
            return true;  // Empty string is considered a palindrome
        }

        int left = 0;
        int right = cleanStr.length() - 1;

        while (left < right) {
            if (cleanStr.charAt(left) != cleanStr.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }

    public static void main(String[] args) {
        // Test cases
        String[] testStrings = {
                "A man, a plan, a canal: Panama",
                "race a car",
                "",
                " ",
                "Madam, I'm Adam",
                "Was it a car or a cat I saw?",
                "No 'x' in Nixon",
                "12321",
                "Not a palindrome",
                null,
                "a",
                "aa",
                "aba"
        };

        for (String str : testStrings) {
            System.out.println("Is \"" + str + "\" a palindrome? " + isPalindrome(str));
        }
    }
}
