package com.interview.notes.code.months.aug24.test6;

import java.util.*;

public class SongChaining {
    public static List<String> chaining(String[] songs, String startSong) {
        Map<String, List<String>> songMap = new HashMap<>();
        
        // Build the song map
        for (String song : songs) {
            String firstWord = getFirstWord(song).toLowerCase();
            songMap.putIfAbsent(firstWord, new ArrayList<>());
            songMap.get(firstWord).add(song);
        }
        
        // Find the longest chain
        List<String> longestChain = new ArrayList<>();
        dfs(songMap, startSong, new ArrayList<>(), longestChain, new HashSet<>());
        
        return longestChain;
    }
    
    private static void dfs(Map<String, List<String>> songMap, String currentSong, 
                            List<String> currentChain, List<String> longestChain, 
                            Set<String> visited) {
        currentChain.add(currentSong);
        visited.add(currentSong);
        
        if (currentChain.size() > longestChain.size()) {
            longestChain.clear();
            longestChain.addAll(currentChain);
        }
        
        String lastWord = getLastWord(currentSong).toLowerCase();
        List<String> nextSongs = songMap.getOrDefault(lastWord, Collections.emptyList());
        
        for (String nextSong : nextSongs) {
            if (!visited.contains(nextSong)) {
                dfs(songMap, nextSong, currentChain, longestChain, visited);
            }
        }
        
        currentChain.remove(currentChain.size() - 1);
        visited.remove(currentSong);
    }
    
    private static String getFirstWord(String song) {
        return song.split("\\s+")[0];
    }
    
    private static String getLastWord(String song) {
        String[] words = song.split("\\s+");
        return words[words.length - 1];
    }
    
    public static void main(String[] args) {
        String[] songs1 = {
            "Down By the River",
            "River of Dreams",
            "Take me to the River",
            "Dreams",
            "Blues Hand Me Down",
            "Forever Young",
            "American Dreams",
            "All My Love",
            "Cantaloop",
            "Take it All",
            "Love is Forever",
            "Young American",
            "Dreamship",
            "Every Breath You Take"
        };
        String song1_1 = "Every Breath You Take";
        String song1_2 = "Dreams";
        String song1_3 = "Blues Hand Me Down";
        String song1_4 = "Cantaloop";

        String[] songs2 = {
            "Bye Bye Love",
            "Nothing at All",
            "Money for Nothing",
            "Love Me Do",
            "Do You Feel Like We Do",
            "Bye Bye Bye",
            "Do You Believe in Magic",
            "Bye Bye Baby",
            "Baby Ride Easy",
            "Easy Money",
            "All Right Now"
        };
        String song2_1 = "Bye Bye Bye";
        String song2_2 = "Bye Bye Love";

        String[] songs3 = {
            "Love Me Do",
            "Do You Believe In Magic",
            "Magic You Do",
            "Magic Man",
            "Man In The Mirror"
        };
        String song3_1 = "Love Me Do";

        System.out.println("Test Case 1:");
        System.out.println(chaining(songs1, song1_1));
        System.out.println(chaining(songs1, song1_2));
        System.out.println(chaining(songs1, song1_3));
        System.out.println(chaining(songs1, song1_4));

        System.out.println("\nTest Case 2:");
        System.out.println(chaining(songs2, song2_1));
        System.out.println(chaining(songs2, song2_2));

        System.out.println("\nTest Case 3:");
        System.out.println(chaining(songs3, song3_1));
    }
}
