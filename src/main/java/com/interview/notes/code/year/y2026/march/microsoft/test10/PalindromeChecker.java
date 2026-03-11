package com.interview.notes.code.year.y2026.march.microsoft.test10;

public class PalindromeChecker { // Define the main class to hold our palindrome logic and tests

    public static void main(String[] args) { // Main method serves as our simple, dependency-free testing framework
        
        var tests = new String[]{ // Use Java 'var' to compactly declare our array of test cases
            "A man, a plan, a canal: Panama", // Test 1: Standard palindrome with punctuation and mixed casing
            "race a car", // Test 2: Standard non-palindrome sentence
            " ", // Test 3: Edge case with only spaces (valid palindrome since it has 0 alphanumeric chars)
            "a".repeat(500000) + "b" + "a".repeat(500000) // Test 4: Massive data input to prove high performance
        }; // End of test cases array initialization
        
        var expected = new boolean[]{true, false, true, true}; // Array containing the correct answers for verification
        
        for (int i = 0; i < tests.length; i++) { // Loop through each test case using a standard index
            var actual = isPalindrome(tests[i]); // Execute our algorithm and store the boolean result
            var status = (actual == expected[i]) ? "PASS" : "FAIL"; // Check if the result matches the expected outcome
            System.out.println("Test " + (i + 1) + ": " + status); // Print the formatted PASS/FAIL result to the console
        } // End of the testing loop
    } // End of the main method

    static boolean isPalindrome(String s) { // Method to evaluate the string, returning true if it's a palindrome
        
        int left = 0; // Initialize a pointer at the very beginning of the string (index 0)
        int right = s.length() - 1; // Initialize a second pointer at the very end of the string
        
        while (left < right) { // Keep evaluating as long as the left pointer is before the right pointer
            
            char lChar = s.charAt(left); // Extract the character currently at the left pointer for easier reading
            char rChar = s.charAt(right); // Extract the character currently at the right pointer for easier reading
            
            if (!Character.isLetterOrDigit(lChar)) { // Check if the left character is a space, symbol, or punctuation
                left++; // Move the left pointer forward one step to skip the invalid character
            } 
            else if (!Character.isLetterOrDigit(rChar)) { // Check if the right character is a space, symbol, or punctuation
                right--; // Move the right pointer backward one step to skip the invalid character
            } 
            else { // If both characters are valid alphanumeric letters or numbers...
                if (Character.toLowerCase(lChar) != Character.toLowerCase(rChar)) { // Convert both to lowercase and compare them
                    return false; // If they don't match, it's not a palindrome, so immediately return false
                } // End of the comparison block
                
                left++; // The characters matched perfectly, so move the left pointer inward
                right--; // Move the right pointer inward to check the next pair
            } // End of the valid character processing block
            
        } // End of the while loop; if it finishes, all characters matched successfully
        
        return true; // Return true because no mismatches were found in the entire string
    } // End of the isPalindrome method
    
} // End of the class definition