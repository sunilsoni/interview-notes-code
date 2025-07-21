package com.interview.notes.code.year.y2025.july.common.test1;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FirstNonRepeatingChar { // Declare the class
    public static void main(String[] args) { // Main method: entry point of the program
        String input = "swiss"; // Input string; change this value to test other inputs

        // Stream processing to find the first non-repeating character
        Optional<Character> result = input.chars() // Create an IntStream of Unicode code points from the string
                .mapToObj(c -> (char) c) // Convert each int code point to its corresponding Character object
                .collect(Collectors.groupingBy( // Collect into a Map<Character, Long>
                        Function.identity(), // The key mapper: the character itself
                        LinkedHashMap::new, // Use LinkedHashMap to preserve the insertion order of characters
                        Collectors.counting() // Downstream collector: count the occurrences of each character
                ))
                .entrySet().stream() // Create a Stream of Map entries (character -> count)
                .filter(e -> e.getValue() == 1) // Keep only entries where count == 1 (non-repeating)
                .map(Map.Entry::getKey) // Extract the character key from each entry
                .findFirst(); // Retrieve the first matching character as an Optional

        // Print the result; orElse(null) returns null if Optional is empty
        System.out.println("First non-repeating character: " + result.orElse(null));
    }
}