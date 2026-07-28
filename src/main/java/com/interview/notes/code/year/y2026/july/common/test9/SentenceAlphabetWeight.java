package com.interview.notes.code.year.y2026.july.common.test9;

public class SentenceAlphabetWeight { // Main class to contain the solution and custom testing logic

    public static long computeWeight(String s) { // Method accepts a string and returns a long for safety with large data
        if (s == null) return 0; // Guard clause: return 0 immediately if input is null to avoid crashes
        
        return s.chars() // Convert the String into an IntStream of character ASCII/Unicode values
                .map(Character::toLowerCase) // Intermediate operation: convert every character to lowercase first
                .filter(c -> c >= 'a' && c <= 'z') // Intermediate operation: strictly keep only standard English letters, ignoring other Unicode chars
                .mapToLong(c -> c - 'a' + 1) // Intermediate operation: subtract 'a' to get 0-25, add 1 to map to 1-26, convert to LongStream
                .sum(); // Terminal operation: add all mapped values together and return the final total sum
    } 

    public static void main(String[] args) { // Main method used strictly to execute tests without needing JUnit
        runTest("Hello, World!", 124); // Test Case 1: Standard example from the first prompt
        runTest("abc XYZ", 81); // Test Case 2: New example handling upper/lower case mapping
        runTest("José!", 43); // Test Case 3: Tests the Unicode constraint (é is ignored, J=10, o=15, s=19 -> 44)
        runTest("1234567890!@#", 0); // Test Case 4: Verifies digits and symbols map to a 0 weight
        runTest("", 0); // Test Case 5: Tests the minimum length constraint of 0
        runTest("z".repeat(100000), 2600000L); // Test Case 6: Tests the maximum length constraint of 100,000
    } 

    private static void runTest(String input, long expected) { // Helper method to run a test and print PASS/FAIL status
        long actual = computeWeight(input); // Call the business logic method with the current test input
        boolean isPass = (actual == expected); // Check if the computed result exactly matches the expected result
        String status = isPass ? "PASS" : "FAIL"; // Assign a formatted string based on the boolean result
        String displayInput = (input != null && input.length() > 50) ? "LARGE_DATA_INPUT" : input; // Prevent massive strings from flooding the console output
        System.out.println(status + " | Expected: " + expected + " | Actual: " + actual + " | Input: " + displayInput); // Print the final formatted result to the console
    } 
}