package com.interview.notes.code.year.y2026.june.common.test3;

import java.util.regex.Pattern; // Import Pattern class to define our regular expression for finding digits and letters
import java.util.stream.Collectors; // Import Collectors to join our processed stream elements into a single output string

public class StringDecoder { // Define the main public class for our text decoding logic
    
    public static String decode(String input) { // Define a static method that takes an encoded string and returns the decoded version
        if (input == null || input.isEmpty()) { // Check if the input is null or empty to prevent NullPointerException
            return ""; // Return an empty string safely if the input is missing or invalid
        } // End of safety validation block
        
        return Pattern.compile("(\\d+)([a-zA-Z])") // Compile a simple regex: group 1 captures digits (\d+), group 2 captures a letter ([a-zA-Z])
                .matcher(input) // Create a matcher object to scan the provided input string against our compiled regex
                .results() // Use Java 9+ results() to convert all regex matches into a continuous Stream of MatchResult objects
                .map(match -> match.group(2).repeat(Integer.parseInt(match.group(1)))) // Extract the letter, parse the number, and duplicate the letter that many times using Java 11 repeat()
                .collect(Collectors.joining()); // Join all the newly generated repeating string segments together into one final string
    } // End of the decode method

    public static void main(String[] args) { // Main execution method serving as our lightweight, pure Java testing framework
        runTest("3A2C0D", "AAACC", "Standard Input Test"); // Run the primary test case exactly as requested in the prompt
        
        runTest("10B", "BBBBBBBBBB", "Multi-digit Number Test"); // Run a test to ensure numbers greater than 9 are handled flawlessly
        
        runTest("0X0Y", "", "All Zeroes Test"); // Verify that letters instructed to repeat zero times output nothing
        
        runTest(null, "", "Null Input Test"); // Verify our early-return logic prevents application crashes on null data
        
        var largeInput = "50000A"; // Create a large data scenario instructing the system to print 'A' 50,000 times
        var largeExpected = "A".repeat(50000); // Dynamically generate the massive expected output safely using the built-in repeat function
        runTest(largeInput, largeExpected, "Large Data Test"); // Execute the large data test to ensure memory and streams handle bulk generation
    } // End of the main execution method

    private static void runTest(String input, String expected, String testName) { // Reusable test evaluation and console printing method
        var actual = decode(input); // Execute the core business logic method with the provided test input using local variable inference ('var')
        var passed = expected.equals(actual); // Compare the expected target string directly with the actual processed string
        
        if (passed) { // Check if the boolean flag indicates a successful string match
            System.out.println("PASS - " + testName); // Print a clean, formatted success message to the console
        } else { // Fallback execution path if the expected and actual strings differ
            System.out.println("FAIL - " + testName + " | Expected: " + expected + ", Actual: " + actual); // Print detailed failure info for rapid debugging
        } // End of the console output block
    } // End of the runTest method
    
} // End of the StringDecoder class definition