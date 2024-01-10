package com.interview.notes.code.months.year2023.july23.test12;

import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        String input = "rollercoaster";

        Map<Character, Long> charCounts = input.chars() // Convert the string to IntStream
                .mapToObj(c -> (char) c) // Convert IntStream to Stream<Character>
                .collect(Collectors.groupingBy(
                        c -> c, Collectors.counting() // Group by character and count them
                ));

        charCounts.entrySet().stream()
                .filter(entry -> entry.getValue() > 1) // Filter entries with count > 1
                .forEach(entry -> {
                    System.out.println(entry.getKey() + " - " + entry.getValue());
                });
    }
}
