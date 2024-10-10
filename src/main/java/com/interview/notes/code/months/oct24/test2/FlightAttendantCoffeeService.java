package com.interview.notes.code.months.oct24.test2;

public class FlightAttendantCoffeeService {

    // Method to calculate the total time to serve coffee
    public static int calculateTotalTime(int n, int[] coffeeRows) {
        int totalTime = 0;
        int passengersServed = 0;
        int currentRow = 0;

        for (int i = 0; i < coffeeRows.length; i++) {
            int nextRow = coffeeRows[i];
            int distance = Math.abs(nextRow - currentRow); // Time to move to the next row
            totalTime += distance; // Add the time to move to the next row
            totalTime += 3; // Add the time to serve coffee

            passengersServed++;
            currentRow = nextRow;

            // Check if we need to refill the coffee pot
            if (passengersServed % 7 == 0 && i != coffeeRows.length - 1) {
                // Return to the front and refill the pot
                totalTime += currentRow; // Time to return to the front
                totalTime += 30; // Time to refill the coffee pot
                totalTime += coffeeRows[i + 1]; // Time to move to the next row after refill
                currentRow = coffeeRows[i + 1]; // Update current position to the next row
                i++; // Skip to the next row since we already moved there
            }
        }

        // After serving all passengers, return to the front
        totalTime += currentRow; // Time to return to the front

        return totalTime;
    }

    // Test method to verify the correctness of the solution
    public static void testCalculateTotalTime() {
        int n = 30;

        // Test Case 1
        int[] testRows1 = {3, 4, 5, 7, 10, 13, 18, 20, 24, 25};
        int expectedTime1 = 139;
        int result1 = calculateTotalTime(n, testRows1);
        System.out.println("Test Case 1: " + (result1 == expectedTime1 ? "PASS" : "FAIL") + " | Expected: " + expectedTime1 + " | Got: " + result1);

        // Test Case 2
        int[] testRows2 = {1, 2, 3, 4, 5, 6, 7};
        int expectedTime2 = 51;
        int result2 = calculateTotalTime(n, testRows2);
        System.out.println("Test Case 2: " + (result2 == expectedTime2 ? "PASS" : "FAIL") + " | Expected: " + expectedTime2 + " | Got: " + result2);

        // Test Case 3
        int[] testRows3 = {1, 8, 15, 22, 29};
        int expectedTime3 = 148;
        int result3 = calculateTotalTime(n, testRows3);
        System.out.println("Test Case 3: " + (result3 == expectedTime3 ? "PASS" : "FAIL") + " | Expected: " + expectedTime3 + " | Got: " + result3);

        // Additional edge case tests
        // Test Case 4: No passengers ordered coffee
        int[] testRows4 = {};
        int expectedTime4 = 0;
        int result4 = calculateTotalTime(n, testRows4);
        System.out.println("Test Case 4: " + (result4 == expectedTime4 ? "PASS" : "FAIL") + " | Expected: " + expectedTime4 + " | Got: " + result4);

        // Test Case 5: Only one passenger ordered coffee
        int[] testRows5 = {15};
        int expectedTime5 = 33; // 15 seconds to reach row 15, 3 seconds to serve, 15 seconds to return
        int result5 = calculateTotalTime(n, testRows5);
        System.out.println("Test Case 5: " + (result5 == expectedTime5 ? "PASS" : "FAIL") + " | Expected: " + expectedTime5 + " | Got: " + result5);
    }

    public static void main(String[] args) {
        testCalculateTotalTime();
    }
}
