package com.interview.notes.code.year.y2025.august.common.test6;

import java.util.Arrays;

public class AnagramCheck {

    // Method to check if two strings are anagrams
    public static boolean isAnagram(String str1, String str2) {
        // Step 1: Remove spaces and convert to lowercase for uniformity
        str1 = str1.replaceAll("\\s", "").toLowerCase();
        str2 = str2.replaceAll("\\s", "").toLowerCase();

        // Step 2: If lengths differ, they cannot be anagrams
        if (str1.length() != str2.length()) {
            return false;
        }

        // Step 3: Convert strings to char arrays
        char[] arr1 = str1.toCharArray();
        char[] arr2 = str2.toCharArray();

        // Step 4: Sort both arrays
        Arrays.sort(arr1);
        Arrays.sort(arr2);

        // Step 5: Compare sorted arrays
        return Arrays.equals(arr1, arr2);
    }

    // Main method for testing
    public static void main(String[] args) {
        // Test Cases
        String[][] testCases = {
                {"listen", "silent"},      // Anagram
                {"triangle", "integral"},  // Anagram
                {"apple", "pale"},         // Not Anagram
                {"Debit Card", "Bad Credit"}, // Anagram (ignore spaces)
                {"Astronomer", "Moon starer"}, // Anagram
                {"Hello", "World"}         // Not Anagram
        };

        // Run all test cases
        for (String[] test : testCases) {
            String s1 = test[0];
            String s2 = test[1];
            boolean result = isAnagram(s1, s2);

            // Print PASS/FAIL result
            System.out.println("Input: \"" + s1 + "\", \"" + s2 + "\" => "
                    + (result ? "Anagram ✅" : "Not Anagram ❌"));
        }
    }
}