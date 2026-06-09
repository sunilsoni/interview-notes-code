package com.interview.notes.code.year.y2026.june.common.test1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class HighestFrequencyString {

    // Method to return all strings having highest frequency
    static List<String> findHighestFrequencyStrings(List<String> list) {

        // Count occurrences of every string
        Map<String, Long> freqMap =
                list.stream()
                        .collect(Collectors.groupingBy(
                                Function.identity(),
                                Collectors.counting()));

        // Find highest frequency
        long maxFreq =
                freqMap.values()
                        .stream()
                        .mapToLong(Long::longValue)
                        .max()
                        .orElse(0);

        // Return all strings having max frequency
        return freqMap.entrySet()
                .stream()
                .filter(e -> e.getValue() == maxFreq)
                .map(Map.Entry::getKey)
                .sorted()
                .toList();
    }

    // Simple PASS/FAIL testing
    static void test(String testName,
                     List<String> input,
                     List<String> expected) {

        List<String> actual = findHighestFrequencyStrings(input);

        boolean pass = new HashSet<>(actual)
                .equals(new HashSet<>(expected));

        System.out.println(
                testName + " : " +
                (pass ? "PASS" : "FAIL"));

        if (!pass) {
            System.out.println("Expected : " + expected);
            System.out.println("Actual   : " + actual);
        }
    }

    public static void main(String[] args) {

        // Given example
        test(
                "Test-1",
                List.of("a","b","a","c","a","b","b","c"),
                List.of("a","b")
        );

        // Single winner
        test(
                "Test-2",
                List.of("a","a","b","c"),
                List.of("a")
        );

        // All same frequency
        test(
                "Test-3",
                List.of("a","b","c"),
                List.of("a","b","c")
        );

        // Single element
        test(
                "Test-4",
                List.of("java"),
                List.of("java")
        );

        // Empty list
        test(
                "Test-5",
                List.of(),
                List.of()
        );

        // Large data test
        List<String> large = new ArrayList<>();

        for (int i = 0; i < 100_000; i++) {
            large.add("A");
        }

        for (int i = 0; i < 100_000; i++) {
            large.add("B");
        }

        for (int i = 0; i < 50_000; i++) {
            large.add("C");
        }

        test(
                "Large-Test",
                large,
                List.of("A", "B")
        );
    }
}