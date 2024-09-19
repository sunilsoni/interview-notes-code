package com.interview.notes.code.months.sept24.test8;

import java.util.Arrays;

/*
Given the arrival and departure times of all trains that reach a railway station, the task is to find the minimum number of platforms required for the railway station so that no train waits.
We are given two arrays that represent the arrival and departure times of trains that stop.
Input: arrl] = 19:00, 9:40, 9:50, 11:00, 15:00, 18:00}
dep[] = 19:10, 12:00, 11:20, 11:30, 19:00, 20:00}
Output: 3
Explanation: There are at-most three trains at a time (time between 11:00 to 11:20)
Input: arrl] = 19:00, 9:40}
dep[] = {9:10, 12:00}
Output: 1
Explanation: Only one platform is needed.
 */
public class MinimumPlatformsMain {

    /**
     * Parses a time string in "HH:MM" format to minutes since midnight.
     *
     * @param time the time string to parse
     * @return the time in minutes since midnight
     */
    private static int parseTime(String time) {
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        return hours * 60 + minutes;
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
        int n = arrivalTimes.length;
        int[] arr = new int[n];
        int[] dep = new int[n];

        // Convert time strings to minutes since midnight
        for (int i = 0; i < n; i++) {
            arr[i] = parseTime(arrivalTimes[i]);
            dep[i] = parseTime(departureTimes[i]);
        }

        // Sort arrival and departure times
        Arrays.sort(arr);
        Arrays.sort(dep);

        int platformsNeeded = 0, maxPlatforms = 0;
        int i = 0, j = 0;

        // Traverse arrival and departure arrays
        while (i < n && j < n) {
            if (arr[i] < dep[j]) {
                platformsNeeded++;
                i++;
                if (platformsNeeded > maxPlatforms)
                    maxPlatforms = platformsNeeded;
            } else {
                platformsNeeded--;
                j++;
            }
        }

        return maxPlatforms;
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
