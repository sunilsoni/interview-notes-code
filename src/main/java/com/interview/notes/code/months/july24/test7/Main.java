package com.interview.notes.code.months.july24.test7;

/*
Given a positive sorted array
a= 1 3, 4, 6, 9, 10, 12, 14, 15, 17, 19, 21 1;
Define a function fla, x) that returns x, the next smallest number, or -1 for errors.
i.e.
f(a, 12) = 12
f(a, 13) = 12
 */
public class Main {
    public static void main(String[] args) {
        int[] a = {1, 3, 4, 6, 9, 10, 12, 14, 15, 17, 19, 21};
        // Test cases
        System.out.println(findFloor(a, 12)); // Should return 12
        System.out.println(findFloor(a, 13)); // Should return 12
        System.out.println(findFloor(a, 22)); // Should return 21
        System.out.println(findFloor(a, 0));  // Should return -1
        System.out.println(findFloor(a, 1));  // Should return 1
    }

    /**
     * This function returns the largest number in the sorted array that is less than or equal to x.
     * If x is smaller than the smallest number in the array, it returns -1.
     *
     * @param arr The sorted array of integers.
     * @param x   The target number.
     * @return The largest number in arr that is <= x, or -1 if no such number exists.
     */
    public static int findFloor(int[] arr, int x) {
        if (arr == null || arr.length == 0) {
            return -1; // Error case: empty or null array
        }

        int left = 0;
        int right = arr.length - 1;
        int res = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == x) {
                return arr[mid]; // Exact match found
            } else if (arr[mid] < x) {
                res = arr[mid]; // Potential answer, look for a larger one
                left = mid + 1;
            } else {
                right = mid - 1; // Look for a smaller number
            }
        }

        return res; // Return the largest number <= x, or -1 if x is smaller than all elements
    }
}
