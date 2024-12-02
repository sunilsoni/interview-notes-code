package com.interview.notes.code.year.y2023.july23.test8;

import java.util.Map;
import java.util.stream.Collectors;

public class DuplicateCharacterCounter {
    public static void main(String[] args) {
        String inputString = "hello world";

        // Step 1: Convert the input string to lowercase (optional, to ignore case sensitivity).
        inputString = inputString.toLowerCase();

        // Step 2: Create a frequency map of characters using streams.
        Map<Character, Long> charFrequencyMap = inputString.chars()
                .mapToObj(c -> (char) c)
                .filter(Character::isLetter)
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));

        // Step 3: Print characters with count greater than 1 (i.e., duplicates).
        charFrequencyMap.entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .forEach(entry -> System.out.println("Character: " + entry.getKey() + ", Count: " + entry.getValue()));
    }
}
