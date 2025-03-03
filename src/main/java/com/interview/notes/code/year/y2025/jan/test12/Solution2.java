package com.interview.notes.code.year.y2025.jan.test12;

/*
/**
 * Instructions to candidate.
 * 1) Run this code in the REPL to observe its behavior. The execution entry point is main().
 * 2) Consider adding some additional tests in doTestsPass().
 * 3) Implement reverseStr(String str) correctly.
 * 4) If time permits, some possible follow-ups.
 */


public class Solution2 {
    /**
     * Reverses the input string
     * Time Complexity: O(n) where n is string length
     * Space Complexity: O(n) for creating new string
     */
    public static String reverseStr(String str) {
        if (str == null) {
            return null;
        }

        StringBuilder reversed = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            reversed.append(str.charAt(i));
        }
        return reversed.toString();
    }

    /**
     * Comprehensive test cases including edge cases
     */
    public static boolean doTestsPass() {
        // Test cases array with input and expected output
        String[][] testCases = {
                {"abcd", "dcba"},           // Basic case
                {"", ""},                   // Empty string
                {"a", "a"},                // Single character
                {"12345", "54321"},        // Numbers
                {"a b c", "c b a"},        // String with spaces
                {"!@#$", "$#@!"},          // Special characters
                {"aabbcc", "ccbbaa"},      // Repeated characters
                {null, null}               // Null case
        };

        boolean allTestsPass = true;

        // Run each test case
        for (String[] test : testCases) {
            String input = test[0];
            String expected = test[1];
            String actual = reverseStr(input);

            boolean testPassed = (actual == null && expected == null) ||
                    (actual != null && actual.equals(expected));

            if (!testPassed) {
                System.out.println("FAIL: Input: " + input +
                        ", Expected: " + expected +
                        ", Actual: " + actual);
                allTestsPass = false;
            }
        }

        // Test for large input
        StringBuilder largeInput = new StringBuilder();
        for (int i = 0; i < 1000000; i++) {
            largeInput.append('a');
        }
        String largeStr = largeInput.toString();

        try {
            long startTime = System.currentTimeMillis();
            String reversed = reverseStr(largeStr);
            long endTime = System.currentTimeMillis();

            if (reversed.length() != largeStr.length()) {
                System.out.println("FAIL: Large input test - incorrect length");
                allTestsPass = false;
            }

            System.out.println("Large input test completed in " +
                    (endTime - startTime) + "ms");

        } catch (Exception e) {
            System.out.println("FAIL: Large input test threw exception: " +
                    e.getMessage());
            allTestsPass = false;
        }

        return allTestsPass;
    }

    /**
     * Main method to run tests
     */
    public static void main(String[] args) {
        if (doTestsPass()) {
            System.out.println("All tests pass");
        } else {
            System.out.println("There are test failures");
        }
    }
}
