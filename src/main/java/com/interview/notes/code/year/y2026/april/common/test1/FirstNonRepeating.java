package com.interview.notes.code.year.y2026.april.common.test1;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FirstNonRepeating {
    public static void main(String[] args) {
        String str = "java program";

        Character result = str.chars()           // IntStream
            .mapToObj(c -> (char) c)             // Stream<Character>
            .collect(Collectors.groupingBy(      // Group and count
                Function.identity(), 
                LinkedHashMap::new,              // Maintain order
                Collectors.counting()
            ))
            .entrySet().stream()
            .filter(entry -> entry.getValue() == 1)
            .map(Map.Entry::getKey)
            .findFirst()
            .orElse(null);

        System.out.println("First non-repeating character: " + result);
    }
}