package com.interview.notes.code.months.aug24.test7;

import java.util.*;

/*
We have a catalog of song titles (and their lengths) that we play at a local radio station. We have been asked to play two of those songs in a row, and they must add up to exactly seven minutes long.
Given a list of songs and their durations, write a function that returns the names of any two distinct songs that add up to exactly seven minutes. If there is no such pair, return an empty collection.

 */
public class SongPairFinder {
    public static List<String> findPair(List<Pair<String, String>> songTimes) {
        if (songTimes == null || songTimes.size() < 2) {
            return Collections.emptyList();
        }

        Map<Integer, String> songMap = new HashMap<>();
        
        for (Pair<String, String> song : songTimes) {
            int duration = convertToSeconds(song.getSecond());
            int complement = 420 - duration; // 7 minutes = 420 seconds
            
            if (songMap.containsKey(complement)) {
                return Arrays.asList(songMap.get(complement), song.getFirst());
            }
            
            songMap.put(duration, song.getFirst());
        }
        
        return Collections.emptyList();
    }
    
    private static int convertToSeconds(String time) {
        String[] parts = time.split(":");
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }
    
    public static void main(String[] args) {
        // Example 1
        List<Pair<String, String>> songTimes1 = Arrays.asList(
            new Pair<>("Stairway to Heaven", "8:05"),
            new Pair<>("Immigrant Song", "2:27"),
            new Pair<>("Rock and Roll", "3:41"),
            new Pair<>("Communication Breakdown", "2:29"),
            new Pair<>("Good Times Bad Times", "2:48"),
            new Pair<>("Hot Dog", "3:19"),
            new Pair<>("The Crunge", "3:18"),
            new Pair<>("Achilles Last Stand", "10:26"),
            new Pair<>("Black Dog", "4:55")
        );
        System.out.println("Example 1 result: " + findPair(songTimes1));
        
        // Example 2
        List<Pair<String, String>> songTimes2 = Arrays.asList(
            new Pair<>("Stairway to Heaven", "8:05"),
            new Pair<>("Immigrant Song", "2:27"),
            new Pair<>("Rock and Roll", "3:41"),
            new Pair<>("Communication Breakdown", "2:29"),
            new Pair<>("Good Times Bad Times", "2:48"),
            new Pair<>("Black Dog", "4:55"),
            new Pair<>("The Crunge", "3:18"),
            new Pair<>("Achilles Last Stand", "10:26"),
            new Pair<>("The Ocean", "4:31"),
            new Pair<>("Hot Dog", "3:19")
        );
        System.out.println("Example 2 result: " + findPair(songTimes2));
        
        // Example 3
        List<Pair<String, String>> songTimes3 = Arrays.asList(
            new Pair<>("Stairway to Heaven", "8:05"),
            new Pair<>("Immigrant Song", "2:27"),
            new Pair<>("Rock and Roll", "3:41"),
            new Pair<>("Communication Breakdown", "2:29"),
            new Pair<>("Hey Hey What Can I Do", "4:00"),
            new Pair<>("Poor Tom", "3:00"),
            new Pair<>("Black Dog", "4:55")
        );
        System.out.println("Example 3 result: " + findPair(songTimes3));

        // Example 4
        List<Pair<String, String>> songTimes4 = Arrays.asList(
            new Pair<>("Henny tao area do", "3:30"),
            new Pair<>("On The Run", "3:50"),
            new Pair<>("The Wrestler", "3:50"),
            new Pair<>("Black Mountain Side", "2:00"),
            new Pair<>("Black Dog", "4:55")
        );
        System.out.println("Example 4 result: " + findPair(songTimes4));

        // Example 5
        List<Pair<String, String>> songTimes5 = Arrays.asList(
            new Pair<>("Celebration Day", "3:30"),
            new Pair<>("Going to California", "3:30")
        );
        System.out.println("Example 5 result: " + findPair(songTimes5));

        // Example 6
        List<Pair<String, String>> songTimes6 = Arrays.asList(
            new Pair<>("Day and night", "3:03"),
            new Pair<>("Tempo song here", "3:59"),
            new Pair<>("Long song", "5:03"),
            new Pair<>("Tempo song", "1:57")
        );
        System.out.println("Example 6 result: " + findPair(songTimes6));
    }
}

class Pair<K, V> {
    private K first;
    private V second;
    
    public Pair(K first, V second) {
        this.first = first;
        this.second = second;
    }
    
    public K getFirst() { return first; }
    public V getSecond() { return second; }
}
