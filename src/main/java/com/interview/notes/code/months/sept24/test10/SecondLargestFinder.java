package com.interview.notes.code.months.sept24.test10;

import java.util.Arrays;

public class SecondLargestFinder {
    public static int findSecondLargest(int[] nums) {
        if (nums == null || nums.length < 2) {
            throw new IllegalArgumentException("Array must contain at least two distinct elements.");
        }
        int[] result = Arrays.stream(nums)
                .distinct() // Filter out duplicates
                .parallel() // Enable parallel processing
                .boxed()
                .collect(
                        () -> new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE},
                        (acc, num) -> {
                            if (num > acc[0]) {
                                acc[1] = acc[0];
                                acc[0] = num;
                            } else if (num > acc[1]) {
                                acc[1] = num;
                            }
                        },
                        (acc1, acc2) -> {
                            if (acc2[0] > acc1[0]) {
                                acc1[1] = acc1[0];
                                acc1[0] = acc2[0];
                            } else if (acc2[0] != acc1[0] && acc2[0] > acc1[1]) {
                                acc1[1] = acc2[0];
                            }
                            if (acc2[1] > acc1[1]) {
                                acc1[1] = acc2[1];
                            }
                        }
                );
        if (result[1] == Integer.MIN_VALUE) {
            throw new IllegalArgumentException("No second largest element found.");
        }
        return result[1];
    }

    public static void main(String[] args) {
        int[] nums = {5, 3, 5, 2, 4};
        System.out.println(findSecondLargest(nums)); // Output: 4
    }
}
