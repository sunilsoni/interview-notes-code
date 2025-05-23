package com.interview.notes.code.year.y2025.may.common.test8;

import java.util.Arrays;
import java.util.stream.IntStream;

public class CarFleet {

    // Method to calculate the number of car fleets
    public static int carFleet(int target, int[] position, int[] speed) {
        // Pair positions and times (time to reach the target) together
        int n = position.length;
        double[][] cars = new double[n][2];

        for (int i = 0; i < n; i++) {
            cars[i][0] = position[i];
            // Calculate time taken to reach the target
            cars[i][1] = (double) (target - position[i]) / speed[i];
        }

        // Sort cars based on their starting position (descending)
        Arrays.sort(cars, (a, b) -> Double.compare(b[0], a[0]));

        int fleets = 0;
        double currentTime = 0;

        for (double[] car : cars) {
            // If this car takes longer than the current fleet's time, form a new fleet
            if (car[1] > currentTime) {
                fleets++;
                currentTime = car[1];
            }
        }

        return fleets;
    }

    // Simple test method
    public static void main(String[] args) {
        test(12, new int[]{10, 8, 0, 5, 3}, new int[]{2, 4, 1, 1, 3}, 3);
        test(10, new int[]{3}, new int[]{3}, 1);
        test(100, new int[]{0, 2, 4}, new int[]{4, 2, 1}, 1);

        // Edge case with large input
        int largeN = 100000;
        int[] largePos = IntStream.range(0, largeN).toArray();
        int[] largeSpeed = IntStream.generate(() -> 1).limit(largeN).toArray();
        test(largeN + 1, largePos, largeSpeed, 1);
    }

    // Helper method to perform and print test results
    private static void test(int target, int[] position, int[] speed, int expected) {
        int result = carFleet(target, position, speed);
        System.out.println("Test case: " + (result == expected ? "PASS" : "FAIL") + ". Expected: " + expected + ", Got: " + result);
    }
}