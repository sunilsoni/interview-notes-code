package com.interview.notes.code.year.y2023.june23.test11;

public class ArrayRotation {
    public static void rotateLeft(int[] arr, int d) {
        int n = arr.length;
        reverseArray(arr, 0, d - 1); // Reverse first d elements
        reverseArray(arr, d, n - 1); // Reverse remaining elements
        reverseArray(arr, 0, n - 1); // Reverse the whole array
    }

    public static void reverseArray(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        int d = 2;
        rotateLeft(arr, d);
        System.out.println("Rotated Array:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}
