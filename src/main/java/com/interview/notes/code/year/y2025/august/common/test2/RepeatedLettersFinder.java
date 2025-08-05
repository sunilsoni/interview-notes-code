package com.interview.notes.code.year.y2025.august.common.test2;

import java.util.*;
import java.util.stream.*;

public class RepeatedLettersFinder {

    public static void main(String[] args) {
        // Define test cases: each entry is [input, expectedOutput]
        List<String[]> tests = Arrays.asList(
            new String[] {"This the input string", "thisn"},
            new String[] {"aabbccddeeff", "abcdef"},
            new String[] {"Hello World!", "lo"},            // 'l' appears 3×, 'o' appears 2×
            new String[] {"ABC abc ABC", "abc"},             // 'a','b','c' each 2+×
            new String[] {"No repeats here", ""}             // no letter repeats
        );

        // Run each test
        for (String[] test : tests) {
            String input = test[0];                         // the string to check
            String expected = test[1];                      // what we expect
            String actual = findRepeatedLetters(input);     // compute actual result

            // Print PASS or FAIL
            if (actual.equals(expected)) {
                System.out.println("PASS: \"" + input + "\" → \"" + actual + "\"");
            } else {
                System.out.println("FAIL: \"" + input + "\" → expected \"" 
                    + expected + "\", but got \"" + actual + "\"");
            }
        }
        
        // Example of handling a very large input (for demonstration only)
        StringBuilder large = new StringBuilder();
        for (int i = 0; i < 1_000_000; i++) {
            large.append("abcxyz");
        }
        // This will still run quickly
        System.out.println("Large input test length: " 
            + findRepeatedLetters(large.toString()).length());
    }

    /**
     * Finds letters that occur more than once in the input string,
     * returns them in the order they first appear, all in lowercase.
     */
    public static String findRepeatedLetters(String input) {
        // 1. Normalize: lowercase and keep only a–z
        String normalized = input.toLowerCase()
                                 .chars()                        // stream of codepoints
                                 .filter(Character::isLetter)   // only letters
                                 .mapToObj(c -> (char) c)       // back to Character
                                 .map(String::valueOf)           // to String for joining
                                 .collect(Collectors.joining());

        // 2. Count occurrences using a Map<Character, Long>
        Map<Character, Long> counts = normalized.chars()
            .mapToObj(c -> (char) c)
            .collect(Collectors.groupingBy(c -> c, Collectors.counting()));

        // 3. Build the result by scanning again, picking letters with count>1 once
        StringBuilder result = new StringBuilder();
        Set<Character> seen = new HashSet<>();  // to avoid duplicates in result

        for (char c : normalized.toCharArray()) {
            if (counts.get(c) > 1 && !seen.contains(c)) {
                result.append(c);               // add to output
                seen.add(c);                    // mark as added
            }
        }
        return result.toString();
    }
}