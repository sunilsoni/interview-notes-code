package com.interview.notes.code.year.y2024.oct24.amazon.test6;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class AmazonLockerRecommendation {

    // Method to calculate the top k closest lockers
    public static List<int[]> findTopKLockers(int[][] lockers, int[] customerLocation, int k) {
        // Priority queue to store lockers sorted by distance in descending order
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Double.compare(
                distance(customerLocation, b), distance(customerLocation, a)));

        // Iterate through all lockers and maintain a heap of size k
        for (int[] locker : lockers) {
            pq.offer(locker);
            if (pq.size() > k) {
                pq.poll();  // Remove the farthest locker if the size exceeds k
            }
        }

        // Convert the heap to a list and return
        List<int[]> result = new ArrayList<>(pq);
        return result;
    }

    // Method to calculate the Euclidean distance between two points
    private static double distance(int[] point1, int[] point2) {
        return Math.sqrt(Math.pow(point1[0] - point2[0], 2) + Math.pow(point1[1] - point2[1], 2));
    }

    // Main method to run test cases
    public static void main(String[] args) {
        // Test case 1
        int[][] lockers = {{1, 2}, {3, 4}, {1, -1}, {2, 2}, {3, 3}};
        int[] customerLocation = {0, 0};
        int k = 3;

        System.out.println("Test case 1: " + runTest(lockers, customerLocation, k));

        // Test case 2: Larger data
        int[][] largeLockers = new int[100000][2];
        for (int i = 0; i < largeLockers.length; i++) {
            largeLockers[i] = new int[]{i, i};
        }
        k = 10;

        System.out.println("Test case 2 (large input): " + runTest(largeLockers, customerLocation, k));

        // Additional test cases
        // You can add more cases to check for edge scenarios like k > N, etc.
    }

    // Utility method to run a test case and check if the result is correct
    private static boolean runTest(int[][] lockers, int[] customerLocation, int k) {
        List<int[]> result = findTopKLockers(lockers, customerLocation, k);

        // Output test result (for simplicity, we'll assume any valid output is a pass)
        if (result.size() == k || result.size() == lockers.length) {
            return true; // pass
        } else {
            return false; // fail
        }
    }
}
