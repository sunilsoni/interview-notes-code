package com.interview.notes.code.year.y2024.feb24.test4;

import java.util.*;

public class CommonFlightDataFinder1 {

    // Example execution
    public static void main(String[] args) {
        CommonFlightDataFinder1 finder = new CommonFlightDataFinder1();

        // Sample lists
        List<String> origins = List.of("123ORDDFW", "456SFOLAX", "456SFOLAX");
        List<String> stopovers = List.of("456SFOLAX", "123ORDDFW");
        List<String> destinations = List.of("123DFWORD", "345NYCLON", "123ORDDFW");

        // Find common elements
        List<String> commonElements = finder.findCommonElements(origins, stopovers, destinations);
        System.out.println(commonElements);
    }

    // Method to find common elements in three lists considering unique occurrence in each
    public List<String> findCommonElements(List<String> origins, List<String> stopovers, List<String> destinations) {
        // Convert lists to sets to remove duplicates within each list
        Set<String> originsSet = new HashSet<>(origins);
        Set<String> stopoversSet = new HashSet<>(stopovers);
        Set<String> destinationsSet = new HashSet<>(destinations);

        HashMap<String, Integer> map = new HashMap<>();

        // Process each set and increment count in map
        processSet(originsSet, map);
        processSet(stopoversSet, map);
        processSet(destinationsSet, map);

        // Filter and collect common elements
        List<String> commonElements = new ArrayList<>();
        for (String key : map.keySet()) {
            if (map.get(key) == 3) { // Element found in all three sets
                commonElements.add(key);
            }
        }

        return commonElements;
    }

    // Helper method to process each set and update map
    private void processSet(Set<String> set, HashMap<String, Integer> map) {
        for (String element : set) {
            map.put(element, map.getOrDefault(element, 0) + 1);
        }
    }
}
