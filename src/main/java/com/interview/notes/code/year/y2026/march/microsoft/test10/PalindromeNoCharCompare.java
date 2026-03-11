package com.interview.notes.code.year.y2026.march.microsoft.test10;

public class PalindromeNoCharCompare { // Define the main class to hold our new logic

    public static void main(String[] args) { // Main method serves as our simple testing framework
        
        var tests = new String[]{ // Declare our array of test cases using 'var'
            "A man, a plan, a canal: Panama", // Test 1: Standard palindrome with punctuation
            "race a car", // Test 2: Standard non-palindrome sentence
            " ", // Test 3: Edge case with only spaces
            "a".repeat(500000) + "b" + "a".repeat(500000) // Test 4: Massive data input to prove it handles large scales
        }; // End of test cases array initialization
        
        var expected = new boolean[]{true, false, true, true}; // Array containing the correct answers
        
        for (int i = 0; i < tests.length; i++) { // Loop through each test case sequentially
            var actual = isPalindrome(tests[i]); // Execute our algorithm and store the boolean result
            var status = (actual == expected[i]) ? "PASS" : "FAIL"; // Check if the result matches the expected outcome
            System.out.println("Test " + (i + 1) + ": " + status); // Print the formatted PASS/FAIL result
        } // End of the testing loop
    } // End of the main method

    static boolean isPalindrome(String s) { // Method to evaluate the string without character comparisons
        
        var cleaned = s.replaceAll("[^a-zA-Z0-9]", ""); // Use regex to instantly remove all non-alphanumeric data at the string level
        
        var reversed = new StringBuilder(cleaned).reverse().toString(); // Create a reversed copy of the cleaned string in one go
        
        return cleaned.equalsIgnoreCase(reversed); // Compare the two full strings directly, ignoring case, fulfilling the new requirement
        
    } // End of the isPalindrome method
    
} // End of the class definition