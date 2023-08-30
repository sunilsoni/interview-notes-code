package com.interview.notes.code.Aug23.test3;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MapMergeExample {
    public static void main(String[] args) {
        // Initialize two maps
        Map<String, Integer> map1 = new HashMap<>();
        map1.put("a", 1);
        map1.put("b", 2);
        map1.put("c", 3);

        Map<String, Integer> map2 = new HashMap<>();
        map2.put("b", 4);
        map2.put("c", 5);
        map2.put("d", 6);

        // Approach 1: Using putAll() method - overwrites duplicate keys
        Map<String, Integer> mergedMap1 = new HashMap<>(map1);
        mergedMap1.putAll(map2); // if keys are duplicate, the value in map2 will overwrite the value in map1
        System.out.println("Merged map using putAll: " + mergedMap1);

        // Approach 2: Using Java 8 Streams - allows handling of duplicate keys
        Map<String, Integer> mergedMap2 = Stream.concat(map1.entrySet().stream(), map2.entrySet().stream())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (value1, value2) -> value1 + value2)); // if keys are duplicate, sum the values
        System.out.println("Merged map using Streams: " + mergedMap2);
    }
}
