package com.interview.notes.code.year.y2024.dec24.test3;

public class MergeSortedArray {

    // Method to merge nums2 into nums1
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1; // Pointer to the last valid element in nums1
        int j = n - 1; // Pointer to the last element in nums2
        int k = m + n - 1; // Pointer to the last position in nums1

        // Merge in reverse order
        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k--] = nums1[i--];
            } else {
                nums1[k--] = nums2[j--];
            }
        }

        // If there are any elements left in nums2, copy them to nums1
        while (j >= 0) {
            nums1[k--] = nums2[j--];
        }
    }

    // Method to test the merge functionality
    public static void main(String[] args) {
        // Test Case 1
        int[] nums1_1 = {1, 2, 3, 0, 0, 0};
        int[] nums2_1 = {2, 5, 6};
        merge(nums1_1, 3, nums2_1, 3);
        printArray(nums1_1);  // Expected Output: [1, 2, 2, 3, 5, 6]

        // Test Case 2
        int[] nums1_2 = {1};
        int[] nums2_2 = {};
        merge(nums1_2, 1, nums2_2, 0);
        printArray(nums1_2);  // Expected Output: [1]

        // Test Case 3
        int[] nums1_3 = {0};
        int[] nums2_3 = {1};
        merge(nums1_3, 0, nums2_3, 1);
        printArray(nums1_3);  // Expected Output: [1]

        // Test Case 4: Large Input Case
        int[] nums1_4 = new int[400];  // m + n = 400
        int[] nums2_4 = new int[200];
        for (int i = 0; i < 200; i++) {
            nums1_4[i] = i + 1;
        }
        for (int i = 0; i < 200; i++) {
            nums2_4[i] = i + 201;
        }
        merge(nums1_4, 200, nums2_4, 200);
        printArray(nums1_4);  // Expected Output: [1, 2, ..., 200, 201, ..., 400]

        // Test Case 5: nums2 is empty
        int[] nums1_5 = {1, 2, 3, 0, 0, 0};
        int[] nums2_5 = {};
        merge(nums1_5, 3, nums2_5, 0);
        printArray(nums1_5);  // Expected Output: [1, 2, 3]

        // Test Case 6: nums1 is empty
        int[] nums1_6 = {0, 0, 0};
        int[] nums2_6 = {1, 2, 3};
        merge(nums1_6, 0, nums2_6, 3);
        printArray(nums1_6);  // Expected Output: [1, 2, 3]
    }

    // Utility method to print array
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
