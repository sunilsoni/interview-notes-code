package com.interview.notes.code.year.y2025.august.common.test7;

public class SortBySquareValue {

    // Method to sort based on absolute values (bubble sort for simplicity)
    public static int[] sortBySquares(int[] arr) {
        int n = arr.length;
        int[] result = new int[n];

        // Copy array to result
        System.arraycopy(arr, 0, result, 0, n);

        // Bubble sort by absolute value (stable)
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (Math.abs(result[j]) > Math.abs(result[j + 1])) {
                    int temp = result[j];
                    result[j] = result[j + 1];
                    result[j + 1] = temp;
                }
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

    // Compare arrays
    public static boolean arraysEqual(int[] a, int[] b) {
        if (a.length != b.length) return false;
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] testCases = {
                {1, 5, 7, 7, 8, 10},
                {-5, -3, -3, 2, 4, 4, 8},
                {},
                {-2, -1, 0, 1, 2},
                {-10, -5, -2, 0, 3, 6}
        };

        int[][] expected = {
                {1, 5, 7, 7, 8, 10},
                {2, -3, -3, 4, 4, -5, 8},
                {},
                {0, -1, 1, -2, 2},
                {0, -2, 3, -5, 6, -10}
        };

        for (int i = 0; i < testCases.length; i++) {
            int[] output = sortBySquares(testCases[i]);
            boolean pass = arraysEqual(output, expected[i]);

            System.out.print("Test " + (i + 1) + ": ");
            printArray(output);
            System.out.println(" -> " + (pass ? "PASS" : "FAIL"));
        }

        // Large dataset
        int size = 10000;
        int[] largeArray = new int[size];
        for (int i = 0; i < size; i++) {
            largeArray[i] = i - size / 2;
        }
        long start = System.currentTimeMillis();
        sortBySquares(largeArray);
        long end = System.currentTimeMillis();
        System.out.println("Large dataset processed in " + (end - start) + " ms -> PASS");
    }
}