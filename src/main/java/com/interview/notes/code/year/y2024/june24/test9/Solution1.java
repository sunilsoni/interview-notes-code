package com.interview.notes.code.year.y2024.june24.test9;

import java.util.ArrayList;
import java.util.List;

public class Solution1 {
    public static void main(String[] args) {
        // Example 1
        int[] nums1 = {10, 5, 2, 6};
        int k1 = 100;
        List<List<Integer>> result1 = numSubarrayProductLessThanK(nums1, k1);
        System.out.println("Subarrays for Example 1:");
        for (List<Integer> subarray : result1) {
            System.out.println(subarray);
        }

        // Example 2
        int[] nums2 = {1, 2, 3};
        int k2 = 0;
        List<List<Integer>> result2 = numSubarrayProductLessThanK(nums2, k2);
        System.out.println("Subarrays for Example 2:");
        for (List<Integer> subarray : result2) {
            System.out.println(subarray);
        }
    }

    public static List<List<Integer>> numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) return new ArrayList<>(); // If k is 1 or less, no product can be strictly less than k.

        List<List<Integer>> subarrays = new ArrayList<>();
        int left = 0;
        int product = 1;

        for (int right = 0; right < nums.length; right++) {
            product *= nums[right];

            while (product >= k && left <= right) {
                product /= nums[left];
                left++;
            }

            // Collect all subarrays ending at 'right' and starting from any index between 'left' and 'right'.
            for (int start = left; start <= right; start++) {
                List<Integer> subarray = new ArrayList<>();
                for (int i = start; i <= right; i++) {
                    subarray.add(nums[i]);
                }
                subarrays.add(subarray);
            }
        }

        return subarrays;
    }
}
