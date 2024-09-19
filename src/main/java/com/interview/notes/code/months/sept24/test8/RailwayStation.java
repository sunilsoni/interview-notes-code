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
public class RailwayStation {

    public static void main(String[] args) {
        String[] arr1 = {"19:00", "9:40", "9:50", "11:00", "15:00", "18:00"};
        String[] dep1 = {"19:10", "12:00", "11:20", "11:30", "19:00", "20:00"};
        System.out.println("Test Case 1: " + (findMinimumPlatforms(arr1, dep1) == 3 ? "Pass" : "Fail"));

        String[] arr2 = {"19:00", "9:40"};
        String[] dep2 = {"9:10", "12:00"};
        System.out.println("Test Case 2: " + (findMinimumPlatforms(arr2, dep2) == 1 ? "Pass" : "Fail"));
    }

    public static int findMinimumPlatforms(String[] arr, String[] dep) {
        int n = arr.length;

        // Convert time to minutes since midnight
        int[] arrival = new int[n];
        int[] departure = new int[n];

        for (int i = 0; i < n; i++) {
            arrival[i] = convertToMinutes(arr[i]);
            departure[i] = convertToMinutes(dep[i]);
        }

        // Sort both arrays
        Arrays.sort(arrival);
        Arrays.sort(departure);

        int maxPlatforms = 0;
        int platformsNeeded = 0;
        int i = 0, j = 0;

        // Use two pointers to traverse the arrival and departure arrays
        while (i < n && j < n) {
            if (arrival[i] <= departure[j]) {
                platformsNeeded++;
                i++;
            } else {
                platformsNeeded--;
                j++;
            }
            maxPlatforms = Math.max(maxPlatforms, platformsNeeded);
        }

        return maxPlatforms;
    }

    // Utility function to convert time in "HH:MM" format to minutes since midnight
    private static int convertToMinutes(String time) {
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        return hours * 60 + minutes;
    }
}
