package com.interview.notes.code.year.y2024.dec24.ibm.test2;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class FoodOutletFinder {
    private static final String BASE_URL = "https://jsonmock.hackerrank.com/api/food_outlets";

    public static String finestFoodOutlet(String city, int votes) {
        String bestOutlet = null;
        double highestRating = -1;
        int maxVotes = -1;

        try {
            String encodedCity = URLEncoder.encode(city, StandardCharsets.UTF_8.toString());
            int currentPage = 1;
            int totalPages = 1;

            do {
                String apiUrl = String.format("%s?city=%s&page=%d", BASE_URL, encodedCity, currentPage);
                JSONObject response = makeApiCall(apiUrl);

                if (currentPage == 1) {
                    totalPages = response.getInt("total_pages");
                }

                JSONArray outlets = response.getJSONArray("data");
                for (int i = 0; i < outlets.length(); i++) {
                    JSONObject outlet = outlets.getJSONObject(i);
                    JSONObject userRating = outlet.getJSONObject("user_rating");
                    int voteCount = userRating.getInt("votes");
                    double rating = userRating.getDouble("average_rating");

                    if (voteCount >= votes) {
                        if (rating > highestRating ||
                                (rating == highestRating && voteCount > maxVotes)) {
                            highestRating = rating;
                            maxVotes = voteCount;
                            bestOutlet = outlet.getString("name");
                        }
                    }
                }
                currentPage++;
            } while (currentPage <= totalPages);

        } catch (Exception e) {
            System.err.println("Error processing request: " + e.getMessage());
            return null;
        }

        return bestOutlet;
    }

    private static JSONObject makeApiCall(String apiUrl) throws Exception {
        URL url = new URL(apiUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            return new JSONObject(response.toString());
        } finally {
            conn.disconnect();
        }
    }

    public static void main(String[] args) {
        // Test cases
        runTestCase(1, "Seattle", 500, "Cafe Juanita");
        runTestCase(2, "Miami", 1000, "Pirates of Grill");

        // Edge cases
        runTestCase(3, "NonExistentCity", 100, null);
        runTestCase(4, "Seattle", 100000, null); // Very high vote threshold

        // Special characters in city name
        runTestCase(5, "New York", 500, "Expected Restaurant"); // Replace with actual expected value

        // Case sensitivity test
        runTestCase(6, "SEATTLE", 500, "Cafe Juanita");

        // Minimum votes edge case
        runTestCase(7, "Seattle", 0, "Expected Restaurant"); // Replace with actual expected value

        // Performance test with multiple pages
        long startTime = System.currentTimeMillis();
        String result = finestFoodOutlet("Los Angeles", 100);
        long endTime = System.currentTimeMillis();
        System.out.printf("Performance Test: Completed in %d ms%n", endTime - startTime);
    }

    private static void runTestCase(int testNumber, String city, int votes, String expected) {
        System.out.printf("Test Case %d: ", testNumber);
        try {
            String result = finestFoodOutlet(city, votes);
            boolean passed = (result == null && expected == null) ||
                    (result != null && result.equals(expected));

            System.out.printf("%s%n", passed ? "PASS" : "FAIL");
            if (!passed) {
                System.out.printf("Expected: %s, Got: %s%n", expected, result);
            }
        } catch (Exception e) {
            System.out.printf("FAIL (Exception: %s)%n", e.getMessage());
        }
    }
}
