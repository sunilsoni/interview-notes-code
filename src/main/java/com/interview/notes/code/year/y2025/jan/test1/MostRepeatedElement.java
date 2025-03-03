package com.interview.notes.code.year.y2025.jan.test1;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MostRepeatedElement {

    // Generic method to find most repeated element
    public static <T> Map.Entry<T, Long> findMostRepeated(T[] array) {
        if (array == null || array.length == 0) {
            return null;
        }

        return Arrays.stream(array)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .orElse(null);
    }

    public static void main(String[] args) {
        // Test Case 1: String array
        String[] fruits = {"Apple", "Apple", "Apple", "Mango", "Mango", "Mango", "Mango"};
        testCase("Test 1 - Fruits", fruits, "Mango", 4L);

        // Test Case 2: Integer array
        Integer[] numbers = {1, 2, 2, 3, 3, 3, 4, 4, 4, 4};
        testCase("Test 2 - Numbers", numbers, 4, 4L);

        // Test Case 3: Empty array
        String[] emptyArray = {};
        testCase("Test 3 - Empty Array", emptyArray, null, null);

        // Test Case 4: Single element
        String[] singleElement = {"Solo"};
        testCase("Test 4 - Single Element", singleElement, "Solo", 1L);

        // Test Case 5: All unique elements
        Integer[] uniqueElements = {1, 2, 3, 4, 5};
        testCase("Test 5 - Unique Elements", uniqueElements, 1, 1L);

        // Test Case 6: Large dataset
        Integer[] largeArray = new Integer[1000000];
        Arrays.fill(largeArray, 42);
        largeArray[0] = 1; // Add one different element
        testCase("Test 6 - Large Dataset", largeArray, 42, 999999L);
    }

    private static <T> void testCase(String testName, T[] input, T expectedElement, Long expectedCount) {
        System.out.println("\nExecuting " + testName);

        long startTime = System.currentTimeMillis();
        Map.Entry<T, Long> result = findMostRepeated(input);
        long endTime = System.currentTimeMillis();

        if (input == null || input.length == 0) {
            if (result == null && expectedElement == null) {
                System.out.println("PASS: Empty/null array handled correctly");
            } else {
                System.out.println("FAIL: Expected null for empty/null array");
            }
            return;
        }

        if (result != null) {
            boolean elementMatch = result.getKey().equals(expectedElement);
            boolean countMatch = result.getValue().equals(expectedCount);

            if (elementMatch && countMatch) {
                System.out.println("PASS: Found " + result.getKey() +
                        " repeated " + result.getValue() + " times");
            } else {
                System.out.println("FAIL: Expected " + expectedElement +
                        " repeated " + expectedCount + " times, but got " +
                        result.getKey() + " repeated " + result.getValue() + " times");
            }
        }

        System.out.println("Execution time: " + (endTime - startTime) + "ms");
    }
}
