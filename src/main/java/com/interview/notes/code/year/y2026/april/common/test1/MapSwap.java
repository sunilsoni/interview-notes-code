package com.interview.notes.code.year.y2026.april.common.test1;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MapSwap {
    public static void main(String[] args) {
        // Original Map: { "Sunil": ["Java", "Kafka"], "Raj": ["Java", "Spring"] }
        Map<String, List<String>> originalMap = Map.of(
            "Sunil", List.of("Java", "Kafka"),
            "Raj", List.of("Java", "Spring")
        );

        // Swapping Logic
        Map<String, List<String>> swappedMap = originalMap.entrySet().stream()
            .flatMap(entry -> entry.getValue().stream()
                .map(subject -> new AbstractMap.SimpleEntry<>(subject, entry.getKey())))
            .collect(Collectors.groupingBy(
                Map.Entry::getKey, 
                Collectors.mapping(Map.Entry::getValue, Collectors.toList())
            ));

        // Result: { "Java": ["Sunil", "Raj"], "Kafka": ["Sunil"], "Spring": ["Raj"] }
        System.out.println(swappedMap);
    }
}