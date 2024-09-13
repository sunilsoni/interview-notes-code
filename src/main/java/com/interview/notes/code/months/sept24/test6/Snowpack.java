package com.interview.notes.code.months.sept24.test6;

public class Snowpack {

    // Method to compute the amount of trapped snow (water)
    public static int computeSnowpack(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }

        int left = 0;
        int right = heights.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        int snowTrapped = 0;

        while (left < right) {
            if (heights[left] < heights[right]) {
                if (heights[left] >= leftMax) {
                    leftMax = heights[left];  // Update leftMax
                } else {
                    snowTrapped += leftMax - heights[left];  // Trap water
                }
                left++;
            } else {
                if (heights[right] >= rightMax) {
                    rightMax = heights[right];  // Update rightMax
                } else {
                    snowTrapped += rightMax - heights[right];  // Trap water
                }
                right--;
            }
        }

        return snowTrapped;
    }

    // Test case method
    public static void doTestsPass() {
        int[] testArray1 = {0, 1, 3, 0, 1, 2, 0, 4, 2, 0, 3, 0};
        int result1 = computeSnowpack(testArray1);
        System.out.println("Test Case 1: " + (result1 == 13 ? "Pass" : "Fail") + " (Result: " + result1 + ")");

        // Add more test cases as needed
        int[] testArray2 = {0, 0, 0, 0};
        int result2 = computeSnowpack(testArray2);
        System.out.println("Test Case 2: " + (result2 == 0 ? "Pass" : "Fail") + " (Result: " + result2 + ")");

        // Edge case: All heights are the same
        int[] testArray3 = {4, 4, 4, 4};
        int result3 = computeSnowpack(testArray3);
        System.out.println("Test Case 3: " + (result3 == 0 ? "Pass" : "Fail") + " (Result: " + result3 + ")");
    }

    public static void main(String[] args) {
        doTestsPass();  // Run the test cases
    }
}
