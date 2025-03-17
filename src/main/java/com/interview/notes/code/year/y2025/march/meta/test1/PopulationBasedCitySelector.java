package com.interview.notes.code.year.y2025.march.meta.test1;

import java.util.*;
/*
Given a list of city names and their corresponding populations, write a program to output a city name subject to the following constraint: •
the probability of the program to output a city's name is based on its population divided by the sum-of all cities' population.-
Assume the program will be repeatedly called many times.
For example:
NY: • 7M-->- 7/20
SF: • 5M•->- 5/20
LA: 8M- -> 8/20|
 */
public class PopulationBasedCitySelector {
    
    // Store city names and their cumulative probability thresholds
    private final String[] cities;
    private final double[] cumulativeProbabilities;
    private final Random random;
    
    /**
     * Constructor that takes a map of cities and their populations
     * @param cityPopulations Map with city names as keys and populations as values
     */
    public PopulationBasedCitySelector(Map<String, Integer> cityPopulations) {
        // Initialize random number generator
        this.random = new Random();
        
        // Calculate total population for probability calculation
        long totalPopulation = 0;
        for (int population : cityPopulations.values()) {
            totalPopulation += population;
        }
        
        // Allocate arrays based on number of cities
        int n = cityPopulations.size();
        this.cities = new String[n];
        this.cumulativeProbabilities = new double[n];
        
        // Track the running sum of probabilities
        double cumulativeProbability = 0.0;
        int index = 0;
        
        // Fill the arrays with data
        for (Map.Entry<String, Integer> entry : cityPopulations.entrySet()) {
            cities[index] = entry.getKey();
            // Add this city's probability to the running sum
            cumulativeProbability += (double) entry.getValue() / totalPopulation;
            cumulativeProbabilities[index] = cumulativeProbability;
            index++;
        }
    }
    
    /**
     * Selects a city based on weighted probability
     * @return Selected city name
     */
    public String selectCity() {
        // Generate random number between 0.0 and 1.0
        double randomValue = random.nextDouble();
        
        // Find the appropriate city using binary search
        int index = Arrays.binarySearch(cumulativeProbabilities, randomValue);
        
        // If exact value not found, binarySearch returns (-(insertion point) - 1)
        if (index < 0) {
            index = -index - 1;
        }
        
        return cities[index];
    }
    
    /**
     * Main method for testing
     */
    public static void main(String[] args) {
        // Example data from the problem
        Map<String, Integer> cityPopulations = new HashMap<>();
        cityPopulations.put("NY", 7000000);  // 7M
        cityPopulations.put("SF", 5000000);  // 5M
        cityPopulations.put("LA", 8000000);  // 8M
        
        PopulationBasedCitySelector selector = new PopulationBasedCitySelector(cityPopulations);
        
        // Test our selector by running many iterations and counting results
        int iterations = 1000000;
        Map<String, Integer> results = new HashMap<>();
        
        for (int i = 0; i < iterations; i++) {
            String selectedCity = selector.selectCity();
            results.put(selectedCity, results.getOrDefault(selectedCity, 0) + 1);
        }
        
        // Print results and expected probabilities
        System.out.println("City Selection Test Results:");
        System.out.println("==========================");
        System.out.println("City | Count | Actual % | Expected %");
        System.out.println("---------------------------------");
        
        for (Map.Entry<String, Integer> entry : results.entrySet()) {
            String city = entry.getKey();
            int count = entry.getValue();
            double actualPercent = (double) count / iterations * 100;
            double expectedPercent = (double) cityPopulations.get(city) / 20000000 * 100;
            
            System.out.printf("%s | %d | %.2f%% | %.2f%%\n", 
                              city, count, actualPercent, expectedPercent);
        }
        
        System.out.println("\nTest with more cities:");
        testWithMoreCities();
    }
    
    /**
     * Additional test with more cities and varying populations
     */
    public static void testWithMoreCities() {
        Map<String, Integer> moreCities = new HashMap<>();
        moreCities.put("Tokyo", 37400000);
        moreCities.put("Delhi", 30290000);
        moreCities.put("Shanghai", 27058000);
        moreCities.put("São Paulo", 22043000);
        moreCities.put("Mexico City", 21782000);
        
        PopulationBasedCitySelector selector = new PopulationBasedCitySelector(moreCities);
        
        // Calculate total population for expected probability
        long totalPop = moreCities.values().stream().mapToLong(Integer::longValue).sum();
        
        // Run test selections
        int iterations = 500000;
        Map<String, Integer> results = new HashMap<>();
        
        for (int i = 0; i < iterations; i++) {
            String city = selector.selectCity();
            results.put(city, results.getOrDefault(city, 0) + 1);
        }
        
        // Print results for this test
        System.out.println("City | Count | Actual % | Expected %");
        System.out.println("---------------------------------");
        
        for (Map.Entry<String, Integer> entry : results.entrySet()) {
            String city = entry.getKey();
            int count = entry.getValue();
            double actualPercent = (double) count / iterations * 100;
            double expectedPercent = (double) moreCities.get(city) / totalPop * 100;
            
            System.out.printf("%s | %d | %.2f%% | %.2f%%\n", 
                              city, count, actualPercent, expectedPercent);
        }
    }
}
