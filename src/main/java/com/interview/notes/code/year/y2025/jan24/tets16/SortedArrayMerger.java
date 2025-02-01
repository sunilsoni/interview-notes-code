package com.interview.notes.code.year.y2025.jan24.tets16;

public class SortedArrayMerger {
    public static void mergeSortArrays(int[] A, int[] B) {
        int i = A.length - 1;
        int j = B.length - 1;

        // Start from end of both arrays
        while (i >= 0 && j >= 0) {
            if (A[i] > B[j]) {
                // Swap elements if A's element is larger
                int temp = A[i];
                A[i] = B[j];
                B[j] = temp;
            }
            i--;
            j--;
        }

        // Sort both arrays
        bubbleSort(A);
        bubbleSort(B);
    }

    private static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Normal case
        testCase(
                new int[]{0, 2, 5, 8},
                new int[]{1, 6, 7},
                new int[]{0, 1, 2, 5},
                new int[]{6, 7, 8}
        );

        // Test Case 2: Equal length arrays
        testCase(
                new int[]{2, 4, 6},
                new int[]{1, 3, 5},
                new int[]{1, 2, 3},
                new int[]{4, 5, 6}
        );

        // Test Case 3: Already sorted
        testCase(
                new int[]{1, 2, 3},
                new int[]{4, 5, 6},
                new int[]{1, 2, 3},
                new int[]{4, 5, 6}
        );

        // Test Case 4: Large numbers
        testCase(
                new int[]{100, 200, 300, 400},
                new int[]{150, 250, 350},
                new int[]{100, 150, 200, 250},
                new int[]{300, 350, 400}
        );
    }

    private static void testCase(int[] A, int[] B, int[] expectedA, int[] expectedB) {
        System.out.println("\nTest Case:");
        System.out.println("Input A: " + arrayToString(A));
        System.out.println("Input B: " + arrayToString(B));

        mergeSortArrays(A, B);

        boolean passed = arrayEquals(A, expectedA) && arrayEquals(B, expectedB);

        System.out.println("Result A: " + arrayToString(A));
        System.out.println("Result B: " + arrayToString(B));
        System.out.println("Test " + (passed ? "PASSED" : "FAILED"));
    }

    private static String arrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) sb.append(", ");
        }
        return sb.append("]").toString();
    }

    private static boolean arrayEquals(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length) return false;
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) return false;
        }
        return true;
    }
}
