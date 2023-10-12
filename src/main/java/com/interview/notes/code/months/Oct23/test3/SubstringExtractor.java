package com.interview.notes.code.months.Oct23.test3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SubstringExtractor {
    public static void main(String[] args) {
        String input = "This is a sample string containing a target substring in brackets [target_substring].";

        // Define a regular expression pattern to match the target substring within brackets.
        String patternString = "\\[([^\\]]+)\\]";

        // Create a Pattern object.
        Pattern pattern = Pattern.compile(patternString);

        // Create a Matcher object to find the substring matching the pattern.
        Matcher matcher = pattern.matcher(input);

        // Find the first match.
        if (matcher.find()) {
            // Extract the matched substring.
            String matchedSubstring = matcher.group(1);
            System.out.println("Matched Substring: " + matchedSubstring);
        } else {
            System.out.println("No match found.");
        }
    }
}
