package com.interview.notes.code.year.y2024.may24.test11;

public class Main2 {
    public static void main(String[] args) {
        // Step 1: Define the string variable
        String text = "This is a string with special characters: !@#$%^&*()";

        // Step 2: Iterate through the string
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            // Step 3: Check if it's a special character and print it
            if (isSpecialCharacter(ch)) {
                System.out.println("Special character found: " + ch);
            }
        }
    }

    // Method to check if a character is a special character
    private static boolean isSpecialCharacter(char ch) {
        // You can define your own set of special characters here
        String specialCharacters = "!@#$%^&*()";

        // Check if the character is present in the specialCharacters string
        return specialCharacters.indexOf(ch) != -1;
    }
}
