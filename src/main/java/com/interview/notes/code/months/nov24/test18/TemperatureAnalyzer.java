package com.interview.notes.code.months.nov24.test18;

import java.util.ArrayList;
import java.util.List;

public class TemperatureAnalyzer {

    public static void main(String[] args) {
        List<Integer> temps = List.of(30, 32, 33, 33, 29, 38, 38, 40);
        int n = 3; // Number of days to consider for each window

        // Call method to get max and min temperatures for each window
        List<String> result = getMaxMinTemps(temps, n);

        // Print the result
        result.forEach(System.out::println);
    }

    // Method to calculate max and min temperatures for each sliding window of size n
    public static List<String> getMaxMinTemps(List<Integer> temps, int n) {
        List<String> result = new ArrayList<>();

        for (int i = 0; i <= temps.size() - n; i++) {
            List<Integer> window = temps.subList(i, i + n);  // Get the current window of size n
            int maxTemp = window.stream().max(Integer::compareTo).orElseThrow();
            int minTemp = window.stream().min(Integer::compareTo).orElseThrow();

            result.add("Window [" + (i + 1) + "-" + (i + n) + "] -> Max: " + maxTemp + ", Min: " + minTemp);
        }

        return result;
    }
}
