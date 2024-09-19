package com.interview.notes.code.months.sept24.test8;

import java.util.Arrays; // Importing the Arrays utility class for sorting.

public class MinimumPlatforms { // Declaring the class.

    /**
     * Parses a time string in "HH:MM" format to minutes since midnight.
     *
     * @param time the time string to parse
     * @return the time in minutes since midnight
     */
    private static int parseTime(String time) {
        String[] parts = time.split(":"); // Splits the time string into hours and minutes.
        int hours = Integer.parseInt(parts[0]); // Converts hours to integer.
        int minutes = Integer.parseInt(parts[1]); // Converts minutes to integer.
        return hours * 60 + minutes; // Calculates total minutes since midnight.
    }

    /**
     * Calculates the minimum number of platforms required at the station
     * so that no train waits.
     *
     * @param arrivalTimes   array of arrival times in "HH:MM" format
     * @param departureTimes array of departure times in "HH:MM" format
     * @return the minimum number of platforms required
     */
    public static int findMinimumPlatforms(String[] arrivalTimes, String[] departureTimes) {
        int n = arrivalTimes.length; // Number of trains.
        int[] arr = new int[n]; // Array to store arrival times in minutes.
        int[] dep = new int[n]; // Array to store departure times in minutes.

        // Convert time strings to minutes since midnight.
        for (int i = 0; i < n; i++) {
            arr[i] = parseTime(arrivalTimes[i]); // Parsing arrival times.
            dep[i] = parseTime(departureTimes[i]); // Parsing departure times.
        }

        // Sort arrival and departure times.
        Arrays.sort(arr); // Sorting arrival times.
        Arrays.sort(dep); // Sorting departure times.

        int platformsNeeded = 0; // Current number of platforms needed.
        int maxPlatforms = 0; // Maximum platforms needed at any time.
        int i = 0, j = 0; // Pointers for arrival and departure arrays.

        // Traverse arrival and departure arrays.
        while (i < n && j < n) {
            if (arr[i] < dep[j]) { // If next event is an arrival.
                platformsNeeded++; // Increase platform count.
                i++; // Move to next arrival.
                // Update maximum platforms if needed.
                if (platformsNeeded > maxPlatforms)
                    maxPlatforms = platformsNeeded;
            } else { // If next event is a departure.
                platformsNeeded--; // Decrease platform count.
                j++; // Move to next departure.
            }
        }
        return maxPlatforms; // Return the maximum platforms needed.
    }


    public static void main(String[] args) {
        // Test Case 1
        String[] arrivalTimes1 = {"9:00", "9:40", "9:50", "11:00", "15:00", "18:00"};
        String[] departureTimes1 = {"9:10", "12:00", "11:20", "11:30", "19:00", "20:00"};
        int expectedOutput1 = 3;
        int result1 = findMinimumPlatforms(arrivalTimes1, departureTimes1);
        System.out.println("Test Case 1: " + (result1 == expectedOutput1 ? "Passed" : "Failed"));

        // Test Case 2
        String[] arrivalTimes2 = {"9:00", "9:40"};
        String[] departureTimes2 = {"9:10", "12:00"};
        int expectedOutput2 = 1;
        int result2 = findMinimumPlatforms(arrivalTimes2, departureTimes2);
        System.out.println("Test Case 2: " + (result2 == expectedOutput2 ? "Passed" : "Failed"));

        // Additional Test Case (Edge Scenario)
        String[] arrivalTimes3 = {"23:50", "23:55", "00:05"};
        String[] departureTimes3 = {"23:55", "00:10", "00:15"};
        int expectedOutput3 = 2;
        int result3 = findMinimumPlatforms(arrivalTimes3, departureTimes3);
        System.out.println("Test Case 3 (Edge Case): " + (result3 == expectedOutput3 ? "Passed" : "Failed"));
    }
}
