package com.interview.notes.code.year.y2025.may.common.test5;

import java.util.*;
import java.util.stream.*;

public class TextFormatter {

    // Formats lines with full justification
    public static List<String> formatText(List<String> words, int width) {
        List<String> lines = new ArrayList<>();
        List<String> currentWords = new ArrayList<>();
        int currentLen = 0;

        for (String word : words) {
            if (currentLen + currentWords.size() + word.length() > width) {
                lines.add(justify(currentWords, width, false));
                currentWords = new ArrayList<>();
                currentLen = 0;
            }
            currentWords.add(word);
            currentLen += word.length();
        }

        if (!currentWords.isEmpty()) {
            lines.add(justify(currentWords, width, true));
        }

        return lines;
    }

    // Justifies one line of words
    private static String justify(List<String> words, int width, boolean isLastLine) {
        int totalWordLen = words.stream().mapToInt(String::length).sum();
        int numWords = words.size();

        // Single word or last line: left-justified with trailing spaces
        if (numWords == 1 || isLastLine) {
            String line = String.join(" ", words);
            int remainingSpaces = width - line.length();
            if (remainingSpaces < 0) remainingSpaces = 0; // safeguard
            return line + " ".repeat(remainingSpaces);
        }

        int totalSpaces = width - totalWordLen;
        int gaps = numWords - 1;
        int evenSpace = totalSpaces / gaps;
        int extra = totalSpaces % gaps;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numWords; i++) {
            sb.append(words.get(i));
            if (i < gaps) {
                int spaceCount = evenSpace + (i < extra ? 1 : 0);
                sb.append(" ".repeat(spaceCount));
            }
        }
        return sb.toString();
    }

    // Method to print formatted lines
    public static void printFormatted(List<String> lines) {
        lines.forEach(System.out::println);
    }

    private static void verifyTest(List<String> result, List<String> expected, int testNumber) {
        if (result.equals(expected)) {
            System.out.println("Test " + testNumber + ": PASS");
        } else {
            System.out.println("Test " + testNumber + ": FAIL");
            System.out.println("Expected: " + expected);
            System.out.println("Got:      " + result);
        }
    }

    public static void main(String[] args) {
        List<String> words1 = Arrays.asList("My", "cherry", "Popsicle", "melted", "and", "fell", "on", "the", "sandy", "ground");

        int width1 = 20;
        List<String> expected1 = Arrays.asList(
            "My  cherry  Popsicle",
            "melted  and  fell on",
            "the   sandy   ground"
        );
        List<String> result1 = formatText(words1, width1);
        verifyTest(result1, expected1, 1);
        printFormatted(result1);

        int width2 = 25;
        List<String> expected2 = Arrays.asList(
            "My cherry Popsicle melted",
            "and  fell  on the sandy",
            "ground                     "
        );
        List<String> result2 = formatText(words1, width2);
        verifyTest(result2, expected2, 2);
        printFormatted(result2);

        List<String> words3 = Collections.singletonList("supercalifragilisticexpialidocious");
        int width3 = 50;
        List<String> expected3 = Collections.singletonList("supercalifragilisticexpialidocious               ");
        List<String> result3 = formatText(words3, width3);
        verifyTest(result3, expected3, 3);
        printFormatted(result3);

        List<String> largeWords = IntStream.range(0, 10000)
                .mapToObj(i -> "word" + i)
                .collect(Collectors.toList());
        int widthLarge = 50;
        List<String> largeResult = formatText(largeWords, widthLarge);
        System.out.println("Large test completed. Lines created: " + largeResult.size());
    }
}
