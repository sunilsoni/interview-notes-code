package com.interview.notes.code.year.y2026.april.cts.test5;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TopDevices {

    public static List<String> topRepeatingKWords(String[] words, int k) {
        return Arrays.stream(words)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue(Comparator.reverseOrder())
                        .thenComparing(Map.Entry.comparingByKey(String.CASE_INSENSITIVE_ORDER)))
                .limit(k)
                .map(Map.Entry::getKey)
                .toList();
    }

    public static void main(String[] args) {
        String[] words = {
            "iPhone", "MacBook Pro", "Airpods", "MacBook Pro", "Airpods", 
            "iPhone", "iPad", "Airpods", "iPhone", "Airpods", "iPad", "MacBook Pro"
        };
        runTest("Standard Case", words, 2, List.of("Airpods", "iPhone"));

        String[] tieData = {"B", "A", "C", "A", "B", "C"};
        runTest("Alphabetical Tie", tieData, 2, List.of("A", "B"));

        String[] largeData = new String[100000];
        Arrays.fill(largeData, 0, 50000, "Z");
        Arrays.fill(largeData, 50000, 90000, "A");
        Arrays.fill(largeData, 90000, 100000, "B");
        runTest("Large Data Case", largeData, 1, List.of("Z"));
    }

    private static void runTest(String testName, String[] input, int k, List<String> expected) {
        List<String> actual = topRepeatingKWords(input, k);
        System.out.println(actual.equals(expected) 
            ? "PASS - " + testName 
            : "FAIL - " + testName + " | Expected: " + expected + " | Actual: " + actual);
    }
}