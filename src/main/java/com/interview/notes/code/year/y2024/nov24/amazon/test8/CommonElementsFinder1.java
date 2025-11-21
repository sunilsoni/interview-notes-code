package com.interview.notes.code.year.y2024.nov24.amazon.test8;

import java.util.*;
import java.util.stream.Collectors;

/*
Given N collections of strings (files), find the common elements (sentences) across all of the collections (files).
[a,b,b,c,d]
[a,b,b,c]
[b, c,d,b]
output: [b,b,c]
[apple, book, cat, dog]
[book, cat,dog]
[book, dog] output: [book, dog]l

 */
public class CommonElementsFinder1 {

    /**
     * Finds the common elements across all collections with their multiplicities.
     *
     * @param collections A list of collections, each containing strings.
     * @return A list of common strings with their minimum multiplicities.
     */
    public static List<String> findCommonElements(List<List<String>> collections) {
        if (collections == null || collections.isEmpty()) {
            return Collections.emptyList(); // If the input is null or empty, return an empty list.
        }

        // Initialize the intersection map with the frequency map of the first collection.
        Map<String, Integer> intersectionMap = getFrequencyMap(collections.get(0));

        // Iterate through the remaining collections to compute the intersection.
        for (int i = 1; i < collections.size(); i++) {
            Map<String, Integer> currentMap = getFrequencyMap(collections.get(i));
            intersectionMap = intersectMaps(intersectionMap, currentMap);

            // Early termination if the intersection becomes empty.
            if (intersectionMap.isEmpty()) {
                break;
            }
        }

        // Reconstruct the result list based on the intersection map.
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : intersectionMap.entrySet()) {
            // Add each element the number of times it appears in the intersection.
            for (int j = 0; j < entry.getValue(); j++) {
                result.add(entry.getKey());
            }
        }

        return result;
    }

    /**
     * Creates a frequency map for a given list of strings.
     *
     * @param list The list of strings.
     * @return A map with strings as keys and their counts as values.
     */
    private static Map<String, Integer> getFrequencyMap(List<String> list) {
        Map<String, Integer> freqMap = new HashMap<>();
        for (String s : list) {
            // Increment the count for each string in the list.
            freqMap.put(s, freqMap.getOrDefault(s, 0) + 1);
        }
        return freqMap;
    }

    /**
     * Computes the intersection of two frequency maps.
     * For each common key, retains the minimum count.
     *
     * @param map1 The first frequency map.
     * @param map2 The second frequency map.
     * @return The intersected frequency map.
     */
    private static Map<String, Integer> intersectMaps(Map<String, Integer> map1, Map<String, Integer> map2) {
        Map<String, Integer> intersection = new HashMap<>();
        for (Map.Entry<String, Integer> entry : map1.entrySet()) {
            String key = entry.getKey();
            if (map2.containsKey(key)) {
                // Retain the minimum count of the common elements.
                intersection.put(key, Math.min(entry.getValue(), map2.get(key)));
            }
        }
        return intersection;
    }

    /**
     * Compares two lists for equality, considering the frequency of elements.
     *
     * @param expected The expected list.
     * @param actual   The actual list.
     * @return True if both lists contain the same elements with the same frequencies; otherwise, false.
     */
    private static boolean areListsEqual(List<String> expected, List<String> actual) {
        if (expected.size() != actual.size()) {
            return false; // If the sizes are different, the lists are not equal.
        }

        // Create frequency maps for both lists and compare them.
        Map<String, Long> expectedMap = expected.stream()
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()));
        Map<String, Long> actualMap = actual.stream()
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()));

        return expectedMap.equals(actualMap); // Return true if the frequency maps are equal.
    }

    /**
     * Executes all test cases and outputs pass/fail results.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        int passed = 0;
        int failed = 0;

        // Define test cases
        List<TestCase> testCases = new ArrayList<>();

        // Test Case 1
        testCases.add(new TestCase(
                Arrays.asList(
                        Arrays.asList("a", "b", "b", "c", "d"),
                        Arrays.asList("a", "b", "b", "c"),
                        Arrays.asList("b", "c", "d", "b")
                ),
                Arrays.asList("b", "b", "c")
        ));

        // Test Case 2
        testCases.add(new TestCase(
                Arrays.asList(
                        Arrays.asList("apple", "book", "cat", "dog"),
                        Arrays.asList("book", "cat", "dog"),
                        Arrays.asList("book", "dog")
                ),
                Arrays.asList("book", "dog")
        ));

        // Test Case 3: No common elements
        testCases.add(new TestCase(
                Arrays.asList(
                        Arrays.asList("x", "y", "z"),
                        Arrays.asList("a", "b", "c"),
                        Arrays.asList("1", "2", "3")
                ),
                Collections.emptyList()
        ));

        // Test Case 4: All elements common with varying multiplicities
        testCases.add(new TestCase(
                Arrays.asList(
                        Arrays.asList("k", "k", "m", "n"),
                        Arrays.asList("k", "m", "m", "n", "n"),
                        Arrays.asList("k", "k", "m", "n", "n", "n")
                ),
                Arrays.asList("k", "m", "n")
        ));

        // Test Case 5: Single collection
        testCases.add(new TestCase(
                List.of(
                        Arrays.asList("single", "collection", "test")
                ),
                Arrays.asList("single", "collection", "test")
        ));

        // Test Case 6: Empty collections
        testCases.add(new TestCase(
                Arrays.asList(
                        List.of(),
                        Arrays.asList("a", "b"),
                        List.of("a")
                ),
                Collections.emptyList()
        ));

        // Test Case 7: Large data input
        List<String> largeList1 = new ArrayList<>();
        List<String> largeList2 = new ArrayList<>();
        List<String> largeList3 = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            largeList1.add("common");
            largeList1.add("unique1_" + i); // Adding unique elements to largeList1.
            largeList2.add("common");
            largeList2.add("unique2_" + i); // Adding unique elements to largeList2.
            largeList3.add("common");
            largeList3.add("unique3_" + i); // Adding unique elements to largeList3.
        }
        testCases.add(new TestCase(
                Arrays.asList(largeList1, largeList2, largeList3),
                Collections.nCopies(100000, "common") // Expecting only the common elements.
        ));

        // Execute test cases
        for (int i = 0; i < testCases.size(); i++) {
            TestCase tc = testCases.get(i);
            List<String> actual = findCommonElements(tc.collections);
            boolean isPass = areListsEqual(tc.expectedOutput, actual);
            if (isPass) {
                System.out.println("Test Case " + (i + 1) + ": PASS");
                passed++;
            } else {
                System.out.println("Test Case " + (i + 1) + ": FAIL");
                System.out.println("Expected: " + tc.expectedOutput);
                System.out.println("Actual  : " + actual);
                failed++;
            }
        }

        // Summary of test results
        System.out.println("\nTotal Test Cases: " + testCases.size());
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failed);
    }

    /**
     * Represents a test case with input collections and the expected output.
     */
    static class TestCase {
        List<List<String>> collections; // The input collections of strings.
        List<String> expectedOutput; // The expected output list of common elements.

        TestCase(List<List<String>> collections, List<String> expectedOutput) {
            this.collections = collections;
            this.expectedOutput = expectedOutput;
        }
    }
}
