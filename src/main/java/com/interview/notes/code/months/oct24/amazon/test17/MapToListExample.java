package com.interview.notes.code.months.oct24.amazon.test17;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MapToListExample {
    public static void main(String[] args) {
        // Step 1: Create a Map with some key-value pairs
        Map<String, Integer> map = new HashMap<>();
        map.put("Alice", 30);
        map.put("Bob", 25);
        map.put("Charlie", 35);

        // Step 2: Convert the Map values to a List using Streams
        List<Integer> valuesList = map.values().stream()
                .collect(Collectors.toList());

        // Step 3: Print the List of values
        System.out.println(valuesList);
    }
}
