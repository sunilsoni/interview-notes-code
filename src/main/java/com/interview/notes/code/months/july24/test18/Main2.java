package com.interview.notes.code.months.july24.test18;

import java.util.Arrays;

public class Main2 {
    public static int PassingGame(int N, int[] A) {
        // Sort the array in descending order
        Arrays.sort(A);
        reverse(A);

        long totalEnergy = 0;
        for (int energy : A) {
            totalEnergy += energy;
        }

        long passes = 0;
        long remainingPlayers = N;

        for (int i = 0; i < N; i++) {
            long currentPasses = Math.min(A[i], totalEnergy / remainingPlayers);
            passes += currentPasses;
            totalEnergy -= currentPasses * remainingPlayers;
            remainingPlayers--;

            if (totalEnergy == 0) break;
        }

        return (int) passes;
    }

    // Helper method to reverse an array
    private static void reverse(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        // Test case 1: Given example
        int N1 = 3;
        int[] A1 = {2, 1, 1};
        System.out.println("Test case 1 output: " + PassingGame(N1, A1));  // Expected: 1

        // Test case 2: All players have same energy
        int N2 = 5;
        int[] A2 = {3, 3, 3, 3, 3};
        System.out.println("Test case 2 output: " + PassingGame(N2, A2));  // Expected: 3

        // Test case 3: One player has all the energy
        int N3 = 4;
        int[] A3 = {10, 0, 0, 0};
        System.out.println("Test case 3 output: " + PassingGame(N3, A3));  // Expected: 2

        // Test case 4: Total energy less than N
        int N4 = 5;
        int[] A4 = {1, 1, 1, 0, 0};
        System.out.println("Test case 4 output: " + PassingGame(N4, A4));  // Expected: 4

        // Test case 5: Large input
        int N5 = 100000;
        int[] A5 = new int[N5];
        Arrays.fill(A5, 1000000);
        System.out.println("Test case 5 output: " + PassingGame(N5, A5));  // Expected: 12158
    }
}
