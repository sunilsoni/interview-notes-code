package com.interview.notes.code.months.may24.test1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CityFilter {
    public static void main(String[] args) {
        List<String> cities = Arrays.asList("New York", "London", "Tokyo", "Paris", "Sydney", "Berlin");

        List<String> filteredCities = cities.stream()
                .filter(city -> city.length() > 3)
                .collect(Collectors.toList());

        System.out.println("Cities with names longer than 3 characters: " + filteredCities);
    }
}
