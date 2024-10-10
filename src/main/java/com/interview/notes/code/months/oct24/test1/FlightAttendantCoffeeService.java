package com.interview.notes.code.months.oct24.test1;

public class FlightAttendantCoffeeService {

    // Method to calculate the total time to serve coffee
    public static int calculateTotalTime(int n, int[] coffeeRows) {
        int totalTime = 0;
        int passengersServed = 0;
        int currentRow = 0;

        for (int i = 0; i < coffeeRows.length; i++) {
            int nextRow = coffeeRows[i];
            int distance = Math.abs(nextRow - currentRow); // Distance to next row
            totalTime += distance; // Time to move to next row
            totalTime += 3; // Time to serve coffee

            passengersServed++;
            currentRow = nextRow;

            // Check if we need to refill
            if (passengersServed % 7 == 0 && i != coffeeRows.length - 1) {
                // Return to the front and refill
                totalTime += currentRow; // Time to return to the front
                totalTime += 30; // Time to refill
                totalTime += coffeeRows[i + 1]; // Time to go to the next row
                currentRow = coffeeRows[i + 1]; // Update current position after refill
            }
        }

        // After serving all passengers, return to the front
        totalTime += currentRow; // Time to return to the front

        return totalTime;
    }

    // Test method to check if test cases pass
    public static void testCalculateTotalTime() {
        int n = 30;
        int[] testRows = {3, 4, 5, 7, 10, 13, 18, 20, 24, 25};
        int expectedTime = 139;
        int result = calculateTotalTime(n, testRows);

        System.out.println("Test Case 1: " + (result == expectedTime ? "PASS" : "FAIL") + " | Expected: " + expectedTime + " | Got: " + result);

        // Additional test cases
        int[] testRows2 = {1, 2, 3, 4, 5, 6, 7};
        expectedTime = 51;
        result = calculateTotalTime(n, testRows2);
        System.out.println("Test Case 2: " + (result == expectedTime ? "PASS" : "FAIL") + " | Expected: " + expectedTime + " | Got: " + result);

        int[] testRows3 = {1, 8, 15, 22, 29};
        expectedTime = 148;
        result = calculateTotalTime(n, testRows3);
        System.out.println("Test Case 3: " + (result == expectedTime ? "PASS" : "FAIL") + " | Expected: " + expectedTime + " | Got: " + result);
    }

    public static void main(String[] args) {
        testCalculateTotalTime();
    }
}
