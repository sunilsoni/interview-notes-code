package com.interview.notes.code.year.y2025.december.oracle.test2;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MedicalRecordsByAge {
    
    // Base URL for the medical records API
    static final String BASE_URL = "https://jsonmock.hackerrank.com/api/medical_records";
    
    // HTTP client for making API requests - reusable instance
    static final HttpClient client = HttpClient.newHttpClient();
    
    // Main function to get records by age group
    public static List<Integer> getRecordsByAgeGroup(int ageStart, int ageEnd, int bpDiff) {
        
        // List to store all matching record IDs
        var matchingIds = new ArrayList<Integer>();
        
        // Fetch first page to get total pages count
        var firstPage = fetchPage(1);
        
        // Extract total pages from response
        int totalPages = extractInt(firstPage, "total_pages");
        
        // Process all pages using stream
        IntStream.rangeClosed(1, totalPages)
            .mapToObj(MedicalRecordsByAge::fetchPage) // Fetch each page
            .flatMap(MedicalRecordsByAge::extractRecords) // Extract records from page
            .filter(record -> isValidRecord(record, ageStart, ageEnd, bpDiff)) // Filter valid records
            .map(record -> extractInt(record, "id")) // Get record ID
            .forEach(matchingIds::add); // Collect IDs
        
        // Sort the result in ascending order
        Collections.sort(matchingIds);
        
        // Return [-1] if no records found, else return sorted IDs
        return matchingIds.isEmpty() ? List.of(-1) : matchingIds;
    }
    
    // Fetch a specific page from the API
    static String fetchPage(int page) {
        try {
            // Build the request with page parameter
            var request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "?page=" + page)) // Append page number to URL
                .GET() // HTTP GET method
                .build();
            
            // Send request and get response body as string
            return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        } catch (Exception e) {
            // Return empty JSON on error
            return "{}";
        }
    }
    
    // Extract records array from JSON response as stream
    static Stream<String> extractRecords(String json) {
        // Find the data array in JSON
        int dataStart = json.indexOf("\"data\":[");
        if (dataStart == -1) return Stream.empty(); // No data found
        
        // Find the start of array
        int arrayStart = json.indexOf("[", dataStart);
        int arrayEnd = findMatchingBracket(json, arrayStart); // Find matching closing bracket
        
        // Extract the array content
        String dataArray = json.substring(arrayStart + 1, arrayEnd);
        
        // Split into individual record objects
        var records = new ArrayList<String>();
        int depth = 0; // Track nested braces depth
        int start = 0; // Start of current record
        
        // Parse through array to split records
        for (int i = 0; i < dataArray.length(); i++) {
            char c = dataArray.charAt(i);
            if (c == '{') depth++; // Entering nested object
            else if (c == '}') depth--; // Exiting nested object
            else if (c == ',' && depth == 0) { // Record separator at top level
                records.add(dataArray.substring(start, i).trim());
                start = i + 1; // Move to next record
            }
        }
        // Add last record if exists
        if (start < dataArray.length()) {
            records.add(dataArray.substring(start).trim());
        }
        
        return records.stream().filter(s -> !s.isEmpty()); // Return non-empty records
    }
    
    // Find matching closing bracket for an opening bracket
    static int findMatchingBracket(String json, int start) {
        int depth = 0;
        for (int i = start; i < json.length(); i++) {
            if (json.charAt(i) == '[') depth++; // Count opening brackets
            else if (json.charAt(i) == ']') depth--; // Count closing brackets
            if (depth == 0) return i; // Found matching bracket
        }
        return json.length() - 1;
    }
    
    // Check if record matches the criteria
    static boolean isValidRecord(String record, int ageStart, int ageEnd, int bpDiff) {
        // Extract timestamp (record creation time in millis)
        long timestamp = extractLong(record, "timestamp");
        
        // Extract user date of birth
        String dob = extractString(record, "userDob");
        
        // Calculate age at record creation time
        int age = calculateAge(dob, timestamp);
        
        // Check if age is within range (inclusive)
        if (age < ageStart || age > ageEnd) return false;
        
        // Extract vitals section
        String vitals = extractObject(record, "vitals");
        
        // Get blood pressure values
        int diastole = extractInt(vitals, "bloodPressureDiastole");
        int systole = extractInt(vitals, "bloodPressureSystole");
        
        // Calculate BP difference
        int actualBpDiff = diastole - systole;
        
        // Check if BP difference is greater than threshold
        return actualBpDiff > bpDiff;
    }
    
    // Calculate age from DOB string and timestamp
    static int calculateAge(String dob, long timestamp) {
        // Parse DOB in DD-MM-YYYY format
        var formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        var birthDate = LocalDate.parse(dob, formatter);
        
        // Convert timestamp to LocalDate
        var recordDate = Instant.ofEpochMilli(timestamp)
            .atZone(ZoneId.of("UTC"))
            .toLocalDate();
        
        // Calculate years between birth and record creation
        return Period.between(birthDate, recordDate).getYears();
    }
    
    // Extract integer value for a key from JSON
    static int extractInt(String json, String key) {
        String value = extractValue(json, key);
        try {
            return Integer.parseInt(value); // Parse string to int
        } catch (Exception e) {
            return 0; // Default value on error
        }
    }
    
    // Extract long value for a key from JSON
    static long extractLong(String json, String key) {
        String value = extractValue(json, key);
        try {
            return Long.parseLong(value); // Parse string to long
        } catch (Exception e) {
            return 0L; // Default value on error
        }
    }
    
    // Extract string value for a key from JSON
    static String extractString(String json, String key) {
        int keyIdx = json.indexOf("\"" + key + "\"");
        if (keyIdx == -1) return "";
        int colonIdx = json.indexOf(":", keyIdx);
        int valueStart = json.indexOf("'", colonIdx);
        if (valueStart == -1) valueStart = json.indexOf("\"", colonIdx);
        int valueEnd = json.indexOf("'", valueStart + 1);
        if (valueEnd == -1) valueEnd = json.indexOf("\"", valueStart + 1);
        return json.substring(valueStart + 1, valueEnd); // Return extracted value
    }
    
    // Extract raw value for a key
    static String extractValue(String json, String key) {
        int keyIdx = json.indexOf("\"" + key + "\"");
        if (keyIdx == -1) return "";
        int colonIdx = json.indexOf(":", keyIdx);
        int valueStart = colonIdx + 1;
        // Skip whitespace
        while (valueStart < json.length() && Character.isWhitespace(json.charAt(valueStart))) {
            valueStart++;
        }
        int valueEnd = valueStart;
        // Find end of value
        while (valueEnd < json.length() && 
               json.charAt(valueEnd) != ',' && 
               json.charAt(valueEnd) != '}') {
            valueEnd++;
        }
        return json.substring(valueStart, valueEnd).trim();
    }
    
    // Extract nested object as string
    static String extractObject(String json, String key) {
        int keyIdx = json.indexOf("\"" + key + "\"");
        if (keyIdx == -1) return "{}";
        int braceStart = json.indexOf("{", keyIdx);
        if (braceStart == -1) return "{}";
        int depth = 1, braceEnd = braceStart + 1;
        // Find matching closing brace
        while (braceEnd < json.length() && depth > 0) {
            if (json.charAt(braceEnd) == '{') depth++;
            else if (json.charAt(braceEnd) == '}') depth--;
            braceEnd++;
        }
        return json.substring(braceStart, braceEnd);
    }
    
    // Test method to verify all test cases
    public static void main(String[] args) {
        System.out.println("=== Medical Records Filter Tests ===\n");
        
        // Test Case 1: Sample case from problem
        runTest(1, 28, 30, 63, List.of(31));
        
        // Test Case 2: Wide age range
        runTest(2, 20, 40, 50, null); // Expected varies based on API data
        
        // Test Case 3: No matches expected
        runTest(3, 100, 120, 10, List.of(-1));
        
        // Test Case 4: Edge case - same start and end age
        runTest(4, 29, 29, 60, null);
        
        // Test Case 5: Large bpDiff - no matches
        runTest(5, 0, 100, 200, List.of(-1));
        
        // Test Case 6: Zero values
        runTest(6, 0, 0, 0, null);
        
        System.out.println("\n=== Tests Complete ===");
    }
    
    // Helper to run and display test results
    static void runTest(int testNum, int ageStart, int ageEnd, int bpDiff, List<Integer> expected) {
        System.out.println("Test " + testNum + ":");
        System.out.println("  Input: ageStart=" + ageStart + ", ageEnd=" + ageEnd + ", bpDiff=" + bpDiff);
        
        long startTime = System.currentTimeMillis(); // Track execution time
        var result = getRecordsByAgeGroup(ageStart, ageEnd, bpDiff);
        long duration = System.currentTimeMillis() - startTime;
        
        System.out.println("  Output: " + result);
        System.out.println("  Time: " + duration + "ms");
        
        // Check if result matches expected (if provided)
        if (expected != null) {
            boolean pass = result.equals(expected);
            System.out.println("  Expected: " + expected);
            System.out.println("  Status: " + (pass ? "PASS ✓" : "FAIL ✗"));
        } else {
            System.out.println("  Status: EXECUTED (manual verification needed)");
        }
        System.out.println();
    }
}