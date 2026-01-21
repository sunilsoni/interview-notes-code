package com.interview.notes.code.year.y2024.dec24.ibm.test3;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FinestFoodOutletFinder {

    /*
     * Complete the 'finestFoodOutlet' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. STRING city
     *  2. INTEGER votes
     * API URL: https://jsonmock.hackerrank.com/api/food_outlets?city={city}&page={page_no}
     */
    public static String finestFoodOutlet(String city, int votes) {
        String baseUrl = "https://jsonmock.hackerrank.com/api/food_outlets?city=" + encodeURIComponent(city) + "&page=";
        List<FoodOutlet> filteredOutlets = new ArrayList<>();
        Gson gson = new Gson();

        try {
            // Initial request to get total pages
            String firstPageUrl = baseUrl + "1";
            String firstPageResponse = getHTTPResponse(firstPageUrl);
            ApiResponse apiResponse = gson.fromJson(firstPageResponse, ApiResponse.class);
            int totalPages = apiResponse.getTotal_pages();

            // Iterate through all pages
            for (int page = 1; page <= totalPages; page++) {
                String pageUrl = baseUrl + page;
                String pageResponse = getHTTPResponse(pageUrl);
                ApiResponse currentPage = gson.fromJson(pageResponse, ApiResponse.class);
                List<FoodOutlet> outlets = currentPage.getData();

                for (FoodOutlet outlet : outlets) {
                    if (outlet.getUser_rating() != null && outlet.getUser_rating().getVotes() >= votes) {
                        filteredOutlets.add(outlet);
                    }
                }
            }

            if (filteredOutlets.isEmpty()) {
                return "No outlet found";
            }

            // Find the outlet with the highest average rating
            // If tie, choose the one with maximum votes
            // If still tie, choose the lexicographically smaller name
            FoodOutlet bestOutlet = null;
            double highestRating = Double.MIN_VALUE;
            int maxVotes = Integer.MIN_VALUE;

            for (FoodOutlet outlet : filteredOutlets) {
                UserRating userRating = outlet.getUser_rating();
                if (userRating == null) continue; // Skip if user_rating is missing

                double avgRating = userRating.getAverage_rating();
                int outletVotes = userRating.getVotes();
                String outletName = outlet.getName();

                if (avgRating > highestRating) {
                    highestRating = avgRating;
                    maxVotes = outletVotes;
                    bestOutlet = outlet;
                } else if (avgRating == highestRating) {
                    if (outletVotes > maxVotes) {
                        maxVotes = outletVotes;
                        bestOutlet = outlet;
                    } else if (outletVotes == maxVotes) {
                        if (bestOutlet == null || outletName.compareTo(bestOutlet.getName()) < 0) {
                            bestOutlet = outlet;
                        }
                    }
                }
            }

            if (bestOutlet != null) {
                return bestOutlet.getName();
            } else {
                return "No outlet found";
            }

        } catch (Exception e) {
            // For debugging purposes, you can print the stack trace.
            // In a production environment, consider logging this instead.
            e.printStackTrace();
            return "Error occurred";
        }
    }

    // Helper method to make HTTP GET requests and return response as String
    private static String getHTTPResponse(String urlString) throws IOException {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        // Check the response code
        int responseCode = conn.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new IOException("HTTP GET Request Failed with Error code : " + responseCode);
        }

        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        return result.toString();
    }

    // Helper method to encode URL parameters
    private static String encodeURIComponent(String component) {
        return URLEncoder.encode(component, StandardCharsets.UTF_8).replaceAll("\\+", "%20")
                .replaceAll("\\%21", "!")
                .replaceAll("\\%27", "'")
                .replaceAll("\\%28", "(")
                .replaceAll("\\%29", ")")
                .replaceAll("\\%7E", "~");
    }

    // Method to run a single test case
    private static void runTestCase(int testCaseNumber, String city, int votes, String expected) {
        String result = finestFoodOutlet(city, votes);
        if (result.equals(expected)) {
            System.out.println("Test Case " + testCaseNumber + ": PASS");
        } else {
            System.out.println("Test Case " + testCaseNumber + ": FAIL (Expected \"" + expected + "\", Got \"" + result + "\")");
        }
    }

    public static void main(String[] args) {
        // Define test cases
        List<TestCase> testCases = new ArrayList<>();

        // Sample Case 0
        testCases.add(new TestCase(0, "Seattle", 500, "Cafe Juanita"));

        // Sample Case 1
        testCases.add(new TestCase(1, "Miami", 1000, "Pirates of Grill"));

        // Additional Test Cases
        // Test Case 2: No outlets in the city
        testCases.add(new TestCase(2, "Atlantis", 100, "No outlet found"));

        // Test Case 3: No outlets meeting the vote requirement
        testCases.add(new TestCase(3, "Seattle", 1000000, "No outlet found"));

        // Test Case 4: Multiple outlets with same rating, different votes
        testCases.add(new TestCase(4, "New York", 100, "Best Bistro"));

        // Test Case 5: Multiple outlets with same rating and votes
        testCases.add(new TestCase(5, "Chicago", 50, "Central Cafe"));

        // Test Case 6: Single outlet meeting the criteria
        testCases.add(new TestCase(6, "Los Angeles", 200, "Sunset Grill"));

        // Test Case 7: All outlets have votes >= minimum and unique ratings
        testCases.add(new TestCase(7, "Houston", 10, "Houston Diner"));

        // Test Case 8: Large number of outlets (simulation assumed)
        testCases.add(new TestCase(8, "Phoenix", 5, "Phoenix Palace"));

        // Run all test cases
        for (TestCase tc : testCases) {
            runTestCase(tc.testCaseNumber, tc.city, tc.votes, tc.expected);
        }
    }

    // Static inner class to map JSON response using Gson
    static class ApiResponse {
        private int page;
        private int per_page;
        private int total;
        private int total_pages;
        private List<FoodOutlet> data;

        public int getPage() {
            return page;
        }

        public int getPer_page() {
            return per_page;
        }

        public int getTotal() {
            return total;
        }

        public int getTotal_pages() {
            return total_pages;
        }

        public List<FoodOutlet> getData() {
            return data;
        }
    }

    // Static inner class to map FoodOutlet data
    static class FoodOutlet {
        private int id;
        private String name;
        private String city;
        private double estimated_cost;
        private UserRating user_rating;

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getCity() {
            return city;
        }

        public double getEstimated_cost() {
            return estimated_cost;
        }

        public UserRating getUser_rating() {
            return user_rating;
        }
    }

    // Static inner class to map UserRating data
    static class UserRating {
        private double average_rating;
        private int votes;

        public double getAverage_rating() {
            return average_rating;
        }

        public int getVotes() {
            return votes;
        }
    }

    // Helper class to represent a test case
    static class TestCase {
        int testCaseNumber;
        String city;
        int votes;
        String expected;

        TestCase(int testCaseNumber, String city, int votes, String expected) {
            this.testCaseNumber = testCaseNumber;
            this.city = city;
            this.votes = votes;
            this.expected = expected;
        }
    }
}
