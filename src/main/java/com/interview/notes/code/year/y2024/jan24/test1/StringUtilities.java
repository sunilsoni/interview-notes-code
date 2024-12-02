package com.interview.notes.code.year.y2024.jan24.test1;

public class StringUtilities {

    // Method to reverse a string
    public static String reverseString(String s) {
        StringBuilder reversed = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            reversed.append(s.charAt(i));
        }
        return reversed.toString();
    }

    // Method to check if a string is a palindrome
    public static boolean isPalindrome(String s) {
        String reversed = reverseString(s);
        return s.equals(reversed);
    }

    // Main method for example execution
    public static void main(String[] args) {
        String testString = "radar";
        System.out.println("Is '" + testString + "' a palindrome? " + isPalindrome(testString));
    }
}

