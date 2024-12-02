package com.interview.notes.code.months.nov24.test15;

import java.util.*;

public class ArrayDifferenceFinder {

    public static int[] findMissingElements(int[] arr1, int[] arr2) {
        // Use HashSet for O(1) lookup
        Set<Integer> set = new HashSet<>();
        List<Integer> result = new ArrayList<>();

        // Add all elements from second array to set
        for (int num : arr2) {
            set.add(num);
        }

        // Check which elements from first array are not in set
        for (int num : arr1) {
            if (!set.contains(num)) {
                result.add(num);
            }
        }

        // Convert List to array
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        // Test cases
        runTest(
                new int[]{1, 2, 3, 4, 5},
                new int[]{2, 4},
                new int[]{1, 3, 5},
                "Basic test with positive numbers"
        );

        runTest(
                new int[]{},
                new int[]{1, 2, 3},
                new int[]{},
                "Empty first array"
        );

        runTest(
                new int[]{1, 2, 3},
                new int[]{},
                new int[]{1, 2, 3},
                "Empty second array"
        );

        // Large data test
        int[] largeArr1 = new int[100000];
        int[] largeArr2 = new int[90000];
        for (int i = 0; i < 100000; i++) {
            largeArr1[i] = i;
        }
        for (int i = 0; i < 90000; i++) {
            largeArr2[i] = i * 2;
        }
        runTest(
                largeArr1,
                largeArr2,
                null, // We don't check exact values for large test
                "Large data test"
        );
    }

    private static void runTest(int[] arr1, int[] arr2, int[] expected, String testName) {
        System.out.println("Running test: " + testName);

        long startTime = System.currentTimeMillis();
        int[] result = findMissingElements(arr1, arr2);
        long endTime = System.currentTimeMillis();

        if (expected != null) {
            boolean passed = Arrays.equals(result, expected);
            System.out.println("Status: " + (passed ? "PASS" : "FAIL"));
            if (!passed) {
                System.out.println("Expected: " + Arrays.toString(expected));
                System.out.println("Got: " + Arrays.toString(result));
            }
        } else {
            // For large data test, just print the execution time
            System.out.println("Execution completed in " + (endTime - startTime) + "ms");
        }
        System.out.println();
    }
}
