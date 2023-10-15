package com.interview.notes.code.months.Oct23.test5;

public class MaxProductOfTwoElements1 {
    
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

        return (max1 - 1) * (max2 - 1);
    }

    public static void main(String[] args) {
        // Example 1
        int[] nums1 = {3,4,5,2};
        System.out.println(maxProduct(nums1)); // Expected output: 20

        // Example 2
        int[] nums2 = {1,5,4,5};
        System.out.println(maxProduct(nums2)); // Expected output: 25

        // Example 3
        int[] nums3 = {3,7};
        System.out.println(maxProduct(nums3)); // Expected output: 21
    }
}
