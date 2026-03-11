package com.interview.notes.code.year.y2026.march.microsoft.test5;

public class PalindromeHalfCompare { // Define the main class to hold our block-comparison logic

    public static void main(String[] args) { // Entry point for our simple, built-in testing
        
        var tests = new String[]{ // Declare our array of test cases using Java 'var'
            "A man, a plan, a canal: Panama", // Test 1: Complex palindrome with punctuation
            "race a car", // Test 2: Standard non-palindrome
            " ", // Test 3: Edge case with only spaces
            "a".repeat(500000) + "b" + "a".repeat(500000) // Test 4: Massive data input to test performance
        }; // End of test cases array initialization
        
        var expected = new boolean[]{true, false, true, true}; // Array containing the correct answers for verification
        
        for (int i = 0; i < tests.length; i++) { // Loop through each test case sequentially
            var actual = isPalindrome(tests[i]); // Execute our algorithm and store the boolean result
            var status = (actual == expected[i]) ? "PASS" : "FAIL"; // Check if the result matches the expected outcome
            System.out.println("Test " + (i + 1) + ": " + status); // Print the formatted PASS/FAIL result to the console
        } // End of the testing loop
    } // End of the main method

    static boolean isPalindrome(String s) { // Method to evaluate the string by comparing halves
        
        var clean = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase(); // Clean out symbols and lowercase the whole string at once
        
        if (clean.length() <= 1) { // If the string is empty or just 1 letter after cleaning...
            return true; // ...it is automatically a palindrome, so return true early
        } // End of edge-case check
        
        int mid = clean.length() / 2; // Find the exact mathematical middle of the cleaned string
        
        var firstHalf = clean.substring(0, mid); // Extract the entire first half of the string as one large block
        
        var secondHalf = clean.substring(clean.length() - mid); // Extract the entire second half of the string as one large block
        
        var reversedSecondHalf = new StringBuilder(secondHalf).reverse().toString(); // Reverse the entire second half block at once
        
        // --- THIS FIXES THE INTERVIEW REQUIREMENT ---
        return firstHalf.equals(reversedSecondHalf); // Compare the two massive blocks using internal memory, ZERO single-character comparisons!
        
    } // End of the isPalindrome method
    
} // End of the class definition