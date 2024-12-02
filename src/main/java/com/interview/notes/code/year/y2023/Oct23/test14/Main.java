package com.interview.notes.code.year.y2023.Oct23.test14;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        String input = "hello world";

        // Use Java 8 Stream API to find duplicate characters and their counts
        Map<Character, Long> charCounts = input.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        // Print the duplicate characters and their counts
        charCounts.forEach((character, count) -> System.out.println(character + ": " + count));
    }
}
