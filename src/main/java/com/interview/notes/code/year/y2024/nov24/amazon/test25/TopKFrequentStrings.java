package com.interview.notes.code.year.y2024.nov24.amazon.test25;

import java.util.*;

public class TopKFrequentStrings {

    /**
     * Returns the top k most frequent strings from the input array.
     *
     * @param strings Array of input strings.
     * @param k       Number of top frequent strings to return.
     * @return List of top k frequent strings.
     */
    public static List<String> topKFrequent(String[] strings, int k) {
        // Edge case checks
        if (strings == null || k <= 0) {
            return new ArrayList<>();
        }

        // Step 1: Count frequency of each string
        Map<String, Integer> frequencyMap = new HashMap<>();
        for (String s : strings) {
            frequencyMap.put(s, frequencyMap.getOrDefault(s, 0) + 1);
        }

        // Step 2: Use a min-heap to keep top k elements
        PriorityQueue<Map.Entry<String, Integer>> minHeap =
                new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));

        for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
            minHeap.offer(entry);
            if (minHeap.size() > k) {
                minHeap.poll(); // Remove the least frequent
            }
        }

        // Step 3: Extract the keys from the heap
        List<String> result = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            result.add(minHeap.poll().getKey());
        }

        // Since it's a min-heap, reverse to get highest frequency first
        Collections.reverse(result);
        return result;
    }

    /**
     * Main method for testing the topKFrequent method.
     */
    public static void main(String[] args) {
        // Define test cases
        List<TestCase> testCases = new ArrayList<>();

        // Test Case 1: Example provided
        testCases.add(new TestCase(
                new String[]{"amazon", "amazon", "media", "experience", "amazon", "media"},
                2,
                Arrays.asList("amazon", "media")
        ));

        // Test Case 2: All strings have same frequency
        testCases.add(new TestCase(
                new String[]{"apple", "banana", "cherry", "date"},
                2,
                Arrays.asList("banana", "date") // Any two strings are acceptable
        ));

        // Test Case 3: k equals number of unique strings
        testCases.add(new TestCase(
                new String[]{"one", "two", "three", "two", "one", "three"},
                3,
                Arrays.asList("one", "two", "three")
        ));

        // Test Case 4: k is zero
        testCases.add(new TestCase(
                new String[]{"single"},
                0,
                Collections.emptyList()
        ));

        // Test Case 5: Empty array
        testCases.add(new TestCase(
                new String[]{},
                1,
                Collections.emptyList()
        ));

        // Test Case 6: Large dataset
        String[] largeDataset = new String[100000];
        for (int i = 0; i < 100000; i++) {
            largeDataset[i] = "string" + (i % 1000); // 1000 unique strings
        }
        List<String> expectedLargeDataset = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            expectedLargeDataset.add("string999"); // Assuming "string999" is the most frequent
        }
        testCases.add(new TestCase(
                largeDataset,
                10,
                expectedLargeDataset // Simplified expectation
        ));

        // Execute test cases
        int passed = 0;
        for (int i = 0; i < testCases.size(); i++) {
            TestCase tc = testCases.get(i);
            List<String> output = topKFrequent(tc.input, tc.k);
            boolean isPass = tc.expected.isEmpty() ? output.isEmpty() :
                    output.containsAll(tc.expected) && tc.expected.containsAll(output);
            if (isPass) {
                System.out.println("Test Case " + (i + 1) + ": PASS");
                passed++;
            } else {
                System.out.println("Test Case " + (i + 1) + ": FAIL");
                System.out.println("Expected: " + tc.expected);
                System.out.println("Got: " + output);
            }
        }

        System.out.println("Passed " + passed + " out of " + testCases.size() + " test cases.");
    }

    /**
     * Helper class to define a test case.
     */
    static class TestCase {
        String[] input;
        int k;
        List<String> expected;

        TestCase(String[] input, int k, List<String> expected) {
            this.input = input;
            this.k = k;
            this.expected = expected;
        }
    }
}
