package com.interview.notes.code.months.oct24.test2;

public class CoffeeService {
    public static int calculateServiceTime(int n, int[] i) {
        int time = 0;
        int position = 0;
        int coffeeLeft = 7;

        for (int row : i) {
            // Move to the passenger's row
            time += Math.abs(row - position);
            position = row;

            // Check if we need to refill
            if (coffeeLeft == 0) {
                time += position; // Return to front
                time += 30; // Refill time
                coffeeLeft = 7;
                position = 0;
                time += row; // Return to current passenger
            }

            // Serve coffee
            time += 3;
            coffeeLeft--;
        }

        // Return to the front of the plane
        time += position;

        return time;
    }

    public static void main(String[] args) {
        // Test cases
        int[][] testCases = {
                {30, 3, 4, 5, 7, 10, 13, 18, 20, 24, 25},
                {20, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10},
                {15, 2, 4, 6, 8, 10, 12, 14},
                {10, 1, 10},
                {5, 1, 2, 3, 4, 5}
        };

        for (int i = 0; i < testCases.length; i++) {
            int[] testCase = testCases[i];
            int n = testCase[0];
            int[] rows = new int[testCase.length - 1];
            System.arraycopy(testCase, 1, rows, 0, rows.length);

            int result = calculateServiceTime(n, rows);
            System.out.println("Test Case " + (i + 1) + ": " + result + " seconds");
        }
    }
}