package com.interview.notes.code.year.y2025.september.common.test6;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DuplicateCounter {

    // Method to find duplicate elements and their count
    public static Map<String, Long> findDuplicates(List<String> items) {

        // Step 1: Group elements and count their occurrences
        Map<String, Long> frequencyMap = items.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        // Step 2: Filter out entries that appear only once
        Map<String, Long> duplicatesOnly = frequencyMap.entrySet().stream()
                .filter(entry -> entry.getValue() > 1) // keep only duplicates
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        return duplicatesOnly; // return only duplicates
    }

    // Main method for testing with different cases
    public static void main(String[] args) {
        // Test Case 1
        List<String> animals1 = Arrays.asList("cat", "lion", "tiger", "dog", "wolf", "cat", "dog", "cat");
        Map<String, Long> expected1 = new HashMap<>();
        expected1.put("cat", 3L);
        expected1.put("dog", 2L);

        Map<String, Long> result1 = findDuplicates(animals1);
        System.out.println("Test Case 1: " + (result1.equals(expected1) ? "PASS" : "FAIL") + " -> " + result1);

        // Test Case 2 - All unique
        List<String> animals2 = Arrays.asList("fox", "lion", "tiger", "bear", "wolf");
        Map<String, Long> expected2 = new HashMap<>();
        Map<String, Long> result2 = findDuplicates(animals2);
        System.out.println("Test Case 2: " + (result2.equals(expected2) ? "PASS" : "FAIL") + " -> " + result2);

        // Test Case 3 - All duplicates
        List<String> animals3 = Arrays.asList("cat", "cat", "cat", "cat");
        Map<String, Long> expected3 = new HashMap<>();
        expected3.put("cat", 4L);
        Map<String, Long> result3 = findDuplicates(animals3);
        System.out.println("Test Case 3: " + (result3.equals(expected3) ? "PASS" : "FAIL") + " -> " + result3);

        // Test Case 4 - Large Input
        List<String> animals4 = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            animals4.add("cat");
        }
        for (int i = 0; i < 100000; i++) {
            animals4.add("dog");
        }
        Map<String, Long> expected4 = new HashMap<>();
        expected4.put("cat", 100000L);
        expected4.put("dog", 100000L);
        Map<String, Long> result4 = findDuplicates(animals4);
        System.out.println("Test Case 4 (Large Data): " + (result4.equals(expected4) ? "PASS" : "FAIL") + " -> Sample: " + result4.entrySet().stream().limit(2).collect(Collectors.toList()));
    }
}