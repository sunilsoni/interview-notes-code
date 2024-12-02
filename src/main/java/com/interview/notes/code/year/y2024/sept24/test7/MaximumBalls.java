package com.interview.notes.code.year.y2024.sept24.test7;

import java.util.Arrays;
import java.util.List;

public class MaximumBalls {
    public static void main(String[] args) {
        // Test cases
        System.out.println(maxBalls(3, Arrays.asList(12, 3, 11))); // Expected: 3
        System.out.println(maxBalls(4, Arrays.asList(6, 9, 12, 15))); // Expected: 3
        System.out.println(maxBalls(2, Arrays.asList(5, 10))); // Expected: 5
        System.out.println(maxBalls(5, Arrays.asList(2, 4, 6, 8, 10))); // Expected: 2
    }

    public static int maxBalls(int N, List<Integer> marks) {
        int totalGCD = marks.get(0);
        for (int i = 1; i < N; i++) {
            totalGCD = gcd(totalGCD, marks.get(i));
        }

        int maxGCD = totalGCD;
        for (int i = 0; i < N; i++) {
            int tempGCD = 0;
            for (int j = 0; j < N; j++) {
                if (i != j) {
                    tempGCD = (tempGCD == 0) ? marks.get(j) : gcd(tempGCD, marks.get(j));
                }
            }
            maxGCD = Math.max(maxGCD, tempGCD);
        }

        return maxGCD;
    }

    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}