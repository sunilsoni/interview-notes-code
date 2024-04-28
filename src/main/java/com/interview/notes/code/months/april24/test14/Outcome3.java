package com.interview.notes.code.months.april24.test14;

import java.util.*;

class Outcome3 {
    public static int solve(int P, List<Integer> a) {
        int collectedGarbage = 0;
        int distance = 1;
        
        for (int position : a) {
            if (position == 1) { // If there's garbage at this position
                if (P - distance > 0 && a.get(P - distance - 1) == 1) {
                    collectedGarbage++; // Garbage to the left
                }
                if (P + distance <= a.size() && a.get(P + distance - 1) == 1) {
                    collectedGarbage++; // Garbage to the right
                }
            }
            distance++;
        }
        
        return collectedGarbage;
    }

    public static void main(String[] args) {
        // Example usage:
        int P = 3;
        List<Integer> a = Arrays.asList(1, 0, 1, 1, 1);
        System.out.println(solve(P, a)); // Output should be 3
    }
}
