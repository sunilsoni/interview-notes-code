package com.interview.notes.code.year.y2024.may24.test10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//FINAL WORKING
class OfflineIntervals {
    private static final int ON = 1;
    private static final int OFF = 0;

    public static List<int[]> offlineIntervals(List<int[]> statuses) {
        List<int[]> offlinePeriods = new ArrayList<>();
        // This assumes that 'ON' comes first and 'OFF' follows; 'ON' should not be collected.
        for (int i = 0; i < statuses.size() - 1; i++) {
            if (statuses.get(i)[1] == ON && statuses.get(i + 1)[1] == OFF) {
                // Start of offline is immediately after an 'ON' period ends
                int startOffline = statuses.get(i)[0];
                // Find the next 'ON' to mark the end of the offline period
                if (i + 2 < statuses.size() && statuses.get(i + 2)[1] == ON) {
                    offlinePeriods.add(new int[]{startOffline, statuses.get(i + 2)[0]});
                }
            }
        }

        return offlinePeriods;
    }

    public static String listToString(List<int[]> arr) {
        return arr.stream()
                .map(a -> "{" + a[0] + ", " + a[1] + "}")
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
