package com.interview.notes.code.year.y2025.september.paypal.karate.test1;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {

    // Helper method: convert "M:SS" into total seconds
    private static int parseDuration(String time) {
        String[] parts = time.split(":");
        int minutes = Integer.parseInt(parts[0]);
        int seconds = Integer.parseInt(parts[1]);
        return minutes * 60 + seconds;
    }

    // Core logic: find two songs that add up to 7 minutes (420 seconds)
    public static List<String> findPair(List<String[]> songs) {
        // Map to store duration -> song name
        Map<Integer, String> seen = new HashMap<>();

        for (String[] song : songs) {
            String title = song[0];
            int duration = parseDuration(song[1]);
            int complement = 420 - duration; // target - current

            // If complement already exists, we found a pair
            if (seen.containsKey(complement)) {
                return Arrays.asList(seen.get(complement), title);
            }
            // Store current song
            seen.put(duration, title);
        }
        return Collections.emptyList();
    }

    // Helper method to run a test case and print PASS/FAIL
    private static void runTest(String testName, List<String[]> songs, List<List<String>> expectedOptions) {
        List<String> result = findPair(songs);

        // Check if result matches any expected option
        boolean pass = expectedOptions.stream()
                .anyMatch(option -> new HashSet<>(option).equals(new HashSet<>(result)));

        System.out.println(testName + ": " + (pass ? "PASS" : "FAIL") + " -> " + result);
    }

    public static void main(String[] args) {
        // Test data
        List<String[]> song_times_1 = Arrays.asList(
                new String[]{"Stairway to Heaven", "8:05"},
                new String[]{"Immigrant Song", "2:27"},
                new String[]{"Rock and Roll", "3:41"},
                new String[]{"Hot Dog", "3:19"}
        );

        List<String[]> song_times_2 = Arrays.asList(
                new String[]{"Rock and Roll", "3:41"},
                new String[]{"Hot Dog", "3:19"},
                new String[]{"Communication Breakdown", "2:29"},
                new String[]{"The Ocean", "4:31"}
        );

        List<String[]> song_times_3 = Arrays.asList(
                new String[]{"Hey Hey What Can I Do", "4:00"},
                new String[]{"Poor Tom", "3:00"}
        );

        List<String[]> song_times_4 = Arrays.asList(
                new String[]{"The Wrestler", "3:50"},
                new String[]{"Brown Eagle", "2:20"}
        );

        List<String[]> song_times_5 = Arrays.asList(
                new String[]{"Celebration Day", "3:30"},
                new String[]{"Going to California", "3:30"}
        );

        List<String[]> song_times_6 = Arrays.asList(
                new String[]{"Day and night", "5:03"},
                new String[]{"Tempo song", "1:57"}
        );

        // Run all tests
        runTest("Test 1", song_times_1, List.of(
                Arrays.asList("Rock and Roll", "Hot Dog")
        ));
        runTest("Test 2", song_times_2, Arrays.asList(
                Arrays.asList("Rock and Roll", "Hot Dog"),
                Arrays.asList("Communication Breakdown", "The Ocean")
        ));
        runTest("Test 3", song_times_3, List.of(
                Arrays.asList("Hey Hey What Can I Do", "Poor Tom")
        ));
        runTest("Test 4", song_times_4, List.of()); // No pair
        runTest("Test 5", song_times_5, List.of(
                Arrays.asList("Celebration Day", "Going to California")
        ));
        runTest("Test 6", song_times_6, List.of(
                Arrays.asList("Day and night", "Tempo song")
        ));

        // Large Data Test
        List<String[]> largeSongs = IntStream.range(0, 100000)
                .mapToObj(i -> new String[]{"Song" + i, (i % 6) + ":00"}) // durations like 0:00,1:00,...5:00
                .collect(Collectors.toList());
        largeSongs.add(new String[]{"Special1", "3:30"});
        largeSongs.add(new String[]{"Special2", "3:30"});

        runTest("Large Data Test", largeSongs, List.of(
                Arrays.asList("Special1", "Special2")
        ));
    }
}