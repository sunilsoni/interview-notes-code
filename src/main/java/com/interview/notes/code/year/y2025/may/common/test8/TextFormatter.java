package com.interview.notes.code.year.y2025.may.common.test8;

import java.util.*;

public class TextFormatter {
    public static List<String> formatText(String[] words, int width) {
        List<String> result = new ArrayList<>();
        StringBuilder currentLine = new StringBuilder();
        int currentLineLength = 0;

        // Process each word
        for (String word : words) {
            // Check if adding new word exceeds width
            if (currentLineLength + word.length() + 
                (currentLineLength > 0 ? 1 : 0) > width) {
                // Add padding spaces to match width
                while (currentLine.length() < width) {
                    currentLine.append(" ");
                }
                result.add(currentLine.toString());
                currentLine = new StringBuilder();
                currentLineLength = 0;
            }

            // Add space between words if needed
            if (currentLineLength > 0) {
                currentLine.append(" ");
                currentLineLength++;
            }

            // Add the word
            currentLine.append(word);
            currentLineLength += word.length();
        }

        // Handle last line
        while (currentLine.length() < width) {
            currentLine.append(" ");
        }
        result.add(currentLine.toString());

        return result;
    }

    public static void main(String[] args) {
        // Test Case 1
        String[] words1 = {"My", "cherry", "Popsicle", "melted", 
                          "and", "fell", "on", "the", "sandy", "ground"};
        int width1 = 20;
        
        System.out.println("Test Case 1:");
        List<String> result1 = formatText(words1, width1);
        result1.forEach(System.out::println);
        
        System.out.println("\nTest Case 2:");
        // Test Case 2
        int width2 = 25;
        List<String> result2 = formatText(words1, width2);
        result2.forEach(System.out::println);

        // Additional test cases for edge scenarios
        System.out.println("\nEdge Case - Single word per line:");
        String[] words3 = {"Test", "of", "small", "words"};
        List<String> result3 = formatText(words3, 5);
        result3.forEach(System.out::println);
    }
}
