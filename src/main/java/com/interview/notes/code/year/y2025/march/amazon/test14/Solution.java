package com.interview.notes.code.year.y2025.march.amazon.test14;

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
                .sorted(Comparator.comparingInt(s -> s.end))
                .collect(Collectors.toList());

        TreeMap<Integer, Integer> dp = new TreeMap<>();
        dp.put(0, 0);

        for (CinemaEntry show : shows) {
            int maxVolumeBefore = dp.floorEntry(show.start).getValue();
            int currVolume = maxVolumeBefore + show.volume;
            int lastMaxVolume = dp.lastEntry().getValue();
            if (currVolume > lastMaxVolume) {
                dp.put(show.end, currVolume);
            }
        }

        return dp.lastEntry().getValue();
    }

    // Simple main method for testing
    public static void main(String[] args) {
        test(Arrays.asList(1, 2, 4), Arrays.asList(2, 2, 1), Arrays.asList(1, 2, 3), 4);
        test(Arrays.asList(1, 10, 100), Arrays.asList(1, 10, 100), Arrays.asList(1, 10, 100), 111);
        test(Arrays.asList(10, 5, 15, 18, 30), Arrays.asList(30, 12, 20, 35, 35), Arrays.asList(50, 51, 20, 25, 10), 76);

        // Large input test (Edge case)
        int largeN = 100000;
        List<Integer> startLarge = IntStream.range(0, largeN).boxed().collect(Collectors.toList());
        List<Integer> durationLarge = Collections.nCopies(largeN, 1);
        List<Integer> volumeLarge = Collections.nCopies(largeN, 1);
        System.out.println("Large case result: " + cinemaEntries(startLarge, durationLarge, volumeLarge)); // Expected: 100000
    }

    private static void test(List<Integer> start, List<Integer> duration, List<Integer> volume, int expected) {
        int result = cinemaEntries(start, duration, volume);
        if (result == expected) {
            System.out.println("Test PASS (Expected=" + expected + ", Actual=" + result + ")");
        } else {
            System.out.println("Test FAIL (Expected=" + expected + ", Actual=" + result + ")");
        }
    }
}
