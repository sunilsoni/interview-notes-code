package com.interview.notes.code.year.y2025.may.common.test5;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class MapValueSwapper {
    public static void main(String[] args) {
        // Test Case 1: Basic scenario
        testBasicScenario();

        // Test Case 2: Empty map
        testEmptyMap();

        // Test Case 3: Large data
        testLargeData();

        // Test Case 4: Duplicate values
        testDuplicateValues();
    }

    public static Map<String, String> swapKeyValue(Map<String, String> inputMap) {
        return inputMap.entrySet()
                .stream()
                .collect(Collectors.groupingBy(
                        Map.Entry::getValue,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Map.Entry.comparingByKey()),
                                opt -> opt.map(Map.Entry::getKey).orElse(null)
                        )
                ))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() != null)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ));
    }

    private static void testBasicScenario() {
        System.out.println("Test Case 1: Basic Scenario");
        Map<String, String> input = new HashMap<>();
        input.put("A1", "MCA");
        input.put("A2", "BCA");
        input.put("A3", "MCA");
        input.put("A4", "BBA");

        Map<String, String> result = swapKeyValue(input);

        // Expected output verification
        boolean passed = result.get("MCA").equals("A3") &&
                result.get("BCA").equals("A2") &&
                result.get("BBA").equals("A4");

        System.out.println("Result: " + result);
        System.out.println("Test " + (passed ? "PASSED" : "FAILED"));
        System.out.println();
    }

    private static void testEmptyMap() {
        System.out.println("Test Case 2: Empty Map");
        Map<String, String> input = new HashMap<>();
        Map<String, String> result = swapKeyValue(input);

        System.out.println("Result: " + result);
        System.out.println("Test " + (result.isEmpty() ? "PASSED" : "FAILED"));
        System.out.println();
    }

    private static void testLargeData() {
        System.out.println("Test Case 3: Large Data");
        Map<String, String> input = new HashMap<>();
        for (int i = 0; i < 10000; i++) {
            input.put("A" + i, "Value" + (i % 100));
        }

        long startTime = System.currentTimeMillis();
        Map<String, String> result = swapKeyValue(input);
        long endTime = System.currentTimeMillis();

        System.out.println("Processing time: " + (endTime - startTime) + "ms");
        System.out.println("Result size: " + result.size());
        System.out.println("Test " + (result.size() == 100 ? "PASSED" : "FAILED"));
        System.out.println();
    }

    private static void testDuplicateValues() {
        System.out.println("Test Case 4: Duplicate Values");
        Map<String, String> input = new HashMap<>();
        input.put("A1", "Value1");
        input.put("A2", "Value1");
        input.put("A3", "Value1");

        Map<String, String> result = swapKeyValue(input);

        System.out.println("Result: " + result);
        System.out.println("Test " + (result.size() == 1 &&
                result.get("Value1").equals("A3") ?
                "PASSED" : "FAILED"));
        System.out.println();
    }
}
