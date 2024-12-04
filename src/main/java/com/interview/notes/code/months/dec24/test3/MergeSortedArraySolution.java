package com.interview.notes.code.months.dec24.test3;

/*
WORKING:


Link: https://leetcode.com/problems/merge-sorted-array/description/

88. Merge Sorted Array


You are given two integer arrays nums1 and nums2, sorted in non-decreasing order, and two integers m and n, representing the number of elements in nums1 and nums2 respectively.

Merge nums1 and nums2 into a single array sorted in non-decreasing order.

The final sorted array should not be returned by the function, but instead be stored inside the array nums1. To accommodate this, nums1 has a length of m + n, where the first m elements denote the elements that should be merged, and the last n elements are set to 0 and should be ignored. nums2 has a length of n.



Example 1:

Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
Output: [1,2,2,3,5,6]
Explanation: The arrays we are merging are [1,2,3] and [2,5,6].
The result of the merge is [1,2,2,3,5,6] with the underlined elements coming from nums1.
Example 2:

Input: nums1 = [1], m = 1, nums2 = [], n = 0
Output: [1]
Explanation: The arrays we are merging are [1] and [].
The result of the merge is [1].
Example 3:

Input: nums1 = [0], m = 0, nums2 = [1], n = 1
Output: [1]
Explanation: The arrays we are merging are [] and [1].
The result of the merge is [1].
Note that because m = 0, there are no elements in nums1. The 0 is only there to ensure the merge result can fit in nums1.


Constraints:

nums1.length == m + n
nums2.length == n
0 <= m, n <= 200
1 <= m + n <= 200
-109 <= nums1[i], nums2[j] <= 109

 */
public class MergeSortedArraySolution {
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1;    // Pointer for nums1
        int p2 = n - 1;    // Pointer for nums2
        int p = m + n - 1; // Pointer for merged array

        // Start from the end and merge in descending order
        while (p2 >= 0) {
            if (p1 >= 0 && nums1[p1] > nums2[p2]) {
                nums1[p] = nums1[p1];
                p1--;
            } else {
                nums1[p] = nums2[p2];
                p2--;
            }
            p--;
        }
    }

    private static boolean arraysEqual(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length) return false;
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        // Test case 1
        int[] nums1_1 = {1, 2, 3, 0, 0, 0};
        int[] nums2_1 = {2, 5, 6};
        int[] expected1 = {1, 2, 2, 3, 5, 6};
        merge(nums1_1, 3, nums2_1, 3);
        System.out.println("Test Case 1: " +
                (arraysEqual(nums1_1, expected1) ? "PASS" : "FAIL"));

        // Test case 2
        int[] nums1_2 = {1};
        int[] nums2_2 = {};
        int[] expected2 = {1};
        merge(nums1_2, 1, nums2_2, 0);
        System.out.println("Test Case 2: " +
                (arraysEqual(nums1_2, expected2) ? "PASS" : "FAIL"));

        // Test case 3
        int[] nums1_3 = {0};
        int[] nums2_3 = {1};
        int[] expected3 = {1};
        merge(nums1_3, 0, nums2_3, 1);
        System.out.println("Test Case 3: " +
                (arraysEqual(nums1_3, expected3) ? "PASS" : "FAIL"));

        // Edge case: Large arrays
        int[] nums1_4 = new int[200];
        int[] nums2_4 = new int[100];
        for (int i = 0; i < 100; i++) {
            nums1_4[i] = i * 2;
            nums2_4[i] = i * 2 + 1;
        }
        merge(nums1_4, 100, nums2_4, 100);
        boolean isLargeArraySorted = true;
        for (int i = 1; i < 200; i++) {
            if (nums1_4[i] < nums1_4[i - 1]) {
                isLargeArraySorted = false;
                break;
            }
        }
        System.out.println("Large Array Test: " +
                (isLargeArraySorted ? "PASS" : "FAIL"));
    }
}
