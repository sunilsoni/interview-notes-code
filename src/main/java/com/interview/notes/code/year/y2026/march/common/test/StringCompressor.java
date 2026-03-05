package com.interview.notes.code.year.y2026.march.common.test;

public class StringCompressor {

    // Main method to act as our custom test runner
    public static void main(String[] args) {
        // Run standard test cases
        runTest("Standard Case", "DDDYYFFDD", "3D2Y2F2D");
        runTest("Single Characters", "ABC", "1A1B1C");
        runTest("All Same", "AAAAA", "5A");
        runTest("Empty String", "", "");
        runTest("Null String", null, null);
        
        // Generate a massive string for large data testing (10,000 'A's followed by 5,000 'B's)
        var largeInput = "A".repeat(10000) + "B".repeat(5000); // Java 11+ String.repeat
        var largeExpected = "10000A5000B"; // Expected compressed output
        runTest("Large Data Load", largeInput, largeExpected); // Execute the large data test
    }

    // The core compression logic
    public static String compress(String input) {
        // Base case: return immediately if the input is null or empty to prevent exceptions
        if (input == null || input.isEmpty()) return input; 
        
        // Use var (Java 10+) for brevity; StringBuilder is critical for performance on large strings
        var result = new StringBuilder(); 
        
        // Initialize our counter to 1, since we always have at least one of the first character
        int count = 1; 
        
        // Loop starts at index 1 so we can safely compare current char with the previous char (i - 1)
        for (int i = 1; i < input.length(); i++) { 
            
            // Check if the current character is exactly the same as the one right before it
            if (input.charAt(i) == input.charAt(i - 1)) { 
                // If it's a match, just increase the tally
                count++; 
            } else { 
                // If the character changed, we've reached the end of a sequence
                // Append the final count of the previous character to our result
                result.append(count); 
                // Append the actual previous character itself
                result.append(input.charAt(i - 1)); 
                // Reset the counter back to 1 for this new character we just found
                count = 1; 
            }
        }
        
        // Edge case: The loop finishes, but we haven't appended the very last sequence yet
        result.append(count); 
        // Grab the very last character of the string and append it
        result.append(input.charAt(input.length() - 1)); 
        
        // Convert the highly efficient StringBuilder back into a standard immutable String
        return result.toString(); 
    }

    // Helper method to format test output as PASS/FAIL
    private static void runTest(String testName, String input, String expected) {
        // Execute our method against the input
        var actual = compress(input); 
        
        // Determine if actual matches expected (handling nulls safely with Objects.equals)
        boolean passed = java.util.Objects.equals(actual, expected); 
        
        // Print the result to the console with a clear PASS/FAIL prefix
        System.out.println((passed ? "[PASS] " : "[FAIL] ") + testName); 
        
        // If it failed, print out what went wrong so we can debug it
        if (!passed) { 
            // Show what was expected vs what was actually returned
            System.out.println("   Expected: " + expected + " | Got: " + actual); 
        }
    }
}