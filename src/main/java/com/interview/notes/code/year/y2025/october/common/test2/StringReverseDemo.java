package com.interview.notes.code.year.y2025.october.common.test2;

public class StringReverseDemo {
    public static void main(String[] args) {
        // Test Case 1: Regular string
        String s = "welcome";
        System.out.println("Test Case 1:");
        System.out.println("Original: " + s);
        System.out.println("Reversed: " + reverseString(s));
        System.out.println("Status: " + ("emoclew".equals(reverseString(s)) ? "PASS" : "FAIL"));

        // Test Case 2: Empty string
        System.out.println("\nTest Case 2:");
        testReverseString("");

        // Test Case 3: Single character
        System.out.println("\nTest Case 3:");
        testReverseString("a");

        // Test Case 4: Palindrome
        System.out.println("\nTest Case 4:");
        testReverseString("madam");
    }

    // Method to reverse string using two pointers
    public static String reverseString(String s) {
        // Convert string to char array as strings are immutable in Java
        char[] charArray = s.toCharArray();
        
        // Initialize two pointers
        int left = 0;                    // Points to start of string
        int right = charArray.length - 1; // Points to end of string

        // Continue until pointers meet
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

    // Helper method to test and display results
    private static void testReverseString(String input) {
        String reversed = reverseString(input);
        System.out.println("Original: '" + input + "'");
        System.out.println("Reversed: '" + reversed + "'");
        
        // Verify by manually reversing to check
        StringBuilder sb = new StringBuilder(input);
        String expected = sb.reverse().toString();
        System.out.println("Status: " + (expected.equals(reversed) ? "PASS" : "FAIL"));
    }
}



