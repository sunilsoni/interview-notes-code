package com.interview.notes.code.year.y2026.jan.common.test5;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamExample {
    public static void main(String[] args) {
        List<String> names = Arrays.asList(
            "Sunil", "Anita", "Ravi", "Suresh", "Amit", "Sneha", "Raj"
        );

        // Filter names starting with 'S', sort alphabetically, and collect
        List<String> filteredSortedNames = names.stream()
            .filter(name -> name.startsWith("S"))   // filter condition
            .sorted()                               // natural alphabetical sort
            .collect(Collectors.toList());

        System.out.println("Filtered & Sorted Names: " + filteredSortedNames);

        // Example: sort by length, then alphabetically
        List<String> sortedByLength = names.stream()
            .filter(name -> name.length() > 4)      // filter names longer than 4 chars
            .sorted(Comparator.comparing(String::length)
                              .thenComparing(Comparator.naturalOrder()))
            .collect(Collectors.toList());

        System.out.println("Sorted by length then alphabetically: " + sortedByLength);
    }
}
