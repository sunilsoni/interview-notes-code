package com.interview.notes.code.year.y2025.may.common.test10;

import java.util.*;
import java.util.stream.Collectors;

public class WordGrouper {

    /**
     * Groups words by their length using Stream API and Optional for null safety
     *
     * @param words Input list of strings (can be null)
     * @return Map with word length as key and list of words as value
     */
    public static Map<Integer, List<String>> groupWordsByLength(List<String> words) {
        // Use Optional to handle null input gracefully
        // If words is null, return empty map, otherwise process the list
        return Optional.ofNullable(words)
                .map(list -> list.stream()
                        .filter(word -> word != null && !word.isEmpty()) // Remove null/empty strings
                        .collect(Collectors.groupingBy(
                                String::length,     // Group by word length
                                HashMap::new,       // Use HashMap as collection type
                                Collectors.toList() // Collect words into List
                        )))
                .orElseGet(HashMap::new);   // Return empty HashMap if input is null
    }

    public static void main(String[] args) {
        // Test 1: Normal case
        System.out.println("\nTest 1: Normal case");
        List<String> test1 = Arrays.asList("cat", "dog", "elephant", "ant", "tiger", "lion");
        System.out.println(groupWordsByLength(test1));

        // Test 2: Null list - now handled gracefully
        System.out.println("\nTest 2: Null list");
        List<String> test2 = null;
        System.out.println(groupWordsByLength(test2));  // Returns empty map instead of throwing exception

        // Test 3: List with nulls and empty strings
        System.out.println("\nTest 3: List with nulls and empty strings");
        List<String> test3 = Arrays.asList("cat", null, "", "dog", "");
        System.out.println(groupWordsByLength(test3));

        // Test 4: Empty list
        System.out.println("\nTest 4: Empty list");
        List<String> test4 = new ArrayList<>();
        System.out.println(groupWordsByLength(test4));

        // Test 5: Large dataset
        System.out.println("\nTest 5: Large dataset");
        List<String> test5 = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            test5.add("test" + i);
        }
        Map<Integer, List<String>> result = groupWordsByLength(test5);
        System.out.println("Number of different word lengths: " + result.size());

        // Test 6: List with all null or empty strings
        System.out.println("\nTest 6: List with all null or empty strings");
        List<String> test6 = Arrays.asList(null, "", null, "");
        System.out.println(groupWordsByLength(test6));
    }
}
