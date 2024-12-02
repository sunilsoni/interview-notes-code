package com.interview.notes.code.year.y2024.may24.test7;

public class StringUtil1 {

    // Method to reverse a string
    public static String reverse(String input) {
        if (input == null) {
            return null;
        }
        return new StringBuilder(input).reverse().toString();
    }

    // Method to check if a string is a palindrome
    public static boolean isPalindrome(String input) {
        if (input == null) {
            return false;
        }
        String reversed = reverse(input);
        return input.equals(reversed);
    }

    // Main method for testing
    public static void main(String[] args) {
        // Test the reverse method
        String testString = "hello";
        System.out.println("Original: " + testString);
        System.out.println("Reversed: " + StringUtil1.reverse(testString));

        // Test the isPalindrome method
        String palindromeTest1 = "racecar";
        String palindromeTest2 = "hello";
        System.out.println("Is 'racecar' a palindrome? " + StringUtil1.isPalindrome(palindromeTest1));
        System.out.println("Is 'hello' a palindrome? " + StringUtil1.isPalindrome(palindromeTest2));
    }
}

