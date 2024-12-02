package com.interview.notes.code.year.y2024.july24.test19;

public class Main1 {
    public static int AvailableChoice(int N, int M, int[] X) {
        // Create a boolean array to mark questions chosen by the friend
        boolean[] chosen = new boolean[M + 1];

        // Mark the questions chosen by the friend
        for (int i = 0; i < N; i++) {
            if (X[i] >= 1 && X[i] <= M) {
                chosen[X[i]] = true;
            }
        }

        // Count the number of questions not chosen
        int availableChoices = 0;
        for (int i = 1; i <= M; i++) {
            if (!chosen[i]) {
                availableChoices++;
            }
        }

        // If no questions are available, return -1
        return availableChoices == 0 ? -1 : availableChoices;
    }

    public static void main(String[] args) {
        // Test case 1
        int N1 = 4, M1 = 3;
        int[] X1 = {1, 2, 2, 1};
        System.out.println("Test case 1 output: " + AvailableChoice(N1, M1, X1));

        // Test case 2
        int N2 = 3, M2 = 3;
        int[] X2 = {1, 2, 3};
        System.out.println("Test case 2 output: " + AvailableChoice(N2, M2, X2));

        // Test case 3
        int N3 = 5, M3 = 5;
        int[] X3 = {1, 1, 1, 1, 1};
        System.out.println("Test case 3 output: " + AvailableChoice(N3, M3, X3));

        // Test case 4
        int N4 = 0, M4 = 5;
        int[] X4 = {};
        System.out.println("Test case 4 output: " + AvailableChoice(N4, M4, X4));

        // Test case 5
        int N5 = 3, M5 = 3;
        int[] X5 = {4, 5, 6};
        System.out.println("Test case 5 output: " + AvailableChoice(N5, M5, X5));
    }
}
