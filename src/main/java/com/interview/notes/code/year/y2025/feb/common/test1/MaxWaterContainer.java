package com.interview.notes.code.year.y2025.feb.common.test1;

public class MaxWaterContainer {

    // Method to calculate the maximum water container area
    public static int maxArea(int[] heights) {
        int n = heights.length;
        int maxArea = 0;

        // Brute force approach: evaluate every pair of bars
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                // Calculate the area between bar i and bar j
                int width = j - i;
                int height = Math.min(heights[i], heights[j]);
                int area = width * height;

                // Update the max area
                maxArea = Math.max(maxArea, area);
            }
        }
        return maxArea;
    }

    // Test method to validate solution against different test cases
    public static void main(String[] args) {
        // Test cases
        int[] heights1 = {1, 7, 2, 5, 4, 7, 3, 6}; // Expected output: 36
        int[] heights2 = {2, 2, 2}; // Expected output: 4

        System.out.println("Test case 1: " + (maxArea(heights1) == 36 ? "PASS" : "FAIL"));
        System.out.println("Test case 2: " + (maxArea(heights2) == 4 ? "PASS" : "FAIL"));

        // Additional test case for edge scenario
        int[] heights3 = {1000, 1000, 1000, 1000}; // Expected output: 3000
        System.out.println("Test case 3: " + (maxArea(heights3) == 3000 ? "PASS" : "FAIL"));

        // Test case with large data input
        int[] heights4 = new int[1000];
        for (int i = 0; i < 1000; i++) {
            heights4[i] = (i % 100) + 1;
        }
        System.out.println("Test case 4 (Large input): " + (maxArea(heights4) > 0 ? "PASS" : "FAIL"));
    }
}