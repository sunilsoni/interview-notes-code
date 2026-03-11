package com.interview.notes.code.year.y2026.march.microsoft.test9;

public class PalindromeSentence { // Define the main class to encapsulate our solution

    public static void main(String[] args) { // Entry point of the program for testing
        
        var testCases = new String[]{ // Declare an array to hold our input strings using 'var'
            "A man, a plan, a canal: Panama", // Test 1: Standard complex palindrome sentence
            "race a car", // Test 2: Standard non-palindrome sentence
            " ", // Test 3: Edge case with only spaces (should be true)
            "a".repeat(100000) + "b" + "a".repeat(100000) // Test 4: Large data input to check performance
        }; // Close the test case array initialization
        
        var expected = new boolean[]{true, false, true, true}; // Array holding the expected boolean results
        
        for (int i = 0; i < testCases.length; i++) { // Start a loop to iterate through every test case
            var actual = isPalindrome(testCases[i]); // Call our method and store the returned boolean
            var status = (actual == expected[i]) ? "PASS" : "FAIL"; // Check if actual matches expected for PASS/FAIL
            System.out.println("Test " + (i + 1) + ": " + status); // Print the formatted outcome to the console
        } // End of the testing loop
    } // End of the main method

    static boolean isPalindrome(String s) { // Method taking a string and returning a boolean result
        var chars = s.chars() // Convert string to an IntStream of characters to use Stream API
            .filter(Character::isLetterOrDigit) // Keep only alphanumeric characters, dropping punctuation/spaces
            .map(Character::toLowerCase) // Standardize everything to lowercase for accurate comparison
            .boxed() // Convert the primitive ints into Integer objects so they can go into a List
            .toList(); // Java 16+ feature to collect the stream directly into an unmodifiable List
            
        return chars.equals(chars.reversed()); // Java 21 feature: compare the list with its reversed view
    } // End of the isPalindrome method
    
} // End of the class