package com.interview.notes.code.year.y2025.april.common.test9;

import java.util.Map;
import java.util.stream.Collectors;

public class CharacterCounter {
    public static void main(String[] args) {
        // Input string
        String input = "programming";

        System.out.println("Input string: " + input);
        System.out.println("\nCharacter counts:");

        // Count characters using streams
        input.chars()
            .mapToObj(ch -> (char) ch)
            .collect(Collectors.groupingBy(
                ch -> ch,
                Collectors.counting()))
            .forEach((character, count) -> 
                System.out.println(character + "_variable = " + count));

        System.out.println("\nSorted by character:");
        // Sort by character
        input.chars()
            .mapToObj(ch -> (char) ch)
            .collect(Collectors.groupingBy(
                ch -> ch,
                Collectors.counting()))
            .entrySet()
            .stream()
            .sorted(Map.Entry.comparingByKey())
            .forEach((entry) -> 
                System.out.println(entry.getKey() + "_variable = " + entry.getValue()));

        System.out.println("\nSorted by count (descending):");
        // Sort by count
        input.chars()
            .mapToObj(ch -> (char) ch)
            .collect(Collectors.groupingBy(
                ch -> ch,
                Collectors.counting()))
            .entrySet()
            .stream()
            .sorted(Map.Entry.<Character, Long>comparingByValue().reversed())
            .forEach((entry) -> 
                System.out.println(entry.getKey() + "_variable = " + entry.getValue()));
    }
}
