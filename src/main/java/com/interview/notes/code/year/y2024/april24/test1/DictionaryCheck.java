package com.interview.notes.code.year.y2024.april24.test1;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashSet;
import java.util.Set;

public class DictionaryCheck {

    private static final String API_ENDPOINT = "https://your-api-url.com/check-word";

    // Method to generate all unique combinations of a given string
    private static void generateCombinations(String str, String ans, Set<String> results) {
        if (str.length() == 0) {
            if (!ans.isEmpty()) {
                results.add(ans);
            }
            return;
        }

        // Include the first character
        generateCombinations(str.substring(1), ans + str.charAt(0), results);

        // Exclude the first character
        generateCombinations(str.substring(1), ans, results);
    }

    // Method to check if a word is valid via the API
    private static boolean isValidWord(String word) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_ENDPOINT + "?word=" + word))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Assuming the API returns a simple true/false as the response body
        return Boolean.parseBoolean(response.body());
    }

    public static void main(String[] args) throws Exception {
        String input = "flower";
        Set<String> combinations = new HashSet<>();
        generateCombinations(input, "", combinations);

        for (String combination : combinations) {
            if (isValidWord(combination)) {
                System.out.println(combination + " is a valid word.");
            }
        }
    }
}
