package com.interview.notes.code.year.y2024.june24.test5;

import java.util.Arrays;

public class MoveZeroes {

    public static void main(String[] args) {
        MoveZeroes mz = new MoveZeroes();

        int[] nums1 = {0, 1, 0, 3, 12};
        mz.moveZeroes(nums1);
        System.out.println(Arrays.toString(nums1)); // Output: [1, 3, 12, 0, 0]

        int[] nums2 = {0};
        mz.moveZeroes(nums2);
        System.out.println(Arrays.toString(nums2)); // Output: [0]
    }

    public void moveZeroes(int[] nums) {
        int lastNonZeroFoundAt = 0;

        // Move all the non-zero elements to the beginning of the array
        for (int current = 0; current < nums.length; current++) {
            if (nums[current] != 0) {
                nums[lastNonZeroFoundAt] = nums[current];
                lastNonZeroFoundAt++;
            }
        }

        // Fill the remaining positions with zeros
        for (int i = lastNonZeroFoundAt; i < nums.length; i++) {
            nums[i] = 0;
        }
    }
}
