package com.interview.notes.code.months.aug24.test18;

import java.util.Arrays;

public class ArrayRotation {

    public static void rotateLeft2(int[] arr, int n) {
        if (arr == null || arr.length <= 1 || n <= 0) {
            return;
        }

        int length = arr.length;
        n = n % length;  // Normalize rotation count

        // Create a temporary array to store rotated elements
        int[] temp = new int[n];

        // Copy the first n elements to temp array
        for (int i = 0; i < n; i++) {
            temp[i] = arr[i];
        }

        // Shift the remaining elements to the left
        for (int i = n; i < length; i++) {
            arr[i - n] = arr[i];
        }

        // Copy the elements from temp back to the end of arr
        for (int i = 0; i < n; i++) {
            arr[length - n + i] = temp[i];
        }
    }


    public static void rotateLeft(int[] arr, int n) {
        if (arr == null || arr.length <= 1 || n <= 0) {
            return;
        }

        int length = arr.length;
        n = n % length; // Normalize rotation count

        // Reverse the entire array
        reverse(arr, 0, length - 1);
        // Reverse the first length-n elements
        reverse(arr, 0, length - n - 1);
        // Reverse the last n elements
        reverse(arr, length - n, length - 1);
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

    public static void main(String[] args) {
        // Example 1
        int[] arr1 = {1, 2, 3, 4, 5};
        int n1 = 2;
        System.out.println("Example 1 - Original: " + Arrays.toString(arr1));
        rotateLeft(arr1, n1);
        System.out.println("Rotated " + n1 + " times: " + Arrays.toString(arr1));

        // Example 2
        int[] arr2 = {3, 8, 9, 7, 6};
        int n2 = 3;
        System.out.println("\nExample 2 - Original: " + Arrays.toString(arr2));
        rotateLeft(arr2, n2);
        System.out.println("Rotated " + n2 + " times: " + Arrays.toString(arr2));

        // Example 3 (Edge case: empty array)
        int[] arr3 = {};
        int n3 = 4;
        System.out.println("\nExample 3 - Original: " + Arrays.toString(arr3));
        rotateLeft(arr3, n3);
        System.out.println("Rotated " + n3 + " times: " + Arrays.toString(arr3));

        // Example 4 (Edge case: rotation count greater than array length)
        int[] arr4 = {1, 2, 3, 4};
        int n4 = 6;
        System.out.println("\nExample 4 - Original: " + Arrays.toString(arr4));
        rotateLeft(arr4, n4);
        System.out.println("Rotated " + n4 + " times: " + Arrays.toString(arr4));

        // Example 5 (Edge case: single element array)
        int[] arr5 = {42};
        int n5 = 2;
        System.out.println("\nExample 5 - Original: " + Arrays.toString(arr5));
        rotateLeft(arr5, n5);
        System.out.println("Rotated " + n5 + " times: " + Arrays.toString(arr5));

        // Example 5 (Edge case: single element array)
        int[] arr6 = {8, 5, 6, 7, 9, 0, 4, 2, 5};
        int n6 = 3;
        System.out.println("\nExample 5 - Original: " + Arrays.toString(arr6));
        rotateLeft(arr5, n5);
        System.out.println("Rotated " + n5 + " times: " + Arrays.toString(arr5));
    }
}
