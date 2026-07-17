package com.interview.notes.code.year.y2026.july.common.test4;

public class ValidPalindrome { // Main class encapsulation for our logic and test runner.

    public static boolean isPalindrome(String s) { // Method accepts the string and returns true if it's a palindrome.
        
        int left = 0; // Initialize a pointer starting at the very beginning (first character) of the string.
        int right = s.length() - 1; // Initialize a second pointer starting at the very end (last character) of the string.

        while (left < right) { // Loop continues strictly while the left pointer is before the right pointer.
            
            char leftChar = s.charAt(left); // Extract the character currently at the left pointer's position.
            char rightChar = s.charAt(right); // Extract the character currently at the right pointer's position.
            
            if (!isAlphanumeric(leftChar)) { // Check if the left character is NOT a valid letter or number using our helper.
                left++; // If it is not alphanumeric, move the left pointer forward to skip it.
            } 
            else if (!isAlphanumeric(rightChar)) { // Check if the right character is NOT a valid letter or number.
                right--; // If it is not alphanumeric, move the right pointer backward to skip it.
            } 
            else { // Both pointers are now sitting on valid letters or numbers.
                
                if (toLowerCase(leftChar) != toLowerCase(rightChar)) { // Convert both to lowercase manually and compare them.
                    return false; // If they do not match, the word is not a palindrome; exit immediately.
                }
                
                left++; // The characters matched, so step the left pointer inward for the next check.
                right--; // Step the right pointer inward for the next check.
            }
        }
        
        return true; // If the pointers meet in the middle without triggering a mismatch, it is a valid palindrome.
    }

    // --- Private Helper Methods ---

    private static boolean isAlphanumeric(char c) { // Helper method to replace Character.isLetterOrDigit
        // Returns true if the character's ASCII value falls inside the lowercase, uppercase, or number ranges.
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9'); 
    }

    private static char toLowerCase(char c) { // Helper method to replace Character.toLowerCase
        if (c >= 'A' && c <= 'Z') { // Check if the character is currently uppercase.
            return (char) (c + 32); // Add 32 to shift it to its exact lowercase equivalent in the ASCII table.
        }
        return c; // If it's already lowercase or a number, return it exactly as it is.
    }

    // --- Testing Section Below ---
    
    public static void main(String[] args) { // Main method serves as our lightweight, dependency-free test framework.
        
        runTest("A man, a plan, a canal: Panama", true); // LeetCode Case 1: Complex phrase with spaces and punctuation.
        runTest("race a car", false); // LeetCode Case 2: Valid alphanumeric words but fails the backward reading check.
        runTest(" ", true); // LeetCode Case 3: Empty/blank strings are mathematically valid palindromes.
        runTest("0P", false); // Edge case: Ensuring digits and letters are compared correctly.
        runTest("ab_a", true); // Edge case: Ensuring underscores are treated as punctuation and skipped.
        
        var largeInput = "a".repeat(500000) + "b" + "a".repeat(500000); // Java 11+ feature to generate a massive string (1 million+ chars).
        runTest(largeInput, true); // Large data test to ensure our O(1) space logic handles scale properly.
    }

    private static void runTest(String input, boolean expected) { // Helper function to execute tests and print visual PASS/FAIL outputs.
        
        var result = isPalindrome(input); // Executes our core logic method using the provided test string.
        var status = (result == expected) ? "PASS" : "FAIL"; // Checks if actual output matches expected, formatting the status.
        
        var displayInput = input.length() > 50 ? "Large Input Data (Truncated)" : input; // Hides massive strings from flooding the console printout.
        System.out.println("Status: " + status + " | Expected: " + expected + " | Actual: " + result + " | Input: '" + displayInput + "'"); // Prints the test report.
    }
}