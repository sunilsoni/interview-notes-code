package com.interview.notes.code.year.y2024.dec24.ibm.test2;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class FinestFoodOutletFinder {

    /*
     * Complete the 'finestFoodOutlet' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. STRING city
     *  2. INTEGER votes
     * API URL https://jsonmock.hackerrank.com/api/food_outlets?city={city}&page={page_no}
     */

    public static String finestFoodOutlet(String city, int votes) {
        String baseUrl = "https://jsonmock.hackerrank.com/api/food_outlets?city=" + city;
        int page = 1;
        int totalPages = 1;
        String finestOutletName = "";
        double highestRating = -1;
        int highestVotes = -1;

        try {
            // Create an HttpClient (Java 11+)
            HttpClient client = HttpClient.newHttpClient();

            while (page <= totalPages) {
                String urlString = baseUrl + "&page=" + page;
                String response = getApiResponse(urlString, client);

                if (response == null) {
                    break; // Error occurred while fetching data
                }

                // Parse JSON response using JSON.simple
                JSONParser parser = new JSONParser();
                JSONObject jsonResponse = (JSONObject) parser.parse(response);

                totalPages = ((Long) jsonResponse.get("total_pages")).intValue();
                JSONArray data = (JSONArray) jsonResponse.get("data");

                for (Object obj : data) {
                    JSONObject outlet = (JSONObject) obj;
                    JSONObject userRating = (JSONObject) outlet.get("user_rating");

                    // Correctly handle the average_rating, which may be Double or Long
                    Object ratingObj = userRating.get("average_rating");
                    double outletRating;
                    if (ratingObj instanceof Number) {
                        outletRating = ((Number) ratingObj).doubleValue();
                    } else {
                        // In case it's a String, parse it
                        outletRating = Double.parseDouble((String) ratingObj);
                    }

                    int outletVotes = ((Long) userRating.get("votes")).intValue();

                    if (outletVotes >= votes) {
                        if (outletRating > highestRating) {
                            highestRating = outletRating;
                            highestVotes = outletVotes;
                            finestOutletName = (String) outlet.get("name");
                        } else if (outletRating == highestRating) {
                            if (outletVotes > highestVotes) {
                                highestVotes = outletVotes;
                                finestOutletName = (String) outlet.get("name");
                            }
                        }
                    }
                }
                page++;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

        return finestOutletName;
    }

    // Helper method to make GET requests using HttpClient
    private static String getApiResponse(String urlString, HttpClient client) {
        try {
            URI uri = new URI(urlString.replace(" ", "%20")); // Encode spaces in URL
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .GET()
                    .timeout(Duration.ofSeconds(10))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return response.body();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        testFinestFoodOutlet();
    }

    // Method to test the finestFoodOutlet function with various test cases
    private static void testFinestFoodOutlet() {
        boolean allPassed = true;

        // Sample Case 0
        String city1 = "Seattle";
        int votes1 = 500;
        String expected1 = "Cafe Juanita";
        String result1 = finestFoodOutlet(city1, votes1);
        if (result1.equals(expected1)) {
            System.out.println("Sample Case 0: PASS");
        } else {
            System.out.println("Sample Case 0: FAIL (Expected \"" + expected1 + "\", Got \"" + result1 + "\")");
            allPassed = false;
        }

        // Sample Case 1
        String city2 = "Miami";
        int votes2 = 1000;
        String expected2 = "Pirates of Grill";
        String result2 = finestFoodOutlet(city2, votes2);
        if (result2.equals(expected2)) {
            System.out.println("Sample Case 1: PASS");
        } else {
            System.out.println("Sample Case 1: FAIL (Expected \"" + expected2 + "\", Got \"" + result2 + "\")");
            allPassed = false;
        }

        // Test Case 3: No outlets meet the criteria
        String city3 = "NonExistentCity";
        int votes3 = 100;
        String expected3 = ""; // Assuming empty string when no outlets are found
        String result3 = finestFoodOutlet(city3, votes3);
        if (result3.equals(expected3)) {
            System.out.println("Test Case 3: PASS");
        } else {
            System.out.println("Test Case 3: FAIL (Expected empty string, Got \"" + result3 + "\")");
            allPassed = false;
        }

        // Test Case 4: Multiple outlets with same rating and votes
        String city4 = "San Francisco";
        int votes4 = 1000;
        String result4 = finestFoodOutlet(city4, votes4);
        // Since we may not know the expected result, we'll check if the result is non-empty
        if (result4 != null && !result4.isEmpty()) {
            System.out.println("Test Case 4: PASS");
        } else {
            System.out.println("Test Case 4: FAIL (Expected non-empty string, Got \"" + result4 + "\")");
            allPassed = false;
        }

        // Final result
        if (allPassed) {
            System.out.println("All test cases passed.");
        } else {
            System.out.println("Some test cases failed.");
        }
    }
}