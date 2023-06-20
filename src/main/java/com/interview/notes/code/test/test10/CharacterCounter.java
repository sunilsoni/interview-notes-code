package com.interview.notes.code.test.test10;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CharacterCounter {
    public static void main(String[] args) {
        String input = "Hello, World!";
        Map<Character, Long> characterCount = countCharacters(input);
        printCharacterCount(characterCount);
    }

    public static Map<Character, Long> countCharacters(String input) {
        return input.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public static void printCharacterCount(Map<Character, Long> characterCount) {
        characterCount.forEach((character, count) ->
                System.out.println(character + ": " + count));
    }
}

