package com.interview.notes.code.year.y2025.feb25.common.test1;

import java.util.Map;

public class MapOfExample {
    public static void main(String[] args) {
        // Example 1: Simple Map.of with key-value pairs
        Map<String, String> fruits = Map.of(
            "apple", "red",
            "banana", "yellow",
            "grape", "purple"
        );
        System.out.println("Fruits: " + fruits);

        // Example 2: Map.of with different data types
        Map<String, Integer> ages = Map.of(
            "John", 25,
            "Alice", 30,
            "Bob", 28
        );
        System.out.println("Ages: " + ages);

        // Example 3: Single entry Map
        Map<String, Boolean> status = Map.of("isActive", true);
        System.out.println("Status: " + status);

        // Accessing values
        System.out.println("Apple color: " + fruits.get("apple"));
        System.out.println("John's age: " + ages.get("John"));

        // Note: Map.of creates immutable maps
        // This will throw an exception:
        // fruits.put("orange", "orange"); // UnsupportedOperationException
    }
}
