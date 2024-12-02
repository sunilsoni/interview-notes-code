package com.interview.notes.code.year.y2024.march24.test11;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class StringOccurrenceUsingMerge {
    public static void main(String[] args) {
        // Initialize your list of strings
        List<String> strings = List.of("apple", "banana", "apple", "orange", "banana", "apple");


        // Initialize a map to store the string and its occurrences
        Map<String, Integer> occurrences = new HashMap<>();


        // Iterate through the list of strings
        for (String string : strings) {
            occurrences.merge(string, 1, Integer::sum);
        }


        // Print the map to see the string occurrences
        System.out.println(occurrences);
    }
}
