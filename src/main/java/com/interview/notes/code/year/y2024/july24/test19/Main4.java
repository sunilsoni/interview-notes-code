package com.interview.notes.code.year.y2024.july24.test19;

public class Main4 {
    public static int PassingGame(int N, int[] A) {
        //this is default OUTPUT. You can change it.
        int result = -404;
        //write your Logic here:
        int maxEnergy = 0;
        for (int i = 0; i < N; i++) {
            maxEnergy = Math.max(maxEnergy, A[i]);
        }

        result = maxEnergy + N - 1; // Total energy + passes needed 
        return result;
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