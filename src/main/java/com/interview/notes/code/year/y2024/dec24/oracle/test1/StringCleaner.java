package com.interview.notes.code.year.y2024.dec24.oracle.test1;

public class StringCleaner {

    // Main method to test the stringClean function
    public static void main(String[] args) {
        StringCleaner cleaner = new StringCleaner();

        // Test cases
        System.out.println("Test Case 1: " + cleaner.stringClean("yyyzzz")); // Expected: "yza"
        System.out.println("Test Case 2: " + cleaner.stringClean("abbccdd")); // Expected: "abcd"
        System.out.println("Test Case 3: " + cleaner.stringClean("Hello"));  // Expected: "Helo"
        System.out.println("Test Case 4: " + cleaner.stringClean(""));       // Expected: ""
        System.out.println("Test Case 5: " + cleaner.stringClean("a"));      // Expected: "a"
        System.out.println("Test Case 6: " + cleaner.stringClean("aabbccaa")); // Expected: "abc"
        System.out.println("Test Case 7: " + cleaner.stringClean("aaaabbbbbccccddddd")); // Expected: "abcd"
    }

    // Recursive function to clean the string
    public String stringClean(String str) {
        // Base case: if the string is empty or has only one character, return it as is
        if (str.length() <= 1) {
            return str;
        }

        // Recursive case: compare first character with the next one
        if (str.charAt(0) == str.charAt(1)) {
            // Skip the next character if they are the same
            return stringClean(str.substring(1));
        } else {
            // Include the first character and recursively clean the rest
            return str.charAt(0) + stringClean(str.substring(1));
        }
    }
}
