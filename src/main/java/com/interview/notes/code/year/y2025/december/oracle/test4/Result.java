package com.interview.notes.code.year.y2025.december.oracle.test4;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Result {

    // API base URL for medical records
    static final String URL = "https://jsonmock.hackerrank.com/api/medical_records?page=";

    // HTTP client instance for API calls
    static final HttpClient client = HttpClient.newHttpClient();

    // Gson parser for JSON handling
    static final Gson gson = new Gson();

    public static List<Integer> getRecordsByAgeGroup(int ageStart, int ageEnd, int bpDiff) {

        var ids = new ArrayList<Integer>(); // Store matching record IDs
        int page = 1, totalPages = 1; // Pagination control

        // Loop through all pages
        while (page <= totalPages) {
            var json = fetchPage(page); // Get page data
            totalPages = json.get("total_pages").getAsInt(); // Update total pages

            // Process each record in data array
            for (var rec : json.getAsJsonArray("data")) {
                var obj = rec.getAsJsonObject(); // Convert to JSON object

                // Extract timestamp and DOB for age calculation
                long ts = obj.get("timestamp").getAsLong();
                String dob = obj.get("userDob").getAsString();

                // Calculate age at record creation
                int age = calcAge(dob, ts);

                // Check age range condition
                if (age >= ageStart && age <= ageEnd) {
                    var vitals = obj.getAsJsonObject("vitals"); // Get vitals object
                    int dia = vitals.get("bloodPressureDiastole").getAsInt(); // Diastolic BP
                    int sys = vitals.get("bloodPressureSystole").getAsInt(); // Systolic BP

                    // Check BP difference condition (must be greater than bpDiff)
                    if ((dia - sys) > bpDiff) {
                        ids.add(obj.get("id").getAsInt()); // Add matching ID
                    }
                }
            }
            page++; // Move to next page
        }

        Collections.sort(ids); // Sort IDs in ascending order
        return ids.isEmpty() ? List.of(-1) : ids; // Return [-1] if no matches
    }

    // Fetch page from API and parse JSON
    static JsonObject fetchPage(int page) {
        try {
            var req = HttpRequest.newBuilder()
                    .uri(URI.create(URL + page)) // Build URL with page number
                    .GET().build(); // GET request
            var res = client.send(req, HttpResponse.BodyHandlers.ofString()); // Send request
            return gson.fromJson(res.body(), JsonObject.class); // Parse response
        } catch (Exception e) {
            return new JsonObject(); // Empty object on error
        }
    }

    // Calculate age from DOB string and timestamp
    static int calcAge(String dob, long timestamp) {
        var birth = LocalDate.parse(dob, DateTimeFormatter.ofPattern("dd-MM-yyyy")); // Parse DOB
        var record = Instant.ofEpochMilli(timestamp).atZone(ZoneOffset.UTC).toLocalDate(); // Record date
        return Period.between(birth, record).getYears(); // Years between dates
    }

    // Main method for testing
    public static void main(String[] args) {
        System.out.println("=== Test Cases ===\n");

        // Test 1: Sample case
        test(1, 28, 30, 63, List.of(31));

        // Test 2: No matches - high age
        test(2, 100, 120, 10, List.of(-1));

        // Test 3: Wide range
        test(3, 20, 50, 40, null);

        // Test 4: Single age
        test(4, 29, 29, 60, null);

        // Test 5: Large bpDiff - likely no match
        test(5, 0, 100, 500, List.of(-1));
    }

    // Test helper method
    static void test(int n, int s, int e, int bp, List<Integer> exp) {
        System.out.println("Test " + n + ": ageStart=" + s + ", ageEnd=" + e + ", bpDiff=" + bp);
        long t = System.currentTimeMillis(); // Start timer
        var res = getRecordsByAgeGroup(s, e, bp); // Run function
        System.out.println("Result: " + res + " (" + (System.currentTimeMillis() - t) + "ms)");
        if (exp != null) System.out.println("Status: " + (res.equals(exp) ? "PASS ✓" : "FAIL ✗"));
        System.out.println();
    }
}