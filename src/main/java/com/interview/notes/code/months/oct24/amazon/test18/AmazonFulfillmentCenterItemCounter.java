package com.interview.notes.code.months.oct24.amazon.test18;

import java.util.*;

public class AmazonFulfillmentCenterItemCounter {

    public static List<Long> getSmallerItems(List<Integer> items, List<Integer> start, List<Integer> end, List<Integer> query) {
        int n = items.size();
        int m = start.size();

        // Step 1: Calculate frequency of each item being picked using prefix sum
        long[] freq = new long[n + 1]; // n+1 to avoid index out of bounds
        for (int i = 0; i < m; i++) {
            int s = start.get(i);
            int e = end.get(i);
            freq[s] += 1;
            if (e + 1 < n) {
                freq[e + 1] -= 1;
            }
        }

        // Compute prefix sum to get actual frequencies
        for (int i = 1; i < n; i++) {
            freq[i] += freq[i - 1];
        }

        // Step 2: Pair each item's value with its frequency
        List<ItemFrequency> itemFreqList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (freq[i] > 0) { // Only consider items that are picked at least once
                itemFreqList.add(new ItemFrequency(items.get(i), freq[i]));
            }
        }

        // Step 3: Sort the items based on their values
        Collections.sort(itemFreqList, Comparator.comparingInt(a -> a.value));

        // Step 4: Create a prefix sum of frequencies
        long[] prefixSum = new long[itemFreqList.size()];
        for (int i = 0; i < itemFreqList.size(); i++) {
            prefixSum[i] = itemFreqList.get(i).frequency;
            if (i > 0) {
                prefixSum[i] += prefixSum[i - 1];
            }
        }

        // Step 5: Process each query using binary search
        List<Long> result = new ArrayList<>();
        for (Integer q : query) {
            int pos = binarySearch(itemFreqList, q);
            if (pos == -1) {
                result.add(0L);
            } else {
                result.add(prefixSum[pos]);
            }
        }

        return result;
    }

    // Helper method to perform binary search
    // Returns the last index where item.value < target
    private static int binarySearch(List<ItemFrequency> list, int target) {
        int left = 0;
        int right = list.size() - 1;
        int res = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (list.get(mid).value < target) {
                res = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return res;
    }

    // Method to compare two lists of Longs
    private static boolean compareLists(List<Long> a, List<Long> b) {
        if (a.size() != b.size()) return false;
        for (int i = 0; i < a.size(); i++) {
            if (!a.get(i).equals(b.get(i))) return false;
        }
        return true;
    }

    // Method to print list
    private static String listToString(List<Long> list) {
        StringBuilder sb = new StringBuilder();
        for (Long num : list) {
            sb.append(num).append(" ");
        }
        return sb.toString().trim();
    }

    // Main method with test cases
    public static void main(String[] args) {
        // Define test cases
        List<TestCase> testCases = new ArrayList<>();

        // Sample Test Case 0
        // Corresponds to the first example in the problem statement
        testCases.add(new TestCase(
                Arrays.asList(1, 2, 5, 4, 5),
                Arrays.asList(0, 0, 1),
                Arrays.asList(1, 2, 2),
                Arrays.asList(2, 4),
                Arrays.asList(2L, 5L)
        ));

        // Sample Test Case 1
        // Corrected to match the second sample in the problem statement
        testCases.add(new TestCase(
                Arrays.asList(4, 4, 5, 3, 2),
                Arrays.asList(0, 1, 0, 2),
                Arrays.asList(4, 1, 2, 3), // Corrected end indices
                Arrays.asList(5, 4, 1),
                Arrays.asList(8L, 3L, 0L)
        ));

        // Additional Test Case 2: No orders
        testCases.add(new TestCase(
                Arrays.asList(1, 2, 3, 4, 5),
                new ArrayList<>(),
                new ArrayList<>(),
                Arrays.asList(1, 3, 5),
                Arrays.asList(0L, 0L, 0L)
        ));

        // Additional Test Case 3: All items picked once
        testCases.add(new TestCase(
                Arrays.asList(5, 1, 3, 2, 4),
                Arrays.asList(0, 1, 2, 3, 4),
                Arrays.asList(0, 1, 2, 3, 4),
                Arrays.asList(2, 3, 4, 5, 6),
                Arrays.asList(1L, 2L, 3L, 4L, 5L)
        ));

        // Additional Test Case 4: Large Input
        int largeN = 100000;
        int largeM = 100000;
        int largeQ = 100000;
        List<Integer> largeItems = new ArrayList<>(Collections.nCopies(largeN, 1));
        List<Integer> largeStart = new ArrayList<>();
        List<Integer> largeEnd = new ArrayList<>();
        for (int i = 0; i < largeM; i++) {
            largeStart.add(0);
            largeEnd.add(largeN - 1);
        }
        List<Integer> largeQuery = new ArrayList<>(Collections.nCopies(largeQ, 2));
        // Note: The expected output for the large test case is very large and not verified here
        testCases.add(new TestCase(
                largeItems,
                largeStart,
                largeEnd,
                largeQuery,
                null // We won't check the output for this large test case
        ));

        // Process each test case
        int testCaseNumber = 1;
        for (TestCase tc : testCases) {
            List<Long> actual;
            if (tc.expectedOutput != null) {
                actual = getSmallerItems(tc.items, tc.start, tc.end, tc.query);
                boolean pass = compareLists(actual, tc.expectedOutput);
                System.out.println("Test Case " + testCaseNumber + ": " + (pass ? "PASS" : "FAIL"));
                if (!pass) {
                    System.out.println("Expected: " + listToString(tc.expectedOutput));
                    System.out.println("Actual:   " + listToString(actual));
                }
            } else {
                // For large test case, just run without checking
                long startTime = System.currentTimeMillis();
                actual = getSmallerItems(tc.items, tc.start, tc.end, tc.query);
                long endTime = System.currentTimeMillis();
                System.out.println("Test Case " + testCaseNumber + " (Large Input): Completed in " + (endTime - startTime) + " ms");
            }
            testCaseNumber++;
        }
    }

    // Helper class to store item value and its frequency
    static class ItemFrequency {
        int value;
        long frequency;

        ItemFrequency(int value, long frequency) {
            this.value = value;
            this.frequency = frequency;
        }
    }

    // Helper class to store test case data
    static class TestCase {
        List<Integer> items;
        List<Integer> start;
        List<Integer> end;
        List<Integer> query;
        List<Long> expectedOutput;

        TestCase(List<Integer> items, List<Integer> start, List<Integer> end, List<Integer> query, List<Long> expectedOutput) {
            this.items = items;
            this.start = start;
            this.end = end;
            this.query = query;
            this.expectedOutput = expectedOutput;
        }
    }
}
