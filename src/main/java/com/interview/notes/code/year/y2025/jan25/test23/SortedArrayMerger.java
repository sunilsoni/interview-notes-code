package com.interview.notes.code.year.y2025.jan25.test23;

public class SortedArrayMerger {
    public static void mergeSortArrays(int[] A, int[] B) {
        for (int i = 0; i < B.length; i++) {
            if (B[i] < A[A.length - 1]) {
                // Swap with last element of A
                int temp = A[A.length - 1];
                A[A.length - 1] = B[i];
                B[i] = temp;

                // Sort A
                for (int j = A.length - 1; j > 0; j--) {
                    if (A[j] < A[j - 1]) {
                        temp = A[j];
                        A[j] = A[j - 1];
                        A[j - 1] = temp;
                    }
                }
            }
        }

        // Sort B
        for (int i = 0; i < B.length - 1; i++) {
            for (int j = 0; j < B.length - 1 - i; j++) {
                if (B[j] > B[j + 1]) {
                    int temp = B[j];
                    B[j] = B[j + 1];
                    B[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Original example
        testCase(
                new int[]{0, 2, 5, 8},
                new int[]{1, 6, 7},
                new int[]{0, 1, 2, 5},
                new int[]{6, 7, 8}
        );

        // Test Case 2: Different sizes
        testCase(
                new int[]{1, 3, 5, 7},
                new int[]{2, 4},
                new int[]{1, 2, 3, 4},
                new int[]{5, 7}
        );

        // Test Case 3: Equal lengths
        testCase(
                new int[]{1, 4, 7},
                new int[]{2, 5, 8},
                new int[]{1, 2, 4},
                new int[]{5, 7, 8}
        );

        // Test Case 4: Large numbers
        testCase(
                new int[]{100, 300, 500, 700},
                new int[]{200, 400},
                new int[]{100, 200, 300, 400},
                new int[]{500, 700}
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
