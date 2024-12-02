package com.interview.notes.code.year.y2023.Sep23.test2;

public class RotateArray {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        rightRotateByOne(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    public static void rightRotateByOne(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        int lastElement = arr[arr.length - 1];
        for (int i = arr.length - 1; i > 0; i--) {
            arr[i] = arr[i - 1];
        }
        arr[0] = lastElement;
    }
}
