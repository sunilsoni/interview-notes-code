package com.interview.notes.code.year.y2025.july.GoldmanSachs.test2;

import java.util.stream.IntStream; // import IntStream for Java 8 Stream API to generate large test arrays

public class MaxSubArray { // declare the class

    // Compute the maximum sum of any continuous subarray using Kadane’s algorithm
    public static int maxSubArray(int[] nums) { // method signature
        int maxSoFar = nums[0]; // initialize maxSoFar to first element
        int currentMax = nums[0]; // initialize currentMax (max ending here) to first element

        for (int i = 1; i < nums.length; i++) { // loop from second element to end
            // either start fresh at nums[i] or extend previous subarray by adding nums[i]
            currentMax = Math.max(nums[i], currentMax + nums[i]);
            // update overall max if currentMax is bigger
            maxSoFar = Math.max(maxSoFar, currentMax);
        }

        return maxSoFar; // return the highest sum found
    }

    // Simple main method to run test cases without any testing framework
    public static void main(String[] args) { 
        // --- Test case 1: mix of positives and negatives ---
        int[] test1 = { -2, 4, -1, 4, 7 }; // input array
        int expected1 = 14; // we know 4 + (-1) + 4 + 7 = 14 is the max
        int result1 = maxSubArray(test1); // compute result
        if (result1 == expected1) { // compare
            System.out.println("Test1 PASS"); // ok
        } else {
            System.out.println("Test1 FAIL: expected " + expected1 + " but got " + result1);
        }

        // --- Test case 2: all negative numbers ---
        int[] test2 = { -3, -1, -5, -2 }; // input array
        int expected2 = -1; // the single largest element
        int result2 = maxSubArray(test2); // compute result
        if (result2 == expected2) {
            System.out.println("Test2 PASS");
        } else {
            System.out.println("Test2 FAIL: expected " + expected2 + " but got " + result2);
        }

        // --- Test case 3: all positive numbers ---
        int[] test3 = { 1, 2, 3, 4 }; // input array
        int expected3 = 10; // sum of all elements
        int result3 = maxSubArray(test3); // compute result
        if (result3 == expected3) {
            System.out.println("Test3 PASS");
        } else {
            System.out.println("Test3 FAIL: expected " + expected3 + " but got " + result3);
        }

        // --- Test case 4: large input performance check ---
        // generate an array of 1 000 000 ones to simulate big data
        int[] largeInput = IntStream.generate(() -> 1).limit(1_000_000).toArray();
        int expectedLarge = 1_000_000; // sum of all ones
        long startTime = System.currentTimeMillis(); // start timer
        int resultLarge = maxSubArray(largeInput); // compute result
        long duration = System.currentTimeMillis() - startTime; // elapsed time
        if (resultLarge == expectedLarge) {
            System.out.println("Large test PASS in " + duration + "ms");
        } else {
            System.out.println("Large test FAIL: expected " + expectedLarge + " but got " + resultLarge);
        }
    }
}