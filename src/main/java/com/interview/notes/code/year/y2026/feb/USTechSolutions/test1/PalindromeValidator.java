package com.interview.notes.code.year.y2026.feb.USTechSolutions.test1;

public class PalindromeValidator {

    /**
     * Checks if a string is a palindrome, ignoring case and non-alphanumeric characters.
     * * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * @param input The string to check
     * @return true if palindrome, false otherwise
     */
    public boolean isPalindrome(String input) {
        // Edge case: Null is not a palindrome (or could throw IllegalArgumentException based on reqs)
        if (input == null) {
            return false;
        }

        // Edge case: Empty or single character is always a palindrome
        if (input.length() <= 1) {
            return true;
        }

        int left = 0;
        int right = input.length() - 1;

        while (left < right) {
            char leftChar = input.charAt(left);
            char rightChar = input.charAt(right);

            // 1. Move left pointer forward if not alphanumeric
            if (!Character.isLetterOrDigit(leftChar)) {
                left++;
                continue;
            }

            // 2. Move right pointer backward if not alphanumeric
            if (!Character.isLetterOrDigit(rightChar)) {
                right--;
                continue;
            }

            // 3. Compare characters (case-insensitive)
            if (Character.toLowerCase(leftChar) != Character.toLowerCase(rightChar)) {
                return false; // Mismatch found
            }

            // 4. Move both pointers inward
            left++;
            right--;
        }

        return true;
    }
}