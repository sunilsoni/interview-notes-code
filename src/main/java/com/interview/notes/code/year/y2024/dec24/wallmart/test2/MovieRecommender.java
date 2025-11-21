package com.interview.notes.code.year.y2024.dec24.wallmart.test2;

import java.util.*;

/*

### Task Description:

One of the fun features of Aquaintly is that users can rate movies they have seen from 1 to 5. We want to use these ratings to make movie recommendations.

Ratings will be provided in the following format:
```
[Member Name, Movie Name, Rating]
```

We consider two users to have similar taste in movies if they have both rated the same movie as 4 or 5.

A movie should be recommended to a user if:
1. They haven't rated the movie.
2. A user with similar taste has rated the movie as 4 or 5.

### Example:
```java
ratings = [
    ["Alice", "Frozen", "5"],
    ["Bob", "Mad Max", "5"],
    ["Charlie", "Lost In Translation", "4"],
    ["Charlie", "Inception", "4"],
    ["Bob", "All About Eve", "3"],
    ["Bob", "Lost In Translation", "5"],
    ["Dennis", "All About Eve", "5"],
    ["Dennis", "Mad Max", "4"],
    ["Charlie", "Topsy-Turvy", "2"],
    ["Dennis", "Topsy-Turvy", "4"],
    ["Alice", "Lost In Translation", "1"],
    ["Franz", "Lost In Translation", "5"],
    ["Franz", "Mad Max", "5"]
]
```

If we want to recommend a movie to Charlie, we would recommend *Mad Max* because:
- Charlie has not rated *Mad Max*.
- Charlie and Bob have similar taste as both rated *Lost in Translation* 4 or 5.
- Bob rated *Mad Max* a 5.

### Function Signature:
Write a function that takes the name of a user and a collection of ratings and returns a collection of all movie recommendations that can be made for the given user.

```java
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

    // Functionality code here
}
```

### Test Cases:
```java
recommendations("Charlie", ratings) => ["Mad Max"]
recommendations("Bob", ratings) => ["Inception", "Topsy-Turvy"]
recommendations("Dennis", ratings) => ["Lost In Translation"]
recommendations("Alice", ratings) => []
recommendations("Franz", ratings) => ["Inception", "All About Eve", "Topsy-Turvy"]
```

### Complexity Variables:
- R = number of ratings
- M = number of movies
- U = number of users

---
 */
public class MovieRecommender {

    public static List<String> recommendations(String user, String[][] ratings) {
        // Map each user to the set of movies they've rated
        Map<String, Set<String>> userMovies = new HashMap<>();
        // Map each user to the set of movies they've rated highly (4 or 5)
        Map<String, Set<String>> userHighRatings = new HashMap<>();
        // Map each movie to the set of users who rated it highly
        Map<String, Set<String>> movieHighRatings = new HashMap<>();

        // Build data structures
        for (String[] ratingEntry : ratings) {
            String memberName = ratingEntry[0];
            String movieName = ratingEntry[1];
            int rating = Integer.parseInt(ratingEntry[2]);

            userMovies.computeIfAbsent(memberName, k -> new HashSet<>()).add(movieName);

            if (rating >= 4) {
                userHighRatings.computeIfAbsent(memberName, k -> new HashSet<>()).add(movieName);
                movieHighRatings.computeIfAbsent(movieName, k -> new HashSet<>()).add(memberName);
            }
        }

        Set<String> userRatedMovies = userMovies.getOrDefault(user, Collections.emptySet());
        Set<String> userHighRatedMovies = userHighRatings.getOrDefault(user, Collections.emptySet());

        // Find users with similar taste
        Set<String> similarUsers = new HashSet<>();
        for (String movie : userHighRatedMovies) {
            Set<String> usersWhoRatedMovieHighly = movieHighRatings.get(movie);
            for (String otherUser : usersWhoRatedMovieHighly) {
                if (!otherUser.equals(user)) {
                    similarUsers.add(otherUser);
                }
            }
        }

        // Generate recommendations
        Set<String> recommendedMovies = new HashSet<>();
        for (String similarUser : similarUsers) {
            Set<String> similarUserHighRatedMovies = userHighRatings.get(similarUser);
            for (String movie : similarUserHighRatedMovies) {
                if (!userRatedMovies.contains(movie)) {
                    recommendedMovies.add(movie);
                }
            }
        }

        // Sort recommendations
        List<String> recommendations = new ArrayList<>(recommendedMovies);
        Collections.sort(recommendations);

        return recommendations;
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

        // Test cases
        testRecommendations("Charlie", ratings, List.of("Mad Max"));
        testRecommendations("Bob", ratings, Arrays.asList("Inception", "Topsy-Turvy"));
        testRecommendations("Dennis", ratings, List.of("Lost In Translation"));
        testRecommendations("Alice", ratings, Collections.emptyList());
        testRecommendations("Franz", ratings, Arrays.asList("All About Eve", "Inception", "Topsy-Turvy"));

        // Additional test cases for edge scenarios and large data
        testRecommendations("Eve", ratings, Collections.emptyList()); // User who hasn't rated any movies
        testRecommendations("User1", new String[][]{}, Collections.emptyList()); // Empty ratings data
        generateAndTestLargeData(); // Large data input
    }

    private static void testRecommendations(String user, String[][] ratings, List<String> expected) {
        List<String> result = recommendations(user, ratings);
        if (new HashSet<>(result).equals(new HashSet<>(expected))) {
            System.out.println("Test case passed for user: " + user);
        } else {
            System.out.println("Test case FAILED for user: " + user);
            System.out.println("Expected: " + expected);
            System.out.println("Got: " + result);
        }
    }

    private static void generateAndTestLargeData() {
        // Generate a large number of ratings
        int numUsers = 1000;
        int numMovies = 1000;
        String[][] largeRatings = new String[numUsers * numMovies][3];
        int index = 0;
        for (int i = 0; i < numUsers; i++) {
            String user = "User" + i;
            for (int j = 0; j < numMovies; j++) {
                String movie = "Movie" + j;
                String rating = String.valueOf((i + j) % 5 + 1); // Ratings from 1 to 5
                largeRatings[index++] = new String[]{user, movie, rating};
            }
        }
        // Test recommendations for a specific user
        String testUser = "User500";
        List<String> result = recommendations(testUser, largeRatings);
        System.out.println("Large data test completed for user: " + testUser + ", recommendations count: " + result.size());
    }
}
