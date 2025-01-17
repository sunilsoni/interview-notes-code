package com.interview.notes.code.year.y2025.jan24.test12;

import java.util.HashMap;
import java.util.Map;

public class EvenKeyFilter {
    public static void main(String[] args) {
        // Create a map with integer keys and string values
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "a");
        map.put(2, "b");
        map.put(3, "c");
        map.put(4, "d");

        // Filter even keys using Java 8 streams
        map.entrySet().stream()
                .filter(entry -> entry.getKey() % 2 == 0)  // Check for even keys
                .forEach(entry -> System.out.println(entry.getKey() + " = " + entry.getValue()));  // Print even key-value pairs
    }
}
