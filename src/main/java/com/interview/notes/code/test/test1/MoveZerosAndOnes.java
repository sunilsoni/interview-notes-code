package com.interview.notes.code.test.test1;

import java.util.Arrays;

public class MoveZerosAndOnes {
    public static void main(String[] args) {
        int[] arr = {1, 0, 1, 0, 0, 1};

        System.out.println("Original array: " + Arrays.toString(arr));

        moveZerosAndOnes(arr);

        System.out.println("Array after moving zeros and ones: " + Arrays.toString(arr));
    }

    public static void moveZerosAndOnes(int[] arr) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            if (arr[left] == 1) {
                left++;
            } else if (arr[right] == 0) {
                right--;
            } else {
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left++;
                right--;
            }
        }
    }


}
