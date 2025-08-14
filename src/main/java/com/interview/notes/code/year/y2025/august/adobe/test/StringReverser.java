package com.interview.notes.code.year.y2025.august.adobe.test;

public class StringReverser {
    
    // Method to reverse a string without using built-in functions
    static String reverse(String str) {
        // Create a new char array to store reversed characters
        char[] reversed = new char[str.length()];
        
        // Get input string length for iteration
        int length = str.length();
        
        // Iterate through string from end to start
        for (int i = 0; i < length; i++) {
            // Place characters from end to start of new array
            reversed[i] = str.charAt(length - 1 - i);
        }
        
        // Convert char array back to string manually
        return new String(reversed);
    }

    // Main method containing test cases and validation
    public static void main(String[] args) {
        // Test cases array containing various scenarios
        TestCase[] testCases = {
            new TestCase("hello", "olleh"),
            new TestCase("world", "dlrow"),
            new TestCase("a", "a"),
            new TestCase("", ""),
            new TestCase("Data Structure", "erutcurtS ataD"),
            // Large data test case
            new TestCase("a".repeat(100000), "a".repeat(100000))
        };

        // Counter for tracking passed tests
        int passedTests = 0;
        
        // Process each test case
        for (int i = 0; i < testCases.length; i++) {
            TestCase test = testCases[i];
            
            // Get actual result from our reverse method
            String result = reverse(test.input);
            
            // Compare with expected output
            boolean passed = result.equals(test.expected);
            
            // Update passed tests counter
            if (passed) passedTests++;
            
            // Print test results
            System.out.printf("Test %d: %s\n", i + 1, passed ? "PASS" : "FAIL");
            System.out.printf("Input: \"%s\"\n", test.input);
            System.out.printf("Expected: \"%s\"\n", test.expected);
            System.out.printf("Actual: \"%s\"\n", result);
            System.out.println();
        }

        // Print final test summary
        System.out.printf("Tests passed: %d/%d\n", passedTests, testCases.length);
    }

    // Helper class to store test cases
    static class TestCase {
        String input;
        String expected;
        
        TestCase(String input, String expected) {
            this.input = input;
            this.expected = expected;
        }
    }
}
