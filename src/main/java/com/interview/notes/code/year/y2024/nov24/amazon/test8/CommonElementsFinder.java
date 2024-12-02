package com.interview.notes.code.year.y2024.nov24.amazon.test8;

import java.util.*;

public class CommonElementsFinder {

    public static List<String> findCommonElements(List<List<String>> collections) {
        if (collections == null || collections.isEmpty()) {
            return new ArrayList<>();
        }

        // Start with the first collection
        Map<String, Integer> elementCount = new HashMap<>();
        for (String element : collections.get(0)) {
            elementCount.put(element, elementCount.getOrDefault(element, 0) + 1);
        }

        // Intersect with other collections
        for (int i = 1; i < collections.size(); i++) {
            Map<String, Integer> currentCount = new HashMap<>();
            for (String element : collections.get(i)) {
                if (elementCount.containsKey(element)) {
                    int count = Math.min(currentCount.getOrDefault(element, 0) + 1, elementCount.get(element));
                    currentCount.put(element, count);
                }
            }
            elementCount = currentCount;
        }

        // Build the result list
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : elementCount.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                result.add(entry.getKey());
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // Test case 1
        List<List<String>> testCase1 = Arrays.asList(
                Arrays.asList("a", "b", "b", "c", "d"),
                Arrays.asList("a", "b", "b", "c"),
                Arrays.asList("b", "c", "d", "b")
        );
        List<String> expected1 = Arrays.asList("b", "b", "c");
        runTest(testCase1, expected1, "Test Case 1");

        // Test case 2
        List<List<String>> testCase2 = Arrays.asList(
                Arrays.asList("apple", "book", "cat", "dog"),
                Arrays.asList("book", "cat", "dog"),
                Arrays.asList("book", "dog")
        );
        List<String> expected2 = Arrays.asList("book", "dog");
        runTest(testCase2, expected2, "Test Case 2");

        // Test case 3: Empty collections
        List<List<String>> testCase3 = Arrays.asList(
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>()
        );
        List<String> expected3 = new ArrayList<>();
        runTest(testCase3, expected3, "Test Case 3 (Empty collections)");

        // Test case 4: Large data input
        List<List<String>> testCase4 = generateLargeInput(1000, 10000);
        List<String> expected4 = Collections.singletonList("common");
        runTest(testCase4, expected4, "Test Case 4 (Large data input)");
    }

    private static void runTest(List<List<String>> input, List<String> expected, String testName) {
        List<String> result = findCommonElements(input);
        boolean passed = result.size() == expected.size() && new HashSet<>(result).equals(new HashSet<>(expected));
        System.out.println(testName + ": " + (passed ? "PASS" : "FAIL"));
        if (!passed) {
            System.out.println("  Expected: " + expected);
            System.out.println("  Actual: " + result);
        }
    }

    private static List<List<String>> generateLargeInput(int numCollections, int elementsPerCollection) {
        List<List<String>> largeInput = new ArrayList<>();
        for (int i = 0; i < numCollections; i++) {
            List<String> collection = new ArrayList<>();
            for (int j = 0; j < elementsPerCollection; j++) {
                collection.add("element" + j);
            }
            collection.add("common");
            largeInput.add(collection);
        }
        return largeInput;
    }
}