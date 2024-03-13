package com.interview.notes.code.months.march24.test11;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringOccurrence {
    public static void main(String[] args) {
        // Initialize your list of strings
        List<String> strings = List.of("apple", "banana", "apple", "orange", "banana", "apple");

        // Initialize a map to store the string and its occurrences
        Map<String, Integer> occurrences = new HashMap<>();

        // Iterate through the list of strings
        for (String string : strings) {
            // If the string is already in the map, increase its count
            if (occurrences.containsKey(string)) {
                occurrences.put(string, occurrences.get(string) + 1);
            } else {
                // If the string is not in the map, add it with a count of 1
                occurrences.put(string, 1);
            }
        }

        // Print the map to see the string occurrences
        System.out.println(occurrences);
    }
}
