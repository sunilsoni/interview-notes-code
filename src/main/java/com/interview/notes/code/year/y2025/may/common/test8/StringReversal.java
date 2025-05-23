package com.interview.notes.code.year.y2025.may.common.test8;

public class StringReversal {

    // Method to reverse a string using two pointers approach
    public static String reverseString(String input) {
        // Handle null input
        if (input == null) return null;

        // Convert string to char array for easier manipulation
        char[] charArray = input.toCharArray();

        // Initialize two pointers
        int left = 0;                    // Points to start of string
        int right = charArray.length - 1; // Points to end of string

        // Continue until pointers meet in middle
        while (left < right) {
            // Swap characters at left and right pointers
            char temp = charArray[left];
            charArray[left] = charArray[right];
            charArray[right] = temp;

            // Move pointers towards center
            left++;
            right--;
        }

        // Convert char array back to string
        return new String(charArray);
    }

    // Main method for testing
    public static void main(String[] args) {
        // Test cases
        testReverseString("hello", "olleh");
        testReverseString("", "");
        testReverseString("a", "a");
        testReverseString("12345", "54321");
        testReverseString(null, null);
        testReverseString("Hello World!", "!dlroW olleH");

        // Large input test
        StringBuilder largeInput = new StringBuilder();
        for (int i = 0; i < 1000000; i++) {
            largeInput.append('a');
        }
        String largeInputStr = largeInput.toString();
        long startTime = System.currentTimeMillis();
        reverseString(largeInputStr);
        long endTime = System.currentTimeMillis();
        System.out.println("Large input (1M chars) processing time: " +
                (endTime - startTime) + "ms");
    }

    // Helper method to test and print results
    private static void testReverseString(String input, String expected) {
        String result = reverseString(input);
        boolean passed = (result == null && expected == null) ||
                (result != null && result.equals(expected));

        System.out.println("Test Case: " + input);
        System.out.println("Expected: " + expected);
        System.out.println("Result: " + result);
        System.out.println("Status: " + (passed ? "PASS" : "FAIL"));
        System.out.println("------------------------");
    }
}
