package com.interview.notes.code.year.y2024.june24.test1;

public class StringRotationOverCharacter {

    // Method to rotate the string over the specified character
    public static String rotateOverCharacter(String str, char ch) {
        // Find the index of the specified character
        int index = str.indexOf(ch);

        // If the character is not found, return the original string
        if (index == -1) {
            return str;
        }

        // Split the string at the index of the character
        String part1 = str.substring(index);
        String part2 = str.substring(0, index);

        // Concatenate the two parts in reverse order
        return part1 + part2;
    }

    public static void main(String[] args) {
        // Example input
        String str = "abcd";
        char ch = 'd';

        // Rotate the string over the specified character
        String rotatedString = rotateOverCharacter(str, ch);

        // Print the result
        System.out.println("Rotated string: " + rotatedString);
    }
}
