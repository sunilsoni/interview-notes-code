package com.interview.notes.code.year.y2025.may.common.test2;

public class StringRotationChecker {

    // Method to check if one string is rotation of another
    public static boolean isRotation(String str1, String str2) {
        // Check if lengths are equal and not zero
        if (str1 == null || str2 == null ||
                str1.length() != str2.length() ||
                str1.length() == 0) {
            return false;
        }

        // Concatenate str1 with itself
        // This creates all possible rotations
        String doubleStr1 = str1 + str1;

        // Check if str2 is substring of doubleStr1
        return doubleStr1.contains(str2);
    }

    // Main method with test cases
    public static void main(String[] args) {
        // Test cases
        runTest("ABCD", "CDAB", true);  // Regular case - should pass
        runTest("ABCD", "ACBD", false); // Not a rotation - should fail
        runTest("", "", false);         // Empty strings - should fail
        runTest("A", "A", true);        // Single char - should pass
        runTest(null, "ABC", false);    // Null check - should fail
        runTest("ABC", null, false);    // Null check - should fail

        // Large string test
        String largeStr1 = "A".repeat(1000000);
        String largeStr2 = "A".repeat(1000000);
        runTest(largeStr1, largeStr2, true); // Large input - should pass
    }

    // Helper method to run tests
    private static void runTest(String s1, String s2, boolean expected) {
        boolean result = isRotation(s1, s2);
        System.out.printf("Test: s1='%s', s2='%s' -> %s%n",
                s1, s2,
                result == expected ? "PASS" : "FAIL");
    }
}
