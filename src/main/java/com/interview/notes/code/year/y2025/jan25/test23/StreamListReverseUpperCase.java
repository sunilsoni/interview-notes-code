package com.interview.notes.code.year.y2025.jan25.test23;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamListReverseUpperCase {

    // Main solution using streams
    public static List<String> reverseAndUppercase(List<String> input) {
        if (input == null || input.isEmpty()) {
            return new ArrayList<>();
        }

        return input.stream()
                .map(str -> str != null && !str.isEmpty()
                        ? str.substring(0, 1).toUpperCase() + str.substring(1)
                        : str)
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        list -> {
                            java.util.Collections.reverse(list);
                            return list;
                        }));
    }

    // Test helper method
    public static void testCase(String testName, List<String> input, List<String> expected) {
        List<String> result = reverseAndUppercase(input);
        boolean passed = result.equals(expected);
        System.out.println(testName + ": " + (passed ? "PASS" : "FAIL"));
        if (!passed) {
            System.out.println("Expected: " + expected);
            System.out.println("Got: " + result);
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Normal case
        testCase("Normal Case",
                Arrays.asList("hello", "world"),
                Arrays.asList("World", "Hello"));

        // Test Case 2: Empty list
        testCase("Empty List",
                new ArrayList<>(),
                new ArrayList<>());

        // Test Case 3: Null input
        testCase("Null Input",
                null,
                new ArrayList<>());

        // Test Case 4: Single item
        testCase("Single Item",
                Arrays.asList("test"),
                Arrays.asList("Test"));

        // Test Case 5: Large data
        List<String> largeList = IntStream.range(0, 10000)
                .mapToObj(i -> "item" + i)
                .collect(Collectors.toList());

        List<String> expectedLarge = IntStream.range(0, 10000)
                .mapToObj(i -> "Item" + (9999 - i))
                .collect(Collectors.toList());

        testCase("Large Data", largeList, expectedLarge);

        // Test Case 6: Special characters
        testCase("Special Characters",
                Arrays.asList("@hello", "123world"),
                Arrays.asList("123world", "@hello"));

        // Test Case 7: Mixed case
        testCase("Mixed Case",
                Arrays.asList("HeLLo", "wOrLD"),
                Arrays.asList("World", "Hello"));
    }
}
