package com.interview.notes.code.year.y2024.dec24.test5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//NOT TRIED
public class CoinCollection {
    public static int solve(List<Integer> A, List<Integer> B) {
        int maxSum = 0;
        int n = A.size();
        int m = B.size();

        // Try starting from each road
        maxSum = Math.max(
                findMaxPath(A, B, true), // Start from road A
                findMaxPath(B, A, true)  // Start from road B
        );

        return maxSum;
    }

    private static int findMaxPath(List<Integer> road1, List<Integer> road2, boolean ascending) {
        int maxSum = 0;
        int currentSum = 0;
        int i = ascending ? 0 : road1.size() - 1;

        // Try all possible switching points
        while (ascending ? i < road1.size() : i >= 0) {
            int currentCoin = road1.get(i);

            // Try switching to other road at matching values
            int maxSwitchSum = currentSum;
            for (int j = 0; j < road2.size(); j++) {
                if (road2.get(j).equals(currentCoin)) {
                    int remainingSum = calcRemainingSum(road2, j, ascending);
                    maxSwitchSum = Math.max(maxSwitchSum, currentSum + remainingSum);
                }
            }

            currentSum += currentCoin;
            maxSum = Math.max(maxSum, Math.max(currentSum, maxSwitchSum));

            i = ascending ? i + 1 : i - 1;
        }

        return maxSum;
    }

    private static int calcRemainingSum(List<Integer> road, int startIndex, boolean ascending) {
        int sum = road.get(startIndex);
        if (ascending) {
            for (int i = startIndex + 1; i < road.size(); i++) {
                sum += road.get(i);
            }
        } else {
            for (int i = startIndex - 1; i >= 0; i--) {
                sum += road.get(i);
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        // Test Case 1
        List<Integer> A1 = Arrays.asList(2, 6, 8, 9);
        List<Integer> B1 = Arrays.asList(1, 4, 5, 6);
        int result1 = solve(A1, B1);
        System.out.println("Test Case 1: " + (result1 == 33 ? "PASS" : "FAIL") + " (Got: " + result1 + ", Expected: 33)");

        // Test Case 2
        List<Integer> A2 = Arrays.asList(8, 7, 6, 5, 2, 1);
        List<Integer> B2 = Arrays.asList(9, 8, 7, 4, 2);
        int result2 = solve(A2, B2);
        System.out.println("Test Case 2: " + (result2 == 38 ? "PASS" : "FAIL") + " (Got: " + result2 + ", Expected: 38)");

        // Edge Case: Empty lists
        List<Integer> A3 = new ArrayList<>();
        List<Integer> B3 = new ArrayList<>();
        int result3 = solve(A3, B3);
        System.out.println("Edge Case (Empty): " + (result3 == 0 ? "PASS" : "FAIL") + " (Got: " + result3 + ", Expected: 0)");

        // Large Input Test
        List<Integer> A4 = new ArrayList<>();
        List<Integer> B4 = new ArrayList<>();
        for (int i = 0; i < 103; i++) {
            A4.add(i);
            B4.add(i);
        }
        long startTime = System.currentTimeMillis();
        solve(A4, B4);
        long endTime = System.currentTimeMillis();
        System.out.println("Large Input Performance: " + (endTime - startTime) + "ms");
    }
}
