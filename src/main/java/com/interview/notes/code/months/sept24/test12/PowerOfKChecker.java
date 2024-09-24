package com.interview.notes.code.months.sept24.test12;

import java.util.ArrayList;
import java.util.List;

public class PowerOfKChecker {

    // Method to check how many readings are powers of k
    public static int countPowersOfK(int[] readings, int k) {
        // Special case for k = 1, since all numbers are powers of 1
        if (k == 1) {
            int count = 0;
            for (int reading : readings) {
                if (reading == 1) {
                    count++;
                }
            }
            return count;
        }

        // Generate all powers of k that are <= 10^9
        List<Long> powersOfK = new ArrayList<>();
        long power = 1;
        while (power <= 1_000_000_000) {
            powersOfK.add(power);
            power *= k;
            if (power > 1_000_000_000) {
                break;
            }
        }

        // Count how many numbers in readings are in the powersOfK list
        int count = 0;
        for (int reading : readings) {
            if (powersOfK.contains((long) reading)) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        // Test case 1
        int[] readings1 = {2, 4, 7, 8, 16, 32, 120};
        int k1 = 2;
        System.out.println("Test Case 1: " + (countPowersOfK(readings1, k1) == 5));

        // Test case 2
        int[] readings2 = {10201, 101, 1030301, 101, 101};
        int k2 = 101;
        System.out.println("Test Case 2: " + (countPowersOfK(readings2, k2) == 5));

        // Additional test case (edge case for k = 1)
        int[] readings3 = {1, 1, 1, 2, 3, 4};
        int k3 = 1;
        System.out.println("Test Case 3: " + (countPowersOfK(readings3, k3) == 3));
    }
}
