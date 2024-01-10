package com.interview.notes.code.months.year2023.july23.test4;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class MapSortByValuesExample {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("A", 3);
        map.put("B", 1);
        map.put("C", 2);

        // Sort map by values in ascending order
        Map<String, Integer> sortedMap = map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, HashMap::new));

        // Print sorted map
        sortedMap.forEach((key, value) -> System.out.println(key + ": " + value));
    }
}
