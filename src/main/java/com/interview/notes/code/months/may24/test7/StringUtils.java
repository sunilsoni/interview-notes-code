package com.interview.notes.code.months.may24.test7;

public class StringUtils {

    // Method to reverse a string
    public static String reverseString(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        StringBuilder reversed = new StringBuilder();
        for (int i = input.length() - 1; i >= 0; i--) {
            reversed.append(input.charAt(i));
        }
        return reversed.toString();
    }

    // Method to check if a string is a palindrome
    public static boolean isPalindrome(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }

        int left = 0;
        int right = input.length() - 1;
        while (left < right) {
            if (input.charAt(left) != input.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    // Test the methods
    public static void main(String[] args) {
        String testString = "madam";
        System.out.println("Original string: " + testString);
        System.out.println("Reversed string: " + reverseString(testString));
        System.out.println("Is palindrome? " + isPalindrome(testString));
    }
}
