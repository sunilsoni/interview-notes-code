package com.interview.notes.code.year.y2025.march.common.test10;

import java.util.*;
import java.util.stream.IntStream;

public class CarFleet {

    public static int carFleet(int target, int[] position, int[] speed) {
        int n = position.length;
        double[][] cars = IntStream.range(0, n)
            .mapToObj(i -> new double[]{position[i], (target - position[i]) / (double) speed[i]})
            .sorted((a, b) -> Double.compare(b[0], a[0])) // sort by position descending
            .toArray(double[][]::new);

        int fleets = 0;
        double currentMaxTime = 0;

        for (double[] car : cars) {
            double arrivalTime = car[1];
            if (arrivalTime > currentMaxTime) { // forms a new fleet
                fleets++;
                currentMaxTime = arrivalTime;
            }
        }

        return fleets;
    }

    // Simple test method
    public static void main(String[] args) {
        test(12, new int[]{10, 8, 0, 5, 3}, new int[]{2, 4, 1, 1, 3}, 3);
        test(10, new int[]{3}, new int[]{3}, 1);
        test(100, new int[]{0, 2, 4}, new int[]{4, 2, 1}, 1);

        // Edge cases
        test(10, new int[]{0, 1}, new int[]{1, 2}, 2);
        test(10, new int[]{9, 7, 4}, new int[]{1, 2, 3}, 3);

        // Large input case
        int largeSize = 100000;
        int[] positionsLarge = IntStream.range(0, largeSize).toArray();
        int[] speedsLarge = IntStream.generate(() -> 1).limit(largeSize).toArray();
        test(1000000, positionsLarge, speedsLarge, 100000);
    }

    private static void test(int target, int[] position, int[] speed, int expected) {
        int result = carFleet(target, position, speed);
        if (result == expected) {
            System.out.println("Test passed. Result: " + result);
        } else {
            System.out.println("Test failed. Expected: " + expected + ", but got: " + result);
        }
    }
}