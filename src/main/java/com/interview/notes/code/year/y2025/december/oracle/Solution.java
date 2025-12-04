package com.interview.notes.code.year.y2025.december.oracle;// Problem Name is &&& Snowpack &&& PLEASE DO NOT REMOVE THIS LINE.

class Solution {

    /*
     ** Find the amount of snow that could be captured.
     */
    public static int computeSnowpack(int[] groundHeightAt) {
        // If array is null or empty, no snow can be trapped.
        if (groundHeightAt == null || groundHeightAt.length == 0) {
            return 0;
        }

        int n = groundHeightAt.length;

        // leftMax[i] will store the highest bar from index 0 to i.
        int[] leftMax = new int[n];
        leftMax[0] = groundHeightAt[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], groundHeightAt[i]);
        }

        // rightMax[i] will store the highest bar from index i to n-1.
        int[] rightMax = new int[n];
        rightMax[n - 1] = groundHeightAt[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], groundHeightAt[i]);
        }

        // Now compute trapped snow at each index.
        int totalSnow = 0;
        for (int i = 0; i < n; i++) {
            // Water level at i is limited by the shorter side (left vs right).
            int waterLevel = Math.min(leftMax[i], rightMax[i]);
            // If water level is higher than ground, snow can be stored.
            if (waterLevel > groundHeightAt[i]) {
                totalSnow += waterLevel - groundHeightAt[i];
            }
        }

        return totalSnow;
    }

    /*
     ** Returns true if the tests pass. Otherwise, returns false.
     */
    public static boolean doTestsPass() {
        boolean result = true;

        // Example from the problem statement: expected 13 units of snow.
        result &= computeSnowpack(
                new int[]{0, 1, 3, 0, 1, 2, 0, 4, 2, 0, 3, 0}) == 13;

        // Simple valley between two walls of height 1: 10 slots of height 1 -> 10 units.
        result &= computeSnowpack(
                new int[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}) == 10;

        // Flat ground, no snow.
        result &= computeSnowpack(
                new int[]{0, 0, 0, 0}) == 0;

        // Single peak, snow runs off both sides.
        result &= computeSnowpack(
                new int[]{0, 0, 1, 0, 0}) == 0;

        // Single element, no snow.
        result &= computeSnowpack(
                new int[]{1}) == 0;

        // Empty array, no snow.
        result &= computeSnowpack(
                new int[]{}) == 0;

        return result;
    }

    /*
     ** Execution entry point.
     */
    public static void main(String[] args) {
        if (doTestsPass()) {
            System.out.println("All tests pass");
        } else {
            System.out.println("Tests fail.");
        }
    }
}
