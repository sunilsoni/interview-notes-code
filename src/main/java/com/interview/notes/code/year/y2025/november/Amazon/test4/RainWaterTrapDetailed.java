package com.interview.notes.code.year.y2025.november.Amazon.test4;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class RainWaterTrapDetailed {

    /**
     * This method calculates how much rainwater can be trapped
     * between buildings of different heights after it rains.
     * <p>
     * Input: an array of building heights
     * Output: total trapped water units (integer)
     */
    public static int calculateTrappedWater(int[] heights) {

        // 🧠 Step 1: Handle edge case
        // If there are fewer than 3 buildings, you can’t form a “bowl”
        // Example: [5,2] or [1,2,3] → no space between walls to hold water
        if (heights == null || heights.length < 3) return 0;

        // 🧭 Step 2: Initialize pointers
        // We use a two-pointer approach: one from the left, one from the right
        int left = 0;                    // start of the array
        int right = heights.length - 1;  // end of the array

        // 🧱 Step 3: Track max heights seen so far from both sides
        // Why needed? Because for any building,
        // trapped water depends on the *smallest of the two boundaries* (left & right)
        int leftMax = 0;
        int rightMax = 0;

        // 💧 Step 4: This will store the total accumulated rainwater
        int totalWater = 0;

        // 🔁 Step 5: Loop until both pointers meet
        // This ensures we check each building only once → O(N) time
        while (left < right) {

            // ⚖️ Step 6: Compare current left and right heights
            // The smaller side determines the water trapping potential.
            // Because water always spills over the shorter boundary.
            if (heights[left] <= heights[right]) {
                // 👉 We handle the LEFT side

                // 🧱 Step 6a: If current left building is taller than previous max,
                // it becomes the new boundary (no water on top)
                if (heights[left] >= leftMax) {
                    leftMax = heights[left];  // update left boundary
                } else {
                    // 💧 Step 6b: Otherwise, water can stay above this building
                    // The amount = (left boundary height - current height)
                    // Example: if leftMax=15 and current=10 → 5 units trapped
                    totalWater += (leftMax - heights[left]);
                }

                // ➡️ Move pointer inward after processing
                left++;
            } else {
                // 👉 Handle the RIGHT side similarly

                // 🧱 Step 6c: If current right building is taller than previous max,
                // update right boundary
                if (heights[right] >= rightMax) {
                    rightMax = heights[right];
                } else {
                    // 💧 Step 6d: Otherwise, trap water on top of this building
                    totalWater += (rightMax - heights[right]);
                }

                // ⬅️ Move pointer inward
                right--;
            }
        }

        // ✅ Step 7: Return total amount of trapped rainwater
        return totalWater;
    }

    public static void main(String[] args) {

        // 🧪 Step 8: Create test cases
        Map<int[], Integer> testCases = new LinkedHashMap<>();

        // Example from your question
        testCases.put(new int[]{10, 15, 12, 10, 9, 12, 8}, 5);

        // A few more validation cases:
        testCases.put(new int[]{3, 0, 2, 0, 4}, 7);   // classic test
        testCases.put(new int[]{1, 2, 3, 4, 5}, 0);   // increasing heights → no water
        testCases.put(new int[]{5, 4, 3, 2, 1}, 0);   // decreasing heights → no water
        testCases.put(new int[]{2, 0, 2}, 2);         // small valley → 2 units

        // Large input for performance test
        int[] largeInput = IntStream.concat(
                IntStream.rangeClosed(1, 500_000),
                IntStream.iterate(500_000, i -> i - 1).limit(500_000)
        ).toArray();
        testCases.put(largeInput, 0);

        // 🧾 Step 9: Run all test cases
        int testNum = 1;
        for (Map.Entry<int[], Integer> entry : testCases.entrySet()) {
            int actual = calculateTrappedWater(entry.getKey());
            int expected = entry.getValue();

            // 🧩 Step 10: Check pass/fail
            if (actual == expected) {
                System.out.println("TestCase " + testNum + ": PASS (Expected=" + expected + ", Actual=" + actual + ")");
            } else {
                System.out.println("TestCase " + testNum + ": FAIL (Expected=" + expected + ", Actual=" + actual + ")");
            }
            testNum++;
        }
    }
}
