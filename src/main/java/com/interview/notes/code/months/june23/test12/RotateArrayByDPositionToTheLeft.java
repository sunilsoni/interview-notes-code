package com.interview.notes.code.months.june23.test12;

/**
 * In Java
 * <p>
 * <p>
 * Given an array arr[J of size N. the task is to rotate the array by d position to the left.
 * Examples:
 * Input: arr[] = {1, 2. 3. 4. 5. 6. 7). d = 2
 * Output: 3. 4. 5. 6. 7. 1. 2
 * Explanation: If the array is rotated by 1 position to the left,
 * it becomes [2. 3. 4. 5, 6. 7,1}.
 * When it is rotated further by 1 position.
 * it becomes: {3. 4. 5. 6. 7. 1. 2}
 * Input: arr[] = {1. 6. 7. 8}. d = 3
 * Output: 8. 1, 6. 7
 */
public class RotateArrayByDPositionToTheLeft {

    // Function to reverse array from start to end
    public static void reverseArray(int arr[], int start, int end) {
        int temp;
        while (start < end) {
            temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    // Function to left rotate array of size n by d
    public static void leftRotate(int arr[], int d, int n) {
        if (d == 0)
            return;
        d = d % n; // In case the rotating factor is greater than array length
        reverseArray(arr, 0, d - 1);
        reverseArray(arr, d, n - 1);
        reverseArray(arr, 0, n - 1);
    }

    // Function to print array
    public static void printArray(int arr[]) {
        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    // Main function
    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 4, 5, 6, 7};
        int d = 2;
        leftRotate(arr, d, arr.length);
        printArray(arr);
    }
}
