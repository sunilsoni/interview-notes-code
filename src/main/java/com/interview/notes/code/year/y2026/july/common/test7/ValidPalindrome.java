package com.interview.notes.code.year.y2026.july.common.test7;

public class ValidPalindrome { // Defines the main class to encapsulate our algorithm and test runner.

    public static boolean isPalindrome(String s) { // Method signature accepting the input string and returning a boolean result.
        
        var cleanText = s.chars() // 'var' minimizes code words; chars() converts the string into an IntStream of character codes.
            .filter(Character::isLetterOrDigit) // Stream filter keeps only letters and numbers, discarding spaces and punctuation.
            .map(Character::toLowerCase) // Maps every valid character to its lowercase equivalent to ensure case-insensitive matching.
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append) // Efficiently gathers stream results into a new StringBuilder.
            .toString(); // Converts the fully assembled StringBuilder back into a standard String for final comparison.
            
        var reversedText = new StringBuilder(cleanText) // Initializes a second StringBuilder with our filtered text so we can manipulate it.
            .reverse() // Flips the sequence of characters backwards in a highly optimized single operation.
            .toString(); // Converts the reversed sequence back to a String so we can perform an exact equality check.
            
        return cleanText.equals(reversedText); // Evaluates if the forward string exactly matches the backward string, returning the result.
    }

    // --- Testing Section Below ---
    
    public static void main(String[] args) { // Main method serves as our lightweight, dependency-free test execution framework.
        
        runTest("A man, a plan, a canal: Panama", true); // LeetCode Case 1: Standard complex phrase with spaces and punctuation.
        runTest("race a car", false); // LeetCode Case 2: Valid alphanumeric words but fails the backward reading check.
        runTest(" ", true); // LeetCode Case 3: Edge case where a blank string becomes empty and should return true.
        runTest("0P", false); // Edge case: Mixing numbers and letters to ensure digits aren't ignored.
        runTest("ab_a", true); // Edge case: Underscores are not alphanumeric and should be stripped out.
        
        var largeInput = "a".repeat(100000) + "b" + "a".repeat(100000); // Java 11+ feature to easily mock massive data (200,001 characters).
        runTest(largeInput, true); // Large data test to ensure the Stream API and memory allocation scale properly without crashing.
    }

    private static void runTest(String input, boolean expected) { // Helper function to execute tests and print visual PASS/FAIL outputs.
        
        var result = isPalindrome(input); // Executes our core logic method using the provided test string.
        var status = (result == expected) ? "PASS" : "FAIL"; // Ternary operator to check if actual output matches expected, formatting the status.
        
        var displayInput = input.length() > 50 ? "Large Input Data (Truncated)" : input; // Prevents console flooding by hiding massive strings from the printout.
        System.out.println("Status: " + status + " | Expected: " + expected + " | Actual: " + result + " | Input: '" + displayInput + "'"); // Prints the clean, readable test report to the console.
    }
}