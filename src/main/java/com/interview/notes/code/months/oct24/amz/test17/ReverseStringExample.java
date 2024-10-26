package com.interview.notes.code.months.oct24.amz.test17;

public class ReverseStringExample {
    public static void main(String[] args) {
        String input = "Hello, World!";
        String reversed = reverseString(input);
        System.out.println("Original: " + input);
        System.out.println("Reversed: " + reversed);
    }

    public static String reverseString(String str) {
        // Convert string to character array
        char[] charArray = str.toCharArray();
        int left = 0;                      // Pointer at the beginning
        int right = charArray.length - 1;  // Pointer at the end

        // Swap characters until the two pointers meet
        while (left < right) {
            // Swap characters
            char temp = charArray[left];
            charArray[left] = charArray[right];
            charArray[right] = temp;

            // Move the pointers
            left++;
            right--;
        }

        // Convert character array back to string
        return new String(charArray);
    }
}
