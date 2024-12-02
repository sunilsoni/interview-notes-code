package com.interview.notes.code.year.y2024.jan24.test4;

public class ArrayRotation {

    public static void main(String[] args) {
        int[] exampleArray = {1, 2, 3, 4, 5};
        int numPositions = 2;

        rotateArray(exampleArray, numPositions);

        // Print rotated array
        for (int i = 0; i < exampleArray.length; i++) {
            System.out.print(exampleArray[i] + " ");
        }
    }

    public static void rotateArray(int[] arr, int numPositions) {
        int n = arr.length;

        // Ensure numPositions is within the bounds of the array length
        numPositions = numPositions % n;

        reverse(arr, 0, n - 1);
        reverse(arr, 0, numPositions - 1);
        reverse(arr, numPositions, n - 1);
    }

    private static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }
}
