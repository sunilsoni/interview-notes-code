package com.interview.notes.code.LeetCode;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class TrappingRainWater {
    /*
     **  Find the amount of snow that could be captured.
     */
    public static Integer computeSnowpack(Integer[] height) {
        if (height == null || height.length < 2) return 0;

        Stack<Integer> stack = new Stack<>();
        int water = 0, i = 0;
        while (i < height.length) {
            if (stack.isEmpty() || height[i] <= height[stack.peek()]) {
                stack.push(i++);
            } else {
                int pre = stack.pop();
                if (!stack.isEmpty()) {
                    // find the smaller height between the two sides
                    int minHeight = Math.min(height[stack.peek()], height[i]);
                    // calculate the area
                    water += (minHeight - height[pre]) * (i - stack.peek() - 1);
                }
            }
        }

        System.out.println("water: "+water);
        return water;
    }




    /* Problem Name is &&& Snowpack &&& PLEASE DO NOT REMOVE THIS LINE. */

    /*
     ** Instructions to candidate.
     **  1) Given an array of non-negative integers representing the elevations
     **     from the vertical cross section of a range of hills, determine how
     **     many units of snow could be captured between the hills.
     **
     **     See the example array and elevation map below.
     **                                 ___
     **             ___                |   |        ___
     **            |   |        ___    |   |___    |   |
     **         ___|   |    ___|   |   |   |   |   |   |
     **     ___|___|___|___|___|___|___|___|___|___|___|___
     **     {0,  1,  3,  0,  1,  2,  0,  4,  2,  0,  3,  0}
     **                                 ___
     **             ___                |   |        ___
     **            |   | *   *  _*_  * |   |_*_  * |   |
     **         ___|   | *  _*_|   | * |   |   | * |   |
     **     ___|___|___|_*_|___|___|_*_|___|___|_*_|___|___
     **     {0,  1,  3,  0,  1,  2,  0,  4,  2,  0,  3,  0}
     **
     **     Solution: In this example 13 units of snow (*) could be captured.
     **
     **  2) Consider adding some additional tests in doTestsPass().
     **  3) Implement computeSnowpack() correctly.
     */

    /*
     **  Returns true if the tests pass. Otherwise, returns false;
     */
    public static boolean doTestsPass() {
        boolean result = true;
        result &= computeSnowpack(new Integer[]{0, 1, 3, 0, 1, 2, 0, 4, 2, 0, 3, 0}) == 13;
        result &= computeSnowpack(new Integer[]{2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2}) == 13;

        return result;
    }

    /*
     **  Execution entry point.
     */
    public static void main(String[] args) {
        if (doTestsPass()) {
            System.out.println("All tests pass");
        } else {
            System.out.println("Tests fail.");
        }
    }

    public int trap(int[] A) {
        if (A == null) return 0;
        Stack<Integer> s = new Stack<Integer>();
        int i = 0, maxWater = 0, maxBotWater = 0;
        while (i < A.length) {
            if (s.isEmpty() || A[i] <= A[s.peek()]) {
                s.push(i++);
            } else {
                int bot = s.pop();
                maxBotWater = s.isEmpty() ? // empty means no il
                        0 : (Math.min(A[s.peek()], A[i]) - A[bot]) * (i - s.peek() - 1);
                maxWater += maxBotWater;
            }
        }
        return maxWater;
    }
}
