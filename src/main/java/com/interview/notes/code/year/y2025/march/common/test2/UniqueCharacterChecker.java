package com.interview.notes.code.year.y2025.march.common.test2;

public class UniqueCharacterChecker {

    // Method to check if all characters in string are unique
    private static boolean areCharactersUnique(String input) {
        if (input == null || input.length() == 0) {
            return true;
        }

        // Create array to track character occurrences
        boolean[] charSeen = new boolean[128]; // Assuming ASCII

        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            // If we've seen this char before, return false
            if (charSeen[currentChar]) {
                return false;
            }
            // Mark this char as seen
            charSeen[currentChar] = true;
        }
        return true;
    }

    public static void main(String[] args) {
        // Test cases
        runTest("ABC", true);
        runTest("ABA", false);
        runTest("ABCDEFGA", false);
        runTest("", true);
        runTest("A", true);
        runTest("11", false);
        runTest("!@#$!", false);

        // Large input test
        StringBuilder largeInput = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            largeInput.append((char) ('A' + (i % 26)));
        }
        runTest(largeInput.toString(), false);
    }

    // Helper method to run tests
    private static void runTest(String input, boolean expectedResult) {
        boolean result = areCharactersUnique(input);
        System.out.printf("Input: %-20s Expected: %-7b Got: %-7b %s%n",
                input.length() > 20 ? input.substring(0, 17) + "..." : input,
                expectedResult,
                result,
                result == expectedResult ? "PASS" : "FAIL");
    }
}
