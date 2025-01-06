package com.interview.notes.code.year.y2024.dec24.test6;

public class RotatedArraySearch {

    public static int findElement(int[] arr, int key) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == key) {
                return mid;
            }

            // Left half is sorted
            if (arr[left] <= arr[mid]) {
                if (key >= arr[left] && key < arr[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            // Right half is sorted
            else {
                if (key > arr[mid] && key <= arr[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    public static void runTest(int[] arr, int key, int expected) {
        int result = findElement(arr, key);
        System.out.printf("Input Array: %s%n", java.util.Arrays.toString(arr));
        System.out.printf("Key: %d%n", key);
        System.out.printf("Expected: %d, Got: %d%n", expected, result);
        System.out.printf("Test %s%n%n", result == expected ? "PASSED" : "FAILED");
    }

    public static void main(String[] args) {
        // Test Case 1: Normal rotated array
        runTest(new int[]{4, 5, 6, 7, 0, 1, 2}, 0, 4);

        // Test Case 2: Element not present
        runTest(new int[]{4, 5, 6, 7, 0, 1, 2}, 3, -1);

        // Test Case 3: Single element array
        runTest(new int[]{1}, 1, 0);

        // Test Case 4: Empty array
        runTest(new int[]{}, 5, -1);

        // Test Case 5: Null array
        runTest(null, 5, -1);

        // Test Case 6: Large array
        int[] largeArray = new int[1000000];
        for (int i = 0; i < largeArray.length; i++) {
            largeArray[i] = (i + 500000) % 1000000;
        }
        // runTest(largeArray, 999999, 499999);
    }
}
