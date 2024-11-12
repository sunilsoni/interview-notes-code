package com.interview.notes.code.months.nov24.amazon.test15;

import java.util.*;

public class Solution {

    /**
     * Function to count the number of picked items with values strictly less than each query value.
     *
     * @param items List of integers representing the value of each item.
     * @param start List of integers representing the start index for each order.
     * @param end   List of integers representing the end index for each order.
     * @param query List of integers representing the query values.
     * @return List of longs where each element corresponds to the answer for each query.
     */
    public static List<Long> getSmallerItems(List<Integer> items, List<Integer> start, List<Integer> end, List<Integer> query) {
        int n = items.size();
        int m = start.size();
        int q = query.size();

        // Step 1: Compute frequency of each item being picked using prefix sums
        long[] freq = new long[n + 1]; // Using long to prevent overflow

        for (int i = 0; i < m; i++) {
            int s = start.get(i);
            int e = end.get(i);
            freq[s] += 1;
            if (e + 1 < n) {
                freq[e + 1] -= 1;
            }
        }

        // Compute the prefix sum to get the actual frequencies
        for (int i = 1; i < n; i++) {
            freq[i] += freq[i - 1];
        }

        // Now, freq[i] represents how many times item i was picked
        // Pair each item's value with its frequency
        Map<Integer, Long> valueToFreq = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (freq[i] > 0) { // Only consider picked items
                int val = items.get(i);
                valueToFreq.put(val, valueToFreq.getOrDefault(val, 0L) + freq[i]);
            }
        }

        // Extract unique values and sort them
        List<Integer> uniqueValues = new ArrayList<>(valueToFreq.keySet());
        Collections.sort(uniqueValues);

        // Compute prefix sums of frequencies
        long[] prefixSums = new long[uniqueValues.size()];
        prefixSums[0] = valueToFreq.get(uniqueValues.get(0));
        for (int i = 1; i < uniqueValues.size(); i++) {
            prefixSums[i] = prefixSums[i - 1] + valueToFreq.get(uniqueValues.get(i));
        }

        // For each query, perform binary search to find the number of items with value < query[i]
        List<Long> result = new ArrayList<>();
        for (int qVal : query) {
            // Find the first index where uniqueValues.get(index) >= qVal
            int idx = lowerBound(uniqueValues, qVal);
            if (idx == 0) {
                result.add(0L);
            } else {
                result.add(prefixSums[idx - 1]);
            }
        }

        return result;
    }

    /**
     * Helper function to find the first index in the sorted list where the element is >= target.
     *
     * @param list   Sorted list of integers.
     * @param target Target integer to compare.
     * @return The first index where list.get(index) >= target.
     */
    private static int lowerBound(List<Integer> list, int target) {
        int left = 0;
        int right = list.size(); // Exclusive

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (list.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    /**
     * Main method to test the getSmallerItems function with various test cases.
     * <p>
     * Outputs "PASS" or "FAIL" for each test case based on expected results.
     */
    public static void main(String[] args) {
        // Define test cases
        List<TestCase> testCases = new ArrayList<>();

        // Sample Case 1
        testCases.add(new TestCase(
                "Sample Case 1",
                Arrays.asList(4, 4, 5, 3, 2),
                Arrays.asList(0, 1, 0, 2),
                Arrays.asList(1, 2, 3, 4),
                Arrays.asList(5, 4, 1),
                Arrays.asList(8L, 3L, 0L)
        ));

        // Sample Case 0
        testCases.add(new TestCase(
                "Sample Case 0",
                Arrays.asList(1, 2, 3, 2, 4, 1),
                Arrays.asList(2, 0),
                Arrays.asList(4, 0),
                Arrays.asList(5, 3),
                Arrays.asList(4L, 2L)
        ));

        // Additional Test Case: No Orders
        testCases.add(new TestCase(
                "No Orders",
                Arrays.asList(10, 20, 30),
                new ArrayList<>(),
                new ArrayList<>(),
                Arrays.asList(15, 25, 35),
                Arrays.asList(0L, 0L, 0L)
        ));

        // Additional Test Case: All Items Picked Once
        testCases.add(new TestCase(
                "All Items Picked Once",
                Arrays.asList(5, 1, 3, 2, 4),
                Arrays.asList(0),
                Arrays.asList(4),
                Arrays.asList(3, 5),
                Arrays.asList(2L, 4L)
        ));

        // Additional Test Case: Large Input
        testCases.add(new TestCase(
                "Large Input",
                generateList(100000, 100), // items: 100, 100, ..., 100 (100000 times)
                generateList(100000, 0),   // start: all 0
                generateList(100000, 99999), // end: all 99999
                Arrays.asList(50, 100, 150),
                Arrays.asList(0L, 100000L, 100000L)
        ));

        // Run all test cases
        int passed = 0;
        for (TestCase tc : testCases) {
            List<Long> output = getSmallerItems(tc.items, tc.start, tc.end, tc.query);
            if (output.equals(tc.expectedOutput)) {
                System.out.println(tc.name + ": PASS");
                passed++;
            } else {
                System.out.println(tc.name + ": FAIL");
                System.out.println("Expected: " + tc.expectedOutput);
                System.out.println("Got     : " + output);
            }
        }

        System.out.println("\n" + passed + " out of " + testCases.size() + " test cases passed.");
    }

    /**
     * Helper method to generate a list with a specific size and all elements set to a given value.
     *
     * @param size  The size of the list.
     * @param value The value to fill the list with.
     * @return The generated list.
     */
    private static List<Integer> generateList(int size, int value) {
        List<Integer> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(value);
        }
        return list;
    }

    /**
     * Inner class to represent a test case.
     */
    private static class TestCase {
        String name;
        List<Integer> items;
        List<Integer> start;
        List<Integer> end;
        List<Integer> query;
        List<Long> expectedOutput;

        TestCase(String name, List<Integer> items, List<Integer> start, List<Integer> end, List<Integer> query, List<Long> expectedOutput) {
            this.name = name;
            this.items = items;
            this.start = start;
            this.end = end;
            this.query = query;
            this.expectedOutput = expectedOutput;
        }
    }
}
