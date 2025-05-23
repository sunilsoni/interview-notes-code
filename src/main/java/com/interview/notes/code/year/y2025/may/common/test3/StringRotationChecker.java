package com.interview.notes.code.year.y2025.may.common.test3;

public class StringRotationChecker {

    // Method to check if one string is rotation of another using for loop
    public static boolean isRotation(String str1, String str2) {
        // Handle edge cases
        if (str1 == null || str2 == null ||
                str1.length() != str2.length() ||
                str1.length() == 0) {
            return false;
        }

        int len = str1.length();

        // Try all possible rotations
        for (int i = 0; i < len; i++) {
            boolean isMatch = true;

            // Check each character of rotated string
            for (int j = 0; j < len; j++) {
                // Get rotated index
                int rotatedIndex = (i + j) % len;

                // Compare characters
                if (str1.charAt(rotatedIndex) != str2.charAt(j)) {
                    isMatch = false;
                    break;
                }
            }

            // If we found a match, return true
            if (isMatch) {
                return true;
            }
        }

        return false;
    }

    // Main method with test cases
    public static void main(String[] args) {
        // Basic test cases
        runTest("ABCD", "CDAB", true);    // Regular rotation
        runTest("ABCD", "ACBD", false);   // Not a rotation
        runTest("", "", false);           // Empty strings
        runTest("A", "A", true);          // Single character
        runTest(null, "ABC", false);      // Null check
        runTest("ABC", null, false);      // Null check
        runTest("AA", "AA", true);        // Repeated characters
        runTest("hello", "llohe", true);  // Longer string

        // Large string test (but keep it reasonable for loop approach)
        String largeStr1 = "A".repeat(1000);
        String largeStr2 = "A".repeat(1000);
        runTest(largeStr1, largeStr2, true);
    }

    // Helper method to run tests
    private static void runTest(String s1, String s2, boolean expected) {
        boolean result = isRotation(s1, s2);
        System.out.printf("Test: s1='%s', s2='%s' -> %s%n",
                s1, s2,
                result == expected ? "PASS" : "FAIL");
    }
}
