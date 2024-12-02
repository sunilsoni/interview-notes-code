package com.interview.notes.code.year.y2024.feb24.test1;

public class MergeSortedArrays {
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        // Initialize pointers
        int p1 = m - 1; // Points to the last non-zero element in nums1
        int p2 = n - 1; // Points to the last element in nums2
        int p = m + n - 1; // Points to the last index of nums1

        // While both pointers are valid
        while (p1 >= 0 && p2 >= 0) {
            // Compare elements at nums1[p1] and nums2[p2]
            if (nums1[p1] > nums2[p2]) {
                // Place the larger element at nums1[p]
                nums1[p] = nums1[p1];
                // Decrement p1 and p accordingly
                p1--;
            } else {
                nums1[p] = nums2[p2];
                p2--;
            }
            // Move p to the left
            p--;
        }

        // Copy remaining elements from nums2 to nums1, if any
        System.arraycopy(nums2, 0, nums1, 0, p2 + 1);
    }

    // Main method for example execution
    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 0, 0, 0}; // given nums1 array
        int m = 3; // length of nums1
        int[] nums2 = {2, 5, 6}; // given nums2 array
        int n = 3; // length of nums2

        // Merge nums2 into nums1
        merge(nums1, m, nums2, n);

        // Output the merged array
        for (int num : nums1) {
            System.out.print(num + " ");
        }
    }
}
