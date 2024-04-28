package com.interview.notes.code.months.april24.test14;

import java.util.*;

public class Outcome2 {

    public static int solve(int P, List<Integer> a) {
        int collectedGarbage = 0;
        int distance = 1;

        // Check the initial position.
        if (a.get(P - 1) == 1) {
            collectedGarbage++;
            a.set(P - 1, 0); // Mark as collected.
        }

        // Continue checking other positions until the end of the array.
        while (P - distance - 1 >= 0 || P + distance - 1 < a.size()) {
            boolean left = P - distance - 1 >= 0 && a.get(P - distance - 1) == 1;
            boolean right = P + distance - 1 < a.size() && a.get(P + distance - 1) == 1;

            // Collect garbage only if there's certainty.
            if (left && right) {
                collectedGarbage += 2; // One on each side.
                a.set(P - distance - 1, 0); // Mark as collected.
                a.set(P + distance - 1, 0); // Mark as collected.
            } else if (left) {
                collectedGarbage++;
                a.set(P - distance - 1, 0); // Mark as collected.
            } else if (right) {
                collectedGarbage++;
                a.set(P + distance - 1, 0); // Mark as collected.
            }
            distance++;
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
