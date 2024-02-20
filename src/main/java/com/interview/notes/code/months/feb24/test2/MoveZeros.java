package com.interview.notes.code.months.feb24.test2;

public class MoveZeros {
    public static void moveZerosToEnd(int[] arr) {
        int nonZeroIndex = 0; // Track where the next non-zero should go

        // Move non-zero elements to the front, maintaining order
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                // Swap if current element is non-zero and i != nonZeroIndex
                if (i != nonZeroIndex) {
                    int temp = arr[i];
                    arr[i] = arr[nonZeroIndex];
                    arr[nonZeroIndex] = temp;
                }
                nonZeroIndex++;
            }
        }

        // No need to explicitly move zeros to the end, as they are already there
    }

    public static void main(String[] args) {
        int[] arr = {0, 5, 10, 2, 0, 0, 15, 20, 0, 0, 10, 0, 0};
        moveZerosToEnd(arr);
        for (int num : arr) {
            System.out.print(num + ", ");
        }
    }
}
