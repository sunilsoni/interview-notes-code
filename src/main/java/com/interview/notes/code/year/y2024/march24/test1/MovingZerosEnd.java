package com.interview.notes.code.year.y2024.march24.test1;

import java.util.Arrays;
import java.util.List;

/**
 * Moving Zeros to End
 * Given an integer array nums], move all O's to the end of it while maintaining the relative order of the non-zero elements.
 * Note: You must do this in-place without making a copy of the array.
 * Input
 * The first line of input contains an integer N, representing the size of the array.
 * The second line of input contains N space-separated integers, representing the array elements.
 * Output
 * The updated array after moving Os to the end of it.
 * Constraints
 * 1 <= N <= 104
 * -231 <= nums|i] <= 231 - 1
 * <p>
 * <p>
 * Example #1
 * Input
 * 5
 * 01 0 3 12
 * Output
 * 1 3 12 0 0
 * Example #2
 * Input
 * 1
 * Output
 * 0
 */
public class MovingZerosEnd {
    public static List<Integer> solve(List<Integer> nums) {
        int j = 0;
        for (int i = 0; i < nums.size(); i++) {
            if (nums.get(i) != 0) {
                nums.set(j, nums.get(i));
                j++;
            }
        }
        for (int i = j; i < nums.size(); i++) {
            nums.set(i, 0);
        }
        return nums;
    }

    public static void main(String[] args) {
        // Example usage
        List<Integer> input = Arrays.asList(0, 1, 0, 3, 12);
        List<Integer> output = solve(input);
        System.out.println(output); // Output: [1, 3, 12, 0, 0]
    }
}
