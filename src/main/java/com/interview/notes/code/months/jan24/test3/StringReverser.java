package com.interview.notes.code.months.jan24.test3;

import com.interview.notes.code.array.Int;

public class StringReverser {

    public static String reverseString(String str) {
        char[] charArray = str.toCharArray();
        int left = 0;
        int right = charArray.length - 1;

        while (left < right) {
            // Swap characters at left and right pointers
            char temp = charArray[left];
            charArray[left] = charArray[right];
            charArray[right] = temp;
            
            // Move pointers towards each other
            left++;
            right--;
        }

        return new String(charArray);
    }

    public static void main(String[] args) {
        String input = "Hello, World!"; // Example input
        String reversed = reverseString(input);
        System.out.println("Reversed string: " + reversed);


    }
}
