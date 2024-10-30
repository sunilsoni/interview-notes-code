package com.interview.notes.code.months.oct24.test26;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CharacterCount {
    public static void main(String[] args) {
        String input = "hello world"; // Input string

        // Count the occurrences of each character while maintaining order
        Map<Character, Long> characterCount = input.chars() // Create an IntStream of characters
                .mapToObj(c -> (char) c) // Convert int to Character
                .filter(c -> c != ' ') // Optional: Ignore spaces
                .collect(Collectors.groupingBy(c -> c, LinkedHashMap::new, Collectors.counting())); // Group by character and count while maintaining order

        // Print the results
        characterCount.forEach((character, count) -> 
            System.out.println(character + ": " + count));
    }
}
