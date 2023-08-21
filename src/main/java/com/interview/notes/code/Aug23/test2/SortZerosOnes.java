package com.interview.notes.code.Aug23.test2;

import java.util.Arrays;

public class SortZerosOnes {
    public static void main(String[] args) {
        int[] input = {1, 0, 0, 1, 1, 1, 0, 1};
        sortZerosOnes(input);
        System.out.println(Arrays.toString(input));
    }

    public static void sortZerosOnes(int[] arr) {
        int left = 0;            // Initialize the left pointer at the beginning of the array
        int right = arr.length - 1; // Initialize the right pointer at the end of the array

        while (left < right) {   // Continue until the left pointer surpasses the right pointer
            // Move the left pointer to the right until we find a 0
            while (arr[left] == 1 && left < right) {
                left++;
            }
            // Move the right pointer to the left until we find a 1
            while (arr[right] == 0 && left < right) {
                right--;
            }

            if (left < right) {
                // Swap arr[left] and arr[right]
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left++;           // Move the left pointer to the next element
                right--;          // Move the right pointer to the previous element
            }
        }
    }
}
