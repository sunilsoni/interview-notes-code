package com.interview.notes.code.year.y2024.oct24.test18;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Result {

    public static String finestFoodOutlet(String city, int minVotes) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        String apiUrl = "https://jsonmock.hackerrank.com/api/food_outlets?city=" + city;
        int page = 1;
        List<FoodOutlet> foodOutlets = new ArrayList<>();

        // Fetch and process paginated API results
        while (true) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl + "&page=" + page))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject jsonResponse = (JSONObject) new JSONParser().parse(response.body());
            JSONArray data = (JSONArray) jsonResponse.get("data");

            if (data.isEmpty()) break;

            for (Object obj : data) {
                JSONObject outlet = (JSONObject) obj;
                long votes = (long) ((JSONObject) outlet.get("user_rating")).get("votes");
                double rating = (double) ((JSONObject) outlet.get("user_rating")).get("average_rating");
                String name = (String) outlet.get("name");

                if (votes >= minVotes) {
                    foodOutlets.add(new FoodOutlet(name, rating, votes));
                }
            }

            long totalPages = (long) jsonResponse.get("total_pages");
            if (page >= totalPages) break;
            page++;
        }

        return foodOutlets.stream()
                .sorted(Comparator.comparing(FoodOutlet::getRating).reversed()
                        .thenComparing(FoodOutlet::getVotes, Comparator.reverseOrder()))
                .map(FoodOutlet::getName)
                .findFirst()
                .orElse("");
    }

    public static void main(String[] args) throws Exception {
        // Sample test cases
        test("Seattle", 500, "Cafe Juanita");
        test("Miami", 1000, "Pirates of Grill");
    }

    // Test method to verify the output
    public static void test(String city, int votes, String expected) throws Exception {
        String result = finestFoodOutlet(city, votes);
        System.out.println(result.equals(expected) ? "PASS" : "FAIL: Expected " + expected + " but got " + result);
    }

    // Helper class for food outlet information
    static class FoodOutlet {
        String name;
        double rating;
        long votes;

        FoodOutlet(String name, double rating, long votes) {
            this.name = name;
            this.rating = rating;
            this.votes = votes;
        }

        String getName() {
            return name;
        }

        double getRating() {
            return rating;
        }

        long getVotes() {
            return votes;
        }
    }
}
