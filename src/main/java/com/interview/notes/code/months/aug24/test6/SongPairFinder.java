package com.interview.notes.code.months.aug24.test6;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SongPairFinder {
    public static List<String> findPair(String[][] songTimes) {
        Map<Integer, String> songMap = new HashMap<>();

        for (String[] song : songTimes) {
            String title = song[0];
            String time = song[1];
            int seconds = convertToSeconds(time);
            int complement = 420 - seconds;

            if (songMap.containsKey(complement)) {
                return Arrays.asList(songMap.get(complement), title);
            }

            songMap.put(seconds, title);
        }

        return Arrays.asList();
    }

    private static int convertToSeconds(String time) {
        String[] parts = time.split(":");
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }

    public static void main(String[] args) {
        String[][] songTimes = {
                {"Rock and Roll", "3:41"},
                {"If I lived here", "3:59"},
                {"Day and night", "5:03"},
                {"Tempo song", "1:57"}
        };

        List<String> result = findPair(songTimes);

        if (result.isEmpty()) {
            System.out.println("No pair found");
        } else {
            System.out.println("Pair found: " + result.get(0) + " and " + result.get(1));
        }
    }
}
