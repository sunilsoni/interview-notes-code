package com.interview.notes.code.months.oct24.amz.test24;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class SortHashMapByValue {
    public static void main(String[] args) {
        // Create a HashMap with some sample data
        HashMap<String, Integer> map = new HashMap<>();
        map.put("Apple", 3);
        map.put("Banana", 1);
        map.put("Cherry", 2);

        // Sort the HashMap by values
        Map<String, Integer> sortedMap = map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue()) // Sort by value
                .collect(Collectors.toMap(
                        Map.Entry::getKey,   // Key mapper
                        Map.Entry::getValue, // Value mapper
                        (e1, e2) -> e1,      // Merge function (not used here)
                        LinkedHashMap::new    // Use LinkedHashMap to maintain insertion order
                ));

        // Print the sorted map
        sortedMap.forEach((key, value) -> System.out.println(key + ": " + value));
    }
}
