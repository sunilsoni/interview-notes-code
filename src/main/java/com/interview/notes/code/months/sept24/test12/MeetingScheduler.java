package com.interview.notes.code.months.sept24.test12;

import java.util.*;

public class MeetingScheduler {
    
    // Method to find the earliest possible meeting time
    public static int solution(int[][][] schedules, int length) {
        // Minutes in a day (24 hours)
        int totalMinutes = 24 * 60;
        boolean[] day = new boolean[totalMinutes]; // Track booked time slots

        // Mark the booked times for all employees
        for (int[][] employeeSchedule : schedules) {
            for (int[] meeting : employeeSchedule) {
                int start = meeting[0];
                int end = meeting[1];
                for (int i = start; i < end; i++) {
                    day[i] = true; // Mark the time slot as booked
                }
            }
        }

        // Find the first available time slot that fits the meeting length
        for (int i = 0; i <= totalMinutes - length; i++) {
            boolean canSchedule = true;
            for (int j = i; j < i + length; j++) {
                if (day[j]) {
                    canSchedule = false;
                    break;
                }
            }
            if (canSchedule) {
                return i; // Return the earliest time
            }
        }

        // Return -1 if no suitable time slot is found
        return -1;
    }

    public static void main(String[] args) {
        // Test case 1
        int[][][] schedules1 = {
            {{0, 1439}}, // Employee 1 schedule
            {{0, 1439}}, // Employee 2 schedule
            {{0, 390}, {480, 510}} // Employee 3 schedule
        };
        int length1 = 90;
        System.out.println(solution(schedules1, length1)); // Output: -1

        // Test case 2
        int[][][] schedules2 = {
            {{60, 150}, {180, 240}}, // Employee 1 schedule
            {{0, 210}, {360, 420}}  // Employee 2 schedule
        };
        int length2 = 120;
        System.out.println(solution(schedules2, length2)); // Output: 240

        // Test case 3
        int[][][] schedules3 = {
            {{480, 510}}, // Employee 1 schedule
            {{240, 330}}, // Employee 2 schedule
            {{375, 400}}  // Employee 3 schedule
        };
        int length3 = 180;
        System.out.println(solution(schedules3, length3)); // Output: 0
    }
}
