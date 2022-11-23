package com.interview.notes.code.misc.test5;

import java.util.Arrays;

public class RemoveDuplicates {
    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }


    public static void main(String[] args) {
        int[] arr = {1, 1, 2, 3, 4, 4};

        removeDuplicates(arr);

        Arrays.stream(arr).forEach(System.out::print);//123444

    }
}
