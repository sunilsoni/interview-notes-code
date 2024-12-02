package com.interview.notes.code.year.y2024.april24.test4;

import java.util.Map;
import java.util.stream.Collectors;

public class CharacterCounter {
    public static void main(String[] args) {
        String input = "Testing";

        Map<Character, Long> characterCounts = input.chars()
                .mapToObj(c -> (char) c) // Convert int to char
                .collect(Collectors.groupingBy(c -> c, Collectors.counting())); // Group and count characters

        characterCounts.forEach((character, count) ->
                System.out.println(character + ": " + count));
    }
}
