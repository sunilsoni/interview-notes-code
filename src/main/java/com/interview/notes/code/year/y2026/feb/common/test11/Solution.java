package com.interview.notes.code.year.y2026.feb.common.test11;

public class Solution { // Defining the main class to hold our solution and tests

    public static boolean solution(String S) { // Method definition taking the input string S and returning a boolean
        // We check if the string contains the invalid sequence "ba"
        // If it does NOT contain "ba", the ! (NOT) operator flips it to true, meaning the string is valid
        return !S.contains("ba"); // Return the boolean result immediately
    }

    public static void main(String[] args) { // Simple main method for testing without JUnit
        
        // --- STANDARD TEST CASES --- //
        
        // Test 1: Given example "aabbb", should be true
        test("aabbb", true, "Test 1"); // Calling our custom test runner method
        
        // Test 2: Given example "ba", should be false
        test("ba", false, "Test 2"); // Calling our custom test runner method
        
        // Test 3: Given example "aaa", should be true
        test("aaa", true, "Test 3"); // Calling our custom test runner method
        
        // Test 4: Given example "b", should be true
        test("b", true, "Test 4"); // Calling our custom test runner method
        
        // Test 5: Given example "abba", should be false
        test("abba", false, "Test 5"); // Calling our custom test runner method
        
        // --- LARGE DATA TEST CASE --- //
        
        // Creating a very large string to test performance up to the 300,000 constraint
        String largeData = "a".repeat(150000) + "b".repeat(150000); // Java 11+ feature to easily repeat strings
        // Testing the large string, expecting true since all 'a's are before 'b's
        test(largeData, true, "Large Data Test (300,000 chars)"); // Calling the test runner
    }

    // Helper method to run tests and format the PASS/FAIL output cleanly
    private static void test(String input, boolean expected, String testName) { // Takes input, what we expect, and a label
        boolean result = solution(input); // Executes our solution against the input string
        if (result == expected) { // Checks if the actual result matches what we expected
            System.out.println(testName + " : PASS"); // If it matches, print PASS
        } else { // If it does not match
            System.out.println(testName + " : FAIL (Expected " + expected + " but got " + result + ")"); // Print FAIL with details
        }
    }
}