package com.interview.notes.code.months.year2023.june23.test3;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MergingMapsWithUniqueElements {
    public static void main(String[] args) {

        // First Map
        Map<String, Integer> map1 = new HashMap<>();
        map1.put("a", 10);
        map1.put("b", 20);

        // Second Map
        Map<String, Integer> map2 = new HashMap<>();
        map2.put("c", 30);
        map2.put("d", 40);

        // Merging maps and sorting by value
        Map<String, Integer> sortedMap = Stream.concat(map1.entrySet().stream(), map2.entrySet().stream())
                .sorted(Map.Entry.<String, Integer>comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        System.out.println(sortedMap);
    }
}
