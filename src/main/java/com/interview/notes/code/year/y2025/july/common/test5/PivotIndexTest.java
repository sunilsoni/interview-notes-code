package com.interview.notes.code.year.y2025.july.common.test5;

import java.util.Arrays;
import java.util.List;

public class PivotIndexTest {
    public static void main(String[] args) {
        // Define test cases and expected results
        List<int[]> inputs = Arrays.asList(
            new int[]{7, 6, 3, 0, 2, 3, 1, 4, 8, 7, -2},  // expect 5
            new int[]{1, 7, 3, 6, 5, 6},                 // expect 3
            new int[]{},                                  // expect -1
            new int[]{42},                                // expect 0
            new int[]{1, 2},                              // expect -1
            new int[]{2, 1, -1}                           // expect 0
        );
        int[] expected = {5, 3, -1, 0, -1, 0};

        // Run unit of work
        for (int i = 0; i < inputs.size(); i++) {
            int result = PivotIndexFinder.pivotIndex(inputs.get(i));
            boolean pass = result == expected[i];
            System.out.printf("Test %d: %s (expected=%d, got=%d)%n",
                              i + 1, pass ? "PASS" : "FAIL",
                              expected[i], result);
        }

        // Large data performance test
        int n = 1_000_000;
        int pivot = n / 2;
        int[] large = new int[n];
        // place values so that index 'pivot' is a valid pivot
        large[pivot - 1] = -500;
        large[pivot]     = 1000;
        large[pivot + 1] = -500;
        long start = System.nanoTime();
        int largeResult = PivotIndexFinder.pivotIndex(large);
        long ms = (System.nanoTime() - start) / 1_000_000;
        System.out.printf("Large test: %s (idx=%d, time=%d ms)%n",
                          largeResult == pivot ? "PASS" : "FAIL",
                          largeResult, ms);
    }
}