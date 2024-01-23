package com.interview.notes.code.months.jan24.test4;

import java.util.Arrays;
import java.util.List;

public class RubbleRoverTest {
    public static void main(String[] args) {
        // Example 1
        int p1 = 3;
        List<Integer> a1 = Arrays.asList(1, 0, 1, 1, 1);
        System.out.println(Outcome1.solve(p1, a1)); // Output should be 3

        // Example 2
        int p2 = 4;
        List<Integer> a2 = Arrays.asList(0, 1, 1, 0);
        System.out.println(Outcome1.solve(p2, a2)); // Output should be 2
    }
}
