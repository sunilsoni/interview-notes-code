package com.interview.notes.code.year.y2025.may.common.test4;

import java.util.*;

public class SongChaining {
    public static List<String> findLongestChain(List<String> songs, String startSong) {
        // Validate inputs
        if (songs == null || startSong == null || !songs.contains(startSong)) {
            return Collections.singletonList(startSong);
        }

        // Track used songs to prevent repetition
        Set<String> used = new HashSet<>();
        used.add(startSong);

        // Find the longest chain starting with startSong
        List<String> longestChain = new ArrayList<>();
        longestChain.add(startSong);

        findChain(songs, startSong, used, new ArrayList<>(Arrays.asList(startSong)), longestChain);

        return longestChain;
    }

    private static void findChain(List<String> songs, String currentSong,
                                  Set<String> used, List<String> currentChain,
                                  List<String> longestChain) {
        String lastWord = getLastWord(currentSong);

        for (String song : songs) {
            if (!used.contains(song) && getFirstWord(song).equals(lastWord)) {
                used.add(song);
                currentChain.add(song);

                if (currentChain.size() > longestChain.size()) {
                    longestChain.clear();
                    longestChain.addAll(currentChain);
                }

                findChain(songs, song, used, currentChain, longestChain);

                currentChain.remove(currentChain.size() - 1);
                used.remove(song);
            }
        }
    }

    private static String getFirstWord(String song) {
        return song.split("\\s+")[0];
    }

    private static String getLastWord(String song) {
        String[] words = song.split("\\s+");
        return words[words.length - 1];
    }

    // Test method
    public static void main(String[] args) {
        // Test case 1
        List<String> songs1 = Arrays.asList(
                "Every Breath You Take",
                "Take it All",
                "All My Love",
                "Love is Forever",
                "Forever Young",
                "Young American",
                "American Dreams",
                "Dreams"
        );

        String startSong = "Every Breath You Take";
        List<String> result = findLongestChain(songs1, startSong);
        System.out.println("Test 1 Result: " + result);

        // Add more test cases here
    }
}
