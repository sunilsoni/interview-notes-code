package com.interview.notes.code.year.y2026.july.common.test3;

public class Solution { // Define the main class named Solution to hold our program logic

    // Public static method to perform Run Length Encoding, taking a String and returning a String
    public static String rle(String input) { 
        
        // Edge case handling: Check if the string is null or empty to prevent NullPointerException or IndexOutOfBounds
        if (input == null || input.isEmpty()) { 
            return input; // If there is no data to process, simply return the original input
        } // End of edge case check

        // Initialize a StringBuilder to construct our encoded string efficiently (avoids memory overhead of String concatenation)
        var encoded = new StringBuilder(); // Use Java 'var' for brevity to let the compiler infer the StringBuilder type
        
        // Initialize a counter to track consecutive identical characters
        int count = 1; // Start at 1 because we inherently have at least one of the first character

        // Loop through the characters of the string, starting from the second character (index 1)
        for (int i = 1; i < input.length(); i++) { 
            
            // Compare the character at the current index with the character immediately preceding it
            if (input.charAt(i) == input.charAt(i - 1)) { 
                count++; // If they are exactly the same, increment our streak counter by 1
            } else { 
                // If they differ, the previous streak has ended, so we record it
                encoded.append(input.charAt(i - 1)).append(count); // Append the preceding character and its total count
                count = 1; // Reset the streak counter back to 1 for the new character we just encountered
            } // End of conditional check for character matching
        } // End of the main loop

        // After the loop finishes, the very last character group hasn't been appended yet
        encoded.append(input.charAt(input.length() - 1)).append(count); // Append the final character and its count to complete the string

        // Convert the StringBuilder object back into a standard String object
        return encoded.toString(); // Return the final encoded result to the caller
    } // End of rle method

    // Custom helper method to test expected results against actual results without needing JUnit
    private static void runTest(String input, String expected, String testName) { 
        var result = rle(input); // Execute the rle method with the provided test input
        
        // Evaluate if our method produced the correct answer
        if (expected.equals(result)) { 
            System.out.println("PASS: " + testName); // Print a success message to the console if outputs match
        } else { 
            // Print a failure message detailing what went wrong to help with debugging
            System.out.println("FAIL: " + testName + " | Expected: '" + expected + "', Got: '" + result + "'"); 
        } // End of verification logic
    } // End of test helper method

    // Standard main method serving as the entry point and our simple testing framework
    public static void main(String[] args) { 
        
        // Test Case 1: Provided example with a single character
        runTest("a", "a1", "Single Character"); 
        // Test Case 2: Provided example with a basic repeating character
        runTest("aa", "a2", "Double Character"); 
        // Test Case 3: Provided example with multiple different grouped characters
        runTest("aabbb", "a2b3", "Multiple Groups"); 
        // Test Case 4: Provided example where a previously seen character repeats later
        runTest("aabbbaa", "a2b3a2", "Repeating Groups Separated"); 
        // Test Case 5: Provided example of an empty string
        runTest("", "", "Empty String");
        // Test Case 5: Provided example of an empty string
        runTest("a11v22c1", "a111b122c11", "Empty String");

        // Large Data Test Case: Handling massive inputs to ensure O(N) performance doesn't cause memory or timeout issues
        var largeInput = "a".repeat(1000000); // Use modern String.repeat() to quickly generate a 1-million character string
        var largeExpected = "a1000000"; // The correct output should just be 'a' followed by the number 1,000,000
        runTest(largeInput, largeExpected, "Large Data Test (1M chars)"); // Execute the stress test
        
    } // End of main method
} // End of Solution class