package com.interview.notes.code.year.y2024.may24.test10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution3 {
    private static final int ON = 1;
    private static final int OFF = 0;

    public static List<int[]> offlineIntervals(List<int[]> statuses) {
        List<int[]> offlinePeriods = new ArrayList<>();

        // Start from the first 'OFF' entry which is expected to be at index 1.
        for (int i = 1; i < statuses.size(); i += 2) {
            int startOffline = statuses.get(i)[0]; // Start of offline
            int endOffline = (i + 1 < statuses.size()) ? statuses.get(i + 1)[0] : Integer.MAX_VALUE; // End of offline, or until max if no next 'ON'
            offlinePeriods.add(new int[]{startOffline, endOffline});
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
            outList.add(Arrays.copyOf(item, item.length));
        }
        return outList;
    }

    public static void main(String[] args) {
        List<int[]> statuses = toListFrom2dArray(new int[][]{{5, ON}, {7, OFF}, {30, ON}, {31, OFF}, {36, ON}});
        System.out.println(listToString(offlineIntervals(statuses)));
    }
}
