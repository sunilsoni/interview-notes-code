package com.interview.notes.code.year.y2025.november.common.test1;

public class BinarySort {
    public static void sortBinaryArray(int[] arr) {
        if (arr == null || arr.length <= 1) return;

        int nextZero = 0; // pointer for next position of 0

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                // swap arr[i] with arr[nextZero]
                if (i != nextZero) {
                    arr[i] = 1;
                    arr[nextZero] = 0;
                }
                nextZero++;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 0, 1, 1, 0, 0, 1};
        sortBinaryArray(arr);
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}
