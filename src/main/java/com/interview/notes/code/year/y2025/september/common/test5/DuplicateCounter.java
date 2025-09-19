package com.interview.notes.code.year.y2025.september.common.test5;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DuplicateCounter {

    public static Map<String, Long> findDuplicates(List<String> items) {
        return items.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static void main(String[] args) {
        List<String> animals1 = Arrays.asList("cat", "lion", "tiger", "dog", "wolf", "cat", "dog", "cat");

        Map<String, Long> expected1 = new HashMap<>();
        expected1.put("cat", 3L);
        expected1.put("dog", 2L);

        Map<String, Long> result1 = findDuplicates(animals1);
        System.out.println("Test Case 1: " + (result1.equals(expected1) ? "PASS" : "FAIL") + " -> " + result1);

        // Additional tests can go here as needed...
    }
}