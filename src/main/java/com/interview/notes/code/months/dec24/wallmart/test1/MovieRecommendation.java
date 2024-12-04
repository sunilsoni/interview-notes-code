package com.interview.notes.code.months.dec24.wallmart.test1;

import java.util.*;

public class MovieRecommendation {

    public static List<String> recommendations(String userName, String[][] ratings) {
        // Map to track which users have rated which movies and how
        Map<String, Map<String, Integer>> userRatings = new HashMap<>();
        // A reverse map of movies to which users rated them highly
        Map<String, Set<String>> movieHighRaters = new HashMap<>();

        // Populate maps with the ratings data
        for (String[] rating : ratings) {
            String user = rating[0];
            String movie = rating[1];
            int score = Integer.parseInt(rating[2]);

            userRatings.computeIfAbsent(user, k -> new HashMap<>()).put(movie, score);

            if (score >= 4) { // Only consider high scores
                movieHighRaters.computeIfAbsent(movie, k -> new HashSet<>()).add(user);
            }
        }

        // The set of recommendations for the user
        Set<String> recommendations = new HashSet<>();

        // The user movies rated map
        Map<String, Integer> userRatedMovies = userRatings.getOrDefault(userName, new HashMap<>());

        // Find all similar users
        Set<String> similarUsers = new HashSet<>();
        for (Map.Entry<String, Integer> entry : userRatedMovies.entrySet()) {
            if (entry.getValue() >= 4) {
                Set<String> highRaters = movieHighRaters.getOrDefault(entry.getKey(), new HashSet<>());
                similarUsers.addAll(highRaters);
            }
        }

        // Recommend based on similar users
        for (String similarUser : similarUsers) {
            if (!similarUser.equals(userName)) {
                Map<String, Integer> similarUserRatings = userRatings.get(similarUser);
                for (Map.Entry<String, Integer> entry : similarUserRatings.entrySet()) {
                    String movie = entry.getKey();
                    int score = entry.getValue();
                    // Recommend movie if it's rated 4 or 5 by a similar user and not yet rated by the target user
                    if (score >= 4 && !userRatedMovies.containsKey(movie)) {
                        recommendations.add(movie);
                    }
                }
            }
        }

        return new ArrayList<>(recommendations);
    }

    public static void main(String[] args) {
        String[][] ratings = {
                {"Alice", "Frozen", "5"},
                {"Bob", "Mad Max", "5"},
                {"Charlie", "Lost In Translation", "4"},
                {"Charlie", "Inception", "4"},
                {"Bob", "All About Eve", "3"},
                {"Bob", "Lost In Translation", "5"},
                {"Dennis", "All About Eve", "5"},
                {"Dennis", "Mad Max", "4"},
                {"Charlie", "Topsy-Turvy", "2"},
                {"Dennis", "Topsy-Turvy", "4"},
                {"Alice", "Lost In Translation", "1"},
                {"Franz", "Lost In Translation", "5"},
                {"Franz", "Mad Max", "5"}
        };

        // Testing
        testRecommendations("Charlie", ratings, Arrays.asList("Mad Max"));
        testRecommendations("Bob", ratings, Arrays.asList("Inception", "Topsy-Turvy"));
        testRecommendations("Dennis", ratings, Arrays.asList("Lost In Translation"));
        testRecommendations("Alice", ratings, Collections.emptyList());
        testRecommendations("Franz", ratings, Arrays.asList("Inception", "All About Eve", "Topsy-Turvy"));
    }

    // Helper method for testing
    private static void testRecommendations(String userName, String[][] ratings, List<String> expected) {
        List<String> result = recommendations(userName, ratings);
        Collections.sort(result);
        Collections.sort(expected);
        boolean passed = result.equals(expected);
        System.out.println("Test for " + userName + ": " + (passed ? "PASS" : "FAIL"));
    }
}