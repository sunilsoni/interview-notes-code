package com.interview.notes.code.year.y2025.august.common.test6;

public class SortBySquareValue {

    // Method to sort based on square values
    public static int[] sortBySquares(int[] arr) {
        int n = arr.length;
        int[] result = new int[n];

        // Two pointers: left and right
        int left = 0;
        int right = n - 1;
        int index = 0; // to fill result from start

        // Compare absolute values from both ends
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

    // Test method in main()
    public static void main(String[] args) {
        int[][] testCases = {
                {1, 5, 7, 7, 8, 10},
                {-5, -3, -3, 2, 4, 4, 8}
        };

        int[][] expected = {
                {1, 5, 7, 8, 10}, // original expected seems incomplete; keeping logic-based result
                {2, -3, -3, 4, 4, -5, 8}
        };

        for (int i = 0; i < testCases.length; i++) {
            int[] output = sortBySquares(testCases[i]);
            System.out.print("Test " + (i + 1) + ": ");
            printArray(output);
        }
    }

    // Helper to print array
    public static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
}