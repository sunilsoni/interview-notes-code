package com.interview.notes.code.months.nov24.test13;

import java.util.*;
import java.util.stream.Collectors;

public class NameCounter {
    public static void main(String[] args) {
        // Sample list of names with repetitions
        List<String> names = Arrays.asList(
            "John", "Alice", "Bob", "John", "Charlie", 
            "Alice", "David", "Bob", "John", "Eve"
        );

        // Method 1: Using groupingBy and counting
        Map<String, Long> nameCountMap1 = names.stream()
            .collect(Collectors.groupingBy(
                name -> name,    // Key: name itself
                Collectors.counting()  // Value: count of occurrences
            ));

        // Method 2: Using toMap with merge function
        Map<String, Integer> nameCountMap2 = names.stream()
            .collect(Collectors.toMap(
                name -> name,    // Key: name itself
                name -> 1,       // Value: initial count
                Integer::sum     // Merge function: sum the counts
            ));

        // Print results
        System.out.println("Method 1 Result:");
        nameCountMap1.forEach((name, count) -> 
            System.out.println(name + ": " + count));

        System.out.println("\nMethod 2 Result:");
        nameCountMap2.forEach((name, count) -> 
            System.out.println(name + ": " + count));
    }
}
