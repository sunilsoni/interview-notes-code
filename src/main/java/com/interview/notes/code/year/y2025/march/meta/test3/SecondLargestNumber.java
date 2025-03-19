package com.interview.notes.code.year.y2025.march.meta.test3;

import java.util.Arrays;

public class SecondLargestNumber {

    /**
     * Rearranges the array to represent the second largest possible number.
     *
     * @param nums Array of integers (0-9)
     * @return Array representing the second largest possible number
     */
    public static int[] findSecondLargest(int[] nums) {
        // Handle edge cases
        if (nums == null || nums.length <= 1) {
            return nums; // Can't form a second largest with 0 or 1 digits
        }

        // Sort in descending order to get the largest possible arrangement
        int[] result = Arrays.stream(nums)
                .boxed()
                .sorted((a, b) -> Integer.compare(b, a))
                .mapToInt(i -> i)
                .toArray();

        // For the second largest, we need to find the rightmost pair where swapping
        // would give us a smaller number but still the largest possible after the swap
        for (int i = result.length - 2; i >= 0; i--) {
            // If we find a position where the digit is larger than the next one
            if (result[i] > result[i + 1]) {
                // Swap these two digits
                int temp = result[i];
                result[i] = result[i + 1];
                result[i + 1] = temp;

                // Sort the remaining digits (to the right of position i) in descending order
                // to ensure we get the largest possible arrangement after the swap
                Arrays.sort(result, i + 1, result.length);
                int left = i + 1;
                int right = result.length - 1;
                while (left < right) {
                    temp = result[left];
                    result[left] = result[right];
                    result[right] = temp;
                    left++;
                    right--;
                }

                return result;
            }
        }

        // If all digits are in ascending order, swap the last two
        if (result.length >= 2) {
            int temp = result[result.length - 1];
            result[result.length - 1] = result[result.length - 2];
            result[result.length - 2] = temp;
        }

        return result;
    }

    public static void main(String[] args) {
        // Test with the example
        int[] input = {1, 2, 3, 4, 5};
        int[] output = findSecondLargest(input);
        System.out.println("Input: " + Arrays.toString(input));
        System.out.println("Output: " + Arrays.toString(output));
        System.out.println("Expected: [5, 4, 3, 1, 2]");
        System.out.println("Result: " + Arrays.equals(output, new int[]{5, 4, 3, 1, 2}));
    }
}