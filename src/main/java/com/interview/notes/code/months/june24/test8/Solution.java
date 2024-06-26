package com.interview.notes.code.months.june24.test8;

public class Solution {
    public static int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) return 0; // If k is 1 or less, no product can be strictly less than k.

        int left = 0;
        int product = 1;
        int count = 0;

        for (int right = 0; right < nums.length; right++) {
            product *= nums[right];

            while (product >= k && left <= right) {
                product /= nums[left];
                left++;
            }

            // All subarrays ending at 'right' and starting from any index between 'left' and 'right' are valid.
            count += right - left + 1;
        }

        return count;
    }

    public static void main(String[] args) {
        // Example 1
        int[] nums1 = {10, 5, 2, 6};
        int k1 = 100;
        System.out.println("Output for Example 1: " + numSubarrayProductLessThanK(nums1, k1));
        // Expected output: 8

        // Example 2
        int[] nums2 = {1, 2, 3};
        int k2 = 0;
        System.out.println("Output for Example 2: " + numSubarrayProductLessThanK(nums2, k2));
        // Expected output: 0
    }
}
