package com.interview.notes.code.year.y2024.april24.test3;

import java.util.regex.Pattern;

/**
 * String Challenge
 * Have the function StringChallenge (str) read str which will contain two strings separated by a space. The first string will consist of the following sets of characters: +, *, $, and {N} which is optional. The plus (+) character represents a single alphabetic character, the ($) character represents a number between 1-9, and the asterisk (*) represents a sequence of the same character of length 3 unless it is followed by {N) which represents how many characters should appear in the sequence where N will be at least 1. Your goal is to determine if the second string exactly matches the pattern of the first string in the input.
 * For example: if str is "++*(5} jtggggg" then the second string in this case does match the pattern, so your program should return the string true. If the second string does not match the pattern your program should return the string false.
 * <p>
 * <p>
 * Examples
 * Input: "+++++*abcdehhhhhh"
 * Output: false
 * Input: "$**+*{2} 9mmmrrrkbb"
 * Output: true
 * Java
 * <p>
 * WORKING
 */
class StringChallenge {

    public static String StringChallenge(String str) {
        String[] parts = str.split(" ");
        String patternStr = parts[0];
        String text = parts[1];

        // Convert the pattern into a regular expression
        String regex = buildRegex(patternStr);

        // Match the pattern with the text
        return text.matches(regex) ? "true" : "false";
    }

    // Helper function to build the regex from the pattern
    private static String buildRegex(String patternStr) {
        StringBuilder regex = new StringBuilder();

        // Loop over the pattern string
        for (int i = 0; i < patternStr.length(); i++) {
            char c = patternStr.charAt(i);
            switch (c) {
                case '+':
                    regex.append("[a-zA-Z]");
                    break;
                case '$':
                    regex.append("[1-9]");
                    break;
                case '*':
                    // Check for the presence of {N}
                    if ((i + 1 < patternStr.length()) && (patternStr.charAt(i + 1) == '{')) {
                        // Find the closing brace
                        int closingBraceIndex = patternStr.indexOf('}', i);
                        if (closingBraceIndex != -1) {
                            // Extract the number N and append a regex to match exactly N characters of any kind
                            String num = patternStr.substring(i + 2, closingBraceIndex);
                            regex.append(".{" + num + "}");
                            i = closingBraceIndex; // Skip ahead in the pattern string
                        }
                    } else {
                        // If no {N} follows, match exactly 3 characters of any kind
                        regex.append(".{3}");
                    }
                    break;
                default:
                    // For any other character, escape it as it is in the pattern
                    regex.append(Pattern.quote(Character.toString(c)));
                    break;
            }
        }

        return regex.toString();
    }

    public static void main(String[] args) {

        System.out.print(StringChallenge("+++++* abcdehhhhhh"));
        System.out.print(StringChallenge("$**+*{2} 9mmmrrrkbb"));
    }
}
