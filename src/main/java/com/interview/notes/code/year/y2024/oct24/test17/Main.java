package com.interview.notes.code.year.y2024.oct24.test17;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Test cases
        runTest(Arrays.asList(3, 4, 5, 6, 7, 10, 2), 10, 5);
        runTest(Arrays.asList(20, 33, 44, 1), 11, -1);

        // Large data input test
        List<Integer> largeInput = new ArrayList<>();
        for (int i = 50000; i < 100000; i++) {
            largeInput.add(i);
        }
        for (int i = 0; i < 50000; i++) {
            largeInput.add(i);
        }
        runTest(largeInput, 75000, 25000);
        runTest(largeInput, 25000, 75000);
        runTest(largeInput, 100001, -1);
    }

    public static void runTest(List<Integer> ar, int K, int expected) {
        int result = 0;// solve(ar, K);
        System.out.println("Input: " + ar);
        System.out.println("K: " + K);
        System.out.println("Expected: " + expected);
        System.out.println("Result: " + result);
        System.out.println("Test " + (result == expected ? "PASSED" : "FAILED"));
        System.out.println();
    }

    // The solve method implementation goes here
}
