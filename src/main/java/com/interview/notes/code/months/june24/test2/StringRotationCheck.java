package com.interview.notes.code.months.june24.test2;

public class StringRotationCheck {

    // Method to check if str2 is a rotation of str1
    public static boolean isRotation(String str1, String str2) {
        // Check if lengths of both strings are equal
        if (str1.length() != str2.length()) {
            return false;
        }

        // Concatenate str1 with itself
        String concatenated = str1 + str1;

        // Check if str2 is a substring of the concatenated string
        return concatenated.contains(str2);
    }

    public static void main(String[] args) {
        // Example strings
        String str1 = "abcd";
        String str2 = "dabc";

        // Check if str2 is a rotation of str1
        boolean result = isRotation(str1, str2);

        // Print the result
        System.out.println("Is \"" + str2 + "\" a rotation of \"" + str1 + "\"? " + result);
    }
}
