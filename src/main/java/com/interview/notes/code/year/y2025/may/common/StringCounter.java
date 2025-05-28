package com.interview.notes.code.year.y2025.may.common;

import java.util.*;
import java.util.stream.Collectors;

public class StringCounter {

    /**
     * Count how many times each string appears in the input list.
     */
    public static Map<String, Long> countStrings(List<Object> input) {
        // 1. Filter only String objects
        // 2. Cast to String
        // 3. Group by the string value, counting occurrences
        return input.stream()
                .filter(obj -> obj instanceof String)       // keep only strings
                .map(obj -> (String) obj)                   // cast Object -> String
                .collect(Collectors.groupingBy(
                        s -> s,                                   // key: the string itself
                        Collectors.counting()                     // downstream: count each
                ));
    }

    public static void main(String[] args) {
        // ---- Test Case 1 ----
        List<Object> input1 = Arrays.asList(
                "a", "b", -1, 0, 1, -1, "a", "b", "a", "c", "c"
        );
        Map<String, Long> expected1 = Map.of("a", 3L, "b", 2L, "c", 2L);
        Map<String, Long> result1 = countStrings(input1);
        System.out.println("Test 1: " +
                (result1.equals(expected1) ? "PASS" :
                        "FAIL (got=" + result1 + ", expected=" + expected1 + ")")
        );

        // ---- Test Case 2: empty list ----
        List<Object> input2 = Collections.emptyList();
        Map<String, Long> expected2 = Collections.emptyMap();
        Map<String, Long> result2 = countStrings(input2);
        System.out.println("Test 2: " +
                (result2.equals(expected2) ? "PASS" :
                        "FAIL (got=" + result2 + ", expected=" + expected2 + ")")
        );

        // ---- Large‐Data Test ----
        List<Object> large = new ArrayList<>();
        for (int i = 0; i < 100_000; i++) {
            large.add("x");       // 100 000 copies of "x"
            large.add(i);         // some integers interleaved
        }
        Map<String, Long> expectedLarge = Map.of("x", 100_000L);
        Map<String, Long> resultLarge = countStrings(large);
        System.out.println("Large Test: " +
                (resultLarge.equals(expectedLarge) ? "PASS" :
                        "FAIL (got=" + resultLarge + ", expected=" + expectedLarge + ")")
        );
    }
}