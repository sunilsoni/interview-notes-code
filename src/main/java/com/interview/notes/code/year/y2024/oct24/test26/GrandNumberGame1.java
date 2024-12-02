package com.interview.notes.code.year.y2024.oct24.test26;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GrandNumberGame1 {

    public static void main(String[] args) {
        // Test cases
        runTest(Arrays.asList(3, 4, 9, 5), 7, "Example 1");
        runTest(Arrays.asList(1, 2, 3, 4, 5, 6), 14, "Example 2");
        runTest(Arrays.asList(10, 20, 30, 40), 30, "Even numbers");
        runTest(Arrays.asList(7, 11, 13, 17), 4, "Prime numbers");
        runTest(generateLargeInput(20), 1140, "Large input");
    }

    private static void runTest(List<Integer> input, int expected, String testName) {
        int result = solve(input);
        System.out.println(testName + ": " + (result == expected ? "PASS" : "FAIL") +
                " (Expected: " + expected + ", Got: " + result + ")");
    }

    private static List<Integer> generateLargeInput(int n) {
        List<Integer> input = new ArrayList<>();
        for (int i = 1; i <= 2 * n; i++) {
            input.add(i * 1000000);
        }
        return input;
    }

    public static int solve(List<Integer> arr) {
        int n = arr.size() / 2;
        Integer[] sortedArr = arr.toArray(new Integer[0]);
        Arrays.sort(sortedArr, Collections.reverseOrder());

        int totalScore = 0;
        for (int i = 0; i < n; i++) {
            int maxGcd = 0;
            int bestPair = -1;
            for (int j = n; j < 2 * n; j++) {
                int currentGcd = gcd(sortedArr[i], sortedArr[j]);
                if (currentGcd > maxGcd) {
                    maxGcd = currentGcd;
                    bestPair = j;
                }
            }
            totalScore += (i + 1) * maxGcd;
            swap(sortedArr, bestPair, 2 * n - 1 - i);
        }

        return totalScore;
    }

    private static void swap(Integer[] arr, int i, int j) {
        Integer temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
