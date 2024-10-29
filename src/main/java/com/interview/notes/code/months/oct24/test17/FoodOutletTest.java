package com.interview.notes.code.months.oct24.test17;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class FoodOutletTest {
    private static final HttpClient client = HttpClient.newHttpClient();
    private static int totalTests = 0;
    private static int passedTests = 0;

    // Main solution implementation using modern Java features
    public static String finestFoodOutlet(String city, int votes) {
        try {
            var baseUrl = "https://jsonmock.hackerrank.com/api/food_outlets";
            var bestRestaurant = new Object() {
                String name = null;
                double rating = 0;
                int votes = 0;
            };

            int currentPage = 1;
            int totalPages = 0;

            do {
                String encodedCity = URLEncoder.encode(city, StandardCharsets.UTF_8);
                URI uri = URI.create(baseUrl + "?city=" + encodedCity + "&page=" + currentPage);

                // Make HTTP call using Java 17 HttpClient
                String response = client.send(
                        HttpRequest.newBuilder(uri).GET().build(),
                        HttpResponse.BodyHandlers.ofString()
                ).body();

                // Parse JSON using simple JSON parser
                JSONObject json = (JSONObject) new JSONParser().parse(response);

                if (currentPage == 1) {
                    totalPages = ((Long) json.get("total_pages")).intValue();
                    if (totalPages == 0) return null;
                }

                // Process restaurants using streams
                ((JSONArray) json.get("data")).forEach(obj -> {
                    JSONObject restaurant = (JSONObject) obj;
                    JSONObject userRating = (JSONObject) restaurant.get("user_rating");
                    double rating = ((Number) userRating.get("average_rating")).doubleValue();
                    int voteCount = ((Long) userRating.get("votes")).intValue();

                    if (voteCount >= votes && (rating > bestRestaurant.rating ||
                            (rating == bestRestaurant.rating && voteCount > bestRestaurant.votes))) {
                        bestRestaurant.name = (String) restaurant.get("name");
                        bestRestaurant.rating = rating;
                        bestRestaurant.votes = voteCount;
                    }
                });

                currentPage++;
            } while (currentPage <= totalPages);

            return bestRestaurant.name;

        } catch (Exception e) {
            System.out.println("Error for city: " + city + " - " + e.getMessage());
            return null;
        }
    }

    // Concise test helper
    private static void test(String city, int votes, String expected, String testName) {
        totalTests++;
        long startTime = System.currentTimeMillis();
        try {
            String result = finestFoodOutlet(city, votes);
            boolean passed = (expected == null && result == null) ||
                    (expected != null && expected.equals(result));

            System.out.printf("%s %s%nInput: city=%s, votes=%d%nTime: %dms%n%n",
                    passed ? "✓" : "✗", testName, city, votes,
                    System.currentTimeMillis() - startTime);

            if (passed) passedTests++;

        } catch (Exception e) {
            System.out.println("✗ " + testName + " (Error: " + e.getMessage() + ")");
        }
    }

    public static void main(String[] args) {
        System.out.println("Food Outlet Tests\n===============");

        // Test cases
        test("Seattle", 500, "Cafe Juanita", "Sample Case 0");
        test("Miami", 1000, "Pirates of Grill", "Sample Case 1");
        test("NewYork", 10000, null, "High vote threshold");
        test("SmallTown", 100, null, "Non-existent city");

        // Summary
        System.out.printf("%nResults: %d/%d passed%n", passedTests, totalTests);
        System.exit(passedTests == totalTests ? 0 : 1);
    }
}