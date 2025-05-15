package com.interview.notes.code.year.y2025.may.common.test8;

import java.util.stream.*;
import java.util.function.Function;
import java.util.Map;

public class CharacterContainmentCheck {

    // Method to check if all characters in 'match' exist within 'input' with sufficient frequency
    public static boolean containsAllCharacters(String input, String match) {
        Map<Integer, Long> inputCharCount = input.chars()
                                                 .boxed()
                                                 .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        Map<Integer, Long> matchCharCount = match.chars()
                                                 .boxed()
                                                 .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        // Simple loop implementation to check character frequencies
        for (Map.Entry<Integer, Long> entry : matchCharCount.entrySet()) {
            if (inputCharCount.getOrDefault(entry.getKey(), 0L) < entry.getValue()) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // Provided test cases
        test("abcdef", "bcd", true);
        test("abcdef", "efg", false);
        test("abcdef", "fdf", false);

        // Edge cases
        test("", "", true);                   // both empty
        test("abcdef", "", true);             // empty match
        test("", "a", false);                 // empty input
        test("a", "a", true);                 // single character
        test("abcdef", "abcdefg", false);     // match longer than input

        // Large input case
        String largeInput = IntStream.rangeClosed('a', 'z')
                                .mapToObj(c -> String.valueOf((char)c).repeat(100000))
                                .collect(Collectors.joining());

        String largeMatch = "abcdefghijklmnopqrstuvwxyz";
        test(largeInput, largeMatch, true);    // large data input
    }

    // Utility method for testing and result verification
    private static void test(String input, String match, boolean expected) {
        boolean result = containsAllCharacters(input, match);
        if (result == expected) {
            System.out.println("PASS: Input='" + input + "', Match='" + match + "'");
        } else {
            System.out.println("FAIL: Input='" + input + "', Match='" + match + "', Expected=" + expected + ", Got=" + result);
        }
    }
}
