package com.interview.notes.code.year.y2024.jan24.test4;

import java.util.List;

class Outcome1 {

    public static int solve(int p, List<Integer> a) {
        int collected = 0;
        int left = p - 2; // start from the left of the initial position
        int right = p; // start from the initial position
        boolean leftDone = false, rightDone = false;

        while (!leftDone || !rightDone) {
            if (!leftDone) {
                if (left >= 0 && a.get(left) == 1) {
                    collected++;
                    a.set(left, 0); // mark as collected
                } else {
                    leftDone = true;
                }
                left--;
            }

            if (!rightDone) {
                if (right < a.size() && a.get(right) == 1) {
                    collected++;
                    a.set(right, 0); // mark as collected
                } else {
                    rightDone = true;
                }
                right++;
            }
        }

        return collected;
    }
}
