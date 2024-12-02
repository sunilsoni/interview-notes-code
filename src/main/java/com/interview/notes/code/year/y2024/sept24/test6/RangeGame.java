package com.interview.notes.code.year.y2024.sept24.test6;

public class RangeGame {
    public static void main(String[] args) {
        int N = 5; // Example input
        System.out.println(solve(N));
    }

    public static int solve(int N) {
        long totalPoints = 0;
        boolean[] targets = new boolean[N + 1]; // Track whether a target is hit
        int remainingTargets = N;

        while (remainingTargets > 1) {
            for (int i = 1; i <= N; i++) {
                if (!targets[i]) { // If the target is not hit
                    totalPoints += i; // Add points for hitting the target
                    targets[i] = true; // Mark the target as hit
                    i++; // Skip the next target
                    remainingTargets--; // Reduce the count of remaining targets
                }
            }
        }

        return (int) totalPoints;
    }
}
