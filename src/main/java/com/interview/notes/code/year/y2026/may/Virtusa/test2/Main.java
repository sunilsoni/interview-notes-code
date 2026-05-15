package com.interview.notes.code.year.y2026.may.Virtusa.test2;

public class Main { // Defines the main class for our program

    public static boolean isValidParentheses(String s) { // Method that takes a string and returns true or false
        int count = 0; // Initializes a counter to track open vs closed brackets

        for (char c : s.toCharArray()) { // Converts the string to characters and loops through each one
            if (c == '(') count++; // If the character is an open parenthesis, increase the count
            if (c == ')') count--; // If the character is a closed parenthesis, decrease the count
            
            if (count < 0) return false; // If count is negative, a closing bracket appeared too early, so fail immediately
        } // End of the character loop

        return count == 0; // If count is zero at the end, everything matched perfectly, otherwise it fails
    } // End of the validation method

    public static void runTest(String testName, String input, boolean expected) { // Helper method to test and print PASS/FAIL
        boolean actual = isValidParentheses(input); // Calls our logic method with the test input
        String result = (actual == expected) ? "PASS" : "FAIL"; // Checks if our actual result matches the expected result
        System.out.println(testName + " -> " + result); // Prints the outcome to the console
    } // End of the helper method

    public static void main(String[] args) { // The starting point of the Java application
        
        // Testing the standard cases provided in the image
        runTest("Test 1", "(a + b) * (c + d)", true); // Should pass (balanced)
        runTest("Test 2", "(a + b))", false); // Should pass (extra closing bracket)
        runTest("Test 3", "(a + (b * c) - (d / e))", true); // Should pass (nested balanced brackets)
        runTest("Test 4", "((a + b)", false); // Should pass (missing a closing bracket)

        // Testing edge cases
        runTest("Empty String", "", true); // Should pass (no brackets means it is technically balanced)
        runTest("No Brackets", "a + b", true); // Should pass (ignoring normal characters works)
        runTest("Reversed", ")a+b(", false); // Should pass (closing bracket first is invalid)

        // Testing Large Data Inputs (using modern Java string repeating)
        var massiveValidInput = "(".repeat(500000) + ")".repeat(500000); // Creates a massive string with 1 million brackets
        runTest("Large Data Valid", massiveValidInput, true); // Should process quickly and pass

        var massiveInvalidInput = "(".repeat(500000) + ")".repeat(500001); // Creates a massive string with 1 extra closing bracket
        runTest("Large Data Invalid", massiveInvalidInput, false); // Should process quickly and pass
        
    } // End of the main method
} // End of the class