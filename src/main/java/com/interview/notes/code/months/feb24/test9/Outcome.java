package com.interview.notes.code.months.feb24.test9;

import java.util.Stack;

/**
 * Biggest Rectangle
 * You are given data about a bar graph as an array of integers in which each element represents the height of a bar from left to right. You depict this data as a bar graph and then realize that you can form rectangles by taking the filled areas of a bar or by combining a number of contiguous bars. Assuming that all bars have equal width, you are curious to find the area of the largest rectangle that can be thus formed.
 * For example, the largest rectangle that can be formed out of the bars in the bar graph represented as 7 3 6 5 6 0 7 is marked in red in the image given below.
 * MAX AREA = 5 X 3 = 15
 * 7
 * 7
 * 6
 * 6
 * 5
 * 3
 * 0
 * Your task is to write a program that can find out the area of the largest rectangle from the given data about bar charts.
 */
public class Outcome {
    public static void main(String[] args) {
        int[] heights1 = {7, 3, 6, 5, 6, 0, 7};
        System.out.println(largestRectangleArea(heights1)); // Example 1

        int[] heights2 = {9, 1, 1, 9};
        System.out.println(largestRectangleArea(heights2)); // Example 2
    }

    public static int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        int i = 0;

        while (i <= heights.length) {
            int h = (i == heights.length ? 0 : heights[i]);
            if (stack.isEmpty() || h >= heights[stack.peek()]) {
                stack.push(i);
                i++;
            } else {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - 1 - stack.peek();
                maxArea = Math.max(maxArea, height * width);
            }
        }

        return maxArea;
    }
}
