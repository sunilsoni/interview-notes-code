package com.interview.notes.code.year.y2025.may.common.test10;

public class RotatedArraySearch {

    // Method to find target in rotated sorted array using binary search
    public static int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2; // Find middle to avoid integer overflow

            if (nums[mid] == target) return mid; // Check if mid-element is the target

            // Determine which half is sorted
            if (nums[left] <= nums[mid]) { // Left half is sorted
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1; // Target lies in sorted left half
                } else {
                    left = mid + 1; // Target lies in right half
                }
            } else { // Right half is sorted
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1; // Target lies in sorted right half
                } else {
                    right = mid - 1; // Target lies in left half
                }
            }
        }

        return -1; // Target not found
    }

    // Simple testing using main method
    public static void main(String[] args) {
        // Test cases
        int[][] testArrays = {
                {4, 5, 6, 7, 0, 1, 2},
                {1},
                {5, 1, 2, 3, 4},
                {1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
                {30, 40, 50, 10, 20}
        };
        int[] targets = {0, 1, 4, 7, 10};
        int[] expected = {4, 0, 4, 6, 3};

        for (int i = 0; i < testArrays.length; i++) {
            int result = search(testArrays[i], targets[i]);
            System.out.println("Test case " + (i + 1) + ": " + (result == expected[i] ? "PASS" : "FAIL") + " (Output: " + result + ")");
        }

        // Edge case: large array
        int size = 1000000;
        int[] largeArray = new int[size];
        for (int i = 0; i < size; i++) {
            largeArray[i] = (i + 500000) % size;
        }
        int largeResult = search(largeArray, 123456);
        System.out.println("Large array test: " + (largeArray[largeResult] == 123456 ? "PASS" : "FAIL"));
    }
}
