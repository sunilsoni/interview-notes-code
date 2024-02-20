package com.interview.notes.code.months.jan24.test12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class EfficientTrek2 {
    public static int efficientTrek1(List<Integer> trails, int record) {
        // Sort the trails in descending order to ensure we are always choosing the longest trails first.
        Collections.sort(trails, Collections.reverseOrder());

        // If the record is more or equal to the size of trails, we only need to consider the longest trail.
        if (record >= trails.size()) {
            return trails.get(0);
        }

        int sum = 0;
        for (int i = 0; i < record; i++) {
            sum += trails.get(i);
        }

        return sum;
    }

    public static int efficientTrek(List<Integer> trails, int record) {
        // Handle invalid input
        if (trails == null || trails.isEmpty() || record <= 0) {
            return -1; // Or throw an IllegalArgumentException
        }

        int n = trails.size();
        if (record > n) {
            // Impossible to hike all trails in record days
            return -1;
        }

        // Sort trails in descending order for greedy approach
        trails.sort((a, b) -> b - a);

        // Calculate minimum possible sum based on record days
        int minSum = 0;
        for (int i = 0; i < record; i++) {
            minSum += trails.get(i);
        }

        // Initialize variables for tracking daily sums and maximums
        int currentSum = 0;
        int maxDaily = 0;
        int day = 0;

        // Greedily assign trails to days while ensuring minimum sum
        for (int i = 0; i < n; i++) {
            currentSum += trails.get(i);
            maxDaily = Math.max(maxDaily, trails.get(i));

            if (currentSum >= minSum || day == record - 1) {
                // Move to next day or adjust sum if minimum sum met
                if (day < record - 1) {
                    minSum += maxDaily;
                }
                currentSum = 0;
                maxDaily = 0;
                day++;
            }
        }

        return minSum;
    }


    public static void main(String[] args) {
        // Example case that needs to be fixed
        List<Integer> trails2 = new ArrayList<>(Arrays.asList(150, 200, 400, 350, 250));
        int record2 = 3;
        System.out.println(efficientTrek(trails2, record2)); // Should return 600 now
    }
}
