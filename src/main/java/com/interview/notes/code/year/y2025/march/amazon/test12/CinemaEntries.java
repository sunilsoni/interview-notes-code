package com.interview.notes.code.year.y2025.march.amazon.test12;

import java.util.*;
import java.util.stream.*;

public class CinemaEntries {

    // Show class representing a show interval with start, end, and volume.
    static class Show {
        int start;
        int end;
        int volume;

        Show(int s, int d, int v) {
            this.start = s;
            this.end = s + d;
            this.volume = v;
        }
    }

    // Returns the maximum total volume of non-overlapping shows.
    public static int cinemaEntries(List<Integer> start, List<Integer> duration, List<Integer> volume) {
        int n = start.size();
        List<Show> shows = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            shows.add(new Show(start.get(i), duration.get(i), volume.get(i)));
        }
        // Sort shows by end time.
        shows.sort(Comparator.comparingInt(show -> show.end));

        int[] dp = new int[n];
        dp[0] = shows.get(0).volume;

        for (int i = 1; i < n; i++) {
            int incl = shows.get(i).volume;
            int l = binarySearch(shows, i);
            if (l != -1) {
                incl += dp[l];
            }
            dp[i] = Math.max(dp[i - 1], incl);
        }
        return dp[n - 1];
    }

    // Binary search to find the rightmost non-conflicting show index for shows.get(i).
    private static int binarySearch(List<Show> shows, int i) {
        int lo = 0, hi = i - 1;
        int ans = -1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (shows.get(mid).end <= shows.get(i).start) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return ans;
    }

    // Main method for testing all test cases.
    public static void main(String[] args) {
        boolean allPassed = true;

        // Test Case 0:
        List<Integer> start0 = Arrays.asList(1, 2, 4);
        List<Integer> duration0 = Arrays.asList(2, 2, 1);
        List<Integer> volume0 = Arrays.asList(1, 2, 3);
        int expected0 = 4;
        int result0 = cinemaEntries(start0, duration0, volume0);
        System.out.println("Test Case 0: " + (result0 == expected0 ? "PASS" : "FAIL"));
        if (result0 != expected0) allPassed = false;

        // Test Case 1:
        List<Integer> start1 = Arrays.asList(1, 10, 100);
        List<Integer> duration1 = Arrays.asList(1, 10, 100);
        List<Integer> volume1 = Arrays.asList(1, 10, 100);
        int expected1 = 111;
        int result1 = cinemaEntries(start1, duration1, volume1);
        System.out.println("Test Case 1: " + (result1 == expected1 ? "PASS" : "FAIL"));
        if (result1 != expected1) allPassed = false;

        // Screenshot Example Test:
        List<Integer> start2 = Arrays.asList(10, 5, 15, 18, 30);
        List<Integer> duration2 = Arrays.asList(30, 12, 20, 35, 35);
        List<Integer> volume2 = Arrays.asList(50, 51, 20, 25, 10);
        int expected2 = 76;
        int result2 = cinemaEntries(start2, duration2, volume2);
        System.out.println("Screenshot Example Test: " + (result2 == expected2 ? "PASS" : "FAIL"));
        if (result2 != expected2) allPassed = false;

        // Edge Case: Single Show
        List<Integer> start3 = Arrays.asList(5);
        List<Integer> duration3 = Arrays.asList(10);
        List<Integer> volume3 = Arrays.asList(100);
        int expected3 = 100;
        int result3 = cinemaEntries(start3, duration3, volume3);
        System.out.println("Single Show Test: " + (result3 == expected3 ? "PASS" : "FAIL"));
        if (result3 != expected3) allPassed = false;

        // Large Data Test:
        int n = 100000;
        List<Integer> startLarge = new ArrayList<>(n);
        List<Integer> durationLarge = new ArrayList<>(n);
        List<Integer> volumeLarge = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            startLarge.add(i * 10); // ensures non-overlapping intervals
            durationLarge.add(5);
            volumeLarge.add(1);
        }
        int expectedLarge = n; // each show contributes 1 volume
        int resultLarge = cinemaEntries(startLarge, durationLarge, volumeLarge);
        System.out.println("Large Data Test: " + (resultLarge == expectedLarge ? "PASS" : "FAIL"));
        if (resultLarge != expectedLarge) allPassed = false;

        System.out.println("Overall: " + (allPassed ? "PASS" : "FAIL"));
    }
}
