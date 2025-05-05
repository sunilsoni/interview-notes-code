package com.interview.notes.code.year.y2025.may.common.test4;

import java.util.*;
import java.util.stream.IntStream;

public class SongPairFinder {

    /**
     * Finds any two distinct songs whose durations sum to exactly 420 seconds.
     *
     * @param songTimes a 2D String array where each element is [songTitle, "m:ss"]
     * @return a list of two song titles that sum to 7 minutes, or empty list if none.
     */
    public static List<String> findPair(String[][] songTimes) {
        Map<Integer, String> durations = new HashMap<>();

        for (String[] song : songTimes) {
            String title = song[0];
            String timeStr = song[1];
            int currentDuration = convertToSeconds(timeStr);
            int required = 420 - currentDuration;

            if (durations.containsKey(required)) {
                return Arrays.asList(durations.get(required), title);
            }
            durations.put(currentDuration, title);
        }
        return Collections.emptyList();
    }

    // Helper to convert "m:ss" into total seconds
    private static int convertToSeconds(String time) {
        String[] parts = time.split(":");
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }

    public static void main(String[] args) {
        // Define test cases as String[][] arrays
        String[][][] testCases = {
                {
                        {"Stairway to Heaven", "8:05"}, {"Immigrant Song", "2:27"},
                        {"Rock and Roll", "3:41"}, {"Communication Breakdown", "2:29"},
                        {"Good Times Bad Times", "2:48"}, {"Hot Dog", "3:19"},
                        {"The Crunge", "3:18"}, {"Achilles Last Stand", "10:26"},
                        {"Black Dog", "4:55"}
                },
                {
                        {"Celebration Day", "3:30"}, {"Going to California", "3:30"}
                },
                {
                        {"Rock and Roll", "3:41"}, {"If I lived here", "3:59"},
                        {"Day and night", "5:03"}, {"Tempo song", "1:57"}
                },
                {
                        {"Hey Hey What Can I Do", "4:00"}, {"Rock and Roll", "3:41"},
                        {"Communication Breakdown", "2:29"}, {"Going to California", "3:30"},
                        {"On The Run", "3:50"}, {"The Wrestler", "3:50"},
                        {"Black Mountain Side", "2:11"}, {"Brown Eagle", "2:20"}
                }
        };
        boolean[] expected = {true, true, true, false};

        for (int i = 0; i < testCases.length; i++) {
            List<String> result = findPair(testCases[i]);
            boolean ok = (expected[i] && !result.isEmpty()) || (!expected[i] && result.isEmpty());
            System.out.println("Test Case " + (i + 1) + (ok ? " PASSED: " : " FAILED: ") + result);
        }

        // Large input performance test
        String[][] large = IntStream.range(0, 10000)
                .mapToObj(j -> new String[]{"Song " + j, "3:30"})
                .toArray(String[][]::new);
        List<String> largeRes = findPair(large);
        System.out.println("Large Test " + (!largeRes.isEmpty() ? "PASSED: " : "FAILED: ") + largeRes);
    }
}
