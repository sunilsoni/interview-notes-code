package com.interview.notes.code.year.y2024.april24.test8;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * If all letter from S1 matches with S2 then return "True" else true "False". Don't sort chracters.
 * String 1 = ("INDIA")
 * String 2 = ("NDIAI")
 */
public class AreCharactersSame {
    public static void main(String[] args) {
        String s1 = "INDIA";
        String s2 = "NDIAI";

        boolean result = areCharactersSame(s1, s2);
        System.out.println(result); // Output: true
    }

    public static boolean areCharactersSame(String s1, String s2) {
        // Check if both strings have the same length
        if (s1.length() != s2.length()) {
            return false;
        }

        // Count characters in both strings
        Map<Character, Long> s1CharCounts = s1.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        Map<Character, Long> s2CharCounts = s2.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        // Compare character counts
        return s1CharCounts.equals(s2CharCounts);
    }
}
