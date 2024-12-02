package com.interview.notes.code.months.nov24.MorganStanley.test1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class CapitalCityFinder {
    private static final String BASE_URL = "https://jsonmock.hackerrank.com/api/countries?name=";

    // Main method for testing
    public static void main(String[] args) {
        // Test cases map with expected results
        Map<String, String> testCases = new HashMap<>();
        testCases.put("Afghanistan", "Kabul");
        testCases.put("India", "New Delhi");
        testCases.put("Oceania", "-1");
        testCases.put("", "-1");
        testCases.put("NonExistentCountry", "-1");

        // Run all test cases
        int passed = 0;
        int total = testCases.size();

        System.out.println("Running test cases...\n");

        for (Map.Entry<String, String> test : testCases.entrySet()) {
            String country = test.getKey();
            String expectedCapital = test.getValue();
            String actualCapital = getCapitalCity(country);

            boolean isPassed = expectedCapital.equals(actualCapital);
            passed += isPassed ? 1 : 0;

            System.out.printf("Test Case: Country='%s'%n", country);
            System.out.printf("Expected: '%s'%n", expectedCapital);
            System.out.printf("Actual: '%s'%n", actualCapital);
            System.out.printf("Status: %s%n%n", isPassed ? "PASSED" : "FAILED");
        }

        // Print summary
        System.out.println("Test Summary:");
        System.out.printf("Passed: %d/%d (%.2f%%)%n",
                passed, total, (passed * 100.0 / total));
    }

    public static String getCapitalCity(String country) {
        if (country == null || country.trim().isEmpty()) {
            return "-1";
        }

        try {
            URL url = new URL(BASE_URL + country.trim());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() != 200) {
                return "-1";
            }

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream())
            );
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Simple JSON parsing for capital city
            String jsonResponse = response.toString();

            // Check if data array is empty
            if (jsonResponse.contains("\"data\":[]")) {
                return "-1";
            }

            // Extract capital from response
            int capitalStart = jsonResponse.indexOf("\"capital\":\"");
            if (capitalStart == -1) {
                return "-1";
            }

            capitalStart += 10; // Length of "\"capital\":\""
            int capitalEnd = jsonResponse.indexOf("\"", capitalStart);

            return jsonResponse.substring(capitalStart, capitalEnd);

        } catch (Exception e) {
            return "-1";
        }
    }
}
