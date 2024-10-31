package com.interview.notes.code.months.oct24.amazon.test17;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WarehouseItemCounter {
    public static List<Long> getSmallerItems(List<Integer> items, List<Integer> start,
                                             List<Integer> end, List<Integer> query) {
        List<Long> result = new ArrayList<>();
        List<Integer> pickedItems = new ArrayList<>();

        // Collect all picked items based on start and end ranges
        for (int i = 0; i < start.size(); i++) {
            for (int j = start.get(i); j <= end.get(i); j++) {
                pickedItems.add(items.get(j));
            }
        }

        // Count items less than each query value
        for (int q : query) {
            long count = pickedItems.stream()
                    .filter(item -> item < q)
                    .count();
            result.add(count);
        }

        return result;
    }

    public static void main(String[] args) {
        // Test Case 0
        List<Integer> items0 = Arrays.asList(1, 2, 3, 2, 4, 1);
        List<Integer> start0 = Arrays.asList(2, 0);
        List<Integer> end0 = Arrays.asList(4, 0);
        List<Integer> query0 = Arrays.asList(5, 3);
        testCase(0, items0, start0, end0, query0, Arrays.asList(4L, 2L));

        // Test Case 1
        List<Integer> items1 = Arrays.asList(4, 4, 5, 3, 2);
        List<Integer> start1 = Arrays.asList(0, 1, 0, 2);
        List<Integer> end1 = Arrays.asList(1, 2, 2, 4);
        List<Integer> query1 = Arrays.asList(5, 4, 1);
        testCase(1, items1, start1, end1, query1, Arrays.asList(8L, 3L, 0L));

        // Large Data Test Case
        testLargeDataCase();
    }

    private static void testCase(int caseNum, List<Integer> items, List<Integer> start,
                                 List<Integer> end, List<Integer> query, List<Long> expected) {
        System.out.println("Test Case " + caseNum + ":");
        System.out.println("Items: " + items);
        System.out.println("Start indices: " + start);
        System.out.println("End indices: " + end);
        System.out.println("Queries: " + query);

        long startTime = System.currentTimeMillis();
        List<Long> result = getSmallerItems(items, start, end, query);
        long endTime = System.currentTimeMillis();

        System.out.println("Expected: " + expected);
        System.out.println("Got: " + result);
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
        System.out.println("Status: " + (result.equals(expected) ? "PASS" : "FAIL"));
        System.out.println();
    }

    private static void testLargeDataCase() {
        int size = 10000;
        List<Integer> items = new ArrayList<>();
        List<Integer> start = new ArrayList<>();
        List<Integer> end = new ArrayList<>();
        List<Integer> query = new ArrayList<>();

        // Generate large test data
        for (int i = 0; i < size; i++) {
            items.add(i % 100);
            if (i < size / 2) {
                start.add(i);
                end.add(Math.min(i + 10, size - 1));
            }
            if (i < 100) {
                query.add(i);
            }
        }

        System.out.println("Large Data Test Case:");
        System.out.println("Items size: " + items.size());
        System.out.println("Ranges count: " + start.size());
        System.out.println("Queries count: " + query.size());

        long startTime = System.currentTimeMillis();
        List<Long> result = getSmallerItems(items, start, end, query);
        long endTime = System.currentTimeMillis();

        System.out.println("Time taken: " + (endTime - startTime) + "ms");
        System.out.println("Result size: " + result.size());
        System.out.println();
    }
}