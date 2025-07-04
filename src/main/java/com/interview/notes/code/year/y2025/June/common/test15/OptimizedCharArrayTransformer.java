package com.interview.notes.code.year.y2025.June.common.test15;

import java.util.Arrays;

public class OptimizedCharArrayTransformer {

    // Optimized transformation method using single pass and StringBuilder
    public static char[] transformArray(char[] input) {
        // Handle edge cases
        if (input == null || input.length == 0) {
            return new char[0];
        }

        // Use StringBuilder for dynamic sizing and better memory efficiency
        StringBuilder sb = new StringBuilder(input.length * 2);

        // Single pass: directly build the result
        for (char c : input) {
            if (c == 'x') {
                sb.append("aa");  // Append two 'a's for each 'x'
            } else if (c != 'y') {
                sb.append(c);     // Append non-'y' characters
            }
        }

        // Convert StringBuilder to char array
        return sb.toString().toCharArray();
    }

    // Alternative optimization using direct array manipulation
    public static char[] transformArrayInPlace(char[] input) {
        if (input == null || input.length == 0) {
            return new char[0];
        }

        // Count 'x' and 'y' in single pass
        int xCount = 0, yCount = 0;
        for (char c : input) {
            if (c == 'x') xCount++;
            if (c == 'y') yCount++;
        }

        // Calculate final size and create result array
        int finalSize = input.length + xCount - yCount;
        char[] result = new char[finalSize];

        // Fill result array in single pass
        int writeIndex = 0;
        for (char c : input) {
            if (c == 'x') {
                result[writeIndex++] = 'a';
                result[writeIndex++] = 'a';
            } else if (c != 'y') {
                result[writeIndex++] = c;
            }
        }

        return result;
    }

    // Main method for testing both implementations
    public static void main(String[] args) {
        // Test cases
        runTests(new char[]{'x', 'x', 'y', 'z', 'x'},
                new char[]{'a', 'a', 'a', 'a', 'z', 'a', 'a'});

        runTests(new char[]{'x', 'y', 'y', 'x', 'y'},
                new char[]{'a', 'a', 'a', 'a'});

        // Edge cases
        runTests(new char[]{}, new char[]{});
        runTests(null, new char[]{});

        // Performance test with large input
        performanceTest();
    }

    // Helper method to run tests
    private static void runTests(char[] input, char[] expected) {
        // Test StringBuilder version
        long startTime = System.nanoTime();
        char[] result1 = transformArray(input);
        long endTime = System.nanoTime();
        boolean passed1 = Arrays.equals(result1, expected);

        System.out.println("StringBuilder Version: " + (passed1 ? "PASS" : "FAIL") +
                " (Time: " + (endTime - startTime) + "ns)");

        // Test in-place version
        startTime = System.nanoTime();
        char[] result2 = transformArrayInPlace(input);
        endTime = System.nanoTime();
        boolean passed2 = Arrays.equals(result2, expected);

        System.out.println("In-place Version: " + (passed2 ? "PASS" : "FAIL") +
                " (Time: " + (endTime - startTime) + "ns)");
        System.out.println();
    }

    // Performance test with large input
    private static void performanceTest() {
        System.out.println("Performance Test with Large Input:");

        // Create large input array
        char[] largeInput = new char[100000];
        Arrays.fill(largeInput, 'x');

        // Test StringBuilder version
        long startTime = System.nanoTime();
        char[] result1 = transformArray(largeInput);
        long time1 = System.nanoTime() - startTime;

        // Test in-place version
        startTime = System.nanoTime();
        char[] result2 = transformArrayInPlace(largeInput);
        long time2 = System.nanoTime() - startTime;

        System.out.println("StringBuilder Version Time: " + time1 + "ns");
        System.out.println("In-place Version Time: " + time2 + "ns");
    }
}
