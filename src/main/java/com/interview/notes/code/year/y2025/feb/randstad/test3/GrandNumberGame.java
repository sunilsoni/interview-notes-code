package com.interview.notes.code.year.y2025.feb.randstad.test3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GrandNumberGame {

    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    public static int solve(List<Integer> arr) {
        int n = arr.size() / 2;
        boolean[] used = new boolean[arr.size()];
        return solveRecursive(arr, used, 1, 0, n);
    }

    private static int solveRecursive(List<Integer> arr, boolean[] used, int opNum, int currentScore, int n) {
        if (opNum > n) {
            return currentScore;
        }

        int maxScore = 0;
        for (int i = 0; i < arr.size(); i++) {
            if (!used[i]) {
                for (int j = i + 1; j < arr.size(); j++) {
                    if (!used[j]) {
                        used[i] = true;
                        used[j] = true;
                        int score = currentScore + opNum * gcd(arr.get(i), arr.get(j));
                        maxScore = Math.max(maxScore, solveRecursive(arr, used, opNum + 1, score, n));
                        used[i] = false; // Backtrack
                        used[j] = false; // Backtrack
                    }
                }
            }
        }
        return maxScore;
    }

    public static void main(String[] args) {
        // Test Case 1
        List<Integer> arr1 = new ArrayList<>(Arrays.asList(3, 4, 9, 5));
        int result1 = solve(arr1);
        System.out.println("Test Case 1 - Expected: 7, Actual: " + result1 + " - " + (result1 == 7 ? "PASS" : "FAIL"));

        // Test Case 2
        List<Integer> arr2 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        int result2 = solve(arr2);
        System.out.println("Test Case 2 - Expected: 14, Actual: " + result2 + " - " + (result2 == 14 ? "PASS" : "FAIL"));

        // Test Case 3: Empty Array
        List<Integer> arr3 = new ArrayList<>();
        int result3 = solve(arr3);
        System.out.println("Test Case 3 (Empty) - Expected: 0, Actual: " + result3 + " - " + (result3 == 0 ? "PASS" : "FAIL"));

        // Test Case 4: N = 1
        List<Integer> arr4 = new ArrayList<>(Arrays.asList(10, 25));
        int result4 = solve(arr4);
        System.out.println("Test Case 4 (N=1) - Expected: 5, Actual: " + result4 + " - " + (result4 == 5 ? "PASS" : "FAIL"));

        // Test Case 5: Large Numbers
        List<Integer> arr5 = new ArrayList<>(Arrays.asList(1000000000, 999999999, 500000000, 499999999));
        int result5 = solve(arr5);
        System.out.println("Test Case 5 (Large) - Expected: 3, Actual: " + result5 + " - " + (result5 == 3 ? "PASS" : "FAIL"));
    }
}