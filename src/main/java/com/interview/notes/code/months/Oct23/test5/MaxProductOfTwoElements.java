package com.interview.notes.code.months.Oct23.test5;

/**
 * Maximum Product of Two Elements in an Array
 * <p>
 * EasyTopicsCompaniesHint
 * <p>
 * Given the array of integers nums, you will choose two different indices i and j of that array. Return the maximum value of (nums[i])*(nums[j]).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [3,4,5,2]
 * <p>
 * Output: 20
 * <p>
 * Explanation: If you choose the indices i=1 and j=2 (indexed from 0), you will get the maximum value, that is, (nums[1])*(nums[2]) = (4)*(5) = 4*5 = 20.
 * <p>
 * <p>
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [1,5,4,5]
 * <p>
 * Output: 25
 * <p>
 * Explanation: Choosing the indices i=1 and j=3 (indexed from 0), you will get the maximum value of (5)*(5) = 25
 * <p>
 * <p>
 * <p>
 * Example 3:
 * <p>
 * Input: nums = [3,7]
 * <p>
 * Output: 21
 */
public class MaxProductOfTwoElements {

    public static int maxProduct(int[] nums) {
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;

        for (int num : nums) {
            if (num > max1) {
                max2 = max1;
                max1 = num;
            } else if (num > max2) {
                max2 = num;
            }
        }

        return max1 * max2;
    }

    public static void main(String[] args) {
        // Example 1
        int[] nums1 = {3, 4, 5, 2};
        System.out.println(maxProduct(nums1)); // Expected output: 20

        // Example 2
        int[] nums2 = {1, 5, 4, 5};
        System.out.println(maxProduct(nums2)); // Expected output: 25

        // Example 3
        int[] nums3 = {3, 7};
        System.out.println(maxProduct(nums3)); // Expected output: 21
    }
}
