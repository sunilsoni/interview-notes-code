package com.interview.notes.code.year.y2024.jan24.test13;

public class StringUtils {

    /**
     * Reverses the given string.
     *
     * @param text The string to reverse.
     * @return The reversed string.
     */
    public static String reverseString(String text) {
        StringBuilder sb = new StringBuilder();
        for (int i = text.length() - 1; i >= 0; i--) {
            sb.append(text.charAt(i));
        }
        return sb.toString();
    }

    /**
     * Checks if the given string is a palindrome.
     *
     * @param text The string to check.
     * @return True if the string is a palindrome, False otherwise.
     */
    public static boolean isPalindrome(String text) {
        text = text.toLowerCase(); // Ignore case sensitivity
        int left = 0;
        int right = text.length() - 1;

        while (left < right) {
            if (text.charAt(left) != text.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
