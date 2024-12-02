package com.interview.notes.code.year.y2024.may24.test11;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpecialCharacterFinder {
    public static void main(String[] args) {
        // String input = "Hello, World! 123 @#$%^&*()_+|";
        String input = " Hello, @ World!";
        // Define the regular expression pattern to match only special characters
        // Note: The regex "[^a-zA-Z0-9]" matches anything that is not
        // an alphabet letter (upper or lowercase) or digit.
        String regex = "[^a-zA-Z0-9 ]";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            System.out.println("Found special character '" + matcher.group() + "' at index " + matcher.start());
        }
    }
}
