package com.interview.notes.code.year.y2024.july24.test18;

import java.util.Arrays;

public class Main3 {
    public static int PassingGame(int N, int[] A) {
        // Sort the array in descending order
        Arrays.sort(A);
        reverse(A);

        int passes = 0;
        int currentPlayer = 0;

        while (A[currentPlayer] > 0) {
            int energy = A[currentPlayer];
            A[currentPlayer] = 0;
            passes += energy;

            // Distribute remaining energy to other players
            for (int i = 1; i < N && energy > 0; i++) {
                int nextPlayer = (currentPlayer + i) % N;
                A[nextPlayer]++;
                energy--;
            }

            // Move to the next player with energy
            do {
                currentPlayer = (currentPlayer + 1) % N;
            } while (A[currentPlayer] == 0 && currentPlayer != 0);
        }

        return passes;
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
        System.out.println("Test case 1 output: " + PassingGame(N1, A1));  // Expected: 4

        // Test case 2: All players have same energy
        int N2 = 5;
        int[] A2 = {3, 3, 3, 3, 3};
        System.out.println("Test case 2 output: " + PassingGame(N2, A2));  // Expected: 15

        // Test case 3: One player has all the energy
        int N3 = 4;
        int[] A3 = {10, 0, 0, 0};
        System.out.println("Test case 3 output: " + PassingGame(N3, A3));  // Expected: 10

        // Test case 4: Total energy less than N
        int N4 = 5;
        int[] A4 = {1, 1, 1, 0, 0};
        System.out.println("Test case 4 output: " + PassingGame(N4, A4));  // Expected: 3

        // Test case 5: New case
        int N5 = 10;
        int[] A5 = {65, 11, 82, 34, 57, 50, 99, 12, 24, 1};
        System.out.println("Test case 5 output: " + PassingGame(N5, A5));  // Expected: 19

        // Test case 6: Another new case
        int N6 = 4;
        int[] A6 = {3, 0, 2, 1};
        System.out.println("Test case 6 output: " + PassingGame(N6, A6));  // Expected: 3
    }
}
