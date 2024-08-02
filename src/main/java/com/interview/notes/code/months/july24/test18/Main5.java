package com.interview.notes.code.months.july24.test18;

import java.util.Arrays;

public class Main5 {
    public static int PassingGame(int N, int[] A) {
        // Sort the array in descending order
        Arrays.sort(A);
        reverse(A);

        int duration = 0;
        int remainingPlayers = N;

        for (int i = 0; i < N; i++) {
            if (A[i] == 0) {
                // Game ends if we encounter a player with 0 energy
                break;
            }

            int fullRounds = Math.min(A[i], remainingPlayers - 1);
            duration += fullRounds;

            if (A[i] > remainingPlayers - 1) {
                // This player will get the ball again
                duration++;
                A[i] -= remainingPlayers;
                i--; // Recheck this player in the next iteration
            } else {
                remainingPlayers--;
            }
        }

        return duration;
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
        // Test cases
        System.out.println(PassingGame(3, new int[]{2, 1, 1})); // Expected: 4
        System.out.println(PassingGame(10, new int[]{65, 11, 82, 34, 57, 50, 99, 12, 24, 1})); // Expected: 19
        System.out.println(PassingGame(4, new int[]{3, 0, 2, 1})); // Expected: 3
        System.out.println(PassingGame(5, new int[]{3, 2, 1, 4, 5})); // Expected: 15
        System.out.println(PassingGame(1, new int[]{10})); // Expected: 10
        System.out.println(PassingGame(4, new int[]{1, 1, 1, 1})); // Expected: 4
        System.out.println(PassingGame(6, new int[]{10, 5, 8, 3, 6, 2})); // Expected: 34
    }
}
