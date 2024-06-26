package com.interview.notes.code.months.june24.test9;

import java.util.ArrayList;
import java.util.List;

public class Solution2 {
    public static void main(String[] args) {
        // Example 1
        int[] nums1 = {2, 3, -2, 4};
        System.out.println("Subarray with the maximum product for Example 1:");
        System.out.println(maxProductSubarray(nums1));

        // Example 2
        int[] nums2 = {-1, -2, -3, 0};
        System.out.println("Subarray with the maximum product for Example 2:");
        System.out.println(maxProductSubarray(nums2));
    }

    public static List<Integer> maxProductSubarray(int[] nums) {
        if (nums.length == 0) return new ArrayList<>();

        int maxSoFar = nums[0];
        int minSoFar = nums[0];
        int maxProduct = nums[0];
        int start = 0;
        int end = 0;
        int tempStart = 0;

        for (int i = 1; i < nums.length; i++) {
            int temp = maxSoFar;
            // Calculate the maximum product up to the current element
            maxSoFar = Math.max(Math.max(maxSoFar * nums[i], minSoFar * nums[i]), nums[i]);
            minSoFar = Math.min(Math.min(temp * nums[i], minSoFar * nums[i]), nums[i]);

            // Update start and end indices of the subarray
            if (maxSoFar == nums[i]) {
                tempStart = i;
            }
            if (maxSoFar > maxProduct) {
                maxProduct = maxSoFar;
                start = tempStart;
                end = i;
            }
        }

        // Collect the subarray with the maximum product
        List<Integer> result = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            result.add(nums[i]);
        }
        return result;
    }
}
