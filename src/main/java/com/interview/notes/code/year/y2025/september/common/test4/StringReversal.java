package com.interview.notes.code.year.y2025.september.common.test4;

public class StringReversal {

    // Custom method to reverse a string without using built-in functions
    public static String reverse(String input) {
        // Convert the input string to character array to allow in-place modification
        char[] chars = input.toCharArray();

        // Define two pointers: one from start, one from end
        int left = 0;
        int right = chars.length - 1;

        // Swap characters until pointers meet in the middle
        while (left < right) {
            // Temporary variable to hold character at left
            char temp = chars[left];

            // Swap characters
            chars[left] = chars[right];
            chars[right] = temp;

            // Move pointers closer to the middle
            left++;
            right--;
        }

        // Create a new string from the reversed character array
        return new String(chars);
    }

    // Main method to test the reverse() function
    public static void main(String[] args) {
        // Test cases to validate correctness
        test("hello", "olleh");
        test("Java8", "8avaJ");
        test("a", "a");
        test("", "");
        test("1234567890", "0987654321");

        // Large input test
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100000; i++) sb.append("a");
        String largeInput = sb.toString();
        String reversedLarge = reverse(largeInput);
        System.out.println("Large test case PASS: " + reversedLarge.equals(largeInput));
    }

    // Helper method to check and print PASS/FAIL for each test case
    private static void test(String input, String expected) {
        String result = reverse(input);
        if (result.equals(expected)) {
            System.out.println("PASS: \"" + input + "\" -> \"" + result + "\"");
        } else {
            System.out.println("FAIL: \"" + input + "\" -> \"" + result + "\" (Expected: \"" + expected + "\")");
        }
    }
}