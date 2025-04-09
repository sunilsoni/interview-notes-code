package com.interview.notes.code.year.y2025.march.amazon.test9;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class CinemaEntry {
    int start, end, volume;

    CinemaEntry(int start, int duration, int volume) {
        this.start = start;
        this.end = start + duration;
        this.volume = volume;
    }
}

public class Solution {

    public static int cinemaEntries(List<Integer> start, List<Integer> duration, List<Integer> volume) {
        int n = start.size();
        List<CinemaEntry> shows = IntStream.range(0, n)
                .mapToObj(i -> new CinemaEntry(start.get(i), duration.get(i), volume.get(i)))
                .sorted(Comparator.comparingInt(s -> s.start))  // Sort by start time
                .collect(Collectors.toList());

        TreeMap<Integer, Integer> dp = new TreeMap<>();
        dp.put(0, 0);

        for (CinemaEntry show : shows) {
            // Get the maximum volume achievable just before this show starts
            Map.Entry<Integer, Integer> prev = dp.floorEntry(show.start);
            int maxVolumeSoFar = prev != null ? prev.getValue() : 0;

            // Check for overlapping shows
            Map.Entry<Integer, Integer> overlap = dp.floorEntry(show.end);
            if (overlap != null && overlap.getKey() > show.start) {
                // If there's an overlap, we can't add this show's volume
                continue;
            }

            // Add this show's volume to the maximum volume achievable before it
            int newVolume = maxVolumeSoFar + show.volume;

            // Update the dp map with the new maximum volume at this end time
            dp.put(show.end, Math.max(newVolume, dp.getOrDefault(show.end, 0)));

            // Clean up any entries that have lower volume but later end time
            Iterator<Map.Entry<Integer, Integer>> iterator = dp.tailMap(show.end, false).entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Integer, Integer> entry = iterator.next();
                if (entry.getValue() <= newVolume) {
                    iterator.remove();
                } else {
                    break;
                }
            }
        }

        return dp.lastEntry().getValue();
    }

    // Simple testing using main method
    public static void main(String[] args) {
        test(Arrays.asList(1, 2, 4), Arrays.asList(2, 2, 1), Arrays.asList(1, 2, 3), 4);
        test(Arrays.asList(1, 10, 100), Arrays.asList(1, 10, 100), Arrays.asList(1, 10, 100), 111);
        test(Arrays.asList(10, 5, 15, 18, 30), Arrays.asList(30, 12, 20, 35, 35), Arrays.asList(50, 51, 20, 25, 10), 76);
        test(Arrays.asList(1, 2, 3), Arrays.asList(2, 2, 2), Arrays.asList(1, 2, 3), 4);
        test(Arrays.asList(1, 2, 3, 4, 5), Arrays.asList(1, 1, 1, 1, 1), Arrays.asList(9, 1, 6, 9, 9), 27);

        // Large data test case
        int largeN = 100000;
        List<Integer> startLarge = IntStream.range(0, largeN).boxed().collect(Collectors.toList());
        List<Integer> durationLarge = Collections.nCopies(largeN, 1);
        List<Integer> volumeLarge = Collections.nCopies(largeN, 1);
        System.out.println("Large case result: " + cinemaEntries(startLarge, durationLarge, volumeLarge));
    }

    private static void test(List<Integer> start, List<Integer> duration, List<Integer> volume, int expected) {
        int result = cinemaEntries(start, duration, volume);
        System.out.println(result == expected
                ? "Test PASS (Expected=" + expected + ", Actual=" + result + ")"
                : "Test FAIL (Expected=" + expected + ", Actual=" + result + ")");
    }
}
