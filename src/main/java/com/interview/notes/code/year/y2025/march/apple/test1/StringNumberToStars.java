package com.interview.notes.code.year.y2025.march.apple.test1;

import java.util.Arrays;
import java.util.stream.Collectors;

public class StringNumberToStars {
    public static void main(String[] args) {
        String input = "abc2de5fg4hi7jk1Im9nopqrstuvwxyz";
        
        // Solution 1: Using Stream and collectors
        String output = input.chars()
            .mapToObj(ch -> Character.isDigit(ch) 
                ? "*".repeat(Character.getNumericValue(ch))
                : String.valueOf((char)ch))
            .collect(Collectors.joining());
        
        System.out.println("Input: " + input);
        System.out.println("Output: " + output);

        // Solution 2: Using Stream with regular expression
        String output2 = Arrays.stream(input.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)"))
            .map(s -> s.matches("\\d") 
                ? "*".repeat(Integer.parseInt(s))
                : s)
            .collect(Collectors.joining());

        System.out.println("Output (Method 2): " + output2);
    }
}
