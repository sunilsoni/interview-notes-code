package com.interview.notes.code.year.y2024.may24.test10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution4 {
    private static final int ON = 1;
    private static final int OFF = 0;

    public static List<int[]> offlineIntervals(List<int[]> statuses) {
        List<int[]> offlinePeriods = new ArrayList<>();
        // Loop through the statuses to directly capture "OFF" periods.
        for (int i = 0; i < statuses.size(); i++) {
            if (statuses.get(i)[1] == OFF) {
                // Start of offline period
                int startOffline = statuses.get(i)[0];
                // We assume that there's always an ON following an OFF, so we get the next time value.
                int endOffline = (i + 1 < statuses.size()) ? statuses.get(i + 1)[0] : Integer.MAX_VALUE;
                offlinePeriods.add(new int[]{startOffline, endOffline});
            }
        }

        return offlinePeriods;
    }

    public static String listToString(List<int[]> arr) {
        return arr.stream()
                .map(a -> "[" + a[0] + ", " + a[1] + "]")
                .reduce((x, y) -> x + ", " + y)
                .orElse("No offline intervals");
    }

    public static List<int[]> toListFrom2dArray(int[][] a) {
        List<int[]> outList = new ArrayList<>();
        for (int[] item : a) {
            outList.add(Arrays.copyOf(item, item.length));
        }
        return outList;
    }

    public static void main(String[] args) {
        List<int[]> statuses = toListFrom2dArray(new int[][]{{5, ON}, {7, OFF}, {30, ON}, {31, OFF}, {36, ON}});
        System.out.println(listToString(offlineIntervals(statuses)));
    }
}
