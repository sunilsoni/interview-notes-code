package com.interview.notes.code.year.y2024.sept24.test7;

public class RangeGame {

    public static void main(String[] args) {
        int N = 5; // Example input
        System.out.println(solve(N));
    }

    public static int solve(int n) {
        boolean[] targets = new boolean[n];
        int remainingTargets = n;
        long totalPoints = 0;

        while (remainingTargets > 1) {
            int hitCount = 0;
            for (int i = 0; i < n; i++) {
                if (!targets[i]) {
                    if (hitCount % 2 == 0) {
                        // Hit this target
                        totalPoints += (i + 1);
                        targets[i] = true;
                        remainingTargets--;
                    }
                    hitCount++;
                }
            }
        }

        return (int) totalPoints;
    }
}
