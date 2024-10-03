package com.interview.notes.code.months.oct24.test1;

import java.util.Arrays;
/*
Minimum Number of Airport Gates
You are managing an airport where multiple flights are scheduled to land and depart throughout the day. Each flight requires a dedicated gate at the airport for both arrival and departure.
Given the arrival and departure times of all the flights scheduled for a day, your task is to determine the minimum number of gates required so that no two flights occupy the same gate at the same time.
Input:
An integer n representing the number of flights.
Two arrays arrival and departure, each of size n, where: arrivalfil represents the arrival time of the i-th flight. departureli] represents the departure time of the i-th flight.
The arrival and departure times are given in 24-hour format, e.g., 930 for 9:30 AM, 1530 for 3:30 PM.
Output:
An integer representing the minimum number of gates required to handle all the flights without overlap.
Input:
 */
public class AirportGates {

    // Method to calculate the minimum number of gates required
    public static int minGatesRequired(int[] arrival, int[] departure, int n) {
        // Sort both the arrival and departure arrays
        Arrays.sort(arrival);
        Arrays.sort(departure);
        
        // Initialize pointers for arrival and departure arrays
        int gatesRequired = 0;
        int maxGates = 0;
        int i = 0, j = 0;
        
        // Traverse both arrays
        while (i < n && j < n) {
            // If a flight is arriving before the next departure, we need a new gate
            if (arrival[i] < departure[j]) {
                gatesRequired++;
                i++;
                
                // Update the maximum number of gates required
                maxGates = Math.max(maxGates, gatesRequired);
            } else {
                // If a flight departs, we can free up a gate
                gatesRequired--;
                j++;
            }
        }
        
        return maxGates;
    }

    // Main Method to run test cases
    public static void main(String[] args) {
        // Test Case 1
        int[] arrival1 = {900, 940, 950, 1100, 1500, 1800};
        int[] departure1 = {910, 1200, 1120, 1130, 1900, 2000};
        int n1 = 6;
        System.out.println("Test Case 1: " + (minGatesRequired(arrival1, departure1, n1) == 3 ? "PASS" : "FAIL"));

        // Test Case 2: All flights overlap
        int[] arrival2 = {1000, 1005, 1010};
        int[] departure2 = {1100, 1105, 1110};
        int n2 = 3;
        System.out.println("Test Case 2: " + (minGatesRequired(arrival2, departure2, n2) == 3 ? "PASS" : "FAIL"));

        // Test Case 3: No overlapping flights
        int[] arrival3 = {900, 1000, 1100};
        int[] departure3 = {930, 1030, 1130};
        int n3 = 3;
        System.out.println("Test Case 3: " + (minGatesRequired(arrival3, departure3, n3) == 1 ? "PASS" : "FAIL"));

        // Test Case 4: Single flight
        int[] arrival4 = {900};
        int[] departure4 = {1000};
        int n4 = 1;
        System.out.println("Test Case 4: " + (minGatesRequired(arrival4, departure4, n4) == 1 ? "PASS" : "FAIL"));

        // Test Case 5: Large overlapping flights
        int[] arrival5 = {900, 940, 950, 1000, 1010};
        int[] departure5 = {920, 1000, 1030, 1100, 1120};
        int n5 = 5;
        System.out.println("Test Case 5: " + (minGatesRequired(arrival5, departure5, n5) == 3 ? "PASS" : "FAIL"));
    }
}
