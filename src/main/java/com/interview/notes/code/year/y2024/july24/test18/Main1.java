package com.interview.notes.code.year.y2024.july24.test18;

import java.util.HashSet;
import java.util.Set;

public class Main1 {
    public static int AvailableChoice(int N, int M, int[] X) {
        // Using a set to keep track of chosen questions
        Set<Integer> chosenQuestions = new HashSet<>();
        for (int i = 0; i < N; i++) {
            chosenQuestions.add(X[i]);
        }

        // Count the number of available questions
        int availableQuestions = 0;
        for (int i = 1; i <= M; i++) {
            if (!chosenQuestions.contains(i)) {
                availableQuestions++;
            }
        }

        // If no questions are available, return -1
        return availableQuestions == 0 ? -1 : availableQuestions;
    }

    public static void main(String[] args) {
        // Test Case 1
        int N1 = 4;
        int M1 = 3;
        int[] X1 = {1, 2, 2, 1};
        System.out.println("Output for Test Case 1: " + AvailableChoice(N1, M1, X1)); // Expected output: 1

        // Test Case 2
        int N2 = 5;
        int M2 = 5;
        int[] X2 = {1, 2, 3, 4, 5};
        System.out.println("Output for Test Case 2: " + AvailableChoice(N2, M2, X2)); // Expected output: -1

        // Test Case 3
        int N3 = 5;
        int M3 = 6;
        int[] X3 = {1, 2, 3, 4, 5};
        System.out.println("Output for Test Case 3: " + AvailableChoice(N3, M3, X3)); // Expected output: 1

        // Additional Test Case 4
        int N4 = 3;
        int M4 = 5;
        int[] X4 = {1, 2, 3};
        System.out.println("Output for Test Case 4: " + AvailableChoice(N4, M4, X4)); // Expected output: 2

        // Additional Test Case 5
        int N5 = 0;
        int M5 = 3;
        int[] X5 = {};
        System.out.println("Output for Test Case 5: " + AvailableChoice(N5, M5, X5)); // Expected output: 3
    }
}
