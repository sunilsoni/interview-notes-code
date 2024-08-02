package com.interview.notes.code.months.july24.test19;

import java.util.Scanner;

public class Main2 {
    public static int PassingGame(int N, int[] A) {
        // Total sum of energies
        int totalEnergy = 0;
        for (int i = 0; i < N; i++) {
            totalEnergy += A[i];
        }

        // Use sliding window to find the minimum energy loss during the passing
        int minEnergyLoss = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            int currentEnergyLoss = 0;
            for (int j = 0; j < N; j++) {
                currentEnergyLoss += j * A[(i + j) % N];
            }
            minEnergyLoss = Math.min(minEnergyLoss, currentEnergyLoss);
        }

        // Maximum duration of the game is totalEnergy minus the minimum energy loss
        int result = totalEnergy - minEnergyLoss;

        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Example Test Cases
        // Test Case 1
        int N1 = 10;
        int[] A1 = {65, 11, 82, 34, 57, 50, 99, 12, 24, 1};
        System.out.println("Output for Test Case 1: " + PassingGame(N1, A1)); // Expected output: 19

        // Test Case 2
        int N2 = 4;
        int[] A2 = {3, 0, 2, 1};
        System.out.println("Output for Test Case 2: " + PassingGame(N2, A2)); // Expected output: 3

        // Additional Test Case 3
        int N3 = 3;
        int[] A3 = {2, 1, 1};
        System.out.println("Output for Test Case 3: " + PassingGame(N3, A3)); // Expected output: 4

        // Read input for dynamic testing
        int N = sc.nextInt();
        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = sc.nextInt();
        }
        System.out.println(PassingGame(N, A));

        sc.close();
    }
}
