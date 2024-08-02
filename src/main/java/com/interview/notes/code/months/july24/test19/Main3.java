package com.interview.notes.code.months.july24.test19;

public class Main3 {
    public static int PassingGame(int N, int[] A) {
        // This is the default OUTPUT. You can change it.
        int result = -404;

        // Calculate the total energy available
        int totalEnergy = 0;
        for (int energy : A) {
            totalEnergy += energy;
        }

        // The maximum duration of the game is determined by the total energy
        // and the number of players.
        // Each player can pass the ball until their energy reaches zero.

        // The maximum duration of the game is limited by the smaller of:
        // 1. The total amount of energy available (totalEnergy)
        // 2. The total number of players (N), since after N passes, 
        // the ball would return to the starting player.
        result = Math.min(totalEnergy, N);

        // Each player can contribute at least their energy + 1 pass to the game duration.
        return result + (totalEnergy >= N ? 0 : totalEnergy);
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(PassingGame(3, new int[]{2, 1, 1})); // Expected output: 4
        System.out.println(PassingGame(4, new int[]{3, 0, 2, 1})); // Expected output: 4
        System.out.println(PassingGame(2, new int[]{1, 1})); // Expected output: 2
        System.out.println(PassingGame(4, new int[]{3, 3, 3, 3})); // Expected output: 4
        System.out.println(PassingGame(5, new int[]{5, 0, 0, 0, 0})); // Expected output: 5
        System.out.println(PassingGame(6, new int[]{0, 0, 0, 0, 0, 0})); // Expected output: 0
        System.out.println(PassingGame(4, new int[]{1, 2, 3, 4})); // Expected output: 6
        System.out.println(PassingGame(1, new int[]{10})); // Expected output: 10
    }
}