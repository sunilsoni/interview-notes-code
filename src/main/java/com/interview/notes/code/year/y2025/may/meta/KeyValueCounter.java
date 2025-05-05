package com.interview.notes.code.year.y2025.may.meta;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class KeyValueCounter {

    // Solution using Java 8 Stream API
    public static Map<String, Integer> combineValues(List<String> keys, List<Integer> values) {
        if (keys.size() != values.size()) {
            throw new IllegalArgumentException("Keys and values must have same size");
        }

        return IntStream.range(0, keys.size())
                .boxed()
                .collect(Collectors.groupingBy(
                        i -> keys.get(i),
                        Collectors.summingInt(i -> values.get(i))
                ));
    }

    public static void main(String[] args) {
        // Test Case 1: Normal case
        test1();

        // Test Case 2: Empty input
        test2();

        // Test Case 3: Large input
        test3();
    }

    private static void test1() {
        List<String> keys = Arrays.asList("a", "c", "d", "e", "b", "a", "e", "f", "f", "d", "c", "b");
        List<Integer> values = Arrays.asList(7, 6, 1, 2, 2, 1, 3, 1, 2, 5, 4, 1);

        Map<String, Integer> result = combineValues(keys, values);
        Map<String, Integer> expected = new HashMap<>() {{
            put("a", 8);
            put("b", 3);
            put("c", 10);
            put("d", 6);
            put("e", 5);
            put("f", 3);
        }};

        System.out.println("Test 1: " + (result.equals(expected) ? "PASS" : "FAIL"));
    }

    private static void test2() {
        List<String> keys = new ArrayList<>();
        List<Integer> values = new ArrayList<>();

        Map<String, Integer> result = combineValues(keys, values);
        System.out.println("Test 2: " + (result.isEmpty() ? "PASS" : "FAIL"));
    }

    private static void test3() {
        // Generate large test data
        List<String> keys = new ArrayList<>();
        List<Integer> values = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            keys.add("key" + (i % 1000));
            values.add(1);
        }

        long startTime = System.currentTimeMillis();
        Map<String, Integer> result = combineValues(keys, values);
        long endTime = System.currentTimeMillis();

        System.out.println("Test 3: " + (result.get("key0") == 1000 ? "PASS" : "FAIL"));
        System.out.println("Large data processing time: " + (endTime - startTime) + "ms");
    }
}
