package com.interview.notes.code.year.y2025.jan.test1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CityNameCapitalization {
    public static void main(String[] args) {
        // Initial list of city names in lowercase
        List<String> cities = Arrays.asList("london", "paris", "new york", "tokyo", "mumbai");

        // Using streams to capitalize first letter of each city
        List<String> capitalizedCities = cities.stream()
                .map(city -> {
                    if (city == null || city.isEmpty()) {
                        return city;
                    }
                    // Handle multi-word city names
                    String[] words = city.split(" ");
                    return Arrays.stream(words)
                            .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1))
                            .collect(Collectors.joining(" "));
                })
                .collect(Collectors.toList());

        // Print original and capitalized cities
        System.out.println("Original cities: " + cities);
        System.out.println("Capitalized cities: " + capitalizedCities);
    }
}
