package com.interview.notes.code.year.y2026.feb.Twilio.test1;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class BestRestaurantFinder {

    public static String finestFoodOutlet(String city, int minVotes) {
        String bestOutlet = "";
        double maxRating = -1.0;
        int maxVotes = -1;
        int page = 1;
        int totalPages = 1;

        try (var client = HttpClient.newHttpClient()) {
            do {
                String encodedCity = URLEncoder.encode(city, StandardCharsets.UTF_8);
                String url = "https://jsonmock.hackerrank.com/api/food_outlets?city=" + encodedCity + "&page=" + page;
                
                var request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
                var response = client.send(request, HttpResponse.BodyHandlers.ofString());
                
                var jsonObject = new Gson().fromJson(response.body(), JsonObject.class);
                totalPages = jsonObject.get("total_pages").getAsInt();
                JsonArray data = jsonObject.getAsJsonArray("data");

                for (JsonElement element : data) {
                    JsonObject outlet = element.getAsJsonObject();
                    JsonObject ratingObj = outlet.getAsJsonObject("user_rating");
                    double rating = ratingObj.get("average_rating").getAsDouble();
                    int votes = ratingObj.get("votes").getAsInt();

                    if (votes >= minVotes) {
                        if (rating > maxRating) {
                            maxRating = rating;
                            maxVotes = votes;
                            bestOutlet = outlet.get("name").getAsString();
                        } else if (rating == maxRating) {
                            if (votes > maxVotes) {
                                maxVotes = votes;
                                bestOutlet = outlet.get("name").getAsString();
                            }
                        }
                    }
                }
                page++;
            } while (page <= totalPages);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bestOutlet;
    }

    public static void main(String[] args) {
        System.out.println("Running Tests...");

        test("Sample Case 0", "Seattle", 500, "Cafe Juanita");
        test("Sample Case 1", "Miami", 1000, "Pirates of Grill");
        
        // Large Data / Edge Cases
        test("Non-Existent City", "Atlantis", 100, ""); 
        test("High Vote Threshold", "Seattle", 200000, ""); 

        System.out.println("Testing Complete.");
    }

    private static void test(String testName, String city, int votes, String expected) {
        long start = System.currentTimeMillis();
        String result = finestFoodOutlet(city, votes);
        long end = System.currentTimeMillis();
        
        boolean pass = result.equals(expected);
        String status = pass ? "PASS" : "FAIL";
        
        System.out.printf("[%s] %s | Input: %s, %d | Expected: '%s', Got: '%s' | Time: %dms%n", 
            status, testName, city, votes, expected, result, (end - start));
    }
}