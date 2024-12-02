package com.interview.notes.code.months.nov24.MorganStanley.test3;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

/*

**Question: REST API - Capital City**

You are tasked with completing the `getCapitalCity` function that interacts with a REST API to fetch the capital city of a specified country. The function must meet the following requirements:

### Function Description:
Complete the function `getCapitalCity` in the editor.

#### Parameters:
- `string country`: The name of the country to query.

#### Returns:
- `string`: The name of the capital city, or `'-1'` if the country is not found or an error occurs.

### Input Format:
- A single line containing the name of the country.

### Output:
- The name of the capital city if the country is found.
- `'-1'` if the country is not found or the API response does not contain the data.

### Details of the REST API:
The function queries the following REST API:
```
https://jsonmock.hackerrank.com/api/countries?name=<country>
```

### Response:
The response is a JSON object with five fields. However, the essential field for this task is `data`, which has the following schema:
- `data`: An array containing either 0 or 1 record.
  - If the country is found, the `data` array contains exactly 1 element.
  - If the country is not found, the `data` array is empty.

Each record in the `data` array contains:
- `name`: The name of the country.
- `capital`: The capital city of the country.
- Other fields that are not relevant for this task.

### Constraints:
- The returned JSON object contains either 0 or 1 record in the `data` array.
- The country name may contain uppercase and lowercase English letters and spaces (`ASCII 32`).

### Examples:
#### Sample Case 0:
**Input:**
```
Afghanistan
```
**API Call:**
```
https://jsonmock.hackerrank.com/api/countries?name=Afghanistan
```
**Output:**
```
Kabul
```
**Explanation:**
The `data` array in the response contains one record with the `capital` field as "Kabul".

---

#### Sample Case 1:
**Input:**
```
Oceania
```
**API Call:**
```
https://jsonmock.hackerrank.com/api/countries?name=Oceania
```
**Output:**
```
-1
```
**Explanation:**
The `data` array in the response is empty, indicating that the country is not found.

### Notes:
- Page-related fields in the response, such as `page`, `per_page`, `total`, and `total_pages`, are not relevant for this task and can be ignored.
- Use appropriate libraries for making HTTP requests, as available in the programming environment. Refer to the [environment documentation](https://www.hackerrank.com/environment) for supported libraries.

---

**Complete the function implementation for accurate results.**

 */
public class CapitalCityFinder {
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

            // Create an HttpClient instance
            HttpClient client = HttpClient.newHttpClient();

            // Build the HttpRequest
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(urlString))
                    .GET()
                    .build();

            // Send the request and get the response asynchronously
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Check if the response status code is 200 OK
            if (response.statusCode() != 200) {
                return "-1";
            }

            // Get the response body
            String responseBody = response.body();

            // Parse the JSON response using Gson
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(responseBody, JsonObject.class);

            // Get the "data" array from the JSON object
            JsonArray dataArray = jsonObject.getAsJsonArray("data");
            if (dataArray == null || dataArray.size() == 0) {
                // If data array is empty or null, country not found, return "-1"
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
