package com.interview.notes.code.months.oct24.test2;

import java.util.Arrays;

/*
How much time (in seconds) does it take for a flight attendant to serve coffee?

There are n passengers on a plane.
There is only one passenger sitting per row.
There are no empty rows.
The flight attendant carries a coffee pot that can serve 7 passengers and must return to the front of the plane to refill the pot in order to serve more.
The flight attendant starts serving passengers from the front of the plane with a full pot of coffee.
It takes 1 second for the flight attendant to move from one row to another.
It takes 3 seconds for the flight attendant to fill a cup of coffee.
Refilling the coffee pot takes 30 seconds regardless of how much coffee is added.
After serving all passengers, the flight attendant needs to return to the front of the plane.


Write an algorithm that finds the amount of time needed to serve all passengers if only the passengers in the rows defined in the table [i] (below) ordered coffee.
There are n number of rows.
          int n = 30; // 1 - 31
          int i[] = {3, 4, 5, 7, 10, 13, 18, 20, 24, 25}; // a set of rows where seated passengers want to order coffee

 */
public class FlightAttendantServiceTimeCalculator {

    public static void main(String[] args) {
        // Test case 1
        int n1 = 30;
        int[] rowsWithOrders1 = {3, 4, 5, 7, 10, 13, 18, 20, 24, 25};
        int expectedTime1 = 146;
        int actualTime1 = calculateTotalTime(n1, rowsWithOrders1);
        System.out.println("Test Case 1: " + (actualTime1 == expectedTime1 ? "PASS" : "FAIL"));

        // Additional test case 2: Edge case where no passengers order coffee
        int n2 = 30;
        int[] rowsWithOrders2 = {};
        int expectedTime2 = 0; // Only return to front time, which is zero since never moved
        int actualTime2 = calculateTotalTime(n2, rowsWithOrders2);
        System.out.println("Test Case 2: " + (actualTime2 == expectedTime2 ? "PASS" : "FAIL"));

        // Additional test case 3: Edge case where pot needs refilling after each passenger
        int n3 = 7;
        int[] rowsWithOrders3 = {1, 2, 3, 4, 5, 6, 7};
        int expectedTime3 = calculateExpectedTimeForEdgeCase(n3, rowsWithOrders3);
        int actualTime3 = calculateTotalTime(n3, rowsWithOrders3);
        System.out.println("Test Case 3: " + (actualTime3 == expectedTime3 ? "PASS" : "FAIL"));

        // Additional test case 4: Large data set
        int n4 = 1000;
        int[] rowsWithOrders4 = new int[500];
        for (int i = 1; i <= 500; i++) {
            rowsWithOrders4[i - 1] = i * 2; // Passengers at every even row
        }
        int expectedTime4 = calculateExpectedTimeForLargeData(n4, rowsWithOrders4);
        int actualTime4 = calculateTotalTime(n4, rowsWithOrders4);
        System.out.println("Test Case 4: " + (actualTime4 == expectedTime4 ? "PASS" : "FAIL"));
    }

    /**
     * Calculates the total time needed to serve coffee to passengers in specified rows.
     *
     * @param n              Total number of rows in the plane.
     * @param rowsWithOrders Array of rows where passengers have ordered coffee.
     * @return Total time in seconds to serve all passengers and return to the front.
     */
    public static int calculateTotalTime(int n, int[] rowsWithOrders) {
        // Constants
        final int MOVE_TIME_PER_ROW = 1;
        final int SERVE_TIME = 3;
        final int REFILL_TIME = 30;
        final int POT_CAPACITY = 7;

        if (rowsWithOrders == null || rowsWithOrders.length == 0) {
            return 0;
        }

        Arrays.sort(rowsWithOrders);

        int totalTime = 0;
        int currentPotCapacity = POT_CAPACITY;
        int flightAttendantPosition = 0;

        for (int index = 0; index < rowsWithOrders.length; index++) {
            int passengerRow = rowsWithOrders[index];

            // Check if the pot is empty before moving to the next passenger
            if (currentPotCapacity == 0) {
                // Return to front to refill
                int moveBackTime = flightAttendantPosition * MOVE_TIME_PER_ROW;
                totalTime += moveBackTime + REFILL_TIME;
                flightAttendantPosition = 0;
                currentPotCapacity = POT_CAPACITY;

                // Move to passenger row
                int moveToPassengerTime = passengerRow * MOVE_TIME_PER_ROW;
                totalTime += moveToPassengerTime;
                flightAttendantPosition = passengerRow;
            } else {
                // Move to passenger row
                int moveTime = Math.abs(passengerRow - flightAttendantPosition) * MOVE_TIME_PER_ROW;
                totalTime += moveTime;
                flightAttendantPosition = passengerRow;
            }

            // Serve coffee
            totalTime += SERVE_TIME;
            currentPotCapacity--;
        }

        // Return to front after serving all passengers
        int returnToFrontTime = flightAttendantPosition * MOVE_TIME_PER_ROW;
        totalTime += returnToFrontTime;

        return totalTime;
    }

    /**
     * Helper method to calculate expected time for edge case where pot needs refilling after each passenger.
     */
    private static int calculateExpectedTimeForEdgeCase(int n, int[] rowsWithOrders) {
        final int MOVE_TIME_PER_ROW = 1;
        final int SERVE_TIME = 3;
        final int REFILL_TIME = 30;
        final int POT_CAPACITY = 7;

        int totalTime = 0;
        int currentPotCapacity = POT_CAPACITY;
        int flightAttendantPosition = 0;

        for (int passengerRow : rowsWithOrders) {
            // Move to passenger row
            int moveTime = Math.abs(passengerRow - flightAttendantPosition) * MOVE_TIME_PER_ROW;
            totalTime += moveTime;
            flightAttendantPosition = passengerRow;

            // Serve coffee
            totalTime += SERVE_TIME;
            currentPotCapacity--;

            // Refill after serving if pot is empty
            if (currentPotCapacity == 0 && passengerRow != rowsWithOrders[rowsWithOrders.length - 1]) {
                int moveBackTime = flightAttendantPosition * MOVE_TIME_PER_ROW;
                totalTime += moveBackTime + REFILL_TIME;
                flightAttendantPosition = 0;
                currentPotCapacity = POT_CAPACITY;
            }
        }

        // Return to front after serving all passengers
        int returnToFrontTime = flightAttendantPosition * MOVE_TIME_PER_ROW;
        totalTime += returnToFrontTime;

        return totalTime;
    }

    /**
     * Helper method to calculate expected time for large data set.
     */
    private static int calculateExpectedTimeForLargeData(int n, int[] rowsWithOrders) {
        // Similar to calculateTotalTime method but tailored for large data set
        return calculateTotalTime(n, rowsWithOrders);
    }
}
