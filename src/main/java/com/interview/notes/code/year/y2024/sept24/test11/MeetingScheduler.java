package com.interview.notes.code.year.y2024.sept24.test11;

import java.util.Arrays;
import java.util.List;

public class MeetingScheduler {
    public static void main(String[] args) {
        List<int[][]> testCases = Arrays.asList(
                new int[][]{{60, 150}, {180, 240}, {0, 210}, {360, 420}},
                new int[][]{{480, 510}, {240, 330}, {375, 400}}
        );
        int[] lengths = {120, 90};
        int[] expectedResults = {240, -1};

        for (int i = 0; i < testCases.size(); i++) {
            int result = findEarliestMeetingTime(testCases.get(i), lengths[i]);
            System.out.println("Test case " + (i + 1) + ": " + (result == expectedResults[i] ? "Pass" : "Fail"));
        }
    }

    public static int findEarliestMeetingTime(int[][] schedules, int length) {
        int maxTime = 1440;
        boolean[] occupied = new boolean[maxTime];

        for (int[] schedule : schedules) {
            for (int i = schedule[0]; i < schedule[1]; i++) {
                occupied[i] = true;
            }
        }

        for (int start = 0; start <= maxTime - length; start++) {
            boolean canSchedule = true;
            for (int j = start; j < start + length; j++) {
                if (occupied[j]) {
                    canSchedule = false;
                    break;
                }
            }
            if (canSchedule) {
                return start;
            }
        }

        return -1;
    }
}
