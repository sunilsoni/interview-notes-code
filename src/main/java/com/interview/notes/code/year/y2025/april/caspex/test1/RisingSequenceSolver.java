package com.interview.notes.code.year.y2025.april.caspex.test1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RisingSequenceSolver {

    public static int solve(int B, List<Integer> ar) {
        int totalAdds = 0;
        for (int i = 1; i < ar.size(); i++) {
            if (ar.get(i) <= ar.get(i - 1)) {
                int diff = ar.get(i - 1) - ar.get(i) + 1;
                int timesToAdd = (int) Math.ceil((double) diff / B);
                ar.set(i, ar.get(i) + timesToAdd * B);
                totalAdds += timesToAdd;
            }
        }
        return totalAdds;
    }

    public static void main(String[] args) {
        testCase(2, Arrays.asList(1, 3, 3, 2), 3);
        testCase(1, Arrays.asList(1, 1), 1);
        // Large input test case
        testCase(1000000, new ArrayList<>(Collections.nCopies(2000, 1)), 1999);
    }

    private static void testCase(int B, List<Integer> input, int expected) {
        int result = solve(B, new ArrayList<>(input));
        System.out.println("Input: " + input);
        System.out.println("B: " + B);
        System.out.println("Expected: " + expected);
        System.out.println("Output: " + result);
        System.out.println(result == expected ? "PASS" : "FAIL");
        System.out.println();
    }
}
