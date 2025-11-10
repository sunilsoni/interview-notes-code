package com.interview.notes.code.year.y2025.november.common.test2;

import java.util.stream.IntStream;

public class PalindromeChecker {

    // Method to check if a number is palindrome using Java 8 Stream API
    public static boolean isPalindrome(String number) {
        // Reverse the string using IntStream and collect to StringBuilder
        String reversed = IntStream.range(0, number.length()) // Stream of indices 0 to length-1
                .mapToObj(i -> number.charAt(number.length() - 1 - i)) // Map each index to reverse character
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append) // Collect chars into StringBuilder
                .toString(); // Convert StringBuilder to String

        return number.equals(reversed); // Return true if original equals reversed
    }

    // Main method to run test cases
    public static void main(String[] args) {
        // Define test cases including edge and large inputs
        String[] testCases = {
                "121",         // Palindrome
                "12321",       // Palindrome
                "123456",      // Not a palindrome
                "1",           // Single digit
                "9876789",     // Palindrome
                "1001",        // Palindrome
                "10",          // Not a palindrome
                "9999999999999999999999999" // Large palindrome input
        };

        // Process each test case and print result
        for (String test : testCases) {
            boolean result = isPalindrome(test);
            String expected = new StringBuilder(test).reverse().toString().equals(test) ? "PASS" : "FAIL";

            System.out.println("Input: " + test + " | IsPalindrome: " + result + " | Test: " +
                    (result ? "PASS" : "FAIL"));
        }

        // Additional large data test
        String largeNumber = "123454321";
        System.out.println("Large Input Test → " + largeNumber + " | Result: " + isPalindrome(largeNumber));
    }
}
