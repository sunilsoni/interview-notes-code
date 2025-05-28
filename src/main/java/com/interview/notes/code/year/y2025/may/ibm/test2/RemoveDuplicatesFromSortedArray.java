package com.interview.notes.code.year.y2025.may.ibm.test2;

import java.util.Arrays;

public class RemoveDuplicatesFromSortedArray {
    // Removes duplicates in-place, fills unused slots with null, returns count of unique elements
    static int removeDuplicates(Integer[] arr, int n) {
        // Edge case: Empty array or null
        if (arr == null || n == 0) return 0;

        // Pointer for position of next unique element
        int j = 0; // index for placing next unique

        // Traverse from second element onwards
        for (int i = 1; i < n; i++) {
            // If current element is different from the last unique
            if (!arr[i].equals(arr[j])) {
                j++;             // Move write pointer ahead
                arr[j] = arr[i]; // Copy unique value
            }
            // else, do nothing (skip duplicates)
        }

        // Fill the remaining slots with nulls (from j+1 onwards)
        for (int k = j + 1; k < n; k++) {
            arr[k] = null;
        }

        // Return the count of unique elements (j+1)
        return j + 1;
    }

    // Utility to test and print PASS/FAIL for one test case
    static void testCase(Integer[] input, Integer[] expected, String name) {
        Integer[] arr = Arrays.copyOf(input, input.length); // clone for test
        int n = removeDuplicates(arr, arr.length);
        boolean pass = Arrays.equals(arr, expected);

        System.out.printf("%-30s: %s\n", name, pass ? "PASS" : "FAIL");
        if (!pass) {
            System.out.println("Expected: " + Arrays.toString(expected));
            System.out.println("Got     : " + Arrays.toString(arr));
        }
    }

    // Main method for all tests, including large data
    public static void main(String[] args) {
        // Basic/edge test cases
        testCase(new Integer[]{2, 2}, new Integer[]{2, null}, "All same elements");
        testCase(new Integer[]{1, 2, 2, 3, 4, 4}, new Integer[]{1, 2, 3, 4, null, null}, "Example with some dups");
        testCase(new Integer[]{1, 2, 3, 4}, new Integer[]{1, 2, 3, 4}, "No duplicates");
        testCase(new Integer[]{}, new Integer[]{}, "Empty array");
        testCase(new Integer[]{5}, new Integer[]{5}, "Single element");
        testCase(new Integer[]{-2, -2, -1, 0, 0, 1, 1, 2}, new Integer[]{-2, -1, 0, 1, 2, null, null, null}, "Negative and zero");

        // Large test case (100,000 elements, all same)
        Integer[] bigArr = new Integer[100_000];
        Arrays.fill(bigArr, 1);
        Integer[] bigExpected = new Integer[100_000];
        bigExpected[0] = 1;
        for (int i = 1; i < 100_000; i++) bigExpected[i] = null;
        testCase(bigArr, bigExpected, "Large: all duplicates (1e5)");

        // Large test case (unique values)
        Integer[] uniqArr = new Integer[100_000];
        Integer[] uniqExp = new Integer[100_000];
        for (int i = 0; i < 100_000; i++) uniqArr[i] = uniqExp[i] = i;
        testCase(uniqArr, uniqExp, "Large: all unique (1e5)");
    }
}
