package com.interview.notes.code.year.y2024.nov24.MorganStanley.test3;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

/**
 * Solution class containing methods to fetch the capital city of a country.
 * <p>
 * <p>
 * *Question: REST API - Capital City**
 * <p>
 * You are tasked with completing the `getCapitalCity` function that interacts with a REST API to fetch the capital city of a specified country. The function must meet the following requirements:
 * <p>
 * ### Function Description:
 * Complete the function `getCapitalCity` in the editor.
 * <p>
 * #### Parameters:
 * - `string country`: The name of the country to query.
 * <p>
 * #### Returns:
 * - `string`: The name of the capital city, or `'-1'` if the country is not found or an error occurs.
 * <p>
 * ### Input Format:
 * - A single line containing the name of the country.
 * <p>
 * ### Output:
 * - The name of the capital city if the country is found.
 * - `'-1'` if the country is not found or the API response does not contain the data.
 * <p>
 * ### Details of the REST API:
 * The function queries the following REST API:
 * ```
 * https://jsonmock.hackerrank.com/api/countries?name=<country>
 * ```
 * <p>
 * ### Response:
 * The response is a JSON object with five fields. However, the essential field for this task is `data`, which has the following schema:
 * - `data`: An array containing either 0 or 1 record.
 * - If the country is found, the `data` array contains exactly 1 element.
 * - If the country is not found, the `data` array is empty.
 * <p>
 * Each record in the `data` array contains:
 * - `name`: The name of the country.
 * - `capital`: The capital city of the country.
 * - Other fields that are not relevant for this task.
 * <p>
 * ### Constraints:
 * - The returned JSON object contains either 0 or 1 record in the `data` array.
 * - The country name may contain uppercase and lowercase English letters and spaces (`ASCII 32`).
 * <p>
 * ### Examples:
 * #### Sample Case 0:
 * *Input:**
 * ```
 * Afghanistan
 * ```
 * *API Call:**
 * ```
 * https://jsonmock.hackerrank.com/api/countries?name=Afghanistan
 * ```
 * *Output:**
 * ```
 * Kabul
 * ```
 * *Explanation:**
 * The `data` array in the response contains one record with the `capital` field as "Kabul".
 * <p>
 * ---
 * <p>
 * #### Sample Case 1:
 * *Input:**
 * ```
 * Oceania
 * ```
 * *API Call:**
 * ```
 * https://jsonmock.hackerrank.com/api/countries?name=Oceania
 * ```
 * *Output:**
 * ```
 * -1
 * ```
 * *Explanation:**
 * The `data` array in the response is empty, indicating that the country is not found.
 * <p>
 * ### Notes:
 * - Page-related fields in the response, such as `page`, `per_page`, `total`, and `total_pages`, are not relevant for this task and can be ignored.
 * - Use appropriate libraries for making HTTP requests, as available in the programming environment. Refer to the [environment documentation](https://www.hackerrank.com/environment) for supported libraries.
 * <p>
 * ---
 * <p>
 * *Complete the function implementation for accurate results.**
 */
public class Solution {

    // Base URL for the API endpoint
    private static final String BASE_URL = "https://jsonmock.hackerrank.com/api/countries?name=";

    // Gson instance for JSON parsing
    private static final Gson GSON = new Gson();

    public static void main(String[] args) {
        // Sample test cases
        testGetCapitalCity("Afghanistan", "Kabul");
        testGetCapitalCity("Oceania", "-1");

        // Additional test cases
        testGetCapitalCity("United States", "Washington, D.C.");
        testGetCapitalCity("NonExistentCountry", "-1");
        testGetCapitalCity("", "-1");
        testGetCapitalCity(" ", "-1");
        testGetCapitalCity("India", "New Delhi");
        testGetCapitalCity("United Arab Emirates", "Abu Dhabi");
    }

    /**
     * Tests the getCapitalCity method with a given country and expected output.
     *
     * @param country  The country name to test.
     * @param expected The expected capital city.
     */
    private static void testGetCapitalCity(String country, String expected) {
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
        // Validate input
        if (country == null || country.trim().isEmpty()) {
            return "-1";
        }

        try {
            // Encode the country name to handle special characters
            String encodedCountry = URLEncoder.encode(country.trim(), StandardCharsets.UTF_8);

            // Construct the full API URL
            String urlString = BASE_URL + encodedCountry;

            // Create an HttpClient instance
            HttpClient client = HttpClient.newHttpClient();

            // Build the HttpRequest
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(urlString))
                    .GET()
                    .build();

            // Send the request and get the response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Check if the response status code is 200 OK
            if (response.statusCode() != 200) {
                return "-1";
            }

            // Parse the JSON response
            JsonObject jsonObject = GSON.fromJson(response.body(), JsonObject.class);

            // Extract the "data" array from the JSON object
            JsonArray dataArray = jsonObject.getAsJsonArray("data");
            if (dataArray == null || dataArray.size() == 0) {
                // Country not found
                return "-1";
            }

            // Extract the capital city from the first element in the data array
            JsonObject countryData = dataArray.get(0).getAsJsonObject();
            String capital = countryData.has("capital") && !countryData.get("capital").isJsonNull()
                    ? countryData.get("capital").getAsString()
                    : "-1";

            return capital.isEmpty() ? "-1" : capital;

        } catch (Exception e) {
            // Log the exception (optional)
            // e.printStackTrace();
            return "-1";
        }
    }
}
