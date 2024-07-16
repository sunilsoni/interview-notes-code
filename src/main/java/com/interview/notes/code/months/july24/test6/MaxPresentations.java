package com.interview.notes.code.months.july24.test6;

import java.util.Arrays;

public class MaxPresentations {
    public static int maxPresentations(int n, int[] arrival, int[] duration) {
        int maxPresentations = 0;

        // Create an array of pairs to store arrival and end times
        int[][] times = new int[n][2];
        for (int i = 0; i < n; i++) {
            times[i][0] = arrival[i];
            times[i][1] = arrival[i] + duration[i];
        }

        // Sort the companies based on arrival times
        Arrays.sort(times, (a, b) -> a[0] - b[0]);

        int endTime = 0;
        for (int i = 0; i < n; i++) {
            if (times[i][0] >= endTime) {
                maxPresentations++;
                endTime = times[i][1];
            }
        }

        return maxPresentations;
    }

    public static void main(String[] args) {
        int n = 4;
        int[] arrival1 = {1, 2, 3, 4};
        int[] duration1 = {1, 1, 1, 1};
        System.out.println(maxPresentations(n, arrival1, duration1)); // Output: 4

        int n2 = 4;
        int[] arrival2 = {1, 2, 3, 4};
        int[] duration2 = {1, 1, 2, 1};
        System.out.println(maxPresentations(n2, arrival2, duration2)); // Output: 3


        int n3 = 6; //no of companies
        int[] arrival3 = {3, 5, 1, 12, 7, 8};//arrival time of company
        int[] duration3 = {2, 2, 2, 20, 1, 3};

        System.out.println(maxPresentations(n3, arrival3, duration3)); // Output: 6
    }
}
