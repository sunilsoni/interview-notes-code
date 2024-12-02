package com.interview.notes.code.year.y2024.may24.test10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution2 {
    private static final int ON = 1;
    private static final int OFF = 0;

    public static void main(String[] args) {
        List<int[]> statuses = toListFrom2dArray(new int[][]{{5, ON}, {7, OFF}, {30, ON}, {31, OFF}, {36, ON}});
        System.out.println(listToString(offlineIntervals(statuses)));
    }

    public static List<int[]> offlineIntervals(List<int[]> statuses) {
        List<int[]> offlinePeriods = new ArrayList<>();
        boolean isOnline = true;
        int lastChange = 0;

        for (int[] entry : statuses) {
            int time = entry[0];
            int status = entry[1];

            if (status == ON && !isOnline) {
                offlinePeriods.add(new int[]{lastChange, time});
                isOnline = true;
            } else if (status == OFF && isOnline) {
                lastChange = time;
                isOnline = false;
            }
        }

        // Ensure that if the list ends in an OFF state, it gets recorded
        if (!isOnline) {
            offlinePeriods.add(new int[]{lastChange, Integer.MAX_VALUE});
        }

        return offlinePeriods;
    }

    public static String listToString(List<int[]> arr) {
        return arr.stream()
                .map(Arrays::toString)
                .reduce((x, y) -> x + ", " + y)
                .orElse("No offline intervals");
    }

    public static List<int[]> toListFrom2dArray(int[][] a) {
        List<int[]> outList = new ArrayList<>();
        for (int[] item : a) {
            outList.add(Arrays.copyOf(item, item.length)); // Use copyOf for safe copying
        }
        return outList;
    }
}
