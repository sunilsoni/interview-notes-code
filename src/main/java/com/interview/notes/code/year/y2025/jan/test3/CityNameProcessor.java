package com.interview.notes.code.year.y2025.jan.test3;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CityNameProcessor {
    public static void main(String[] args) {
        // Initial list of city names (with duplicates and mixed cases)
        List<String> cities = Arrays.asList(
                "london", "paris", "new york", "TOKYO",
                "mumbai", "London", "PARIS", "singapore"
        );

        // Process cities using streams
        List<String> processedCities = cities.stream()
                // Convert to lowercase first to handle mixed cases
                .map(String::toLowerCase)
                // Remove duplicates
                .distinct()
                // Capitalize first letter of each word
                .map(CityNameProcessor::capitalizeWords)
                // Sort alphabetically
                .sorted()
                .collect(Collectors.toList());

        // Print original and processed cities
        System.out.println("Original cities: " + cities);
        System.out.println("Processed cities: " + processedCities);
    }

    // Helper method to capitalize first letter of each word
    private static String capitalizeWords(String city) {
        if (city == null || city.isEmpty()) {
            return city;
        }

        String[] words = city.split(" ");
        return Arrays.stream(words)
                .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1))
                .collect(Collectors.joining(" "));
    }
}
