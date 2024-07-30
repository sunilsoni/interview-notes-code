package com.interview.notes.code.months.july24.test14;

public class ArrayUtils {
    public static int secondSmallest(int[] x) {
        // Check if the array has fewer than 2 elements
        if (x == null || x.length < 2) {
            return 0;
        }

        int smallest = Integer.MAX_VALUE;
        int secondSmallest = Integer.MAX_VALUE;

        for (int value : x) {
            if (value < smallest) {
                // Current value is smaller than the smallest
                secondSmallest = smallest;
                smallest = value;
            } else if (value < secondSmallest && value != smallest) {
                // Current value is smaller than second smallest but not the smallest
                secondSmallest = value;
            }
        }

        // Check if we found a valid second smallest
        return (secondSmallest != Integer.MAX_VALUE) ? secondSmallest : 0;
    }

    // Optional: Main method for testing
    public static void main(String[] args) {
        int[] test1 = {5, 2, 8, 1, 9};
        int[] test2 = {1, 1, 1, 1};
        int[] test3 = {7};
        int[] test4 = {};

        System.out.println("Test 1: " + secondSmallest(test1)); // Expected: 2
        System.out.println("Test 2: " + secondSmallest(test2)); // Expected: 0
        System.out.println("Test 3: " + secondSmallest(test3)); // Expected: 0
        System.out.println("Test 4: " + secondSmallest(test4)); // Expected: 0

        doTestsPass();
    }

    public static boolean doTestsPass() {
        boolean result = true;

        // Test case 1: Null array
        result &= secondSmallest(null) == 0;

        // Test case 2: Empty array
        result &= secondSmallest(new int[]{}) == 0;

        // Test case 3: Array with one element
        result &= secondSmallest(new int[]{0}) == 0;

        // Test case 4: Array with two identical elements
        result &= secondSmallest(new int[]{1, 1}) == 0;

        // Test case 5: Array with two different elements
        result &= secondSmallest(new int[]{0, 1}) == 1;

        // Test case 6: Array with multiple elements, second smallest is unique
        result &= secondSmallest(new int[]{5, 2, 8, 1, 9}) == 2;

        // Test case 7: Array with multiple elements, smallest is repeated
        result &= secondSmallest(new int[]{1, 1, 2, 3}) == 2;

        // Test case 8: Array with all identical elements
        result &= secondSmallest(new int[]{4, 4, 4, 4}) == 0;

        // Test case 9: Array with negative numbers
        result &= secondSmallest(new int[]{-1, -5, 0, 4}) == -1;

        // Test case 10: Array with Integer.MAX_VALUE and Integer.MIN_VALUE
        result &= secondSmallest(new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE, 0}) == 0;

        if (result) {
            System.out.println("All tests pass\n");
        } else {
            System.out.println("There are test failures\n");
        }

        return result;
    }

}
