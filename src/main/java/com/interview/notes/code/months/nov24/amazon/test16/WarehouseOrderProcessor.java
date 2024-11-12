package com.interview.notes.code.months.nov24.amazon.test16;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WarehouseOrderProcessor {

    public static List<Long> getSmallerItems(List<Integer> items, List<Integer> start,
                                             List<Integer> end, List<Integer> query) {
        List<Integer> pickedItems = new ArrayList<>();

        // Collect all picked items from each order range
        for (int i = 0; i < start.size(); i++) {
            for (int j = start.get(i); j <= end.get(i); j++) {
                pickedItems.add(items.get(j));
            }
        }

        // Process each query
        List<Long> result = new ArrayList<>();
        for (int q : query) {
            long count = pickedItems.stream()
                    .filter(item -> item < q)
                    .count();
            result.add(count);
        }

        return result;
    }

    public static void main(String[] args) {
        // Test case runner
        testCases();
    }

    private static void testCases() {
        int testCaseCount = 0;
        int passedTests = 0;

        // Test Case 1 - Sample Case
        testCaseCount++;
        List<Integer> items1 = Arrays.asList(4, 4, 5, 3, 2);
        List<Integer> start1 = Arrays.asList(0, 1, 0, 2);
        List<Integer> end1 = Arrays.asList(1, 2, 3, 4);
        List<Integer> query1 = Arrays.asList(5, 4, 1);
        List<Long> expected1 = Arrays.asList(8L, 3L, 0L);

        List<Long> result1 = getSmallerItems(items1, start1, end1, query1);
        if (result1.equals(expected1)) {
            System.out.println("Test Case 1: PASSED");
            passedTests++;
        } else {
            System.out.println("Test Case 1: FAILED");
            System.out.println("Expected: " + expected1);
            System.out.println("Got: " + result1);
        }

        // Test Case 2 - Empty Query
        testCaseCount++;
        List<Integer> items2 = Arrays.asList(1, 2, 3);
        List<Integer> start2 = Arrays.asList(0);
        List<Integer> end2 = Arrays.asList(2);
        List<Integer> query2 = new ArrayList<>();
        List<Long> expected2 = new ArrayList<>();

        List<Long> result2 = getSmallerItems(items2, start2, end2, query2);
        if (result2.equals(expected2)) {
            System.out.println("Test Case 2: PASSED");
            passedTests++;
        } else {
            System.out.println("Test Case 2: FAILED");
        }

        // Test Case 3 - Large Data
        testCaseCount++;
        List<Integer> items3 = new ArrayList<>();
        List<Integer> start3 = new ArrayList<>();
        List<Integer> end3 = new ArrayList<>();

        // Generate large test data
        for (int i = 0; i < 10000; i++) {
            items3.add(i % 100);
            if (i < 1000) {
                start3.add(0);
                end3.add(i);
            }
        }
        List<Integer> query3 = Arrays.asList(50, 75, 100);

        try {
            List<Long> result3 = getSmallerItems(items3, start3, end3, query3);
            System.out.println("Test Case 3 (Large Data): PASSED");
            passedTests++;
        } catch (Exception e) {
            System.out.println("Test Case 3 (Large Data): FAILED");
            e.printStackTrace();
        }

        // Test Case 4 - Edge Case with Single Item
        testCaseCount++;
        List<Integer> items4 = Arrays.asList(5);
        List<Integer> start4 = Arrays.asList(0);
        List<Integer> end4 = Arrays.asList(0);
        List<Integer> query4 = Arrays.asList(6);
        List<Long> expected4 = Arrays.asList(1L);

        List<Long> result4 = getSmallerItems(items4, start4, end4, query4);
        if (result4.equals(expected4)) {
            System.out.println("Test Case 4: PASSED");
            passedTests++;
        } else {
            System.out.println("Test Case 4: FAILED");
        }

        // Summary
        System.out.println("\nTest Summary:");
        System.out.println("Total Tests: " + testCaseCount);
        System.out.println("Passed Tests: " + passedTests);
        System.out.println("Failed Tests: " + (testCaseCount - passedTests));
    }
}