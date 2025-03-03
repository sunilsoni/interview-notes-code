package com.interview.notes.code.year.y2025.feb.common.test4;

public class WordSplit {
    public static String splitWords(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        int lastPos = 0;

        // Split into words while preserving delimiters
        for (int i = 0; i < input.length(); i++) {
            if (Character.isWhitespace(input.charAt(i)) || !Character.isLetter(input.charAt(i))) {
                // Process the word before the delimiter
                if (i > lastPos) {
                    String word = input.substring(lastPos, i);
                    result.append(processWord(word));
                }
                // Add the delimiter
                result.append(input.charAt(i));
                lastPos = i + 1;
            }
        }

        // Process last word if exists
        if (lastPos < input.length()) {
            result.append(processWord(input.substring(lastPos)));
        }

        return result.toString();
    }

    private static String processWord(String word) {
        // Count only letters
        int letterCount = 0;
        for (char c : word.toCharArray()) {
            if (Character.isLetter(c)) letterCount++;
        }

        // If word has even number of letters >= 4, split it
        if (letterCount >= 4 && letterCount % 2 == 0) {
            int midPoint = word.length() / 2;
            return word.substring(0, midPoint) + " " + word.substring(midPoint);
        }
        return word;
    }

    public static void main(String[] args) {
        // Test cases
        String[] tests = {
                "A dog can't walk in off the street and order a large soda.",
                "Programming is fun",
                "Ab cd efgh ijkl",
                "",
                "a",
                "abcd",
                "abc!def"
        };

        for (String test : tests) {
            System.out.println("Input:  \"" + test + "\"");
            System.out.println("Output: \"" + splitWords(test) + "\"");
            System.out.println();
        }
    }
}
