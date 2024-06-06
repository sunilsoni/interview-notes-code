package com.interview.notes.code.months.may24.test12;

import java.util.stream.Collectors;

public class Main1 {
    public static void main(String[] args) {
        String input = "aaabbcddefgggh";
        String result = removeDuplicates(input);
        System.out.println(result); // Output: abcdefgh
    }

    public static String removeDuplicates(String input) {
        return input.chars()                // Convert input string to IntStream of character codes
                .distinct()            // Remove duplicate character codes
                .mapToObj(ch -> (char) ch) // Convert character codes back to characters
                .map(String::valueOf) // Convert characters to strings
                .collect(Collectors.joining()); // Collect the strings into a single string
    }
}
