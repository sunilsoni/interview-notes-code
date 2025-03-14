package com.interview.notes.code.year.y2025.march.GoldmanSachs.test2;

import java.util.*;
import java.util.stream.*;

public class SnowpackSolution {

    // Method computes snowpack units trapped between hills
    public static int computeSnowpack(int[] arr) {
        int n = arr.length;
        if (n <= 2) return 0; // Edge case: not enough hills to trap snow

        // Array to track maximum height on left side for each position
        int[] leftMax = new int[n];
        leftMax[0] = arr[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], arr[i]);
        }

        // Array to track maximum height on right side for each position
        int[] rightMax = new int[n];
        rightMax[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], arr[i]);
        }

        // Calculate total snow trapped
        int snow = IntStream.range(0, n)
            .map(i -> Math.min(leftMax[i], rightMax[i]) - arr[i])
            .sum();

        return snow;
    }

    // Test cases to validate the solution
    public static boolean doTestsPass() {
        boolean result = true;

        // Basic provided test
        result &= computeSnowpack(new int[]{0, 1, 3, 0, 1, 2, 0, 4, 2, 0, 3, 0}) == 13;

        // Edge cases
        result &= computeSnowpack(new int[]{}) == 0; // Empty input
        result &= computeSnowpack(new int[]{1}) == 0; // Single element
        result &= computeSnowpack(new int[]{2, 2}) == 0; // Two elements, no trap

        // Large input test case
        int[] largeTest = new int[100000];
        Arrays.fill(largeTest, 0, 50000, 1);
        Arrays.fill(largeTest, 50000, 100000, 2);
        result &= computeSnowpack(largeTest) == 0; // No snow trapped as slope continuously rises

        return result;
    }

    // Minimal reproducible example
    public static void main(String[] args) {
        if (doTestsPass()) {
            System.out.println("All tests pass");
        } else {
            System.out.println("Tests fail");
        }

        // Example call
        int exampleSnow = computeSnowpack(new int[]{0, 1, 3, 0, 1, 2, 0, 4, 2, 0, 3, 0});
        System.out.println("Example trapped snow units: " + exampleSnow);
    }
}