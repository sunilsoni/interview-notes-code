package com.interview.notes.code.year.y2026.april.assessments.test2;

public class ReverseString {
    // Method to reverse string
    static String reverse(String s) {
        // StringBuilder is fast for reversing
        return new StringBuilder(s).reverse().toString();
    }

    // Main method for testing
    public static void main(String[] args) {
        // Test cases array: input + expected output
        String[][] tests = {
            {"Hello World 123", "321 dlroW olleH"}, // normal case
            {"", ""},                              // empty string
            {"A", "A"},                            // single char
            {"12345", "54321"},                    // numbers only
            {"racecar", "racecar"},                // palindrome
            { "a".repeat(1000000), "a".repeat(1000000) } // large input
        };

        // Process each test case
        for (int i = 0; i < tests.length; i++) {
            String input = tests[i][0];   // original string
            String expected = tests[i][1]; // expected reversed
            String actual = reverse(input); // call reverse method

            // Compare and print PASS/FAIL
            if (actual.equals(expected)) {
                System.out.println("Test " + (i+1) + ": PASS");
            } else {
                System.out.println("Test " + (i+1) + ": FAIL");
            }
        }
    }
}
