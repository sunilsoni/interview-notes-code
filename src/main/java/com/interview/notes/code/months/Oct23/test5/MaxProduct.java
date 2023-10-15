package com.interview.notes.code.months.Oct23.test5;

public class MaxProduct {
    public int maxProduct(int[] nums) {
        // Step 1: Initialize two variables to hold the two largest numbers
        int max1 = Integer.MIN_VALUE; // This will hold the largest number
        int max2 = Integer.MIN_VALUE; // This will hold the second largest number

        // Step 2: Loop through the array to find the two largest numbers
        for (int num : nums) {
            if (num > max1) {
                max2 = max1; // If we find a new largest number, the current largest becomes the second largest
                max1 = num;  // Set the new largest number
            } else if (num > max2) { // If the number is between max1 and max2, it becomes the second largest
                max2 = num;
            }
        }

        // Step 3: Return the product of the two largest numbers
        return (max1 - 1) * (max2 - 1);
    }

}
