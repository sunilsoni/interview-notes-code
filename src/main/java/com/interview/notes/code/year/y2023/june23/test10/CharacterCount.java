package com.interview.notes.code.year.y2023.june23.test10;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CharacterCount {
    public static void main(String[] args) {
        String input = "Hello, World!";
        Map<Character, Long> characterCount = countCharacters(input);

        // Print the character count
        characterCount.forEach((character, count) -> System.out.println(character + ": " + count));
    }

    public static Map<Character, Long> countCharacters(String input) {
        return input.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }
}
