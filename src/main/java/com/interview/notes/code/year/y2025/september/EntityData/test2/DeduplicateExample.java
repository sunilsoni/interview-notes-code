package com.interview.notes.code.year.y2025.september.EntityData.test2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DeduplicateExample {

    public static void main(String[] args) {
        // Create a new growable list to hold our strings
        ArrayList<String> strings = new ArrayList<>();

        // Add the sample values (some appear multiple times)
        strings.add("Hello, World!");                                // greeting
        strings.add("Welcome to CoderPad.");                         // intro
        strings.add("This pad is running Java " + Runtime.version().feature()); // version info
        strings.add("Hello, World!");                                // duplicate of greeting
        strings.add("Welcome to CoderPad.");                         // duplicate of intro
        strings.add("This pad is running Java " + Runtime.version().feature()); // duplicate of version info
        strings.add("Hello, World!");                                // another duplicate
        strings.add("Welcome to CoderPad.");                         // another duplicate
        strings.add("This pad is running Java " + Runtime.version().feature()); // another duplicate

        // Convert the list into a stream so we can process it with Stream API
        // filter(Objects::nonNull)   → drop any null entries (none here, but good practice)
        // distinct()                 → keep only the first occurrence of each value
        // collect(Collectors.toList()) → gather results back into a List, preserving order
        List<String> uniqueStrings = strings.stream()
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());

        // Print out each unique string on its own line
        uniqueStrings.forEach(System.out::println);
    }
}