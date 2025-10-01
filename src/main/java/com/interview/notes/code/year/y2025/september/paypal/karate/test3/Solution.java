package com.interview.notes.code.year.y2025.september.paypal.karate.test3;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {

    // -------------------------------
    // Helper method: convert "M:SS" into total seconds
    // -------------------------------
    private static int parseDuration(String time) {
        String[] parts = time.split(":");
        int minutes = Integer.parseInt(parts[0]);
        int seconds = Integer.parseInt(parts[1]);
        return minutes * 60 + seconds;
    }

    // -------------------------------
    // Core logic: find two songs that add up to 7 minutes (420 seconds)
    // -------------------------------
    public static List<String> findPair(String[][] songs) {
        Map<Integer, String> seen = new HashMap<>();

        for (String[] song : songs) {
            String title = song[0];
            int duration = parseDuration(song[1]);
            int complement = 420 - duration;

            if (seen.containsKey(complement)) {
                return Arrays.asList(seen.get(complement), title);
            }
            seen.put(duration, title);
        }
        return Collections.emptyList();
    }

    // -------------------------------
    // Test runner method (prints PASS/FAIL)
    // -------------------------------
    private static void runTest(String testName, String[][] songs, List<List<String>> expectedOptions) {
        List<String> result = findPair(songs);

        boolean pass;
        if (expectedOptions.isEmpty()) {
            pass = result.isEmpty();
        } else {
            pass = expectedOptions.stream()
                    .anyMatch(option -> new HashSet<>(option).equals(new HashSet<>(result)));

            if (!pass && result.size() == 2) {
                pass = (parseDurationFromList(result, songs) == 420);
            }
        }

        System.out.println(testName + ": " + (pass ? "PASS" : "FAIL") + " -> " + result);
    }

    // -------------------------------
    // Helper: compute total duration of result songs
    // -------------------------------
    private static int parseDurationFromList(List<String> result, String[][] songs) {
        Map<String, Integer> songMap = Arrays.stream(songs)
                .collect(Collectors.toMap(s -> s[0], s -> parseDuration(s[1])));
        return songMap.get(result.get(0)) + songMap.get(result.get(1));
    }

    // -------------------------------
    // Main method: define test cases and run them
    // -------------------------------
    public static void main(String[] args) {
        String[][] songTimes1 = {
                {"Stairway to Heaven", "8:05"}, {"Immigrant Song", "2:27"},
                {"Rock and Roll", "3:41"}, {"Communication Breakdown", "2:29"},
                {"Good Times Bad Times", "2:48"}, {"Hot Dog", "3:19"},
                {"The Crunge", "3:18"}, {"Achilles Last Stand", "10:26"},
                {"Black Dog", "4:55"}
        };

        String[][] songTimes2 = {
                {"Stairway to Heaven", "8:05"}, {"Immigrant Song", "2:27"},
                {"Rock and Roll", "3:41"}, {"Communication Breakdown", "2:29"},
                {"Good Times Bad Times", "2:48"}, {"Black Dog", "4:55"},
                {"The Crunge", "3:18"}, {"Achilles Last Stand", "10:26"},
                {"The Ocean", "4:31"}, {"Hot Dog", "3:19"}
        };

        String[][] songTimes3 = {
                {"Stairway to Heaven", "8:05"}, {"Immigrant Song", "2:27"},
                {"Rock and Roll", "3:41"}, {"Communication Breakdown", "2:29"},
                {"Hey Hey What Can I Do", "4:00"}, {"Poor Tom", "3:00"},
                {"Black Dog", "4:55"}
        };

        String[][] songTimes4 = {
                {"Hey Hey What Can I Do", "4:00"}, {"Rock and Roll", "3:41"},
                {"Communication Breakdown", "2:29"}, {"Going to California", "3:30"},
                {"On The Run", "3:50"}, {"The Wrestler", "3:50"},
                {"Black Mountain Side", "2:11"}, {"Brown Eagle", "2:20"}
        };

        String[][] songTimes5 = {
                {"Celebration Day", "3:30"}, {"Going to California", "3:30"}
        };

        String[][] songTimes6 = {
                {"Rock and Roll", "3:41"}, {"If I lived here", "3:59"},
                {"Day and night", "5:03"}, {"Tempo song", "1:57"}
        };

        // -------------------------------
        // Run all test cases
        // -------------------------------
        runTest("Test 1", songTimes1, List.of(
                Arrays.asList("Rock and Roll", "Hot Dog")
        ));
        runTest("Test 2", songTimes2, Arrays.asList(
                Arrays.asList("Rock and Roll", "Hot Dog"),
                Arrays.asList("Communication Breakdown", "The Ocean")
        ));
        runTest("Test 3", songTimes3, List.of(
                Arrays.asList("Hey Hey What Can I Do", "Poor Tom")
        ));
        runTest("Test 4", songTimes4, List.of()); // Expect no pair
        runTest("Test 5", songTimes5, List.of(
                Arrays.asList("Celebration Day", "Going to California")
        ));
        runTest("Test 6", songTimes6, List.of(
                Arrays.asList("Day and night", "Tempo song")
        ));

        // -------------------------------
        // Large Data Test
        // -------------------------------
        String[][] largeSongs = IntStream.range(0, 100000)
                .mapToObj(i -> new String[]{"Song" + i, (i % 6) + ":00"})
                .toArray(String[][]::new);
        // Add guaranteed pair
        String[][] extended = Arrays.copyOf(largeSongs, largeSongs.length + 2);
        extended[largeSongs.length] = new String[]{"Special1", "3:30"};
        extended[largeSongs.length + 1] = new String[]{"Special2", "3:30"};

        runTest("Large Data Test", extended, List.of(
                Arrays.asList("Special1", "Special2")
        ));
    }
}