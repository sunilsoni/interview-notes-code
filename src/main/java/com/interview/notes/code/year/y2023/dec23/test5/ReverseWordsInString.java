package com.interview.notes.code.year.y2023.dec23.test5;

/**
 * Write a program in Java to reverse the order of words in a string without using the reverse)
 * method.
 */
public class ReverseWordsInString {
    public static void main(String[] args) {
        String inputString = "Hello World Java Example"; // Replace with your input string
        String reversedString = reverseWords(inputString);
        System.out.println("Original String: " + inputString);
        System.out.println("Reversed String: " + reversedString);
    }

    public static String reverseWords(String input) {
        // Split the input string into words using space as the delimiter
        String[] words = input.split(" ");
        StringBuilder reversedString = new StringBuilder();

        // Iterate through the words in reverse order and append them to the reversedString
        for (int i = words.length - 1; i >= 0; i--) {
            reversedString.append(words[i]);
            if (i > 0) {
                // Add a space between words, except for the last word
                reversedString.append(" ");
            }
        }

        return reversedString.toString();
    }
}
