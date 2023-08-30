package com.interview.notes.code.Aug23.test3;

/*
Given an array of n elements that contains elements from 0 to n-1, with any of these numbers appearing any number of times.
Find these repeating numbers in O(n) time complexity and using only constant memory space O(1).
 */
public class RepeatingChars {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 1, 3, 6, 6, 7, 7};
        findAndPrintRepeatingNumbers(arr);
    }

    public static void findAndPrintRepeatingNumbers(int[] arr) {
        int n = arr.length;

        // Increment arr[arr[i] % n] by n for all elements in the array
        for (int i = 0; i < n; i++) {
            arr[arr[i] % n] += n;
        }

        // Find indexes with values greater than or equal to n
        for (int i = 0; i < n; i++) {
            if (arr[i] / n > 1) {
                System.out.print(i + " ");
            }

            // Restore the original value of arr[i]
            arr[i] %= n;
        }

        System.out.println();
    }
}
