package com.interview.notes.code.months.year2023.nov23.amazon;

import java.util.Stack;

/**
 * You have apartment puildings with a specific height on an ocean shore. Your task is to figure out
 * which building would be blocking the ocean view for each building. Given building heights: 4,5,2,8,1,5 return 5,8,8,-1,5,-1
 */
public class OceanView {

    public static int[] findBlockingBuildings(int[] heights) {
        int n = heights.length;
        int[] blockers = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() < heights[i]) {
                stack.pop();
            }
            blockers[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(heights[i]);
        }

        return blockers;
    }

    /**
     * Q1: Algorithm Change if Ocean on the Left
     * If the ocean is on the left, the buildings would block the view in the opposite direction.
     * The algorithm would iterate from left to right instead of right to left.
     * The logic remains the same, with the stack tracking potential blockers for future buildings.
     *
     * @param heights
     * @return
     */
    public static int[] findBlockingBuildingsLeftOcean(int[] heights) {
        int n = heights.length;
        int[] blockers = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && stack.peek() < heights[i]) {
                stack.pop();
            }
            blockers[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(heights[i]);
        }

        return blockers;
    }


    public static void main(String[] args) {
        int[] buildingHeights = {4, 5, 2, 8, 1, 5};
        int[] blockingBuildings = findBlockingBuildings(buildingHeights);

        for (int height : blockingBuildings) {
            System.out.print(height + " ");
        }
        System.out.println();
        int[] buildingHeights1 = {1, 1, 1, 1, 1, 1};
        int[] blockingBuildings1 = findBlockingBuildings(buildingHeights1);

        for (int height : blockingBuildings1) {
            System.out.print(height + " ");
        }
    }
}
