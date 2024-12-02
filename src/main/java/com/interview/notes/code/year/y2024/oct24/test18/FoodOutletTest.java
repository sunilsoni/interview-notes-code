package com.interview.notes.code.year.y2024.oct24.test18;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FoodOutletTest {
    private static int totalTests = 0;
    private static int passedTests = 0;

    // Main solution implementation
    public static String finestFoodOutlet(String city, int votes) {
        try {
            String baseUrl = "https://jsonmock.hackerrank.com/api/food_outlets";
            String bestRestaurant = null;
            double highestRating = 0;
            int maxVotes = 0;
            int currentPage = 1;
            int totalPages = 1;

            do {
                // Construct URL with parameters
                String urlString = String.format("%s?city=%s&page=%d", baseUrl, city, currentPage);
                URL url = new URL(urlString);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                // Read response
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Parse JSON response
                JSONParser parser = new JSONParser();
                JSONObject jsonResponse = (JSONObject) parser.parse(response.toString());

                // Get total pages on first iteration
                if (currentPage == 1) {
                    totalPages = ((Long) jsonResponse.get("total_pages")).intValue();
                }

                // Process data array
                JSONArray data = (JSONArray) jsonResponse.get("data");
                for (Object obj : data) {
                    JSONObject restaurant = (JSONObject) obj;
                    JSONObject userRating = (JSONObject) restaurant.get("user_rating");
                    double rating = ((Number) userRating.get("average_rating")).doubleValue();
                    int voteCount = ((Long) userRating.get("votes")).intValue();
                    String name = (String) restaurant.get("name");

                    if (voteCount >= votes) {
                        if (rating > highestRating || (rating == highestRating && voteCount > maxVotes)) {
                            highestRating = rating;
                            maxVotes = voteCount;
                            bestRestaurant = name;
                        }
                    }
                }

                currentPage++;
            } while (currentPage <= totalPages);

            return bestRestaurant;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Test helper method
    private static void testCase(String city, int votes, String expected, String testName) {
        totalTests++;
        try {
            long startTime = System.currentTimeMillis();
            String result = finestFoodOutlet(city, votes);
            long duration = System.currentTimeMillis() - startTime;

            if (expected.equals(result)) {
                passedTests++;
                System.out.println("✓ PASS: " + testName);
            } else {
                System.out.println("✗ FAIL: " + testName);
                System.out.println("  Expected: " + expected + ", Got: " + result);
            }
            System.out.println("  Time taken: " + duration + "ms");

        } catch (Exception e) {
            System.out.println("✗ FAIL: " + testName + " (Exception occurred)");
            e.printStackTrace();
        }
    }

    // Test cases
    public static void main(String[] args) {
        System.out.println("Starting Food Outlet Tests");
        System.out.println("=========================");

        // Sample test cases
        testCase("Seattle", 500, "Cafe Juanita", "Sample Case 0");
        testCase("Miami", 1000, "Pirates of Grill", "Sample Case 1");

        // Edge cases
        testCase("New York", 10000, "Expected Restaurant", "High vote threshold");
        testCase("Small Town", 100, "Expected Restaurant", "Low population city");

        // Performance test
        testCase("Los Angeles", 1000, "Expected Restaurant", "Large city test");

        // Print summary
        System.out.println("\nTest Summary");
        System.out.println("=========================");
        System.out.println("Total Tests: " + totalTests);
        System.out.println("Passed: " + passedTests);
        System.out.println("Failed: " + (totalTests - passedTests));

        if (passedTests == totalTests) {
            System.out.println("\nALL TESTS PASSED! ✓");
        } else {
            System.out.println("\nSOME TESTS FAILED! ✗");
            System.exit(1);
        }
    }
}