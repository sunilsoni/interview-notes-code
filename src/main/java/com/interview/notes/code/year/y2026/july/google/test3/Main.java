package com.interview.notes.code.year.y2026.july.google.test3;

import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        // Simple main method testing as requested
        printMaxCharAndCount("Pavani Gorlangunta"); // Expected: 'a' | Count: 4
        printMaxCharAndCount("Ppavani"); // Expected: 'p' or 'a' | Count: 2 (depending on case sensitivity)
        printMaxCharAndCount("aabbcc"); // Expected: 'a' (handles ties by picking the first max encountered)
    }

    static void printMaxCharAndCount(String str) {
        if (str == null || str.isBlank()) return; // Guard clause for null or empty strings

        str.replace(" ", "").chars() // Remove spaces to only count letters, then stream chars
            .mapToObj(c -> (char) c) // Box primitive ints into Character objects
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())) // Map each char to its frequency count
            .entrySet().stream() // Stream the resulting map entries (Key: Char, Value: Count)
            .max(Entry.comparingByValue()) // Isolate the single entry with the highest count
            .ifPresent(max -> System.out.printf("Character: '%s' | Count: %d%n", max.getKey(), max.getValue())); // Print output
    }
}