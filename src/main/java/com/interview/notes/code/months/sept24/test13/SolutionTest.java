package com.interview.notes.code.months.sept24.test13;

import java.util.HashSet;
import java.util.Set;

public class SolutionTest {
    public static void main(String[] args) {
        // Example test cases
        int[] readings1 = {2, 4, 7, 8, 16, 32, 120};
        int k1 = 2;
        System.out.println(solution(readings1, k1)); // Should output 5

        int[] readings2 = {10201, 101, 1030301, 101, 101};
        int k2 = 101;
        System.out.println(solution(readings2, k2)); // Should output 5
    }

    public static int solution(int[] readings, int k) {
        Set<Long> powersOfK = new HashSet<>();
        long power = 1;

        // Compute powers of k and add them to the set until they exceed 10^9
        while (power <= 1_000_000_000L) {
            powersOfK.add(power);
            if (power > Long.MAX_VALUE / k) break; // Avoid overflow
            power *= k;
        }

        // Count how many readings are powers of k
        int count = 0;
        for (int reading : readings) {
            if (powersOfK.contains((long) reading)) {
                count++;
            }
        }

        return count;
    }
}
