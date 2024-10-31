package com.interview.notes.code.months.oct24.amazon.test8;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
public class AmazonLockerRecommendation {

    public static List<Locker> findTopKLockers(List<Locker> lockers, int customerX, int customerY, int k) {
        // Calculate distances
        for (Locker locker : lockers) {
            locker.distance = calculateDistance(locker.x, locker.y, customerX, customerY);
        }

        // Sort lockers based on distance
        lockers.sort(Comparator.comparingDouble(l -> l.distance));

        // Return top k lockers
        return lockers.subList(0, Math.min(k, lockers.size()));
    }

    private static double calculateDistance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    public static void main(String[] args) {
        // Test cases
        runTestCase(new int[][]{{1, 1}, {2, 2}, {3, 3}}, 0, 0, 2, "PASS");
        runTestCase(new int[][]{{1, 1}, {2, 2}, {3, 3}}, 1, 1, 1, "PASS");
        runTestCase(new int[][]{{1, 1}, {2, 2}, {3, 3}}, 4, 4, 3, "PASS");

        // Large data input test case
        int[][] largeInput = new int[1000000][2];
        for (int i = 0; i < 1000000; i++) {
            largeInput[i] = new int[]{i, i};
        }
        runTestCase(largeInput, 500000, 500000, 10, "PASS");
    }

    private static void runTestCase(int[][] lockerCoordinates, int customerX, int customerY, int k, String expectedResult) {
        List<Locker> lockers = new ArrayList<>();
        for (int[] coord : lockerCoordinates) {
            lockers.add(new Locker(coord[0], coord[1]));
        }

        long startTime = System.nanoTime();
        List<Locker> result = findTopKLockers(lockers, customerX, customerY, k);
        long endTime = System.nanoTime();

        boolean pass = result.size() == Math.min(k, lockerCoordinates.length);
        for (int i = 1; i < result.size(); i++) {
            if (result.get(i).distance < result.get(i - 1).distance) {
                pass = false;
                break;
            }
        }

        System.out.printf("Test case: %s, Time taken: %.3f ms\n",
                pass ? "PASS" : "FAIL", (endTime - startTime) / 1e6);
    }

    static class Locker {
        int x, y;
        double distance;

        Locker(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
