package com.interview.notes.code.year.y2026.june.cognizant.test4;

public class PalindromeChecker {
    public static void main(String[] args) {
        // Define test cases: words, empty strings, and large data strings
        String[] inputs = {"racecar", "hello", "aba", "a", "", "abcdeedcba"};
        
        // Loop through each test case using Stream API for clean iteration
        java.util.stream.Stream.of(inputs).forEach(str -> {
            // Logic: Compare string with its reverse by splitting into chars and reversing
            String reversed = new StringBuilder(str).reverse().toString(); // Create reverse of string
            boolean isPalindrome = str.equals(reversed); // Compare original with reversed for equality
            
            // Output result for each test case
            System.out.println("Input: \"" + str + "\" | Is Palindrome: " + isPalindrome);
        });

        // Large Data Test Case
        String largeData = "a".repeat(1000000) + "b" + "a".repeat(1000000); // Generate 2 million length string
        boolean largeResult = largeData.contentEquals(new StringBuilder(largeData).reverse()); // Perform check
        System.out.println("Large Data Test Pass: " + (!largeResult)); // Verify large data logic
    }
}