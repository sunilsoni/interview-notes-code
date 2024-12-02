package com.interview.notes.code.year.y2024.may24.test11;

import java.util.List;

public class PopulationCalculator {
    public static int getPopulation(List<Country> countries, String continent) {
        int totalPopulation = 0;
        for (Country country : countries) {
            if (country.getContinent().equals(continent)) {
                totalPopulation += country.getPopulation();
            }
        }
        return totalPopulation;
    }

    public static void main(String[] args) {
        // Example usage
        List<Country> countries = List.of(
                new Country("Asia", 1000000000),
                new Country("Europe", 500000000),
                new Country("Asia", 800000000)
        );
        String continent = "Asia";
        int population = getPopulation(countries, continent);
        System.out.println("Total population of " + continent + ": " + population);
    }
}