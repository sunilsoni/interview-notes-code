package com.interview.notes.code.character;

/**
 * This code uses an array of size 256 to store the count of each character in the string. The first for loop initializes the count of each character to 0, and the second for loop increments the count of each character. The third for loop iterates over the string again and finds the first non-repeating character by checking if the count of a character is equal to 1.
 */
public class SecondNonRepeatingChar {
    public static Character secondNonRepeatingChar(String str) {
        // Create an array of size 256 to store the count of characters
        int[] count = new int[256];

        // Initialize the count of each character to 0
        for (int i = 0; i < 256; i++) {
            count[i] = 0;
        }

        // Increment the count of each character in the string
        for (int i = 0; i < str.length(); i++) {
            count[str.charAt(i)]++;
        }

        // Iterate over the string again and find the first non-repeating character
        for (int i = 0; i < str.length(); i++) {
            if (count[str.charAt(i)] == 1) {
                return str.charAt(i);
            }
        }

        return null;
    }

}
