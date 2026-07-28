package com.interview.notes.code.year.y2026.july.common.test1;

import java.util.Arrays;

public class DuplicateRemover {
    public static int[] removeDuplicates(int[] arr) {
        int[] unique = new int[arr.length];
        int count = 0;
        
        for (int num : arr) {
            boolean found = false;
            for (int i = 0; i < count; i++) {
                if (unique[i] == num) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                unique[count++] = num;
            }
        }
        return Arrays.copyOf(unique, count);
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 2, 3, 4, 4, 5};
        System.out.println(Arrays.toString(removeDuplicates(nums)));
    }
}