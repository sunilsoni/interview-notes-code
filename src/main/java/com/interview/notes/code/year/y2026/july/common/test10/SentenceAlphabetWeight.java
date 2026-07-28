package com.interview.notes.code.year.y2026.july.common.test10;

public class SentenceAlphabetWeight { // Main class definition to encapsulate the solution and testing

    public static long computeWeight(String s) { // Method accepts a string and returns a long to safely handle very large sums
        if (s == null) return 0; // Guard clause: immediately return 0 if the input is null to prevent runtime exceptions
        
        return s.chars() // Convert the String into an IntStream representing the character codes of the string
                .filter(Character::isLetter) // Intermediate operation: keep only characters that are alphabetic letters
                .map(c -> Character.toLowerCase(c) - 'a' + 1) // Intermediate operation: convert to lowercase, subtract ASCII 'a', and add 1 to get a 1-26 range
                .asLongStream() // Intermediate operation: convert the IntStream to a LongStream to prevent integer overflow during summation
                .sum(); // Terminal operation: add all the mapped long values together and return the final total
    } 

    public static void main(String[] args) { // Standard main method used strictly for running custom tests without JUnit
        runTest("Hello, World!", 124); // Test Case 1: The standard example provided in the problem description
        runTest("abc", 6); // Test Case 2: Simple consecutive lowercase letters to verify basic mapping (1+2+3=6)
        runTest("Z y X!", 75); // Test Case 3: Mixed uppercase, spaces, and punctuation to verify filtering and case insensitivity
        runTest("1234567890!@#", 0); // Test Case 4: Only digits and symbols to verify that non-letters are completely ignored
        runTest("", 0); // Test Case 5: Empty string to verify it returns 0 without throwing an index error
        runTest(null, 0); // Test Case 6: Null input to verify our initial guard clause works properly
        runTest("z".repeat(1000000), 26000000L); // Test Case 7: Extreme large data (1 million 'z's) to verify performance and test Long overflow safety
    } 

    private static void runTest(String input, long expected) { // Helper method to execute a test case and format the PASS/FAIL output
        long actual = computeWeight(input); // Execute our business logic method with the provided test input
        boolean isPass = (actual == expected); // Evaluate if the actual result strictly matches the expected result
        String status = isPass ? "PASS" : "FAIL"; // Assign a human-readable status string based on the boolean result
        String displayInput = (input != null && input.length() > 50) ? "LARGE_DATA_INPUT" : input; // Truncate console output for massive strings to keep logs readable
        System.out.println(status + " | Expected: " + expected + " | Actual: " + actual + " | Input: " + displayInput); // Print the final formatted test report to the system console
    } 
}