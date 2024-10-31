package com.interview.notes.code.months.oct24.amazon.test7;

import java.util.Arrays;

/*
Amazon Lockers provide customers with the option of picking up packages at a secure location convenient to them. We want to build a service that will recommend the top k lockers based on a customer’s current location.

To start, assume you are given a customer location (x, y) and a list of locker locations [(x1, y1), (x2, y2), ..., (xn, yn)]. Walk me through an approach to solve this problem and discuss the strengths and weaknesses of the options you are considering. This also sends messages to aim for important notifications.

Input:

Number of lockers: N
Locker locations: [(x1, y1), (x2, y2), (x3, y3), ..., (xn, yn)]
Customer location: [x, y]
Output:

Top k lockers
 */
public class OptimizedDistanceCalculator {

    private static final int MAX_COORD = 1000; // Adjust based on your coordinate range
    private static final int[] SQUARE_LOOKUP = new int[MAX_COORD + 1];

    static {
        for (int i = 0; i <= MAX_COORD; i++) {
            SQUARE_LOOKUP[i] = i * i;
        }
    }

    /**
     * Calculates the squared Euclidean distance between two points.
     * This is faster and sufficient for comparisons.
     */
    public static int squaredDistance(int x1, int y1, int x2, int y2) {
        int dx = x2 - x1;
        int dy = y2 - y1;
        return dx * dx + dy * dy;
    }

    /**
     * Calculates the squared Euclidean distance using bit manipulation.
     * This can be faster for integer coordinates.
     */
    public static int squaredDistanceBit(int x1, int y1, int x2, int y2) {
        int dx = x2 - x1;
        int dy = y2 - y1;
        return (dx * dx) + (dy * dy);
    }

    /**
     * Calculates the squared Euclidean distance using a lookup table.
     * This is very fast for small coordinate ranges.
     */
    public static int squaredDistanceLookup(int x1, int y1, int x2, int y2) {
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        if (dx <= MAX_COORD && dy <= MAX_COORD) {
            return SQUARE_LOOKUP[dx] + SQUARE_LOOKUP[dy];
        }
        return dx * dx + dy * dy;
    }

    /**
     * Calculates the actual Euclidean distance.
     * Uses the lookup table for small coordinates and falls back to Math.sqrt for larger ones.
     */
    public static double distance(int x1, int y1, int x2, int y2) {
        int squaredDist = squaredDistanceLookup(x1, y1, x2, y2);
        return (squaredDist <= MAX_COORD * MAX_COORD) ?
                Math.sqrt(SQUARE_LOOKUP[squaredDist]) : Math.sqrt(squaredDist);
    }

    /**
     * Bulk calculation of squared distances using SIMD-like approach.
     * This is much faster for large numbers of calculations.
     */
    public static void bulkSquaredDistance(int[] x1, int[] y1, int[] x2, int[] y2, int[] result) {
        for (int i = 0; i < x1.length; i += 4) {
            int dx0 = x2[i] - x1[i];
            int dy0 = y2[i] - y1[i];
            int dx1 = x2[i + 1] - x1[i + 1];
            int dy1 = y2[i + 1] - y1[i + 1];
            int dx2 = x2[i + 2] - x1[i + 2];
            int dy2 = y2[i + 2] - y1[i + 2];
            int dx3 = x2[i + 3] - x1[i + 3];
            int dy3 = y2[i + 3] - y1[i + 3];

            result[i] = dx0 * dx0 + dy0 * dy0;
            result[i + 1] = dx1 * dx1 + dy1 * dy1;
            result[i + 2] = dx2 * dx2 + dy2 * dy2;
            result[i + 3] = dx3 * dx3 + dy3 * dy3;
        }
    }

    public static void main(String[] args) {
        int x1 = 0, y1 = 0, x2 = 3, y2 = 4;
        System.out.println("Squared Distance: " + squaredDistance(x1, y1, x2, y2));
        System.out.println("Squared Distance (Bit): " + squaredDistanceBit(x1, y1, x2, y2));
        System.out.println("Squared Distance (Lookup): " + squaredDistanceLookup(x1, y1, x2, y2));
        System.out.println("Actual Distance: " + distance(x1, y1, x2, y2));

        // Bulk calculation example
        int[] X1 = {0, 1, 2, 3};
        int[] Y1 = {0, 1, 2, 3};
        int[] X2 = {3, 4, 5, 6};
        int[] Y2 = {4, 5, 6, 7};
        int[] result = new int[4];
        bulkSquaredDistance(X1, Y1, X2, Y2, result);
        System.out.println("Bulk Squared Distances: " + Arrays.toString(result));
    }
}
