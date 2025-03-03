package com.interview.notes.code.year.y2025.jan.ibm.test3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ConsecutiveNumbersGame {

    // Core logic as specified:
    public static int solve(List<Integer> arr) {
        if (arr == null || arr.size() <= 1) {
            return 0;
        }

        int n = arr.size();
        int rounds = 0;

        // Helper to check if first n-1 elements are multiples of 3
        while (!allButLastAreMultiplesOf3(arr)) {
            // Perform one round
            for (int i = 0; i < n - 1; i++) {
                int product = arr.get(i) * arr.get(i + 1);
                if (product % 3 == 0) {
                    arr.set(i, product);
                }
            }
            rounds++;
        }
        return rounds;
    }

    private static boolean allButLastAreMultiplesOf3(List<Integer> arr) {
        for (int i = 0; i < arr.size() - 1; i++) {
            if (arr.get(i) % 3 != 0) {
                return false;
            }
        }
        return true;
    }

    // Simple main method for testing (no JUnit)
    public static void main(String[] args) {

        // Minimal test #1 (from example)
        List<Integer> test1 = new ArrayList<>();
        test1.add(34);
        test1.add(56);
        test1.add(20);
        test1.add(90);
        test1.add(100);
        int result1 = solve(new ArrayList<>(test1));
        System.out.println("Test #1: " + (result1 == 3 ? "PASS" : "FAIL")
                + " (Expected 3, Got " + result1 + ")");

        // Minimal test #2 (from example)
        List<Integer> test2 = new ArrayList<>();
        test2.add(1);
        test2.add(333);
        test2.add(222);
        test2.add(22);
        int result2 = solve(new ArrayList<>(test2));
        System.out.println("Test #2: " + (result2 == 1 ? "PASS" : "FAIL")
                + " (Expected 1, Got " + result2 + ")");

        // Edge case: single element
        List<Integer> test3 = new ArrayList<>();
        test3.add(7);
        int result3 = solve(new ArrayList<>(test3));
        System.out.println("Test #3: " + (result3 == 0 ? "PASS" : "FAIL")
                + " (Expected 0, Got " + result3 + ")");

        // Edge case: two elements that never become multiple of 3
        List<Integer> test4 = new ArrayList<>();
        test4.add(2);
        test4.add(2);
        // This may never converge if the problem doesn't define a stop,
        // but we run it briefly:
        int result4 = solve(new ArrayList<>(test4));
        // No official expected output given by problem statement.
        // We'll just print the result for observation.
        System.out.println("Test #4: Rounds = " + result4);

        // Larger random test (to show it can handle bigger data).
        // WARNING: Adjust size/time as needed.
        List<Integer> largeTest = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < 20; i++) {
            largeTest.add(rand.nextInt(500) + 1); // random 1..500
        }
        int largeResult = solve(new ArrayList<>(largeTest));
        System.out.println("Large Test: Finished with rounds = " + largeResult);
    }
}
