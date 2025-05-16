package com.interview.notes.code.year.y2025.may.common.test7;

import java.util.*;
import java.util.stream.*;

public class TextFormatter {

    // Method to format words into lines not exceeding the specified width
    public static List<String> formatText(List<String> words, int width) {
        List<String> lines = new ArrayList<>();
        StringBuilder currentLine = new StringBuilder();

        // Iterate through each word to construct lines
        for (String word : words) {
            if (currentLine.length() == 0) {
                // If current line is empty, add the word directly
                currentLine.append(word);
            } else if (currentLine.length() + word.length() + 1 <= width) {
                // Append the word if it fits in the current line with a space
                currentLine.append(" ").append(word);
            } else {
                // Current line is full, add it to lines and start a new one
                lines.add(currentLine.toString());
                currentLine = new StringBuilder(word);
            }
        }

        // Add last line if not empty
        if (currentLine.length() > 0) {
            lines.add(currentLine.toString());
        }

        return lines;
    }

    // Method to print formatted lines
    public static void printFormatted(List<String> lines, int width) {
        lines.forEach(line -> {
            System.out.print(line);
            // Print extra spaces at end for exact width formatting (optional)
            IntStream.range(0, width - line.length()).forEach(i -> System.out.print(" "));
            System.out.println();
        });
    }

    // Simple method to verify test results
    private static void verifyTest(List<String> result, List<String> expected, int testNumber) {
        if (result.equals(expected)) {
            System.out.println("Test " + testNumber + ": PASS");
        } else {
            System.out.println("Test " + testNumber + ": FAIL");
            System.out.println("Expected: " + expected);
            System.out.println("Got: " + result);
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        // Test case 1
        List<String> words1 = Arrays.asList("My", "cherry", "Popsicle", "melted", "and", "fell", "on", "the", "sandy", "ground");
        int width1 = 20;
        List<String> expected1 = Arrays.asList(
            "My cherry Popsicle",
            "melted and fell on",
            "the sandy ground"
        );
        List<String> result1 = formatText(words1, width1);
        verifyTest(result1, expected1, 1);
        printFormatted(result1, width1);

        // Test case 2
        int width2 = 25;
        List<String> expected2 = Arrays.asList(
            "My cherry Popsicle melted",
            "and fell on the sandy",
            "ground"
        );
        List<String> result2 = formatText(words1, width2);
        verifyTest(result2, expected2, 2);
        printFormatted(result2, width2);

        // Edge case: Single long word
        List<String> words3 = Collections.singletonList("supercalifragilisticexpialidocious");
        int width3 = 10;
        List<String> expected3 = Collections.singletonList("supercalifragilisticexpialidocious");
        List<String> result3 = formatText(words3, width3);
        verifyTest(result3, expected3, 3);
        printFormatted(result3, width3);

        // Large data test
        List<String> largeWords = IntStream.range(0, 10000)
                .mapToObj(i -> "word" + i)
                .collect(Collectors.toList());
        int widthLarge = 50;
        List<String> largeResult = formatText(largeWords, widthLarge);
        System.out.println("Large test completed. Lines created: " + largeResult.size());
    }
}