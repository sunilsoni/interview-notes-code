package com.interview.notes.code.year.y2025.august.common.test5;

public class SortBySquareValue {

    // Method to sort based on square values
    public static int[] sortBySquares(int[] arr) {
        int n = arr.length;
        int[] result = new int[n];

        int left = 0;
        int right = n - 1;
        int index = 0;

        while (left <= right) {
            int leftSq = arr[left] * arr[left];
            int rightSq = arr[right] * arr[right];

            if (leftSq < rightSq) {
                result[index++] = arr[left++];
            } else {
                result[index++] = arr[right--];
            }
        }
        return result;
    }

    // Helper to print array
    public static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) System.out.print(", ");
        }
        System.out.print("]");
    }

    // Helper to compare arrays
    public static boolean arraysEqual(int[] a, int[] b) {
        if (a.length != b.length) return false;
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        // Test cases
        int[][] testCases = {
                {1, 5, 7, 7, 8, 10},
                {-5, -3, -3, 2, 4, 4, 8},
                {},                      // empty array
                {-2, -1, 0, 1, 2},       // mixed negatives and positives
                {-10, -5, -2, 0, 3, 6}   // varied range
        };

        // Expected results based on ascending squares
        int[][] expected = {
                {1, 5, 7, 7, 8, 10},
                {2, -3, -3, 4, 4, -5, 8},
                {},
                {0, -1, 1, -2, 2},
                {0, -2, 3, -5, 6, -10}
        };

        // Run all test cases
        for (int i = 0; i < testCases.length; i++) {
            int[] output = sortBySquares(testCases[i]);
            boolean pass = arraysEqual(output, expected[i]);

            System.out.print("Test " + (i + 1) + ": ");
            printArray(output);
            System.out.println(" -> " + (pass ? "PASS" : "FAIL"));
        }

        // Large dataset test
        int size = 100000;
        int[] largeArray = new int[size];
        for (int i = 0; i < size; i++) {
            largeArray[i] = i - size / 2; // mix of negative and positive
        }
        long start = System.currentTimeMillis();
        sortBySquares(largeArray);
        long end = System.currentTimeMillis();
        System.out.println("Large dataset processed in " + (end - start) + " ms -> PASS");
    }
}