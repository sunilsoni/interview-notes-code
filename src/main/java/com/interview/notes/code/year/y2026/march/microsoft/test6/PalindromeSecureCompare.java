package com.interview.notes.code.year.y2026.march.microsoft.test6;

public class PalindromeSecureCompare { // Define our class for the updated secure requirement

    public static void main(String[] args) { // Main method to run our standalone tests
        
        var tests = new String[]{ // Array holding our various test scenarios using var
            "A man, a plan, a canal: Panama", // Test 1: Standard complex palindrome
            "race a car", // Test 2: Standard failure case
            " ", // Test 3: Edge case with no valid alphanumeric characters
            "a".repeat(50000) + "b" + "a".repeat(50000) // Test 4: Large dataset to ensure it still performs well
        }; // End of test array
        
        var expected = new boolean[]{true, false, true, true}; // The expected boolean answers
        
        for (int i = 0; i < tests.length; i++) { // Loop over every test case
            var actual = isPalindrome(tests[i]); // Run the updated method
            var status = (actual == expected[i]) ? "PASS" : "FAIL"; // Validate if actual matches expected
            System.out.println("Test " + (i + 1) + ": " + status); // Print the results
        } // End testing loop
    } // End main

    static boolean isPalindrome(String s) { // Method returning true if palindrome, passing security rule
        
        int left = 0; // Pointer starting at index 0
        int right = s.length() - 1; // Pointer starting at the final index
        
        while (left < right) { // Loop until the pointers meet in the middle
            
            if (!Character.isLetterOrDigit(s.charAt(left))) { // Check if the left character is invalid
                left++; // Move pointer forward to skip spaces or punctuation
            } 
            else if (!Character.isLetterOrDigit(s.charAt(right))) { // Check if the right character is invalid
                right--; // Move pointer backward to skip spaces or punctuation
            } 
            else { // Both pointers are now resting on valid letters or numbers
                
                // --- THIS IS THE FIX FOR THE INTERVIEW REQUIREMENT ---
                var leftStr = s.substring(left, left + 1); // Extract the left character as a 1-length String object
                var rightStr = s.substring(right, right + 1); // Extract the right character as a 1-length String object
                
                if (!leftStr.equalsIgnoreCase(rightStr)) { // Use String API to compare securely, entirely avoiding char != char
                    return false; // The strings do not match, so return false immediately
                } // End of the secure comparison block
                
                left++; // The one-letter strings matched, move left pointer inward
                right--; // Move right pointer inward to check the next set
            } // End of valid character processing
            
        } // End of while loop
        
        return true; // Pointers crossed with zero mismatches, it is a palindrome
    } // End method
    
} // End class