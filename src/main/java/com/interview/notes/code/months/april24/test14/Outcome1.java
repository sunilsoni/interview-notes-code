package com.interview.notes.code.months.april24.test14;

import java.util.Arrays;
import java.util.List;

public class Outcome1 {

    public static int solve(int P, List<Integer> a) {
        int collectedGarbage = 0;

        // Check the position P itself first
        if (a.get(P - 1) == 1) {
            collectedGarbage++;
            a.set(P - 1, 0); // Set to 0 once collected
        }

        // Check distances from the starting position P
        for (int dist = 1; dist < a.size(); dist++) {
            int leftIndex = P - dist - 1;
            int rightIndex = P + dist - 1;
            boolean left = leftIndex >= 0 && a.get(leftIndex) == 1;
            boolean right = rightIndex < a.size() && a.get(rightIndex) == 1;

            // If both sides have garbage, collect both
            if (left && right) {
                collectedGarbage += 2;
                a.set(leftIndex, 0); // Set to 0 once collected
                a.set(rightIndex, 0); // Set to 0 once collected
            } else if (left) { // If only left side has garbage, collect it
                collectedGarbage++;
                a.set(leftIndex, 0); // Set to 0 once collected
            } else if (right) { // If only right side has garbage, collect it
                collectedGarbage++;
                a.set(rightIndex, 0); // Set to 0 once collected
            }
        }

        return collectedGarbage;
    }

    public static void main(String[] args) {
        List<List<Integer>> examples = Arrays.asList(
                Arrays.asList(1, 0, 1, 1, 1), // Example #1
                Arrays.asList(0, 1, 1, 0)     // Example #2
        );
        List<Integer> starts = Arrays.asList(3, 4); // Starting positions for each example

        for (int i = 0; i < examples.size(); i++) {
            int result = solve(starts.get(i), examples.get(i));
            System.out.println("Example #" + (i + 1) + " Output: " + result);
        }
    }
}
