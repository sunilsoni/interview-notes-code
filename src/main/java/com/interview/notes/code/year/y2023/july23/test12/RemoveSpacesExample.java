package com.interview.notes.code.year.y2023.july23.test12;

public class RemoveSpacesExample {
    public static void main(String[] args) {
        String input = "This is a sample string with spaces";

        String stringWithoutSpaces = removeSpaces(input);

        System.out.println("Original String: " + input);
        System.out.println("String without Spaces: " + stringWithoutSpaces);
    }

    public static String removeSpaces(String input) {
        StringBuilder result = new StringBuilder();

        for (char c : input.toCharArray()) {
            if (c != ' ') {
                result.append(c);
            }
        }

        return result.toString();
    }
}
