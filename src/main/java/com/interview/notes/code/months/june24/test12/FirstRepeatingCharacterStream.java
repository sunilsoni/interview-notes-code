package com.interview.notes.code.months.june24.test12;

import java.util.HashSet;
import java.util.Optional;

public class FirstRepeatingCharacterStream {

    public static Optional<Character> findFirstRepeatingCharacter(String str) {
        HashSet<Character> seenCharacters = new HashSet<>();

        return str.chars()
                .mapToObj(c -> (char) c)
                .filter(c -> !seenCharacters.add(c))
                .findFirst();
    }

    public static void main(String[] args) {
        String input = "swiss";
        Optional<Character> result = findFirstRepeatingCharacter(input);

        result.ifPresentOrElse(
                ch -> System.out.println("First repeating character: " + ch),
                () -> System.out.println("No repeating characters found.")
        );
    }
}
