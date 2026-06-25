package com.interview.notes.code.year.y2026.june.common.test1;

public class StringReverser { // Define the main class for our string reversing program
    
    public static String reverseString(String text) { // Create a public static method that takes a String and returns a reversed String
        if (text == null) { // Check if the input string is null to prevent a NullPointerException
            return null; // Return null early so the program doesn't crash on invalid data
        } // End of the null validation block
        
        var length = text.length(); // Get the length of the string using Java's 'var' keyword for brevity
        var reversed = new StringBuilder(length); // Initialize a StringBuilder with exact capacity to save memory on large data
        
        for (var i = length - 1; i >= 0; i--) { // Start a loop from the last character index, moving backwards down to zero
            reversed.append(text.charAt(i)); // Extract the character at the current index and append it to our builder
        } // End of the reversal loop block
        
        return reversed.toString(); // Convert the populated StringBuilder back into a standard String and return it
    } // End of the reverseString method

    public static void main(String[] args) { // Main method serves as the entry point for executing our custom test cases
        runTest("Hello World 123", "321 dlroW olleH", "Standard Test"); // Run the primary test case explicitly requested in the prompt
        runTest("", "", "Empty String Test"); // Run a test case with an empty string to ensure the loop handles it safely
        runTest(null, null, "Null Input Test"); // Run a test case passing null to verify our early-return safety check works
        
        var largeInput = "A".repeat(100000); // Generate a massive string of 100,000 'A's using Java 11+ repeat feature for stress testing
        runTest(largeInput, largeInput, "Large Data Test"); // Run the large data test to ensure it processes without memory or timeout errors
    } // End of the main execution method

    private static void runTest(String input, String expected, String testName) { // Helper method to compare actual vs expected results and print output
        var actual = reverseString(input); // Call our core logic method with the provided test input
        var passed = false; // Initialize a boolean flag to false to track if the test passes validation
        
        if (expected == null && actual == null) { // Check if both expected and actual values are null
            passed = true; // Mark as passed because handling null correctly is a success
        } else if (expected != null && expected.equals(actual)) { // If not null, check if expected text exactly matches the actual text
            passed = true; // Mark as passed since the reversed string is correct
        } // End of the validation logic block
        
        if (passed) { // Check if the passed flag was set to true
            System.out.println("PASS - " + testName); // Print a formatted success message to the console for this test
        } else { // Fallback execution path if the strings did not match
            System.out.println("FAIL - " + testName + " | Expected: " + expected + ", Actual: " + actual); // Print a failure message detailing what went wrong
        } // End of the console output block
    } // End of the runTest helper method
    
} // End of the class definition