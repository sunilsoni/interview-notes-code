package com.interview.notes.code.months.jan24.test12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class EfficientTrek4 {
    public static int efficientTrek(List<Integer> trails, int record) {
        // Sort the trails in descending order.
        Collections.sort(trails, Collections.reverseOrder());

        // We will accumulate the sum here.
        int sum = 0;

        // Calculate the maximum number of trails that the hiker can hike in the given record days
        // which is the number of days minus 1 for the start day, times the (record - 1) remaining days,
        // plus 1 for the last day.
        int maxTrails = (record - 1) + 1 + (record - 1) * (record - 1);

        // If the trails are less than or equal to max trails, the sum is just the longest trail.
        if (trails.size() <= maxTrails) {
            sum = trails.get(0);
        } else {
            // Otherwise, we distribute the hikes over the days to ensure the sum of the longest trails is minimum.
            int day = 1;
            for (int i = 0; i < trails.size(); i++) {
                // Only add the trail if it's the day for the longest trail
                if (day <= record) {
                    sum += trails.get(i);
                    day++;
                }
                // Once we've covered the record days, we skip the number of trails hiked
                // to start the count for the next longest trail.
                if (day > record) {
                    i += record - 1;
                    day = 1;
                }
            }
        }

        return sum;
    }

    public static void main(String[] args) {
        List<Integer> trails2 = new ArrayList<>(Arrays.asList(150, 200, 400, 350, 250));
        int record2 = 3;
        System.out.println(efficientTrek(trails2, record2)); // Should return 750
    }
}
