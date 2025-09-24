package com.interview.notes.code.year.y2025.september.EntityData.test2;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DuplicateChars {
    public static void main(String[] args) {
        String s = "programming";

        // 1. Convert to a stream of Character
        // 2. Group by character and count occurrences
        // 3. Filter entries with count > 1
        // 4. Collect the characters into a list
        List<Character> duplicates = s.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        // Print the result
        System.out.println("Repeating chars: " + duplicates);
    }
}