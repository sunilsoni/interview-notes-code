package com.interview.notes.code.months.aug24.test9;

import java.util.*;

/**
 * WOKRING
 * One of the fun features of Aquaintly is that users can rate movies they have seen from 1 to 5. We want to use these ratings to make movie recommendations.
 * Ratings will be provided in the following format:
 * [Member Name, Movie Name, Rating]
 * We consider two users to have similar taste in movies if they have both rated the same movie as 4 or 5.
 * A movie should be recommended to a user if:
 * - They haven't rated the movie
 * - A user with similar taste has rated the movie as 4 or 5
 * Example:
 */
public class MovieRecommender {
    public static List<String> recommendations(String user, List<String[]> ratings) {
        Map<String, Map<String, Integer>> userRatings = new HashMap<>();
        Set<String> userMovies = new HashSet<>();
        Set<String> similarUsers = new HashSet<>();

        // Process ratings
        for (String[] rating : ratings) {
            String rater = rating[0];
            String movie = rating[1];
            int score = Integer.parseInt(rating[2]);

            userRatings.computeIfAbsent(rater, k -> new HashMap<>()).put(movie, score);

            if (rater.equals(user)) {
                userMovies.add(movie);
            }
        }

        // Find users with similar taste
        for (String movie : userRatings.getOrDefault(user, new HashMap<>()).keySet()) {
            if (userRatings.get(user).get(movie) >= 4) {
                for (Map.Entry<String, Map<String, Integer>> entry : userRatings.entrySet()) {
                    if (!entry.getKey().equals(user) && entry.getValue().getOrDefault(movie, 0) >= 4) {
                        similarUsers.add(entry.getKey());
                    }
                }
            }
        }

        // Generate recommendations
        Set<String> recommendations = new HashSet<>();
        for (String similarUser : similarUsers) {
            for (Map.Entry<String, Integer> entry : userRatings.get(similarUser).entrySet()) {
                if (entry.getValue() >= 4 && !userMovies.contains(entry.getKey())) {
                    recommendations.add(entry.getKey());
                }
            }
        }

        // Sort and return recommendations
        List<String> sortedRecommendations = new ArrayList<>(recommendations);
        Collections.sort(sortedRecommendations);
        return sortedRecommendations;
    }

    public static void main(String[] args) {
        List<String[]> ratings = Arrays.asList(
                new String[]{"Alice", "Frozen", "5"},
                new String[]{"Bob", "Mad Max", "5"},
                new String[]{"Charlie", "Lost In Translation", "4"},
                new String[]{"Charlie", "Inception", "4"},
                new String[]{"Bob", "All About Eve", "3"},
                new String[]{"Bob", "Lost In Translation", "5"},
                new String[]{"Dennis", "All About Eve", "5"},
                new String[]{"Dennis", "Mad Max", "4"},
                new String[]{"Charlie", "Topsy-Turvy", "2"},
                new String[]{"Dennis", "Topsy-Turvy", "4"},
                new String[]{"Alice", "Lost In Translation", "1"},
                new String[]{"Franz", "Lost In Translation", "5"},
                new String[]{"Franz", "Mad Max", "5"}
        );

        String user = "Charlie";
        List<String> recommendedMovies = recommendations(user, ratings);
        System.out.println("Recommended movies for " + user + ": " + recommendedMovies);
    }
}
