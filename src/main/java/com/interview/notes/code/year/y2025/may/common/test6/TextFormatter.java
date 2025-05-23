package com.interview.notes.code.year.y2025.may.common.test6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        if (words.size() == 1 || isLastLine) {
            // Left align for last line or single word
            return String.join(" ", words) + " ".repeat(width - (words.stream().mapToInt(String::length).sum() + words.size() - 1));
        }

        int totalWordLen = words.stream().mapToInt(String::length).sum();
        int spaces = width - totalWordLen;
        int gaps = words.size() - 1;
        int evenSpace = spaces / gaps;
        int extra = spaces % gaps;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.size(); i++) {
            sb.append(words.get(i));
            if (i < gaps) {
                sb.append(" ".repeat(evenSpace + (i < extra ? 1 : 0)));
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
                "ground"
        );
        List<String> result2 = formatText(words1, width2);
        verifyTest(result2, expected2, 2);
        printFormatted(result2);

        List<String> words3 = Collections.singletonList("supercalifragilisticexpialidocious");
        int width3 = 10;
        List<String> expected3 = Collections.singletonList("supercalifragilisticexpialidocious");
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
