package com.interview.notes.code.months.year2023.Oct23.test2;

import java.util.HashSet;
import java.util.Optional;

public class FindFirstRepeatedChar {
    public static void main(String[] args) {
        String input = "Hello World";
        Optional<Character> firstRepeatedChar = findFirstRepeatedChar(input);
        firstRepeatedChar.ifPresent(charFound -> System.out.println("First repeated character: " + charFound));
    }

    public static Optional<Character> findFirstRepeatedChar(String input) {
        HashSet<Character> seen = new HashSet<>();
        return input.chars()
                .mapToObj(c -> (char) c)
                .filter(c -> !seen.add(c))
                .findFirst();
    }
}
