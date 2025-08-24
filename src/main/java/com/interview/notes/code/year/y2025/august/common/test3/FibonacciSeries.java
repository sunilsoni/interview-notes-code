package com.interview.notes.code.year.y2025.august.common.test3;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FibonacciSeries {

    // Method to generate first n Fibonacci numbers
    public static List<Long> generateFibonacci(int n) {
        // Create a list to store the sequence
        List<Long> fibList = new ArrayList<>();

        // If n <= 0, return empty list
        if (n <= 0) {
            return fibList;
        }

        // First number is always 0
        fibList.add(0L);

        // If n == 1, return immediately
        if (n == 1) {
            return fibList;
        }

        // Second number is always 1
        fibList.add(1L);

        // For n > 2, generate remaining numbers
        for (int i = 2; i < n; i++) {
            long next = fibList.get(i - 1) + fibList.get(i - 2); // Sum of last two
            fibList.add(next);
        }

        return fibList;
    }

    // Main method for testing PASS/FAIL
    public static void main(String[] args) {
        // Test cases: Map<input, expectedOutput>
        Map<Integer, String> testCases = new LinkedHashMap<>();
        testCases.put(3, "0 1 1");
        testCases.put(7, "0 1 1 2 3 5 8");
        testCases.put(1, "0");
        testCases.put(0, "");

        // Large n test case for performance (not comparing string, just checking size)
        int largeN = 50;

        // Run each test
        for (Map.Entry<Integer, String> entry : testCases.entrySet()) {
            int n = entry.getKey();
            String expected = entry.getValue();
            String actual = generateFibonacci(n).stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(" "));

            if (actual.equals(expected)) {
                System.out.println("PASS for n = " + n);
            } else {
                System.out.println("FAIL for n = " + n + " | Expected: " + expected + " | Got: " + actual);
            }
        }

        // Large input test
        List<Long> largeFib = generateFibonacci(largeN);
        if (largeFib.size() == largeN) {
            System.out.println("PASS for large n = " + largeN + " (Size check only)");
        } else {
            System.out.println("FAIL for large n = " + largeN);
        }
    }
}