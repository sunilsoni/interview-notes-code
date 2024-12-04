package com.interview.notes.code.months.dec24.wallmart.test1;

import java.util.*;

public class MovieRecommender {

    /**
     * Recommends movies to the specified user based on similar users' high ratings.
     *
     * @param user    The name of the user to recommend movies to.
     * @param ratings A 2D array containing [Member Name, Movie Name, Rating].
     * @return A list of recommended movie names.
     */
    public static List<String> recommendations(String user, String[][] ratings) {
        // Map to store user to movie ratings
        Map<String, Map<String, Integer>> userRatingsMap = new HashMap<>();
        // Map to store movie to users who rated it 4 or 5
        Map<String, Set<String>> movieHighRatingsMap = new HashMap<>();

        // Populate the maps
        for (String[] ratingEntry : ratings) {
            String member = ratingEntry[0];
            String movie = ratingEntry[1];
            int rating = Integer.parseInt(ratingEntry[2]);

            // Update userRatingsMap
            userRatingsMap.putIfAbsent(member, new HashMap<>());
            userRatingsMap.get(member).put(movie, rating);

            // Update movieHighRatingsMap if rating is 4 or 5
            if (rating >= 4) {
                movieHighRatingsMap.putIfAbsent(movie, new HashSet<>());
                movieHighRatingsMap.get(movie).add(member);
            }
        }

        // Get the target user's rated movies
        Map<String, Integer> targetUserRatings = userRatingsMap.getOrDefault(user, new HashMap<>());
        Set<String> targetHighRatedMovies = new HashSet<>();
        for (Map.Entry<String, Integer> entry : targetUserRatings.entrySet()) {
            if (entry.getValue() >= 4) {
                targetHighRatedMovies.add(entry.getKey());
            }
        }

        // Find similar users
        Set<String> similarUsers = new HashSet<>();
        for (String movie : targetHighRatedMovies) {
            Set<String> users = movieHighRatingsMap.getOrDefault(movie, new HashSet<>());
            for (String similarUser : users) {
                if (!similarUser.equals(user)) {
                    similarUsers.add(similarUser);
                }
            }
        }

        // Gather potential recommendations
        Set<String> recommendedMovies = new HashSet<>();
        for (String similarUser : similarUsers) {
            Map<String, Integer> similarUserRatings = userRatingsMap.getOrDefault(similarUser, new HashMap<>());
            for (Map.Entry<String, Integer> entry : similarUserRatings.entrySet()) {
                String movie = entry.getKey();
                int rating = entry.getValue();
                if (rating >= 4 && !targetUserRatings.containsKey(movie)) {
                    recommendedMovies.add(movie);
                }
            }
        }

        // Convert to list and sort for consistency
        List<String> recommendations = new ArrayList<>(recommendedMovies);
        Collections.sort(recommendations);
        return recommendations;
    }

    /**
     * Runs all test cases and outputs PASS/FAIL for each.
     *
     * @param args Command-line arguments (not used).
     */
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

        // Define test cases
        Map<String, List<String>> testCases = new LinkedHashMap<>();
        testCases.put("Charlie", Arrays.asList("Mad Max"));
        testCases.put("Bob", Arrays.asList("Inception", "Topsy-Turvy"));
        testCases.put("Dennis", Arrays.asList("Lost In Translation"));
        testCases.put("Alice", Collections.emptyList());
        testCases.put("Franz", Arrays.asList("All About Eve", "Inception", "Topsy-Turvy"));

        // Run test cases
        int passed = 0;
        int failed = 0;
        for (Map.Entry<String, List<String>> testCase : testCases.entrySet()) {
            String user = testCase.getKey();
            List<String> expected = testCase.getValue();
            List<String> actual = recommendations(user, ratings);
            Collections.sort(actual);
            List<String> sortedExpected = new ArrayList<>(expected);
            Collections.sort(sortedExpected);
            if (actual.equals(sortedExpected)) {
                System.out.println("Test case for user '" + user + "': PASS");
                passed++;
            } else {
                System.out.println("Test case for user '" + user + "': FAIL");
                System.out.println("  Expected: " + sortedExpected);
                System.out.println("  Actual:   " + actual);
                failed++;
            }
        }

        // Additional edge test cases
        runAdditionalTests();

        // Summary
        System.out.println("\nTest Summary:");
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failed);
    }

    /**
     * Runs additional test cases to cover edge scenarios and large data inputs.
     */
    private static void runAdditionalTests() {
        System.out.println("\nRunning Additional Test Cases...");

        // Test case: User with no ratings
        String[][] ratings1 = {
                {"Alice", "Frozen", "5"},
                {"Bob", "Mad Max", "5"}
        };
        List<String> expected1 = Collections.emptyList();
        List<String> actual1 = recommendations("Eve", ratings1);
        if (actual1.equals(expected1)) {
            System.out.println("Additional Test - User with no ratings: PASS");
        } else {
            System.out.println("Additional Test - User with no ratings: FAIL");
            System.out.println("  Expected: " + expected1);
            System.out.println("  Actual:   " + actual1);
        }

        // Test case: User has rated all movies
        String[][] ratings2 = {
                {"Alice", "Frozen", "5"},
                {"Alice", "Mad Max", "4"},
                {"Alice", "Inception", "5"}
        };
        List<String> expected2 = Collections.emptyList();
        List<String> actual2 = recommendations("Alice", ratings2);
        if (actual2.equals(expected2)) {
            System.out.println("Additional Test - User has rated all movies: PASS");
        } else {
            System.out.println("Additional Test - User has rated all movies: FAIL");
            System.out.println("  Expected: " + expected2);
            System.out.println("  Actual:   " + actual2);
        }

        // Test case: Large dataset
        String[][] largeRatings = new String[10000][3];
        for (int i = 0; i < 10000; i++) {
            largeRatings[i][0] = "User" + (i % 100);
            largeRatings[i][1] = "Movie" + (i % 500);
            largeRatings[i][2] = String.valueOf((i % 5) + 1);
        }
        // Add specific ratings for testing
        largeRatings = Arrays.copyOf(largeRatings, 10010);
        largeRatings[10000] = new String[]{"TargetUser", "UniqueMovie", "3"};
        largeRatings[10001] = new String[]{"SimilarUser", "AnotherMovie", "5"};
        List<String> expected3 = Arrays.asList("AnotherMovie");
        List<String> actual3 = recommendations("TargetUser", largeRatings);
        if (actual3.equals(expected3)) {
            System.out.println("Additional Test - Large dataset: PASS");
        } else {
            System.out.println("Additional Test - Large dataset: FAIL");
            System.out.println("  Expected: " + expected3);
            System.out.println("  Actual:   " + actual3);
        }
    }
}
