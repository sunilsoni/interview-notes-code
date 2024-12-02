package com.interview.notes.code.year.y2024.jan24.test12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class EfficientTrek1 {

    public static int efficientTrek(List<Integer> trails, int record) {
        // Sort the trails in descending order to ensure we are always choosing the longest trails first.
        Collections.sort(trails, Collections.reverseOrder());

        int sum = 0;
        int days = 1;
        for (int i = 0; i < trails.size(); i++) {
            // Add the longest trail for each day, then skip 'record - 1' trails to simulate moving to the next day.
            if (days <= record) {
                sum += trails.get(i);
                i += record - 1;
                days++;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        // Example cases
        List<Integer> trails1 = new ArrayList<>(Arrays.asList(10, 5, 9, 3, 8, 15));
        int record1 = 2;
        System.out.println(efficientTrek(trails1, record1)); // Should return 25

        List<Integer> trails2 = new ArrayList<>(Arrays.asList(150, 200, 400, 350, 250));
        int record2 = 3;
        System.out.println(efficientTrek(trails2, record2)); // Should return 750

        List<Integer> trails3 = new ArrayList<>(Arrays.asList(78, 45, 12, 56, 85, 45));
        int record3 = 1;
        System.out.println(efficientTrek(trails3, record3)); // Should return 85
    }
}
