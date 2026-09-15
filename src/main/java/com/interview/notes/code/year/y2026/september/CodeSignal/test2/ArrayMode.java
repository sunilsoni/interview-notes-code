package com.interview.notes.code.year.y2026.september.CodeSignal.test2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArrayMode {

    static int mode(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Array is empty");
        }

        Map<Integer, Integer> count = new HashMap<>();

        for (int n : nums) {
            count.put(n, count.getOrDefault(n, 0) + 1);
        }

        int mode = nums[0];
        int max = 0;

        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                mode = entry.getKey();
            }
        }

        return mode;
    }

    static void test(int[] nums, int... expected) {
        int actual = mode(nums);
        boolean pass = false;

        for (int n : expected) {
            if (n == actual) {
                pass = true;
                break;
            }
        }

        System.out.println((pass ? "PASS" : "FAIL") + " | Result: " + actual);
    }

    static void main(String[] args) {
        test(new int[]{5, 3, 8, 4, 9, 8, 4, 1, 4}, 4);
        test(new int[]{1, 2, 1, 2}, 1, 2);
        test(new int[]{7}, 7);
        test(new int[]{3, 3, 3, 3}, 3);
        test(new int[]{-1, -2, -1, 5}, -1);
        test(new int[]{0, 0, 1, 2}, 0);

        int[] large = new int[1_000_000];

        Arrays.fill(large, 10);
        large[0] = 20;

        test(large, 10);
    }
}