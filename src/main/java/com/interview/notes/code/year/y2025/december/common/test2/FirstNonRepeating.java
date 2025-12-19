package com.interview.notes.code.year.y2025.december.common.test2;

public class FirstNonRepeating {

    public static void main(String[] args) {

        String s = "aaabcccd";                // Given input string

        int[] count = new int[256];           // Array to store count of each character (ASCII range)

        for (char c : s.toCharArray()) {      // Convert string to char array and loop
            count[c]++;                       // Increase count for each character
        }

        char result = '\0';                   // Variable to store answer (null char means not found)

        for (char c : s.toCharArray()) {      // Loop again to maintain original order
            if (count[c] == 1) {              // Check if character appears only once
                result = c;                   // Store first non-repeating character
                break;                        // Stop loop once found
            }
        }

        if (result != '\0')                   // Check if result exists
            System.out.println("PASS : " + result); // Print result
        else
            System.out.println("FAIL : No non-repeating character");
    }
}
