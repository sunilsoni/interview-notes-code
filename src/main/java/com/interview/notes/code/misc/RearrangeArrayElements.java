package com.interview.notes.code.misc;

//https://www.geeksforgeeks.org/move-ve-elements-end-order-extra-space-allowed/
class RearrangeArrayElements {

    static void rearrange(int arr[], int n) {

        // Create an empty array to store result
        int temp[] = new int[n];

        // Traversal array and store +ve element in temp array
        int j = 0; // index of temp

        for (int i = 0; i < n; i++)
            if (arr[i] >= 0)
                temp[j++] = arr[i];

        // If array contains all positive or all negative.
        if (j == n || j == 0)
            return;

        // Store -ve element in temp array
        for (int i = 0; i < n; i++)
            if (arr[i] < 0)
                temp[j++] = arr[i];

        // Copy contents of temp[] to arr[]
        for (int i = 0; i < n; i++)
            arr[i] = temp[i];
    }


    public static void main(String[] args) {
        int arr[] = {1, -1, -3, -2, 7, 5, 11, 6};
        int n = arr.length;

        rearrange(arr, n);

        for (int i = 0; i < n; i++)
            System.out.print(arr[i] + " ");//1 7 5 11 6 -1 -3 -2
    }
}