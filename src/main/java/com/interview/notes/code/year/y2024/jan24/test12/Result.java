package com.interview.notes.code.year.y2024.jan24.test12;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

class Result {

    public static int efficientTrek1(List<Integer> trails, int record) {
        // Create a mutable copy of the trails list
        List<Integer> mutableTrails = new ArrayList<>(trails);

        // Sort the mutable list in descending order so that we can pick the longest trails first
        Collections.sort(mutableTrails, Collections.reverseOrder());

        // Initialize the sum to 0
        int sum = 0;

        // Loop over the trails for the given number of record days
        for (int i = 0; i < record; i++) {
            // Add the longest trail left to the sum
            sum += mutableTrails.get(i);
        }

        // Return the total sum of the longest trails
        return sum;
    }


    public static int efficientTrek2(List<Integer> trails, int record) {
        // If there are as many days as trails, the hiker will hike one trail per day
        if (record >= trails.size()) {
            return trails.stream().mapToInt(Integer::intValue).sum();
        }

        // Create a max heap to keep track of the maximum trail lengths for each day
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        // Add all trails to the max heap
        maxHeap.addAll(trails);

        // Sum the `record` number of largest elements from the max heap
        int sum = 0;
        for (int i = 0; i < record; i++) {
            sum += maxHeap.poll();
        }

        return sum;
    }

    public static int efficientTrek(List<Integer> trails, int record) {
        // If there are as many days as trails, the hiker will hike one trail per day
        if (record >= trails.size()) {
            return trails.stream().mapToInt(Integer::intValue).sum();
        }

        // Sort the trails in descending order to get the longest trails first
        Collections.sort(trails, Collections.reverseOrder());

        // Calculate the number of days we need to take multiple trails
        int daysWithMultipleTrails = trails.size() - record;

        // Sum the lengths of the longest trails
        int sum = 0;
        for (int i = 0; i < record - daysWithMultipleTrails; i++) {
            sum += trails.get(i);
        }

        // For the remaining days, add the next largest trail since we have to hike multiple trails
        for (int i = 0; i < daysWithMultipleTrails; i++) {
            sum += trails.get(record - 1 + i);
        }

        return sum;
    }


    public static void main(String[] args) {
        // Test the examples provided in the screenshots
        System.out.println(efficientTrek(new ArrayList<>(List.of(10, 5, 9, 3, 8, 15)), 2)); // Should return 25
        System.out.println(efficientTrek(new ArrayList<>(List.of(150, 200, 400, 350, 250)), 3)); // Should return 750
        System.out.println(efficientTrek(new ArrayList<>(List.of(78, 45, 12, 56, 85, 45)), 1)); // Should return 85
    }
}
