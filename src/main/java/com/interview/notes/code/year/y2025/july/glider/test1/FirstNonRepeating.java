package com.interview.notes.code.year.y2025.july.glider.test1;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FirstNonRepeating {

    /**
     * Finds the first non-repeating character in the given string.
     * @param s the input string
     * @return the first character with frequency == 1, or null if none
     */
    public static Character firstNonRepeatingChar(String s) {
        // Convert to a stream of Characters, group by character, counting occurrences
        // Use LinkedHashMap to preserve the order of first appearance
        Map<Character, Long> freq = s.chars()                              // [1] convert String to IntStream of code points
            .mapToObj(c -> (char) c)                                        // [2] box each int to Character
            .collect(Collectors.groupingBy(                                // [3] group by character
                Function.identity(),                                       //     key = the character itself
                LinkedHashMap::new,                                        //     use insertion-order map
                Collectors.counting()                                      //     downstream = count occurrences
            ));

        // Stream the entries in insertion order, find the first with count == 1
        return freq.entrySet().stream()                                    // [4] stream map entries
            .filter(e -> e.getValue() == 1L)                               // [5] keep only unique chars
            .map(Map.Entry::getKey)                                        // [6] extract the Character key
            .findFirst()                                                   // [7] pick the first one if present
            .orElse(null);                                                 // [8] or return null if none
    }

    /**
     * Simple main method to test various cases and report PASS/FAIL.
     */
    public static void main(String[] args) {
        // Define test cases: input → expected output
        Map<String, Character> tests = new LinkedHashMap<>();
        tests.put("swiss",    'w');   // w is the first char that appears only once
        tests.put("racecar",  'e');   // r(2), a(2), c(2), e(1)
        tests.put("aabbcc",   null);  // no non-repeating char
        tests.put("",         null);  // empty string
        tests.put("z",        'z');   // single char

        // Large-data test: 100k 'x' followed by one 'y'
        StringBuilder big = new StringBuilder();
        for (int i = 0; i < 100_000; i++) {
            big.append('x');
        }
        big.append('y');
        tests.put(big.toString(), 'y');

        // Run each test
        tests.forEach((input, expected) -> {
            Character result = firstNonRepeatingChar(input);              // call our method
            boolean pass = Objects.equals(result, expected);              // compare (handles null)
            System.out.printf("Input length=%6d → expected=%4s, got=%4s : %s%n",
                input.length(),
                expected,
                result,
                pass ? "PASS" : "FAIL"
            );
        });
    }
}