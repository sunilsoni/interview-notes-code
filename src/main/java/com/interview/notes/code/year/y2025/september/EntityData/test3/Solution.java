package com.interview.notes.code.year.y2025.september.EntityData.test3;

import java.util.Random;

public class Solution {
    /**
     * computeSnowpack: Given an array of non-negative heights, return
     * the total units of water that would collect between the hills.
     */
    public static Integer computeSnowpack(Integer[] arr) {
        // If null or fewer than 3 hills, no water can be trapped.
        if (arr == null || arr.length < 3) {
            return 0;
        }

        int n = arr.length;                // Number of positions
        int[] leftMax = new int[n];        // leftMax[i] = highest hill from 0..i
        int[] rightMax = new int[n];       // rightMax[i] = highest hill from i..n-1

        // Build leftMax in a forward pass
        leftMax[0] = arr[0];               // First hill is its own left max
        for (int i = 1; i < n; i++) {
            // Compare previous leftMax with current height
            leftMax[i] = Math.max(leftMax[i - 1], arr[i]);
        }

        // Build rightMax in a backward pass
        rightMax[n - 1] = arr[n - 1];      // Last hill is its own right max
        for (int i = n - 2; i >= 0; i--) {
            // Compare next rightMax with current height
            rightMax[i] = Math.max(rightMax[i + 1], arr[i]);
        }

        // Sum water trapped at each position
        int totalWater = 0;
        for (int i = 0; i < n; i++) {
            // Water = min(highest left, highest right) − current height
            int waterHere = Math.min(leftMax[i], rightMax[i]) - arr[i];
            totalWater += waterHere;      // Add this position's water
        }

        return totalWater;                // Return total units of water
    }

    /**
     * doTestsPass: Run several fixed tests and return true only if all pass.
     */
    public static boolean doTestsPass() {
        boolean result = true;             // Tracks overall pass/fail

        // 1) Sample test from prompt: should trap 13 units
        Integer[] sample = {0, 1, 3, 0, 1, 2, 0, 4, 2, 0, 3, 0};
        result &= computeSnowpack(sample).equals(13);

        // 2) Edge case: empty array → 0 water
        Integer[] empty = {};
        result &= computeSnowpack(empty).equals(0);

        // 3) Edge case: one hill → 0 water
        Integer[] one = {5};
        result &= computeSnowpack(one).equals(0);

        // 4) Two hills → still 0 water
        Integer[] two = {5, 2};
        result &= computeSnowpack(two).equals(0);

        // 5) Flat hills → no valleys → 0 water
        Integer[] flat = {3, 3, 3, 3, 3};
        result &= computeSnowpack(flat).equals(0);

        // 6) Strictly increasing → water runs off right → 0
        Integer[] inc = {0, 1, 2, 3, 4};
        result &= computeSnowpack(inc).equals(0);

        // 7) Strictly decreasing → water runs off left → 0
        Integer[] dec = {5, 4, 3, 2, 1};
        result &= computeSnowpack(dec).equals(0);

        // 8) Simple valley → max walls of 3 around a 0 → traps 3 units
        Integer[] valley = {3, 0, 3};
        result &= computeSnowpack(valley).equals(3);

        // 9) Wider valley → walls 4 around two 1s → traps 6 units total
        Integer[] wideValley = {4, 1, 1, 4};
        result &= computeSnowpack(wideValley).equals(6);

        return result;                     // True if all tests passed
    }

    /**
     * main: Entry point. Runs small tests then a large random test.
     */
    public static void main(String[] args) {
        // Run fixed test cases
        if (doTestsPass()) {
            System.out.println("All fixed tests PASS");
        } else {
            System.out.println("Fixed tests FAIL");
        }

        // Now test performance on a large random dataset
        int largeSize = 200_000;           // Number of hills
        Random rand = new Random(42);      // Fixed seed for repeatability

        // Build a large array of random heights (0..1000), duplicated to create valleys
        Integer[] largeArr = new Integer[largeSize];
        for (int i = 0; i < largeSize; i++) {
            // Random height between 0 and 1000
            largeArr[i] = rand.nextInt(1001);
        }

        // Measure start time
        long start = System.currentTimeMillis();
        // Compute trapped water
        Integer largeResult = computeSnowpack(largeArr);
        // Measure end time
        long duration = System.currentTimeMillis() - start;

        // Always consider this PASS if it completes quickly and returns non-negative
        if (largeResult >= 0) {
            System.out.println("Large data test PASS (water=" + largeResult
                    + ", time=" + duration + "ms)");
        } else {
            System.out.println("Large data test FAIL");
        }
    }
}