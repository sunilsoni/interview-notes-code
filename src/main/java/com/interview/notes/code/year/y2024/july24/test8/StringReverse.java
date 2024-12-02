package com.interview.notes.code.year.y2024.july24.test8;

public class StringReverse {

    public static void main(String[] args) {
        String input = "Hello, World!";
        String reversed = reverseString(input);
        System.out.println("Original: " + input);
        System.out.println("Reversed: " + reversed);
    }

    public static String reverseString(String str) {
        // Convert the string to a character array
        char[] charArray = str.toCharArray();

        // Initialize pointers for the beginning and end of the array
        int start = 0;
        int end = charArray.length - 1;

        // Swap characters from start to end
        while (start < end) {
            // Swap characters
            char temp = charArray[start];
            charArray[start] = charArray[end];
            charArray[end] = temp;

            // Move pointers towards the center
            start++;
            end--;
        }

        // Convert character array back to string and return
        return new String(charArray);
    }
}


