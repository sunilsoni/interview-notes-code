package com.interview.notes.code.year.y2025.may.common.test3;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Pair<K, V> {
    K first;
    V second;

    Pair(K first, V second) {
        this.first = first;
        this.second = second;
    }
}

public class SongPairFinder {

    public static List<String> findPair(List<Pair<String, String>> songTimes) {
        Map<Integer, String> durations = new HashMap<>();

        for (Pair<String, String> song : songTimes) {
            int currentDuration = convertToSeconds(song.second);
            int requiredDuration = 420 - currentDuration;

            if (durations.containsKey(requiredDuration)) {
                return Arrays.asList(durations.get(requiredDuration), song.first);
            }

            durations.put(currentDuration, song.first);
        }

        return Collections.emptyList();
    }

    private static int convertToSeconds(String time) {
        String[] parts = time.split(":");
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }

    public static void main(String[] args) {
        List<List<Pair<String, String>>> testCases = Arrays.asList(
                Arrays.asList(
                        new Pair<>("Stairway to Heaven", "8:05"), new Pair<>("Immigrant Song", "2:27"),
                        new Pair<>("Rock and Roll", "3:41"), new Pair<>("Communication Breakdown", "2:29"),
                        new Pair<>("Good Times Bad Times", "2:48"), new Pair<>("Hot Dog", "3:19"),
                        new Pair<>("The Crunge", "3:18"), new Pair<>("Achilles Last Stand", "10:26"),
                        new Pair<>("Black Dog", "4:55")
                ),
                Arrays.asList(
                        new Pair<>("Celebration Day", "3:30"), new Pair<>("Going to California", "3:30")
                ),
                Arrays.asList(
                        new Pair<>("Rock and Roll", "3:41"), new Pair<>("If I lived here", "3:59"),
                        new Pair<>("Day and night", "5:03"), new Pair<>("Tempo song", "1:57")
                ),
                Arrays.asList(
                        new Pair<>("Hey Hey What Can I Do", "4:00"), new Pair<>("Rock and Roll", "3:41"),
                        new Pair<>("Communication Breakdown", "2:29"), new Pair<>("Going to California", "3:30"),
                        new Pair<>("On The Run", "3:50"), new Pair<>("The Wrestler", "3:50"),
                        new Pair<>("Black Mountain Side", "2:11"), new Pair<>("Brown Eagle", "2:20")
                )
        );

        List<Boolean> expectedResults = Arrays.asList(true, true, true, false);

        for (int i = 0; i < testCases.size(); i++) {
            List<String> result = findPair(testCases.get(i));

            boolean testPassed = (expectedResults.get(i) && !result.isEmpty()) || (!expectedResults.get(i) && result.isEmpty());

            System.out.println("Test Case " + (i + 1) + (testPassed ? " PASSED: " : " FAILED: ") + result);
        }

        // Edge Test - Large input case
        List<Pair<String, String>> largeTestCase = IntStream.range(0, 10000)
                .mapToObj(j -> new Pair<>("Song " + j, "3:30"))
                .collect(Collectors.toList());

        List<String> largeTestResult = findPair(largeTestCase);
        System.out.println("Large Test Case " + (!largeTestResult.isEmpty() ? "PASSED: " : "FAILED: ") + largeTestResult);
    }
}
