package com.interview.notes.code.months.year2023.Aug23.test2;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class FindFirstNonRepeatingChar {
    public static void main(String[] args) {
        String input = "aabbccdeeff";

        Optional<Character> firstNonRepeatingChar = findFirstNonRepeatingChar(input);
        if (firstNonRepeatingChar.isPresent()) {
            System.out.println("First non-repeating character: " + firstNonRepeatingChar.get());
        } else {
            System.out.println("No non-repeating character found.");
        }
    }

    public static Optional<Character> findFirstNonRepeatingChar(String input) {
        return input.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(c -> c, LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst();
    }

}

