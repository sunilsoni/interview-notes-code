package com.interview.notes.code.year.y2024.nov24.MorganStanley.test2;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class Solution {
    public static void main(String[] args) {
        // Sample test cases
        testGetCapitalCity("Afghanistan", "Kabul");
        testGetCapitalCity("Oceania", "-1");

        // Adjusted test cases
        testGetCapitalCity("United States", "Washington, D.C.");
        testGetCapitalCity("NonExistentCountry", "-1");
        testGetCapitalCity("", "-1");
        testGetCapitalCity(" ", "-1");
        testGetCapitalCity("India", "New Delhi");
        testGetCapitalCity("United Arab Emirates", "Abu Dhabi");
    }

    /**
     * Test the getCapitalCity method with a given country and expected output.
     *
     * @param country  The country name to test.
     * @param expected The expected capital city.
     */
    public static void testGetCapitalCity(String country, String expected) {
        String result = getCapitalCity(country);
        if (result.equals(expected)) {
            System.out.println("Test Passed for country: \"" + country + "\"");
        } else {
            System.out.println("Test Failed for country: \"" + country + "\"");
            System.out.println("Expected: \"" + expected + "\", but got: \"" + result + "\"");
        }
    }

    /**
     * Fetches the capital city of a given country using a REST API.
     *
     * @param country The name of the country.
     * @return The capital city if found, otherwise "-1".
     */
    public static String getCapitalCity(String country) {
        try {
            // Construct the API URL with the encoded country name
            String baseUrl = "https://jsonmock.hackerrank.com/api/countries?name=";
            String encodedCountry = URLEncoder.encode(country, StandardCharsets.UTF_8);
            String urlString = baseUrl + encodedCountry;

            // Create a URL and open a connection
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // Set the request method to GET
            conn.setRequestMethod("GET");

            // Get the response code and check if it's HTTP OK (200)
            int responseCode = conn.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                // If not OK, return "-1"
                return "-1";
            }

            // Read the response from the input stream
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder responseStr = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                responseStr.append(inputLine);
            }
            in.close();

            // Convert the response to a String
            String response = responseStr.toString();

            // Parse the JSON response using Gson
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(response, JsonObject.class);

            // Get the "data" array from the JSON object
            JsonArray dataArray = jsonObject.getAsJsonArray("data");
            if (dataArray.size() == 0) {
                // If data array is empty, country not found, return "-1"
                return "-1";
            } else {
                // Extract the capital city from the first element in the data array
                JsonObject countryData = dataArray.get(0).getAsJsonObject();
                if (countryData.has("capital") && !countryData.get("capital").isJsonNull()) {
                    String capital = countryData.get("capital").getAsString();
                    return capital;
                } else {
                    // If "capital" field is missing or null, return "-1"
                    return "-1";
                }
            }

        } catch (Exception e) {
            // If any exception occurs, return "-1"
            return "-1";
        }
    }
}
