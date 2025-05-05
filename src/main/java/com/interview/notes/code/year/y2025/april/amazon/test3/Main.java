package com.interview.notes.code.year.y2025.april.amazon.test3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {

    public static int countPossibleWinners(List<Integer> initialRewards) {
        int n = initialRewards.size();
        int[] rewards = new int[n];
        for (int i = 0; i < n; i++) {
            rewards[i] = initialRewards.get(i);
        }

        Arrays.sort(rewards);

        int count = 0;
        for (int i = 0; i < n; i++) {
            int rank = n;
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                if (initialRewards.get(j) + (j < i ? n - j : n - j - 1) > initialRewards.get(i) + rank) {
                    rank--;
                }
            }
            if (rank > 0) {
                count++;
                break;
            }
        }

        // re-evaluate the logic
        int maxPossible = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            maxPossible = Math.max(maxPossible, initialRewards.get(i) + n);
        }

        int winnerCount = 0;
        for (int i = 0; i < n; i++) {
            int points = initialRewards.get(i) + n;
            int worstCase = initialRewards.get(i) + 1;
            boolean canWin = true;
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                if (initialRewards.get(j) + (n - (j > i ? j : j + 1)) >= points) {
                    canWin = false;
                    break;
                }
            }
            if (canWin) winnerCount++;
        }

        return winnerCount;
    }

    public static void main(String[] args) {
        // Test cases
        List<Integer> test1 = Arrays.asList(1, 3, 4);
        System.out.println("Test case 1: " + (countPossibleWinners(test1) == 1 ? "PASS" : "FAIL"));

        List<Integer> test2 = Arrays.asList(8, 10, 9);
        System.out.println("Test case 2: " + (countPossibleWinners(test2) == 2 ? "PASS" : "FAIL"));

        List<Integer> test3 = Arrays.asList(5, 7, 9, 11);
        System.out.println("Test case 3: " + (countPossibleWinners(test3) == 1 ? "PASS" : "FAIL"));

        // Large data test case
        List<Integer> largeTest = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < 100000; i++) {
            largeTest.add(rand.nextInt(100000));
        }
        System.out.println("Large test case executed");

        long startTime = System.currentTimeMillis();
        countPossibleWinners(largeTest);
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken for large test case: " + (endTime - startTime) + "ms");
    }
}