package com.interview.notes.code.year.y2025.september.paypal.karate.test2;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {

    // -------------------------------
    // Helper method: convert "M:SS" into total seconds
    // Example: "3:41" -> 3*60 + 41 = 221
    // -------------------------------
    private static int parseDuration(String time) {
        String[] parts = time.split(":");         // Split minutes and seconds
        int minutes = Integer.parseInt(parts[0]); // Convert minutes
        int seconds = Integer.parseInt(parts[1]); // Convert seconds
        return minutes * 60 + seconds;            // Return total seconds
    }

    // -------------------------------
    // Core logic: find two songs that add up to 7 minutes (420 seconds)
    // -------------------------------
    public static List<String> findPair(List<String[]> songs) {
        // Map to store duration -> song name for quick lookup
        Map<Integer, String> seen = new HashMap<>();

        for (String[] song : songs) {          // Loop through each song
            String title = song[0];            // Song title
            int duration = parseDuration(song[1]); // Song duration in seconds
            int complement = 420 - duration;   // Needed duration to make 7:00

            // If complement already exists, we found a pair
            if (seen.containsKey(complement)) {
                return Arrays.asList(seen.get(complement), title);
            }

            // Store current song in the map
            seen.put(duration, title);
        }
        // No valid pair found
        return Collections.emptyList();
    }

    // -------------------------------
    // Helper method: run one test case and print PASS/FAIL
    // songs -> input songs
    // expectedOptions -> list of valid answers (to allow multiple correct pairs)
    // -------------------------------
    private static void runTest(String testName, List<String[]> songs, List<List<String>> expectedOptions) {
        List<String> result = findPair(songs);

        boolean pass;
        if (expectedOptions.isEmpty()) {
            // Expecting no pair: result must also be empty
            pass = result.isEmpty();
        } else {
            // Case 1: result matches one of the expected options (order independent)
            pass = expectedOptions.stream()
                    .anyMatch(option -> new HashSet<>(option).equals(new HashSet<>(result)));

            // Case 2: result not in expectedOptions, but still a valid 7-min pair
            if (!pass && result.size() == 2) {
                pass = (parseDurationFromList(result, songs) == 420);
            }
        }

        System.out.println(testName + ": " + (pass ? "PASS" : "FAIL") + " -> " + result);
    }

    // -------------------------------
    // Helper: compute total duration of two result songs
    // -------------------------------
    private static int parseDurationFromList(List<String> result, List<String[]> songs) {
        // Build a map: song title -> duration
        Map<String, Integer> songMap = songs.stream()
                .collect(Collectors.toMap(s -> s[0], s -> parseDuration(s[1])));
        // Sum the durations of the two chosen songs
        return songMap.get(result.get(0)) + songMap.get(result.get(1));
    }

    // -------------------------------
    // Main method: test runner
    // -------------------------------
    public static void main(String[] args) {
        // -------------------------------
        // Define all test cases
        // Each song is represented as [title, duration]
        // -------------------------------
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

        // -------------------------------
        // Run all fixed test cases
        // -------------------------------
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
        runTest("Test 4", song_times_4, List.of()); // Expecting empty
        runTest("Test 5", song_times_5, List.of(
                Arrays.asList("Celebration Day", "Going to California")
        ));
        runTest("Test 6", song_times_6, List.of(
                Arrays.asList("Day and night", "Tempo song")
        ));

        // -------------------------------
        // Large Data Test
        // Generate 100000 dummy songs with durations cycling 0:00..5:00
        // Add two guaranteed special songs 3:30 + 3:30
        // -------------------------------
        List<String[]> largeSongs = IntStream.range(0, 100000)
                .mapToObj(i -> new String[]{"Song" + i, (i % 6) + ":00"})
                .collect(Collectors.toList());
        largeSongs.add(new String[]{"Special1", "3:30"});
        largeSongs.add(new String[]{"Special2", "3:30"});

        runTest("Large Data Test", largeSongs, List.of(
                Arrays.asList("Special1", "Special2")
        ));
    }
}