package com.interview.notes.code.months.oct24.codereport.test1;

import java.util.*;

public class MergeSortedLists {

    public static Set<Integer> mergeLists(List<Integer> list1, List<Integer> list2) {
        Set<Integer> mergedSet = new TreeSet<>();
        mergedSet.addAll(list1);
        mergedSet.addAll(list2);
        return mergedSet;
    }

    public static void main(String[] args) {
        // Test case 1: Basic test
        List<Integer> list1 = Arrays.asList(1, 4, 2, 5);
        List<Integer> list2 = Arrays.asList(7, 9, 0, 8);
        Set<Integer> expected1 = new TreeSet<>(Arrays.asList(0, 1, 2, 4, 5, 7, 8, 9));
        testCase(list1, list2, expected1, "Basic test");

        // Test case 2: Empty lists
        List<Integer> emptyList = Collections.emptyList();
        Set<Integer> expected2 = Collections.emptySet();
        testCase(emptyList, emptyList, expected2, "Empty lists");

        // Test case 3: One empty list
        List<Integer> nonEmptyList = Arrays.asList(1, 2, 3);
        Set<Integer> expected3 = new TreeSet<>(Arrays.asList(1, 2, 3));
        testCase(nonEmptyList, emptyList, expected3, "One empty list");

        // Test case 4: Duplicate numbers
        List<Integer> duplicateList1 = Arrays.asList(1, 2, 2, 3, 3, 4);
        List<Integer> duplicateList2 = Arrays.asList(2, 3, 4, 4, 5);
        Set<Integer> expected4 = new TreeSet<>(Arrays.asList(1, 2, 3, 4, 5));
        testCase(duplicateList1, duplicateList2, expected4, "Duplicate numbers");

        // Test case 5: Negative numbers
        List<Integer> negativeList1 = Arrays.asList(-5, -3, -1, 0);
        List<Integer> negativeList2 = Arrays.asList(-4, -2, 1, 2);
        Set<Integer> expected5 = new TreeSet<>(Arrays.asList(-5, -4, -3, -2, -1, 0, 1, 2));
        testCase(negativeList1, negativeList2, expected5, "Negative numbers");

        // Test case 6: Large data input
        List<Integer> largeList1 = new ArrayList<>();
        List<Integer> largeList2 = new ArrayList<>();
        Set<Integer> expectedLarge = new TreeSet<>();
        for (int i = 0; i < 100000; i++) {
            int num = (int) (Math.random() * 200000 - 100000);
            if (i % 2 == 0) {
                largeList1.add(num);
            } else {
                largeList2.add(num);
            }
            expectedLarge.add(num);
        }
        testCase(largeList1, largeList2, expectedLarge, "Large data input");
    }

    private static void testCase(List<Integer> list1, List<Integer> list2, Set<Integer> expected, String testName) {
        long startTime = System.nanoTime();
        Set<Integer> result = mergeLists(list1, list2);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000; // Convert to milliseconds

        boolean passed = result.equals(expected);
        System.out.println(testName + ": " + (passed ? "PASS" : "FAIL"));
        System.out.println("Execution time: " + duration + " ms");

        if (!passed) {
            System.out.println("Expected: " + expected);
            System.out.println("Actual: " + result);
        }
        System.out.println();
    }
}
