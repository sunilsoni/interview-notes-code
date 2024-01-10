package com.interview.notes.code.months.year2023.Aug23.test6;

import java.util.ArrayList;
import java.util.List;

public class WordWrapper {

    public static List<String> wrapLines(List<String> words, int maxLen) {
        List<String> result = new ArrayList<>();
        StringBuilder currentLine = new StringBuilder();

        for (String word : words) {
            // If the word itself is longer than maxLen
            if (word.length() > maxLen) {
                if (currentLine.length() > 0) {
                    result.add(currentLine.toString());
                    currentLine = new StringBuilder();
                }
                result.add(word);
                continue;
            }

            if (currentLine.length() + word.length() + (currentLine.length() > 0 ? 1 : 0) <= maxLen) {
                if (currentLine.length() != 0) {
                    currentLine.append('-'); // append space as '-'
                }
                currentLine.append(word);
            } else {
                result.add(currentLine.toString());
                currentLine = new StringBuilder(word);
            }
        }

        if (currentLine.length() > 0) {
            result.add(currentLine.toString());
        }

        return result;
    }

    public static void main(String[] args) {
        List<String> words1 = List.of("The", "day", "began", "as", "still", "as", "the",
                "night", "abruptly", "lighted", "with", "brilliant", "flame");
        System.out.println(wrapLines(words1, 13));
        // ... other test cases as earlier
    }
}
