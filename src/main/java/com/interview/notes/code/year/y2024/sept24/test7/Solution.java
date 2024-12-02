package com.interview.notes.code.year.y2024.sept24.test7;

import java.util.Arrays;
import java.util.List;

public class Solution {

    // Helper function to compute GCD of two numbers
    private static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    public static int maxBalls(int N, List<Integer> marks) {
        int[] prefixGCD = new int[N];
        int[] suffixGCD = new int[N];

        // Step 1: Compute prefix GCDs
        prefixGCD[0] = marks.get(0);
        for (int i = 1; i < N; i++) {
            prefixGCD[i] = gcd(prefixGCD[i - 1], marks.get(i));
        }

        // Step 2: Compute suffix GCDs
        suffixGCD[N - 1] = marks.get(N - 1);
        for (int i = N - 2; i >= 0; i--) {
            suffixGCD[i] = gcd(suffixGCD[i + 1], marks.get(i));
        }

        // Step 3: Determine the maximum possible GCD after changing at most one student's marks
        int maxGCD = 0;
        for (int i = 0; i < N; i++) {
            int currentGCD;
            if (i == 0) {
                currentGCD = suffixGCD[1];
            } else if (i == N - 1) {
                currentGCD = prefixGCD[N - 2];
            } else {
                currentGCD = gcd(prefixGCD[i - 1], suffixGCD[i + 1]);
            }
            maxGCD = Math.max(maxGCD, currentGCD);
        }

        return maxGCD;
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(maxBalls(3, Arrays.asList(12, 3, 11))); // Expected: 3
        System.out.println(maxBalls(4, Arrays.asList(6, 9, 12, 15))); // Expected: 3
        System.out.println(maxBalls(2, Arrays.asList(5, 10))); // Expected: 5
        System.out.println(maxBalls(5, Arrays.asList(2, 4, 6, 8, 10))); // Expected: 2
    }
}