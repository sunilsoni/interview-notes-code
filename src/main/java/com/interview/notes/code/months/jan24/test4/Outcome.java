package com.interview.notes.code.months.jan24.test4;

import java.util.Arrays;
import java.util.List;

class Outcome {

    public static int solve(int p, List<Integer> a) {
        int collected = 0;
        int left = p - 2; // start from the left of the initial position
        int right = p; // start from the right of the initial position
        boolean leftDone = (left < 0); // if left is out of bounds, no need to check
        boolean rightDone = (right >= a.size()); // if right is out of bounds, no need to check

        while (!leftDone || !rightDone) {
            if (!leftDone) {
                if (a.get(left) == 1) {
                    collected++;
                    a.set(left, 0); // mark as collected
                }
                left--;
                leftDone = (left < 0); // mark as done if out of bounds
            }

            if (!rightDone) {
                if (a.get(right) == 1) {
                    collected++;
                    a.set(right, 0); // mark as collected
                }
                right++;
                rightDone = (right >= a.size()); // mark as done if out of bounds
            }
        }

        return collected;
    }

    public static void main(String[] args) {
        // Example 1
        int p1 = 3;
        List<Integer> a1 = Arrays.asList(1, 0, 1, 1, 1);
        System.out.println(Outcome.solve(p1 - 1, a1)); // Adjusting for 1-based index

        // Example 2
        int p2 = 4;
        List<Integer> a2 = Arrays.asList(0, 1, 1, 0);
        System.out.println(Outcome.solve(p2 - 1, a2)); // Adjusting for 1-based index
    }
}
