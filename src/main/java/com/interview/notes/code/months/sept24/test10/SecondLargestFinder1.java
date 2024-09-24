package com.interview.notes.code.months.sept24.test10;

import java.util.Arrays;
import java.util.stream.Collector;

public class SecondLargestFinder1 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 5, 4, 3, 2, 1};
        int result = findSecondLargest(nums);
        System.out.println("Second largest is " + result);
    }

    public static int findSecondLargest(int[] nums) {
        if (nums == null || nums.length < 2) {
            throw new IllegalArgumentException("Array must contain at least two elements.");
        }

        int[] result = Arrays.stream(nums).parallel().boxed().collect(
                Collector.of(
                        () -> new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE},
                        (acc, num) -> {
                            if (num > acc[0]) {
                                acc[1] = acc[0];
                                acc[0] = num;
                            } else if (num > acc[1] && num != acc[0]) {
                                acc[1] = num;
                            }
                        },
                        (acc1, acc2) -> {
                            if (acc2[0] > acc1[0]) {
                                acc1[1] = acc1[0];
                                acc1[0] = acc2[0];
                            } else if (acc2[0] > acc1[1] && acc2[0] != acc1[0]) {
                                acc1[1] = acc2[0];
                            }
                            if (acc2[1] > acc1[1] && acc2[1] != acc1[0]) {
                                acc1[1] = acc2[1];
                            }
                            return acc1;
                        }
                )
        );

        if (result[1] == Integer.MIN_VALUE) {
            throw new IllegalArgumentException("No second largest element found.");
        }

        return result[1];
    }


    public static void main1(String[] args) {
        // Example large dataset
        int[] largeArray = generateLargeArray(1000000); // 1 million elements

        try {
            int result = findSecondLargest(largeArray);
            System.out.println("Second largest is " + result);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static int[] generateLargeArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * Integer.MAX_VALUE);
        }
        return array;
    }
}
