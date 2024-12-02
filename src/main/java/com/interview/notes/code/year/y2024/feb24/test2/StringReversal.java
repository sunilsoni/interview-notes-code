package com.interview.notes.code.year.y2024.feb24.test2;

public class StringReversal {
    public static void main(String[] args) {
        String input = "welcome";
        String reversed = reverseString(input);
        System.out.println("Reversed string: " + reversed);
    }

    public static String reverseString(String str) {
        // Convert the string to a character array
        char[] chars = str.toCharArray();

        // Initialize pointers for swapping
        int start = 0;
        int end = chars.length - 1;

        // Swap characters until reaching the middle
        while (start < end) {
            // Swap characters at start and end indices
            char temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;

            // Move pointers towards the middle
            start++;
            end--;
        }

        // Convert the character array back to a string
        return new String(chars);
    }
}
