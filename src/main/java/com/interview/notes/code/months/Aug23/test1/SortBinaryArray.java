package com.interview.notes.code.months.Aug23.test1;

public class SortBinaryArray {
    public static void main(String[] args) {
        int[] arr = {1, 0, 0, 1, 1, 1, 0, 1};
        //int[] sortedArr = sortZerosOnes(arr);

        //for (int val : sortedArr) {
        //  System.out.print(val + " ");
        //}
    }

    public static void sortZerosOnes(int[] arr) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            while (arr[left] == 1 && left < right) {
                left++;
            }
            while (arr[right] == 0 && left < right) {
                right--;
            }

            if (left < right) {
                // Swap arr[left] and arr[right]
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left++;
                right--;
            }
        }
    }

    public static int[] sortBinaryArray(int[] arr) {
        int zeroCount = 0; // Count of zeros
        int oneCount = 0;  // Count of ones

        // Iterate through the array to count zeros and ones
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                zeroCount++;
            } else {
                oneCount++;
            }
        }

        // Fill the array with ones and then with zeros
        for (int i = 0; i < oneCount; i++) {
            arr[i] = 1;
        }
        for (int i = oneCount; i < oneCount + zeroCount; i++) {
            arr[i] = 0;
        }

        return arr;
    }
}
