package com.interview.notes.code.year.y2025.november.Amazon.test5;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class RainWaterTrappingExplained {

    // Method to calculate total rainwater trapped between buildings
    public static int calculateTrappedWater(int[] heights) {
        // 📝 If there are fewer than 3 buildings, no water can be trapped.
        if (heights == null || heights.length < 3) return 0;

        int left = 0;                    // 🧭 Pointer starting from the left end of array
        int right = heights.length - 1;  // 🧭 Pointer starting from the right end
        int leftMax = 0;                 // 🧱 Keeps track of tallest building seen so far from left
        int rightMax = 0;                // 🧱 Keeps track of tallest building seen so far from right
        int totalWater = 0;              // 💧 Total accumulated water units

        // 🚶 Move both pointers inward until they meet
        while (left < right) {

            // ⚖️ Compare heights at both ends to decide which side to process
            if (heights[left] <= heights[right]) {
                // 👉 We process the LEFT side

                // 🧱 If current left building is taller than or equal to leftMax, update leftMax
                if (heights[left] >= leftMax) {
                    leftMax = heights[left]; // new taller boundary found on left
                } else {
                    // 💧 Else, current building is shorter → water trapped = leftMax - current height
                    totalWater += (leftMax - heights[left]);
                }

                left++; // ➡️ Move left pointer one step inward
            } else {
                // 👉 We process the RIGHT side

                // 🧱 If current right building is taller than or equal to rightMax, update rightMax
                if (heights[right] >= rightMax) {
                    rightMax = heights[right]; // new taller boundary found on right
                } else {
                    // 💧 Else, current building is shorter → water trapped = rightMax - current height
                    totalWater += (rightMax - heights[right]);
                }

                right--; // ⬅️ Move right pointer one step inward
            }
        }

        return totalWater; // ✅ Return total units of water trapped
    }

    // 🧪 Test method to verify functionality
    public static void main(String[] args) {
        // Test cases map: each entry = (input array → expected output)
        Map<int[], Integer> testCases = new LinkedHashMap<>();

        // Example from your question
        testCases.put(new int[]{10, 15, 12, 10, 9, 12, 8}, 5);
        // Other quick sanity tests
        testCases.put(new int[]{3, 0, 2, 0, 4}, 7);
        testCases.put(new int[]{1, 2, 3, 4, 5}, 0);
        testCases.put(new int[]{5, 4, 3, 2, 1}, 0);
        testCases.put(new int[]{2, 2, 2, 2}, 0);

        // Performance test — large input (increasing then decreasing)
        int[] largeInput = IntStream.concat(
                IntStream.rangeClosed(1, 500_000),
                IntStream.iterate(500_000, i -> i - 1).limit(500_000)
        ).toArray();
        testCases.put(largeInput, 0);

        // 🔍 Run and validate all test cases
        int testNum = 1;
        for (Map.Entry<int[], Integer> entry : testCases.entrySet()) {
            int actual = calculateTrappedWater(entry.getKey());
            int expected = entry.getValue();

            if (actual == expected) {
                System.out.println("TestCase " + testNum + ": PASS (Expected=" + expected + ", Actual=" + actual + ")");
            } else {
                System.out.println("TestCase " + testNum + ": FAIL (Expected=" + expected + ", Actual=" + actual + ")");
            }
            testNum++;
        }
    }
}
