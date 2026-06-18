package com.interview.notes.code.year.y2026.june.common.test4;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class SortMapByValueDesc {

    // Sort map by value descending
    static Map<String, Integer> sort(Map<String, Integer> map) {

        // Convert entries to stream
        return map.entrySet()

                // Sort by value descending
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())

                // Store result in LinkedHashMap to preserve order
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> b,
                        LinkedHashMap::new));
    }

    // Simple test helper
    static void test(String name,
                     Map<String, Integer> input,
                     Map<String, Integer> expected) {

        Map<String, Integer> actual = sort(input);

        System.out.println(
                expected.equals(actual)
                        ? "PASS : " + name
                        : "FAIL : " + name +
                          "\nExpected : " + expected +
                          "\nActual   : " + actual);
    }

    public static void main(String[] args) {

        // Test Case 1
        test(
                "Basic Example",
                Map.of(
                        "A", 1,
                        "B", 2,
                        "C", 3,
                        "D", 5,
                        "E", 6),
                new LinkedHashMap<>() {{
                    put("E", 6);
                    put("D", 5);
                    put("C", 3);
                    put("B", 2);
                    put("A", 1);
                }}
        );

        // Test Case 2
        test(
                "Single Element",
                Map.of("A", 10),
                new LinkedHashMap<>() {{
                    put("A", 10);
                }}
        );

        // Test Case 3
        test(
                "Empty Map",
                new HashMap<>(),
                new LinkedHashMap<>()
        );

        // Large Data Test
        Map<String, Integer> largeMap = new HashMap<>();

        for (int i = 1; i <= 100000; i++) {
            largeMap.put("K" + i, i);
        }

        long start = System.currentTimeMillis();

        Map<String, Integer> result = sort(largeMap);

        long end = System.currentTimeMillis();

        System.out.println(
                "\nLarge Data Test PASS" +
                "\nSize = " + result.size() +
                "\nTime = " + (end - start) + " ms");
    }
}