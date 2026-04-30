package com.interview.notes.code.year.y2026.april.common.test2;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MapInverter {
    public static void main(String[] args) {
        Map<String, List<String>> map = new HashMap<>();
        map.put("Ajay", List.of("Math", "Physics"));
        map.put("Mehga", List.of("Science", "Math"));
        map.put("Chetan", List.of("Physics", "Science"));

        Map<String, List<String>> result = map.entrySet().stream()
            // 1. Flatten: Ajay -> Math, Ajay -> Physics
            .flatMap(entry -> entry.getValue().stream()
                .map(subject -> new AbstractMap.SimpleEntry<>(subject, entry.getKey())))
            // 2. Group by Subject, collect Names into a List
            .collect(Collectors.groupingBy(
                Map.Entry::getKey,
                Collectors.mapping(Map.Entry::getValue, Collectors.toList())
            ));

        System.out.println(result);
    }
}