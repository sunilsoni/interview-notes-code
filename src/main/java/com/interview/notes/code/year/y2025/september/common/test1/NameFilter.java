package com.interview.notes.code.year.y2025.september.common.test1;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class NameFilter {
    public static void main(String[] args) {
        // Create a large list of names (for demonstration, let's create a smaller sample)
        List<String> names = new ArrayList<>();
        
        // Adding sample names (in real scenario, you might have 100k names)
        names.add("Adam");
        names.add("Bob");
        names.add("Alice");
        names.add("Charlie");
        names.add("Andrew");
        names.add("David");
        names.add("Anna");
        // ... more names

        // Method 1: Using Stream filter
        List<String> namesStartingWithA = names.stream()
            .filter(name -> name.startsWith("A"))
            .collect(Collectors.toList());

        // Print filtered names
        System.out.println("Names starting with 'A': " + namesStartingWithA);

        // Method 2: If you want to make it case-insensitive
        List<String> namesStartingWithACaseInsensitive = names.stream()
            .filter(name -> name.toLowerCase().startsWith("a"))
            .collect(Collectors.toList());

        // Method 3: If you want to count how many names start with 'A'
        long count = names.stream()
            .filter(name -> name.startsWith("A"))
            .count();

        System.out.println("Number of names starting with 'A': " + count);
    }
}
