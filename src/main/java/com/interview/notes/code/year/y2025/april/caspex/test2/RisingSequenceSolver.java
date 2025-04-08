package com.interview.notes.code.year.y2025.april.caspex.test2;

import java.util.*;

/*
WORKING 100%
 */
public class RisingSequenceSolver {


    public static int solve(int B, List<Integer> ar) {
        int totalAdds = 0;
        int prev = ar.get(0);
        for (int i = 1; i < ar.size(); i++) {
            if (ar.get(i) <= prev) {
                int diff = prev - ar.get(i) + 1;
                int timesToAdd = (diff + B - 1) / B;
                prev = ar.get(i) + timesToAdd * B;
                totalAdds += timesToAdd;
            } else {
                prev = ar.get(i);
            }
        }
        return totalAdds;
    }

    public static void main(String[] args) {
        testCase(2, Arrays.asList(1, 3, 3, 2), 3);
        testCase(1, Arrays.asList(1, 1), 1);
        testCase(2, Arrays.asList(1, 5, 5, 4), 3);
        testCase(3, Arrays.asList(1, 2, 2), 1);
        // Large input test case
        testCase(1000000, new ArrayList<>(Collections.nCopies(2000, 1)), 1999);
    }

    private static void testCase(int B, List<Integer> input, int expected) {
        int result = solve(B, new ArrayList<>(input));
        System.out.println("B: " + B);
        System.out.println("Expected: " + expected);
        System.out.println("Output: " + result);
        System.out.println(result == expected ? "PASS" : "FAIL");
        System.out.println();
    }
}