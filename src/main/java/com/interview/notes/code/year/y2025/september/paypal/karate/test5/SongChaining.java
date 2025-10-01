package com.interview.notes.code.year.y2025.september.paypal.karate.test5;

import java.util.*;

public class SongChaining {

    // Helper: get first word of a song
    private static String firstWord(String song) {
        return song.split(" ")[0].toLowerCase();
    }

    // Helper: get last word of a song
    private static String lastWord(String song) {
        String[] parts = song.split(" ");
        return parts[parts.length - 1].toLowerCase();
    }

    // Core method: find longest chain
    public static List<String> chaining(List<String> songs, String start) {
        List<String> longest = new ArrayList<>();

        // Backtracking DFS
        dfs(start, songs, new ArrayList<>(), new HashSet<>(), longest);

        return longest;
    }

    // DFS recursive search
    private static void dfs(String current, List<String> songs,
                            List<String> path, Set<String> visited,
                            List<String> longest) {
        path.add(current);
        visited.add(current);

        // Update longest path found so far
        if (path.size() > longest.size()) {
            longest.clear();
            longest.addAll(path);
        }

        String last = lastWord(current);

        // Try all next songs
        for (String next : songs) {
            if (!visited.contains(next) && firstWord(next).equals(last)) {
                dfs(next, songs, path, visited, longest);
            }
        }

        // Backtrack
        path.remove(path.size() - 1);
        visited.remove(current);
    }

    // Helper method: run a test
    private static void runTest(String testName, List<String> songs, String start, List<String> expected) {
        List<String> result = chaining(songs, start);
        boolean pass = result.equals(expected) || result.size() == expected.size();
        System.out.println(testName + ": " + (pass ? "PASS" : "FAIL") + " -> " + result);
    }

    public static void main(String[] args) {
        // Test 1
        List<String> songs1 = Arrays.asList(
                "Down By the River", "River of Dreams", "Take me to the River", "Dreams",
                "Blues Hand Me Down", "Forever Young", "American Dreams", "All My Love",
                "Cantaloop", "Take it All", "Love is Forever", "Young American",
                "Dreamship", "Every Breath You Take"
        );
        String song1_1 = "Every Breath You Take";
        runTest("Test 1", songs1, song1_1, Arrays.asList(
                "Every Breath You Take", "Take it All", "All My Love", "Love is Forever",
                "Forever Young", "Young American", "American Dreams", "Dreams"
        ));

        // Test 2
        String song1_2 = "Dreams";
        runTest("Test 2", songs1, song1_2, List.of("Dreams"));

        // Test 3
        String song1_3 = "Blues Hand Me Down";
        runTest("Test 3", songs1, song1_3, Arrays.asList(
                "Blues Hand Me Down", "Down By the River", "River of Dreams", "Dreams"
        ));

        // Test 4
        String song1_4 = "Cantaloop";
        runTest("Test 4", songs1, song1_4, List.of("Cantaloop"));

        // Test 5
        List<String> songs2 = Arrays.asList(
                "Bye Bye Love", "Nothing at All", "Money for Nothing", "Love Me Do",
                "Do You Feel Like We Do", "Bye Bye Bye", "Do You Believe in Magic",
                "Bye Bye Baby", "Baby Ride Easy", "Easy Money", "All Right Now"
        );
        String song2_1 = "Bye Bye Bye";
        runTest("Test 5", songs2, song2_1, Arrays.asList(
                "Bye Bye Bye", "Bye Bye Baby", "Baby Ride Easy", "Easy Money",
                "Money for Nothing", "Nothing at All", "All Right Now"
        ));

        // Test 6
        String song2_2 = "Bye Bye Love";
        runTest("Test 6", songs2, song2_2, Arrays.asList(
                "Bye Bye Love", "Love Me Do", "Do You Feel Like We Do", "Do You Believe in Magic"
        ));

        // Test 7
        List<String> songs3 = Arrays.asList(
                "Love Me Do", "Do You Believe In Magic", "Magic You Do", "Magic Man", "Man In The Mirror"
        );
        String song3_1 = "Love Me Do";
        runTest("Test 7", songs3, song3_1, Arrays.asList(
                "Love Me Do", "Do You Believe In Magic", "Magic Man", "Man In The Mirror"
        ));
    }
}